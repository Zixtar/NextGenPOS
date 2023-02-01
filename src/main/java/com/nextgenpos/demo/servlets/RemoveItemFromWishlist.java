package com.nextgenpos.demo.servlets;

import com.nextgenpos.demo.common.ProductDto;
import com.nextgenpos.demo.common.UserDto;
import com.nextgenpos.demo.ejb.ProductsBean;
import com.nextgenpos.demo.ejb.UsersBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RemoveItemFromWishlist", value = "/RemoveItemFromWishlist")
public class RemoveItemFromWishlist extends HttpServlet {
    @Inject
    UsersBean usersBean;
    @Inject
    ProductsBean productsBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO:userId
        UserDto user=usersBean.findUserByUsername(request.getRemoteUser());
        Long productId=Long.parseLong(request.getParameter("id"));
        usersBean.deleteItemFromWishlist(productId,user.getId());
        List<ProductDto> userWishlist=productsBean.copyProductsToDto(user.getUserWishlist());
        response.sendRedirect(request.getContextPath()+"/HomePage");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
