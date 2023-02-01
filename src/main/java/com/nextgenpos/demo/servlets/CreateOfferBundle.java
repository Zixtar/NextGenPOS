package com.nextgenpos.demo.servlets;

import com.nextgenpos.demo.ejb.OfferBundleBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "CreateOfferBundle", value = "/CreateOfferBundle")
public class CreateOfferBundle extends HttpServlet {
    @Inject
    OfferBundleBean offerBundleBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/pages/createBundle.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("bundleName");
        offerBundleBean.createOfferBundle(name);
        response.sendRedirect(request.getContextPath() + "/HomePage");
    }
}
