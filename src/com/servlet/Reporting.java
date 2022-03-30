package com.servlet;

import DAO.DBUtil;
import DAO.DataBaseDAOImp;
import Info.UserInfo;
import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import javafx.util.converter.ShortStringConverter;

import java.io.PrintWriter;
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
        UserInfo userInfo = new UserInfo();
        userInfo.setIdentify(request.getParameter("identify"));
        userInfo.setName(request.getParameter("name"));
        userInfo.setCollege(request.getParameter("college"));
        userInfo.setSex(request.getParameter("sex"));
        userInfo.setProvince(request.getParameter("province"));
        userInfo.setCity(request.getParameter("city"));
        userInfo.setTemp(request.getParameter("temp"));
        userInfo.setIs_back(request.getParameter("is_back"));
        userInfo.setSusp(request.getParameter("susp"));
        userInfo.setDiag(request.getParameter("diag"));
        try {
            Connection cnn = DBUtil.getConnection();
            DataBaseDAOImp dataBaseDAOImp = new DataBaseDAOImp(cnn);
            if (dataBaseDAOImp.addInfo(userInfo)){
                PrintWriter oo = response.getWriter();
                response.setCharacterEncoding("utf-8");
                oo.print(new Gson().toJson("上传成功！"));
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
