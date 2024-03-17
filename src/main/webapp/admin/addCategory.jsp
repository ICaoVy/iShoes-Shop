<%-- 
    Document   : addCategory
    Created on : Mar 14, 2024, 3:17:40 PM
    Author     : asus
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="DAOs.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Category</title>
        <link rel="stylesheet" href="../cssAdmin/styleA.css">
    </head>
    <body>
        <section id="delete-form">
            <h1>Add Category</h1>
            <form method="post" action="MyCategory">                        
                <select name="cateD_id" onchange="onChangeHandle()" id="mySelect">
                    <%
                        ProductDAO pDAO = new ProductDAO();
                        ResultSet rs1 = pDAO.getCategory_Details();
                        while (rs1.next()) {
                    %>
                    <option style="display: none" value="<%= rs1.getInt("cateD_id")%>"> <%= rs1.getString("cate_nameD")%></option>
                    <%
                        }
                    %>

                    <%
                        ResultSet rs = pDAO.getCategory_Details();
                        while (rs.next()) {
                    %>
                    <option value="<%= rs.getInt("cateD_id")%>"> <%= rs.getString("cate_nameD")%></option>
                    <%
                        }
                    %>
                </select>

                <label for="parentCategory">Show categories:</label>
                <div name="cate_idL" style="display: none" class="Luxury" id="">
                    <%
                        ResultSet rsL = pDAO.getCategoryLuxury();
                        while (rsL.next()) {
                    %>
                    <span value="<%= rsL.getInt("cate_id")%>"> -<%= rsL.getString("cate_name")%></span><br>
                    <%
                        }
                    %>
                </div>

                
                <div name="cate_idS" style="display: none" class="Sandal" id="">
                    <%                            
                        ResultSet rsS = pDAO.getCategorySandal();
                        while (rsS.next()) {
                    %>
                    <span value="<%= rsS.getInt("cate_id")%>"> -<%= rsS.getString("cate_name")%></span><br>
                    <%
                        }
                    %>
                </div>

                <div  name="cate_idSP" style="display: none" class="Sport" id="">                
                    <%                            
                        ResultSet rsSp = pDAO.getCategorySport();
                        while (rsSp.next()) {
                    %>
                    <span value="<%= rsSp.getInt("cate_id")%>"> -<%= rsSp.getString("cate_name")%></span><br>
                    <%
                        }
                    %>
                </div>

                <script>
                    var selectElement = document.getElementById('mySelect');
                    var selectedValue = selectElement.value;
                    var selectLuxury = document.querySelector(".Luxury");
                    var selectSandal = document.querySelector(".Sandal");
                    var selectSport = document.querySelector(".Sport");
                    console.log('Selected value: ' + selectedValue);

                    if (selectedValue === "1") {
                        selectLuxury.style.display = "block";
                    } else {
                        selectLuxury.style.display = "none";
                    }

                    if (selectedValue === "2") {
                        selectSport.style.display = "block";
                    } else {
                        selectSport.style.display = "none";
                    }

                    if (selectedValue === "3") {
                        selectSandal.style.display = "block";
                    } else {
                        selectSandal.style.display = "none";
                    }
                </script>
                <script>
                    onChangeHandle = () => {
                        // Đối tượng select
                        var selectElement = document.getElementById('mySelect');
                        var selectedValue = selectElement.value;
                        var selectLuxury = document.querySelector(".Luxury");
                        var selectSandal = document.querySelector(".Sandal");
                        var selectSport = document.querySelector(".Sport");
                        console.log('Selected value: ' + selectedValue);

                        if (selectedValue === "1") {
                            selectLuxury.style.display = "block";
                        } else {
                            selectLuxury.style.display = "none";
                        }

                        if (selectedValue === "2") {
                            selectSport.style.display = "block";
                        } else {
                            selectSport.style.display = "none";
                        }

                        if (selectedValue === "3") {
                            selectSandal.style.display = "block";
                        } else {
                            selectSandal.style.display = "none";
                        }
                    }

                </script>


                <label for="cate_name">Enter new category:</label>
                <input type="text" id="cate_name" name="cate_name">


                <button style="width: 30%;
                        padding: 10px;
                        background-color: green;
                        color: #fff;
                        border: none;
                        border-radius: 5px;
                        cursor: pointer;
                        margin-left: 90px;
                        }" class="add" type="submit" name="btn-addC">Add Category </button>
                
                        <button style="width:30%;
                        padding: 10px;
                        background-color: red;
                        color: #fff;
                        border: none;
                        border-radius: 5px;
                        cursor: pointer;
                        margin-left: 200px;
                        margin-top:-51px;
                        }" class="back" type="submit" name="btn_backCa">Back</button>
            </form>
        </section>
                <script>
    document.addEventListener("DOMContentLoaded", function() {
        document.querySelector("form").addEventListener("submit", function(event) {
            // Kiểm tra xem nút "Back" đã được nhấn chưa
            if (document.activeElement.name === "btn_backCa") {
                // Nút "Back" được nhấn, không cần kiểm tra và thêm mục mới
                return;
            }

            // Kiểm tra trường nhập liệu có rỗng không
            var cateName = document.getElementById("cate_name").value.trim();
            if (cateName === "") {
                alert("Please enter a new category.");
                event.preventDefault(); // Ngăn form được submit
                return;
            }

            // Kiểm tra có kí tự đặc biệt không
            var specialChars = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+/;
            if (specialChars.test(cateName)) {
                alert("Category name cannot contain special characters.");
                event.preventDefault(); // Ngăn form được submit
                return;
            }
        });
    });
</script>
    </body>
</html>
