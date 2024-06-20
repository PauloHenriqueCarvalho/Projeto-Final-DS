<%-- 
    Document   : header
    Created on : 04/05/2024, 11:47:20
    Author     : paulo
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <div id="top-header">
                <div class="container">
                    <ul class="header-links pull-left">
                        <li><a href="#"><i class="fa fa-phone"></i> 43 9 99195-0011 </a></li>
                        <li><a href="#"><i class="fa fa-envelope-o"></i> pauloevelin2007@email.com</a></li>
                        <li><a href="#"><i class="fa fa-map-marker"></i> 919 Augusto Gomes Jd das Palmeiras</a></li>
                    </ul>

                    <ul class="header-links pull-right">
                        <li><a href="#"><i class="fa fa-dollar"></i></a></li>

                        <c:choose>
                            <c:when test="${empty usuario}">
                                <li><a href="./logar"><i class="fa fa-user-o"></i>Login / Cadastro</a></li>                        
                                </c:when>
                                <c:otherwise>                          
                                <li><a href="#"><i class="fa fa-user-o"></i> Minha Conta</a></li>
                                <form action="sair" method="post" class="header-links pull-right">
                                    <button type="submit" class="sair">
                                        <i class="fa-solid fa-right-from-bracket"></i>
                                        <span>Sign Out</span>
                                    </button>
                                </form>
                            </c:otherwise>
                        </c:choose>

                    </ul>
                </div>
            </div>

            <div id="header">
                <div class="container">
                    <div class="row">
                        <div class="col-md-3">
                            <div class="header-logo">
                                <a href="./inicio" class="logo">
                                    <img class="img-logo" src="./img/image.webp" alt="">
                                </a>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="header-search">
                                <form class="form-inline my-2 my-lg-0" action="buscaProdutos" method="GET">
                                    <input id="searchInput" class="input" placeholder="Search here" type="search" name="termo"  aria-label="Search">
                                    <button  class="search-btn" type="submit">Search</button>
                                </form>
                                <div id="searchResults"></div>
                            </div>
                        </div>
                        <div class="col-md-3 clearfix">
                            <div class="header-ctn">
                                <div>
                                    <a href="./lista-desejos"><p class="icons"><i class="fa fa-heart-o"></i></p></a>     
                                </div>

                                <div class="dropdown">
                                    <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                                        <p class="icons"><i class="fa fa-shopping-cart"></i></p>
                                    </a>
                                    <div class="cart-dropdown">
                                        <div class="cart-list">
                                            <c:choose>
                                                <c:when test="${empty carrinhos}">
                                                    <li>
                                                        <p>O seu carrinho está vazio.</p>
                                                    </li>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:forEach items="${carrinhos}" var="carrinho">
                                                        <div class="product-widget">
                                                            <div class="product-img">
                                                                <img class="img-card-cart" src="data:image/jpeg;base64,${carrinho.imagemBase64}" alt="${carrinho.nome}">
                                                            </div>
                                                            <div class="product-body">
                                                                <h3 class="product-name"><a href="#">${carrinho.nome}</a></h3>

                                                                <h4 class="product-price"><span class="qty">${carrinho.quantidade}x</span>$${carrinho.valorAdicional}</h4>


                                                            </div>
                                                            <form action="deletarProduto" method="post">
                                                                <input type="hidden" id="idProduto" name="idProduto" value="${carrinho.idProduto_Carrinho}">
                                                                <button type="submit" onclick="removeCarrinho(event)" class="delete"><i class="fa fa-close"></i></button>

                                                                <script>
                                                                    function removeCarrinho(event) {
                                                                        event.preventDefault();
                                                                        swal('Removido Com Sucesso!', 'Não deixe para depois, compre agora!', 'success').then(() => {
                                                                            event.target.closest('form').submit();
                                                                        });
                                                                    }
                                                                </script>
                                                            </form>

                                                        </div>
                                                    </c:forEach>
                                                </div>
                                                <div class="cart-summary">
                                                    <small>3 Item(s) selected</small>
                                                    <h5>SUBTOTAL: $${total}</h5>
                                                </div>
                                                <div class="cart-btns">
                                                    <a href="./revisar-carrinho">View Cart</a>
                                                    <a href="./revisar-carrinho">Checkout  <i class="fa fa-arrow-circle-right"></i></a>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>       

                                    </div>
                                </div>

                                <div class="menu-toggle">
                                    <a href="#">
                                        <i class="fa fa-bars"></i>
                                        <span>Menu</span>

                                    </a>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>


        <nav id="navigation">
            <div class="container">
                <div id="responsive-nav">
                    <ul class="main-nav nav navbar-nav">
                        <li><a href="./inicio"><i class="fa-solid fa-location-dot"></i>Home</a></li>
                            <c:forEach items="${categorias}" var="categoria">
                            <li ><a href="./lista?cat=${categoria.idCategoria}">${categoria.nome}</a></li>
                            </c:forEach>
                        <li ><a href="./lista?cat=-1">Todos Produtos</a></li>
                            <c:if test="${!empty usuario}">
                            <li><a href="./endereco"><i class="fa-solid fa-location-dot"></i>Endereco</a></li>
                            </c:if>
                    </ul>
                </div>
            </div>
        </nav>
    </body>
    <script src="./js/wishlist.js"></script>
</html>