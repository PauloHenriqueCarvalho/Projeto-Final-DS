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

        <title>Dashboard - NiceAdmin Bootstrap Template</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

        <!-- Favicons -->
        <link href="assets/img/favicon.png" rel="icon">
        <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


        <!-- Google Fonts -->
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
        <!-- Template Main CSS File -->
        <link href="assets/css/style.css" rel="stylesheet">
        <link rel="stylesheet" href="./styles/estoqueProdutos.css">

    </head>

    <body>

        <jsp:include page="headerAdministrador.jsp"></jsp:include>

            <main id="main" class="main">

                <div class="pagetitle">
                    <h1>Especificacao de Produtos</h1>
                    <nav>
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="./inicioAdministrador">Home</a></li>
                            <li class="breadcrumb-item active">Especificacao de Produtos</li>
                        </ol>
                    </nav>
                </div><!-- End Page Title -->

                <section class="section dashboard">
                    <div class="row">

                        <!-- Left side columns -->
                        <div class="col-lg-8">
                            <div class="row">
                                <!-- Top Selling -->
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
                                        <h5 class="card-title">Produtos <span>| Today</span></h5>

                                        <table class="table table-borderless">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Imagem</th>
                                                    <th scope="col">Produto</th>
                                                    <th scope="col">Ver Produto</th>
                                                    <th scope="col">Deletar Produto</th>
                                                    <th scope="col">Status Do Produto</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${produtos}" var="produto">
                                                    <tr>

                                                        <th scope="row"><a href="#"><img src="data:image/jpeg;base64,${produto.imagemBase64}" alt="${produto.nome}"></a></th>
                                                        <td><a href="#" class="text-primary fw-bold">${produto.nome}</a></td>
                                                        <td><!-- Button trigger modal -->
                                                            <form action="ver-produto" method="post">
                                                                <input type="hidden" id="idProduto" name="idProduto" value="${produto.idProduto}">
                                                                <button type="submit" class="learn-more">
                                                                    <span class="circle" aria-hidden="true">
                                                                        <span class="icon arrow"></span>
                                                                    </span>
                                                                    <span class="button-text"> Ver Produto</span>
                                                                </button>
                                                            </form>


                                                            <!-- Modal -->
                                                            <div class="modal fade" id="exampleModalCenter${produto.idProduto}" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                                                <div class="modal-dialog modal-dialog-centered" role="document">
                                                                    <div class="modal-content">
                                                                        <div class="modal-header">
                                                                            <h5 class="modal-title" id="exampleModalLongTitle${produto.idProduto}">${produto.nome}</h5>
                                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                <span aria-hidden="true">&times;</span>
                                                                            </button>
                                                                        </div>
                                                                        <div class="modal-body">
                                                                            <div class="col-lg-6">

                                                                                <div class="card">
                                                                                    <div class="card-body">
                                                                                        <h5 class="card-title">Adicionar Tipos para especificacao</h5>
                                                                                        <div class="row g-3">
                                                                                            <div data-mdb-input-init class="form-outline mb-4">
                                                                                                <label class="form-label" for="nome">Nome</label>
                                                                                                <input type="text" id="nome" placeholder="Ex - Sabor" name="nome" class="form-control form-control-lg" required />
                                                                                            </div>
                                                                                            <div data-mdb-input-init class="form-outline mb-4">
                                                                                                <label class="form-label" for="descricao">Descricao</label>
                                                                                                <input type="text" id="descricao" name="descricao" class="form-control form-control-lg" required />

                                                                                            </div>
                                                                                        </div>

                                                                                    </div>
                                                                                </div>
                                                                            </div>


                                                                        </div>
                                                                        <div class="modal-footer">
                                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
                                                                            <button type="button" class="btn btn-primary">Salvar Alterações</button>
                                                                        </div>
                                                                    </div>
                                                                </div>                 
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <form action="deletar-produto" method="post">
                                                                <input type="hidden" id="idProduto" name="idProduto" value="${produto.idProduto}">
                                                                <button  onclick="showAlert(event)" type="submit" class="button-del">
                                                                    <svg viewBox="0 0 448 512" class="svgIcon"><path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z"></path></svg>
                                                                </button>
                                                            </form>
                                                        </td>
                                                        <td>
                                                            <form action="status-produto" method="post">
                                                                <input type="hidden" id="idProduto" name="idProduto" value="${produto.idProduto}">
                                                                <label class="switch">
                                                                    <input type="checkbox" name="statusProduto" ${produto.status ? 'checked' : ''} onchange="submitForm(this.form)">
                                                                    <span class="slider round"></span>
                                                                </label>

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
                            </div><!-- End Top Selling -->

                        </div>

                    </div><!-- End Left side columns -->

                    <!-- Right side columns -->
                    <div class="col-lg-4">

                        <!-- Recent Activity -->
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
                                    </div><!-- End activity item-->

                                </div>

                            </div>
                        </div><!-- End Recent Activity -->



                        <!-- Website Traffic -->
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


                        </div><!-- End Right side columns -->

                    </div>
            </section>

        </main><!-- End #main -->


        <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

         <!-- Vendor JS Files -->
        <script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="assets/vendor/chart.js/chart.umd.js"></script>
        <script src="assets/vendor/echarts/echarts.min.js"></script>
        <script src="assets/vendor/quill/quill.js"></script>
        <script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
        <script src="assets/vendor/tinymce/tinymce.min.js"></script>
        <script src="assets/vendor/php-email-form/validate.js"></script>

        <!-- Template Main JS File -->
        <script src="assets/js/main.js"></script>
    </body>

</html>
