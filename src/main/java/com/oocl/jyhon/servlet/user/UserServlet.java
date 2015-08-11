package com.oocl.jyhon.servlet.user;

import com.oocl.jyhon.entiy.UserEntity;
import com.oocl.jyhon.service.UserEntityService;
import com.oocl.jyhon.serviceimpl.UserEntityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by WhiteSaber on 15/8/10.
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role = request.getParameter("role");
        UserEntityService userEntityService = new UserEntityServiceImpl();
        List<UserEntity> userEntityList = userEntityService.getUserByRole(role);
        request.setAttribute("userEntityList", userEntityList);
        request.getRequestDispatcher("main/user.jsp").forward(request, response);
        System.out.printf(role);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
