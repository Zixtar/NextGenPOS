<%--
  Created by IntelliJ IDEA.
  User: andra
  Date: 1/29/2023
  Time: 12:53 PM
  To change this template use File | Settings | File Templates.
--%>
<t:pageTemplate pageTitle="Add product photo">
    <h1>Add Car Photo</h1>
    <form class="needs-validation" novalidate method="POST" enctype="multipart/form-data"
          action="${pageContext.request.contextPath}/AddProductPhoto">
        <div class="row">
            <div class="col-md-6 mb-3">
                Product name: ${product.name}
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <lavel for="file">Photo</lavel>
                <input type="file" name="file" id="file" required>
                <div class="invalid-feedback">
                    Photo is required
                </div>
            </div>
        </div>
        <input type="hidden" name="product_id" value="${product.id}"/>
        <hr class="mb-4">
        <button class="btn btn-primary btn-lg btn-block" type="submit">Save</button>
    </form>
</t:pageTemplate>
