<%-- 
    Document   : cart
    Created on : Feb 24, 2024, 3:14:23 PM
    Author     : ASUS
--%>

<%@page import="java.util.LinkedList"%>
<%@page import="Models.Carts"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="DAOs.CartDAO"%>
<%@page import="DAOs.AccountDAO"%>
<%@page import="Models.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="Website Icon" href="./images/LogoICafy.png" type="png" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css" />
        <link rel="stylesheet" href="./lib/bootstrap/bootstrap_css/bootstrap.min.css" />
        <link rel="stylesheet" href="./css/style.css" />
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
                integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"
        defer></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
                integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"
        defer></script>
        <title>iShoes</title>
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
                            <a href="/"><img width="70px" src="./images/LogoICafy-removebg-preview.png" width="container-fluid"
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
                                            <a href="#" class="dropdown-item">Profile</a>
                                            <a href="../purchasehistory.jsp" class="dropdown-item">History Bought</a>
                                            <a href="#" class="dropdown-item">...</a>
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
                <form action="" class="d-flex justify-content-center">
                    <div class="search input-group">
                        <input class="form-control" type="text" placeholder="Search" />
                        <button><i class="bi bi-search"></i></button>
                    </div>
                </form>
            </div>
        </header>

        <main class="main-close mt-4">

            <div class="container">
                <div class="d-flex justify-content-around cart-list-1">
                    <a id="current-page" href="/ProductController/CartController">Your Cart</a>
                    <a href="#">Order Status</a>
                    <a href="#">Cancelled</a>
                    <a href="./purchasehistory.html">Purchase History</a>
                </div>
            </div>

            <form class="mt-3" action="OrderController" method="post" onsubmit="return validateCheckBox()">
                <div class="container">
                    <div class="row">
                        <div class="col-md-8">

                            <p>You currently have <strong> ${cart_list.size()}</strong>product in your cart</p> 

                            <div class="row pro-cart-list">
                                <c:forEach items="${cart_list}" var="c">
                                    <div class="cart-item row">
                                        <div class="col-md-1  mt-5">
                                            <!--                                        <div  class="checkbox" aria-label="Basic checkbox toggle button ">-->
                                            <input type="checkbox" name="checkbox" class="checkBuy" value="${c.cart_id}">
                                            <!--                                            <label  class="btn btn-outline-primary" for="btncheck" ><i class="bi bi-check2"></i></label>-->
                                            <!--                                        </div>-->
                                        </div>
                                        <div class="col-md-2 mt-2 mb-2">
                                            <img name="pro_picture" src="./images/${c.pro_picture}" alt="product4" class="img-fluid">
                                        </div>
                                        <div class="col-md-5 mt-3">
                                            <p name="pro_name" >${c.pro_name}</p>
                                            <strong name="cart_price" class="cart-price">${c.cart_price}<span>$</span></strong>
                                        </div>
                                        <div class="text-end col-md-4 mt-3">
                                            <div class="col-md-6"></div>
                                            <div class="col-md-6 quantity-product">
                                                <div class="row">
                                                    <a class="btn col-2 mx-3 btn-sm btn-decre btn-dark" href="/CartController/DecQuan/${c.pro_id}">-</i></a>
                                                    <input type="text" name="quantity" class="mx-1 col-2 form-control cart-quantity" value="${c.cart_quantity}" readonly>
                                                    <a  class="btn col-2 btn-sm btn-incre btn-dark mx-2" href="/CartController/IncQuan/${c.pro_id}">+</i></a>
                                                    <a onclick="return confirm('Do you want to delete ${c.pro_name} from cart?')" href="/CartController/Delete/${c.cart_id}"class="btn btn-sm btn-danger mt-2">Remove</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="inf-pay">
                                <h3>Information Order</h3>
                                <h4 class="row">                                   
                                    <p class="col-md-6 text-start">Total: </p>                                    
                                    <p class="col-md-4" style="color: red">$<strong id="total-price" class="col-md-6 text-start" style="color: red;"></strong></p>

                                </h4>
                                <p>
                                <ul>
                                    <li><p>Phí vận chuyển sẽ được tính ở trang thanh toán.</p></li>
                                    <li><p>Bạn cũng có thể nhập mã giảm giá ở trang thanh toán.</p></li>
                                </ul>
                                </p>
                            </div>


                            <c:if test="${cart_list.size()!=0}">
                                <input class="pay form-control" value="Buy Now" name="btnBuy" type="submit"> 
                            </c:if>
                        </div>
                    </div>
                </div>

            </form>
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
        function calculateTotalPrice() {
            var total = 0;
            var cartItems = document.getElementsByClassName('cart-item');

// Loop through each cart item
            for (var i = 0; i < cartItems.length; i++) {
                var cartItem = cartItems[i];
                var priceElement = cartItem.querySelector('.cart-price');
                var quantityElement = cartItem.querySelector('.cart-quantity');
                var price = parseFloat(priceElement.textContent); // Get price from HTML element
                var quantity = parseInt(quantityElement.value); // Get quantity from HTML element
                total += price; // Calculate subtotal for this item
//                total += priceElement;
            }

// Display total price
            document.getElementById('total-price').textContent = total.toFixed(1); // Set total price in HTML element
        }
// Call calculateTotalPrice() when the page loads
        window.onload = calculateTotalPrice;
    </script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.7.0.js"></script> <%-- 3 dong script la thu vien cua table lay o trang datatable.nets --%>


    <script src="./lib/bootstrap/bootstrap_js/bootstrap.min.js"></script>
    <script src="./tri/js/main.js"></script>
    <script>
        function validateCheckBox() {
            var checkboxes = document.querySelectorAll(".checkBuy");
            let atLeastOneChecked = false;
            for (var i = 0; i < checkboxes.length; i++) {
                if (checkboxes[i].checked) {
                    atLeastOneChecked = true;
                    break;
                }
            }

            if (!atLeastOneChecked) {
                alert("You must select at least one checkbox.");
                atLeastOneChecked = false;
            }

            return atLeastOneChecked;
        }

        function changeImage(image) {
            var largeImage = document.getElementById("largeImage");
            largeImage.src = image.src;
        }
    </script>
</body>

</html>
