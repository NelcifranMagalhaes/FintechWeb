<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Fintech</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./resources/css/bootstrap.css">
</head>
<body>
<%@include file="WEB-INF/header.jsp"%>
<div class="container">
    <div class="container">
        <c:if test="${empty userEmail}">
            <h1 class="mt-3">Você não está autenticado!</h1>
            <h5>Para acessar o sistema, identifique-se!</h5>
        </c:if>
        <c:if test="${not empty userEmail}">
            <h1 class="mt-3">Bem-vindo a FiapStore!</h1>
            <h5>Seu login foi efetuado com sucesso!</h5>
        </c:if>
    </div>
</div>
<%@include file="WEB-INF/footer.jsp"%>
<script src="./resources/js/bootstrap.bundle.js"></script>
</body>
</html>