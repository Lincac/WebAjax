package com.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import javafx.util.converter.ShortStringConverter;
import java.util.Date;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "Reporting", value = "/Reporting")
public class Reporting extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String identify = request.getParameter("identify");
        String name = request.getParameter("name");
        String college = request.getParameter("college");
        String sex = request.getParameter("sex");
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String temp = request.getParameter("temp");
        String is_back = request.getParameter("is_back");
        String susp = request.getParameter("susp");
        String diag = request.getParameter("diag");

        Connection cnn;
        ResultSet re;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/epidemic?serverTimezone=GMT%2B8",
                    "root",
                    "123456");
            String sql = "INSERT INTO info values(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = cnn.prepareStatement(sql);
            stmt.setString(1,identify);
            stmt.setString(2,name);
            stmt.setString(3,college);
            stmt.setString(4,sex);
            stmt.setString(5,province);
            stmt.setString(6,city);
            stmt.setString(7,temp);
            stmt.setString(8,is_back);
            stmt.setString(9,susp);
            stmt.setString(10,diag);
            Date date = new Date();
            stmt.setDate(11, new java.sql.Date(date.getTime()));
            int item = stmt.executeUpdate();
            if (item == 1){
                System.out.println("上传成功！");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
