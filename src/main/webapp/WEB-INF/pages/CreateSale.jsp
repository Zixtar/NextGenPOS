<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="<c:url value ="/resources/css/createSale.css"/>"/>

<t:pageTemplate pageTitle="Create Sale">
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/create-sale.js"></script>

  <div class="container justify-content-center Content">
    <div class="Sale-Text d-flex justify-content-between">
      <div class="align-self-center"> Cashier: ${pageContext.request.getRemoteUser().toString()}</div>
      <div class="text-center">Date: <div class="d-inline-block>" name="date"></div></div>
    </div>
    <div class="text-center Sale-Text">Sale: #${saleID}</div>
    <div class="overflow-auto SaleProducts-Container " >
      <c:if test="${not empty saleProducts}">
        <c:forEach var="saleProduct" items="${saleProducts}" varStatus="status">
          <div class="Sale-Item d-flex justify-content-between align-items-center">
            <img src="${pageContext.request.contextPath}/ProductPhotosIds?id=${saleProduct.photoId}" width="50px" height="50px"/>
            <div class="text-center">${saleProduct.productName}</div>
            <div>
              <div class="text-center">Qty:${saleProduct.quantity}</div>
              <div class="d-flex justify-content-between">
                <div onclick="ChangeItem(`${status.index}`,`increaseQty`)"  class="btn Qty-btn"><svg width="20px" height="20px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <path fill-rule="evenodd" clip-rule="evenodd" d="M2 4.5C2 3.11929 3.11929 2 4.5 2H19.5C20.8807 2 22 3.11929 22 4.5V19.5C22 20.8807 20.8807 22 19.5 22H4.5C3.11929 22 2 20.8807 2 19.5V4.5ZM12.5 5.5C13.0523 5.5 13.5 5.94772 13.5 6.5V10.5H17.5C18.0523 10.5 18.5 10.9477 18.5 11.5V12.5C18.5 13.0523 18.0523 13.5 17.5 13.5H13.5V17.5C13.5 18.0523 13.0523 18.5 12.5 18.5H11.5C10.9477 18.5 10.5 18.0523 10.5 17.5V13.5H6.5C5.94772 13.5 5.5 13.0523 5.5 12.5V11.5C5.5 10.9477 5.94772 10.5 6.5 10.5H10.5V6.5C10.5 5.94772 10.9477 5.5 11.5 5.5H12.5Z" fill="deeppink"></path> </g></svg></div>
                <div onclick="ChangeItem(`${status.index}`,`decreaseQty`)" class="btn Qty-btn" ><svg width="20px" height="20px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <path d="M16.19 2H7.81C4.17 2 2 4.17 2 7.81V16.18C2 19.83 4.17 22 7.81 22H16.18C19.82 22 21.99 19.83 21.99 16.19V7.81C22 4.17 19.83 2 16.19 2ZM16 12.75H8C7.59 12.75 7.25 12.41 7.25 12C7.25 11.59 7.59 11.25 8 11.25H16C16.41 11.25 16.75 11.59 16.75 12C16.75 12.41 16.41 12.75 16 12.75Z" fill="deeppink"></path> </g></svg></div>
              </div>
            </div>
            <div> Price:${saleProduct.price} RON </div>
            <svg onclick="ChangeItem(`${status.index}`,`removeItm`)" width="28" height="28" xmlns="http://www.w3.org/2000/svg" fill-rule="evenodd" clip-rule="evenodd"><path d="M19 24h-14c-1.104 0-2-.896-2-2v-16h18v16c0 1.104-.896 2-2 2zm-7-10.414l3.293-3.293 1.414 1.414-3.293 3.293 3.293 3.293-1.414 1.414-3.293-3.293-3.293 3.293-1.414-1.414 3.293-3.293-3.293-3.293 1.414-1.414 3.293 3.293zm10-8.586h-20v-2h6v-1.5c0-.827.673-1.5 1.5-1.5h5c.825 0 1.5.671 1.5 1.5v1.5h6v2zm-8-3h-4v1h4v-1z" fill="deeppink"/></svg>
          </div>
        </c:forEach>
      </c:if>
    </div>
    <div class="BottomPart">
    <div class="d-flex justify-content-end">
      <div class="justify-self-end"> Total:${totalPrice} RON</div>
    </div>
    <div class="d-flex justify-content-center">
      <div class="btn btn-primary FinBtn " onclick="FinalizeSale()" ${empty saleProducts  ? '' : 'disabled'}>Finalize</div>
    </div>
    <a class="btn btn-danger" href="${pageContext.request.contextPath}/EndSale">Cancel</a>
    </div>
  </div>
</t:pageTemplate>