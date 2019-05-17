<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<%@ include file = "../jspf/head_config.jspf" %>
</head>
<body>
<%@ include file = "../jspf/header.jspf" %>
<%@ include file = "../jspf/main_menu.jspf" %>

<div class="w3-container w3-center w3-theme1">
	<h1 class="w3-text-teal">Log in</h1>
</div>
<h2 class="error" style="text-align: center; font-weight: bold">${msg}</h2>

<div class="w3-container w3-padding-16 w3-margin w3-col w3-card-4 w3-display-middle w3-theme-15" style="width:30%">
	<form:form method="post" modelAttribute="loginData">
		<div class="w3-container w3-padding-16">
			<label>User:</label>
			<form:input type="text" path="userName" class="w3-input w3-theme-15"/>
			<form:errors path="userName" cssClass="w3-text-red" element="div"/>
		</div>
		<div class="w3-container w3-padding-16">
			<label>Password:</label>
			<form:input type="password" path="password" class="w3-input w3-theme-15" />
			<form:errors path="password" cssClass="w3-text-red" element="div"/>
		</div>
		<div class="w3-center w3-section">
			<input type="submit" value="Login" class="w3-btn w3-theme w3-large w3-margin" style="width: 30%">
		</div>
		<form:errors></form:errors>
	</form:form>

</div>
<%--<%@ include file = "../jspf/footer.jspf" %>--%>
</body>
</html>