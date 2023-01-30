package com.nextgenpos.demo.servlets;

import com.nextgenpos.demo.ejb.UsersBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
@DeclareRoles({"ADMIN"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"ADMIN"}))
@WebServlet(name = "AddUser", value = "/AddUser")
public class AddUser extends HttpServlet {
    @Inject
    UsersBean usersBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("userGroups", new String[] {"CASHIER", "GENERAL_DIRECTOR",
                "ADMIN"});
        request.getRequestDispatcher("/WEB-INF/pages/addUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String country = request.getParameter("country");
        String city = request.getParameter("city");
        String street = request.getParameter("street");
        Integer number = Integer.parseInt(request.getParameter("number"));
        Integer postalCode = Integer.parseInt(request.getParameter("postal_code"));
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String telNr = request.getParameter("tel_nr");
        String userGroup = request.getParameter("user_group");
        String mbti = request.getParameter("mbti");
        usersBean.createUser(username, email, password,country,city,street,number,postalCode,
                firstName,lastName,telNr, userGroup,mbti);
        response.sendRedirect(request.getContextPath() + "/Users");
    }
}
