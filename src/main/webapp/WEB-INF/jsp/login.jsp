<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value='${sessionScope["lang"]}'/>
<fmt:setBundle basename="messages"/>
<c:set value='${requestScope["loginFlag"]}' var="loginFlag"/>
<c:if test="${not empty loginFlag}">
    <c:redirect url="/controller?command=get_films"/>
</c:if>
<c:set value='${requestScope["error"]}' var="error"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/global.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/auth.css">
    <title><fmt:message key="login_header"/></title>
</head>
<body>
<div class="wrapper">
    <form action="controller" method="post" class="form">
        <input type="hidden" name="command" value="do_login">
        <h1 class="header"><fmt:message key="login_header"/></h1>
        <div class="element">
            <label for="email"><fmt:message key="email"/></label>
            <input type="email" id="email" name="email" placeholder="example@somewhere.any" required/>
        </div>
        <div class="element">
            <label for="password"><fmt:message key="password"/></label>
            <input type="password" id="password" name="password" placeholder="strongpassword123" required/>
        </div>
        <div class="error">${error}</div>
        <button type="submit" class="submit">
            <fmt:message key="login_button"/>
        </button>
        <div class="link">
            <span><fmt:message key="no_account"/></span>
            <a href="<c:url value="/controller?page=registration.jsp"/>" class="link">
                <fmt:message key="sign_up"/>
            </a>
        </div>
    </form>
</div>
</body>
</html>
