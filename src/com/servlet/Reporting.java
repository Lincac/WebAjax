package com.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import javafx.util.converter.ShortStringConverter;

import java.io.IOException;

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
        System.out.println(identify+name+college);

    }
}
