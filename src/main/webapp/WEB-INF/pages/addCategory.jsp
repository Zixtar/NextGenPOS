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
  <h1>Add Category</h1>
  <form class="needs-validation"  action="${pageContext.request.contextPath}/AddCategory" novalidate method="post">
    <div class="row">
      <div class="col-md-6 mb-3">
        <label for="name">Name</label>
        <input type="text" class="form-control"  name="name" id="name" placeholder="" value="" required>
        <div class="invalid-feedback">
          Name is required.
        </div>
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

  </form>
</add:pageTemplate>

