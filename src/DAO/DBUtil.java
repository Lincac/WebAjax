package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// 工具包类：提供数据库链接接口
public class DBUtil {
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/epidemic?serverTimezone=GMT%2B8";
        Connection cnn = DriverManager.getConnection(url, "root", "123456");
        return cnn;
    };
}
