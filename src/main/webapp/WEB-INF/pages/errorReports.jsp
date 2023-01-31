<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Error Reports">
    <div class="container text-center">
        <a href="${pageContext.request.contextPath}/AddErrorReport" class="btn btn-primary">Add Error Report</a>
        <form class="needs-validation"  action="${pageContext.request.contextPath}/ErrorReports" novalidate method="post">
            <c:forEach var="errorReport" items="${errorReports}">
                <div class="row">
                    <div class="col">
                            ${errorReport.username}
                    </div>
                    <div class="col">
                            ${errorReport.date}
                    </div>
                    <div class="col">
                            ${errorReport.description}
                    </div>
                    <div class="col">
                    <c:if test="${pageContext.request.isUserInRole('ADMIN')}">
                            <label for="error_report_ids">State:</label>
                            <c:if test="${errorReport.state}">
                                <input type="checkbox" name="error_report_ids" id="error_report_ids" checked value="${errorReport.id}">
                            </c:if>
                            <c:if test="${!errorReport.state}">
                                <input type="checkbox" name="error_report_ids" id="error_report_ids" value="${errorReport.id}">
                            </c:if>
                    </c:if>
                    <c:if test="${pageContext.request.isUserInRole('GENERAL_DIRECTOR') or pageContext.request.isUserInRole('CASHIER')}">
                        <c:if test="${errorReport.state}">
                            Solved
                        </c:if>
                        <c:if test="${!errorReport.state}">
                            Not solved
                        </c:if>
                    </c:if>
                    </div>
                </div>
            </c:forEach>
            <c:if test="${pageContext.request.isUserInRole('ADMIN')}">
                <button class="btn btn-primary btn-lg" type="submit">Save</button>
            </c:if>
        </form>
    </div>
</t:pageTemplate>