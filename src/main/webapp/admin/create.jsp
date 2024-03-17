<%-- 
    Document   : create
    Created on : Feb 24, 2024, 4:25:21 PM
    Author     : ASUS
--%>

<%@page import="DAOs.ProductDAO"%>
<%@page import="java.sql.ResultSet"%>
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
        <div id="add-product-form">
            <h1>Add Product</h1>
            <form method="post" action="MyStore" enctype="multipart/form-data">
                <select name="cateD_id" onchange="onChangeHandle()" id="mySelect">
                    <%
                        ProductDAO pDAO = new ProductDAO();
                        ResultSet rs1 = pDAO.getCategory_Details();
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

                <select name="cate_idL" style="display: none" class="Luxury" id="">
                    <%
                        ResultSet rsL = pDAO.getCategoryLuxury();
                        while (rsL.next()) {
                    %>
                    <option value="<%= rsL.getInt("cate_id")%>"><%= rsL.getString("cate_name")%></option>
                    <%
                        }

                    %>
                </select>

                <select name="cate_idS" style="display: none" class="Sandal" id="">
                    <%                            ResultSet rsS = pDAO.getCategorySandal();
                        while (rsS.next()) {
                    %>
                    <option value="<%= rsS.getInt("cate_id")%>"><%= rsS.getString("cate_name")%></option>
                    <%
                        }

                    %>
                </select>
                <select name="cate_idSP" style="display: none" class="Sport" id="">
                    <%                            ResultSet rsSp = pDAO.getCategorySport();
                        while (rsSp.next()) {
                    %>
                    <option value="<%= rsSp.getInt("cate_id")%>"><%= rsSp.getString("cate_name")%></option>
                    <%
                        }

                    %>
                </select>
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

                <label for="proCode">Product Code:</label>
                <input type="text" id="proCode" name="proCode" >

                <label for="proName">Product Name:</label>
                <input type="text" id="proName" name="proName" >

                <label for="proPrice">Product Price:</label>
                <input type="text" id="proPrice" name="proPrice" step="0.01" >

                <label for="proQuantity">Product Quantity:</label>
                <input type="text" id="proQuantity" name="proQuantity" >

                <label for="proDiscount">Product Discount:</label>
                <input type="text" id="proDiscount" name="proDiscount" step="0.01" >

                <label for="proPicture">Product Picture:</label>
                <input type="file" id="proPicture" name="proPicture">

                <label for="proColour">Product Colour:</label>
                <select type="text" id="proColour" name="proColour">
                    <option value="blue">Blue</option>
                    <option value="red">Red</option>
                    <option value="yellow">Yellow</option>
                    <option value="White">White</option>
                    <option value="Green">Green</option>
                    <option value="Pink">Pink</option>
                    <option value="Black">Black</option>
                    <option value="Grey">Grey</option>
                    <option value="Grey">Orange</option>
                    <option value="Grey">Brown</option>

                </select>

                <label for="proSize">Product Size:</label>
                <select type="text" id="proSize" name="proSize">
                    <%
                        for (int i = 30; i <= 45; i++) {
                                %>
                                <option value="<%= i %>"><%= i %></option>
                    <%
                                
                            }
                    %>
                   
                </select>

                <label for="proBrand">Product Brand:</label>
                <input type="text" id="proBrand" name="proBrand" >

                <label for="proOrigin">Product Origin:</label>
                <input type="text" id="proOrigin" name="proOrigin" >

                <label for="proMaterial">Product Material:</label>
                <input type="text" id="proMaterial" name="proMaterial" >

                <label for="proDescription">Product Description:</label>
                <textarea type="text" id="proDescription" name="proDescription" rows="4" ></textarea>

                <button style="width: 30%;margin-left: 15%" class="add" type="submit" name="btn-add">Add Product</button>
                <button style="width: 30%;
                        padding: 10px;
                        background-color: red;
                        color: #fff;
                        border: none;
                        border-radius: 5px;
                        cursor: pointer;     margin-left: 55%; margin-top: -51px;}" 
                        class="back" type="submit" name="btn-backA">Back</button>
            </form>
        </div>
                    <script>
    document.addEventListener("DOMContentLoaded", function() {
        document.querySelector("form").addEventListener("submit", function(event) {
            // Kiểm tra dữ liệu khi form được submit
            var proCode = document.getElementById("proCode").value.trim();
            var proName = document.getElementById("proName").value.trim();
            var proPrice = document.getElementById("proPrice").value.trim();
            var proQuantity = document.getElementById("proQuantity").value.trim();
            var proDiscount = document.getElementById("proDiscount").value.trim();
            var proBrand = document.getElementById("proBrand").value.trim();
            var proOrigin = document.getElementById("proOrigin").value.trim();
            var proMaterial = document.getElementById("proMaterial").value.trim();
            var proDescription = document.getElementById("proDescription").value.trim();
            
            // Kiểm tra proCode không được để trống
            if (proCode === "") {
                alert("Please enter product code.");
                event.preventDefault(); // Ngăn form được submit
                return;
            }

            // Kiểm tra proName không được để trống
            if (proName === "") {
                alert("Please enter product name.");
                event.preventDefault(); // Ngăn form được submit
                return;
            }

            // Kiểm tra proPrice phải là số
            if (isNaN(proPrice) || proPrice === "") {
                alert("Please enter a valid product price.");
                event.preventDefault(); // Ngăn form được submit
                return;
            }

            // Kiểm tra proQuantity phải là số
            if (isNaN(proQuantity) || proQuantity === "") {
                alert("Please enter a valid product quantity.");
                event.preventDefault(); // Ngăn form được submit
                return;
            }

            // Kiểm tra proDiscount phải là số
            if (isNaN(proDiscount)) {
                alert("Please enter a valid product discount.");
                event.preventDefault(); // Ngăn form được submit
                return;
            }

            // Kiểm tra proBrand không được để trống
            if (proBrand === "") {
                alert("Please enter product brand.");
                event.preventDefault(); // Ngăn form được submit
                return;
            }

            // Kiểm tra proOrigin không được để trống
            if (proOrigin === "") {
                alert("Please enter product origin.");
                event.preventDefault(); // Ngăn form được submit
                return;
            }

            // Kiểm tra proMaterial không được để trống
            if (proMaterial === "") {
                alert("Please enter product material.");
                event.preventDefault(); // Ngăn form được submit
                return;
            }

            // Kiểm tra proDescription không được để trống
            if (proDescription === "") {
                alert("Please enter product description.");
                event.preventDefault(); // Ngăn form được submit
                return;
            }
        });

        // Kiểm tra chỉ cho phép nhập số trong các trường số
        var numberFields = document.querySelectorAll("#proPrice, #proQuantity, #proDiscount");
        numberFields.forEach(function(element) {
            element.addEventListener("keypress", function(event) {
                var charCode = event.which ? event.which : event.keyCode;
                if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                    event.preventDefault();
                }
            });
        });

//        // Kiểm tra không cho phép nhập kí tự đặc biệt trong các trường số
//        var specialCharFields = document.querySelectorAll("#proCode, #proName, #proBrand, #proOrigin, #proMaterial");
//        specialCharFields.forEach(function(element) {
//            element.addEventListener("keypress", function(event) {
//                var charCode = event.which ? event.which : event.keyCode;
//                if (charCode == 33 || (charCode >= 35 && charCode <= 38) || (charCode >= 40 && charCode <= 47) || charCode == 58 || charCode == 59 || charCode == 61 || charCode == 63 || charCode == 64 || charCode == 91 || charCode == 92 || charCode == 93 || charCode == 94 || charCode == 96 || charCode == 123 || charCode == 124 || charCode == 125 || charCode == 126) {
//                    event.preventDefault();
//                }
            });
        });
    });
</script>
    </body>
</html>
