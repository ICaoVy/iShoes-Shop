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
                <div class="head-title">
                    <div class="left">
                        <h1>My Store</h1>
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
                    <table class="product-table text-center">
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
                                String pro_code = request.getParameter("pro_code");
                                int pro_id = Integer.parseInt(request.getParameter("pro_id"));
                                productList = pDAO.getAllProduct(pro_code ,pro_id);
                                Set<String> uniqueProductCodes = new HashSet<>();
                                for (Product l : productList) {
                                    String productCode = pDAO.subString(l.getPro_code());
                                    if (uniqueProductCodes.add(l.getPro_code())) {
                            %>
                            <tr> <td><%= l.getPro_code()%></td>
                                <td><%= l.getPro_name()%></td>
                                <td><img style="margin: auto" class="img-fluid" width="120px" src="./../images/<%= l.getPro_picture()%>" alt="<%=l.getPro_picture()%>"/></td>
                                <td>

                                </td>
                            </tr>

                            <%
                                    }
                                }
                            %>




                        </tbody>
                    </table>
                </div>


                <div class="head container">
                    <form action="" method="post" enctype="multipart/form-data" >
                        <table class="product-table text-center">
                            <thead>
                                <tr>
                                    <th>Galleris</th>

                                </tr>
                            </thead>
                            <tbody class="text-center">
                                <tr>
                                    <%
                                        ResultSet rs = pDAO.getGalley(pro_id);
                                        while (rs.next()) {

                                    %>
                                    <td style="position: relative"><img style="margin: auto" class="img-fluid" width="120px" src="./../images/<%= rs.getString("gal_picture")%>" alt="<%= rs.getString("gal_picture")%>"/>
                                        <a onclick="return alert('Are you sure?')" href="/AdminController/GANEW/Delete?gal_id=<%= rs.getInt("gal_id") %>&pro_id=<%= rs.getInt("pro_id") %>&pro_code=<%= pro_code %>" style="position: absolute;top: 0px;right:10px; color: red">X</a></td>
                                    <%
                                        }
                                    %>
                                    <td>
                                        <input class="form-control" type="file" name="file-picture"/>
                                        <button style="width: 10%;
                        padding: 10px;
                        background-color: green;
                        color: #fff;
                        border: none;
                        border-radius: 5px;
                        cursor: pointer;
                        margin-left: 80%;
                        margin-bottom:-10px;
                        }" type="submit" name="btn-anganew" class="mt-3">Add</button>
                                    </td>
                                </tr>



                            </tbody>
                        </table>
                    </form>
                </div>
            </main>
            <!--MAIN-->
        </section>
        <!--CONTENT-->

        <script src="./jsAdmin/scriptA.js"></script>
    </body>
</html>