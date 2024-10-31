<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edição de receita</title>
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
                ATUALIZAR RECEITA
            </div>
            <c:if test="${not empty message}">
                <div class="alert alert-success ms-2 me-2 m-auto mt-2">${message}</div>
            </c:if>

            <c:if test="${not empty error}">
                <div class="alert alert-danger ms-2 me-2 m-auto mt-2">${error}</div>
            </c:if>
            <div class="card-body">
                <form action="moneyOuts?action=update" method="post">

                    <input type="hidden" value="${moneyOut.id}" name="id">

                    <div class="form-group">
                        <label for="id-description">Descrição</label>
                        <input type="text" name="description" id="id-description" class="form-control" value="${moneyOut.description}">
                    </div>
                    <div class="form-group">
                        <label for="id-value">Valor</label>
                        <input type="number" name="value" id="id-value" step="any" class="form-control"  value="${moneyOut.value}">
                    </div>
                    <div class="form-group">
                        <label for="id-category">Categoria</label>
                        <select class="form-select form-control" aria-label="Default select example" name="category" id="id-category">
                            <option selected value="" disabled ${moneyOut.category == null ? 'selected' : ''}>Selecione a Categoria</option>
                            <option value="diversão" ${moneyOut.category == 'diversão' ? 'selected' : ''}>Diversão</option>
                            <option value="alimentação" ${moneyOut.category == 'alimentação' ? 'selected' : ''}>Alimentação</option>
                            <option value="moradia" ${moneyOut.category == 'moradia' ? 'selected' : ''}>Moradia</option>
                            <option value="saúde" ${moneyOut.category == 'saúde' ? 'selected' : ''}>Saúde</option>
                            <option value="transporte" ${moneyOut.category == 'transporte' ? 'selected' : ''}>Transporte</option>
                            <option value="outro" ${moneyOut.category == 'outro' ? 'selected' : ''}>Outro</option>
                        </select>
                    </div>
                    <input type="submit" value="Salvar" class="btn btn-primary mt-3">
                </form>
            </div>
        </div>
    </div>
</div>
<script src="resources/js/bootstrap.bundle.js"></script>
</body>
</html>