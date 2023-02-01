<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="form-validation.js"></script>
<t:pageTemplate pageTitle="Add OfferBundle Photo">
    <h2>Add OfferBundle Photo</h2>
    <form method="POST" action="${pageContext.request.contextPath}/AddCarouselPhoto"
          enctype="multipart/form-data" class="needs-validation" novalidate>

        <div class="row">
            <div class="col-md-6 mb-3" style="font-weight: bolder; font-size: 1rem">
                Offer bundle name:
                <br>
                   <p> ${offerBundle.name}</p>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="file">Photo</label>
                <input type="file" name="file" id="file" required placeholder="Browse...">
                <div class="invalid-feedback">
                    Photo is required.
                </div>
            </div>
        </div>

        <input type="hidden" name="offerBundle_id" value="${offerBundle.id}">
        <hr class="mb-4">
        <button class="btn btn-primary btn-lg btn-block" type="submit" style="border-color: deeppink;background-color:deeppink">Save</button>

    </form>

</t:pageTemplate>