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

@MultipartConfig
@WebServlet(name = "AddProductPhoto", value = "/AddProductPhoto")
public class AddProductPhoto extends HttpServlet {
    @Inject
    ProductsBean productsBean;
    @Inject
    CategoriesBean categoriesBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long productId = Long.parseLong(request.getParameter("id"));
        ProductDto product= productsBean.findById(productId);
        request.setAttribute("product", product);
        List<CategoryDto> categories = categoriesBean.findAllCategories();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/WEB-INF/pages/addProductPhoto.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long productId = Long.parseLong(request.getParameter("product_id"));
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        String fileType= filePart.getContentType();
        long fileSize = filePart.getSize();
        byte[] fileContent = new byte[(int) fileSize];
        filePart.getInputStream().read(fileContent);
        List<CategoryDto> categories = categoriesBean.findAllCategories();
        request.setAttribute("categories", categories);

        productsBean.addPhotosToProduct(productId, fileName, fileType, fileContent);
        response.sendRedirect(request.getContextPath() + "/Products");
    }
}
