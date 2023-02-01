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

@WebServlet(name = "ProductFirstPhoto", value = "/ProductFirstPhoto")
public class ProductFirstPhoto extends HttpServlet {
    @Inject
    ProductsBean productsBean;
    @Inject
    CategoriesBean categoriesBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long productId=Long.parseLong(request.getParameter("id"));
        List<CategoryDto> categories = categoriesBean.findAllCategories();
        request.setAttribute("categories", categories);
        ProductPhotoDto productPhoto=productsBean.findFirstProductPhotoById(productId);
        if(productPhoto!=null)
        {
            response.setContentType(productPhoto.getFileType());
            response.setContentLength(productPhoto.getFileContent().length);
            response.getOutputStream().write(productPhoto.getFileContent());
        } else
        {
            URL url = getClass().getResource("/Placeholder.png");
            File file = new File(url.getPath());
            byte[] fileContent = Files.readAllBytes(file.toPath());

            response.setContentType("image/png");
            response.setContentLength(fileContent.length);
            response.getOutputStream().write(fileContent);

//            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
