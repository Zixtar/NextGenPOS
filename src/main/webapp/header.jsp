<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/header.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
<link rel="stylesheet" href="<c:url value ="/resources/css/header.css"/>"/>
<%--<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">--%>
<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>--%>


<div class="header_bar container-fluid fixed-top">
    <div class="row w-100 d-flex  justify-content-between ">
        <div class="col-auto col-menu">
            <button class="hamburger_button" id="hamburger_button" type="button" data-bs-toggle="collapse"
                aria-expanded="false" data-bs-target="#collapsableMenu" aria-controls="collapsableMenu">
<%--                <svg xmlns="http://www.w3.org/2000/svg" width="38" height="38" fill="currentColor"--%>
<%--                     class="bi bi-list" viewBox="0 0 16 16">--%>
<%--                    <path fill-rule="evenodd"--%>
<%--                          d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>--%>
<%--                </svg>--%>

                <svg fill="currentColor"  id="Capa_1" xmlns="http://www.w3.org/2000/svg"  width="38" height="38" viewBox="0 0 142 142" xml:space="preserve"><g id="SVGRepo_bgCarrier" stroke-width="10"></g>
                    <g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g>
                    <g id="SVGRepo_iconCarrier">
                    <g> <path d="M112,6H12C5.4,6,0,11.4,0,18s5.4,12,12,12h100c6.6,0,12-5.4,12-12S118.6,6,112,6z"></path>
                        <path d="M112,50H12C5.4,50,0,55.4,0,62c0,6.6,5.4,12,12,12h100c6.6,0,12-5.4,12-12C124,55.4,118.6,50,112,50z"></path>
                        <path d="M112,94H12c-6.6,0-12,5.4-12,12s5.4,12,12,12h100c6.6,0,12-5.4,12-12S118.6,94,112,94z"></path>
                    </g>
                  </g>
                </svg>
            </button>
        </div>
        <div class="col-auto">
            <p>ENTER LOGO HERE</p>
        </div>
        <div class="col-auto">
            <p>ROLE</p>
        </div>
    </div>
</div>


<div class="hamburger-menu mt-0 collapse" id="collapsableMenu">
    <h2>All products</h2>
    <%--TODO:form when clicking search button for category--%>
    <form action="dunno_yet.jsp" method="POST">
        <div class="search_bar" tabindex="-1">
            <input type="search" id="search" placeholder="Search...">
            <button type="submit" id="heart_search">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                     class="bi bi-search-heart-fill" viewBox="0 0 16 16">
                    <path d="M6.5 13a6.474 6.474 0 0 0 3.845-1.258h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.008 1.008 0 0 0-.115-.1A6.471 6.471 0 0 0 13 6.5 6.502 6.502 0 0 0 6.5 0a6.5 6.5 0 1 0 0 13Zm0-8.518c1.664-1.673 5.825 1.254 0 5.018-5.825-3.764-1.664-6.69 0-5.018Z"/>
                </svg>
            </button>
        </div>

    </form>
    <button type="button" id="close_btn" data-bs-toggle="collapse" data-bs-target="#collapsableMenu"
            aria-expanded="false" aria-controls="collapsableMenu">
        <i class="closeIcon material-icons">close</i>
    </button>
    <h4>Categories</h4>
    <ul>
        <li><a class="hambMenu_item" href="#">Category1</a></li>
        <li><a class="hambMenu_item" href="#">Category2</a></li>
        <li><a class="hambMenu_item" href="#">Category3</a></li>
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

