package com.nextgenpos.demo.servlets;

import com.nextgenpos.demo.common.CategoryDto;
import com.nextgenpos.demo.common.OfferBundleDto;
import com.nextgenpos.demo.common.OfferItemDto;
import com.nextgenpos.demo.common.ProductDto;
import com.nextgenpos.demo.ejb.CategoriesBean;
import com.nextgenpos.demo.ejb.OfferBundleBean;
import com.nextgenpos.demo.ejb.OfferItemBean;
import com.nextgenpos.demo.ejb.ProductsBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@DeclareRoles({"GENERAL_DIRECTOR","ADMIN"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"GENERAL_DIRECTOR","ADMIN"}))
@WebServlet(name = "AddOfferItem", value = "/AddOfferItem")
public class AddOfferItem extends HttpServlet {

    @Inject
    ProductsBean productsBean;
    @Inject
    OfferBundleBean offerBundleBean;
    @Inject
    CategoriesBean categoriesBean;

    @Inject
    OfferItemBean offerItemBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ProductDto> products = productsBean.findAllProducts();
        request.setAttribute("products",products);
        List<CategoryDto> categories = categoriesBean.findAllCategories();
        request.setAttribute("categories", categories);

        Long bundleId=Long.parseLong(request.getParameter("id"));
        request.setAttribute("bundleId", bundleId);

        request.getRequestDispatcher("/WEB-INF/pages/addOfferItem.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long productId = Long.parseLong(request.getParameter("product_id"));
        Long bundleId = Long.parseLong(request.getParameter("bundle_id"));
        Float newPrice = Float.parseFloat(request.getParameter("new_price"));
        String beginningDate = request.getParameter("beginning_date");
        String endDate = request.getParameter("end_date");

        try {
            offerItemBean.createOfferItem(productId, bundleId, newPrice, beginningDate,endDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect(request.getContextPath() + "/DisplayOfferBundles");
    }
}
