package com.oocl.jyhon.servlet.audit;

import com.oocl.jyhon.dao.StatusEntityDao;
import com.oocl.jyhon.daoimple.StatusEntityDaoImple;
import com.oocl.jyhon.entiy.FoodEntity;
import com.oocl.jyhon.entiy.StatusEntity;
import com.oocl.jyhon.entiy.UserEntity;
import com.oocl.jyhon.service.AuditService;
import com.oocl.jyhon.serviceimpl.AuditSerivceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by WhiteSaber on 15/8/9.
 */
@WebServlet(name = "AuditServlet", urlPatterns = {"/AuditServlet"})
public class AuditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AuditService auditService = new AuditSerivceImpl();
        Map<String, Object> requestMap = auditService.getRequest(this.getServletContext());
        if (requestMap.isEmpty()) {
            request.getRequestDispatcher(".").forward(request, response);
        } else if (requestMap.containsKey("requestFoodEntity")) {
            request.setAttribute("currentAuditFoodEntity", requestMap.get("requestFoodEntity"));
            request.getRequestDispatcher("main/auditFoodApply.jsp").forward(request, response);
        } else if (requestMap.containsKey("requestUserEntity")) {
            UserEntity userEntity = (UserEntity) requestMap.get("requestUserEntity");
            System.out.printf("License:"+userEntity.getLicense());
            request.setAttribute("currentAuditUserEntity", requestMap.get("requestUserEntity"));
            request.getRequestDispatcher("main/auditUserApply.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
