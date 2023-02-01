package com.nextgenpos.demo.servlets;

import com.nextgenpos.demo.common.CategoryDto;
import com.nextgenpos.demo.common.ProductDto;
import com.nextgenpos.demo.ejb.CategoriesBean;
import com.nextgenpos.demo.ejb.ProductsBean;
import com.nextgenpos.demo.entities.Product;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@WebServlet(name = "Products", value = "/Products")
public class Products extends HttpServlet {

    @Inject
    ProductsBean productsBean;
    @Inject
    CategoriesBean categoriesBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      List<ProductDto> products = productsBean.findAllProducts();
        for(ProductDto product: products){
            product.setPhotoIds(productsBean.findPhotosIdByProductId(product.getId()));
        }
      request.setAttribute("products", products);
        List<CategoryDto> categories = categoriesBean.findAllCategories();
        request.setAttribute("categories", categories);
        request.setAttribute("prodNr",products.size());
        Boolean inAllProducts = Boolean.TRUE;
        request.setAttribute("inAllProducts", inAllProducts);
        request.getRequestDispatcher("/WEB-INF/pages/products.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] productIdsAsString = request.getParameterValues("product_ids");
        if(productIdsAsString != null){
            List<Long> productIds = new ArrayList<>();
            for(String productIdAsString : productIdsAsString){
                productIds.add(Long.parseLong(productIdAsString));
            }
            productsBean.deleteProductsByIds(productIds);
        }
        response.sendRedirect(request.getContextPath() + "/Products");
    }
}
