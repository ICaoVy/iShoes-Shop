<%-- 
    Document   : importproductquantity
    Created on : Feb 28, 2024, 7:38:25 PM
    Author     : binht
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="DAOs.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <!--Boxicons-->
        <title>iShoes</title>
        <link rel="Website Icon" href="<%= request.getContextPath()%>./images/LogoICafy.png" type="png" />
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <!--My CSS-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css" />
        <link rel="stylesheet" href="../cssAdmin/styleA.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ADMIN</title>
    </head>
    <body>
                  <!--SIDERBAR-->
        <section id="siderbar">
            <a href="/AdminController" class="brand">
                <i class='bx bxs-smile' ></i>
                <span class="text">Admin</span>
            </a>
            <ul class="side-menu top">
                <li class="active">
                    <a href="/AdminController">
                        <i class='bx bxs-dashboard' ></i> 
                        <span class="text">Dashboard</span>
                    </a>
                </li>
                <li >
                    <a href="/AdminController/MyStore">
                        <i class='bx bxs-shopping-bag-alt' ></i>
                        <span class="text">My Store</span>
                    </a>
                </li>
                <li >
                    <a href="/AdminController/Analytics">
                        <i class='bx bxs-doughnut-chart' ></i>
                        <span class="text">Analytics</span>
                    </a>
                </li>
                <li >
                    <a href="/AdminController/ManegerUser">
                        <i class='bx bxs-group' ></i>
                        <span class="text">Manger user</span>
                    </a>
                </li>
                <li >
                    <a href="/AdminController/ListOrder">
                        <i class='bx bx-list-check' ></i>
                        <span class="text">List orders</span>
                    </a>
                </li>
                <li >
                    <a href="/AdminController/ListCustomerOrder">
                        <i class='bx bxs-plus-circle' ></i>
                        <span class="text">List Customer Order</span>
                    </a>
                </li>
                <li >
                    <a href="/AdminController/MyCategory">
                        <i class='bx bxs-plus-circle' ></i>
                        <span class="text">Category</span>
                    </a>
                </li>
                <li >
                    <a href="/AdminController/Gallery">
                        <i class='bx bxs-plus-circle' ></i>
                        <span class="text">Gallery</span>
                    </a>
                </li>
            </ul>
            <ul class="side-menu top">
                <li>
                    <a href="/LogoutController" class="logout">
                        <i class='bx bx-log-out' ></i>
                        <span class="text">Logout</span>
                    </a>
                </li>
            </ul>
        </section>
        <!--SIDEBAR-->

        <!--CONTENT-->
        <section id="content">
            <!--NAVBAR-->
            <nav>
                <i class='bx bx-menu'></i>
                <a href="#" class="nav-link">Categories</a>
                <form action="#">
                    <div class="form-input">
                        <input type="search" name="" id="" placeholder="Search...">
                        <button type="submit" class="search-btn"><i class='bx bx-search' ></i></button>
                    </div>
                </form>
                <a href="#" class="notification">
                    <i class='bx bxs-bell' ></i>
                    <span class="num">8</span>
                </a>
                <a href="#">
                    <img src="" alt="">
                </a>
            </nav>
            <!--NAVBAR-->
            <!--MAIN-->
            <main>
                            <%
                                ProductDAO pDAO = new ProductDAO();
                                int pro_id = 0;
                                if (request.getParameter("id") != null) {
                                    pro_id = Integer.parseInt(request.getParameter("id"));
                                }
                                ResultSet rs = pDAO.getProduct(pro_id);
                                if (rs.next()) {
                            %>

                            <form action="" method="post">
                                <label style="text-align: center"><b>Product Code</b></label>
                                <input class="form-control"  type="text" name="proCode" value="<%= rs.getString("pro_code")%>" disabled>
                                <label style="text-align: center"><b>Import Quantity</b></label>
                                <input class="form-control" type="number" min="0" placeholder="Please import quantity greater than or equal to 0" name="quantity">
                                <button type="submit" name="btn-add-quantity">Add</button>
                            </form>
                            <%
                                }
                            %>
            </main>
            <!--MAIN-->
        </section>
        <!--CONTENT-->

        <script src="./jsAdmin/scriptA.js"></script>
    </body>
</html>
