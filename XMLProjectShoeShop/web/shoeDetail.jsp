<%-- 
    Document   : shoeDetail
    Created on : Feb 7, 2016, 9:47:37 AM
    Author     : Daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Thành Đạt Shop</title>
        <script src="js/MyJavascript.js" type="text/javascript"></script>
        <link href="css/styles.css" rel="stylesheet" type="text/css" />
        
        <link href='https://fonts.googleapis.com/css?family=Roboto+Condensed&subset=latin,vietnamese' rel='stylesheet' type='text/css'>

        <script type="text/javascript">
            var shoeCount = '${requestScope.NUMOFRESULT}';
            var curPage = 1;
            var shoePerPage = 9;
            var numOfPage = parseInt(parseInt(shoeCount) / shoePerPage) + 1;
            var cells = [];
            var count = 0;
            var resultAddShoe = "";
            var numShoeOfCate;
            var trackingCateId = -1;
            var modeSearch = 0; // 0 is search, 1 is category, 2 is search price


            var shoeId = '${requestScope.shoeId}';
            var xmlHttp = null;
            var xmlDom = new ActiveXObject("Microsoft.XMLDOM");
            var regObj;
            var realPath = '${pageContext.request.contextPath}';
            //xsl path
            var xslUrl = realPath + "/ShoeDetail.xsl";
            var xsl = new ActiveXObject("Msxml2.FreeThreadedDOMDocument.6.0");
            xsl.load(xslUrl);

            function init() {
                document.getElementById("category-search").style.display = "none";
            }

            function enableSearchCategory(id, name) {
                document.getElementById("category-search").style.display = "block";
                document.getElementById("output").style.display = "none";
                regObj = '${requestScope.SHOES}';
                searchProcess('category', id, 0, shoePerPage, name);
            }

            function enableSearchName() {
                document.getElementById("category-search").style.display = "block";
                document.getElementById("output").style.display = "none";
                regObj = '${requestScope.SHOES}';
                searchProcess('search', '', 0, shoePerPage, '')
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


            function sendAjax() {
                init();
                setCartNumber();
                var xmlStr;
                xmlHttp = getXmlHttpObject();
                if (xmlHttp == null) {
                    alert("Your browser doese not support ajax");
                    return;
                }
                var url = "MarshallToJavaScript?shoeId=" + '${requestScope.shoeId}';
                xmlHttp.onreadystatechange = function () {
                    if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                        regObj = xmlHttp.responseText;

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

                                var xslTemplate = new ActiveXObject("MSXML2.XSLTemplate.6.0");
                                xslTemplate.stylesheet = xsl;
                                var xslProcessor = xslTemplate.createProcessor();
                                xslProcessor.input = xmlDom;
                                xslProcessor.addParameter("shoeId", shoeId);
                                xslProcessor.transform();
                                document.getElementById('output').innerHTML = xslProcessor.output;
                            }
                        }
                    }
                };
                xmlHttp.open("GET", url, true);
                xmlHttp.send();
            }

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

            function addToCart(id, name, thumbPath, price) {
                var shoeSize = 0;
                var sizes = document.getElementsByName('size');
                for (var i = 0; i < sizes.length; i++) {
                    if (sizes[i].checked) {
                        shoeSize = sizes[i].value;
                    }
                }

                if (shoeSize != 0) {
                    var shoeObj = {
                        id: id,
                        name: name,
                        thumbPath: thumbPath,
                        size: shoeSize,
                        price: price,
                        quantity: 1
                    };

                    if (typeof (sessionStorage) != "undefined") {
                        var cart = sessionStorage.getItem('ShoeCart');
                        if (cart != null) {
                            var cartObj = JSON.parse(cart);
                            var totalQuantity = 0;
                            var isIncart = false;
                            for (var i = 0; i < cartObj.length; i++) {
                                totalQuantity += cartObj[i].quantity;
                                if (cartObj[i].id == shoeObj.id) {
                                    if (cartObj[i].size == shoeObj.size) {
                                        cartObj[i].quantity += 1;
                                        totalQuantity += 1;
                                        isIncart = true;
                                    } else {
                                    }

                                }
                            }
                            if (!isIncart) {

                                cartObj.push(shoeObj);
                                totalQuantity += 1;
                            }
                            document.getElementById("cart-num").innerHTML = 'Giỏ hàng [' + totalQuantity + ']';
                        } else {
                            var cartObj = [];
                            cartObj.push(shoeObj);
                            document.getElementById("cart-num").innerHTML = 'Giỏ hàng [' + 1 + ']';
                        }
                        sessionStorage.setItem("ShoeCart", JSON.stringify(cartObj));
                    } else {
                        alert("Your browser does not support session storage");
                    }
                } else {
                    alert("Xin bạn vui lòng chọn size");
                }


            }
        </script>
    </head>
    <body onload="sendAjax()">
        <div class="header-wrapper">
            <div class="menu">
                <ul>
                    <li><a href="index.jsp" class="active">Trang chủ</a></li>
                        <c:set var="listCate" value="${requestScope.LISTCATE}" />
                        <c:set var="listSubCate" value="${requestScope.LISTSUBCATE}" />
                        <c:if test="${not empty listCate}">
                            <c:forEach var="item" items="${listCate}">
                            <li><a href="#" onclick="searchShoe()">${item.name}</a>
                                <div class="dropdown-content">
                                    <c:if test="${not empty listSubCate}">
                                        <c:forEach var="subItem" items="${listSubCate}">
                                            <c:if test="${subItem.categoryId == item.id}">
                                                <a onclick="enableSearchCategory(${subItem.id}, '${subItem.name}')">${subItem.name}</a>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </div>
                            </li>
                        </c:forEach>
                    </c:if>
                    
                </ul>
                <div class="account">
                    <a href="cartDetail.html" id="cart-num">Giỏ hàng [0]</a>
                </div>
            </div>
            <div class="clearing"></div>
            <div class="logo-search-wrapper">
                <div class="logo-search-container">
                    <div class="logo"> Thành Đạt Shop </div>
                    <div class="search">
                        <ul>
                            <li class="search-input">
                                <input type="text" class="search-input-textfield" id="search-name" placeholder="Search name here" />
                            </li>
                            <li class="search-button"><a href="#" onclick="enableSearchName()"><img src="images/search-icon.png" alt="themedemic" /></a></li>
                        </ul>
                    </div>
                </div>
            </div>

        </div>
        <div id="output" ></div>

        <!--Div enable category search-->
        <div class="main-content-wrapper" id="category-search">
            <div class="left-column">
                <div class="left-col-content mar-top">
                    <div class="left-col-content-title" id="title-header">
                        <h1 id="sub-header">Sản phẩm nhiều người mua</h1>
                    </div>
                    <ul id="shoes-content">

                    </ul>
                </div>
                <div class="page-num-container"> 
                    <button class="btn" onclick="goPageBack()">Back</button>
                    <div id="page-num-container">

                    </div>
                    <button class="btn" onclick="goPageNext()">Next</button>
                </div>
            </div>
            <div class="right-column">

                <div class="right-col-content mar-top">
                    <div class="right-col-content-title  ">
                        <h1>Tìm kiếm giá</h1>
                    </div>
                    <ul class="login">
                        <li>Giá từ :</li>
                        <li class="login-field-bg">
                            <label>
                                <input type="text" name="textfield" id="priceFrom" class="login-input-textfield" />
                            </label>
                        </li>
                        <li>Giá đến :</li>
                        <li class="login-field-bg">
                            <label>
                                <input type="text" name="textfield" id="priceTo" class="login-input-textfield" />
                            </label>
                        </li>
                        <li>
                            <div class="submit"><a href="#" onclick="searchProcess('search-price', '', 0, shoePerPage, '');">Tìm kiếm</a></div>
                        </li>
                    </ul>
                </div>

            </div>
        </div>
        <!--End Div enable category search-->
        <div class="copyrights">Copyright (c) Thành Đạt Shop</div>
    </body>
</html>
