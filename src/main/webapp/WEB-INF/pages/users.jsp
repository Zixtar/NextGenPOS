<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Users">
    <div class="container text-center">
        <a href="${pageContext.request.contextPath}/AddUser" class="btn btn-primary">Add User</a>
        <c:forEach var="user" items="${users}">
            <div class="row">
                <div class="col">
                    ${user.username}
                </div>
                <div class="col">
                    ${user.email}
                </div>
                <div class="col">
                    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/EditUser?id=${user.id}">Edit User</a>
                </div>
            </div>
        </c:forEach>
    </div>
</t:pageTemplate>