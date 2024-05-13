<%@ page import="model.dao.UsuarioDAO" %>
<%@ page import="model.bean.Usuario" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<!DOCTYPE html>
<html lang="pt-br"> <%-- Corrigido de pr-br para pt-br --%>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>Electro - HTML Ecommerce Template</title>

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
        <link rel="stylesheet" href="./styles/home.css">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>
    <body>
        <!-- HEADER -->
    <header>
        <jsp:include page="header.jsp"></jsp:include>

            <!-- SECTION -->
            <div class="section">
                <!-- container -->
                <div class="container">
                    <!-- row -->
                    <div class="row">
                        <!-- shop -->
                        <div class="col-md-4 col-xs-6">
                            <div class="shop">
                                <div class="shop-img">
                                    <img src="./img/pngtree-colorful-birthday-cake-decorated-with-melted-strawberries-png-image_13063823.png" alt="">
                                </div>
                                <div class="shop-body">
                                    <h3>Tipos<br>De Bolos</h3>
                                    <a href="#" class="cta-btn">Compre agora <i class="fa fa-arrow-circle-right"></i></a>
                                </div>
                            </div>

                        </div>
                        <!-- /shop -->

                        <!-- shop -->
                        <div class="col-md-4 col-xs-6">
                            <div class="shop">
                                <div class="shop-img">
                                    <img src="./img/pngtree-colorful-birthday-cake-decorated-with-melted-strawberries-png-image_13063823.png" alt="">
                                </div>
                                <div class="shop-body">
                                    <h3>Tipos<br>De Salgados</h3>
                                    <a href="#" class="cta-btn">Compre agora <i class="fa fa-arrow-circle-right"></i></a>
                                </div>
                            </div>
                        </div>
                        <!-- /shop -->

                        <!-- shop -->
                        <div class="col-md-4 col-xs-6">
                            <div class="shop">
                                <div class="shop-img">
                                    <img src="./img/pngtree-colorful-birthday-cake-decorated-with-melted-strawberries-png-image_13063823.png" alt="">
                                </div>
                                <div class="shop-body">
                                    <h3>Tipos<br>De Doces</h3>
                                    <a href="#" class="cta-btn">Compre agora <i class="fa fa-arrow-circle-right"></i></a>
                                </div>
                            </div>
                        </div>
                        <!-- /shop -->
                    </div>
                    <!-- /row -->
                </div>
                <!-- /container -->
            </div>
            <!-- /SECTION -->

            <!-- SECTION -->
            <div class="section">
                <!-- container -->
                <div class="container">
                    <!-- row -->
                    <div class="row">

                        <!-- section title -->
                        <div class="col-md-12">
                            <div class="section-title">
                                <h3 class="title">Novos Produtos</h3>
                                <div class="section-nav">
                                    <ul class="section-tab-nav tab-nav">
                                    <c:forEach items="${categorias}" var="categoria">
                                        <li ><a href="./lista?cat=${categoria.idCategoria}">${categoria.nome}</a></li>
                                        </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!-- /section title -->

                    <!-- Products tab & slick -->
                    <c:if test="${not empty produtos}">
                        <div class="col-md-12">
                            <div class="row">
                                <div class="products-tabs">
                                    <!-- tab -->
                                    <div id="tab1" class="tab-pane active">
                                        <div class="products-slick" data-nav="#slick-nav-1">
                                            <!-- product -->
                                            <c:forEach items="${produtos}" var="produto">
                                                <a href="./produto-unico?idPro=${produto.idProduto}">
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
                                                            <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> Adicionar ao carrinho</button>
                                                        </div>
                                                    </div>
                                                </a>
                                            </c:forEach>
                                            <!-- /product -->
                                        </div>
                                        <div id="slick-nav-1" class="products-slick-nav"></div>
                                    </div>
                                    <!-- /tab -->
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${empty produtos}">
                        <p>Nenhum produto encontrado.</p>
                    </c:if>

                    <!-- Products tab & slick -->
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /SECTION -->

        <!-- HOT DEAL SECTION -->
        <div id="hot-deal" class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">
                    <div class="col-md-12">
                        <div class="hot-deal">

                        </div>
                    </div>
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /HOT DEAL SECTION -->

        <!-- SECTION -->
        <div class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">

                    <!-- section title -->
                    <div class="col-md-12">
                        <div class="section-title">
                            <h3 class="title">Mais Vendidos</h3>
                            <div class="section-nav">
                                <ul class="section-tab-nav tab-nav">
                                    <c:forEach items="${categorias}" var="categoria">
                                        <li ><a href="./lista?cat=${categoria.idCategoria}">${categoria.nome}</a></li>
                                        </c:forEach>

                                </ul>
                            </div>
                        </div>
                    </div>
                    <!-- /section title -->

                    <!-- Products tab & slick -->
                    <div class="col-md-12">
                        <div class="row">
                            <div class="products-tabs">
                                <!-- tab -->
                                <div id="tab2" class="tab-pane fade in active">
                                    <div class="products-slick" data-nav="#slick-nav-2">
                                        <!-- product -->
                                        <c:forEach items="${produtos}" var="produto">
                                            <a href="./produto-unico?idPro=${produto.idProduto}">
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
                                                            <button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
                                                            <button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>
                                                            <button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
                                                        </div>
                                                    </div>
                                                    <div class="add-to-cart">
                                                        <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> Add ao carrinho</button>
                                                    </div>
                                                </div>
                                            </a>
                                        </c:forEach>
                                        <!-- /product -->

                                    </div>
                                    <div id="slick-nav-2" class="products-slick-nav"></div>
                                </div>
                                <!-- /tab -->
                            </div>
                        </div>
                    </div>
                    <!-- /Products tab & slick -->
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /SECTION -->


        <!-- FOOTER -->
        <footer id="footer">
            <!-- top footer -->
            <div class="section">
                <!-- container -->
                <div class="container">
                    <!-- row -->
                    <div class="row">
                        <div class="col-md-3 col-xs-6">
                            <div class="footer">
                                <h3 class="footer-title">Sobre nos</h3>
                                <p>Empresa a mais de 10 anos no comercio, especializa em bolos, doces e salgados.</p>
                                <ul class="footer-links">
                                    <li><a href="#"><i class="fa fa-map-marker"></i>Rua augusto gomes 919 | londrina</a></li>
                                    <li><a href="#"><i class="fa fa-phone"></i>43991950011</a></li>
                                    <li><a href="#"><i class="fa fa-envelope-o"></i>pauloevelin2007@gmail.com</a></li>
                                </ul>
                            </div>
                        </div>

                        <div class="col-md-3 col-xs-6">
                            <div class="footer">
                                <h3 class="footer-title">Categorias</h3>
                                <ul class="footer-links">
                                    <c:forEach items="${categorias}" var="categoria">
                                        <li>${categoria.nome}</li>
                                        </c:forEach>
                                </ul>
                            </div>
                        </div>

                        <div class="clearfix visible-xs"></div>

                        <div class="col-md-3 col-xs-6">
                            <div class="footer">
                                <h3 class="footer-title">Informacoes</h3>
                                <ul class="footer-links">
                                    <li><a href="#">Sobre nos</a></li>
                                    <li><a href="#">Contato</a></li>
                                    <li><a href="#">Politica de Privacidade</a></li>
                                    <li><a href="#">Bebidas</a></li>
                                    <li><a href="#">Combos</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!-- /row -->
                </div>
                <span class="copyright">
                    <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                    Copyright ©2024 todos direitos reservados 
                    <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                </span>
                <!-- /container -->
            </div>
            <!-- /top footer -->
        </footer>
        <!-- /FOOTER -->

        <!-- jQuery Plugins -->
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/slick.min.js"></script>
        <script src="js/nouislider.min.js"></script>
        <script src="js/jquery.zoom.min.js"></script>
        <script src="js/main.js"></script>

    </body>
</html>
