<%-- 
    Document   : inicioAdministrador
    Created on : 01/05/2024, 19:04:25
    Author     : paulo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Area do Administrador</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

        <link href="assets/img/favicon.png" rel="icon">
        <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

        <link href="https://fonts.gstatic.com" rel="preconnect">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
        <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
        <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
        <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

        <link href="assets/css/style.css" rel="stylesheet">


    </head>

    <body class="toggle-sidebar">
        <jsp:include page="headerAdministrador.jsp"></jsp:include>


            <main id="main" class="main">

                <div class="pagetitle">
                    <h1>Dashboard</h1>
                    <nav>
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="./inicioAdministrador">Home</a></li>
                            <li class="breadcrumb-item active">Dashboard</li>
                        </ol>
                    </nav>
                </div>

                <section class="section dashboard">
                    <div class="row">

                        <div class="col-lg-8">
                            <div class="row">

                                <div class="col-xxl-4 col-md-6">
                                    <div class="card info-card sales-card">

                                        <div class="card-body">
                                            <h5 class="card-title">Total de Pedidos <span>| Tudo</span></h5>

                                            <div class="d-flex align-items-center">
                                                <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                                                    <i class="bi bi-cart"></i>
                                                </div>
                                                <div class="ps-3">
                                                    <h6>${e.vendas}</h6>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>

                            <div class="col-xxl-4 col-md-6">
                                <div class="card info-card revenue-card">
                                    <div class="card-body">
                                        <h5 class="card-title">Receita <span>| tudo</span></h5>

                                        <div class="d-flex align-items-center">
                                            <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                                                <i class="bi bi-currency-dollar"></i>
                                            </div>
                                            <div class="ps-3">
                                                <h6>$${e.vendido}</h6>
                                                <span class="text-success small pt-1 fw-bold">R$${e.lucro}</span> <span class="text-muted small pt-2 ps-1">lucro</span>

                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>

                            <div class="col-xxl-4 col-xl-12">
                                <div class="card info-card customers-card">
                                    <div class="card-body">
                                        <h5 class="card-title">Pedidos Em Processo</h5>

                                        <div class="d-flex align-items-center">
                                            <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                                                <i class="bi bi-people"></i>
                                            </div>
                                            <div class="ps-3">
                                                <h6>${e.funcionarios}</h6>

                                            </div>
                                        </div>

                                    </div>
                                </div>

                            </div>
                            <div class="col-12">
                                <div class="card">

                                    <div class="card-body">
                                        <h5 class="card-title">Vendidos </h5>

                                        <div id="reportsChart"></div>

                                        <script>
                                            document.addEventListener("DOMContentLoaded", () => {
                                            new ApexCharts(document.querySelector("#reportsChart"), {
                                            series: [{
                                            name: 'Valor vendido',
                                                    data: [
                                            <c:forEach items="${grafico}" var="p" varStatus="status">
                                                ${p.total}<c:if test="${!status.last}">,</c:if>
                                            </c:forEach>
                                                    ],
                                            }],
                                                    chart: {
                                                    height: 350,
                                                            type: 'area',
                                                            toolbar: {
                                                            show: false
                                                            },
                                                    },
                                                    markers: {
                                                    size: 4
                                                    },
                                                    colors: ['#4154f1', '#2eca6a', '#ff771d'],
                                                    fill: {
                                                    type: "gradient",
                                                            gradient: {
                                                            shadeIntensity: 1,
                                                                    opacityFrom: 0.3,
                                                                    opacityTo: 0.4,
                                                                    stops: [0, 90, 100]
                                                            }
                                                    },
                                                    dataLabels: {
                                                    enabled: false
                                                    },
                                                    stroke: {
                                                    curve: 'smooth',
                                                            width: 2
                                                    },
                                                    xaxis: {
                                                    type: 'datetime',
                                                            categories: [
                                            <c:forEach items="${grafico}" var="p" varStatus="status">
                                                            '${p.dataentrega}'<c:if test="${!status.last}">,</c:if>
                                            </c:forEach>
                                                            ]
                                                    },
                                                    tooltip: {
                                                    x: {
                                                    format: 'dd/MM/yy HH:mm'
                                                    },
                                                    }
                                            }).render();
                                            });
                                        </script>

                                    </div>

                                </div>
                            </div>

                            <div class="col-12">
                                <div class="card top-selling overflow-auto">             

                                    <div class="card-body pb-0">
                                        <h5 class="card-title">Produtos <span>| Todos</span></h5>

                                        <table class="table table-borderless">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Imagem</th>
                                                    <th scope="col">Nome</th>
                                                    <th scope="col">Preço de Venda</th>
                                                    <th scope="col">Quantidade em Estoque</th>
                                                    <th scope="col">Custo</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${produtos}" var="p">
                                                    <tr>
                                                        <th scope="row"><a href="#"><img src="data:image/jpeg;base64,${p.imagemBase64}" alt="${p.nome}"></a></th>
                                                        <td><a href="#" class="text-primary fw-bold">${p.nome}</a></td>
                                                        <td>$${p.formatadoValor()}</td>
                                                        <td class="fw-bold">${p.quantidadeEstoque}</td>
                                                        <td>$${p.formatadoCusto()}</td>
                                                    </tr>
                                                </c:forEach>


                                            </tbody>
                                        </table>

                                    </div>

                                </div>
                            </div>

                        </div>
                    </div>

                   
                    <div class="col-lg-4">

                        <div class="card">

                            <div class="card-body">
                                <h5 class="card-title">Atividades Recentes <span>| Todos</span></h5>

                                <div class="activity">
                                    <c:forEach items="#{auditoria}" var="a">
                                        <div class="activity-item d-flex">
                                        <div class="activite-label">${a.data_operacao}</div>
                                        <i class='bi bi-circle-fill activity-badge text-success align-self-start'></i>
                                        <div class="activity-content">
                                            ${a.operacao} <a href="#" class="fw-bold text-dark">${a.tabela}</a> ${a.nome}
                                        </div>
                                    </div>
                                    </c:forEach>
                                </div>

                            </div>
                        </div>


                    </div>

                </div>
            </section>

        </main>


        <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

        <script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="assets/vendor/chart.js/chart.umd.js"></script>
        <script src="assets/vendor/echarts/echarts.min.js"></script>
        <script src="assets/vendor/quill/quill.js"></script>
        <script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
        <script src="assets/vendor/tinymce/tinymce.min.js"></script>
        <script src="assets/vendor/php-email-form/validate.js"></script>

        <script src="assets/js/main.js"></script>

    </body>

</html>