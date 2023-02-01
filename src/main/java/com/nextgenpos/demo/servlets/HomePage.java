package com.nextgenpos.demo.servlets;

import com.nextgenpos.demo.common.*;
import com.nextgenpos.demo.ejb.*;
import com.nextgenpos.demo.entities.OfferItem;
import com.nextgenpos.demo.entities.Product;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jdk.internal.net.http.common.Pair;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "HomePage", value = "/HomePage")
public class HomePage extends HttpServlet {

    @Inject
    OfferItemBean offerItemBean;
    @Inject
    CategoriesBean categoriesBean;

    @Inject
    UsersBean usersBean;

    @Inject
    ProductsBean productsBean;

    @Inject
    OfferBundleBean offerBundleBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CategoryDto> categories = categoriesBean.findAllCategories();
        request.setAttribute("categories", categories);
//        List<OfferItemDto> offerItems = offerItemBean.findAllOfferItems();
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        List<OfferItemDto> offerItems= null;
        try {
            offerItems = offerItemBean.findAllOferItemsWithinDateInterval(formatter.parse("2023-03-03"), formatter.parse("2023-03-29"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("offerItems", offerItems);

        if(!offerItems.isEmpty()) {   //request.getRequestDispatcher("/homePage.jsp").forward(request, response);
            Long bundleId = offerItemBean.getBundleId(offerItems.get(0).getId());
            request.setAttribute("bundleId", bundleId);
        }
        //TODO: userId
        UserDto user=usersBean.findUserByUsername(request.getRemoteUser());
        if(user==null)
            request.getRequestDispatcher("/homePage.jsp").forward(request, response);
        List<ProductDto> userWishlist = productsBean.copyProductsToDto(user.getUserWishlist());

        for (ProductDto prodDto: userWishlist) {

            List<OfferItem> list = prodDto.getOfferItems();

            if(list.size()!=0)
            {
                prodDto.setPrice(list.get(0).getNewPrice().intValue());
            }
        }

        request.setAttribute("userWishlist", userWishlist);

        int userWishlistSize = userWishlist.size();
        request.setAttribute("userWishlistSize", userWishlistSize);

        request.getRequestDispatcher("/homePage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
