package DAO;

import com.google.gson.Gson;
import com.mysql.cj.protocol.Resultset;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAOImp implements UserDAO{
    private Connection cnn = null;
    public UserDAOImp(Connection connection) {this.cnn = connection;}

    @Override
    public boolean LoginUser(String username, String password) throws SQLException {
        String sql = "select * from user";
        Statement stmt = cnn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            if (rs.getString(1).equals(username) && rs.getString(2).equals(password)){
                System.out.println("登录成功");
                stmt.close();
                rs.close();
                return true;
            }
        }
        return false;
    }
}
