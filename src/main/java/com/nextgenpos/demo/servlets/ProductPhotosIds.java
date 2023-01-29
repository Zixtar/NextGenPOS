package com.nextgenpos.demo.servlets;

import com.nextgenpos.demo.common.ProductPhotoDto;
import com.nextgenpos.demo.ejb.ProductsBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ProductPhotosIds", value = "/ProductPhotosIds")
public class ProductPhotosIds extends HttpServlet {
    @Inject
    ProductsBean productsBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long photoId = Long.parseLong(request.getParameter("id"));
        ProductPhotoDto photo = productsBean.findProductPhotoById(photoId);
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
