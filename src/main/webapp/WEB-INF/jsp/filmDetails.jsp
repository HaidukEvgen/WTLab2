<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value='${sessionScope["lang"]}'/>
<fmt:setBundle basename="messages"/>
<c:set value='${sessionScope["user"]}' var="user"/>
<c:if test="${empty user}">
    <c:redirect url="/controller?page=login.jsp"/>
</c:if>
<c:set value='${requestScope["film"]}' var="film"/>
<c:set value='${requestScope["isButtonDisabled"]}' var="isDisabled"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/global.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/header.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/filmDetails.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/modal.css">
    <title>${film.getName()}</title>
</head>
<body>
<div class="wrapper">
    <%@ include file="/header.jsp" %>
    <main class="content">
        <a class="back" href="<c:url value="/controller?command=get_films"/>"><</a>
        <div class="film-details">
            <div class="film-details-left">
                <img src="${film.getImgUrl()}" alt="${film.getName()}" class="poster">
                <div class="btn-wrapper">
                    <c:choose>
                        <c:when test="${isDisabled}">
                            <button class="btn-add-review" disabled="disabled">
                                <span>+</span>
                                <<fmt:message key="film_add_review"/>
                            </button>
                        </c:when>
                        <c:otherwise>
                            <button class="btn-add-review" onclick="openReviewModal()">
                                <span>+</span>
                                <fmt:message key="film_add_review"/>
                            </button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="film-details-right">
                <div class="film-name">
                    ${film.getName()}
                    <span class="film-rating">${film.getRating()}</span>
                </div>
                <div class="film-description">${film.getDescription()}</div>
                <div class="film-reviews">
                    <h2 class="reviews-header"><fmt:message key="reviews_header"/></h2>
                    <c:set value='${requestScope["reviews"]}' var="reviews"/>
                    <c:forEach var="review" items="${reviews}">
                        <div class="review">
                            <div class="review-top">
                                <p class="user">${review.getUserName()}</p>
                                <p class="user-rating ${review.getUserRating() >= 0 ? "green" : "red"}">
                                        ${review.getUserRating() > 0 ? '+' : ''}${review.getUserRating()}
                                </p>
                                <p class="review-rating">${review.getFilmRating()}</p>
                                <c:if test="${user.getName() == review.getUserName()}">
                                    <form action="controller" method="post" style="margin-left: auto">
                                        <input type="hidden" name="command" value="delete_review">
                                        <input type="hidden" name="filmName" value="${film.getName()}">
                                        <input type="hidden" name="reviewId" value="${review.getId()}">
                                        <button class="delete-button"><fmt:message key="review_delete"/></button>
                                    </form>
                                </c:if>

                            </div>
                            <p class="text">${review.getText()}</p>
                        </div>
                    </c:forEach>
                </div>
                <div class="modal-wrapper" id="reviewModal">
                    <div class="modal-form">
                        <h2 class="modal-header"><fmt:message key="film_add_review"/></h2>
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="add_review">
                            <input type="hidden" name="filmName" value="${film.getName()}">
                            <label for="userRating"><fmt:message key="review_rating"/></label>
                            <input
                                    id="userRating"
                                    type="number"
                                    name="userRating"
                                    value="10"
                                    min="1"
                                    max="10"
                                    step="0.1"
                                    required
                            >
                            <label for="reviewText"><fmt:message key="review_review"/></label>
                            <textarea name="reviewText" id="reviewText" required></textarea>
                            <button class="modal-submit" type="submit"><fmt:message key="review_submit"/></button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </main>
</div>
<script>
    openReviewModal = () => {
        const modal = document.getElementById("reviewModal");
        modal.style.display = "flex";
    }

    window.onclick = (event) => {
        const modal = document.getElementById("reviewModal");
        if (event.target === modal) {
            const userRatingInput = document.getElementById("userRating");
            const reviewTextInput = document.getElementById("reviewText");
            userRatingInput.value = 10;
            reviewTextInput.value = '';
            modal.style.display = "none";
        }
    }
</script>
</body>
</html>
