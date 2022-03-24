package com.servlet;

import Info.UserInfo;
import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Iterator;
import java.util.Map;

import static java.sql.DriverManager.getConnection;

@WebServlet(name = "Query", value = "/Query")
public class Qyery extends HttpServlet {
    private int count=0;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection cnn;
        ResultSet rs;
        Map<String, String> map = null;
        UserInfo userInfo = new UserInfo();
        String identify = request.getParameter("identify");
        map.put("identify",identify);
        String name = request.getParameter("name");
        map.put("name",name);
        String sex = request.getParameter("sex");
        map.put("sex",sex);
        String province = request.getParameter("province");
        map.put("province",province);
        String city = request.getParameter("city");
        map.put("city",city);
        String susp = request.getParameter("susp");
        map.put("susp",susp);
        String diag = request.getParameter("diag");
        map.put("diag",diag);
        String date = request.getParameter("date");
        map.put("date",date);
        for (String key : map.keySet()) {
            String val = map.get(key);
            if (val.equals("")) {
                count+=1;
            }
        }
        System.out.println(identify+name+sex+province+city+susp+diag+date);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cnn = getConnection("jdbc:mysql://localhost:3306/epidemic?serverTimezone=GMT%2B8",
                    "root",
                    "123456");
            String sql = "SELECT * FROM info WHERE (?,?)";
            PreparedStatement stmt = cnn.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (count == 0){

            }else if (count == 1){

            }else if (count == 2){

            }else if (count == 3){

            }else if (count == 4){

            }else if (count == 5){

            }else if (count == 6){

            }else if (count == 7){

            }else{

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        PrintWriter oo = response.getWriter();
        response.setCharacterEncoding("utf-8");
        oo.print(new Gson().toJson("查询成功！"));
        oo.flush();
        oo.close();
    }
}
