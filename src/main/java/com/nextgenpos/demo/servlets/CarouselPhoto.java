package com.nextgenpos.demo.servlets;

import com.nextgenpos.demo.common.CarouselPhotoDto;
import com.nextgenpos.demo.common.CategoryDto;
import com.nextgenpos.demo.common.OfferBundleDto;
import com.nextgenpos.demo.ejb.CategoriesBean;
import com.nextgenpos.demo.ejb.OfferBundleBean;
import com.nextgenpos.demo.entities.OfferBundle;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CarouselPhoto", value = "/CarouselPhoto")
public class CarouselPhoto extends HttpServlet {
    @Inject
    OfferBundleBean offerBundleBean;
    @Inject
    CategoriesBean categoriesBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long bundleId=Long.parseLong(request.getParameter("id"));
        List<CategoryDto> categories = categoriesBean.findAllCategories();
        request.setAttribute("categories", categories);
        CarouselPhotoDto photo=offerBundleBean.getCarouselPhotoByOfferBundleId(bundleId);
        if(photo!=null)
        {
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
