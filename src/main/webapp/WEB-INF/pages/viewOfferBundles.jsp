<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:pageTemplate pageTitle="Cars">
  <h1>Offer bundles</h1>
  <form method="POST" action="${pageContext.request.contextPath}/DisplayOfferBundles">
  <a href="${pageContext.request.contextPath}/CreateOfferBundle" class="btn btn-primary" style="background-color: deeppink; border-color: deeppink">Create offer bundle</a>
    <div class="container text-center">
      <c:forEach var="offerBundle" items="${offerBundles}">
        <div class="row">
          <div class="col">
              ${offerBundle.id}
          </div>
          <div class="col">
              ${offerBundle.name}
          </div>

          <div class="col">
            <img src="${pageContext.request.contextPath}/CarouselPhoto?id=${offerBundle.id}" width="50">
          </div>

            <div class="col mb-2 ">
              <a class="btn btn-secondary"
                 href="${pageContext.request.contextPath}/AddCarouselPhoto?id=${offerBundle.id}">Add photo</a>
            </div>

            <div class="col">
              <a class="btn btn-secondary"
                 href="${pageContext.request.contextPath}/AddOfferItem?id=${offerBundle.id}">Add offer items</a>
            </div>


        </div>
      </c:forEach>
    </div>
  </form>
</t:pageTemplate>
