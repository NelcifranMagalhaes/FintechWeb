<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="index">Fintech</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link" href="users?action=list-users">Usuários</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="moneyIns?action=list-moneyIns">Receitas</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="moneyOuts?action=list-moneyOuts">Despesas</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="investments?action=list-investments">Investimentos</a>
        </li>
      </ul>
      <c:if test="${empty userEmail}">
        <span class="navbar-text text-danger" style="margin-right:10px">
          <a href="./login.jsp" class="btn btn-outline-primary my-2 my-sm-0">Faça Login</a>
            ${error}
        </span>
      </c:if>
      <c:if test="${not empty userEmail }">
        <span class="navbar-text">
            ${userEmail}
            <a href="login" class="btn btn-outline-primary my-2 my-sm-0">Sair</a>
        </span>
      </c:if>
    </div>
  </div>
</nav>