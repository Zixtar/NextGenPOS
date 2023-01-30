package com.nextgenpos.demo.servlets;

import com.nextgenpos.demo.common.CategoryDto;
import com.nextgenpos.demo.common.UserDto;
import com.nextgenpos.demo.ejb.CategoriesBean;
import com.nextgenpos.demo.ejb.UsersBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@DeclareRoles({"ADMIN"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"ADMIN"}))
@WebServlet(name = "EditUser", value = "/EditUser")
public class EditUser extends HttpServlet {
    @Inject
    UsersBean usersBean;
    @Inject
    CategoriesBean categoriesBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("userGroups", new String[] {"CASHIER", "GENERAL_DIRECTOR",
                "ADMIN"});
        Long userId=Long.parseLong(request.getParameter("id"));
        UserDto user=usersBean.findUserById(userId);
        request.setAttribute("user",user);
        List<CategoryDto> categories = categoriesBean.findAllCategories();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/WEB-INF/pages/editUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        String city = request.getParameter("city");
        String street = request.getParameter("street");
        Integer number = Integer.parseInt(request.getParameter("number"));
        Integer postalCode = Integer.parseInt(request.getParameter("postal_code"));
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String telNr = request.getParameter("tel_nr");
        String mbti = request.getParameter("mbti");
        Long userId = Long.parseLong(request.getParameter("user_id"));
        usersBean.updateUser(userId,email,country,city,street,number,postalCode,firstName,lastName,telNr,mbti);
        response.sendRedirect(request.getContextPath()+"/Users");
    }
}
