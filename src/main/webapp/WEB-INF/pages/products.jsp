<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Products">
    <div class="VarHolderDiv" value="${prodNr}"></div>
    <div class="container">
    <link rel="stylesheet" href="<c:url value="/resources/css/carousel.css"/>"/>
    <form method="POST" action="${pageContext.request.contextPath}/Products">
    <c:if test="${inAllProducts}">
        <a href="${pageContext.request.contextPath}/AddProduct" class="btn btn-primary">Add Product</a>
        <button class="btn btn-danger" type="submit">Delete Products</button>
    </c:if>

    <c:forEach var="product" items="${products}" varStatus="prod">


        <div class="col">
            <div id="carousel${prod.index+5}" class="carousel">
                <!--<div class="carousel_item carousel_item--visible">
            <img src="${pageContext.request.contextPath}/ProductPhotosIds?id=${photosId}" width="28" />
          </div>-->
                <c:if test="${empty product.photoIds}">
                    <div class="carousel_item carousel_item--visible}">
                        <img src="resources/img/Placeholder.png" width="28"/>
                    </div>
                </c:if>
                <c:forEach var="photosId" items="${product.photoIds}" begin="0" varStatus="status">
                    <div class="carousel_item ${status.index == 0 ? "carousel_item--visible" : "carousel_item--hidden"}">
                        <img src="${pageContext.request.contextPath}/ProductPhotosIds?id=${photosId}" width="28"/>
                    </div>
                </c:forEach>
                <c:if test="${not empty product.photoIds}">
                    <div class="carousel_actions">
                        <div class="prev_btn">
                            <button type="button" id="carousel_button--prev" aria-label="Previous slide">
                                &lt;
                            </button>
                        </div>

                        <div class="next_btn">
                            <button type="button" id="carousel_button--next" aria-label="Next slide">
                                &gt;
                            </button>
                        </div>

                    </div>

                    <div class="dots">
                        <c:forEach var="photosId" items="${product.photoIds}" varStatus="status">
                            <span class="dot ${status.index == 0 ? "active" : " "}"
                                  data-index="${status.index}"></span>
                        </c:forEach>
                    </div>
                </c:if>
            </div>

        </div>
        <div class="row">
            <b> Product Name: ${product.name} </b>
        </div>
        <div class="row">
            Price:${product.price} lei
        </div>
        <div class="row">
            <a href="${pageContext.request.contextPath}/CreateSale?id=${product.id}" class="btn btn-primary">Add to
                Cart</a>
        </div>
        </div>
        <div class="d-flex justify-content-center">
            <div class="d-flex justify-content-center">
                <div>
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/AddProductPhoto?id=${product.id}"
               role="button">Add photo</a>
                </div>
                <c:if test="${inAllProducts}">
                        <div class>
                        <div>Delete check</div>
                        <input class="del-cb" type="checkbox" name="product_ids" value="${product.id}"/>
                    </div>
                </c:if>

                <div>
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/EditProduct?id=${product.id}">Edit
                Product</a>
                </div>
            </div>
        </div>
    </c:forEach>
     </form>


    <script src="${pageContext.request.contextPath}/resources/js/carousel.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/products-carousels.js"></script>
    </div>
</t:pageTemplate>