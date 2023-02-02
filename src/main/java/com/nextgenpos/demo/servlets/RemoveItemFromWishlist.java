package com.nextgenpos.demo.servlets;

import com.nextgenpos.demo.common.CategoryDto;
import com.nextgenpos.demo.common.ProductDto;
import com.nextgenpos.demo.common.UserDto;
import com.nextgenpos.demo.ejb.CategoriesBean;
import com.nextgenpos.demo.ejb.ProductsBean;
import com.nextgenpos.demo.ejb.UsersBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;
@DeclareRoles({"CASHIER","GENERAL_DIRECTOR","ADMIN"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"CASHIER","GENERAL_DIRECTOR","ADMIN"}))
@WebServlet(name = "RemoveItemFromWishlist", value = "/RemoveItemFromWishlist")
public class RemoveItemFromWishlist extends HttpServlet {
    @Inject
    UsersBean usersBean;
    @Inject
    ProductsBean productsBean;
    @Inject
    CategoriesBean categoriesBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO:userId
        UserDto user=usersBean.findUserByUsername(request.getRemoteUser());
        List<CategoryDto> categories = categoriesBean.findAllCategories();
        request.setAttribute("categories", categories);
        Long productId=Long.parseLong(request.getParameter("id"));
        usersBean.deleteItemFromWishlist(productId,user.getId());
        List<ProductDto> userWishlist=productsBean.copyProductsToDto(user.getUserWishlist());
        response.sendRedirect(request.getContextPath()+"/HomePage");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
