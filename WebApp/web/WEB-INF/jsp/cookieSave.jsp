<%@ page import="java.net.URLEncoder" %><%--
  Created by IntelliJ IDEA.
  User: lucas
  Date: 2022-09-29
  Time: 오후 1:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Cookie cookie = new Cookie("message", URLEncoder.encode("한글입니다.", "UTF-8"));
    response.addCookie(cookie);
%>
<h1> 쿠키 한글 저장 </h1>
<h3>get Cookie</h3>
<a href="getCookie.do">GET</a>
</body>
</html>
