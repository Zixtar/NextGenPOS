<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/header.js"> </script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
<link rel="stylesheet" href="<c:url value ="/resources/css/header.css"/>"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<nav class="header_bar navbar navbar-dark bg-dark" >
    <div class="container-fluid">

        <div class="navbar-collapse me-1 pt-0 " >

            <ul class="navbar nav">

                <li class="nav-item">

                    <button class="hamburger_button" id="hamburger_button" type="button"  data-bs-toggle="collapse" aria-expanded="false" data-bs-target="#collapsableMenu" aria-controls="collapsableMenu">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-list" viewBox="0 0 16 16">
                            <path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
                        </svg>
                    </button>

                </li>
                <li class="nav-item">
                    <a class="logo navbar-brand mx-auto">
                        <%--<img src="<c:url value="/resources/img/logo_placeholder.jpg" /> " id="logo_img" alt="logo.png">--%>
                        <p class="">ENTER LOGO HERE</p>

                        <%-- TODO:   For Logo: image or text? If Logo then we need to resize the hamburger menu else it's alright --%>
                    </a>

                </li>
                <li class="nav-item">
                    <p class="">ROLE</p>
                    <%--TODO:    Add something for ROLE--%>
                </li>
            </ul>

        </div>

    </div>

</nav>

<div class="hamburger-menu mt-0 collapse" id="collapsableMenu">
    <h2>All products</h2>
    <%--TODO:form when clicking search button for category--%>
    <form action="dunno_yet.jsp" method="POST">
    <div class="search_bar" tabindex="-1">
        <input type="search" id="search" placeholder="Search...">
        <button type="submit" id="heart_search">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search-heart-fill" viewBox="0 0 16 16">
                <path d="M6.5 13a6.474 6.474 0 0 0 3.845-1.258h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.008 1.008 0 0 0-.115-.1A6.471 6.471 0 0 0 13 6.5 6.502 6.502 0 0 0 6.5 0a6.5 6.5 0 1 0 0 13Zm0-8.518c1.664-1.673 5.825 1.254 0 5.018-5.825-3.764-1.664-6.69 0-5.018Z"/>
            </svg>
        </button>
    </div>
    </form>
    <button type="button" id="close_btn"  data-bs-toggle="collapse"  data-bs-target="#collapsableMenu" aria-expanded="false" aria-controls="collapsableMenu" >
        <i class="closeIcon material-icons" >close</i>
    </button>
    <h4>Categories</h4>
    <ul>
        <li><a class="hambMenu_item" href="#">Category1</a></li>
        <li><a class="hambMenu_item" href="#">Category2</a></li>
        <li><a class="hambMenu_item" href="#">Category3</a> </li>
        <%-- TODO:  Menu categories - Use when access granted to Category entity
        <c:forEach var="category" items="${categories}">
            <li><a class="hambMenu_item" href="#">${category.name}</a> </li>
        </c:forEach>
        --%>
    </ul>
    <footer>
        <p class="text-muted">&copy Copyright Teen Titans.All rights reserved</p>
    </footer>
</div>


