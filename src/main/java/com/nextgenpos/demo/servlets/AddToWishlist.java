package com.nextgenpos.demo.servlets;

import com.nextgenpos.demo.common.OfferItemDto;
import com.nextgenpos.demo.common.UserDto;
import com.nextgenpos.demo.ejb.OfferItemBean;
import com.nextgenpos.demo.ejb.UsersBean;
import com.nextgenpos.demo.entities.OfferItem;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AddToWishlist", value = "/AddToWishlist")
public class AddToWishlist extends HttpServlet {
    @Inject
    UsersBean usersBean;
    @Inject
    OfferItemBean offerItemBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long offerItemId=Long.parseLong(request.getParameter("id"));
        OfferItemDto of= offerItemBean.getOfferItemById(offerItemId);
        //TODO:userId
        UserDto user=usersBean.findUserByUsername(request.getRemoteUser());
        usersBean.addProductToUserWishList(of.getProduct().getId(), user.getId());
        response.sendRedirect(request.getContextPath()+"/HomePage");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
