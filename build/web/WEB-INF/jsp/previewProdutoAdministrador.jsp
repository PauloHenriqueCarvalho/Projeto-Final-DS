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
        <link href="assets/img/favicon.png" rel="icon">
        <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link href="https://fonts.gstatic.com" rel="preconnect">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500,600,600,700,700i" rel="stylesheet">
        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
        <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
        <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
        <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

        <link href="assets/css/style.css" rel="stylesheet">
        <link rel="stylesheet" href="./styles/estoqueProdutos.css">
    </head>

    <body>

        <jsp:include page="headerAdministrador.jsp"></jsp:include>

            <main id="main" class="main">

                <div class="pagetitle">
                    <h1>Produto</h1>
                    <nav>
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="./inicioAdministrador">Home</a></li>
                            <li class="breadcrumb-item active">Produto</li>
                        </ol>
                    </nav>
                </div>

                <section class="section dashboard">
                    <div class="row">
                        <div class="card">
                            <div class="produto">
                                <div class="produto-imagens">
                                    <c:forEach items="${imagensProdutos}" var="imagem">
                                        <c:choose>
                                            <c:when test="${imagem.imagemPadrao}">
                                                <img style="width:  280px" src="data:image/jpeg;base64,${imagem.imagemBase64}">
                                            </c:when>
                                            <c:otherwise> 
                                                 <img style="width: 280px" src="data:image/jpeg;base64,${imagem.imagemBase64}">
                                                
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </div>
                                <div class="produto-info">  
                                    <div class="header-info">
                                        <h2>Informações do Produto</h2>
                                    </div>
                                    <div class="main-info">
                                        <div class="nome-info">
                                            <h3>Nome do Produto</h3>
                                            <p>${produto.nome}</p>
                                        </div>
                                        <div class="preco-info">
                                            <h3>Valor do Produto :</h3>
                                            <p>R$${produto.formatadoValor()}</p>
                                            <h3>Custo do Produto :</h3>
                                            <p>R$${produto.formatadoCusto()}</p>
                                            <h3>Categoria do Produto :</h3>
                                            <p>${produto.categoria.nome}</p>
                                            <span style="color: green; font-size: 12px;">Lucro: R$ ${produto.valor - produto.precoCusto}</span>
                                        </div>
                                        <hr>
                                        <div class="linha"></div>
                                        <div class="descricao">
                                            <h3>Descricao</h3>
                                            <p>${produto.descricao}</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="produto-tipos">
                                    <div class="header-tipos">
                                        <h2>Tipos</h2>
                                    </div>
                                    <div class="main-tipos">
                                        <a href="./tipoProduto">Adicionar Tipos</a>
                                        <c:forEach items="${sabores}" var="s">

                                               <div class="tipo-nome">
                                                <h2>${s.nome}</h2>
                                                <label class="switch">
                                                    <input type="checkbox" name="statusProduto">
                                                    <span class="slider round"></span>
                                                </label>
                                            </div>
                                            <c:forEach items="${saboresEspecificos}" var="se">
                                                <c:if test="${se.idPai == s.idSabor}">
                                                    <div class="nome-especifico">
                                                   
                                                    <h4>${se.nome} <span>R$ ${se.formatadoValorAdicional()}</span> </h4>
                                                   
                                                    <form class="nome-especifico-form" action="excluir-especifico" method="post">
                                                         <input type="hidden" id="idSabor" name="idSabor" value="${se.idSabor}">
                                                        <button type="submit" class="btn">
                                                            <svg viewBox="0 0 15 17.5" height="17.5" width="15" xmlns="http://www.w3.org/2000/svg" class="icon">
                                                            <path transform="translate(-2.5 -1.25)" d="M15,18.75H5A1.251,1.251,0,0,1,3.75,17.5V5H2.5V3.75h15V5H16.25V17.5A1.251,1.251,0,0,1,15,18.75ZM5,5V17.5H15V5Zm7.5,10H11.25V7.5H12.5V15ZM8.75,15H7.5V7.5H8.75V15ZM12.5,2.5h-5V1.25h5V2.5Z" id="Fill"></path>
                                                            </svg>
                                                        </button>
                                                    </form>
                                                </div>
                                                </c:if>
                                            </c:forEach>
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
