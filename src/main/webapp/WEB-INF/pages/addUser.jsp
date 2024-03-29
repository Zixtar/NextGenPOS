<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Add User">
  <div class="row d-flex justify-content-center">
    <div class="form-group col-md-6">
      <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/AddUser">
        <div class="d-flex justify-content-center">
          <h1 class="h3 mb-3 font-weight-normal">Add User</h1>
        </div>
        <div class="row-md-6">
          <div class="col">
            <label for="username">Username</label>
            <input type="text" class="form-control" id="username" name="username" placeholder="" value="" required>
            <div class="invalid-feedback">
              Username is required
            </div>
          </div>
        </div>
        <div class="row-md-6">
          <div class="col">
            <label for="email">Email</label>
            <input type="email" class="form-control" id="email" name="email" placeholder="" value="" required>
            <div class="invalid-feedback">
              Email is required
            </div>
          </div>
        </div>
        <div class="row-md-6">
          <div class="col">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="" value="" required>
            <div class="invalid-feedback">
              Password is required
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <label for="country">Country</label>
            <input type="text" class="form-control" id="country" name="country" placeholder="" value="" required>
            <div class="invalid-feedback">
              Country is required
            </div>
          </div>
          <div class="col-md-6">
            <label for="city">City</label>
            <input type="text" class="form-control" id="city" name="city" placeholder="" value="" required>
            <div class="invalid-feedback">
              City is required
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-4">
            <label for="street">Street</label>
            <input type="text" class="form-control" id="street" name="street" placeholder="" value="" required>
            <div class="invalid-feedback">
              Street is required
            </div>
          </div>
          <div class="col-md-4">
            <label for="number">Number</label>
            <input type="number" class="form-control" id="number" name="number" placeholder="" value="" required>
            <div class="invalid-feedback">
              Number is required
            </div>
          </div>
          <div class="col-md-4">
            <label for="postal_code">Postal Code</label>
            <input type="number" class="form-control" id="postal_code" name="postal_code" placeholder="" value="" required>
            <div class="invalid-feedback">
              Postal code is required
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <label for="first_name">First Name</label>
            <input type="text" class="form-control" id="first_name" name="first_name" placeholder="" value="" required>
            <div class="invalid-feedback">
              First name is required
            </div>
          </div>
          <div class="col-md-6">
            <label for="last_name">Last Name</label>
            <input type="text" class="form-control" id="last_name" name="last_name" placeholder="" value="" required>
            <div class="invalid-feedback">
              Last name is required
            </div>
          </div>
        </div>
        <div class="row-md-6">
          <div class="col">
            <label for="tel_nr">Telephone Number</label>
            <input type="number" class="form-control" id="tel_nr" name="tel_nr" placeholder="" value="" required>
            <div class="invalid-feedback">
              Telephone number is required
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <label for="user_group">Groups</label>
            <select name="user_group" class="custom-select d-block w-100" id="user_group">
              <c:forEach var="user_group" items="${userGroups}" varStatus="=status">
                <option value="${user_group}">${user_group}</option>
              </c:forEach>
            </select>
          </div>
          <div class="col-md-6">
            <label for="mbti">MBTI</label>
            <input type="text" class="form-control" id="mbti" name="mbti" placeholder="" value="" required>
            <div class="invalid-feedback">
              MBTI is required :)
            </div>
          </div>
        </div>
        <div class="cold-md-6 d-flex justify-content-center">
          <button class="btn btn-primary btn-lg" type="submit">Save</button>
        </div>
      </form>
    </div>
  </div>
</t:pageTemplate>