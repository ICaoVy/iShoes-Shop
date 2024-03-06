<%-- 
    Document   : product
    Created on : Feb 24, 2024, 1:56:05 PM
    Author     : ASUS
--%>

<%@page import="Models.Search"%>
<%@page import="DAOs.SearchDAO"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page import="DAOs.CategoryDAO"%>
<%@page import="DAOs.CartDAO"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.List"%>
<%@page import="Models.Product"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="DAOs.ProductDAO"%>
<%@page import="DAOs.AccountDAO"%>
<%@page import="Models.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="Website Icon" href="../images/LogoICafy.png" type="png" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css" />
        <link rel="stylesheet" href="../lib/bootstrap/bootstrap_css/bootstrap.min.css" />
        <link rel="stylesheet" href="../vi/css/style.css" />
        <title>iShoes</title>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
                integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"
        defer></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
                integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"
        defer></script>

        <style>
            .quantity-order{
                position: absolute;
                margin-left: 15px;
                margin-bottom: 10px;
            }
        </style>
    </head>

    <body>
        <header>
            <div class="mt-3 head-close">
                <nav class="container">
                    <div class="row">
                        <div class="col-md-3 header-1 ">
                            <a href="/"><img width="70px" src="../images/LogoICafy-removebg-preview.png" width="container-fluid"
                                             alt="logo" /></a>
                            <i id="nodebar" class="bi bi-list"></i>
                        </div>

                        <div id="hihi" class="col-md-6 clickClose">
                            <ul class="nav mt-3">
                                <li class="nav-item"><a class="nav-link" id="current-main" href="/">Home</a></li>
                                <li class="nav-item"><a class="nav-link" href="/ProductController">Product</a></li>
                                <li class="nav-item"><a class="nav-link" href="/IntroduceController">Introduce</a></li>
                                <li class="nav-item"><a class="nav-link" href="/ContactController">Contact</a></li>
                            </ul>
                        </div>
                        <%
                            Cookie cookies[] = request.getCookies();
                            String fullname = "";
                            int cus_id = 0;
                            if (cookies != null) {
                                for (int i = 0; i < cookies.length; i++) {
                                    if (cookies[i].getName().equals("fullC")) {
                                        fullname = cookies[i].getValue();
                                    } else if (cookies[i].getName().equals("idC")) {
                                        cus_id = Integer.parseInt(cookies[i].getValue());
                                    }
                                }
                            }
                            CartDAO ctDAO = new CartDAO();
                            AccountDAO ad = new AccountDAO();
                            int count = ctDAO.getCountCartByCusID(cus_id);
                            String noLogin = "";
                            String yeslogin = "";
                            Customer infor = (Customer) session.getAttribute("cus_inf");
                            if (fullname != "") {
                                noLogin = "none";
                                yeslogin = "";
                            } else {
                                noLogin = "";
                                yeslogin = "none";
                            }
                        %>
                        <div style="display: <%= noLogin%>" id="hehe" class="header-card col-md-3 text-end mt-3 clickClose">
                            <div class="row">
                                <div class="col-md-8">
                                    <a href="/LoginController"><i class="bi bi-cart2"></i></a>
                                </div>
                                <div class="col-md-2">
                                    <a href="/LoginController"><i class="bi bi-person-circle"></i></a>
                                </div>
                            </div>
                        </div>


                        <div style="display: <%= yeslogin%>" id="hehe" class="header-card col-md-3 text-end mt-3 clickClose">
                            <div class="row">
                                <div style="position: relative" class="col-md-6 text-end">
                                    <%
                                        String checkCount = "";
                                        if (count == 0) {
                                            checkCount = "none";
                                        }
                                    %>
                                    <span class="align-items-center quantity-order" style="display: <%= checkCount%> ;border-radius:100px ; color: white;background: red; padding: 0px 6px; width: fit-content;"><%= count%></span>
                                    <a href="/CartController"><i class="bi bi-cart2"></i></a>
                                </div>
                                <div class="col-md-4 text-start mt-1">
                                    <div class="btn-group col-md-3">
                                        <button style="border: none;" type="button" class="btn btn-dark dropdown-toggle"
                                                data-bs-toggle="dropdown"><span class="username"><%= ad.decodeString(fullname)%></span></button>
                                        <div class="dropdown-menu menu-homeC">
                                            <a href="#" class="dropdown-item">Profile</a>
                                            <a href="../purchasehistory.jsp" class="dropdown-item">History Bought</a>
                                            <a href="#" class="dropdown-item">...</a>
                                            <form class="dropdown-item" action="LogoutController" method="post">
                                                <button name="btnlogout" style="background: none;color: black; border: none">Logout</button>
                                            </form>
                                            <!--                                        <a href="/LogoutController" class="dropdown-item">Logout</a>-->
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>



                    </div>
                </nav>
            </div>
            <div class="container mt-3 text-center">
                <!-- Form tìm kiếm -->
                <form action="" class="d-flex justify-content-center">
                    <div class="search input-group">
                        <input class="form-control" type="text" placeholder="Search" />
                        <button><i class="bi bi-search"></i></button>
                    </div>
                </form>
            </div>
        </header>
        <%

            String checkCateName = "";
            String checkPrice = "";
            String checkSize = "";
            String checkColour = "";
            String checkCategory = "";
            String nameD = (String) session.getAttribute("nameD");
            System.out.println(nameD);
            if (request.getParameter("nameCate") != null) {
                checkCateName = "&" + "nameCate=" + request.getParameter("nameCate");
            }
            if (request.getParameter("price") != null) {
                checkPrice = "&" + "price=" + request.getParameter("price");
            }
            if (request.getParameter("size") != null) {
                checkSize = "&" + "size=" + request.getParameter("size");
            }
            if (request.getParameter("colour") != null) {
                checkColour = "&" + "colour=" + request.getParameter("colour");
            }
            if (request.getParameter("category") != null) {
                checkCategory = "&" + "category=" + request.getParameter("category");
            }

            String pathJSP = checkCateName + checkPrice + checkSize + checkColour;
            String pathForSize = checkCateName + checkPrice + checkColour;
            String pathForCate = checkPrice + checkSize + checkColour;
            String pathForColour = checkCateName + checkPrice + checkSize;
            String pathForPrice = checkCateName + checkSize + checkColour;
            String pathForCategory = checkCateName + checkPrice + checkSize + checkColour;
        %>

        <main class="main-close mt-3">
            <div class="container">
                <div class="row left-pro-position">
                    <div class="col-md-3 left-pro">
                        <h3 class="title-iShoes">iShoes All (387)</h3><!-- comment -->
                        <div  class="left-scroll">
                            <div class="luxury border-title">
                                <h5 id="luxuryClick"><a style="text-decoration: none; color: black;" href="/ProductController/Luxury">Luxury</a><i  onclick="luxClick()" class="bi bi-caret-1 bi-chevron-up"></i>
                                </h5>
                                <ul class="cat-title-show luxuryUL">
                                    <%
                                        CategoryDAO cgDAO = new CategoryDAO();
                                        ResultSet rscate = cgDAO.getLuxyry();
                                        while (rscate.next()) {
                                    %>
                                    <li><a href="/ProductController/Luxury?nameCate=<%= rscate.getString("cate_name") + pathForCate%>"><%= rscate.getString("cate_name")%></a></li>
                                        <%
                                            }
                                        %>


                                </ul>
                            </div>
                            <div class="sport border-title mt-2">
                                <h5 id="sportClick"><a style="text-decoration: none; color: black;" href="/ProductController/Sport">Sports</a><i onclick="sportClick()" class="bi bi-caret-2 bi-chevron-up"></i>
                                </h5>
                                <ul class="cat-title-show sportUL">
                                    <%
                                        ResultSet rscate1 = cgDAO.getSport();
                                        while (rscate1.next()) {
                                    %>
                                    <li><a href="/ProductController/Sport?nameCate=<%= rscate1.getString("cate_name") + pathForCate%>"><%= rscate1.getString("cate_name")%></a></li>
                                        <%
                                            }
                                        %>
                                </ul>
                            </div>
                            <div class="sandal border-title mt-2">
                                <h5 id="sandalClick"><a style="text-decoration: none; color: black;" href="/ProductController/Sandal">Sandal</a><i onclick="SandalClick()"
                                                                                                                                                   class="bi bi-caret-3 bi-chevron-up"></i></h5>
                                <ul class="cat-title-show sandalUL">
                                    <%
                                        ResultSet rscate2 = cgDAO.getSandal();
                                        while (rscate2.next()) {
                                    %>
                                    <li><a href="/ProductController/Sandal?nameCate=<%= rscate2.getString("cate_name") + pathForCate%>"><%= rscate2.getString("cate_name")%></a></li>
                                        <%
                                            }
                                        %>

                                </ul>
                            </div>
                            <!-- Shop by price -->
                            <div class="shop-by-price border-title mt-2">
                                <h5 onclick="PriceClick()">Price<i class="bi bi-caret-4 bi-chevron-down"></i></h5>

                                <ul class="cat-title-show dot-1 priceUL">
                                    <li>
                                        <a href="/ProductController/<%= nameD%>?<%= "price=1000000" + pathForPrice%>">
                                            <input type="checkbox" name="priceCheckbox" >
                                            <span>1.000.000vnd <i class="bi bi-arrow-down"></i></span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="/ProductController/<%= nameD%>?<%= "price=1000000-2000000" + pathForPrice%>">
                                            <input type="checkbox">
                                            <span>1.000.000vnd - 2.000.000vnd</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="/ProductController/<%= nameD%>?<%= "price=2000000-5000000" + pathForPrice%>">
                                            <input type="checkbox">
                                            <span>2.000.000vnd - 5.000.000vnd</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="/ProductController/<%= nameD%>?<%= "price=5000000" + pathForPrice%>">
                                            <input type="checkbox" <%= (2 == 5000000) ? "checked" : ""%>>
                                            <span>5.000.000vnd <i class="bi bi-arrow-up"></i></span>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                            <!-- Size -->
                            <div class="size border-title mt-2">
                                <h5 onclick="SizeClick()">Size<i class="bi bi-caret-5 bi-chevron-down"></i></h5>
                                <div class="size-shoes sizeUL">
                                    <a href="/ProductController/<%= nameD%>?<%=  "size=35" + pathForSize%>">35</a>
                                    <a href="/ProductController/<%= nameD%>?<%=  "size=36" + pathForSize%>">36</a>
                                    <a href="/ProductController/<%= nameD%>?<%=  "size=37" + pathForSize%>">37</a>
                                    <a href="/ProductController/<%= nameD%>?<%=  "size=38" + pathForSize%>">38</a>
                                    <a href="/ProductController/<%= nameD%>?<%=  "size=39" + pathForSize%>">39</a>
                                    <a href="/ProductController/<%= nameD%>?<%=  "size=40" + pathForSize%>">40</a>
                                    <a href="/ProductController/<%= nameD%>?<%=  "size=41" + pathForSize%>">41</a>
                                    <a href="/ProductController/<%= nameD%>?<%=  "size=42" + pathForSize%>">42</a>
                                    <a href="/ProductController/<%= nameD%>?<%=  "size=43" + pathForSize%>">43</a>
                                    <a href="/ProductController/<%= nameD%>?<%=  "size=44" + pathForSize%>">44</a>
                                    <a href="/ProductController/<%= nameD%>?<%=  "size=45" + pathForSize%>">45</a>


                                </div>
                            </div>
                            <!-- Color -->
                            <div class="color border-title mt-2">
                                <h5 onclick="ColourClick()">Colour<i class="bi bi-caret-6 bi-chevron-down"></i></h5>
                                <div class="color-manager colourUL">
                                    <a class="text-center" href="/ProductController/<%= nameD%>?<%="colour=Black" + pathForColour%>">
                                        <div style="background-color: black;"><i class="bi bi-check2"></i></div>
                                        <p>Black</p>
                                    </a>
                                    <a class="text-center" href="/ProductController/<%= nameD%>?<%=  "colour=Blue" + pathForColour%>">
                                        <div style="background-color: #40A2D8;"><i class="bi bi-check2"></i></div>
                                        <p>Blue</p>
                                    </a>
                                    <a class="text-center" href="/ProductController/<%= nameD%>?<%=  "colour=Brown" + pathForColour%>">
                                        <div style="background-color: #B99470;"><i class="bi bi-check2"></i></div>
                                        <p>Brown</p>
                                    </a>
                                    <a class="text-center" href="/ProductController/<%= nameD%>?<%=  "colour=Green" + pathForColour%>">
                                        <div style="background-color: #99BC85;"><i class="bi bi-check2"></i></div>
                                        <p>Green</p>
                                    </a>
                                    <a class="text-center" href="/ProductController/<%= nameD%>?<%=  "colour=Grey" + pathForColour%>">
                                        <div style="background-color: #B6BBC4;"><i class="bi bi-check2"></i></div>
                                        <p>Grey</p>
                                    </a>
                                    <a class="text-center" href="/ProductController/<%= nameD%>?<%=  "colour=Multi" + pathForColour%>">
                                        <div style="background: url(./images/multi-colour.jpg)"><i class="bi bi-check2"></i>
                                        </div>
                                        <p>Multi</p>
                                    </a>
                                    <a class="text-center" href="/ProductController/<%= nameD%>?<%=  "colour=Orange" + pathForColour%>">
                                        <div style="background-color: #EA906C;"><i class="bi bi-check2"></i></div>
                                        <p>Orange</p>
                                    </a>
                                    <a class="text-center" href="/ProductController/<%= nameD%>?<%=  "colour=Pink" + pathForColour%>">
                                        <div style="background-color: #E6A4B4;"><i class="bi bi-check2"></i></div>
                                        <p>Pink</p>
                                    </a>
                                    <a class="text-center" href="/ProductController/<%= nameD%>?<%=  "colour=Purple" + pathForColour%>">
                                        <div style="background-color: #756AB6;"><i class="bi bi-check2"></i></div>
                                        <p>Purple</p>
                                    </a>
                                    <a class="text-center" href="/ProductController/<%= nameD%>?<%=  "colour=Red" + pathForColour%>">
                                        <div style="background-color: #DC3535;"><i class="bi bi-check2"></i></div>
                                        <p>Red</p>
                                    </a>
                                    <a class="text-center" href="/ProductController/<%= nameD%>?<%=  "colour=White" + pathForColour%>">
                                        <div style="background-color: white; border: solid 1px rgb(143, 142, 141);"><i
                                                class="bi bi-check2"></i></div>
                                        <p>White</p>
                                    </a>
                                    <a class="text-center" href="/ProductController/<%= nameD%>?<%=  "colour=Other" + pathForColour%>">
                                        <div style="background-color:rgb(139, 139, 139);"><i style="color: white;"
                                                                                             class="bi bi-layers"></i></div>
                                        <p>Other</p>
                                    </a>
                                </div>
                            </div>
                            <!-- Brand -->

                        </div>
                    </div>
                    <div class="col-md-9 right-pro text-center">
                        <div class="container">
                            <div class="row">
                                <button onclick="FiltersButton()" class="buttonFilters col-md-3">Hide Filters <i class="bi bi-sliders"></i></button>
                                <div class="col-md-2 text-start mt-1">
                                    <div class="col-md-3">
                                        <button style="border: none;" type="button" class="sortBy btn btn-dark dropdown-toggle text-center"
                                                data-bs-toggle="dropdown"><span class="">Sort By <i class="bi bi-funnel"></i></span></button>
                                        <div class="dropdown-menu">
                                            <a href="/ProductController/<%= nameD%>?sort=High_Low<%=pathJSP%>" class="dropdown-item">Price: Hight-Low</a>
                                            <a href="/ProductController/<%= nameD%>?sort=Low_High<%=pathJSP%>" class="dropdown-item">Price: Low-Hight</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div style="margin-bottom: 300px" class="row mt-3 list-product-right">
                                <%
                                    System.out.println(request.getParameter("price"));
                                    SearchDAO sDAO = new SearchDAO();
                                    List<Search> productList = null;
                                    String nameCate1 = request.getParameter("nameCate");
                                    String price1 = request.getParameter("price");
                                    String size1 = request.getParameter("size");
                                    String colour1 = request.getParameter("colour");
                                    if (nameCate1 != null && price1 != null && size1 != null && colour1 != null) {
                                        if (price1.equals("1000000")) {
                                            productList = sDAO.getProductByAllSearchPriceLow(nameCate1, Integer.parseInt(size1), colour1, Float.parseFloat(price1), nameD);
                                        } else if (price1.contains("-")) {
                                            String s[] = price1.split("-");
                                            productList = sDAO.getProductByAllSearchFromTo(nameCate1, Integer.parseInt(size1), colour1, Float.parseFloat(s[0]), Float.parseFloat(s[1]), nameD);
                                        } else {
                                            productList = sDAO.getProductByAllSearchPriceHigh(nameCate1, Integer.parseInt(size1), colour1, Float.parseFloat(price1), nameD);
                                        }
                                    } else if (nameCate1 != null || price1 != null || size1 != null || colour1 != null) {
                                        if (nameCate1 != null && price1 != null && size1 != null) {
                                            if (price1.equals("1000000")) {
                                                productList = sDAO.getProductByAllSearchPriceLow3(nameCate1, Integer.parseInt(size1), Float.parseFloat(price1), nameD);
                                            } else if (price1.contains("-")) {
                                                String s[] = price1.split("-");
                                                productList = sDAO.getProductByAllSearchFromTo3(nameCate1, Integer.parseInt(size1), Float.parseFloat(s[0]), Float.parseFloat(s[1]), nameD);
                                            } else {
                                                productList = sDAO.getProductByAllSearchPriceHigh3(nameCate1, Integer.parseInt(size1), Float.parseFloat(price1), nameD);
                                            }
                                        } else if (nameCate1 != null && price1 != null && colour1 != null) {
                                            if (price1.equals("1000000")) {
                                                productList = sDAO.getProductByAllSearchPriceNoSizeLow3(nameCate1, colour1, Float.parseFloat(price1), nameD);
                                            } else if (price1.contains("-")) {
                                                String s[] = price1.split("-");
                                                productList = sDAO.getProductByAllSearchNoSizeFromTo3(nameCate1, colour1, Float.parseFloat(s[0]), Float.parseFloat(s[1]), nameD);
                                            } else {
                                                productList = sDAO.getProductByAllSearchPriceNoSizeHigh3(nameCate1, colour1, Float.parseFloat(price1), nameD);
                                            }
                                        } else if (nameCate1 != null && size1 != null && colour1 != null) {
                                            productList = sDAO.getProductByAllSearchNoPrice3(nameCate1, Integer.parseInt(size1), colour1, nameD);
                                        } else if (price1 != null && size1 != null && colour1 != null) {
                                            if (price1.equals("1000000")) {
                                                productList = sDAO.getProductByAllSearchPriceNoNameLow3(Integer.parseInt(size1), colour1, Float.parseFloat(price1), nameD);
                                            } else if (price1.contains("-")) {
                                                String s[] = price1.split("-");
                                                productList = sDAO.getProductByAllSearchNoNameFromTo(Integer.parseInt(size1), colour1, Float.parseFloat(s[0]), Float.parseFloat(s[1]), nameD);
                                            } else {
                                                productList = sDAO.getProductByAllSearchPriceNoNameHigh3(Integer.parseInt(size1), colour1, Float.parseFloat(price1), nameD);
                                            }
                                        } else if (nameCate1 != null && price1 != null) {
                                            if (price1.equals("1000000")) {
                                                productList = sDAO.getProductByAllSearchNoSizeColorLow2(nameCate1, Float.parseFloat(price1), nameD);
                                            } else if (price1.contains("-")) {
                                                String s[] = price1.split("-");
                                                productList = sDAO.getProductByAllSearchNoSizeColorFromTo2(nameCate1, Float.parseFloat(s[0]), Float.parseFloat(s[1]), nameD);
                                            } else {
                                                productList = sDAO.getProductByAllSearchNoSizeColorHigh2(nameCate1, Float.parseFloat(price1), nameD);
                                            }
                                        } else if (nameCate1 != null && size1 != null) {
                                            productList = sDAO.getProductByAllSearchNameAndSize2(nameCate1, Integer.parseInt(price1), nameD);
                                        } else if (nameCate1 != null && colour1 != null) {
                                            productList = sDAO.getProductByAllSearchNameAndColor2(nameCate1, colour1, nameD);
                                        } else if (price1 != null && size1 != null) {
                                            if (price1.equals("1000000")) {
                                                productList = sDAO.getProductByAllSearchPriceSizeLow2(Integer.parseInt(size1), Float.parseFloat(price1), nameD);
                                            } else if (price1.contains("-")) {
                                                String s[] = price1.split("-");
                                                productList = sDAO.getProductByAllSearchPriceSizeFromTo2(Integer.parseInt(size1), Float.parseFloat(s[0]), Float.parseFloat(s[1]), nameD);
                                            } else {
                                                productList = sDAO.getProductByAllSearchPriceSizeHigh2(Integer.parseInt(size1), Float.parseFloat(price1), nameD);
                                            }
                                        } else if (price1 != null && colour1 != null) {
                                            //productList = sDAO.getProductByAllSearchPriceCollor2(Float.parseFloat(price1), colour1);
                                            if (price1.equals("1000000")) {
                                                productList = sDAO.getProductByAllSearchPriceColorLow2(colour1, Float.parseFloat(price1), nameD);
                                            } else if (price1.contains("-")) {
                                                String s[] = price1.split("-");
                                                productList = sDAO.getProductByAllSearchPriceColorFromTo2(colour1, Float.parseFloat(s[0]), Float.parseFloat(s[1]), nameD);
                                            } else {
                                                productList = sDAO.getProductByAllSearchPriceColorHigh2(colour1, Float.parseFloat(price1), nameD);
                                            }
                                        } else if (size1 != null && colour1 != null) {
                                            productList = sDAO.getProductByAllSearchSizeColor(Integer.parseInt(size1), colour1, nameD);
                                        } else if (nameCate1 != null) {
                                            productList = sDAO.getProductByCateName(nameCate1, nameD);
                                        } else if (price1 != null) {
                                            if (price1.equals("1000000")) {
                                                productList = sDAO.getProductByPriceLow1(Float.parseFloat(price1), nameD);
                                            } else if (price1.contains("-")) {
                                                String s[] = price1.split("-");
                                                productList = sDAO.getProductByPriceFromTo2(Float.parseFloat(s[0]), Float.parseFloat(s[1]), nameD);
                                            } else {
                                                productList = sDAO.getProductByPriceHigh1(Float.parseFloat(price1), nameD);
                                            }
                                        } else if (size1 != null) {
                                            productList = sDAO.getProductBySize(Integer.parseInt(size1), nameD);
                                        } else if (colour1 != null) {
                                            productList = sDAO.getProductByColour(colour1, nameD);
                                        }
                                    } else {
                                        productList = sDAO.getAllProduct(nameD);
                                    }

                                    if (session.getAttribute("search") != null) {
                                        productList = sDAO.getProduceSearchName((String) session.getAttribute("search"));
                                        session.removeAttribute("search");
                                    }

                                    if (!productList.isEmpty()) {

                                        if (request.getParameter("sort") != null) {
                                            if (request.getParameter("sort").equalsIgnoreCase("High_Low")) {
                                                Comparator<Search> priceComparator = new Comparator<Search>() {
                                                    @Override
                                                    public int compare(Search p1, Search p2) {
                                                        if (p1.getPro_price() < p2.getPro_price()) {
                                                            return 1;
                                                        } else if (p1.getPro_price() > p2.getPro_price()) {
                                                            return -1;
                                                        } else {
                                                            return 0;
                                                        }
                                                    }
                                                };

                                                Collections.sort(productList, priceComparator);
                                            } else if (request.getParameter("sort").equalsIgnoreCase("Low_High")) {
                                                Comparator<Search> priceComparator = new Comparator<Search>() {
                                                    @Override
                                                    public int compare(Search p1, Search p2) {
                                                        if (p1.getPro_price() > p2.getPro_price()) {
                                                            return 1;
                                                        } else if (p1.getPro_price() < p2.getPro_price()) {
                                                            return -1;
                                                        } else {
                                                            return 0;
                                                        }
                                                    }
                                                };

                                                Collections.sort(productList, priceComparator);
                                            }
                                        }
                                        Set<String> uniqueProductCodes = new HashSet<>();

                                        for (Search l : productList) {
                                            String productCode = sDAO.subString(l.getPro_code());
                                            if (uniqueProductCodes.add(productCode)) {
                                %>
                                <div onmouseenter="ShowOther(event)" onmouseleave="HideOther(event)" class="col-md-4 text-center show-list-pro">
                                    <a class="event-mouse-a" href="/ProductController/Details?id=<%= l.getPro_id()%>&proCode=<%= l.getPro_code()%>"">
                                        <img width="100%" class="images-change" src="../images/<%= l.getPro_picture()%>" class="img-fluid" alt="">
                                    </a>
                                    <div class="mt-1 text-start check-other show-list-other">
                                        <%
                                            List<Product> productListChild = sDAO.getProduceCode(sDAO.subString(l.getPro_code()));
                                            Set<String> uniqueProductCodes1 = new HashSet<>();
                                            for (Product lc : productListChild) {

                                                if (uniqueProductCodes1.add(lc.getPro_code())) {
                                        %>

                                        <a class="hover1" href="/ProductController/Details?id=<%= lc.getPro_id()%>"><img src="../images/<%= lc.getPro_picture()%>" class="img-fluid" width="40px" alt="<%= lc.getPro_picture()%>"></a>

                                        <%
                                                }
                                            }
                                        %>
                                    </div>
                                    <p class="text-start mt-2"><%= l.getPro_name()%><br>
                                        <strong><%= l.getPro_price()%></strong>
                                    </p>

                                </div>
                                <%
                                        }
                                    }
                                } else {
                                %>

                                <h3>Product Not Found!!!</h3>

                                <%
                                    }
                                %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>

        <footer class="container footer mt-3 pt-3">

            <div class="row">
                <div class="col-md-3">
                    <h4>About ICAFY</h4>
                    <p>ICAFY là một trung tâm mua sắm trực tuyến đa dạng và phong cách, chuyên về việc cung cấp những sản
                        phẩm và dịch vụ tối ưu cho khách hàng trên khắp mọi nơi.</p>
                </div>
                <div class="col-md-3">
                    <h4>Address</h4>
                    <p>- Cần Thơ:</p>
                    <p>
                        600 Nguyễn Văn Cừ nối dài, An Bình, Ninh Kiều, Cần Thơ 900000, Việt Nam</p>
                    <div>Phone: 0907626222</div>
                    <div>Email: icafy.vn@gmail.com</div>
                </div>
                <div id="hotro" class="col-md-3">
                    <h4>Hổ trợ khách hàng</h4>
                    <div>
                        <ul>
                            <li><a href="#">Giới thiệu ICao Store</a></li>
                            <li><a href="#">Hướng đẫn đặt hàng</a></li>
                            <li><a href="#">Chính sách bảo mật</a></li>
                            <li><a href="#">Thông tin sở hữu</a></li>
                        </ul>
                    </div>
                </div>

                <div class="col-md-3">

                    <h5>Follow Us</h5>
                    <div class="follow-us">
                        <div class="follow-div"><i class="bi bi-facebook"></i></div>
                        <div class="follow-div"><i class="bi bi-instagram"></i></div>
                        <div class="follow-div"><i class="bi bi-youtube"></i></div>
                        <div class="follow-div"><i class="bi bi-google"></i></div>
                    </div>

                </div>
            </div>
        </div>



        <div class="container-fluid text-center pt-3">
            <span>&copy; Copyright 2023, Design by Ha Cao Vi</span>
        </div>
    </footer>


    <script src="../lib/bootstrap/bootstrap_js/bootstrap.min.js"></script>
    <script src="../vi/js/main.js"></script>
</body>
</html>
