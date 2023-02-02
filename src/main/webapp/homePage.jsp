<%@page contentType="text/html charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="HomePage">

    <div class="container">

        <link rel="stylesheet" href="<c:url value="resources/css/carousel.css"/>"/>

        <!-- Carousel 1 -->
        <div class="row">
            <div id="carousel1">
                    <%--            TODO:Carousel images iterartion with jstl library forEach :::Maybe it will be useful in the future--%>
                    <%--            <c:forEach var="car_img" items="carousel_images">--%>
                    <%--                <div class="carousel_item">--%>
                    <%--                    <img src="/resources/img/${img_name}"></img>--%>
                    <%--                </div>--%>
                    <%--            </c:forEach> --%>

                <div class="carousel_item carousel_item--visible">
                    <img src="${pageContext.request.contextPath}/CarouselPhoto?id=${bundleId}"/>
                </div>

                <div class="carousel_item ">
                    <img src="${pageContext.request.contextPath}/resources/img/violet_carousel.jpg"/>
                </div>

                <div class="carousel_item">
                    <img src="${pageContext.request.contextPath}/resources/img/7ds_carousel.jpg"/>
                </div>

                <div class="carousel_actions d-flex  justify-content-between">
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

        <!-- Carousel 2  <=> PRODUCT CAROUSEL-->
        <div class="row">
            <div id="carousel2">
                <section class="product">
                    <h2 class="product-category">Promo items</h2>
                    <button id="carousel_button--prev" aria-label="Previous slide">
                        &lt;
                    </button>
                    <button id="carousel_button--next" aria-label="Next slide">
                        &gt;
                    </button>
                    <div class="product-container">
                        <c:forEach var="offerItem" items="${offerItems}">
                            <div class="product-card">
                                <div class="product-image">
                                    <img src="${pageContext.request.contextPath}/ProductFirstPhoto?id=${offerItem.product.id}"
                                         class="product-heart image-placeholder"
                                         alt="${offerItem.product.description}"></img>
                                    <c:if test="${pageContext.request.getRemoteUser() != null}">
                                        <a href="${pageContext.request.contextPath}/CreateSale?id=${offerItem.product.id}">
                                            <button class="wishlist-btn" type="submit">
                                                <i class="bi bi-suit-heart-fill"></i>
                                            </button>
                                        </a>
                                    </c:if>
                                </div>
                                <c:if test="${pageContext.request.getRemoteUser() != null}">
                                    <div class="add-btn">
                                        <a href="${pageContext.request.contextPath}/CreateSale?id=${offerItem.product.id}">
                                            <button class="card-btn">Add to cart</button>
                                        </a>
                                    </div>
                                </c:if>
                                <div class="product-info">
                                    <p class="product-description">${offerItem.product.description}</p>
                                    <span class="price">${offerItem.newPrice}$</span>
                                    <span class="actual-price">${offerItem.product.price}$</span>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </section>
                <div class="dots">
                    <span class="dot active" data-index="0"></span>
                    <span class="dot" data-index="1"></span>
                    <span class="dot" data-index="2"></span>
                </div>
            </div>
        </div>

            <%-- Carousel 3 --%>
        <div class="row">
            <div id="carousel3">
                <section class="product">
                    <h2 class="product-category">Wishlist</h2>
                    <button id="carousel_button--prev" aria-label="Previous slide">
                        &lt;
                    </button>
                    <button id="carousel_button--next" aria-label="Next slide">
                        &gt;
                    </button>
                    <div class="product-container">
                        <c:forEach var="element" items="${mixedWishlist}">
                            <div class="product-card">
                                <div class="product-image">
                                    <c:choose>
                                        <c:when test="${not empty element.key}">
                                            <img src="${pageContext.request.contextPath}/ProductFirstPhoto?id=${element.key.product.id}"
                                                 class="product-heart image-placeholder" alt="${element.key.product.description}"></img>
                                            <c:if test="${pageContext.request.getRemoteUser() != null}">
                                                <a href="${pageContext.request.contextPath}/RemoveItemFromWishlist?id=${element.key.product.id}">
                                                    <button class="wishlist-btn">
                                                        <i class="bi bi-suit-heart-fill" style="color:deeppink"></i>
                                                    </button>
                                                </a>
                                            </c:if>
                                        </c:when>
                                        <c:when test="${empty element.key}">
                                            <img src="${pageContext.request.contextPath}/ProductFirstPhoto?id=${element.value.id}"
                                                 class="product-heart image-placeholder" alt="${element.value.description}"></img>
                                            <c:if test="${pageContext.request.getRemoteUser() != null}">
                                            <a href="${pageContext.request.contextPath}/RemoveItemFromWishlist?id=${element.value.id}">
                                                <button class="wishlist-btn">
                                                    <i class="bi bi-suit-heart-fill" style="color:deeppink"></i>
                                                </button>
                                            </a>
                                            </c:if>
                                        </c:when>
                                    </c:choose>
                                </div>
                                <c:if test="${pageContext.request.getRemoteUser() != null}">
                                    <div class="add-btn">
                                        <button class="card-btn">Add to cart</button>
                                    </div>
                                </c:if>

                                <div class="product-info">
                                    <c:if test="${not empty element.key}">
                                        <p class="product-description">${element.key.product.description}</p>
                                        <span class="price">${element.key.newPrice}$</span>
                                        <span class="actual-price"
                                              style="text-decoration:none; color:black; font-weight:700; font-size:1.5rem">${element.key.product.price}$
                                        </span>
                                    </c:if>
                                    <c:if test="${empty element.key}">
                                        <p class="product-description">${element.value.description}</p>
                                        <span class="actual-price"
                                              style="text-decoration:none; color:black; font-weight:700; font-size:1.5rem">${element.value.price}$
                                        </span>
                                    </c:if>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                </section>
                <c:if test="${mixedWishlistSize>3}">
                    <div class="dots">
                        <span class="dot active" data-index="0"></span>
                        <span class="dot" data-index="1"></span>
                        <span class="dot" data-index="2"></span>
                    </div>
                </c:if>
            </div>

        </div>

    </div>

    <%--  !!Please note that the script must be placed after all html elements if --%>
    <%--          you are intending to use the html elements in the js file!!--%>
        <script src="${pageContext.request.contextPath}/resources/js/carousel.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/homepage-carousels.js"></script>
    </div>


</t:pageTemplate>