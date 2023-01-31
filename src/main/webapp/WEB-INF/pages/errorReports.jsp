<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Error Reports">
    <div class="container text-center">
        <a href="${pageContext.request.contextPath}/AddErrorReport" class="btn btn-primary">Add Error Report</a>
        <c:forEach var="errorReport" items="${errorReports}">
            <div class="row">
                <div class="col">
                        ${errorReport.date}
                </div>
                <div class="col">
                        ${errorReport.description}
                </div>
                <div class="col">
                        ${errorReport.state}
                </div>
            </div>
        </c:forEach>
    </div>
</t:pageTemplate>