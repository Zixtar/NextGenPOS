package com.nextgenpos.demo.servlets;

import com.nextgenpos.demo.common.CategoryDto;
import com.nextgenpos.demo.ejb.CategoriesBean;
import com.nextgenpos.demo.ejb.ProductsBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddProduct", value = "/AddProduct")
public class AddProduct extends HttpServlet {

    @Inject
    CategoriesBean categoriesBean;
    @Inject
    ProductsBean productsBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     List<CategoryDto> categories = categoriesBean.findAllCategories();
     request.setAttribute("categories", categories);
      request.getRequestDispatcher("/WEB-INF/pages/addProduct.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          String name = request.getParameter("name");
          Integer stock = Integer.parseInt(request.getParameter("stock"));
          String description = request.getParameter("description");
          Integer price = Integer.parseInt(request.getParameter("price"));
          Long categoryId = Long.parseLong(request.getParameter("category_id"));
          productsBean.createProduct(name, stock, description,price, categoryId);
          response.sendRedirect(request.getContextPath() + "/Products");

    }
}