<%-- 
    Document   : login
    Created on : Feb 24, 2024, 2:54:52 PM
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
        <link rel="Website Icon" href="<%= request.getContextPath()%>/images/LogoICafy.png" type="png" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css" />
        <link rel="stylesheet" href="<%= request.getContextPath()%>/lib/bootstrap/bootstrap_css/bootstrap.min.css" />

        <link rel="stylesheet" href="<%= request.getContextPath()%>/css/style.css" />
        <link rel="stylesheet" href="<%= request.getContextPath()%>/vi/css/style.css">


        <style>
            #error-messages {
                color: red;
                margin-top: 10px;
                font-weight: bold;
            }

            .error-message {
                color: red;
                display: none;
                font-weight: bold;
            }

            .quantity-order{
                position: absolute;
                margin-left: 15px;
                margin-bottom: 10px;
            }
            .forgotHover{
                text-decoration: none;
                color: #000;

            }
            .forgotHover:hover{
                color: #007bff;
            }
        </style>


        <title>iShoes</title>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
                integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"
        defer></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
                integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"
        defer></script>


    </head>


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
                            <li class="nav-item"><a class="nav-link"  href="/">Home</a></li>
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
                            <div id="current-main" class="col-md-2">
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
    <%
        String check = "";
        if (session.getAttribute("checkLoginMess") != null) {
            check = (String) session.getAttribute("checkLoginMess");
        }
        session.removeAttribute("checkLoginMess");
    %>
    <main class="main-close">
        <div class="container">
            <div id="login-id" class="text-center sign-in">
                <h3 class="mt-3">Sign In</h3>
                <form id="login-final" method="post" action="LoginController" onsubmit="return validateLogin()">
                    <div class="form-login">
                        <input name="email" class="form-control" id="email-login" type="text" placeholder="Email">
                        <br>
                        <input name="password" class="form-control" id="password-login" type="password" placeholder="Password">
                        <div style="color: red"><strong><%= check%></strong></div>
                        <div style="width: fit-content;margin: auto;margin-right: 3px;" class="text-end mt-2"><a style="color: #007bff" class="forgotHover" href="/ForgotController">Forgot password?</a></div>
                    </div>
                    <div id="error-messages" class="mt-2"></div>

                    <div class="mt-2">
                        <p>Do you have an account yet? <span class="register-control">Register</span></p>
                    </div>

                    <button name="signIn" class="mt-2 button-login" type="submit">Sign in</button>

                </form>
                <div class="row mt-3">
                    <div class="col-md-12">

                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid &redirect_uri=http://localhost:80/Back_End_iShoes_Shop_Group5/LoginGoogleHandler&response_type=code&client_id=820617316597-5fc47u77eefclk6ptdd43brq6hgvno47.apps.googleusercontent.com&approval_prompt=force" style="text-decoration: none; cursor: pointer" name="byGoogle" class="login-with"><i class="bi bi-google"></i> Login with Google</a>
                    </div>
                </div>
            </div>

            <div id="register-id" class="text-center mt-3 register-form">
                <h3>Register</h3>
                <form id="register-final" action="SendEmailController" method="post" onsubmit="return validateRegister(event)">
                    <div class="form-login">
                        <input name="fullname" class="form-control" type="text" placeholder="Full name">
                        <div id="full-name-error" class="error-message"></div>
                        <br>

                        <input name="email" class="form-control" type="text" placeholder="Email">
                        <div id="email-error" class="error-message"></div>
                        <br>

                        <input name="phone" class="form-control" type="text" placeholder="Phone number">
                        <div id="phone-number-error" class="error-message"></div>
                        <br>
                        <input name="birthday" style="opacity: 0.8;" class="form-control" type="date" placeholder="Birthday">
                        <div id="birthday-error" class="error-message"></div>
                        <br>
                        <input name="address" style="opacity: 0.8;" class="form-control" type="text" placeholder="Address">
                        <div id="address-error" class="error-message"></div>
                        <br>
                        <input name="password" class="form-control" type="password" placeholder="Password">
                        <div id="password-error" class="error-message"></div>
                        <br>

                        <input name="confirm" class="form-control" type="password" placeholder="Confirm password">
                        <div id="confirm-password-error" class="error-message"></div>
                    </div>
                    <button name="btn-register" class="mt-2 button-login" type="submit">Register</button>
                </form>
                <div class="mt-3 return-login">
                    <i class="bi bi-arrow-left"> Sign In</i>
                </div>
            </div>
        </div>


    </main>

    <footer class="container mt-3 pt-3">

        <div class="row">
            <div class="col-md-3">
                <h4>About ICAFY</h4>
                <p>ICAFY là một trung tâm mua sắm trực tuyến đa dạng và phong cách, chuyên về việc cung cấp những sản phẩm và
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

<%
    String checkRegis = (String) session.getAttribute("registerSuccessfull");
    if (checkRegis != null) {
%>
<script>
    alert("You have successfully registered\nClick Ok to back login page!!!");</script>
    <%
            session.removeAttribute("registerSuccessfull");
        }
        String checkForgot = (String) session.getAttribute("forgotSuccessfull");
        if (checkForgot != null) {
    %>
<script>
    alert("You have successfully reseted\nClick Ok to back login page!!!");</script>
    <%
            session.removeAttribute("forgotSuccessfull");
        }
        String checkEmail = (String) session.getAttribute("emailisExist");
        if (checkEmail != null) {
    %>
<script>
    alert("The email you just entered already exists in the system!!!");</script>
    <%
            session.removeAttribute("emailisExist");
        }
    %>
<script src="<%= request.getContextPath()%>/lib/bootstrap/bootstrap_js/bootstrap.min.js"></script>
<script>
    // ########## Show Hide NavBar ###############

    const nodeBar = document.getElementById('nodebar');
    const clickClose = document.querySelectorAll(".clickClose");
    const hihi = document.getElementById('hihi');
    const hehe = document.getElementById('hehe');
    nodeBar.addEventListener('click', () => {
        hihi.classList.toggle('clickClose');
        hehe.classList.toggle('clickClose');
    });
    const headClose = document.querySelector('.main-close');
    headClose.addEventListener('click', () => {
        hihi.classList.toggle('clickClose');
        hehe.classList.toggle('clickClose');
    });
//######################### Login and Register ############################

    const signIn = document.querySelector('.sign-in');
    const register = document.querySelector('.register-form');
    const eventClickRes = document.querySelector('.register-control');
    const registerID = document.getElementById('register-id');
    const loginID = document.getElementById('login-id');
    eventClickRes.addEventListener('click', () => {
        registerID.classList.toggle('register-form');
        loginID.classList.add('sign-close');
    });
    const eventReturnLogin = document.querySelector('.return-login');
    eventReturnLogin.addEventListener('click', () => {
        registerID.classList.toggle('register-form');
        loginID.classList.remove('sign-close');
    });
// ########## Cart Infomation ###############

// const title = document.querySelectorAll('.main-inf-pro');

// const content = document.querySelectorAll('.inf-pro');

// title.addEventListener('click',() =>{
//     alert('oke');
// });


//#################### Check form ##############################

    const formLogin = document.querySelector('#login-final');
    const formRegister = document.querySelector('#register-final');
//#####Check validate login#####//
    function validateLogin() {
        var email = document.getElementById('email-login').value;
        var errorMessages = document.getElementById('error-messages');
        //Clear previous error messages
        errorMessages.innerHTML = '';
        //Check empty
        if (email.trim() === '' || password.trim() === '') {
            errorMessages.innerHTML = 'Please fill in all fields';
            return false;
        }

        //Check email format
        var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            errorMessages.innerHTML = 'Invalid email format';
            return false;
        }

        //If all checks pass, the form is valid
        return true;
    }
//###################################################


//#####Check validate register#####//
    function validateRegister() {
        var fullName = document.querySelector('#register-final input[placeholder="Full name"]').value;
        var email = document.querySelector('#register-final input[placeholder="Email"]').value;
        var phoneNumber = document.querySelector('#register-final input[placeholder="Phone number"]').value;
        var birthday = document.querySelector('#register-final input[placeholder="Birthday"]').value;
        var address = document.querySelector('#register-final input[placeholder="Address"]').value;
        var password = document.querySelector('#register-final input[placeholder="Password"]').value;
        var confirmPassword = document.querySelector('#register-final input[placeholder="Confirm password"]').value;
        // Reset error message before check validate
        resetErrorMessages();
        if (fullName === '') {
            displayErrorMessage('full-name-error', 'Please enter your Full Name');
        }

        if (email === '') {
            displayErrorMessage('email-error', 'Please enter your Email');
        } else {
            if (!email.endsWith("@gmail.com")) {
                displayErrorMessage('email-error', 'Your extension must be @gmail.com!');
            } else {
                const s = email.split("@");
                if (s[0] === "") {
                    displayErrorMessage('email-error', 'Your extension must be example@gmail.com!');
                }
            }
        }




//        var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;



        if (phoneNumber === '') {
            displayErrorMessage('phone-number-error', 'Please enter your phone number');
        }

        const  phonePattern = /^0\d{9}$/;
        if (!phonePattern.test(phoneNumber)) {
            displayErrorMessage('phone-number-error', 'Your phone number invalid!');
        }


        if (birthday === '') {
            displayErrorMessage('birthday-error', 'Please choose your Birthday');
        }

        if (address === "") {
            displayErrorMessage('address-error', 'Your address invalid!');
        }

        if (password === '') {
            displayErrorMessage('password-error', 'Please enter your Password');
        }

        if (confirmPassword === '') {
            displayErrorMessage('confirm-password-error', 'Please enter your Password again');
        }

        if (password !== confirmPassword) {
            displayErrorMessage('confirm-password-error', 'Password does not match password confirmation!');
        }

        if (password.length < 8) {
            displayErrorMessage('password-error', 'Password must be getter than or equal 8');
        }

        // If there is an error, prevent form submission
        if (document.querySelectorAll('.error-message:not([style="display: none;"])').length > 0) {
            return false;
        }



        // If all is valid, submit the form
        return true;
    }
//###################################################

//##### Displays an error message - Reset an error message #####//
    function displayErrorMessage(elementId, message) {
        var errorElement = document.getElementById(elementId);
        errorElement.style.display = 'block';
        errorElement.innerHTML = message;
    }

    function resetErrorMessages() {
        var errorElements = document.querySelectorAll('.error-message');
        errorElements.forEach(function (element) {
            element.style.display = 'none';
            element.innerHTML = '';
        });
    }
//#################################################




</script>
</html>
