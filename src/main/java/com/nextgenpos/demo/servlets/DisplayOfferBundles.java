package com.nextgenpos.demo.servlets;

import com.nextgenpos.demo.common.OfferBundleDto;
import com.nextgenpos.demo.ejb.OfferBundleBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DisplayOfferBundles", value = "/DisplayOfferBundles")
public class DisplayOfferBundles extends HttpServlet {
    @Inject
    OfferBundleBean offerBundleBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<OfferBundleDto> offerBundleDtoList=offerBundleBean.findAllOfferBundles();
        request.setAttribute("offerBundles",offerBundleDtoList);
        request.getRequestDispatcher("WEB-INF/pages/viewOfferBundles.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
