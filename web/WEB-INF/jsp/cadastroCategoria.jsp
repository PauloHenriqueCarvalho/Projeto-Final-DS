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

        <title>Produto / Categorias - Evelin Verissimo</title>
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
        <link rel="stylesheet" href="./styles/estoqueProdutos.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
    </head>

    <body>
        <jsp:include page="headerAdministrador.jsp"></jsp:include>
            <main id="main" class="main">

                <div class="pagetitle">
                    <h1>Informações Da Categorias</h1>
                    <nav>
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                            <li class="breadcrumb-item">Produto</li>
                            <li class="breadcrumb-item active">Categorias</li>
                        </ol>
                    </nav>
                </div>
                <section class="section">
                    <div   class="row"> 
                        <div class="col-lg-6">

                            <div class="card">
                                <form action="cadastro-categoria" method="post" name="frmCadastro" class="card-body">
                                    <h5 class="card-title">Categorias</h5>

                                    <div class="row g-3">
                                        <div data-mdb-input-init class="form-outline mb-4">
                                            <label class="form-label" for="nome">Nome</label>
                                            <input type="text" id="nome" name="nome" class="form-control form-control-lg" required />
                                        </div>
                                        <c:if test="${not empty sessionScope.erroCadastro}">
                                            <p style="color: red">${sessionScope.erroCadastro}</p>
                                        </c:if> 
                                        <div id="botoes" class="d-flex justify-content-center">

                                            <button type="submit" class="btn-admin">Cadastrar</button>

                                        </div>
                                    </div>

                                </form>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="card">
                                <div class="card-body pb-0">
                                    <h5 class="card-title">Categorias <span>| Todos</span></h5>
                                    <table class="table table-borderless">
                                        <thead>
                                            <tr>
                                                <th scope="col">Nome</th>
                                                <th scope="col">Deletar Categoria</th>
                                                <th scope="col">Status Da Categoria</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${categorias}" var="p">
                                                <tr>
                                                    <td> <h3>${p.nome}</h3></td>
                                                    <td>
                                                        <form action="deletar-categoria" method="post">
                                                            <input type="hidden" id="idCategoria" name="idCategoria" value="${p.idCategoria}">
                                                            <button  onclick="showAlert(event)" type="submit" class="button-del">
                                                                <svg viewBox="0 0 448 512" class="svgIcon"><path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z"></path></svg>
                                                            </button>
                                                        </form>
                                                    </td>
                                                    <td>
                                                        <form action="status-categoria" method="post">
                                                            <input type="hidden" id="idCategoria" name="idCategoria" value="${p.idCategoria}">
                                                            <label class="switch">
                                                                <input type="checkbox" name="statusProduto"  ${p.status ? 'checked' : ''} onchange="submitForm(this.form)">
                                                                <span class="slider round"></span>
                                                            </label>

                                                        </form>

                                                    </td>
                                                </tr>

                                            </c:forEach>
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
                                        </tbody>
                                    </table>

                                </div>
                            </div>
                        </div>
                </div>

            </section>


        </main>
        <footer id="footer" class="footer">

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