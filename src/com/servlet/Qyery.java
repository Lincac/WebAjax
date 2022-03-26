package com.servlet;

import Info.UserInfo;
import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;

import static java.sql.DriverManager.getConnection;

@WebServlet(name = "Query", value = "/Query")
public class Qyery extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection cnn;
        int count=0;
        ResultSet rs;
        Map<String, String> map = new HashMap<>();
        List<UserInfo> userInfos = new ArrayList<UserInfo>();
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
            if (!val.equals("")) {
                count++;
            }
        }
        System.out.println(identify+name+sex+province+city+susp+diag+date);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cnn = getConnection("jdbc:mysql://localhost:3306/epidemic?serverTimezone=GMT%2B8",
                    "root",
                    "123456");
            String sql = "SELECT * FROM info";
            if (count != 0){
                sql+=" WHERE ";
            }
            for (String key : map.keySet()) {
                String val = map.get(key);
                if (!val.equals("")&& count!=0) {
                    sql+=key+"="+"'"+val +"'";
                    count--;
                    if (count!=0){
                        sql+= " AND ";
                    }
                }
            }
            Statement stmt = cnn.createStatement();
            System.out.println(sql);
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                UserInfo uu = new UserInfo();
                uu.setIdentify(rs.getString(1));
                uu.setName(rs.getString(2));
                uu.setSex(rs.getString(3));
                uu.setProvince(rs.getString(4));
                uu.setCity(rs.getString(5));
                uu.setSusp(rs.getString(6));
                uu.setDiag(rs.getString(7));
                uu.setDate(rs.getString(8));
                userInfos.add(uu);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        PrintWriter oo = response.getWriter();
        response.setCharacterEncoding("utf-8");
        oo.print(new Gson().toJson(userInfos));
        oo.flush();
        oo.close();
    }
}
