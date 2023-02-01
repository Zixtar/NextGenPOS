package com.nextgenpos.demo.servlets;

import com.nextgenpos.demo.common.ErrorReportDto;
import com.nextgenpos.demo.ejb.ErrorReportBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Bool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ErrorReports", value = "/ErrorReports")
public class ErrorReports extends HttpServlet {
    @Inject
    ErrorReportBean errorReportBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ErrorReportDto> errorReports=errorReportBean.findAllErrorReports();
        request.setAttribute("errorReports", errorReports);
        request.getRequestDispatcher("/WEB-INF/pages/errorReports.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] errorReportIdsByString=request.getParameterValues("error_report_ids");
        if(errorReportIdsByString != null){
            List<Long> errorReportIds = new ArrayList<>();
            for(String categoryIdAsString : errorReportIdsByString){
                errorReportIds.add(Long.parseLong(categoryIdAsString));
            }
            errorReportBean.setErrorReportStateByIds(errorReportIds);
        }
        response.sendRedirect(request.getContextPath() + "/ErrorReports");
    }
}
