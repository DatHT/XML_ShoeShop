<%-- 
    Document   : index
    Created on : Feb 2, 2016, 3:35:22 PM
    Author     : Daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thành Đạt Shop</title>
        <script src="js/MyJavascript.js" type="text/javascript"></script>
        <link href="css/styles.css" rel="stylesheet" type="text/css" />

        <link href='https://fonts.googleapis.com/css?family=Roboto+Condensed&subset=latin,vietnamese' rel='stylesheet' type='text/css'>
        <script>

            var regObj = '${requestScope.SHOES}';
            var xmlDom = new ActiveXObject("Microsoft.XMLDOM");
            var shoeCount = '${requestScope.NUMOFRESULT}';
            var curPage = 1;
            var shoePerPage = 9;
            var numOfPage = 0;
            var cells = [];
            var count = 0;
            var resultAddShoe = "";
            var trackingCateId = -1;
            var modeSearch = 0; // 0 is search, 1 is category, 2 is search price

            

            function enableCategory(cate) {
                var banner = document.getElementsByClassName("banner");
                banner[0].style.display = "none";
                document.getElementById("sub-header").innerHTML = cate;

            }

            function refesh() {
                var banner = document.getElementsByClassName("banner");
                banner[0].style.display = "block";
                document.getElementById("sub-header").innerHTML = 'Sản phẩm nhiều người mua';
            }

        </script>
    </head>
    <body onload="searchProcess('search', '', 0, shoePerPage, '')">

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
                                                <a onclick="searchProcess('category',${subItem.id}, 0, shoePerPage, '${subItem.name}')">${subItem.name}</a>
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
                            <li class="search-button"><a href="#" onclick="searchProcess('search', '', 0, shoePerPage, '')"><img src="images/search-icon.png" alt="themedemic" /></a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="banner">
                <div class="banner-content">
                    <h1>Chất lượng tuyệt vời</h1>
                    <h2>Đầy đủ các mẫu hợp thời trang cho nam và nữ </h2>
                </div>
            </div>
        </div>
        <div class="main-content-wrapper">
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
        <div class="copyrights">Copyright (c) Thành Đạt Shop</div>
    </body>
</html>
