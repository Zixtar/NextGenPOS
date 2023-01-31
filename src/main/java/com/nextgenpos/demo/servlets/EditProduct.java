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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EditProduct", value = "/EditProduct")
public class EditProduct extends HttpServlet {
    @Inject
    CategoriesBean categoriesBean;
    @Inject
    ProductsBean productsBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CategoryDto> categories = categoriesBean.findAllCategories();
        request.setAttribute("categories", categories);
        List<String> noCategoriesForProduct = new ArrayList<>();
        for(CategoryDto category:categories){
            noCategoriesForProduct.add(category.getName());
        }


        Long productId = Long.parseLong(request.getParameter("id"));
        ProductDto product = productsBean.findById(productId);
        noCategoriesForProduct.removeAll(product.getCategoriesName());
        List<CategoryDto> noCategoriesForProductDto = categoriesBean.findCategoriesByName(noCategoriesForProduct);
        List<CategoryDto> CategoriesForProductDto = categoriesBean.findCategoriesByName(product.getCategoriesName());

        request.setAttribute("product", product);
        request.setAttribute("productNoCategories", noCategoriesForProductDto);
        request.setAttribute("productCategories", CategoriesForProductDto);

        request.getRequestDispatcher("/WEB-INF/pages/editProduct.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        Integer stock = Integer.parseInt(request.getParameter("stock"));
        String description = request.getParameter("description");
        Integer price = Integer.parseInt(request.getParameter("price"));
        Long productId = Long.parseLong(request.getParameter("product_id"));
        String[] categoryIds = request.getParameterValues("category_ids");
        productsBean.updateProduct(name, stock, description,price, categoryIds, productId);
        List<CategoryDto> categories = categoriesBean.findAllCategories();
        request.setAttribute("categories", categories);
        response.sendRedirect(request.getContextPath() + "/Products");

    }
}
