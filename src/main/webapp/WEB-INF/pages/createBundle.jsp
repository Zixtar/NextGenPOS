<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="form-validation.js"></script>
<t:pageTemplate pageTitle="Add offer bundle">
    <div class="row d-flex justify-content-center">
        <div class="form-group col-md-6">
            <form method="POST" class="needs-validation" action="${pageContext.request.contextPath}/CreateOfferBundle">
                <div class="d-flex justify-content-center">
                    <h1 class="h3 mb-3 font-weight-normal">Add Offer Bundle</h1>
                </div>
                <div class="row-md-6">
                    <div class="col">
                        <br>
                        <label for="bundleName">Bundle name</label>
                        <input type="text" name="bundleName" id="bundleName" class="form-control" required >
                        <div class="invalid-feedback">
                            Bundle name is required.
                        </div>

                    </div>
                </div>
                <br>
                <button type="submit" class="btn btn-primary btn-lg pt-2" style="background-color: deeppink;border-color: deeppink;">Save</button>
            </form>
        </div>
    </div>

</t:pageTemplate>
