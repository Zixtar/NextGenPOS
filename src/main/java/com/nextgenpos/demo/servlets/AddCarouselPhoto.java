package com.nextgenpos.demo.servlets;

import com.nextgenpos.demo.common.CategoryDto;
import com.nextgenpos.demo.common.OfferBundleDto;
import com.nextgenpos.demo.ejb.CategoriesBean;
import com.nextgenpos.demo.ejb.OfferBundleBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;
@DeclareRoles({"GENERAL_DIRECTOR","ADMIN"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"GENERAL_DIRECTOR","ADMIN"}))
@WebServlet(name = "AddCarouselPhoto", value = "/AddCarouselPhoto")
@MultipartConfig
public class AddCarouselPhoto extends HttpServlet {
    @Inject
    OfferBundleBean offerBundleBean;
    @Inject
    CategoriesBean categoriesBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long offerBundleId=Long.parseLong(request.getParameter("id"));
        OfferBundleDto offerBundleDto=offerBundleBean.findOfferBundleById(offerBundleId);
        List<CategoryDto> categories = categoriesBean.findAllCategories();
        request.setAttribute("categories", categories);
        request.setAttribute("offerBundle",offerBundleDto);
        request.getRequestDispatcher("/WEB-INF/pages/addCarouselPhoto.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long offerBundleId=Long.parseLong(request.getParameter("offerBundle_id"));
        Part filePart=request.getPart("file");
        String fileType=filePart.getContentType();
        String fileName=filePart.getSubmittedFileName();
        long fileSize=filePart.getSize();
        byte[] fileContent=new byte[(int) fileSize];
        filePart.getInputStream().read(fileContent);

        offerBundleBean.addPhotoToOfferBundle(offerBundleId,fileName,fileType,fileContent);
        response.sendRedirect(request.getContextPath()+"/DisplayOfferBundles");
    }
}
