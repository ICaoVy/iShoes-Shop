<%-- 
    Document   : pay
    Created on : Feb 24, 2024, 2:57:01 PM
    Author     : ASUS
--%>

<%@page import="DAOs.CartDAO"%>
<%@page import="Models.Customer"%>
<%@page import="DAOs.AccountDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="Website Icon" href="../images/LogoICafy.png" type="png" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css" />
        <link rel="stylesheet" href="../lib/bootstrap/bootstrap_css/bootstrap.min.css" />
        <link rel="stylesheet" href="../css/style.css" />
        <title>iShoes</title>
        <style>
            .error-message {
                color: red;
                display: none;
                font-weight: bold;
            }
        </style>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
                integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"
        defer></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
                integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"
        defer></script>
    </head>
    <style>
        .quantity-order{
            position: absolute;
            margin-left: 15px;
            margin-bottom: 10px;
        }
    </style>
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

        <main class="main-close mt-3">

            <div class="container-fluid">

                <div class="row">
                    <div class="col-md-6">
                        <h3 class="text-center">INFORMATION CUSTOMER</h3>
                        <form id="pay" action="OrderController" method="post" onsubmit="return validatePay()">
                            <div class="row mt-2">
                                <div class="mt-2">
                                    <input readonly="" name="fullname" type="text" class="form-control"  value="<%= ad.decodeString(fullname)%>">
                                    <div id="full-name-error" class="error-message"></div>
                                </div>
                                <div class="mt-2">
                                    <input name="email" class="form-control" type="email" placeholder="Email">
                                    <div id="email-error" class="error-message"></div>
                                </div>
                                <div class="mt-2">
                                    <input name="phone" class="form-control" type="text" placeholder="Phone">
                                    <div id="phone-error" class="error-message"></div>
                                </div>
                            </div>
                            <div class="mt-2">
                                <textarea name="address" placeholder="Delivery Address" class="form-control"></textarea>
                                <div id="delivery-address-error" class="error-message"></div>

                            </div>
                            <div class="mt-2">
                                <textarea name="note" placeholder="Note" class="form-control"></textarea>
                            </div>
                            <button name="btnCheckOut" type="submit" class="order-now form-control mt-2"><i class="bi bi-bag-fill"></i>Order Now </button>                        
                        </form>
                        <a class="creditcard" href="./creditcard.html"> <button class="form-control creditcard mt-2"><i class="bi bi-bag-fill"></i> Order by Credit Card</button> </a>
                    </div>

                    <div class="col-md-6 border-left">

                        <div class="product-table-container">
                            <c:if test="${cart_out.size()!=0}">
                                <h6 style="color: red">Sản phẩm này không đủ số lượng</h6>
                                <table class="table-product">
                                    <thead class="pay-title-th">
                                        <tr>
                                            <th></th>
                                            <th>Name</th>
                                            <th>Quantity</th>
                                            <th>Size</th>
                                            <th>Colour</th>
                                            <th>Price</th>
                                        </tr>
                                    </thead>
                                    <c:forEach items="${cart_out}" var="c">

                                        <tbody  class="pay-title">
                                            <tr>
                                                <td>
                                                    <img src="./images/${c.pro_picture}" width="100px" class="img-fluid" alt="">
                                                </td>
                                                <td>${c.pro_name}</td>
                                                <td>${c.cart_quantity}</td>
                                                <td>${c.cart_size}</td>
                                                <td>${c.cart_colour}</td>
                                                <td>${c.cart_price}</td>

                                            </tr>

                                        </tbody>
                                    </c:forEach>
                                </table>
                            </c:if>
                            <c:if test="${cart_buy.size()!=0}">
                                <h6 style="color: green">Sản phẩm đủ số lượng có thể mua</h6>
                                <table class="table-product">
                                    <thead class="pay-title-th">
                                        <tr>
                                            <th></th>
                                            <th>Name</th>
                                            <th>Quantity</th>
                                            <th>Size</th>
                                            <th>Colour</th>
                                            <th>Price</th>
                                        </tr>
                                    </thead>
                                    <c:forEach items="${cart_buy}" var="c">

                                        <tbody  class="pay-title">
                                            <tr>
                                                <td>
                                                    <img src="./images/${c.pro_picture}" width="100px" class="img-fluid" alt="">
                                                </td>
                                                <td>${c.pro_name}</td>
                                                <td>${c.cart_quantity}</td>
                                                <td>${c.cart_size}</td>
                                                <td>${c.cart_colour}</td>
                                                <td>${c.cart_price}</td>

                                            </tr>

                                        </tbody>
                                    </c:forEach>
                                </table>
                            </c:if>
                        </div>
                        <div class="pay-tt">


                        </div>
                        <div class="pay-total">
                            <p>Total: ${tong_tien}</p>
                        </div>

                    </div>

                </div>
            </div>

        </main>
        <footer class="container mt-3 pt-3">

            <div class="row">
                <div class="col-md-3">
                    <h4>About ICAFY</h4>
                    <p>ICAFY là một trung tâm mua sắm trực tuyến đa dạng và phong cách, chuyên về việc cung cấp những sản
                        phẩm và
                        dịch vụ tối ưu cho khách hàng trên khắp mọi nơi.</p>
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
    <script src="../tri/js/main.js"></script>
</body>

</html>
