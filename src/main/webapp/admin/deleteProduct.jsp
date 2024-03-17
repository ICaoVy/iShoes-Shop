<%-- 
    Document   : deleteProduct
    Created on : Feb 24, 2024, 4:24:48 PM
    Author     : ASUS
--%>

<%@page import="Models.Stock"%>
<%@page import="DAOs.StockDAO"%>
<%@page import="Models.Product"%>
<%@page import="DAOs.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link rel="stylesheet" href="../cssAdmin/styleA.css">
    </head>
    <body>
        
        <%
            int pro_id = Integer.parseInt(request.getParameter("id"));
            ProductDAO pDAO = new ProductDAO();
            Product pro = pDAO.getProductQH(pro_id);
            StockDAO tDAO = new StockDAO();
            Stock st = tDAO.getStock(pro_id);
            if(pro != null){
        %>
        <section id="delete-form">
            <h1>Delete Product</h1>
            <form action="" method="post">
                <label for="productCode">Product Code:</label>
                <input type="text" id="productCode" name="productCode" value="<%= pro.getPro_code()%>" >
                
                <label for="productName">Product Name:</label>
                <input type="text" id="productName" name="productName" value="<%= pro.getPro_name()%>" >

                <label for="productPicture">Product Picture:</label> 
                <input type="file" id="productPicture" name="productPicture">
                <img src="../images/<%= pro.getPro_picture()%>" alt="<%= pro.getPro_picture()%>" id="previewImage" style="max-width: 200px;">

                <label for="productPrice">Product Price:</label>
                <input type="text" id="productPrice" name="productPrice" value="<%= pro.getPro_price()%>" >

                <label for="productQuantity">Product Quantity:</label>
                <input type="text" id="productQuantity" name="productQuantity" value="<%= st.getStock_import()%>" >

                <div class="action-buttons">
                    <button style="box-shadow: none; color: white;" type="submit" class="btn-deleteD" name="btn-deleteD">Delete</button>
                    <button style="box-shadow: none; color: white;" type="submit" class="btn-cancelD" name="btn-cancelID" >Cancel</button>
                </div>
            </form>
        </section>
        <%
            }
        %>
    </body>
</html>
