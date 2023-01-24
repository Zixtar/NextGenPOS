<%@tag description="Base page template" pageEncoding="UTF-8"%>
<%@attribute name="pageTitle"%>
<html>
<head>
    <title>${pageTitle}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</head>
<body>

    <jsp:include page="/header.jsp"/>

    <main class="myMain mt-5">
        <jsp:doBody/>
    </main>

<%--<jsp:include page="/WEB-INF/footer.jsp"/>--%>
<%-- TODO --%>
<%--<script src="/resources/js/header.js"></script> --%>
<script src="${pageContext.request.contextPath}/resources/js/form-validation.js"></script>
</body>
</html>
