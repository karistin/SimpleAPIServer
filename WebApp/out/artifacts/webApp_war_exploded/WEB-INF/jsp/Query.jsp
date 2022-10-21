<%--
  Created by IntelliJ IDEA.
  User: lucas
  Date: 2022-10-21
  Time: 오후 2:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Yet Another Bookshop</title>
</head>
<body>
<h2>One More Bookshop</h2>
<form method="get" action="queryResult.do">
    <b>Choose an author:</b>
    <input type="checkbox" name="author" value="Tan Ah Teck" />Ah Teck
    <input type="checkbox" name="author" value="Mohammad Ali" />Ali
    <input type="checkbox" name="author" value="Kumar" />Kumar
    <input type="submit" value="Search" />
</form>
</body>
</html>
