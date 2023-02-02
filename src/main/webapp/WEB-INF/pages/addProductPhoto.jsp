<%--
  Created by IntelliJ IDEA.
  User: andra
  Date: 1/29/2023
  Time: 12:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Add product photo">
    <div class="row d-flex justify-content-center">
        <div class="form-group col-md-6">
            <form class="needs-validation" novalidate method="POST" enctype="multipart/form-data"
                  action="${pageContext.request.contextPath}/AddProductPhoto">
                <div class="d-flex justify-content-center">
                    <h1 class="h3 mb-3 font-weight-normal">Add Car Photo</h1>
                </div>
                <div class="row-md-6">
                    <div class="col">
                        Product name: ${product.name}
                    </div>
                </div>
                <div class="row-md-6">
                    <div class="col">
                        <lavel for="file">Photo</lavel>
                        <input type="file" name="file" id="file" required>
                        <div class="invalid-feedback">
                            Photo is required
                        </div>
                    </div>
                </div>
                <input type="hidden" name="product_id" value="${product.id}"/>
                <hr class="mb-4">
                <button class="btn btn-primary btn-lg btn-block" type="submit">Save</button>
            </form>
        </div>
    </div>
</t:pageTemplate>
