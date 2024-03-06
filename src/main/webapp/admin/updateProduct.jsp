<%-- 
    Document   : editProduct
    Created on : Feb 24, 2024, 4:23:07 PM
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
        <link rel="stylesheet" href="./../lib/bootstrap/bootstrap_css/bootstrap.min.css"/>
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
            if (pro != null) {
        %>
        <section id="update-form">
            <h1>Update Product</h1>
            <div class="container mb-5">
                <form action="" method="post" class="">
                <label for="proCate">Product Cate:</label>
                <input type="text" id="proCate" name="proCate" value="<%= pro.getCate_id()%>" >

                <label for="proCode">Product Code:</label>
                <input type="text" id="proCode" name="proCode" value="<%= pro.getPro_code()%>" >

                <label for="proName">Product Name:</label>
                <input type="text" id="proName" name="proName" value="<%= pro.getPro_name()%>" >

                <label for="proPrice">Product Price:</label>
                <input type="text" id="proPrice" name="proPrice" value="<%= pro.getPro_price()%>">


                <label for="proDiscount">Product Discount:</label>
                <input type="text" id="proDiscount" name="proDiscount" value="<%= pro.getPro_size()%>">


                <label for="proPicture">Product Picture:</label>
                <input type="file" id="proPicture" name="proPicture">
                <img src="../images/<%= pro.getPro_picture()%>" alt="<%= pro.getPro_picture()%>" id="previewImage" style="max-width: 200px;" >


                <label for="proSize">Product Size:</label>
                <input type="text" id="proSize" name="proSize" value="<%= pro.getPro_size()%>">


                <label for="proQuan">Product Quantity:</label>
                <input type="text" id="proQuan" name="proQuan" value="<%= st.getStock_import()%>" >


                <label for="proColour">Product Colour:</label>
                <input type="text" id="proColour" name="proColour" value="<%= pro.getPro_colour()%>" >


                <label for="proBrand">Product Brand:</label>
                <input type="text" id="proBrand" name="proBrand" value="<%= st.getStock_import()%>" >


                <label for="proOrigin">Product Origin:</label>
                <input type="text" id="proOrigin" name="proOrigin" value="<%= pro.getPro_origin()%>" >

                <label for="proMaterial">Product Material:</label>
                <input type="text" id="proMaterial" name="proMaterial" value="<%= pro.getPro_material()%>" >


                <label for="proDes">Product Description:</label>
                <input type="text" id="proDes" name="proDes" value="<%= pro.getPro_description()%>" >

                <label for="prodCreate">Product Create_AT:</label>
                <input type="text" id="prodCreate" name="prodCreate" value="<%= pro.getPro_create_at()%>">

                <label for="proUpdate">Product Update_At:</label>
                <input type="text" id="proUpdate" name="proUpdate" value="<%= pro.getPro_update_at()%>" >


                <label for="proDelete">Product Delete_At :</label>
                <input type="text" id="proDelete" name="proDelete" value="<%= pro.getPro_delete_at()%>" >

                <div class="action-buttons">
                    <button type="submit" class="btn-update" name="btn-update">Update</button>
                    <button type="submit" class="btn-cancel" name="btn-cancel" onclick="cancelEdit()">Cancel</button>
                </div>
            </form>
            </div>
        </section>
        <script src="./jsAdmin/scriptA.js"></script>
        <%
            }
        %>
    </body>
</html>
