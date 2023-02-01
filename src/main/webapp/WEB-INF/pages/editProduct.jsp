<%--
  Created by IntelliJ IDEA.
  User: andra
  Date: 12/6/2022
  Time: 10:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="add" tagdir="/WEB-INF/tags" %>>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<add:pageTemplate pageTitle="Edit Product">
    <h1> Edit Product</h1>
    <form class="needs-validation"  action="${pageContext.request.contextPath}/EditProduct" novalidate method="post">
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="name">Name</label>
                <input type="text" class="form-control"  name="name" id="name" placeholder="" value="${product.name}" required>
                <div class="invalid-feedback">
                    Name is required.
                </div>
            </div>
        </div>



        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="stock">Stock</label>
                <input class="form-control" type="number" name="stock" id="stock" placeholder="" value="${product.stock}" required>
                <div class="invalid-feedback">
                    Stock is required.
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="description">Description</label>
                <input class="form-control" type="text" name="description" id="description" placeholder="" value="${product.description}" required>
                <div class="invalid-feedback">
                    Description is required.
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="price">Price</label>
                <input class="form-control" type="number" name="price" id="price" placeholder="" value="${product.price}" required>
                <div class="invalid-feedback">
                    Price is required.
                </div>
            </div>
        </div>


        <div class="row">
            <div class="col-md-4">
                <label for="category_ids" class="form-label">Category</label>
                <select class="form-select" id="category_ids" name="category_ids" multiple >
                    <c:forEach var="category" items ="${productCategories}" varStatus="status">
                        <option value="${category.id}" selected>${category.name}</option>
                    </c:forEach>
                    <c:forEach var="category" items ="${productNoCategories}" varStatus="status">
                        <option value="${category.id}" >${category.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="invalid-feedback">
                Category is required.
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <button class="w-100 btn btn-primary btn-lg" type="submit">Save</button>
            </div>
            <div class="invalid-feedback">
                Submit is required.
            </div>
        </div>
        <input type="hidden" name="product_id" value="${product.id}" />
    </form>
</add:pageTemplate>
