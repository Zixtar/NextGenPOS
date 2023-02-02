package com.nextgenpos.demo.servlets;

import com.nextgenpos.demo.common.CategoryDto;
import com.nextgenpos.demo.ejb.CategoriesBean;
import com.nextgenpos.demo.ejb.SaleCreateBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.List;
@DeclareRoles({"CASHIER","GENERAL_DIRECTOR","ADMIN"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"CASHIER","GENERAL_DIRECTOR","ADMIN"}))
@WebServlet(name = "EndSale", value = "/EndSale")
public class EndSale extends HttpServlet {
    @Inject
    CategoriesBean categoriesBean;

    private static final String SALE_BEAN_SESION_KEY
            = "saleCreateBean";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CategoryDto> categories = categoriesBean.findAllCategories();
        request.setAttribute("categories", categories);
        SaleCreateBean saleCreateBean = (SaleCreateBean) request.getSession().getAttribute(SALE_BEAN_SESION_KEY);

        saleCreateBean.CancelSale(request.getRemoteUser());

        response.sendRedirect(request.getContextPath() + "/CreateSale");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SaleCreateBean saleCreateBean = (SaleCreateBean) request.getSession().getAttribute(SALE_BEAN_SESION_KEY);

        if(saleCreateBean.getCurrentSaleProducts().size()>0)
            saleCreateBean.FinalizeSale();

        response.sendRedirect(request.getContextPath() + "/CreateSale");
    }
}
