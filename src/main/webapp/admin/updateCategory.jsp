<%-- 
    Document   : updateCategory
    Created on : Mar 16, 2024, 5:10:08 PM
    Author     : asus
--%>

<%@page import="DAOs.ProductDAO"%>
<%@page import="Models.Category_Details"%>
<%@page import="Models.Category"%>
<%@page import="DAOs.CategoryDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="../cssAdmin/styleA.css">
    </head>
    <body>
        <%
            ProductDAO pDAO = new ProductDAO();
            int cate_id = Integer.parseInt(request.getParameter("id"));
            CategoryDAO cDAO = new CategoryDAO();

            Category ca = cDAO.getCategoryID(cate_id);

            if (ca != null) {
        %>
        <section id="delete-form">
            <h1>Update Product</h1>
            <div class="container mb-5">
                <form action="" method="post" class="">

                    <select name="cateD_id" id="mySelect">
                        <%
                            ResultSet rs1 = cDAO.getCategory_DetailsWhereCateID(cate_id);
                            while (rs1.next()) {
                        %>
                        <option style="display: none" value="<%= rs1.getInt("cateD_id")%>"><%= rs1.getString("cate_nameD")%></option>
                        <%
                            }
                        %>
                        <%
                            ResultSet rs = pDAO.getCategory_Details();
                            while (rs.next()) {
                        %>
                        <option value="<%= rs.getInt("cateD_id")%>"><%= rs.getString("cate_nameD")%></option>
                        <%
                            }
                        %>
                    </select>
                    <%
                        }
                    %>

                    <input type="text" name="newCate" value="<%= ca.getCate_name() %>"/>
                    <button style="width: 20%;
                        padding: 10px;
                        background-color: green;
                        color: #fff;
                        border: none;
                        border-radius: 5px;
                        cursor: pointer;
                        margin-left: 90px;
                        }" type="submit" name="btn-upCate">Update</button>
                        
                        
                        <button style="width: 20%;
                        padding: 10px;
                        background-color: red;
                        color: #fff;
                        border: none;
                        border-radius: 5px;
                        cursor: pointer;
                        margin-left: 200px;
                        margin-top:-51px;
                        }" type="submit" name="btn-backCateU">Back</button>
                </form>
            </div>
        </section>
        </body>
</html>
