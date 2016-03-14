/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function setCartNumber() {
    var cart = sessionStorage.getItem('ShoeCart');
    var quantity = 0;
    if (cart != null) {
        var cartObj = JSON.parse(cart);
        for (var i = 0; i < cartObj.length; i++) {
            quantity += cartObj[i].quantity;
        }
        document.getElementById("cart-num").innerHTML = 'Giỏ hàng [' + quantity + ']';
    }
}

function getXmlHttpObject() {
    var xmlHttp = null;
    try {
        xmlHttp = new XMLHttpRequest();
    } catch (e) {
        try {
            xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
    }
    return xmlHttp;
}

function addShoe(cells) {
    resultAddShoe += '<li class="gallery pad-right2">'
            + '<a href="shoeDetail.jsp?shoeId=' + cells[0] + '">'
            + '<div class="imageHolder">'
            + '<img class="image-border" src="/static/' + cells[3] + '" />'
            + '<div class="caption"><br>' + parseInt(cells[2]).toLocaleString() + ' vnd</div>'
            + '</div>'
            + '<div class="text-content">' + cells[1] + '</div>'
            + '</a>'
            + '</li>';
    document.getElementById("shoes-content").innerHTML = resultAddShoe;

}

function searchShoe(mode, node, cateId, start, end, cateName) {

    if (node == null) {
        alert("Null");
        return;
    }
    var xpathEpx = "";
    if (mode == "search") {
        modeSearch = 0;

        var searchValue = document.getElementById("search-name").value;
        if (searchValue.trim().length != 0) {
            document.getElementById("sub-header").innerHTML = 'Tìm kiếm ' + searchValue;
            var banner = document.getElementsByClassName("banner");
            if (banner.length > 0) {
                banner[0].style.display = "none";
            }

        } else {
            refesh();
        }

        xpathEpx = "//shoe[contains(name, '" + searchValue + "')]";
    } else if (mode == "category") {
        modeSearch = 1;
        if (trackingCateId == -1) {
            curPage = 1;
        }
        if (trackingCateId != cateId) {
            curPage = 1;
        }
        trackingCateId = cateId;
        xpathEpx = "//shoe[subCategoryId='" + cateId + "']";
        if (cateName.length != 0) {
            enableCategory(cateName);
        }
    } else if (mode == "search-price") {
        modeSearch = 2;
        var priceFrom = document.getElementById("priceFrom").value;
        var priceTo = document.getElementById("priceTo").value;
        xpathEpx = "//shoe[price>='" + priceFrom + "' and price<='" + priceTo + "']";
        if (priceFrom.trim().length != 0 || priceTo.trim().length) {
            document.getElementById("sub-header").innerHTML = 'Tìm kiếm ' + priceFrom.toLocaleString() + " vnd - " + priceTo.toLocaleString() + " vnd";
            var banner = document.getElementsByClassName("banner");
            if (banner.length > 0) {
                banner[0].style.display = "none";
            }

        } else {
            refesh();
        }
    }
    node.setProperty("SelectionLanguage", "XPath");
    var result = node.selectNodes(xpathEpx);
    setNumShoeOfPage(result.length);
    resultAddShoe = "";
    
    if (end > result.length) {
        end = result.length;
    }
    if (result.length != 0) {
        setPageNumber();
        for (var i = start; i < end; i++) {
            cells[0] = result.item(i).selectSingleNode("id").text;
            cells[1] = result.item(i).selectSingleNode("name").text;
            cells[2] = result.item(i).selectSingleNode("price").text;
            cells[3] = result.item(i).selectSingleNode("thumbPath").text;
            addShoe(cells);
        }
    }else {
        resultAddShoe = "";
        document.getElementById("page-num-container").innerHTML = "";
        document.getElementById("shoes-content").innerText = "Không tìm thấy sản phẩm bạn yêu cầu";
    }
}

function setNumShoeOfPage(length) {
    if (length % shoePerPage == 0) {
        numOfPage = parseInt(parseInt(length) / shoePerPage);
    } else {
        numOfPage = parseInt(parseInt(length) / shoePerPage) + 1;
    }
}

function searchProcess(mode, cateId, start, end, cateName) {
    setCartNumber();
    if (!regObj) {
        //is null
        alert("Null here");
        return false;
    }
    if (regObj) {
        xmlDom.async = false;
        xmlDom.loadXML(regObj);
        if (xmlDom.parseError.errorCode != 0) {
            alert("Error:" + xmlDom.parseError.reason);
        } else {
            count = 0;
            //parsing
            searchShoe(mode, xmlDom, cateId, start, end, cateName);
        }
    }
}

function setPageNumber() {
    var str = "";
    for (var i = 1; i < numOfPage + 1; i++) {
        str += '<button class="btn btn-default" id="page-' + i + '" onclick="goPage(' + i + ')">' + i + '</button>';
    }
    document.getElementById("page-num-container").innerHTML = str;
    document.getElementById("page-" + curPage).style.background = '#819EDA';
}


function goPage(page) {
    resultAddShoe = "";
    var start = shoePerPage * (page - 1);
    var end = shoePerPage * page;
    curPage = page;
    if (modeSearch == 0) {
        searchProcess('search', '', start, end, '');
    } else if (modeSearch == 1) {
        searchProcess('category', trackingCateId, start, end, '');
    } else if (modeSearch == 2) {
        searchProcess('search-price', '', start, end, '');
    }
    document.getElementById("page-" + curPage).style.background = '#819EDA';
}

function goPageNext() {
    if (curPage < numOfPage) {
        goPage(curPage + 1);
    }
}

function goPageBack() {
    if (curPage > 1) {
        goPage(curPage - 1);
    }
}

function enableCategory(cate) {

    document.getElementById("sub-header").innerHTML = cate;

}


