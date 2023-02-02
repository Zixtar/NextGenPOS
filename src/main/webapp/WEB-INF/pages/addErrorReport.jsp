<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Add Error Report">
    <div class="row d-flex justify-content-center">
        <div class="form-group col-md-6">
            <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/AddErrorReport">
                <div class="d-flex justify-content-center">
                    <h1 class="h3 mb-3 font-weight-normal">Add Error Report</h1>
                </div>
                <div class="row-md-6">
                    <div class="col">
                        <label for="description">Description</label>
                        <input type="text" class="form-control" id="description" name="description" placeholder="" value="" required>
                        <div class="invalid-feedback">
                            Description is required
                        </div>
                    </div>
                </div>
                <input type="hidden" name="user_id" id="user_id" value="${user.id}">
                <div class="cold-md-6 d-flex justify-content-center">
                    <button class="btn btn-primary btn-lg" type="submit">Save</button>
                </div>
            </form>
        </div>
    </div>
</t:pageTemplate>