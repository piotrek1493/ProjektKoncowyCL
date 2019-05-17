<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <%@ include file = "jspf/head_config.jspf" %>
</head>
<body>
<%@ include file = "jspf/header.jspf" %>
<%@ include file = "jspf/main_menu.jspf" %>
<div class="w3-main" style="margin-left:250px">

    <c:choose>
        <c:when test="${sessionScope.user == null}">
            Please log in first!
        </c:when>
        <c:otherwise>
            <%@ include file = "jspf/user_page.jspf" %>
        </c:otherwise>
    </c:choose>

</div>
</body>
</html>