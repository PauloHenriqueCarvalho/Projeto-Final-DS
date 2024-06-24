<%-- 
    Document   : listaProdutosCliente
    Created on : 05/05/2024, 20:42:09
    Author     : paulo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Evelin Verissimo | Enderecos </title>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.css">
        
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>
        <link type="text/css" rel="stylesheet" href="css/slick.css"/>
        <link type="text/css" rel="stylesheet" href="css/slick-theme.css"/>
        <link type="text/css" rel="stylesheet" href="css/nouislider.min.css"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link type="text/css" rel="stylesheet" href="css/style.css"/>
        <link type="text/css" rel="stylesheet" href="css/endereco.css"/>

        <script src="http://code.jquery.com/jquery-3.7.1.js"></script>
        <script src="http://jqueryvalidation.org/files/dist/jquery.validate.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>

            <div class="section">

                <main id="main-endereco">
                    <div class="container-main">
                        <h2>Meus Endereços</h2>
                        <div class="cards-endereco">
                        <c:forEach items="${enderecos}" var="e">
                            <div class="informacoes-endereco">
                                <c:choose>
                                    <c:when test="${e.enderecoPadrao == true}">
                                        <h3 class="end-padrao">Endereço Padrão</h3>
                                    </c:when>
                                    <c:otherwise>
                                        <h3 class="end-padrao">Endereço Alternativo</h3>
                                    </c:otherwise>
                                </c:choose>

                                <p>${e.idUsuario.nome}</p>
                                <h3>${e.logradouro} ${e.numero}</h3>
                                <h3>${e.localidade}, ${e.uf} - ${e.cep}</h3>
                                <h3>TEL- ${e.idUsuario.telefone}</h3>
                                <div class="button-group">
                                    <c:if test="${!e.enderecoPadrao}">
                                        <form action="definirEnderecoPadrao" method="post">
                                            <input type="hidden" id="idEndereco" name="idEndereco" value="${e.idEndereco}">
                                            <button class="edit-btn">Definir como padrão</button>
                                        </form>
                                    </c:if>
                                    <form action="remover-endereco" method="post">
                                        <input type="hidden" value="${e.idEndereco}" id="idEndereco" name="idEndereco">
                                        <button class="remove-btn" onclick="showAlertRemover(event)">Remover Endereço</button>
                                    </form>

                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="btn-confirmar">
                        <button id="add-btn">Adicionar Endereco</button>
                    </div>
                </div>


                <div id="add-modal" class="modal">
                    <div class="modal-content">
                        <span class="close-add">&times;</span>
                        <h2>Adicionar Endereço</h2>
                        <form action="add-endereco" method="post">
                            <label for="add-cep">CEP:</label>
                            <input type="number" minlength="8"  id="add-cep" name="add-cep" required="">
                            <a href="https://buscacepinter.correios.com.br/app/endereco/index.php" target="_blank">Nao sei meu CEP</a>
                            <label for="telefone">Numero</label>
                            <input type="number" id="numero"  name="numero" required="">
                            <label for="complemento">Complemento</label>
                            <input type="text" id="complemento" name="complemento">
                            <c:if test="${not empty sessionScope.erroCadastroEndereco}">
                                <p style="color: red">${sessionScope.erroCadastroEndereco}</p>
                            </c:if> 
                            <button type="submit" onclick="validarCep(event)">Adicionar</button>
                        </form>
                    </div>
                </div>
            </main>
            <jsp:include page="footer.jsp"></jsp:include>
            <script src="js/alert.js"></script>
            <script src="js/validacoes.js"></script>
            <script src="js/jquery.min.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="js/slick.min.js"></script>
            <script src="js/nouislider.min.js"></script>
            <script src="js/jquery.zoom.min.js"></script>
            <script src="js/main.js"></script>
            <script src="js/endereco.js"></script>
    </body>
</html>
