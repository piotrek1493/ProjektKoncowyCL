<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="true"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <%@ include file = "jspf/head_config.jspf" %>
<body>
<%@ include file = "jspf/header.jspf" %>
<%@ include file = "jspf/main_menu.jspf" %>

<div class="w3-main" style="margin-left:250px">

    <h1 class="w3-text-teal">Add new offer</h1>

    <c:choose>
        <c:when test="${sessionScope.user == null}">
            Please sign in first to add a new offer
        </c:when>
        <c:otherwise>
            ${eMessage}
            <h1>Multiple File Upload</h1>
            <form:form method="post" modelAttribute="photos" enctype="multipart/form-data">
                <p>
                    Upload Files: <input multiple type="file" name="photo">
                </p>
                <p>
                    <input type="submit" value="Upload"/>
                </p>
            </form:form>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>