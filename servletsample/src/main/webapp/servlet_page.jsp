<%--
  Created by IntelliJ IDEA.
  User: seong
  Date: 2022-08-31
  Time: 오후 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Servlet page!!</h1>
    <p>
        <%= request.getHeader("User-Agent") %>
    </p>
</body>
</html>
