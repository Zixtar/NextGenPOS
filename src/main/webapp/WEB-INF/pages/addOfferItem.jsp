<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="form-validation.js"></script>
<t:pageTemplate pageTitle="Add offer item">
  <div class="row d-flex justify-content-center">
    <div class="form-group col-md-6">
      <form method="POST" class="needs-validation"  action="${pageContext.request.contextPath}/AddOfferItem">
        <div class="d-flex justify-content-center">
          <h1 class="h3 mb-3 font-weight-normal">Add Offer Item</h1>
        </div>
        <div class="row-md-6">
          <div class="col">
            <br>
            <div class="row">
              <div class="col-md-4">
                <label for="product_id" class="form-label" style="font-size: 1.5rem; margin-bottom: 1rem; font-weight: bolder">Product</label>
                <br>
                <select class="form-select" id="product_id" name="product_id" required style="margin-bottom: 1rem">
                  <c:forEach var="product" items ="${products}" varStatus="status">
                    <option value="${product.id}">${product.name}</option>
                  </c:forEach>
                </select>
              </div>
              <div class="invalid-feedback">
                Product is required.
              </div>
            </div>


    <%--        <div class="row">--%>
    <%--          <div class="col-md-4">--%>
    <%--            <label for="bundle_id" class="form-label">Bundle</label>--%>
                <input type="hidden" name="bundle_id" id="bundle_id"  value="${bundleId}" >
    <%--          </div>--%>
    <%--          <div class="invalid-feedback">--%>
    <%--            Bundle is required.--%>
    <%--          </div>--%>
    <%--        </div>--%>


            <label for="new_price" style="font-size: 1.5rem; font-weight: bolder; margin-bottom: 1rem">New price</label>
            <br>
            <input type="text" name="new_price" id="new_price"  required style="margin-bottom: 1rem">
            <div class="invalid-feedback">
              New price is required.
            </div>

          </div>
        </div>
        <div class="row-md-6">
          <div class="col">
            <label for="beginning_date" style="font-size: 1.5rem; font-weight: bolder; margin-bottom: 1rem">Beginning date</label>
            <br>
            <input type="date" id="beginning_date" name="beginning_date" required style="margin-bottom: 1rem">
            <div class="invalid-feedback">
              Beginning date is required.
            </div>
          </div>
        </div>

        <div class="row-md-6">
          <div class="col">
            <label for="end_date" style="font-size: 1.5rem; margin-bottom: 1rem; font-weight: bolder">End date</label>
            <br>
            <input type="date" id="end_date" name="end_date" required style="margin-bottom: 1rem; ">
            <div class="invalid-feedback">
              End date is required.
            </div>
            <br>
            <button type="submit" class=" btn btn-primary btn-lg" style="background-color: deeppink; border-color: deeppink">Save</button>
          </div>

        </div>
      </form>
    </div>
  </div>
</t:pageTemplate>
