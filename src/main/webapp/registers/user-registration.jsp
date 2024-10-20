<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Cadastro de usuário</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="../resources/css/bootstrap.css">
</head>
<body>
<%@include file="../WEB-INF/header.jsp" %>
<div class="container">
  <div class="mt-5 ms-5 me-5">
    <div class="card mb-3">
      <div class="card-header">
        CADASTRO DE USUÀRIO
      </div>
      <div class="card-body">
        <form action="user" method="post">
          <div class="form-group">
            <label for="id-name">Nome</label>
            <input type="text" name="name" id="id-name" class="form-control">
          </div>
          <div class="form-group">
            <label for="id-email">Email</label>
            <input type="text" name="email" id="id-email" class="form-control">
          </div>
          <div class="form-group">
            <label for="id-gender">Genêro</label>
            <input type="text" name="gender" id="id-gender" class="form-control">
          </div>
          <div class="form-group">
            <label for="id-birthdate">Data de Aniversário</label>
            <input type="date" name="birthdate" id="id-birthdate" class="form-control">
          </div>
          <input type="submit" value="Salvar" class="btn btn-primary mt-3">
        </form>
      </div>
    </div>
  </div>
</div>
<%@include file="../WEB-INF/footer.jsp" %>
<script src="../resources/js/bootstrap.bundle.js"></script>
</body>
</html>