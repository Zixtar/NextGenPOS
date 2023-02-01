package com.nextgenpos.demo.servlets;

import com.nextgenpos.demo.common.CategoryDto;
import com.nextgenpos.demo.ejb.CategoriesBean;
import com.nextgenpos.demo.ejb.OfferBundleBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CreateOfferBundle", value = "/CreateOfferBundle")
public class CreateOfferBundle extends HttpServlet {
    @Inject
    OfferBundleBean offerBundleBean;
    @Inject
    CategoriesBean categoriesBean;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<CategoryDto> categories = categoriesBean.findAllCategories();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/WEB-INF/pages/createBundle.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("bundleName");
        offerBundleBean.createOfferBundle(name);
        response.sendRedirect(request.getContextPath() + "/HomePage");
    }
}
