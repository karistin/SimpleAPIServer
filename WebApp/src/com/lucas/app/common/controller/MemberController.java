package com.lucas.app.common.controller;


import com.lucas.app.handler.dao.main.MemberDAO;
import com.lucas.app.handler.dto.MemberDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;

@WebServlet("/Member")
public class MemberController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doHandle(req, resp);
    }

    private void doHandle(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");

            MemberDAO dao = new MemberDAO();
            PrintWriter out = response.getWriter();
            String command = request.getParameter("command");

            if (command != null && command.equals("addMember")) {
                String _id=request.getParameter("id");

                String _pwd=request.getParameter("pwd");
                String _name=request.getParameter("name");
                String _email=request.getParameter("email");
                MemberDTO vo=new MemberDTO();
                vo.setId(_id);
                vo.setPwd(_pwd);
                vo.setName(_name);
                vo.setEmail(_email);
//                duplication check
                if(dao.idCheck(vo))
                {
                    dao.addMember(vo);
                }
            }
            else if(command!= null && command.equals("delMember")) {
                String id = request.getParameter("id");
                dao.delMember(id);
            }

            List list=dao.listMembers();
            out.print("<html><body>");
            out.print("<table border=1><tr align='center' bgcolor='lightgreen'>");
            out.print("<td>아이디</td><td>비밀번호</td ><td>이름</td><td>이메일</td><td>가입일</td><td >삭제</td></tr>");

            for (int i=0; i<list.size();i++){
                MemberDTO memberVO=(MemberDTO) list.get(i);

                String id=memberVO.getId();
                String pwd = memberVO.getPwd();
                String name = memberVO.getName();
                String email =memberVO.getEmail();

                Date joinDate = memberVO.getJoinDate();
                out.print("<tr><td>"+id+"</td><td>"
                        +pwd+"</td><td>"
                        +name+"</td><td>"
                        +email+"</td><td>"
                        +joinDate+"</td><td>"
                        +"<a href='/Member?command=delMember&id="+id+"'>삭제 </a></td></tr>");

            }
            out.print("</table></body></html>");
            out.print("<a href='/form.do'>새 회원 등록하기</a");
            //클릭하면 다시 회원 가입창으로 이동

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
