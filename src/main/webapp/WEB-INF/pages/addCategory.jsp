<%--
  Created by IntelliJ IDEA.
  User: andra
  Date: 1/30/2023
  Time: 5:30 PM
  To change this template use File | Settings | File Templates.
--%>
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
<add:pageTemplate pageTitle="Add Category">
  <div class="row d-flex justify-content-center">
    <div class="form-group col-md-6">
      <form class="needs-validation"  action="${pageContext.request.contextPath}/AddCategory" novalidate method="post">
        <div class="d-flex justify-content-center">
          <h1 class="h3 mb-3 font-weight-normal">Add Category</h1>
        </div>
        <div class="row-md-6">
          <div class="col">
            <label for="name">Name</label>
            <input type="text" class="form-control"  name="name" id="name" placeholder="" value="" required>
            <div class="invalid-feedback">
              Name is required.
            </div>
          </div>
        </div>

        <div class="row-md-6">
          <div class="col">
            <button class="w-100 btn btn-primary btn-lg" type="submit">Save</button>
          </div>
          <div class="invalid-feedback">
            Submit is required.
          </div>
        </div>

      </form>
    </div>
  </div>
</add:pageTemplate>

