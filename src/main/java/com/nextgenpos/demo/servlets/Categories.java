package com.nextgenpos.demo.servlets;

import com.nextgenpos.demo.ejb.CategoriesBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Categories", value = "/Categories")
public class Categories extends HttpServlet {
    @Inject
    CategoriesBean categoriesBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] categoryIdsAsString = request.getParameterValues("category_ids");
        if(categoryIdsAsString != null){
            List<Long> carIds = new ArrayList<>();
            for(String categoryIdAsString : categoryIdsAsString){
                carIds.add(Long.parseLong(categoryIdAsString));
            }
            categoriesBean.deleteCategoriesByIds(carIds);
        }
        response.sendRedirect(request.getContextPath() + "/HomePage");
    }
    }

