<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value='${sessionScope["lang"]}'/>
<fmt:setBundle basename="messages"/>
<c:set value='${sessionScope["user"]}' var="user"/>
<c:if test="${empty user}">
    <c:redirect url="/controller?page=login.jsp"/>
</c:if>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/global.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/header.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/films.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/modal.css">
    <title><fmt:message key="films_header"/></title>
</head>
<body>
<div class="wrapper">
    <%@ include file="/header.jsp" %>
    <main class="content">
        <div class="content-top">
            <c:if test="${user.getRole() == 'admin'}">
                <button class="add" onclick="openAddFilmModal()">
                    <span>+</span>
                    <fmt:message key="add_film"/>
                </button>
            </c:if>
        </div>
        <div class="films">
            <c:set value='${requestScope["filmsList"]}' var="filmsList"/>
            <c:forEach var="film" items="${filmsList}">
                <div class="film">
                    <a href="<c:url value="/controller?command=get_film&filmName=${film.getName()}"/>">
                        <img src="${film.getImgUrl()}" alt="${film.getName()}" class="poster">
                        <div class="rating">${film.getRating()}</div>
                        <div class="name">${film.getName()}</div>
                    </a>
                    <c:if test="${user.getRole() == 'admin'}">
                        <button class="add edit"
                                onclick="openEditFilmModal(`${film.getId()}`, `${film.getName()}`, `${film.getDescription()}`, `${film.getImgUrl()}`)">
                            <fmt:message key="edit_film"/>
                        </button>
                    </c:if>
                </div>
            </c:forEach>
        </div>
        <div class="modal-wrapper" id="addFilmModal">
            <div class="modal-form">
                <h2 class="modal-header"><fmt:message key="add_film_header"/></h2>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="add_film">

                    <label for="addFilmTitle"><fmt:message key="film_title"/></label>
                    <input type="text" name="filmTitle" id="addFilmTitle" required>

                    <label for="addFilmDescription"><fmt:message key="film_description"/></label>
                    <textarea name="filmDescription" id="addFilmDescription" required></textarea>

                    <label for="addImgUrl"><fmt:message key="film_image_url"/></label>
                    <input type="text" name="imgUrl" id="addImgUrl" required>

                    <button class="modal-submit" type="submit">
                        <fmt:message key="film_add_btn"/>
                    </button>
                </form>
            </div>
        </div>
        <div class="modal-wrapper" id="editFilmModal">
            <div class="modal-form">
                <h2 class="modal-header"><fmt:message key="edit_film_header"/></h2>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="edit_film">

                    <input type="hidden" name="filmId" id="editFilmId" value="">
                    <label for="editFilmTitle"><fmt:message key="film_title"/></label>
                    <input type="text" name="filmTitle" id="editFilmTitle" required>

                    <label for="editFilmDescription"><fmt:message key="film_description"/></label>
                    <textarea name="filmDescription" id="editFilmDescription" required></textarea>

                    <label for="editFilmImgUrl"><fmt:message key="film_image_url"/></label>
                    <input type="text" name="filmImgUrl" id="editFilmImgUrl" required>

                    <button class="modal-submit" type="submit">
                        <fmt:message key="film_edit_btn"/>
                    </button>
                </form>
            </div>
        </div>
    </main>
</div>
<script>
    openAddFilmModal = () => {
        const modal = document.getElementById("addFilmModal");
        modal.style.display = "flex";
    }

    openEditFilmModal = (filmId, filmTitle, filmDescription, imgUrl) => {
        const modal = document.getElementById("editFilmModal");
        modal.style.display = "flex";
        document.getElementById("editFilmId").value = filmId;
        document.getElementById("editFilmTitle").value = filmTitle;
        document.getElementById("editFilmDescription").value = filmDescription;
        document.getElementById("editFilmImgUrl").value = imgUrl;
    }

    window.onclick = (event) => {
        const addModal = document.getElementById("addFilmModal");
        const editModal = document.getElementById("editFilmModal");
        if (event.target === addModal) {
            addModal.style.display = "none";
        }
        if (event.target === editModal) {
            editModal.style.display = "none";
        }
    }
</script>
</body>
</html>
