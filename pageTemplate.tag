<%@tag description="Base page template" pageEncoding="UTF-8"%>
<%@attribute name="pageTitle"%>
<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<main class="myMain"
<jsp:doBody/>
<main/>
<%-- TODO --%>
<script src="${pageContext.request.contextPath}/resources/js/header.js"></script>
</body>
</html>
