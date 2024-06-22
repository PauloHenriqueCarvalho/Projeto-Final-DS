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

            <div id="breadcrumb" class="section">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <ul class="breadcrumb-tree">
                                <li><a href="#">Home</a></li>
                                <li><a href="#">Lista de Produtos</a></li>
                                <li><a href="#">${categoriaAtual}</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <div class="section">
            <div class="container">
                <div class="row">
                    <div id="aside" class="col-md-3">
                        <div class="aside">
                            <h3 class="aside-title">Categorias</h3>
                            <div class="checkbox-filter">
                                <c:forEach items="${categorias}" var="categoria">
                                    <div class="input-checkbox">
                                        <input type="checkbox" id="${categoria.idCategoria}">
                                        <label for="categoria">
                                            <span></span>
                                            ${categoria.nome}
                                            <small>(120)</small>
                                        </label>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>

                    <div id="store" class="col-md-9">
                        <div class="store-filter clearfix">
                            <div class="store-sort">
                                <label>
                                    Filtrar:
                                    <select id="priceFilter" class="input-select" onchange="filterProducts()">
                                        <option>Escolha:</option>
                                        <option value="desc">Mais Caro</option>
                                        <option value="asc">Mais Barato</option>
                                    </select>
                                </label>
                            </div>
                        </div>

                        <div class="row">
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
                                            <h3 class="product-name"><a href="#">${produto.nome}</a></h3>

                                            <h4 class="product-price">R$${produto.valor}</h4>
                                            <span class="product-old-price"> *Podendo Alterar</span>

                                            <div class="product-rating">
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                            </div>
                                            <div class="product-btns">
                                                <form class="product-btns" action="listaDesejos" method="post">
                                                    <input type="hidden" name="idProduto" id="idProduto" value="${produto.idProduto}">
                                                    <c:choose>
                                                        <c:when test="${empty usuario}">

                                                            <button type="submit" onclick="showAlert(event)" class="add-to-wishlist">
                                                                <i class="fa fa-heart-o"></i>
                                                                <span class="tooltipp">Adicionar na Lista de Desejos</span>
                                                            </button>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <button type="submit" onclick="showAlert4(event)" class="add-to-wishlist">
                                                                <i class="fa fa-heart-o"></i>
                                                                <span class="tooltipp">Adicionar na Lista de Desejos</span>
                                                            </button>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </form>
                                            </div>
                                        </div>  
                                        <form action="produtoPage" method="post" class="add-to-cart">
                                            <input type="hidden" name="idProduto" id="idProduto" value="${produto.idProduto}">
                                            <button type="submit" class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i>Adicionar ao Carrinho</button>
                                        </form>

                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="store-filter clearfix">
                            <ul class="store-pagination">
                                <c:forEach begin="1" end="${totalPages}" var="page">
                                    <li class="${currentPage == page ? 'active' : ''}">
                                        <a href="?cat=${idCat}&page=${page}&filter=${selectedFilter}">${page}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
        <script src="js/produtos.js"></script>
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/slick.min.js"></script>
        <script src="js/nouislider.min.js"></script>
        <script src="js/jquery.zoom.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
