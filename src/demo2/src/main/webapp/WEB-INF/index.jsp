
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>product</title>
</head>
<body>
<h1 style="text-align: center">Product Discount Calculator</h1>
<form action="/product" method="post">
    <h3>Product Description</h3>
    <input type="text" name="descrise">
    <h3>List Price</h3>
    <input type="text" name="price">
    <h3>Discount Percent</h3>
    <input type="text" name="percent">
    <button type="submit">Calculate Discount</button>
</form>
</body>
</html>
