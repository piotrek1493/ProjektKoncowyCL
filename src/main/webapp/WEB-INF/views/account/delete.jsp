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

<div class="w3-main" style="margin-left:250px">

	<c:choose>

		<c:when test="${sessionScope.user == null}">
			You need to be logged in, in order to edit user details.
		</c:when>

		<c:otherwise>
			<h2 style="text-align: center">Are you sure you want to delete your account?</h2><br><br>
			<div class="w3-container w3-padding-16 w3-margin w3-col w3-card-4 w3-display-middle w3-theme-15" style="width: 30%">
				<div class="w3-center w3-section">
					<form action="/delete/1"><button type="submit" class="w3-btn w3-large w3-margin" style="color: red; background-color: black; width: 50%">Yes, delete my account</button></form>
				</div>
				<hr>
				<div class="w3-center w3-section">
					<form action="/delete/0"><button type="submit" class="w3-btn w3-large w3-margin" style="color: white; background-color: black; width: 50%">No, return me<br>to homepage</button></form>
				</div>
			</div>
			<%--<form action="/delete/1"><button type="submit">Yes, delete my account</button></form>--%>
			<%--<form action="/delete/0"><button type="submit">No, that was a mistake</button></form>--%>
		</c:otherwise>

	</c:choose>

	<!-- END MAIN -->
<%--	<%@ include file = "../jspf/footer.jspf" %>--%>
</div>
</body>
</html>