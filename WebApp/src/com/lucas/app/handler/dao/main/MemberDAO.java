package com.lucas.app.handler.dao.main;



import com.lucas.app.common.sql.Config;
import com.lucas.app.handler.dto.MemberDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    public static MemberDAO instance;

    public static MemberDAO getInstance(){
        if (instance == null) {
            instance = new MemberDAO();
        }
        return instance;
    }
    public List<MemberDTO> listMembers(){
        List<MemberDTO> list = new ArrayList<>();
        Connection conn = Config.getInstance().sqlLogin();
        String query = "select * from t_member ";
        ResultSet res;
        try (Statement stat = conn.createStatement();){
            res = stat.executeQuery(query);
            while (res.next()) {
                String id = res.getString("id");
                String pwd = res.getString("pwd");
                String name = res.getString("name");
                String email = res.getString("email");
                Date joinDate = res.getDate("joinDate");
                MemberDTO memberDTO = new MemberDTO();
                memberDTO.setId(id);
                memberDTO.setPwd(pwd);
                memberDTO.setName(name);
                memberDTO.setEmail(email);
                memberDTO.setJoinDate(joinDate);
                list.add(memberDTO);
            }
            res.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void addMember(MemberDTO memberDTO) {
        try {
            Connection conn = Config.getInstance().sqlLogin();
            String id = memberDTO.getId();

            String pwd = memberDTO.getPwd();
            String name = memberDTO.getName();
            String email = memberDTO.getEmail();

            String query = "insert into t_member";

            query += "(id,pwd,name,email)";
            query += "values(?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,id);
            pstmt.setString(2,pwd);
            pstmt.setString(3,name);
            pstmt.setString(4,email);

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void delMember(String id) {
        try {
            Connection conn = Config.getInstance().sqlLogin();
            String query = "delete from t_member" + " where id =?";
            PreparedStatement psmt = conn.prepareStatement(query);
            psmt.setString(1, id);
            psmt.executeUpdate();
            psmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean idCheck(MemberDTO vo) {
        try{
            Connection conn = Config.getInstance().sqlLogin();
            String query = "select id from t_member" + " where id =?";
            PreparedStatement psmt = conn.prepareStatement(query);

            psmt.setString(1, vo.getId());
            ResultSet result = psmt.executeQuery();


            if(result.next())
            {
                return false;
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
