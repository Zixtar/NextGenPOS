package com.nextgenpos.demo.servlets;

import com.nextgenpos.demo.common.CategoryDto;
import com.nextgenpos.demo.common.ErrorReportDto;
import com.nextgenpos.demo.common.UserDto;
import com.nextgenpos.demo.ejb.CategoriesBean;
import com.nextgenpos.demo.ejb.ErrorReportBean;
import com.nextgenpos.demo.ejb.UsersBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;
@DeclareRoles({"CASHIER","GENERAL_DIRECTOR","ADMIN"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"CASHIER","GENERAL_DIRECTOR","ADMIN"}))
@WebServlet(name = "ErrorReportsByUser", value = "/ErrorReportsByUser")
public class ErrorReportsByUser extends HttpServlet {
    @Inject
    ErrorReportBean errorReportBean;
    @Inject
    UsersBean usersBean;
    @Inject
    CategoriesBean categoriesBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CategoryDto> categories = categoriesBean.findAllCategories();
        request.setAttribute("categories", categories);
        UserDto user=usersBean.findUserByUsername(request.getRemoteUser());
        List<ErrorReportDto> errorReports=errorReportBean.findErrorReportsByUserId(user.getId());
        request.setAttribute("errorReports", errorReports);
        request.getRequestDispatcher("/WEB-INF/pages/errorReports.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
