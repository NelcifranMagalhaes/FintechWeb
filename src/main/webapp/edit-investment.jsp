<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edição de Investimentos</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="resources/css/bootstrap.css">
    <link rel="icon" type="ico" sizes="16x16" href="resources/assets/favicon.ico" >
</head>
<body>
<%@include file="WEB-INF/header.jsp" %>
<div class="container">
    <div class="mt-5 ms-5 me-5">
        <div class="card mb-3">
            <div class="card-header">
                ATUALIZAR INVESTIMENTOS
            </div>
            <c:if test="${not empty message}">
                <div class="alert alert-success ms-2 me-2 m-auto mt-2">${message}</div>
            </c:if>

            <c:if test="${not empty error}">
                <div class="alert alert-danger ms-2 me-2 m-auto mt-2">${error}</div>
            </c:if>
            <div class="card-body">
                <form action="investments?action=update" method="post">

                    <input type="hidden" value="${investment.id}" name="id">

                    <div class="form-group">
                        <label for="id-description">Descrição</label>
                        <input type="text" name="description" id="id-description" class="form-control" value="${investment.description}">
                    </div>
                    <div class="form-group">
                        <label for="id-value">Valor</label>
                        <input type="number" name="value" id="id-value" step="any" class="form-control"  value="${investment.value}">
                    </div>
                    <input type="submit" value="Salvar" class="btn btn-primary mt-3">
                </form>
            </div>
        </div>
    </div>
</div>
<%@include file="WEB-INF/footer.jsp" %>
<script src="resources/js/bootstrap.bundle.js"></script>
</body>
</html>