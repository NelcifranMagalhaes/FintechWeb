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
                LISTA DE USUÁRIOS
            </div>
            <div class="card-body">
                <h5 class="card-title">Gestão de Usuários</h5>
                <p class="card-text">Mantenha os dados do seu usuário sempre atualizado.</p>
                <table class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>Nome</th>
                        <th class="text-end">Email</th>
                        <th class="text-end">Gênero</th>
                        <th class="text-center">Data de Nascimento</th>
                        <th class="text-center">Data de Criação</th>
                        <th class="text-center"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${fintechUsers}" var="user">
                        <tr>
                            <td>${user.name}</td>
                            <td class="text-end">${user.email}</td>
                            <td class="text-end">${user.gender}</td>
                            <td class="text-center">
                                <fmt:parseDate
                                        value="${user.birthDate}"
                                        pattern="yyyy-MM-dd"
                                        var="birthDateFmt"/>
                                <fmt:formatDate
                                        value="${birthDateFmt}"
                                        pattern="dd/MM/yyyy"/>
                            </td>
                            <td class="text-center">
                                <fmt:parseDate
                                        value="${user.createdAt}"
                                        pattern="yyyy-MM-dd"
                                        var="createdAtFmt"/>
                                <fmt:formatDate
                                        value="${createdAtFmt}"
                                        pattern="dd/MM/yyyy"/>
                            </td>
                            <td class="text-center">
                                <c:url value="users" var="link">
                                    <c:param name="action" value="open-edit-form" />
                                    <c:param name="id" value="${user.id}" />
                                </c:url>
                                <a href="${link}" class="btn btn-primary"> Editar</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <a href="user-registration.jsp" class="btn btn-primary">Adicionar usuário</a>
            </div>
        </div>
    </div>
</div>
<%@include file="WEB-INF/footer.jsp"%>
<script src="./resources/js/bootstrap.bundle.js"></script>
</body>
</html>