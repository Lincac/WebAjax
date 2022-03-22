package com.servlet;
import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/Servlet")
public class Servlet extends HttpServlet {
    public String name;
    public String email;
    public String text;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         this.name = request.getParameter("UserName");
         this.email = request.getParameter("UserEmail");
         this.text = request.getParameter("UserText");
         System.out.println("上传的数据：" + "name:"+name + "email:"+email+"text:"+text);
        List<String> toto = new ArrayList<String>();
        toto.add(name);
        toto.add(email);
        toto.add(text);
        PrintWriter outt = response.getWriter();
        Gson gson = new Gson();
        response.setCharacterEncoding("utf-8");
        outt.print(gson.toJson(toto));
        outt.flush();
        outt.close();
        /*        try{
         *//*            //mysql5.5以后可以不写，mysql8.0得加上cj
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","123456");
            PreparedStatement psmt = conn.prepareStatement("select *from users where username=?");*//*
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/


    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
