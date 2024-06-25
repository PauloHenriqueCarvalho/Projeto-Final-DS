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

        <title>Evelin Verissimo | Pagamento</title>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
         <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
            rel="stylesheet"
            />
        <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>
        <link type="text/css" rel="stylesheet" href="css/slick.css"/>
        <link type="text/css" rel="stylesheet" href="css/slick-theme.css"/>
        <link type="text/css" rel="stylesheet" href="css/nouislider.min.css"/>
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="./styles/home.css">
        <link type="text/css" rel="stylesheet" href="css/style.css"/>
        <link type="text/css" rel="stylesheet" href="css/checkout.css"/>
        <link type="text/css" rel="stylesheet" href="css/pagamento.css"/>
        <script src="http://code.jquery.com/jquery-3.7.1.js"></script>
        <script src="http://jqueryvalidation.org/files/dist/jquery.validate.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.js"></script> 

    </head>
    <body>
        <header>
            <div id="top-header">
                <div class="container">
                    <ul class="header-links pull-left">
                        <li><a href="#"><i class="fa fa-phone"></i> 43 9 99603-5529 </a></li>
                        <li><a href="#"><i class="fa fa-envelope-o"></i> pauloevelin2007@Gmail.com</a></li>
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
                                <form class="form-inline my-2 my-lg-0" action="buscar" method="GET">
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


                                                                <h4 class="product-price"><span class="qty">${carrinho.quantidade}x</span>$${carrinho.formatadoValorAdicional()}</h4>


                                                            </div>
                                                            <form action="deletarProduto" method="post">
                                                                <input type="hidden" id="idProduto" name="idProduto" value="${carrinho.idProduto_Carrinho}">
                                                                <button type="submit" onclick="removeCarrinho(event)" class="delete"><i class="fa fa-close"></i></button>
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

        <section class="vh-10" style="background-color: #f3f3f3 ;">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-12">
                        <div class="card card-stepper" style="border-radius: 16px;">

                            <div class="card-body p-5">

                                <ul id="progressbar-2" class="d-flex justify-content-between mx-0 mt-0 mb-5 px-0 pt-0 pb-2">
                                    <li class="step0 active text-center" id="step1"></li>
                                    <li class="step0 active text-center" id="step2"></li>
                                    <li class="step0 active text-end" id="step3"></li>
                                </ul>

                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </section>
        <hr>
        <form id="payment-form" action="finalizar-compra" method="post">
            <div class="container-endereco">
                <div class="payment-methods">
                    <h2>ESCOLHA A FORMA DE PAGAMENTO</h2>
                    <div class="method">
                        <input type="radio" id="pix" name="payment" value="pix" checked>
                        <label for="pix">
                            <img src="img/logo-pix-1024.png" alt="Pix">
                            <span>PAGUE COM PIX</span>
                        </label>
                        <div class="pix-info">

                        </div>
                    </div>
                    <div class="method">
                        <input type="radio" id="cartao" name="payment" value="cartao">
                        <label for="cartao">
                            <img src="img/cartao.jpg" alt="Cartão">
                            <span>PAGUE COM CARTÃO</span>
                        </label>
                        <div class="card-info" style="display: none;">
                            <input type="text" placeholder="Número do Cartão" id="card-number" name="card-number">
                            <small class="error-message" id="card-number-error"></small>
                            <input type="text" placeholder="Nome no Cartão"  id="card-name"  name="card-name" onkeypress="validateInput(event)">

                            <small class="error-message" id="card-name-error"></small>
                            <input type="text" placeholder="Validade (MM/AA)" id="card-expiry" name="card-expiry">
                            <small class="error-message" id="card-expiry-error"></small>
                            <input type="text" placeholder="CVV" id="card-cvv" name="card-cvv">
                            <small class="error-message" id="card-cvv-error"></small>
                        </div>
                    </div>
                </div>
                <div class="summary">
                    <div class="address">
                        <p>${e.logradouro} ${e.numero}<br>${e.localidade} - ${e.uf} ${e.cep}</p>
                        <p><strong>Frete:</strong>Entrega Propria<br>Entrega ${data}</p>
                    </div>

                    <div class="prices">
                        <p><span>Subtotal</span><span>R$ ${total}</span></p>
                        <p><span>Frete</span><span>R$ 10.00</span></p>
                        <p class="total"><span>TOTAL</span><span>R$ ${totalFinal}</span></p>
                        <button type="submit" onclick="finalizarCompra(event)" id="confirmar">Finalizar Compra</button>
                    </div>
                </div>
            </div>
        </form>
                         <jsp:include page="footer.jsp"></jsp:include>

        <style>
            .error-message {
                color: red;
                font-size: 12px;
            }
        </style>

        <script src="js/pagamento.js"></script>
        <script src="js/validacoes.js"></script>
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/slick.min.js"></script>
        <script src="js/nouislider.min.js"></script>
        <script src="js/jquery.zoom.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
