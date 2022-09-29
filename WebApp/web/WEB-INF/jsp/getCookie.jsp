<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: lucas
  Date: 2022-09-29
  Time: 오후 1:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
  Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
      String cookieName = cookie.getName();
        String cookieValue = URLDecoder.decode(cookie.getValue(), "UTF-8");


    %>
    <h1> 쿠키 이름 - <%= cookieName %>, 쿠키 값 - <%= cookieValue%></h1>
<%
    }
%>
</body>
</html>
