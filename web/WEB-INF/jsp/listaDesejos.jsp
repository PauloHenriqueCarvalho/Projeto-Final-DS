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

        <title>Evelin Verissimo | Lista de Desejos</title>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>
        <link type="text/css" rel="stylesheet" href="css/slick.css"/>
        <link type="text/css" rel="stylesheet" href="css/slick-theme.css"/>
        <link type="text/css" rel="stylesheet" href="css/nouislider.min.css"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link type="text/css" rel="stylesheet" href="css/style.css"/>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>

            <div class="section">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12"> 
                            <div class="section-title">
                                <h3 class="title">Lista de Desejos</h3>
                            </div>
                        </div>
                    </div>

                <c:if test="${not empty produtos}">
                    <div class="col-md-12">
                        <div class="row">
                            <div class="products-tabs">
                                <div id="tab1" class="tab-pane active">
                                    <div class="products-slick" data-nav="#slick-nav-1">
                                        <c:forEach items="${produtos}" var="produto">
                                            <div class="col-md-4 col-xs-6">

                                                <div class="product">
                                                    <div class="product-img">
                                                        <img class="img-card" src="data:image/jpeg;base64,${produto.imagemBase64}" alt="${produto.nome}">
                                                        <div class="product-label">
                                                            <c:if test="${produto.novo}">
                                                                <span class="new">NEW</span>
                                                            </c:if>
                                                        </div>
                                                    </div>
                                                    <div class="product-body">
                                                        <p class="product-category">Category</p>
                                                        <h3 class="product-name"><a href="#">${produto.nome}</a></h3>
                                                        <h4 class="product-price">$${produto.valor} <del class="product-old-price">$990.00</del></h4>
                                                        <div class="product-rating">
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                        </div>

                                                        <div class="product-btns">
                                                            <form class="product-btns" action="deletarListaDejesos" method="post">
                                                                <input type="hidden" name="idProduto" id="idProduto" value="${produto.idProduto}">
                                                                <button type="submit" onclick="showAlert2(event)" class="add-to-wishlist">
                                                                    <p id="removerLista"> <i class="fa fa-heart-o"></i> </p>

                                                                    <span class="tooltipp">Remover da Lista de Desejos</span>
                                                                </button>
                                                            </form>
                                                        </div>
                                                    </div>
                                                    <form  action="produtoPage" method="post" class="add-to-cart">
                                                        <input type="hidden" name="idProduto" id="idProduto" value="${produto.idProduto}">
                                                        <button type="submit" class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i>Add ao Carrinho</button>

                                                    </form>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <div id="slick-nav-1" class="products-slick-nav"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${empty produtos}">
                    <p>Nenhum produto encontrado.</p>
                </c:if>
            </div>
        </div>
    </div>
</div>

<script src="js/validacoes.js"></script>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/slick.min.js"></script>
<script src="js/nouislider.min.js"></script>
<script src="js/jquery.zoom.min.js"></script>
<script src="js/main.js"></script>
</body>
</html>
