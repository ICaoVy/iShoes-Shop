<%-- 
    Document   : purchasehistory
    Created on : Feb 24, 2024, 3:02:21 PM
    Author     : ASUS
--%>

<%@page import="Models.Orders"%>
<%@page import="java.util.List"%>
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

        <!--CSS============================================= -->
        <link rel="stylesheet" href="../sang/css/main.css">
        <!-- <style>
            #current-page {
            color: #0e0e0e;
            background-color: white;
            border: #0e0e0e 1px solid;
        }
        </style> -->
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
        .order-status {
            border: none;
            width: 90px;
            position: relative;
            top: -7px;
            margin-left: 3px
        }
        .processing {
            color: darkorange;
        }

        .approved {
            color: green;
        }




    </style>
    <body>
        <header>
            <div class="mt-3 head-close">
                <nav class="container">
                    <div class="row">
                        <div class="col-md-3 header-1 ">
                            <a href="/"><img width="70px" src="../images/LogoICafy-removebg-preview.png" width="container-fluid"
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

        <div class="mt-4 container">
            <div class="d-flex justify-content-around cart-list-1">
                <a id="current-page" href="/OrderController/Ordered">Purchase History</a>
            </div>
            <%-- Initialize counters for statuses --%>
            <%-- Initialize counters for statuses --%>
            <% int processingCount = 0; %>
            <% int approvedCount = 0;%>
            <% int totalCount = approvedCount + processingCount;%>

            <h5 class="mt-4">Your orders: <span id="processingCount"></span> Processing, <span id="approvedCount"></span> Approved, <span id="totalCount"></span> Total Order</h5>

            <table>
                <tr>
                    <th>Order details</th>
                    <th>Order product</th>
                    <th></th>
                </tr>
                <c:forEach items="${orders}" var="o" varStatus="loop">
                    <c:if test="${loop.index == 0 || orders[loop.index - 1].order_id != o.order_id}">
                        <c:set var="rowSpan" value="1" />
                        <c:forEach var="subOrder" items="${orders}" varStatus="subLoop">
                            <c:if test="${subLoop.index > loop.index && subOrder.order_id == o.order_id}">
                                <c:set var="rowSpan" value="${rowSpan + 1}" />
                            </c:if>
                        </c:forEach>
                        <tr>
                            <td rowspan="${rowSpan}">
                                <h6>Order: ${o.order_id}</h6>
                                <p>Order date: ${o.pay_create_at}</p>
                            </td>
                            <td>
                                <h6>${o.detail_quantity} x ${o.pro_name}</h6>
                                <img style="width: 25%" src="../images/${o.pro_picture}">
                            </td>
                            <td rowspan="${rowSpan}" >
                                <div style="display: flex; justify-content: space-between;">
                                    <div class="status">
                                        <h6>Order status:</h6>
                                        <input disabled id="status" class="order-status"  value="${o.order_status}">

                                    </div>
                                    <div >
                                        <a style="border-radius: 30px" class="btn btn-primary" href="/OrderController/Ordered/BuyAgain/${o.order_id}">Buy Again</a>
                                        <a style="border-radius: 30px" class="btn btn-success" href="/OrderController/Ordered/detail/${o.order_id}">Detail</a>
                                        <a style="border-radius: 30px" class="btn btn-danger" href="/OrderController/Ordered/cancel/${o.order_id}">Cancel</a>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <c:forEach var="subOrder" items="${orders}" varStatus="subLoop">
                            <c:if test="${subLoop.index > loop.index && subOrder.order_id == o.order_id}">
                                <tr>
                                    <td>
                                        <h6>${subOrder.detail_quantity} x ${subOrder.pro_name}</h6>
                                        <img style="width: 25%" src="../images/${subOrder.pro_picture}">
                                    </td>

                                </tr>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </c:forEach>




            </table>

        </div>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                var orderStatusInputs = document.querySelectorAll('.order-status');
                var processingCount = 0;
                var approvedCount = 0;

                // Define CSS classes for different statuses
                var processingClass = "processing";
                var approvedClass = "approved";

                orderStatusInputs.forEach(function (input) {
                    var status = parseInt(input.value);
                    if (status === 0) {
                        processingCount++;
                        // Set the value of the current element to "Processing"
                        input.value = "Processing";
                        // Add CSS class for processing
                        input.classList.add(processingClass);
                    } else if (status === 1) {
                        approvedCount++;
                        // Set the value of the current element to "Approved"
                        input.value = "Approved";
                        // Add CSS class for approved
                        input.classList.add(approvedClass);
                    }
                });


                var totalCount = processingCount + approvedCount;
                // Update counts in HTML
                document.getElementById('processingCount').textContent = processingCount;
                document.getElementById('approvedCount').textContent = approvedCount;
                document.getElementById('totalCount').textContent = totalCount;
            });
        </script>

    </body>
</html>
