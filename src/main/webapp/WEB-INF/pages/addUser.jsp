<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Add User">
  <div class="row justify-content-center">
    <div class="form-group col-md-4 col-md-offset-3 align-center">
      <div>
        <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/AddUser">
          <div class="row">
            <div class="col-md-6 mb-3">
              <label for="username">Username</label>
              <input type="text" class="form-control" id="username" name="username" placeholder="" value="" required>
              <div class="invalid-feedback">
                Username is required
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6 mb-3">
              <label for="email">Email</label>
              <input type="email" class="form-control" id="email" name="email" placeholder="" value="" required>
              <div class="invalid-feedback">
                Email is required
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6 mb-3">
              <label for="password">Password</label>
              <input type="password" class="form-control" id="password" name="password" placeholder="" value="" required>
              <div class="invalid-feedback">
                Password is required
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6 mb-3">
              <label for="user_group">Groups</label>
              <select name="user_group" class="custom-select d-block w-100" id="user_group">
                <c:forEach var="user_group" items="${userGroups}" varStatus="=status">
                  <option value="${user_group}">${user_group}</option>
                </c:forEach>
              </select>
            </div>
          </div>

          <button class="btn btn-primary btn-lg" type="submit">Save</button>
        </form>
      </div>
    </div>
  </div>
</t:pageTemplate>