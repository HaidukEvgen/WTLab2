<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value='${sessionScope["lang"]}'/>
<fmt:setBundle basename="messages"/>
<c:set value='${requestScope["error"]}' var="error"/>
<c:set value='${requestScope["message"]}' var="message"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/global.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/auth.css">
    <title><fmt:message key="register_header"/></title>
</head>
<body>
<div class="wrapper">
    <form action="controller" method="post" class="form">
        <input type="hidden" name="command" value="do_register">
        <h1 class="header"><fmt:message key="register_header"/></h1>
        <div class="element">
            <label for="name"><fmt:message key="full_name"/></label>
            <input type="text" id="name" name="name" placeholder="User Name" required/>
        </div>
        <div class="element">
            <label for="email"><fmt:message key="email"/></label>
            <input type="email" id="email" name="email" placeholder="example@somewhere.any" required/>
        </div>
        <div class="element">
            <label for="password"><fmt:message key="password"/></label>
            <input type="password" id="password" name="password" placeholder="strongpassword123" required/>
        </div>
        <div class="element">
            <label for="re-password"><fmt:message key="repeat_password"/></label>
            <input type="password" id="re-password" name="re-password" placeholder="strongpassword123" required/>
        </div>
        <div class="error">${error}</div>
        <div class="success">${message}</div>
        <button type="submit" class="submit">
            <fmt:message key="create_account"/>
        </button>
        <div class="link">
            <span><fmt:message key="already_have_account"/></span>
            <a href="<c:url value="/controller?page=login.jsp"/>">
                <fmt:message key="sing_in"/>
            </a>
        </div>
    </form>
</div>
</body>
</html>
