package com.nextgenpos.demo.servlets;

import com.nextgenpos.demo.ejb.SaleCreateBean;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;

@WebServlet(name = "EndSale", value = "/EndSale")
public class EndSale extends HttpServlet {

    private static final String SALE_BEAN_SESION_KEY
            = "saleCreateBean";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
