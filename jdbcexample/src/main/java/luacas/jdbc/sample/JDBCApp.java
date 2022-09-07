package luacas.jdbc.sample;

import java.sql.*;

public class JDBCApp {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_jdbc", "root", "test");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from sample");
            while (rs.next()) {
                int idx = rs.getInt("idx");
                String name = rs.getString("name");
                System.out.printf("%d - %s\n", idx, name);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
