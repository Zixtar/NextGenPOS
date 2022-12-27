<%@page contentType="text/html charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="HomePage">
    <div class="container">

    <div class="row">
        <div class="carousel1">
<%--            TODO:Carousel images iterartion with jstl library forEach :::Maybe it will be useful in the future--%>
<%--            <c:forEach var="car_img" items="carousel_images">--%>
<%--                <div class="carousel1_item">--%>
<%--                    <img src="/resources/img/${img_name}"></img>--%>
<%--                </div>--%>
<%--            </c:forEach> --%>

    <div class="carousel1_item">
        <img src="/resources/img/logo_placeholder.jpg"/>
    </div>

    <div class="carousel1_item">
        <img src="/resources/img/logo_placeholder.jpg"/>
    </div>

    <div class="carousel1_item">
        <img src="/resources/img/logo_placeholder.jpg"/>
    </div>

        </div>
    </div>

    <div class="row">

    </div>

    <div class="row">

    </div>

    </div>

</t:pageTemplate>