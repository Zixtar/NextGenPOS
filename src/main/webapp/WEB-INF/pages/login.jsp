<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Login">
  <c:if test="${message} != null}">
    <div class="alert" role="alert">
      ${message}
    </div>
  </c:if>
  <div class="row justify-content-center">
    <div class="form-group col-md-4 col-md-offset-4 align-center">
      <div>
        <form class="form-signin" method="POST" action="j_security_check">
          <h1 class="h3 mb-3 font-weight-normal">Login</h1>
          <label for="username" class="sr-only align-center">Registration Name</label>
          <input type="text" id="username" name="j_username" class="form-control" placeholder="Username" required autofocus>
          <label for="password" class="sr-only">Password</label>
          <input type="password" id="password" name="j_password" class="form-control" placeholder="Password" required>
          <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
        </form>
      </div>
    </div>
  </div>
</t:pageTemplate>