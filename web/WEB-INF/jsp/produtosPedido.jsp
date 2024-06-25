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

        <title>Produto / Detalhes Pedido - Evelin Verissimo</title>
        <meta content="" name="description">
        <meta content="" name="keywords">
        <link href="assets/img/favicon.png" rel="icon">
        <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/echarts/dist  /echarts.min.js"></script>
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

    </head>

    <body>

        <jsp:include page="headerAdministrador.jsp"></jsp:include>

            <main id="main" class="main">

                <div class="pagetitle">
                    <h1>Detalhes do Pedido</h1>
                    <nav>
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="./inicioAdministrador">Home</a></li>
                            <li class="breadcrumb-item ">Pedido</li>
                            <li class="breadcrumb-item active">Detalhes do Pedido</li>
                        </ol>
                    </nav>
                </div>

                <section class="section dashboard">
                <div class="row">
                    <div class="col-lg-8">
                        <div class="row">
                            <div class="col-12">
                                <div class="card top-selling overflow-auto">
                                    <div class="card-body pb-0">
                                        <h5 class="card-title">Pedido <span>| Detalhes</span></h5>
                                        <table class="table table-borderless">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Imagem</th>
                                                    <th scope="col">Produto</th>
                                                    <th scope="col">Especificações</th>
                                                    <th scope="col">Quantidade</th>
                                                    <th scope="col">Valor Adicional</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${produtos}" var="produto">
                                                    <tr>
                                                        <th scope="row">
                                                            <a href="#"><img src="data:image/jpeg;base64,${produto.id_produto.imagemBase64}" alt="${produto.id_produto.nome}"></a>
                                                        </th>
                                                        <td>
                                                            <a href="#" class="text-primary fw-bold">${produto.id_produto.nome}</a>
                                                        </td>
                                                        <td>
                                                            <a href="#" class="text-primary fw-bold">
                                                                <c:forEach items="${sabores}" var="s">
                                                                    
                                                                        <label>
                                                                            <c:if test="${produto.id_produto_pedido == s.id_produto_pedido.id_produto_pedido}">
                                                                                 ${s.sabor.nomePai}: ${s.sabor.nome}
                                                                            </c:if>
                                                                                            
                                                                        </label>
                                                                </c:forEach>
                                                            </a>
                                                        </td>
                                                        <td>
                                                            <a href="#" class="text-primary fw-bold">${produto.quantidade}</a>
                                                        </td>
                                                        <td>
                                                            <a href="#" class="text-primary fw-bold">${produto.formatadoValorAdicional()}</a>
                                                        </td>
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
                                <h5 class="card-title">Endereço de Entrega<span></span></h5>
                                <div class="activity">
                                    <div class="activity-item d-flex">
                                        <div class="activite-label">Endereço</div>
                                        <i class='bi bi-circle-fill activity-badge text-success align-self-start'></i>
                                        <div class="activity-content">
                                            ${p.id_endereco.localidade} <a href="#" class="fw-bold text-dark">${p.id_endereco.logradouro}</a> ${p.id_endereco.numero}
                                        </div>
                                    </div>
                                    <div class="activity-item d-flex">
                                        <div class="activite-label">Data de Entrega</div>
                                        <i class='bi bi-circle-fill activity-badge text-success align-self-start'></i>
                                        <div class="activity-content">
                                            <a href="#" class="fw-bold text-dark">${p.data_entrega}</a>
                                        </div>
                                    </div>
                                        <div class="activity-item d-flex">
                                        <div class="activite-label">Valor do Pedido</div>
                                        <i class='bi bi-circle-fill activity-badge text-success align-self-start'></i>
                                        <div class="activity-content">
                                            <a href="#" class="fw-bold text-dark">${p.total + p.frete}</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card">
                            <div class="card-body pb-0">
                                <h5 class="card-title">Dados do Cliente<span></span></h5>
                                <div class="activity">
                                    <div class="activity-item d-flex">
                                        <div class="activite-label">Nome</div>
                                        <i class='bi bi-circle-fill activity-badge text-success align-self-start'></i>
                                        <div class="activity-content">
                                            <a href="#" class="fw-bold text-dark">${p.id_cliente.nome}</a>
                                        </div>
                                    </div>
                                    <div class="activity-item d-flex">
                                        <div class="activite-label">Telefone</div>
                                        <i class='bi bi-circle-fill activity-badge text-success align-self-start'></i>
                                        <div class="activity-content">
                                            <a href="#" class="fw-bold text-dark">${p.id_cliente.telefone}</a>
                                        </div>
                                    </div>
                                    <div class="activity-item d-flex">
                                        <div class="activite-label">Email</div>
                                        <i class='bi bi-circle-fill activity-badge text-success align-self-start'></i>
                                        <div class="activity-content">
                                            <a href="#" class="fw-bold text-dark">${p.id_cliente.email}</a>
                                        </div>
                                    </div>
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
