<%-- 
    Document   : create
    Created on : Feb 24, 2024, 4:25:21 PM
    Author     : ASUS
--%>

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
            <form method="post" action="MyStore">
                <label for="proCate">Product Cate:</label>
                <input type="number" id="proCate" name="proCate" >

                <label for="proCode">Product Code:</label>
                <input type="text" id="proCode" name="proCode" >

                <label for="proName">Product Name:</label>
                <input type="text" id="proName" name="proName" >

                <label for="proPrice">Product Price:</label>
                <input type="number" id="proPrice" name="proPrice" step="0.01" >

                <label for="proQuantity">Product Quantity:</label>
                <input type="text" id="proQuantity" name="proQuantity" >

                <label for="proDiscount">Product Discount:</label>
                <input type="number" id="proDiscount" name="proDiscount" step="0.01" >

                <label for="proPicture">Product Picture:</label>
                <input type="file" id="proPicture" name="proPicture">

                <label for="proColour">Product Colour:</label>
                <select type="text" id="proColour" name="proColour">
                    <option value="blue">Blue</option>
                    <option value="red">Red</option>
                    <option value="yellow">Yellow</option>
                </select>

                <label for="proSize">Product Size:</label>
                <select type="number" id="proSize" name="proSize">
                    <option value="30">30</option>
                    <option value="31">31</option>
                    <option value="32">32</option>
                    <option value="33">33</option>
                </select>

                <label for="proBrand">Product Brand:</label>
                <input type="text" id="proBrand" name="proBrand" >

                <label for="proOrigin">Product Origin:</label>
                <input type="text" id="proOrigin" name="proOrigin" >

                <label for="proMaterial">Product Material:</label>
                <input type="text" id="proMaterial" name="proMaterial" >

                <label for="proDescription">Product Description:</label>
                <textarea type="text" id="proDescription" name="proDescription" rows="4" ></textarea>

                <button class="add" type="submit" name="btn-add">Add Product</button>
                <button style="width: 100%;
                        padding: 10px;
                        background-color: red;
                        color: #fff;
                        border: none;
                        border-radius: 5px;
                        cursor: pointer;" class="back" type="submit" name="btn-backA">Back</button>
            </form>
        </div>
    </body>
</html>
