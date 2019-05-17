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

<div style="margin-left:250px">

	<h1 style="text-align: center">Add new offer</h1>

	<c:choose>
		<c:when test="${sessionScope.user == null}">
			Log first if you want to add new offer!
		</c:when>
		<c:otherwise>
			<div class="w3-container w3-padding-16 w3-margin w3-col w3-card-4 w3-display-middle w3-theme-15" style="display: block; overflow: auto; height: 80%; width:30%">
				<form:form method="post" modelAttribute="flat" enctype="multipart/form-data">
					<div class="w3-container w3-padding-16">
						<label>Name:</label>
						<form:input path="name" class="w3-input w3-theme-15"/>
						<form:errors path="name" cssClass="w3-text-red" element="div"/>
					</div>
					<div class="w3-container w3-padding-16">
						<label>Voivodeship:</label>
						<form:select path="voivodeship" items="${voivodeship}" class="w3-select w3-theme-15"/>
					</div>
					<div class="w3-container w3-padding-16">
						<label>Post Code:</label>
						<form:input path="postCode" class="w3-input w3-theme-15"/>
						<form:errors path="postCode" cssClass="w3-text-red" element="div"/>
					</div>
					<div class="w3-container w3-padding-16">
						<label>City:</label>
						<form:input path="city" class="w3-input w3-theme-15"/>
						<form:errors path="city" cssClass="w3-text-red" element="div"/>
					</div>
					<div class="w3-container w3-padding-16">
						<label>Street:</label>
						<form:input path="street" class="w3-input w3-theme-15"/>
						<form:errors path="street" cssClass="w3-text-red" element="div"/>
					</div>
					<div class="w3-container w3-padding-16">
						<label>Type:</label>
						<form:select path="typeOfFlat" items="${typeOfFlat}" class="w3-input w3-theme-15"/>
					</div>
					<div class="w3-container w3-padding-16">
						<label>Surface(m<sup>2</sup>):</label>
						<form:input path="surface" class="w3-input w3-theme-15"/>
					</div>
					<div class="w3-container w3-padding-16">
						<label>Price:(PLN/day)</label>
						<form:input path="price" class="w3-input w3-theme-15"/>
					</div>
					<div class="w3-container w3-padding-16">
						<label>Maximum number of guests:</label>
						<form:input path="numberOfGuests" class="w3-input w3-theme-15"/>
					</div>
					<div class="w3-container w3-padding-16">
						<label>Description:</label>
						<form:textarea path="description" rows="10" cols="50"/>
					</div>
					<div class="w3-container w3-padding-16">
						<label>Upload photo:</label>
						<input type="file" name="photo">
					</div>
					<div class="w3-center w3-section">
						<input type="submit" value="Add offer" class="w3-btn w3-theme w3-large w3-margin" style="width: 30%">
					</div>
				</form:form>
			</div>
		</c:otherwise>
	</c:choose>

	<!-- END MAIN -->
<%--	<%@ include file = "jspf/footer.jspf" %>--%>
</div>
</body>
</html>