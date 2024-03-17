<%-- 
    Document   : productDetails
    Created on : Feb 24, 2024, 2:59:11 PM
    Author     : ASUS
--%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.List"%>
<%@page import="Models.Product"%>
<%@page import="Models.Stock"%>
<%@page import="DAOs.CartDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.ResultSet"%>
<%@page import="DAOs.ProductDAO"%>
<%@page import="DAOs.AccountDAO"%>
<%@page import="Models.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="Website Icon" href="<%= request.getContextPath()%>/images/LogoICafy.png" type="png" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css" />
        <link rel="stylesheet" href="<%= request.getContextPath()%>/lib/bootstrap/bootstrap_css/bootstrap.min.css" />
        <link rel="stylesheet" href="<%= request.getContextPath()%>/vi/css/style.css" />
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
                            <a href="/"><img width="70px" src="<%= request.getContextPath()%>/images/LogoICafy-removebg-preview.png" width="container-fluid"
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
                        <div style="display: <%= noLogin%>" id="hehe" class="header-card col-md-3 text-end mt-4 clickClose">
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
                                            <a href="/ProfileController" class="dropdown-item">Profile</a>
                                            <a href="/OrderController/Ordered" class="dropdown-item">Bought</a>
<!--                                            <a href="#" class="dropdown-item">...</a>-->
                                            <form class="dropdown-item" action="LogoutController" method="post">
                                                <button name="btnlogout" style="background: none;color: black">Logout</button>
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
                <form action="ProductController" method="post" class="d-flex justify-content-center">
                    <div class="search input-group">
                        <input class="form-control" type="text" name="search" placeholder="Search" />
                        <button type="submit" name="btn-search"><i class="bi bi-search"></i></button>
                    </div>
                </form>
            </div>
        </header>

        <main class="main-close mt-4">

            <div class="container">
                <div class="row ">
                    <div class="cart-mobile-container col-md-2 text-end mt-4">
                        <%
                            ProductDAO pDAO = new ProductDAO();
                            String pro_code = "";
                            if (request.getParameter("proCode") != null) {
                                pro_code = request.getParameter("proCode");
                            }
                            int pro_id = 0;
                            if (request.getParameter("id") != null) {
                                pro_id = Integer.parseInt(request.getParameter("id"));
                            }

                            ResultSet rs = pDAO.getGallery(pro_code);
                            while (rs.next()) {
                        %>

                        <div class="cart-mobile" style="width: 100px; display: inline-block;">
                            <img onmouseenter="changeImage(this)" class="img-fluid mt-2" src="<%= request.getContextPath()%>/images/<%= rs.getString("gal_picture")%>" alt="product1">
                        </div>
                        <%
                            }

                        %>
                    </div>
                    <%                        ResultSet rs1 = pDAO.getProduct(pro_id);
                        while (rs1.next()) {
                    %>

                    <div class="col-md-4 mt-5">
                        <div style="width: 100%;">
                            <img class="img-fluid images-change-product-details" id="largeImage" src="<%= request.getContextPath()%>/images/<%= rs1.getString("pro_picture")%>" alt="product1">
                        </div>
                    </div>
                    <div class="col-md-6 mt-4">
                        <div class="quantity-product mt-4">
                            <form id="form-cart-1" action="" method="post" >
                                <h2><%= rs1.getString("pro_name")%> <strong class="checkSize"></strong></h2>
                                <div class="price-product mt-3">
                                    <%
                                        if (rs1.getFloat("pro_discount") == 0) {
                                    %>
                                    <h4 name="pro_price" id="price"><%= (rs1.getFloat("pro_price") - rs1.getFloat("pro_price") * rs1.getFloat("pro_discount"))%></h4>
                                    <%
                                    } else {
                                    %>
                                    <del><h4 id="price"><%= rs1.getFloat("pro_price")%></h4></del><h4 name="pro_price" style="color: red" id="price"><%= (rs1.getFloat("pro_price") - rs1.getFloat("pro_price") * rs1.getFloat("pro_discount"))%></h4>
                                        <%
                                            }
                                        %>

                                </div>
                                <div class="row image-color-details" style="width: 400px;">
                                    <%
                                        List<Product> productListChild = pDAO.getProduceCode(pDAO.subString(rs1.getString("pro_code")));
                                        Set<String> uniqueProductCodes1 = new HashSet<>();
                                        for (Product lc : productListChild) {

                                            if (uniqueProductCodes1.add(lc.getPro_code())) {

                                    %>
                                    <a style="opacity: <%= (pDAO.getStockOfProduct(lc.getPro_code(), lc.getPro_size()) <= 0) ? "0.5" : "1"%>" href="Details?id=<%= lc.getPro_id()%>&proCode=<%= lc.getPro_code()%>" class="col-md-3" ><img class="img-fluid" src="<%= request.getContextPath()%>/images/<%= lc.getPro_picture()%>" alt="<%= lc.getPro_picture()%>"></a>
                                        <%
                                                }
                                            }
                                        %>
                                </div>

                                <!--//-->
                                <input style="display: none" type="text" name="pro_name" value="<%= rs1.getString("pro_name")%>">
                                <input style="display: none" type="text" name="pro_price"value="<%= (rs1.getFloat("pro_price") - rs1.getFloat("pro_price") * rs1.getFloat("pro_discount"))%>">
                                <input style="display: none" type="text" name="pro_picture" value="<%= rs1.getString("pro_picture")%>">
                                <input style="display: none" type="text" name="pro_id" value="<%= rs1.getInt("pro_id")%>">
                                <input style="display: none" type="text" name="pro_colour" value="<%= rs1.getString("pro_colour")%>">



                                <!--//-->
                              <input type="hidden" name="id" value="<%= pro_id%>">
                                <div class="size">
                                    <div class="row">
                                        <h5 class="col-md-3">Choose size:</h5>
                                        <div class="size-shoes sizeUL">
                                            <select id="id" style="width: 100px" class="form-control" name="sizeoption">
                                                <%
                                             
                                                    ResultSet rs4 = pDAO.getProductByProCode(pro_code);
                                                    while (rs4.next()) {
                                                %>
                                                <option value="<%= rs4.getInt("pro_size")%>"><%= rs4.getInt("pro_size")%></option>
                                                <%
                                                    }
                                                %>


                                            </select>

                                        </div>
                                    </div>
                                </div>
                                <input style="display: none"  type="number" class="quantity-input-form" name="detail_quan" value="1">
                                <div class="mt-3">

                                    <button class="quantity-button minus" onclick="decreaseQuantity(event)">-</button>
                                    <input type="text" style="" class="text-center quantity-input" name="detail_quan" value="1"/>
                                    <button class="quantity-button plus" onclick="increaseQuantity(event)">+</button>

                                    <button class="addtocard-1" name="AddtoCard" type="submit">Add to Cart</button >
                                </div>
                            </form>

                        </div>

                        <div class="mt-3">
                            <div class="infomation">
                                <h3 class="main-inf-pro">Information</h3>
                                <p class="inf-pro">Lorem ipsum dolor, sit amet consectetur adipisicing elit. Iusto, atque aliquam. Nam,
                                    error fugiat. Officiis sit, suscipit enim atque fuga, inventore incidunt nisi quia
                                    impedit perferendis cupiditate aliquam itaque recusandae?</p>
                            </div>
                            <div class="size-table mt-2">
                                <h3 class="main-inf-pro">Size Table</h3>
                                <p class="inf-pro">Lorem ipsum dolor, sit amet consectetur adipisicing elit. Iusto, atque aliquam. Nam,
                                    error fugiat. Officiis sit, suscipit enim atque fuga, inventore incidunt nisi quia
                                    impedit perferendis cupiditate aliquam itaque recusandae?</p>
                            </div>
                            <div class="rule-return">
                                <h3 class="main-inf-pro">Refund Regulations</h3>
                                <p class="inf-pro">Lorem ipsum dolor, sit amet consectetur adipisicing elit. Iusto, atque aliquam. Nam,
                                    error fugiat. Officiis sit, suscipit enim atque fuga, inventore incidunt nisi quia
                                    impedit perferendis cupiditate aliquam itaque recusandae?</p>
                            </div>
                        </div>


                    </div>

                    <%
                        }
                    %>
                </div>
            </div>

        </main>

        <footer class="container mt-3 pt-3">

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

    <script>

        function decreaseQuantity(e) {
            const quantityInput = document.querySelector('.quantity-input');
            const quantityInputForm = document.querySelector('.quantity-input-form');
            let currentValue = parseInt(quantityInput.value);
            let currentFormValue = parseInt(quantityInputForm.value);
            if (currentValue > 1 && currentFormValue > 1) {
                quantityInput.value = currentValue - 1;
                quantityInputForm.value = currentFormValue - 1;
            }

            e.preventDefault();
        }

        function increaseQuantity(e) {
            const quantityInput = document.querySelector('.quantity-input');
            const quantityInputForm = document.querySelector('.quantity-input-form');

            let currentValue = parseInt(quantityInput.value);
            let currentFormValue = parseInt(quantityInputForm.value);


            quantityInput.value = currentValue + 1;
            quantityInputForm.value = currentFormValue + 1;
            e.preventDefault();
        }
        function onChangeInValue(event) {
            // Lấy giá trị mới của input
            var newValue = event.target.value;

            quantityInput = newValue;
            quantityInputForm = newValue;

            // Thực hiện các hành động mong muốn với giá trị mới tại đây
            console.log("Giá trị mới của input là: " + newValue);
        }
    </script>
    <script src="./lib/bootstrap/bootstrap_js/bootstrap.min.js"></script>
    <script src="./tri/js/main.js"></script>
    <script src="./vi/js/main.js"></script>

</body>

</html>
