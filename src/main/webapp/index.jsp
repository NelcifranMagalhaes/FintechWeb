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
    <c:forEach items="${moneyOuts}" var="moneyOut">
        <h3>${moneyOut.category}</h3>
        <h3>${moneyOut.total_value}</h3>
    </c:forEach>
    <div class="row mt-5">
        <div>
            <canvas id="myChart"></canvas>
        </div>
        <div class="col-sm-6">
            <div class="card bg-danger-subtle">
                <div class="card-body">
                    <h5 class="card-title">Despesas</h5>
                    <p class="card-text">Controle de Despesas.</p>
                    <a href="moneyOuts?action=list-moneyOuts" class="btn btn-primary">Lista de Despesas</a>
                </div>
            </div>
        </div>
        <div class="col-sm-6">
            <div class="card bg-success-subtle">
                <div class="card-body">
                    <h5 class="card-title">Receitas</h5>
                    <p class="card-text">Controle do Dinheiro entrando.</p>
                    <a href="moneyIns?action=list-moneyIns" class="btn btn-primary">Lista de Receitas</a>
                </div>
            </div>
        </div>
        <div class="col-sm-6 mt-4">
            <div class="card bg-info-subtle">
                <div class="card-body">
                    <h5 class="card-title">Investimentos</h5>
                    <p class="card-text">Invista no seu futuro ou em sonhos.</p>
                    <a href="investments?action=list-investments" class="btn btn-primary">Lista de investimentos</a>
                </div>
            </div>
        </div>
        <div class="col-sm-6 mt-4">
            <div class="card bg-light-subtle">
                <div class="card-body">
                    <h5 class="card-title">Saldo</h5>
                    <p class="card-text">Seu saldo total.</p>
                    <a href="#" class="btn btn-primary">Go somewhere</a>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="WEB-INF/footer.jsp"%>
<script src="./resources/js/bootstrap.bundle.js"></script>
<script src="./resources/js/chart.js"></script>
<script>
    const ctx = document.getElementById('myChart');

    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
            datasets: [{
                label: '# of Votes',
                data: [12, 19, 3, 5, 2, 3],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
</script>
</body>
</html>