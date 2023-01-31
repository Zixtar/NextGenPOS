package com.nextgenpos.demo.servlets;

import com.nextgenpos.demo.common.UserDto;
import com.nextgenpos.demo.ejb.ErrorReportBean;
import com.nextgenpos.demo.ejb.UsersBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AddErrorReport", value = "/AddErrorReport")
public class AddErrorReport extends HttpServlet {
    @Inject
    ErrorReportBean errorReportBean;
    @Inject
    UsersBean usersBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        UserDto user=usersBean.findUserByUsername(request.getRemoteUser());
        request.setAttribute("user",user);
        request.getRequestDispatcher("/WEB-INF/pages/addErrorReport.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description=request.getParameter("description");
        Long userId=Long.parseLong(request.getParameter("user_id"));
        errorReportBean.createErrorReportForUserId(description,userId);
        response.sendRedirect(request.getContextPath() + "/ErrorReports");
    }
}
