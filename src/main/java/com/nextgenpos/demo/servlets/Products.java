package com.nextgenpos.demo.servlets;

import com.nextgenpos.demo.common.CategoryDto;
import com.nextgenpos.demo.common.ProductDto;
import com.nextgenpos.demo.ejb.CategoriesBean;
import com.nextgenpos.demo.ejb.ProductsBean;
import com.nextgenpos.demo.entities.Product;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
@DeclareRoles({"GENERAL_DIRECTOR"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"GENERAL_DIRECTOR"}))
@WebServlet(name = "Products", value = "/Products")
public class Products extends HttpServlet {

    @Inject
    ProductsBean productsBean;
    @Inject
    CategoriesBean categoriesBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      List<ProductDto> products = productsBean.findAllProducts();
      request.setAttribute("products", products);
      List<CategoryDto> categories = categoriesBean.findAllCategories();
        request.setAttribute("categories", categories);
        if(!categories.isEmpty()){
            List<List<ProductDto>> productsForCategories = productsBean.getProductsOfCategories(categories);
            request.setAttribute("productsForCategories", productsForCategories);
        }
      request.getRequestDispatcher("/WEB-INF/pages/products.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
