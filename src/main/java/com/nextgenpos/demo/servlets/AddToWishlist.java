package com.nextgenpos.demo.servlets;

import com.nextgenpos.demo.common.CategoryDto;
import com.nextgenpos.demo.common.OfferItemDto;
import com.nextgenpos.demo.common.ProductDto;
import com.nextgenpos.demo.common.UserDto;
import com.nextgenpos.demo.ejb.CategoriesBean;
import com.nextgenpos.demo.ejb.OfferItemBean;
import com.nextgenpos.demo.ejb.ProductsBean;
import com.nextgenpos.demo.ejb.UsersBean;
import com.nextgenpos.demo.entities.OfferItem;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;
@DeclareRoles({"CASHIER","GENERAL_DIRECTOR","ADMIN"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"CASHIER","GENERAL_DIRECTOR","ADMIN"}))
@WebServlet(name = "AddToWishlist", value = "/AddToWishlist")
public class AddToWishlist extends HttpServlet {
    @Inject
    UsersBean usersBean;
    @Inject
    ProductsBean productsBean;
    @Inject
    CategoriesBean categoriesBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long productID=Long.parseLong(request.getParameter("id"));
        ProductDto productDto= productsBean.findById(productID);
        List<CategoryDto> categories = categoriesBean.findAllCategories();
        request.setAttribute("categories", categories);
        //TODO:userId
        UserDto user=usersBean.findUserByUsername(request.getRemoteUser());
        usersBean.addProductToUserWishList(productDto.getId(), user.getId());
        response.sendRedirect(request.getContextPath()+"/HomePage");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
