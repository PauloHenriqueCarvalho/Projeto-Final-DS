<%-- 
    Document   : cadastrarProdutos
    Created on : 01/05/2024, 21:53:05
    Author     : paulo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>
<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Produto / Cadastro - Evelin Verissimo</title>
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
        <link rel="stylesheet" href="./styles/cadastro.css">

    </head>

    <body>
        <jsp:include page="headerAdministrador.jsp"></jsp:include>
        <main id="main" class="main">

            <div class="pagetitle">
                <h1>Especificação Do Produto</h1>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                        <li class="breadcrumb-item">Produto</li>
                        <li class="breadcrumb-item">Adicionar Produto</li>
                        <li class="breadcrumb-item active">Especificação do Produto ${produtoAtual.nome}</li>
                    </ol>
                </nav>
            </div>
            <section class="section">
                <div class="row"> 
                   <form  action="cadastro-tipo" method="post" name="frmCadastro"  class="col-lg-6">

                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Adicionar Tipo ao Produto  ${produtoAtual.nome}</h5>

                                <!-- Vertical Form -->
                                <div class="row g-3">
                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <label class="form-label" for="nome">Nome do Tipo da Especificação</label>
                                        <input type="text" id="nome" name="nome" class="form-control form-control-lg" required />
                                    </div>

                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <label class="form-label" for="descricao">Descricao</label>
                                        <input type="text" id="descricao" name="descricao" class="form-control form-control-lg" required />
                                    </div>
                                    
                                      <c:if test="${not empty sessionScope.erroCadastroEspecificacao}">
                                        <p style="color: red">${sessionScope.erroCadastroEspecificacao}</p>
                                    </c:if> 
                                    <div id="botoes" class="d-flex justify-content-center">
                                    
                                        <button type="submit" class="btn-admin">Cadastrar</button>
                                 
                                    </div>
                                    

                                </div>

                            </div>
                        </div>
                    </form>
                    <form  action="cadastro-especificacao" method="post" name="frmCadastro" class="col-lg-6">

                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Especificação</h5>
                         
                                <!-- Vertical Form -->
                                <div  class="row g-3"> 

                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <label class="form-label" for="nome"> Nome da especificação</label>
                                        <input type="text" id="nome" name="nome" class="form-control form-control-lg" required />
                                    </div>
                                    
                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <label class="form-label" for="descricao">Descricao</label>
                                        <input type="text" id="descricao" name="descricao" class="form-control form-control-lg" required />
                                    </div>
                                    
                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <label class="form-label" for="preco">Valor Adicional</label>
                                        <input type="number" id="valorAdicional" name="valorAdicional" step="0.01" class="form-control form-control-lg" required />
                                    </div>
                                    
                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <label class="form-label" for="categoria">Tipo da Especificação</label>
                                        <select id="idPai" name="idPai" class="form-select form-select-lg">
                                            <c:forEach items="${especificacao}" var="e">
                                                <option value="${e.idSabor}">${e.nome}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    

                                     <c:if test="${not empty sessionScope.erroCadastroEspecificacao}">
                                        <p style="color: red">${sessionScope.erroCadastroEspecificacao}</p>
                                    </c:if> 
                                    <div id="botoes" class="d-flex justify-content-center">
                                    
                                        <button type="submit" class="btn-admin">Cadastrar</button>
                                 
                                    </div>
                                </div>

                            </div>
                        </div>
                    </form>
                </div>

            </section>
            

        </main>

        <!-- ======= Footer ======= -->
        <footer id="footer" class="footer">
              
        </footer><!-- End Footer -->

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