<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Products">

    <div class="container">
  <link rel="stylesheet" href="<c:url value="/resources/css/carousel.css"/>"/>

    <c:forEach var="product" items="${products}">

        <div class="col">
          <div id="carousel1">
          <div class="carousel_item carousel_item--visible">
            <img src="${pageContext.request.contextPath}/ProductPhotosIds?id=${photosId}" width="28" />
          </div>
        <c:forEach var="photosId" items="${product.photoIds}" begin="1">
          <div class="carousel_item">
            <img src="${pageContext.request.contextPath}/ProductPhotosIds?id=${photosId}" width="28" />
          </div>
        </c:forEach>
          <div class="carousel_actions">
            <div class="prev_btn">
              <button id="carousel_button--prev" aria-label="Previous slide">
                &lt;
              </button>
            </div>

            <div class="next_btn">
              <button id="carousel_button--next" aria-label="Next slide">
                &gt;
              </button>
            </div>

          </div>

          <div class="dots">
            <span class="dot active" data-index="0"></span>
            <span class="dot" data-index="1"></span>
            <span class="dot" data-index="2"></span>
          </div>
        </div>

      </div>
          <div class="row">
            <b> Product Name: ${product.name} </b>
          </div>
          <div class="row">
            Price:${product.price} lei
          </div>
          <div class="row">
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/AddProductPhoto?id=${product.id}" role="button">Add photo</a>
          </div>
        </div>
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/EditProduct?id=${product.id}">Edit Product</a>
    </c:forEach>
    <a href="${pageContext.request.contextPath}/AddProduct" class="btn btn-primary">Add Product</a>

    <script src="${pageContext.request.contextPath}/resources/js/carousel.js"></script>
    </div>
</t:pageTemplate>