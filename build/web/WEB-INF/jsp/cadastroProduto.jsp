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
                <form  action="cadastro-produto" method="post" name="frmCadastro" enctype="multipart/form-data" class="row"> 
                    <div class="col-lg-6">

                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Produto</h5>

                                <div class="row g-3">
                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <label class="form-label" for="nome">Nome</label>
                                        <input type="text" id="nome" name="nome" class="form-control form-control-lg" required />
                                    </div>

                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <label class="form-label" for="descricao">Descricao</label>
                                        <input type="text" id="descricao" name="descricao" class="form-control form-control-lg" required />

                                    </div>

                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <label class="form-label" for="preco">Preco de Venda</label>
                                        <input type="number" id="preco" name="preco" step="0.01" class="form-control form-control-lg" required />
                                    </div>
                                    
                                     <div data-mdb-input-init class="form-outline mb-4">
                                        <label class="form-label" for="precoCusto">Preco de Custo</label>
                                        <input type="number" id="precoCusto" name="precoCusto" step="0.01" class="form-control form-control-lg" required />
                                    </div>
                                    
                                     <div data-mdb-input-init class="form-outline mb-4">
                                        <label class="form-label" for="quantidade">Quantidade em Estoque</label>
                                        <input type="number" id="quantidade" name="quantidade" step="0.01" class="form-control form-control-lg" required />
                                    </div>
                                    
                          

                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <label class="form-label" for="categoria">Categoria</label>
                                        <select id="categoria" name="categoria" class="form-select form-select-lg">
                                           
                                            <c:forEach items="${categorias}" var="categoria">
                                                <option value="${categoria.idCategoria}">${categoria.nome}</option>
                                            </c:forEach>
                                        </select>
                                    </div>



                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">

                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Imagens do Produto</h5>
                                <div  class="row g-3"> 

                                    <div>
                                        <div class="mb-522 d-flex justify-content-center">
                                            <img id="selectedImage" src="./img/imagemFoto.png"
                                                 alt="example placeholder" style="width: 100px;" />
                                        </div>
                                        <div class="d-flex justify-content-center">
                                            <div class="input-imagem">
                                                <label class="form-label" for="confirmarSenha">Imagem Principal</label>
                                                <input type="file" id="imagemPadrao" name="imagemPadrao" required accept="image/*" class="form-control form-control-lg" onchange="displaySelectedImage(event, 'selectedImage')"/>
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
                                        
                                        function displaySelectedImage2(event, imageId) {
                                            const selectedImage = document.getElementById(imageId);
                                            const file = event.target.files[0];
                                            const reader = new FileReader();

                                            reader.onload = function () {
                                                selectedImage2.src = reader.result;
                                            }

                                            if (file) {
                                                reader.readAsDataURL(file);
                                            }
                                        }
                                        function displaySelectedImage3(event, imageId) {
                                            const selectedImage = document.getElementById(imageId);
                                            const file = event.target.files[0];
                                            const reader = new FileReader();

                                            reader.onload = function () {
                                                selectedImage3.src = reader.result;
                                            }

                                            if (file) {
                                                reader.readAsDataURL(file);
                                            }
                                        }
                                                                                
                                    </script>




                                    <div>
                                        <div class="mb-5222 d-flex justify-content-center">
                                            <img id="selectedImage2" src="./img/imagemFoto.png"
                                                 alt="example placeholder" style="width: 100px;" />
                                        </div>
                                        <div class="d-flex justify-content-center">
                                            <div class="input-imagem">
                                                <label class="form-label" for="confirmarSenha">Imagem</label>
                                                <input type="file" id="imagem2" name="imagem2" required accept="image/*" class="form-control form-control-lg" onchange="displaySelectedImage2(event, 'selectedImage')"/>
                                            </div>
                                        </div>  
                                    </div>

                                    
                                    <div>
                                        <div class="mb-522 d-flex justify-content-center">
                                            <img id="selectedImage3" src="./img/imagemFoto.png"
                                                 alt="example placeholder" style="width: 100px;" />
                                        </div>
                                        <div class="d-flex justify-content-center">
                                            <div class="input-imagem">
                                                <label class="form-label" for="confirmarSenha">Imagem</label>
                                                <input type="file" id="imagem3" name="imagem3" required accept="image/*" class="form-control form-control-lg" onchange="displaySelectedImage3(event, 'selectedImage')"/>
                                            </div>
                                        </div>
                                    </div>


                                     <c:if test="${not empty sessionScope.erroCadastro}">
                                        <p style="color: red">${sessionScope.erroCadastro}</p>
                                    </c:if> 
                                    <div id="botoes" class="d-flex justify-content-center">                     
                                        <button type="submit" class="btn-admin">Cadastrar</button>
                                 
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </form>

            </section>
            

        </main>

        <footer id="footer" class="footer">
              
        </footer>

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