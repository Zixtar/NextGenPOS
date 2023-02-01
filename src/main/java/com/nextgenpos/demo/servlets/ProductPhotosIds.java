package com.nextgenpos.demo.servlets;

import com.nextgenpos.demo.common.CategoryDto;
import com.nextgenpos.demo.common.ProductPhotoDto;
import com.nextgenpos.demo.ejb.CategoriesBean;
import com.nextgenpos.demo.ejb.ProductsBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;

@WebServlet(name = "ProductPhotosIds", value = "/ProductPhotosIds")
public class ProductPhotosIds extends HttpServlet {
    @Inject
    ProductsBean productsBean;
    @Inject
    CategoriesBean categoriesBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Long photoId;
        ProductPhotoDto photo;
        if(!id.isEmpty()) {
            photoId = Long.parseLong(id);
            photo = productsBean.findProductPhotoById(photoId);
        }else {photo=null;}

            List<CategoryDto> categories = categoriesBean.findAllCategories();
            request.setAttribute("categories", categories);

        if (photo != null) {
            response.setContentType(photo.getFileType());
             response.setContentLength(photo.getFileContent().length);
             response.getOutputStream().write(photo.getFileContent());
              } else {
                    URL url = getClass().getResource("/Placeholder.png");
                    File file = new File(url.getPath());
                    byte[] fileContent = Files.readAllBytes(file.toPath());

                    response.setContentType("image/png");
                    response.setContentLength(fileContent.length);
                    response.getOutputStream().write(fileContent);
               //response.sendError(HttpServletResponse.SC_NOT_FOUND);
             }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
