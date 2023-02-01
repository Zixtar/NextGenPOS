<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Products">
    <div class="VarHolderDiv" value="${prodNr}"></div>
    <div class="container">
    <link rel="stylesheet" href="<c:url value="/resources/css/carousel.css"/>"/>
    <form method="POST" action="${pageContext.request.contextPath}/Products">
    <c:if test="${inAllProducts}">
        <c:if test="${pageContext.request.isUserInRole('GENERAL_DIRECTOR') or pageContext.request.isUserInRole('ADMIN')}">
            <a href="${pageContext.request.contextPath}/AddProduct" class="btn btn-primary">Add Product</a>
            <button class="btn btn-danger" type="submit">Delete Products</button>
        </c:if>
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
        <div class="d-flex justify-content-between">
            <b> Product Name: ${product.name} </b>
            <a href="${pageContext.request.contextPath}/AddToWishlist?id=${product.id}">
            <svg id="_x32_" xmlns="http://www.w3.org/2000/svg" width="25px" height="25px" viewBox="0 0 512 512" xml:space="preserve"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <style type="text/css">  .st0{fill:deeppink;}  </style> <g>
                <path class="st0" d="M256,0C114.609,0,0,114.625,0,256s114.609,256,256,256c141.375,0,256-114.625,256-256S397.375,0,256,0z M274.859,362.063c-5.969,2.656-17.984,7.281-17.984,7.281c-0.281,0.125-0.578,0.188-0.875,0.188s-0.594-0.063-0.875-0.188 c0,0-12.016-4.625-17.984-7.281c-94.297-41.719-110.078-105.625-110.078-139.125c0-35.594,28.859-64.469,64.469-64.469 S256,187.344,256,222.938c0-35.594,28.859-64.469,64.453-64.469c35.609,0,64.469,28.875,64.469,64.469 C384.922,256.438,369.156,320.344,274.859,362.063z"></path> </g> </g></svg></a>
        </div>
        <div class="row">
            Price:${product.price} lei
        </div>
        <c:if test="${pageContext.request.getRemoteUser() != null}">
            <div class="row">
                <a href="${pageContext.request.contextPath}/CreateSale?id=${product.id}" class="btn btn-primary">Add to
                    Cart</a>
            </div>
        </c:if>
        </div>
        <c:if test="${pageContext.request.isUserInRole('GENERAL_DIRECTOR') or pageContext.request.isUserInRole('ADMIN')}">
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
        </c:if>
    </c:forEach>
     </form>


    <script src="${pageContext.request.contextPath}/resources/js/carousel.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/products-carousels.js"></script>
    </div>
</t:pageTemplate>