<%-- 
    Document   : myStore
    Created on : Feb 24, 2024, 3:50:41 PM
    Author     : ASUS
--%>

<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.List"%>
<%@page import="Models.Product"%>
<%@page import="DAOs.ProductDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <!--Boxicons-->
        <title>iShoes</title>
        <link rel="Website Icon" href="../images/LogoICafy.png" type="png" />
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <!--My CSS-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css" />
        <link rel="stylesheet" href="../cssAdmin/styleA.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ADMIN</title>
        <script src="https://code.jquery.com/jquery-3.7.0.js"></script>
        <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#example').DataTable();
            });
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
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
                <a href="#" class="nav-link">Gallery</a>
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
                <div class="head-title">
                    <div class="left">
                        <h1>My Gallery</h1>
                        <!-- <ul class="breadcrumb">
                            <li>
                                <a href="#">My Store</a>
                            </li>
                            <li><i class='bx bxs-chevron-right'></i></li>
                            <li>
                                <a class="active" href="#">Product management</a>
                            </li>
                        </ul> -->
                    </div>
                </div>

                <!--                <div class="table-data">
                                    <div class="order">-->
                <div class="head container">
                    <table id="example" class="product-table text-center">
                        <thead>
                            <tr>
                                <th>Code</th>
                                <th>Name</th>
                                <th>Picture</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody class="text-center">
                            <%
                                ProductDAO pDAO = new ProductDAO();
                                List<Product> productList = null;
                                productList = pDAO.getAllProductDECS();
                                Set<String> uniqueProductCodes = new HashSet<>();
                                for (Product l : productList) {
                                    String productCode = pDAO.subString(l.getPro_code());
                                    if (uniqueProductCodes.add(l.getPro_code())) {
                            %>
                            <tr>
                                <td><%= l.getPro_code() %></td>
                                <td><%= l.getPro_name() %></td>
                                <td><img style="margin: auto" class="img-fluid" width="120px" src="./../images/<%= l.getPro_picture() %>" alt="<%=l.getPro_picture()%>"/></td>
                                <td>
                                    <a href="/AdminController/GANEW?pro_code=<%= l.getPro_code()%>&pro_id=<%= l.getPro_id() %>" class="btn-edit" onclick="editProduct(1)" name="btn-edit"><i class="bi bi-pencil-square"></i></a>
                                    <a href="#" class="btn-delete" onclick="deleteProduct(1)" name="btn-delete"><i class="bi bi-trash3"></i></a>
                                    <a style="background-color: gray" href="#" class="btn-delete" name="btn-delete"><i class="bi bi-upload"></i></i></a>

                                </td>
                            </tr>

                            <%
                                }
                            %>

                            <%
                                }
                            %>
                        </tbody>
                    </table>
                </div>
                <!--                    </div>
                                </div>-->
            </main>
            <!--MAIN-->
        </section>
        <!--CONTENT-->

        <script src="./jsAdmin/scriptA.js"></script>
    </body>
</html>