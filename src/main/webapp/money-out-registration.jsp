<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cadastro de Despesas</title>
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
                CADASTRO DE DESPESAS
            </div>
            <c:if test="${not empty message}">
                <div class="alert alert-success ms-2 me-2 m-auto mt-2">${message}</div>
            </c:if>

            <c:if test="${not empty error}">
                <div class="alert alert-danger ms-2 me-2 m-auto mt-2">${error}</div>
            </c:if>
            <div class="card-body">
                <form action="moneyOuts?action=create" method="post">
                    <div class="form-group">
                        <label for="id-label">Descrição</label>
                        <input type="text" name="description" id="id-label" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="id-value">Valor</label>
                        <input type="number" name="value" id="id-value" step="any" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="id-category">Categoria</label>
                        <select class="form-select form-control" aria-label="Default select example" name="category" id="id-category">
                            <option selected value="">Selecione a Categoria</option>
                            <option value="diversão">Diversão</option>
                            <option value="alimentação">Alimentação</option>
                            <option value="moradia">Moradia</option>
                            <option value="saúde">Saúde</option>
                            <option value="transporte">Transporte</option>
                            <option value="outro">Outro</option>
                        </select>
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