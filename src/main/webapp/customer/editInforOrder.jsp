<%-- 
    Document   : editInforOrder
    Created on : Mar 11, 2024, 2:47:38 PM
    Author     : binht
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./../lib/bootstrap/bootstrap_css/bootstrap.min.css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css" />
        <link rel="stylesheet" href="<%= request.getContextPath()%>/lib/bootstrap/bootstrap_css/bootstrap.min.css" />
        <link rel="stylesheet" href="<%= request.getContextPath()%>/css/style.css" />
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <style>
            h1 {
                text-align: center;
                font-size: 30px;
                color: #333;
                margin-top: 20px;
            }
            .action-buttons {
                display: flex;
                justify-content: center;

            }
            .btn-edit,
            .btn-cancel {
                padding: 10px;
                border: none;
                border-radius: 5px;
                color: #fff;
                cursor: pointer;
            }
            .btn-edit {
                background-color: #4caf50;
                margin-right: 10px;
                padding: 15px 20px;
            }
            .btn-cancel {
                background-color: #f44336;
                padding: 15px 20px;
            }
        </style>
    </head>
    <body>
        <section id="edit-form">
            <h1>Edit Information Order</h1>
            <div class="container mb-5 mt-3">
                <form action="OrderController" method="post" class="">
                    <c:forEach items="${oDetail}" var="o" varStatus="loop">
                        <div class="mb-3 row">
                            <label for="orderID" class="col-sm-2 col-form-label">Order ID:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="orderID" name="orderID" value="${o.order_id}" readonly="">
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="orderFullname" class="col-sm-2 col-form-label">Fullname:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="orderFullname" name="orderFullname" value="${o.order_fullname}" readonly="">
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="orderEmail" class="col-sm-2 col-form-label">Email:</label>
                            <div class="col-sm-10">
                                <input type="email" class="form-control" id="orderEmail" name="orderEmail" value="${o.order_email}" required pattern=".*\S.*">
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="orderPhone" class="col-sm-2 col-form-label">Phone:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="orderPhone" name="orderPhone" value="${o.order_phone}" required pattern="[0-9]{10,12}" title="Please enter a phone number with 10 to 12 digits.">
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="orderAddress" class="col-sm-2 col-form-label">Address:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="orderAddress" name="orderAddress" value="${o.order_address}" required pattern=".*\S.*">
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="orderNote" class="col-sm-2 col-form-label">Note:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="orderNote" name="orderNote" value="${o.order_note}">
                            </div>
                        </div>

                        <div class="action-buttons">
                            <button type="submit" class="btn-edit" name="btn-edit">Edit</button>
                            <button type="submit" class="btn-cancel" name="btn-cancel" onclick="cancelEdit()">Cancel</button>
                        </div>
                    </c:forEach>
                </form>
            </div>
        </section>
    </body>
</html>
