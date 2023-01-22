<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Products">
  <div class="container text-center">
    <a href="${pageContext.request.contextPath}/AddProduct" class="btn btn-primary">Add Product</a>
    <c:forEach var="productsForCategory" items="${productsForCategories}">
      <div>
        <h3 >Category name : </h3>
      </div>
      <c:forEach var="productForCategory" items="${productsForCategory}">
        <div class="row">
          <div class="col">
            <b> Product Name: ${productForCategory.name} </b> <br>
          </div>
          <div class="col">
            Price:${productForCategory.price} lei
          </div>
        </div>
      </c:forEach>
    </c:forEach>
  </div>
</t:pageTemplate>