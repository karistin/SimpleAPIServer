<%--
  Created by IntelliJ IDEA.
  User: lucas
  Date: 2022-10-26
  Time: 오전 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원 가입창</title>
    <script type="text/javascript">
      function fn_sendMember(){
        var frmMember=document.frmMember;
        var id=frmMember.id.value;
        var pwd=frmMember.pwd.value;
        var name=frmMember.name.value;
        var email=frmMember.email.value;
        if(id.length==0 ||id==""){
          alert("아이디는 필수입니다.");
        }else if(pwd.length==0 ||pwd==""){
          alert("비밀번호는 필수입니다.");
        }
        else if(name.length==0 ||name==""){
          alert("이름은 필수입니다.");
        }else if(email.length==0 ||email==""){
          alert("이메일은 필수입니다.");
        }else{
          frmMember.method="post";
          frmMember.action="Member";
          frmMember.submit();
          //전송 방법은 post이고 매핑이름은 Member3, 그리고 서블릿으로 전송한다.
        }
      }
    </script>
</head>
<body>
<form name="frmMember">
  <table>
    <th>회원 가입</th>
    <tr>
      <td>ID</td>
      <td><input type="text" name="id" value="ExampleId"></td>
    </tr>
    <tr>
      <td>Password</td>
      <td><input type="password" name="pwd" value="ExamplePwd"></td>
    </tr>
    <tr>
      <td>이름</td>
      <td><input type="text" name="name" value="ExampleName"></td>
    </tr>
    <tr>
      <td>이메일</td>
      <td><input type="text" name="email" value="ExampleEmail@naver.com"></td>
    </tr>
    <!-- 위의 input태그는 서블릿으로 데이터를 전송한다. -->
  </table>
  <input type="button" value="가입하기" onclick="fn_sendMember()">
  <input type="reset" value="다시입력">
  <input  type="hidden" name="command" value="addMember" />
  <input type="button" value="홈으로 돌아가기" onclick="location.href='main.do'">
  <!-- hidden 태그를 사용하여 서블릿에게 회원 등록임을 알림 -->
</form>
</body>
</html>
