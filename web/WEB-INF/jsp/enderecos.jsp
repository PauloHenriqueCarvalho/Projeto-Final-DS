<%-- 
    Document   : listaProdutosCliente
    Created on : 05/05/2024, 20:42:09
    Author     : paulo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Evelin Verissimo | Produtos</title>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.css">


        <!-- Google font -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

        <!-- Bootstrap -->
        <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>

        <!-- Slick -->
        <link type="text/css" rel="stylesheet" href="css/slick.css"/>
        <link type="text/css" rel="stylesheet" href="css/slick-theme.css"/>

        <!-- nouislider -->
        <link type="text/css" rel="stylesheet" href="css/nouislider.min.css"/>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>


        <!-- Font Awesome Icon -->
        <link rel="stylesheet" href="css/font-awesome.min.css">

        <!-- Custom stlylesheet -->
        <link type="text/css" rel="stylesheet" href="css/style.css"/>
        <link type="text/css" rel="stylesheet" href="css/endereco.css"/>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        
            <div class="section">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12"> 
                            <div class="section-title">
                                <h3 class="title">Ola Paulo</h3>
                                

                            </div>
                        </div>
                    </div>
            </div>
            <main>
                <div class="container-main">
                    <h2>Meus Endereços</h2>
                    <div class="cards-endereco">
                        <div class="informacoes-endereco">
                            <h3 class="end-padrao">Endereço Padrão</h3>
                            <p>Paulo Henrique</p>
                            <h3>RUA AUGUSTO GOMES 919</h3>
                            <h3>LONDRINA, PR - 86083590</h3>
                            <h3>TEL- (43) 9 9195-0011</h3>
                            <div class="button-group">
                                <button class="edit-btn">Editar Endereço</button>
                                <button class="remove-btn">Remover Endereço</button>
                            </div>
                        </div>
                        <div class="informacoes-endereco">
                            <h3>Endereço Secundário</h3>
                            <p>Maria Clara</p>
                            <h3>AVENIDA BRASIL 1234</h3>
                            <h3>CURITIBA, PR - 80000000</h3>
                            <h3>TEL- (41) 9 9999-9999</h3>
                            <div class="button-group">
                                <button class="edit-btn">Editar Endereço</button>
                                <button class="remove-btn">Remover Endereço</button>
                            </div>
                        </div>
                        <div class="informacoes-endereco">
                            <h3>Endereço Trabalho</h3>
                            <p>José Silva</p>
                            <h3>RUA DA EMPRESA 456</h3>
                            <h3>SAO PAULO, SP - 01234567</h3>
                            <h3>TEL- (11) 9 8888-8888</h3>
                            <div class="button-group">
                                <button class="edit-btn">Editar Endereço</button>
                                <button class="remove-btn">Remover Endereço</button>
                            </div>
                        </div>
                    </div>
                    <div class="add-endereco">
                        <button id="add-btn">+</button>
                    </div>
                    <div class="btn-confirmar">
                        <button>CONFIRMAR</button>
                    </div>
                </div>
            
                <div id="modal" class="modal">
                    <div class="modal-content">
                        <span class="close">&times;</span>
                        <h2>Editar Endereço</h2>
                        <form id="edit-form">
                            <label for="nome">Nome:</label>
                            <input type="text" id="nome" name="nome">
                            <label for="rua">Rua:</label>
                            <input type="text" id="rua" name="rua">
                            <label for="cidade">Cidade:</label>
                            <input type="text" id="cidade" name="cidade">
                            <label for="estado">Estado:</label>
                            <input type="text" id="estado" name="estado">
                            <label for="cep">CEP:</label>
                            <input type="text" id="cep" name="cep">
                            <label for="telefone">Telefone:</label>
                            <input type="text" id="telefone" name="telefone">
                            <button type="submit">Salvar</button>
                        </form>
                    </div>
                </div>
            
                <!-- Modal para adicionar endereço -->
                <div id="add-modal" class="modal">
                    <div class="modal-content">
                        <span class="close-add">&times;</span>
                        <h2>Adicionar Endereço</h2>
                        <form id="add-form">
                            <label for="add-nome">Nome:</label>
                            <input type="text" id="add-nome" name="nome">
                            <label for="add-rua">Rua:</label>
                            <input type="text" id="add-rua" name="rua">
                            <label for="add-cidade">Cidade:</label>
                            <input type="text" id="add-cidade" name="cidade">
                            <label for="add-estado">Estado:</label>
                            <input type="text" id="add-estado" name="estado">
                            <label for="add-cep">CEP:</label>
                            <input type="text" id="add-cep" name="cep">
                            <label for="add-telefone">Telefone:</label>
                            <input type="text" id="add-telefone" name="telefone">
                            <button type="submit">Adicionar</button>
                        </form>
                    </div>
                </div>
            </main>
            
            
            

<!-- jQuery Plugins -->
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/slick.min.js"></script>
<script src="js/nouislider.min.js"></script>
<script src="js/jquery.zoom.min.js"></script>
<script src="js/main.js"></script>
<script src="js/endereco.js"></script>
</body>
</html>
