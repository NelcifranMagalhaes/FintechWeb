<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
  <title>Edição de usuário</title>
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
        ATUALIZAR USUÁRIO
      </div>
      <c:if test="${not empty message}">
        <div class="alert alert-success ms-2 me-2 m-auto mt-2">${message}</div>
      </c:if>

      <c:if test="${not empty error}">
        <div class="alert alert-danger ms-2 me-2 m-auto mt-2">${error}</div>
      </c:if>
      <div class="card-body">
        <form action="users?action=update" method="post">

          <input type="hidden" value="${fintechUser.id}" name="id">

          <div class="form-group">
            <label for="id-name">Nome</label>
            <input type="text" name="name" id="id-name" required class="form-control" value="${fintechUser.name}">
          </div>
          <div class="form-group">
            <label for="id-email">Email</label>
            <input type="text" name="email" id="id-email" required class="form-control" value="${fintechUser.email}">
          </div>
          <div class="form-group">
            <label for="id-gender">Genêro</label>
            <select class="form-select form-control" aria-label="Default select example" name="gender" id="id-gender">
              <option value="" disabled ${fintechUser.gender == null ? 'selected' : ''}>Selecione seu Gênero</option>
              <option value="F" ${fintechUser.gender == 'F' ? 'selected' : ''}>Feminino</option>
              <option value="M" ${fintechUser.gender == 'M' ? 'selected' : ''}>Masculino</option>
              <option value="O" ${fintechUser.gender == 'O' ? 'selected' : ''}>Outro</option>
            </select>
          </div>
          <div class="form-group">
            <label for="id-birthdate">Data de Aniversário</label>
            <c:set var="formattedBirthDate" value="${fn:substring(fintechUser.birthDate, 0, 10)}" />
            <input type="date" name="birthdate" id="id-birthdate" required class="form-control" value="${formattedBirthDate}">
          </div>
          <div class="form-group">
            <label for="id-password">Senha</label>
            <input type="password" name="password" id="id-password" required class="form-control" value="${fintechUser.passwordHash}">
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