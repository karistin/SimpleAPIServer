<%--
  Created by IntelliJ IDEA.
  User: lucas
  Date: 2022-09-21
  Time: 오후 2:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%@include file="./header.jsp"%>
<body>
main 페이지의 내용<br>
<a href="example.do">DB</a><br>
<h3>Filter Testing</h3>
    <form action="Id.do" method="post">
        ID : <input type="text" name="Id">
        <input type="submit" value="확인">
    </form>
<h3>Web Servlet Testing</h3>
    <a href="${pageContext.request.contextPath}/WebServlet">Servlet 3.0 </a>
<h3>Save Cookie</h3>
    <a href="cookieSave.do">Save</a>
<h3>query String</h3>
    <a href="query.do">DBSearch</a>
<h3>CURD Testing</h3>
    <a href="form.do">CURD 3.0</a>

</body>
</html>
