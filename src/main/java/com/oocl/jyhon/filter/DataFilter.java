package com.oocl.jyhon.filter;

import com.oocl.jyhon.dao.EntityDao;
import com.oocl.jyhon.daoimple.StatusEntityDaoImple;
import com.oocl.jyhon.entiy.FoodTypeEntity;
import com.oocl.jyhon.entiy.StatusEntity;
import com.oocl.jyhon.service.FoodTypeEntityService;
import com.oocl.jyhon.serviceimpl.FoodTypeEntityServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 为用户准备必要的数据
 * Created by WhiteSaber on 15/8/11.
 */
@WebFilter(filterName = "DataFilter", urlPatterns = "/*")
public class DataFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;

        if (null == httpServletRequest.getAttribute("foodTypeEntityList") && null == httpServletRequest.getSession().getAttribute("foodTypeEntityList")) {
            FoodTypeEntityService foodTypeEntityService = new FoodTypeEntityServiceImpl();
            List<FoodTypeEntity> foodTypeEntityList = foodTypeEntityService.findAll();
            httpServletRequest.getSession().setAttribute("foodTypeEntityList", foodTypeEntityList);
        }


        //status
        if (null == httpServletRequest.getAttribute("statusEntityList") && null == httpServletRequest.getSession().getAttribute("statusEntityList")) {
            EntityDao statusEntityDaoImple = new StatusEntityDaoImple();
            List<StatusEntity> statusEntityList = statusEntityDaoImple.findAll();
            httpServletRequest.getSession().setAttribute("statusEntityList", statusEntityList);

        }
        chain.doFilter(req, resp);

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
