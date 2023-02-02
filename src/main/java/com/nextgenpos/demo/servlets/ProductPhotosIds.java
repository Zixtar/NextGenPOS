package com.nextgenpos.demo.servlets;

import com.nextgenpos.demo.common.CategoryDto;
import com.nextgenpos.demo.common.ProductPhotoDto;
import com.nextgenpos.demo.ejb.CategoriesBean;
import com.nextgenpos.demo.ejb.ProductsBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductPhotosIds", value = "/ProductPhotosIds")
public class ProductPhotosIds extends HttpServlet {
    @Inject
    ProductsBean productsBean;
    @Inject
    CategoriesBean categoriesBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long photoId = Long.parseLong(request.getParameter("id"));
        ProductPhotoDto photo = productsBean.findProductPhotoById(photoId);
        List<CategoryDto> categories = categoriesBean.findAllCategories();
        request.setAttribute("categories", categories);
        if (photo != null) {
            response.setContentType(photo.getFileType());
             response.setContentLength(photo.getFileContent().length);
             response.getOutputStream().write(photo.getFileContent());
              } else {
               response.sendError(HttpServletResponse.SC_NOT_FOUND);
             }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
