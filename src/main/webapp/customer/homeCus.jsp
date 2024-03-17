<%-- 
    Document   : homeCus
    Created on : Feb 27, 2024, 5:03:19 PM
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

        <!--CSS============================================= -->
        <link rel="stylesheet" href="./sang/css/main.css">
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

        <main class="main-close mt-3">

            <div class="carousel-1">
                <div class="container-md my-3">
                    <div id="myCarousel" class="carousel slide" data-bs-ride="carousel">
                        <!-- Carousel indicators -->
                        <ol class="carousel-indicators">
                            <li data-bs-target="#myCarousel" data-bs-slide-to="0" class="active"></li>
                            <li data-bs-target="#myCarousel" data-bs-slide-to="1"></li>
                        </ol>

                        <!-- Wrapper for carousel items -->
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img src="https://i.ytimg.com/vi/0Q9ojKszgL0/maxresdefault.jpg" class="img-fluid" alt="">
                            </div>
                            <div class="carousel-item">
                                <img
                                    src="https://img.freepik.com/premium-psd/sport-sneakers-shoes-sale-social-media-instagram-post-facebook-web-banner-template_70055-1705.jpg?w=2000"
                                    class="d-block w-100" alt="Slide 2">
                            </div>
                        </div>

                        <!-- Carousel controls -->
                        <a class="carousel-control-prev" href="#myCarousel" data-bs-slide="prev">
                            <span class="carousel-control-prev-icon"></span>
                        </a>
                        <a class="carousel-control-next" href="#myCarousel" data-bs-slide="next">
                            <span class="carousel-control-next-icon"></span>
                        </a>
                    </div>
                </div>
            </div>

            <!-- <div class="container mt-3 text-center">
             
            </div> -->

            <!-- start features Area -->
            <section class="features-area section_gap align-items-center justify-content-center">
                <div class="container" data-aos="fade-up">
                    <div class="row gy-4 mt-5 justify-content-center" data-aos="zoom-in" data-aos-delay="250">
                        <!-- single features -->
                        <div class="col-xl-2 col-md-4 align-items-center">
                            <a href="#section2">
                                <div class="single-features">
                                    <div class="f-icon">
                                        <img src="./sang/img/features/best seller2-1-50.png">
                                    </div>
                                    <h6>Best Sellers</h6>
                                </div>
                            </a>
                        </div>
                        <!-- single features -->
                        <div class="col-xl-2 col-md-4">
                            <a href="#section1">
                                <div class="single-features">
                                    <div class="f-icon">
                                        <img src="./sang/img/features/new product2-1-50.png">
                                    </div>
                                    <h6>New Products</h6>
                                </div>
                            </a>
                        </div>
                        <!-- single features -->
                        <div class="col-xl-2 col-md-4">
                            <a href="">
                                <div class="single-features">
                                    <div class="f-icon">
                                        <img src="./sang/img/features/oxford2-1-50.png">
                                    </div>
                                    <h6>Luxury</h6>
                                </div>
                            </a>
                        </div>
                        <!-- single features -->
                        <div class="col-xl-2 col-md-4">
                            <a href="">
                                <div class="single-features">
                                    <div class="f-icon">
                                        <img src="./sang/img/features/sneaker2-1-50.png">
                                    </div>
                                    <h6>Sneaker</h6>
                                </div>
                            </a>
                        </div>
                        <!-- single features -->
                        <div class="col-xl-2 col-md-4">
                            <a href="">
                                <div class="single-features">
                                    <div class="f-icon">
                                        <img src="./sang/img/features/sandal2-1-50.png">
                                    </div>
                                    <h6>Sandal</h6>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
            </section>
            <!-- end features Area -->

            <!-- start product Area -->

            <!-- New Product List -->
            <section id="section1">
                <!-- single product slide -->
                <div class="single-product-slider">
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-6 text-center">
                                <div class="section-title">
                                    <h1>New Products</h1>
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore
                                        et
                                        dolore
                                        magna aliqua.</p>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <!-- single product -->
                            <div class="col-lg-3 col-md-6">
                                <div class="single-product">
                                    <img class="img-fluid" src="./sang/img/product/p1.jpg" alt="">
                                    <div class="product-details">
                                        <h6>addidas New Hammer sole
                                            for Sports person</h6>
                                        <div class="price">
                                            <h6>$150.00</h6>
                                            <h6 class="l-through">$210.00</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- single product -->
                            <div class="col-lg-3 col-md-6">
                                <div class="single-product">
                                    <img class="img-fluid" src="./sang/img/product/p2.jpg" alt="">
                                    <div class="product-details">
                                        <h6>addidas New Hammer sole
                                            for Sports person</h6>
                                        <div class="price">
                                            <h6>$150.00</h6>
                                            <h6 class="l-through">$210.00</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- single product -->
                            <div class="col-lg-3 col-md-6">
                                <div class="single-product">
                                    <img class="img-fluid" src="./sang/img/product/p3.jpg" alt="">
                                    <div class="product-details">
                                        <h6>addidas New Hammer sole
                                            for Sports person</h6>
                                        <div class="price">
                                            <h6>$150.00</h6>
                                            <h6 class="l-through">$210.00</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- single product -->
                            <div class="col-lg-3 col-md-6">
                                <div class="single-product">
                                    <img class="img-fluid" src="./sang/img/product/p4.jpg" alt="">
                                    <div class="product-details">
                                        <h6>addidas New Hammer sole
                                            for Sports person</h6>
                                        <div class="price">
                                            <h6>$150.00</h6>
                                            <h6 class="l-through">$210.00</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- single product -->
                            <div class="col-lg-3 col-md-6">
                                <div class="single-product">
                                    <img class="img-fluid" src="./sang/img/product/p5.jpg" alt="">
                                    <div class="product-details">
                                        <h6>addidas New Hammer sole
                                            for Sports person</h6>
                                        <div class="price">
                                            <h6>$150.00</h6>
                                            <h6 class="l-through">$210.00</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- single product -->
                            <div class="col-lg-3 col-md-6">
                                <div class="single-product">
                                    <img class="img-fluid" src="./sang/img/product/p6.jpg" alt="">
                                    <div class="product-details">
                                        <h6>addidas New Hammer sole
                                            for Sports person</h6>
                                        <div class="price">
                                            <h6>$150.00</h6>
                                            <h6 class="l-through">$210.00</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- single product -->
                            <div class="col-lg-3 col-md-6">
                                <div class="single-product">
                                    <img class="img-fluid" src="./sang/img/product/p7.jpg" alt="">
                                    <div class="product-details">
                                        <h6>addidas New Hammer sole
                                            for Sports person</h6>
                                        <div class="price">
                                            <h6>$150.00</h6>
                                            <h6 class="l-through">$210.00</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- single product -->
                            <div class="col-lg-3 col-md-6">
                                <div class="single-product">
                                    <img class="img-fluid" src="./sang/img/product/p8.jpg" alt="">
                                    <div class="product-details">
                                        <h6>addidas New Hammer sole
                                            for Sports person</h6>
                                        <div class="price">
                                            <h6>$150.00</h6>
                                            <h6 class="l-through">$210.00</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <!-- Best Seller List -->
            <section id="section2">
                <!-- single product slide -->
                <div class="single-product-slider">
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-6 text-center">
                                <div class="section-title">
                                    <h1>Best Sellers</h1>
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore
                                        et
                                        dolore
                                        magna aliqua.</p>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <!-- single product -->
                            <div class="col-lg-3 col-md-6">
                                <div class="single-product">
                                    <img class="img-fluid" src="./sang/img/product/p6.jpg" alt="">
                                    <div class="product-details">
                                        <h6>addidas New Hammer sole
                                            for Sports person</h6>
                                        <div class="price">
                                            <h6>$150.00</h6>
                                            <h6 class="l-through">$210.00</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- single product -->
                            <div class="col-lg-3 col-md-6">
                                <div class="single-product">
                                    <img class="img-fluid" src="./sang/img/product/p8.jpg" alt="">
                                    <div class="product-details">
                                        <h6>addidas New Hammer sole
                                            for Sports person</h6>
                                        <div class="price">
                                            <h6>$150.00</h6>
                                            <h6 class="l-through">$210.00</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- single product -->
                            <div class="col-lg-3 col-md-6">
                                <div class="single-product">
                                    <img class="img-fluid" src="./sang/img/product/p3.jpg" alt="">
                                    <div class="product-details">
                                        <h6>addidas New Hammer sole
                                            for Sports person</h6>
                                        <div class="price">
                                            <h6>$150.00</h6>
                                            <h6 class="l-through">$210.00</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- single product -->
                            <div class="col-lg-3 col-md-6">
                                <div class="single-product">
                                    <img class="img-fluid" src="./sang/img/product/p5.jpg" alt="">
                                    <div class="product-details">
                                        <h6>addidas New Hammer sole
                                            for Sports person</h6>
                                        <div class="price">
                                            <h6>$150.00</h6>
                                            <h6 class="l-through">$210.00</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- single product -->
                            <div class="col-lg-3 col-md-6">
                                <div class="single-product">
                                    <img class="img-fluid" src="./sang/img/product/p1.jpg" alt="">
                                    <div class="product-details">
                                        <h6>addidas New Hammer sole
                                            for Sports person</h6>
                                        <div class="price">
                                            <h6>$150.00</h6>
                                            <h6 class="l-through">$210.00</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- single product -->
                            <div class="col-lg-3 col-md-6">
                                <div class="single-product">
                                    <img class="img-fluid" src="./sang/img/product/p4.jpg" alt="">
                                    <div class="product-details">
                                        <h6>addidas New Hammer sole
                                            for Sports person</h6>
                                        <div class="price">
                                            <h6>$150.00</h6>
                                            <h6 class="l-through">$210.00</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- single product -->
                            <div class="col-lg-3 col-md-6">
                                <div class="single-product">
                                    <img class="img-fluid" src="./sang/img/product/p1.jpg" alt="">
                                    <div class="product-details">
                                        <h6>addidas New Hammer sole
                                            for Sports person</h6>
                                        <div class="price">
                                            <h6>$150.00</h6>
                                            <h6 class="l-through">$210.00</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- single product -->
                            <div class="col-lg-3 col-md-6">
                                <div class="single-product">
                                    <img class="img-fluid" src="./sang/img/product/p8.jpg" alt="">
                                    <div class="product-details">
                                        <h6>addidas New Hammer sole
                                            for Sports person</h6>
                                        <div class="price">
                                            <h6>$150.00</h6>
                                            <h6 class="l-through">$210.00</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

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


    <script src="<%= request.getContextPath()%>/lib/bootstrap/bootstrap_js/bootstrap.min.js"></script>
    <script src="<%= request.getContextPath()%>/js/main.js"></script>

    <script src="<%= request.getContextPath()%>/sang/js/vendor/jquery-2.2.4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
            integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
    crossorigin="anonymous"></script>
    <script src="<%= request.getContextPath()%>/sang/js/vendor/bootstrap.min.js"></script>
</body>

</html>

