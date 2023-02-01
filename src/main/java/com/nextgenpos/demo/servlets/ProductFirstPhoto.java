package com.nextgenpos.demo.servlets;

import com.nextgenpos.demo.common.ProductPhotoDto;
import com.nextgenpos.demo.ejb.ProductsBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ProductFirstPhoto", value = "/ProductFirstPhoto")
public class ProductFirstPhoto extends HttpServlet {
    @Inject
    ProductsBean productsBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long productId=Long.parseLong(request.getParameter("id"));
        ProductPhotoDto productPhoto=productsBean.findFirstProductPhotoById(productId);
        if(productPhoto!=null)
        {
            response.setContentType(productPhoto.getFileType());
            response.setContentLength(productPhoto.getFileContent().length);
            response.getOutputStream().write(productPhoto.getFileContent());
        } else
        {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
