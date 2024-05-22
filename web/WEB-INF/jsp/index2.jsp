<%@ page import="model.dao.UsuarioDAO" %>
<%@ page import="model.bean.Usuario" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Evelin Verissimo</title>
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>
        <link type="text/css" rel="stylesheet" href="css/slick.css"/>
        <link type="text/css" rel="stylesheet" href="css/slick-theme.css"/>
        <link type="text/css" rel="stylesheet" href="css/nouislider.min.css"/>
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link type="text/css" rel="stylesheet" href="css/style.css"/>
        <link rel="stylesheet" href="./styles/home.css">
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>

            <!-- SECTION -->
            <div class="section">
                <div class="container">
                    <div class="row">
                        <div class="col-md-4 col-xs-6">
                            <div class="shop">
                                <div class="shop-img">
                                    <img src="./img/image.png" alt="">
                                </div>
                                <div class="shop-body">
                                    <h3>Tipos<br>De Bolos</h3>
                                    <a href="#" class="cta-btn">Compre agora <i class="fa fa-arrow-circle-right"></i></a>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-4 col-xs-6">
                            <div class="shop">
                                <div class="shop-img">
                                    <img src="./img/imageSalgado.png" alt="">
                                </div>
                                <div class="shop-body">
                                    <h3>Tipos<br>De Salgados</h3>
                                    <a href="#" class="cta-btn">Compre agora <i class="fa fa-arrow-circle-right"></i></a>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-4 col-xs-6">
                            <div class="shop">
                                <div class="shop-img">
                                    <img src="./img/doce-png.webp" alt="">
                                </div>
                                <div class="shop-body">
                                    <h3>Tipos<br>De Doces</h3>
                                    <a href="#" class="cta-btn">Compre agora <i class="fa fa-arrow-circle-right"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /SECTION -->

            <!-- SECTION -->
            <div class="section">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12"> 
                            <div class="section-title">
                                <h3 class="title">Novos Produtos</h3>
                                <div class="section-nav">
                                    <ul class="section-tab-nav tab-nav">
                                    <c:forEach items="${categorias}" var="categoria">
                                        <li><a href="./lista?cat=${categoria.idCategoria}">${categoria.nome}</a></li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <!-- Products tab & slick -->
                    <c:if test="${not empty produtos}">
                        <div class="col-md-12">
                            <div class="row">
                                <div class="products-tabs">
                                    <div id="tab1" class="tab-pane active">
                                        <div class="products-slick" data-nav="#slick-nav-1">
                                            <c:forEach items="${produtos}" var="produto">
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
                                                        <h4 class="product-price">${produto.valor}<del class="product-old-price">$990.00</del></h4>
                                                        <div class="product-rating">
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                        </div>
                                                        <div class="product-btns">
                                                            <button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
                                                            <button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>
                                                            <button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
                                                        </div>
                                                    </div>
                                                    <div class="add-to-cart">
                                                        <!-- Adicionando um evento JavaScript ao botão de adicionar ao carrinho -->
                                                        <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> Adicionar ao carrinho</button>
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

        <div class="section">
            <div class="faixa">
                <div class="left-img">
                    <img src="./img/pngtree-colorful-birthday-cake-decorated-with-melted-strawberries-png-image_13063823.png" alt="">
                </div>
                <div class="center-btn">
                    <div class="btn-radius">02</div>
                    <h3>PROMOCOES DA SEMANA</h3>
                    <h3>PROMOCOES DE ATÉ 30%</h3>
                    <div class="btn-comprar"><p>COMPRE AGORA</p></div>
                </div>
                <div class="right-img">
                    <img src="./img/image-removebg-preview.png" alt="">
                </div>
            </div>
        </div>

        <!-- Products tab & slick -->
        <div class="section">
            <div class="container">
                <div class="row">
                    <div class="col-md-12"> 
                        <div class="section-title">
                            <h3 class="title">Mais Vendidos</h3>
                            <div class="section-nav">
                                <ul class="section-tab-nav tab-nav">
                                    <c:forEach items="${categorias}" var="categoria">
                                        <li><a href="./lista?cat=${categoria.idCategoria}">${categoria.nome}</a></li>
                                        </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <!-- Products tab & slick -->
                    <c:if test="${not empty produtos}">
                        <div class="col-md-12">
                            <div class="row">
                                <div class="products-tabs">
                                    <div id="tab1" class="tab-pane active">
                                        <div class="products-slick" data-nav="#slick-nav-1">
                                            <c:forEach items="${produtos}" var="produto">
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
                                                        <h4 class="product-price">${produto.valor}<del class="product-old-price">$990.00</del></h4>
                                                        <div class="product-rating">
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                        </div>
                                                        <div class="product-btns">
                                                            <button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
                                                            <button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>
                                                            <button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
                                                        </div>
                                                    </div>
                                                    <div class="add-to-cart">
                                                        <!-- Adicionando um evento JavaScript ao botão de adicionar ao carrinho -->
                                                        <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> Adicionar ao carrinho</button>
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


        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/slick.min.js"></script>
        <script src="js/nouislider.min.js"></script>
        <script src="js/jquery.zoom.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
