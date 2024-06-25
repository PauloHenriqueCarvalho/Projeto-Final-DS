<%-- 
    Document   : estoqueProdutos
    Created on : 02/05/2024, 00:09:29
    Author     : paulo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Produto / Especificacao - Evelin Verissimo</title>
        <meta content="" name="description">
        <meta content="" name="keywords">
        <link href="assets/img/favicon.png" rel="icon">
        <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/echarts/dist/echarts.min.js"></script>
        <link href="https://fonts.gstatic.com" rel="preconnect">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">        <!-- Vendor CSS Files -->
        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
        <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
        <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
        <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <link href="assets/css/style.css" rel="stylesheet">
        <link rel="stylesheet" href="./styles/estoqueProdutos.css">
        <link rel="stylesheet" href="./styles/historico.css">

    </head>

    <body>

        <jsp:include page="headerAdministrador.jsp"></jsp:include>

            <main id="main" class="main">

                <div class="pagetitle">
                    <h1>Pedidos Atuais</h1>
                    <nav>
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="./inicioAdministrador">Home</a></li>
                            <li class="breadcrumb-item ">Pedidos</li>
                            <li class="breadcrumb-item active">Pedidos Atuais</li>
                        </ol>
                    </nav>
                </div>

                <section class="section dashboard">
                    <div class="row">
                        <div class="col-lg-8">
                            <div class="row">
                                <div class="col-12">
                                    <div class="card top-selling overflow-auto">
                                        <div class="filter">
                                            <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                                            <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                                                <li class="dropdown-header text-start">
                                                    <h6>Filter</h6>
                                                </li>
                                            <c:forEach items="${categorias}" var="categoria">
                                                <li><a class="dropdown-item" href="#">${categoria.nome}</a></li>
                                                </c:forEach> 
                                        </ul>
                                    </div>

                                    <div class="card-body pb-0">
                                        <h5 class="card-title">Pedidos <span>| todos</span></h5>

                                        <table class="table table-borderless">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Cliente</th>
                                                    <th scope="col">Total</th>
                                                    <th scope="col">Data Entrega</th>
                                                    <th scope="col">Pagamento</th>
                                                    <th scope="col">Endereco de Entrega</th>
                                                    <th scope="col">Status</th>
                                                    <th scope="col">Detalhes</th>
                                                    
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${pedidos}" var="p">
                                                    <tr>
                                                        <td><a href="#" class="text-primary fw-bold">${p.id_cliente.nome}</a></td>
                                                        <td><a href="#" class="text-primary fw-bold">${p.total + p.frete}</a></td>

                                                        <td><a href="#" class="text-primary fw-bold">${p.data_entrega}</a></td>
                                                        <td><a href="#" class="text-primary fw-bold">${p.idPagamento.nome}</a></td>
                                                        <td><a href="#" class="text-primary fw-bold">${p.id_endereco.logradouro} ${p.id_endereco.numero}</a></td>
                                                        <td>
                                                            <form action="updateStatus" method="post">
                                                                <input type="hidden" name="id" value="${p.id_pedido}" id="id">
                                                                <input type="hidden" name="status" value="${p.status}" id="status">
                                                                <button  class="btn-status">${p.status} </button>
                                                            </form>
                                                            

                                                        </td>
                                                        <td>
                                                            <form action="verDetalhesAtual" method="post">
                                                                <input type="hidden" id="idPedido" name="idPedido" value="${p.id_pedido}">
                                                                <button type="submit" class="learn-more">
                                                                    <span class="circle" aria-hidden="true">
                                                                        <span class="icon arrow"></span>
                                                                    </span>
                                                                    <span class="button-text"> Ver Detalhes</span>
                                                                </button>
                                                            </form>
                                                        </td>
                                                <script>
                                                    function showAlert(event) {
                                                    event.preventDefault();
                                                    swal('Produto removido com Sucesso!', '', 'success').then(() => {
                                                    event.target.closest('form').submit();
                                                    });
                                                    }
                                                    function submitForm(form) {
                                                    form.submit();
                                                    }
                                                </script>
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
                            <div class="filter">
                                <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                                <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                                    <li class="dropdown-header text-start">
                                        <h6>Filter</h6>
                                    </li>
                                    <li><a class="dropdown-item" href="#">Today</a></li>
                                    <li><a class="dropdown-item" href="#">This Month</a></li>
                                    <li><a class="dropdown-item" href="#">This Year</a></li>
                                </ul>
                            </div>

                            <div class="card-body">
                                <h5 class="card-title">Atividades Recentes<span>| Todas</span></h5>

                                <div class="activity">

                                    <div class="activity-item d-flex">
                                        <div class="activite-label">32 min</div>
                                        <i class='bi bi-circle-fill activity-badge text-success align-self-start'></i>
                                        <div class="activity-content">
                                            Ultimos <a href="#" class="fw-bold text-dark">5 produtos</a> adicionados    
                                        </div>
                                    </div>

                                </div>

                            </div>
                        </div>



                        <div class="card">
                            <div class="filter">
                                <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                                <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                                    <li class="dropdown-header text-start">
                                        <h6>Filter</h6>
                                    </li>

                                    <li><a class="dropdown-item" href="#">Today</a></li>
                                    <li><a class="dropdown-item" href="#">This Month</a></li>
                                    <li><a class="dropdown-item" href="#">This Year</a></li>
                                </ul>
                            </div>

                            <div class="card-body pb-0">
                                <h5 class="card-title">Tipos de Produtos <span>| Today</span></h5>

                                <div id="trafficChart" style="min-height: 400px;" class="echart"></div>

                                <script>
                                    document.addEventListener("DOMContentLoaded", () => {
                                    echarts.init(document.querySelector("#trafficChart")).setOption({
                                    tooltip: {
                                    trigger: 'item'
                                    },
                                            legend: {
                                            top: '5%',
                                                    left: 'center'
                                            },
                                            series: [{
                                            name: 'Access From',
                                                    type: 'pie',
                                                    radius: ['40%', '70%'],
                                                    avoidLabelOverlap: false,
                                                    label: {
                                                    show: false,
                                                            position: 'center'
                                                    },
                                                    emphasis: {
                                                    label: {
                                                    show: true,
                                                            fontSize: '18',
                                                            fontWeight: 'bold'
                                                    }
                                                    },
                                                    labelLine: {
                                                    show: false
                                                    },
                                                    data: [
                                    <c:forEach items="${listaCategoria}" var="c" varStatus="status">
                                                    {
                                                    value: ${c.valor},
                                                            name: '${c.nomeCategoria}'
                                                    }<c:if test="${!status.last}">,</c:if>
                                    </c:forEach>
                                                    ]
                                            }]
                                    });
                                    });
                                </script>
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
