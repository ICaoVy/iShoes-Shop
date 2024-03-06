<%-- 
    Document   : manageruser
    Created on : Feb 24, 2024, 4:19:31 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <title>Document</title>
        <link rel="stylesheet" href="./cssAdmin/styleA.css">
        <link rel="stylesheet" href="user.css">
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
                <a href="#">
                    <i class='bx bxs-chat' ></i>
                    <span class="text">Message</span>
                </a>
            </li>
            <li >
                <a href="./manageruser.jsp">
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
                <a href="./importproduct.jsp">
                    <i class='bx bxs-plus-circle' ></i>
                    <span class="text">Import product</span>
                </a>
            </li>
        </ul>
        <ul class="side-menu top">
            <li>
                <a href="#">
                    <i class='bx bxs-cog' ></i>
                    <span class="text">Settings</span>
                </a>
            </li>
            <li>
                <a href="/LogoutController" class="logout">
                    <i class='bx bx-log-out' ></i>
                    <span class="text">Logout</span>
                </a>
            </li>
        </ul>
    </section>

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


            <div class="table-data">
                <div class="order">
                    <div class="head">
                        <h2>Manage User</h2>
                        <!-- <i class='bx bx-search' ></i>
                        <i class='bx bx-filter' ></i> -->
                    </div>
                    <div>
                        <table>
                            <thead>
                                <tr>

                                    <th>Username</th>
                                    <th>User-email</th>
                                    <th>User-phone</th>
                                    <th>Date Create Account</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr id="row-1">
                                    <td>User 1</td>
                                    <td>user1@gmail.com </td>
                                    <td>0909090909</td>
                                    <td>19-01-2024</td>
                                    <td>
                                        <button onclick="changeStatus('status1')"><span id="status1" class="status completed">Active</span></button>
                                        <button><a style="color: black;" href="./userdetail.html">Detail</a></button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <script src="./jsAdmin/scriptA.js"></script>
                </body>
                </html>
