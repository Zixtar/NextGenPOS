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
                    <img src="${pageContext.request.contextPath}/resources/img/saiki_carousel.jpg"/>
                </div>

                <div class="carousel_item ">
                    <img src="${pageContext.request.contextPath}/resources/img/violet_carousel.jpg"/>
                </div>

                <div class="carousel_item">
                    <img src="${pageContext.request.contextPath}/resources/img/7ds_carousel.jpg"/>
                </div>

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

        <!-- Carousel 2  <=> PRODUCT CAROUSEL-->
        <div class="row">
            <div id="carousel2">
                    <%--            TODO:Carousel images iterartion with jstl library forEach :::Maybe it will be useful in the future--%>
                    <%--            <c:forEach var="car_img" items="carousel_images">--%>
                    <%--                <div class="carousel1_item">--%>
                    <%--                    <img src="/resources/img/${img_name}"></img>--%>
                    <%--                </div>--%>
                    <%--            </c:forEach> --%>


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
                                    <img src="resources/img/converse.jpg" class="product-heart"
                                         alt="prodDescription"></img>
<%--                                <img src="{offerItem.product.photos[0]" class="product-heart"--%>
<%--                                     alt="prodDescription"></img>--%>
                                    <button class="wishlist-btn">
                                        <i class="bi bi-suit-heart-fill"></i>
                                    </button>
                                </div>
                                <div class="add-btn">
                                    <button class="card-btn">Add to cart</button>
                                </div>
                                <div class="product-info">
                                    <p class="product-description">${offerItem.product.description}</p>
                                    <span class="price">${offerItem.newPrice}$</span>
                                    <span class="actual-price">${offerItem.product.price}$</span>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                </section>
                    <%--                        <div class="product-card">--%>
                    <%--                            <div class="product-image">--%>
                    <%--                                <img src="resources/img/converse.jpg" class="product-heart"--%>
                    <%--                                     alt="prodDescription"></img>--%>
                    <%--                                <button class="wishlist-btn">--%>
                    <%--                                    <i class="bi bi-suit-heart-fill"></i>--%>
                    <%--                                </button>--%>
                    <%--                            </div>--%>
                    <%--                            <div class="add-btn">--%>
                    <%--                                <button class="card-btn">Add to cart</button>--%>
                    <%--                            </div>--%>
                    <%--                            <div class="product-info">--%>
                    <%--                                <p class="product-description">Product description</p>--%>
                    <%--                                <span class="price">$28</span>--%>
                    <%--                                <span class="actual-price">$40</span>--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>

                    <%--                        <div class="product-card">--%>
                    <%--                            <div class="product-image">--%>
                    <%--                                <img src="resources/img/converse.jpg" class="product-heart"--%>
                    <%--                                     alt="prodDescription"></img>--%>
                    <%--                                <button class="wishlist-btn">--%>
                    <%--                                    <i class="bi bi-suit-heart-fill"></i>--%>
                    <%--                                </button>--%>
                    <%--                            </div>--%>
                    <%--                            <div class="add-btn">--%>
                    <%--                                <button class="card-btn">Add to cart</button>--%>
                    <%--                            </div>--%>
                    <%--                            <div class="product-info">--%>
                    <%--                                <p class="product-description">Product description</p>--%>
                    <%--                                <span class="price">$28</span>--%>
                    <%--                                <span class="actual-price">$40</span>--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>

                    <%--                        <div class="product-card">--%>
                    <%--                            <div class="product-image">--%>
                    <%--                                <img src="resources/img/converse.jpg" class="product-heart"--%>
                    <%--                                     alt="prodDescription"></img>--%>
                    <%--                                <button class="wishlist-btn">--%>
                    <%--                                    <i class="bi bi-suit-heart-fill"></i>--%>
                    <%--                                </button>--%>
                    <%--                            </div>--%>
                    <%--                            <div class="add-btn">--%>
                    <%--                                <button class="card-btn">Add to cart</button>--%>
                    <%--                            </div>--%>
                    <%--                            <div class="product-info">--%>
                    <%--                                <p class="product-description">Product description</p>--%>
                    <%--                                <span class="price">$28</span>--%>
                    <%--                                <span class="actual-price">$40</span>--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>


                    <%--                        <div class="product-card">--%>
                    <%--                            <div class="product-image">--%>
                    <%--                                <img src="resources/img/converse.jpg" class="product-heart"--%>
                    <%--                                     alt="prodDescription"></img>--%>
                    <%--                                <button class="wishlist-btn">--%>
                    <%--                                    <i class="bi bi-suit-heart-fill"></i>--%>
                    <%--                                </button>--%>
                    <%--                            </div>--%>
                    <%--                            <div class="add-btn">--%>
                    <%--                                <button class="card-btn">Add to cart</button>--%>
                    <%--                            </div>--%>
                    <%--                            <div class="product-info">--%>
                    <%--                                <p class="product-description">Product description</p>--%>
                    <%--                                <span class="price">$28</span>--%>
                    <%--                                <span class="actual-price">$40</span>--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>


                    <%--                        <div class="product-card">--%>
                    <%--                            <div class="product-image">--%>
                    <%--                                <img src="resources/img/converse.jpg" class="product-heart"--%>
                    <%--                                     alt="prodDescription"></img>--%>
                    <%--                                <button class="wishlist-btn">--%>
                    <%--                                    <i class="bi bi-suit-heart-fill"></i>--%>
                    <%--                                </button>--%>
                    <%--                            </div>--%>
                    <%--                            <div class="add-btn">--%>
                    <%--                                <button class="card-btn">Add to cart</button>--%>
                    <%--                            </div>--%>
                    <%--                            <div class="product-info">--%>
                    <%--                                <p class="product-description">Product description</p>--%>
                    <%--                                <span class="price">$28</span>--%>
                    <%--                                <span class="actual-price">$40</span>--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>

                    <%--                        <div class="product-card">--%>
                    <%--                            <div class="product-image">--%>
                    <%--                                <img src="resources/img/converse.jpg" class="product-heart"--%>
                    <%--                                     alt="prodDescription"></img>--%>
                    <%--                                <button class="wishlist-btn">--%>
                    <%--                                    <i class="bi bi-suit-heart-fill"></i>--%>
                    <%--                                </button>--%>
                    <%--                            </div>--%>
                    <%--                            <div class="add-btn">--%>
                    <%--                                <button class="card-btn">Add to cart</button>--%>
                    <%--                            </div>--%>
                    <%--                            <div class="product-info">--%>
                    <%--                                <p class="product-description">Product description</p>--%>
                    <%--                                <span class="price">$28</span>--%>
                    <%--                                <span class="actual-price">$40</span>--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>


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
                    <%--            TODO:Carousel images iterartion with jstl library forEach :::Maybe it will be useful in the future--%>
                    <%--            <c:forEach var="car_img" items="carousel_images">--%>
                    <%--                <div class="carousel1_item">--%>
                    <%--                    <img src="/resources/img/${img_name}"></img>--%>
                    <%--                </div>--%>
                    <%--            </c:forEach> --%>

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
                                            <img src="resources/img/converse.jpg" class="product-heart"
                                                 alt="prodDescription"></img>
                                                <%--                                <img src="{offerItem.product.photos[0]" class="product-heart"--%>
                                                <%--                                     alt="prodDescription"></img>--%>
                                            <button class="wishlist-btn">
                                                <i class="bi bi-suit-heart-fill"></i>
                                            </button>
                                        </div>
                                        <div class="add-btn">
                                            <button class="card-btn">Add to cart</button>
                                        </div>
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

    </div>

    <%--  !!Please note that the script must be placed after all html elements if --%>
    <%--          you are intending to use the html elements in the js file!!--%>
    </div>
        <script src="${pageContext.request.contextPath}/resources/js/carousel.js"></script>
</t:pageTemplate>