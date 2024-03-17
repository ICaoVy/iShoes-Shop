<%-- 
    Document   : listorder
    Created on : Feb 24, 2024, 4:21:37 PM
    Author     : ASUS
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="DAOs.OrdersDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <!--Boxicons-->
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <!--My CSS-->
        <link rel="stylesheet" href="../cssAdmin/styleA.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css" />
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
            <main id="info-main">
                <div class="head-title">
                    <div class="left">
                        <h1>Dashboard</h1>
                        <ul class="breadcrumb">
                            <li>
                                <a href="#">Dashboard</a>
                            </li>
                            <!-- <li><i class='bx bxs-chevron-right' ></i></li> -->
                            <!-- <li>
                                <a class="active" href="#">Home</a>
                            </li> -->
                        </ul>
                    </div>
                    <a href="#" class="btn-download">
                        <i class='bx bxs-cloud-download' ></i>
                        <span class="text">Download PDF</span>
                    </a>
                </div>
                <ul class="box-info">
                    <li>
                        <i class='bx bxs-check-square' ></i>
                        <span class="text">
                            <h3>1020</h3>
                            <p>New Order</p>
                        </span>
                    </li>
                    <li>
                        <i class='bx bxs-group' ></i>
                        <span class="text">
                            <h3>2834</h3>
                            <p>Vistors</p>
                        </span>
                    </li>
                    <li>
                        <i class='bx bxs-dollar-circle' ></i>
                        <span class="text">
                            <h3>$1020</h3>
                            <p>Total Sale</p>
                        </span>
                    </li>
                </ul>

                <div class="table-data">
                    <div class="order">
                        <div class="head">
                            <h3>List Orders</h3>
                            <i class='bx bx-search' ></i>
                            <i class='bx bx-filter' ></i>
                        </div>
                        <div>
                            <table class="product-table text-center">
                                <thead>
                                    <tr>
                                        <th>Order ID</th>
                                        <th>Order Name</th>
                                        <th>Date Order</th>
                                        <th>Phone</th>
                                        <th>Address</th>
                                        <th>Note</th>
                                        <th>Price</th>
                                        <th>Status</th>
                                        <th>Change Status</th>
                                    </tr>
                                </thead>
                                <tbody class="text-center">
                                    <%
                                        OrdersDAO oDAO = new OrdersDAO();
                                        ResultSet rs = oDAO.getAll();
                                        while (rs.next()) {
                                    %>
                                    <tr>
                                        <td><%= rs.getInt("order_id")%></td>
                                        <td><%= rs.getString("order_fullname")%></td>
                                        <td><%= rs.getDate("pay_create_at")%></td>
                                        <td><%= rs.getString("order_phone")%></td>
                                        <td><%= rs.getString("order_address")%></td>
                                        <td><%= rs.getString("order_note")%></td>
                                        <td><%= rs.getFloat("detail_price")%></td>
                                        <td>
                                            <span class="status pending"><% if (rs.getInt("order_status") == 0) {
                                            out.print("Processing");
                                                } else {
                                                out.print("Approved");
                                            } %></span>
                                        </td>
                                        <td>
                                            <a href="/AdminController/ChangeStatus/<%= rs.getInt("order_id")%>" class="btn-edit" name="btn-edit">Approve</a>
                                        </td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </main>
            <!--MAIN-->
        </section>
        <!--CONTENT-->

        <script src="./jsAdmin/scriptA.js"></script>
    </body>
</html>