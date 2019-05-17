<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <%@ include file = "jspf/head_config.jspf" %>
</head>
<body>
<div id="container">

    <div id="header">
        <%@ include file = "jspf/header.jspf" %>
    </div>

    <div id="body">
        <br>
        <%@ include file = "jspf/main_menu.jspf" %>
        <div class="w3-main" style="margin-left:250px">

            <%@ include file = "jspf/search_form.jspf" %>
            <%@ include file = "jspf/main_page.jspf" %>
            <!-- END MAIN -->
        </div>
    </div>

    <div id="footer"
        <%@ include file = "jspf/footer.jspf" %>
    </div>
</div>
</body>
</html>