package com.servlet;

import Info.UserInfo;
import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "JDBC", value = "/JDBC")
public class JDBC extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserInfo> userInfos = new ArrayList<UserInfo>();
        ResultSet rs;
        Connection conn;
        String name =request.getParameter("username");
        String pass = request.getParameter("userpassword");
        int age = Integer.parseInt(request.getParameter("age"));
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");//加载驱动
            // 链接驱动
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gg?serverTimezone=GMT%2B8", "root", "123456");
            String sql = "INSERT INTO userinfo(username,userpassword,age) VALUES(?,?,?)";//利用索引写值
            PreparedStatement stmt =  conn.prepareStatement(sql);//通过数据库的连接操作数据库，实现增删改查
            //preparedStatement 带参数的sal语句
            stmt.setString(1,name);
            stmt.setString(2,pass);
            stmt.setInt(3,age);
            int item = stmt.executeUpdate(); //执行
            if(item == 1){
                System.out.println("嘿嘿嘿，你成功了");
                sql = "select * from userinfo";
                stmt = conn.prepareStatement(sql);
                rs = stmt.executeQuery();
                while(rs.next()){
                    UserInfo pp = new UserInfo();
                    pp.setName(rs.getString(1)); // 获得列索引
/*                    pp.setPass(rs.getString(2));
                    pp.setAge(rs.getInt(3));*/
                    userInfos.add(pp);
                }
            }
        }catch (ClassNotFoundException | SQLException e){
            System.out.println(e.toString());
        }
        PrintWriter oo = response.getWriter();
        response.setCharacterEncoding("utf-8");
        oo.println(new Gson().toJson(userInfos));
        oo.flush();
        oo.close();
    }
}
