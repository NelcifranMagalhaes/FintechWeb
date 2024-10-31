<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
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
    <div class="mt-5 ms-5 me-5">

        <div class="card mb-3">
            <div class="card-header">
                LISTA DE DESPESAS
            </div>
            <div class="card-body">
                <h5 class="card-title">Gestão de despesas</h5>
                <p class="card-text">Cadastre suas receitas e elas estarão aqui.</p>
                <table class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>Descrição</th>
                        <th>Categoria</th>
                        <th class="text-end">Valor</th>
                        <th class="text-center">Data de Criação</th>
                        <th class="text-center"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${moneyOuts}" var="moneyOut">
                        <tr>
                            <td>${moneyOut.description}</td>
                            <td>${moneyOut.category}</td>
                            <td class="text-end">
                                <fmt:formatNumber value="${moneyOut.value}" currencySymbol="R$" type="currency"/>
                            </td>
                            <td class="text-center">
                                <fmt:parseDate
                                        value="${moneyOut.createdAt}"
                                        pattern="yyyy-MM-dd"
                                        var="createdAtFmt"/>
                                <fmt:formatDate
                                        value="${createdAtFmt}"
                                        pattern="dd/MM/yyyy"/>
                            </td>
                            <td class="text-center">
                                <c:url value="moneyOuts" var="link">
                                    <c:param name="action" value="open-edit-form" />
                                    <c:param name="id" value="${moneyOut.id}" />
                                </c:url>
                                <a href="${link}" class="btn btn-primary"> Editar</a>
                                <!-- Botão para abrir a modal -->
                                <button
                                        type="button"
                                        class="btn btn-danger"
                                        data-bs-toggle="modal"
                                        data-bs-target="#excluirModal"
                                        onclick="deleteCode.value = ${moneyOut.id}"
                                >
                                    Excluir
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <a href="money-out-registration.jsp" class="btn btn-primary">Adicionar Receita</a>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<div
        class="modal fade"
        id="excluirModal"
        tabindex="-1"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1
                        class="modal-title fs-5"
                        id="exampleModalLabel">
                    Confirmar Exclusão
                </h1>
                <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close">
                </button>
            </div>
            <div class="modal-body">
                <h4>Você confirma a exclusão desta Despesa?</h4>
                <p><strong>Atenção!</strong> Esta ação é irreversível.</p>
            </div>
            <div class="modal-footer">

                <form action="moneyOuts?action=delete" method="post">
                    <input
                            type="hidden"
                            name="action"
                            value="delete">
                    <input
                            type="hidden"
                            name="deleteCode"
                            id="deleteCode">
                    <button
                            type="button"
                            class="btn btn-secondary"
                            data-bs-dismiss="modal">
                        Não
                    </button>
                    <button
                            type="submit"
                            class="btn btn-danger">
                        Sim
                    </button>
                </form>

            </div>
        </div>
    </div>
</div>
<%--    fim modal--%>
<script src="./resources/js/bootstrap.bundle.js"></script>
</body>
</html>