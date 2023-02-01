<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="form-validation.js"></script>
<t:pageTemplate pageTitle="Add offer bundle">
    <h1 class="pt-3">Add offer bundle</h1>
    <form method="POST" class="needs-validation" action="${pageContext.request.contextPath}/CreateOfferBundle">
        <div class="row">
            <div class="col-md-6 mb-3">
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
        </div>
    </form>
</t:pageTemplate>
