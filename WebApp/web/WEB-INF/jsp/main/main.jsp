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
<a href="example.do">연습 페이지로 이동하기</a><br>
<h3>Filter Testing</h3>
    <form action="Id.do" method="post">
        ID : <input type="text" name="Id">
        <input type="submit" value="확인">
    </form>
<h3>Init Testing</h3>
    <a href="${pageContext.request.contextPath}/output">output</a>
</body>
</html>
