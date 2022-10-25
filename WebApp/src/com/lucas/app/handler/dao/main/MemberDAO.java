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
                String pwd = res.getString("id");
                String name = res.getString("id");
                String email = res.getString("id");
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
}
