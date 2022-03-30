package com.servlet;

import DAO.DBUtil;
import DAO.DataBaseDAOImp;
import Info.User;
import Info.UserInfo;
import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import javafx.print.Printer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;

import static java.sql.DriverManager.getConnection;

@WebServlet(name = "Query", value = "/Query")
public class Qyery extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserInfo> userInfos = new ArrayList<UserInfo>();
        try {
            Connection cnn = DBUtil.getConnection();
            DataBaseDAOImp dataBaseDAOImp = new DataBaseDAOImp(cnn);
            userInfos = dataBaseDAOImp.FindInfoAll();
            if (!userInfos.isEmpty()){
                PrintWriter pp = response.getWriter();
                response.setCharacterEncoding("utf-8");
                pp.print(new Gson().toJson(userInfos));
                pp.flush();
                pp.close();
            }else {
                response.sendError(500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserInfo> userInfos = new ArrayList<>();
        UserInfo Finduser = new UserInfo();
        Finduser.setIdentify(request.getParameter("identify"));
        Finduser.setName(request.getParameter("name"));
        Finduser.setSex(request.getParameter("sex"));
        Finduser.setProvince(request.getParameter("province"));
        Finduser.setCity(request.getParameter("city"));
        Finduser.setSusp(request.getParameter("susp"));
        Finduser.setDiag(request.getParameter("diag"));
        Finduser.setDate(request.getParameter("date"));
        try {
            Connection cnn = DBUtil.getConnection();
            DataBaseDAOImp dataBaseDAOImp = new DataBaseDAOImp(cnn);
            userInfos = dataBaseDAOImp.FindInfoBY(Finduser);
            if (!userInfos.isEmpty()){
                PrintWriter oo = response.getWriter();
                response.setCharacterEncoding("utf-8");
                oo.print(new Gson().toJson(userInfos));
                oo.flush();
                oo.close();
            }else{
                response.sendError(500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
