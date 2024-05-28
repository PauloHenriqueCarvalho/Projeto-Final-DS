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

        <!-- Google font -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

        <!-- Bootstrap -->
        <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>

        <!-- Slick -->
        <link type="text/css" rel="stylesheet" href="css/slick.css"/>
        <link type="text/css" rel="stylesheet" href="css/slick-theme.css"/>

        <!-- nouislider -->
        <link type="text/css" rel="stylesheet" href="css/nouislider.min.css"/>

        <!-- Font Awesome Icon -->
        <link rel="stylesheet" href="css/font-awesome.min.css">

        <!-- Custom stlylesheet -->
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
                            <li><a href="#">All Categories</a></li>
                            <li><a href="#">Accessories</a></li>
                            <li class="active">Headphones (227,490 Results)</li>
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
                                    <select class="input-select">
                                        <option value="0">Popular</option>
                                        <option value="1">Position</option>
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
                                                    <span class="sale">-30%</span>
                                                    <span class="new">NEW</span>
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
                                                    <form class="product-btns"  action="listaDesejos" method="post">
                                                        <input type="hidden" name="idProduto" id="idProduto" value="${produto.idProduto}">
                                                        <button type="submit" class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">Adicionar na Lista de Desejos</span></button>
                                                    </form>
                                                </div>
                                            </div>
                                            <form  action="produtoPage" method="post" class="add-to-cart">
                                                <input type="hidden" name="idProduto" id="idProduto" value="${produto.idProduto}">
                                                <button type="submit" class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i>Add ao Carrinho</button>
                                            </form>
                                        </div>-
                                    </div>
                            </c:forEach>
                        </div>
                        <div class="store-filter clearfix">                      
                            <ul class="store-pagination">
                                <li class="active">1</li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">4</a></li>
                                <li><a href="#"><i class="fa fa-angle-right"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- jQuery Plugins -->
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/slick.min.js"></script>
        <script src="js/nouislider.min.js"></script>
        <script src="js/jquery.zoom.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
