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

        <title>Evelin Verissimo | Pedidos </title>

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
                    <h2>Meus Pedidos</h2>
                    <div class="cards-endereco">
                        <c:forEach items="${pedido}" var="e">
                            <div class="informacoes-endereco">
                                <h3 class="end-padrao">Pedido #${e.id_pedido}</h3>

                                <h3 style="color: #614031; padding:0 10px o" >Total: ${e.total}</h3>
                                <h3>Data Entrega: ${e.data_entrega}</h3>
                                <h3>Entrega: ${e.id_endereco.logradouro} ${e.id_endereco.numero}</h3>
                                <h3>Status: ${e.status}</h3>
                                <c:if test="${e.status != 'cancelado'}">
                                    <c:if test="${e.status != 'entregue'}">
                                        <div class="button-group">
                                            <form action="cancelar-pedido" method="post">
                                                <input type="hidden" value="${e.id_pedido}" id="idPedido" name="idPedido">
                                                <button class="remove-btn" onclick="showAlertRemoverPedido(event)">Cancelar Pedido</button>
                                            </form>
                                        </div>
                                    </c:if>
                                </c:if>

                            </div>
                        </c:forEach>
                    </div>
                    <div class="btn-confirmar">
                        <a href="./inicio"><button>Fazer novo Pedido</button></a>

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
