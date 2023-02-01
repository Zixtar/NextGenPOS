package com.nextgenpos.demo.servlets;

import com.nextgenpos.demo.common.CategoryDto;
import com.nextgenpos.demo.common.ProductDto;
import com.nextgenpos.demo.ejb.CategoriesBean;
import com.nextgenpos.demo.ejb.ProductsBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductsByCategory", value = "/ProductsByCategory")
public class ProductsByCategory extends HttpServlet {
    @Inject
    CategoriesBean categoriesBean;
    @Inject
    ProductsBean productsBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CategoryDto> categories = categoriesBean.findAllCategories();
        request.setAttribute("categories", categories);
        Long categoryId = Long.parseLong(request.getParameter("id"));
        if(!categories.isEmpty()){
            List<ProductDto> productsOfCategory = productsBean.getProductsOfCategories(categoryId);
            request.setAttribute("products", productsOfCategory);
        }
        Boolean inAllProducts = Boolean.FALSE;
        request.setAttribute("inAllProducts", inAllProducts);
        request.getRequestDispatcher("/WEB-INF/pages/products.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
