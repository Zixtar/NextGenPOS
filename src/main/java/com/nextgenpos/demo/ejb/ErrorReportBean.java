package com.nextgenpos.demo.ejb;

import com.nextgenpos.demo.common.CategoryDto;
import com.nextgenpos.demo.common.ErrorReportDto;
import com.nextgenpos.demo.entities.Category;
import com.nextgenpos.demo.entities.ErrorReport;
import com.nextgenpos.demo.entities.Product;
import com.nextgenpos.demo.entities.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class ErrorReportBean {
    private static final Logger LOG = Logger.getLogger(ErrorReportBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<ErrorReportDto> findAllErrorReports(){
        LOG.info("findAllErrorReports");
        TypedQuery<ErrorReport> typedQuery = entityManager.createQuery("SELECT e FROM ErrorReport e", ErrorReport.class);
        List<ErrorReport> errorReports = typedQuery.getResultList();
        return copyErrorReportsToDto(errorReports);
    }

    private List<ErrorReportDto> copyErrorReportsToDto(List<ErrorReport> errorReports) {
        java.util.List<ErrorReportDto> errorReportDtoList = new ArrayList<>();
        for (ErrorReport s: errorReports) {
            errorReportDtoList.add(new ErrorReportDto(s.getId(), s.getDescription(), s.getState(), s.getDate()));
        }
        return errorReportDtoList;
    }

    public List<ErrorReportDto> findErrorReportsByUserId(Long userId){
        LOG.info("findErrorReportsByUserId");
        User user=entityManager.find(User.class,userId);
        TypedQuery<ErrorReport> typedQuery = entityManager.createQuery("SELECT e FROM ErrorReport e where e.user=:user", ErrorReport.class)
                .setParameter("user",user);
        List<ErrorReport> errorReports = typedQuery.getResultList();
        return copyErrorReportsToDto(errorReports);
    }

    public void createErrorReportForUserId(String description, Long userId){
        LOG.info("createErrorReportForUserId");
        User user=entityManager.find(User.class,userId);
        ErrorReport errorReport=new ErrorReport();
        errorReport.setDate(java.time.LocalDate.now());
        errorReport.setDescription(description);
        errorReport.setState(false);
        errorReport.setUser(user);
        user.getErrorReports().add(errorReport);
        entityManager.persist(errorReport);
    }
}
