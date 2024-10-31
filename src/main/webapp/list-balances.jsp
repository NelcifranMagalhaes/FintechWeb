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
    <div class="card text-white bg-info mb-3" style="max-width: 18rem;">
      <div class="card-header">Total</div>
      <div class="card-body">
        <h5 class="card-title"><fmt:formatNumber value="${totalValue}" currencySymbol="R$" type="currency"/> </h5>
      </div>
    </div>
    <div class="card mb-3">
      <div class="card-body">
        <table class="table table-striped table-bordered">
          <thead>
          <tr>
            <th class="text-end">Valor</th>
            <th class="text-center">Data de Criação</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${balances}" var="balance">
            <tr>
              <td class="text-end">
                <fmt:formatNumber value="${balance.value}" currencySymbol="R$" type="currency"/>
              </td>
              <td class="text-center">
                <fmt:parseDate
                        value="${balance.createdAt}"
                        pattern="yyyy-MM-dd"
                        var="createdAtFmt"/>
                <fmt:formatDate
                        value="${createdAtFmt}"
                        pattern="dd/MM/yyyy"/>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>
<script src="./resources/js/bootstrap.bundle.js"></script>
</body>
</html>