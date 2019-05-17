<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <%@ include file="jspf/head_config.jspf" %>
</head>
<body>
<%@ include file="jspf/header.jspf" %>
<%@ include file="jspf/main_menu.jspf" %>
<br><br>
<div class="w3-main" style="margin-left:250px">

    <c:choose>
        <c:when test="${sessionScope.user == null}">
            Log first if you want to check offers!
        </c:when>
        <c:otherwise>
            <div>
                <p class="flatName">${flat.name}</p>
                    ${msg}
                <p>Localization: <b>${flat.postCode} ${flat.city}</b>, ulica: <b>${flat.street}</b>, wojewodztwo
                    <b>${flat.voivodeship}</b>
                </p>
                <p>
                    Type of Flat: <b>${flat.typeOfFlat}</b>
                </p>
                <p>
                    Surface: <b>${flat.surface}</b>m<sup>2</sup>
                </p>
                <p>
                    Price: <b>${flat.price}</b> PLN/day
                </p>
                <p>
                    Max number of guests: <b>${flat.numberOfGuests}</b>
                </p>
                <p>
                    Description: <b>${flat.description}</b>
                </p>
                <p>
                    Author: <b>${flat.user.userName}</b>
                </p>
                <form action="/userFlats/${flat.user.id}">
                    <button type="submit" class="btn btn-primary btn-lg">
                        Check all offers
                    </button>
                </form>

                <c:if test="${user.userName != flat.user.userName}">
                    <p>
                        <a href="/message/${flat.user.id}" class="btn btn-info btn-lg">
                            <span class="glyphicon glyphicon-envelope"></span> Send message
                        </a>
                    </p>
                </c:if>

                <p>Contact: ${flat.user.email}</p>

                <div style="width: auto; height: auto;">
                    <c:forEach items="${photos}" var="photo">
                        <img src="//localhost:8080/resources/picture/${photo.url}" width="300" height="250"/>
                    </c:forEach>
                </div>
            </div>
            <c:if test="${user.userName eq flat.user.userName}">
                <form action="/add/photos/${flat.id}">
                    <button type="submit" class="btn">Add new photos</button>
                </form>
                <form action="/flat/edit/${flat.id}">
                    <button type="submit" class="btn btn-primary">Edit offer</button>
                </form>
                <form action="/flat/delete/${flat.id}">
                    <button type="submit" class="btn btn-danger">Delete offer</button>
                </form>
            </c:if>
            <div>
                <p class="comments" style="text-align: left; font-size: x-large">
                    <b>Comments</b>
                </p>
                <c:forEach items="${comments}" var="comment">
                    <span>
                        <a href="/userFlats/${comment.user.id}">${comment.user.userName}</a>
                    </span>
                    <span>
                            ${comment.created}:<p class="speech-bubble">${comment.text}</p>
                    </span>
                </c:forEach>
            </div>
            <form:form action="addComment/${flat.id}" method="post" modelAttribute="comment">
                <label>Add comment:</label><br>
                <form:textarea rows="10" cols="50" path="text"/>
                <form:errors path="text"></form:errors><br>
                <input type="submit" value="Add comment"/>
            </form:form>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
