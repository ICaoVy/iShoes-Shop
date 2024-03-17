<%-- 
    Document   : newPassword
    Created on : Feb 24, 2024, 2:56:22 PM
    Author     : ASUS
--%>

<%@page import="DAOs.CartDAO"%>
<%@page import="DAOs.AccountDAO"%>
<%@page import="Models.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="Website Icon" href="./images/LogoICafy.png" type="png" />
        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css"
            />
        <link
            rel="stylesheet"
            href="./lib/bootstrap/bootstrap_css/bootstrap.min.css"
            />
        <link rel="stylesheet" href="./css/style.css" />
        <title>iShoes</title>
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

        <main class="main-close">

            <div class="container mt-3">
                <div class="row">
                    <div class="text-center">
                        <h3>Reset Password</h3>
                        <p>We will email you to reset your password</p>
                        <form class="form-login" action="ForgotController" method="post" onsubmit="return validateForm()">
                            <input name="otp" id="otp" type="password" class="form-control" placeholder="Email Code">
                            <br>
                            <input name="newPass" id="newPass" type="password" class="form-control" placeholder="New Password">
                            <br>
                            <input name="confirm" id="confirm" type="password" class="form-control" placeholder="Confirm password">
                            <div style="width: fit-content;margin: auto;margin-right: 3px" class="text-end"><button name="btn-sendAgain" style="color: #007bff;background:white">Send again?</button></div>
                            <button name="btn-ChangeForgot" class="form-control mt-3 button-login" type="submit">Change</button>
                        </form>
                    </div>
                </div>
            </div>



        </main>

        <footer class="container mt-5 pt-3">

            <div class="row">
                <div class="col-md-3">
                    <h4>About ICAFY</h4>
                    <p>ICAFY là một trung tâm mua sắm trực tuyến đa dạng và phong cách, chuyên về việc cung cấp những sản phẩm và dịch vụ tối ưu cho khách hàng trên khắp mọi nơi.</p>
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
        function validateForm() {
            var otpInput = document.getElementById('otp');
            var newPassInput = document.getElementById('newPass');
            var confirmInput = document.getElementById('confirm');

            var otpValue = otpInput.value.trim();
            var newPassValue = newPassInput.value.trim();
            var confirmValue = confirmInput.value.trim();

            // Kiểm tra input rỗng
            if (otpValue === '') {
                alert('Email Code cannot be empty');
                return false;
            }

            if (newPassValue === '') {
                alert('New Password cannot be empty');
                return false;
            }

            if (confirmValue === '') {
                alert('Confirm Password cannot be empty');
                return false;
            }

            // Kiểm tra xác nhận mật khẩu
            if (newPassValue !== confirmValue) {
                alert('New Password and Confirm Password must match');
                return false;
            }
            
            if (newPassValue.length < 8 ) {
                alert('New Password must be getter than 8');
                return false;
            }

            return true; // Nếu không có lỗi, cho phép submit
        }
    </script>
    <script src="./lib/bootstrap/bootstrap_js/bootstrap.min.js"></script>
    <script src="./js/main.js"></script>
</body>
</html>

