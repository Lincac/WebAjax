package com.servlet;

import com.google.gson.Gson;
import com.sun.org.apache.bcel.internal.generic.Select;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.xml.transform.Result;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "epidemic", value = "/epidemic")
public class epidemic extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection cnn;
        ResultSet rs;
        boolean is_ok = false;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userfrom = request.getParameter("userfrom");
        System.out.println("前端数据："+username+","+password+","+userfrom);

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/epidemic?serverTimezone=GMT%2B8",
                    "root",
                    "123456");
            String sql = "select * from user";
            Statement stmt = cnn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                if (rs.getString(1).equals(username) && rs.getString(2).equals(password)){
                    is_ok = true;
                    System.out.println("登录成功");
                    PrintWriter oo = response.getWriter();
                    response.setCharacterEncoding("utf-8");
                    oo.print(new Gson().toJson("登录成功"));
                    // 当后端返回的数据不是json类型，前端的就不会接受返回的数据
                    oo.flush();
                    oo.close();
                    break;
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        if (!is_ok){
            PrintWriter oo = response.getWriter();
            oo.print("错误");
            // 当后端返回的数据不是json类型，前端的就不会接受返回的数据
            oo.flush();
            oo.close();
        }
    }
}
