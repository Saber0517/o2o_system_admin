package com.oocl.jyhon.servlet;

import com.oocl.jyhon.entiy.FoodEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by WhiteSaber on 15/8/8.
 */
@WebServlet(name = "AuditServlet",urlPatterns={"/AuditServlet"})
public class AuditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<FoodEntity> foodEntityList = Collections.emptyList();
        request.getRequestDispatcher("main/Food.jsp").forward(request, response);
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
