package com.nextgenpos.demo.servlets;

import com.nextgenpos.demo.common.OfferItemDto;
import com.nextgenpos.demo.ejb.OfferItemBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomePage", value = "/HomePage")
public class HomePage extends HttpServlet {

    @Inject
    OfferItemBean offerItemBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<OfferItemDto> offerItems=offerItemBean.findAllOfferItems();
        request.setAttribute("offerItems",offerItems);
        request.getRequestDispatcher("/homePage.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
