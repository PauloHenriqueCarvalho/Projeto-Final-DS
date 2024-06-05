<%-- 
    Document   : cadastrarProdutos
    Created on : 01/05/2024, 21:53:05
    Author     : paulo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Forms / Layouts - NiceAdmin Bootstrap Template</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

        <!-- Favicons -->
        <link href="assets/img/favicon.png" rel="icon">
        <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

        <!-- Google Fonts -->
        <link href="https://fonts.gstatic.com" rel="preconnect">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

        <!-- Vendor CSS Files -->
        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
        <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
        <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
        <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

        <!-- Template Main CSS File -->
        <link href="assets/css/style.css" rel="stylesheet">
        <link rel="stylesheet" href="./styles/cadastro.css">
        

        <!-- =======================================================
        * Template Name: NiceAdmin
        * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
        * Updated: Apr 20 2024 with Bootstrap v5.3.3
        * Author: BootstrapMade.com
        * License: https://bootstrapmade.com/license/
        ======================================================== -->
    </head>

    <body>
        <jsp:include page="headerAdministrador.jsp"></jsp:include>
        <main id="main" class="main">

            <div class="pagetitle">
                <h1>Informações Do Produto</h1>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                        <li class="breadcrumb-item">Produto</li>
                        <li class="breadcrumb-item active">Adicionar Produto</li>
                    </ol>
                </nav>
            </div>
            <section class="section">
                <div class="row"> 
                    <div class="col-lg-6">

                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Produto</h5>

                                <!-- Vertical Form -->
                                <form action="cadastro-produto" class="row g-3" method="post" name="frmCadastro" enctype="multipart/form-data">
                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <label class="form-label" for="nome">Nome</label>
                                        <input type="text" id="nome" name="nome" class="form-control form-control-lg" required />
                                    </div>

                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <label class="form-label" for="descricao">Descricao</label>
                                        <input type="text" id="descricao" name="descricao" class="form-control form-control-lg" required />

                                    </div>

                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <label class="form-label" for="senha">Preco</label>
                                        <input type="number" id="preco" name="preco" step="0.01" class="form-control form-control-lg" required />
                                    </div>

                                    <div>
                                        <div class="mb-4 d-flex justify-content-center">
                                            <img id="selectedImage" src="https://mdbootstrap.com/img/Photos/Others/placeholder.jpg"
                                                 alt="example placeholder" style="width: 300px;" />
                                        </div>
                                        <div class="d-flex justify-content-center">
                                            <div>
                                                <label class="form-label" for="confirmarSenha">Imagem</label>
                                                <input type="file" id="imagem" name="imagem" required accept="image/*" class="form-control form-control-lg" onchange="displaySelectedImage(event, 'selectedImage')"/>
                                            </div>
                                        </div>
                                    </div>

                                    <script>
                                        function displaySelectedImage(event, imageId) {
                                            const selectedImage = document.getElementById(imageId);
                                            const file = event.target.files[0];
                                            const reader = new FileReader();

                                            reader.onload = function () {
                                                selectedImage.src = reader.result;
                                            }

                                            if (file) {
                                                reader.readAsDataURL(file);
                                            }
                                        }
                                    </script>





                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <label class="form-label" for="categoria">Categoria</label>
                                        <select id="categoria" name="categoria" class="form-select form-select-lg">
                                            <option value="">Selecione uma categoria</option>
                                            <c:forEach items="${categorias}" var="categoria">
                                                <option value="${categoria.idCategoria}">${categoria.nome}</option>
                                            </c:forEach>
                                        </select>
                                    </div>



                                    <div id="botoes" class="d-flex justify-content-center">
                                        <button type="submit" class="btn-admin">Cadastrar</button>
                                        <button type="reset" class="btn-admin">Reset</button>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
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