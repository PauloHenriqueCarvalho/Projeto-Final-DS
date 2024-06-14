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


        <!-- Custom stlylesheet -->
        <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>
        <link type="text/css" rel="stylesheet" href="css/slick.css"/>
        <link type="text/css" rel="stylesheet" href="css/slick-theme.css"/>
        <link type="text/css" rel="stylesheet" href="css/nouislider.min.css"/>
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="./styles/home.css">
        <link type="text/css" rel="stylesheet" href="css/style.css"/>
        <link type="text/css" rel="stylesheet" href="css/checkout.css"/>

    </head>
    <body>
        <header>
            <!-- TOP HEADER -->
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
            <!-- /TOP HEADER -->

            <!-- MAIN HEADER -->
            <div id="header">
                <!-- container -->
                <div class="container">
                    <!-- row -->
                    <div class="row">
                        <!-- LOGO -->
                        <div class="col-md-3">
                            <div class="header-logo">
                                <a href="./inicio" class="logo">
                                    <img class="img-logo" src="./img/image.webp" alt="">
                                </a>
                            </div>
                        </div>
                        <!-- /LOGO -->

                        <!-- SEARCH BAR -->
                        <div class="col-md-6">
                            <div class="header-search">
                                <form class="form-inline my-2 my-lg-0" action="buscar" method="GET">


                                    <input id="searchInput" class="input" placeholder="Search here" type="search" name="termo"  aria-label="Search">
                                    <button  class="search-btn" type="submit">Search</button>
                                </form>
                                <div id="searchResults"></div>
                            </div>
                        </div>


                        <!-- /SEARCH BAR -->

                        <!-- ACCOUNT -->
                        <div class="col-md-3 clearfix">
                            <div class="header-ctn">
                                <!-- Wishlist -->
                                <div>
                                    <a href="./lista-desejos"><p class="icons"><i class="fa fa-heart-o"></i></p></a>     
                                </div>

                                <!-- Cart -->
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
                                                                    <c:choose>
                                                                        <c:when test="${carrinho.categoria == 1}">
                                                                        <h4 class="product-price"><span class="qty">${carrinho.quantidade}kg</span>$${carrinho.valorAdicional}</h4>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <h4 class="product-price"><span class="qty">${carrinho.quantidade}x</span>$${carrinho.valorAdicional}</h4>
                                                                    </c:otherwise>
                                                                </c:choose>

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
                                                    <h5>3, $${total}</h5>
                                                </div>
                                                <div class="cart-btns">
                                                    <a href="./revisar-carrinho">View Cart</a>
                                                    <a href="./revisar-carrinho">Checkout  <i class="fa fa-arrow-circle-right"></i></a>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>       

                                    </div>
                                </div>
                                <!-- /Cart -->

                                <!-- Menu Toogle -->
                                <div class="menu-toggle">
                                    <a href="#">
                                        <i class="fa fa-bars"></i>
                                        <span>Menu</span>

                                    </a>

                                </div>
                                <!-- /Menu Toogle -->
                            </div>
                        </div>
                        <!-- /ACCOUNT -->
                    </div>
                    <!-- row -->
                </div>
                <!-- container -->
            </div>
            <!-- /MAIN HEADER -->
        </header>

        <section class="vh-10" style="background-color: #fff;">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-12">
                        <div class="card card-stepper" style="border-radius: 16px;">

                            <div class="card-body p-5">

                                <ul id="progressbar-2" class="d-flex justify-content-between mx-0 mt-0 mb-5 px-0 pt-0 pb-2">
                                    <li class="step0 active text-center" id="step1"></li>
                                    <li class="step0 active text-center" id="step2"></li>
                                    <li class="step0 text-muted text-end" id="step3"></li>
                                </ul>

                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </section>
        <hr>
         <form id="main-endereco" class="container-endereco" action="continuar-checkout-pagamento" method="post" onsubmit="return validarFormulario()">
            <div class="address">
                <input type="hidden" id="idEndereco" name="idEndereco" value="${e.idEndereco}">
                <h3>Endereco de Entrega</h3>
                <div class="current-address">
                    <p>${e.logradouro} ${e.numero}</p>
                    <p>${e.localidade} ${e.uf} - ${e.cep}</p>
                </div>
                <a href="./endereco"><p class="choose-address">Escolher outro endereco</p></a>
                <div class="row">
                    <div class="col-12 mt-3">
                        <label for="datePicker" class="form-label">Escolha a data de entrega:</label>
                        <input type="date" class="form-control" id="datePicker" name="dataEntrega" placeholder="Selecione a data de entrega" required>
                    </div>
                    <div class="col-12 mt-3">
                        <label for="timePicker" class="form-label">Escolha a hora de entrega:</label>
                        <input type="time" class="form-control" id="timePicker" name="horarioEntrega" placeholder="Selecione o horario de entrega" required>
                    </div>
                </div>
            </div>
            <div class="cart">
                <h3>Seu Carrinho</h3>
                <c:forEach items="${carrinhos}" var="c">
                    <div class="product">
                        <div>
                            <img src="data:image/jpeg;base64,${c.imagemBase64}" alt="${c.nome}" width="50" height="50">
                            <span>${c.nome}</span>
                        </div>
                        <div>
                            <span>${c.quantidade}</span>
                        </div>
                        <div>${c.quantidade}x R$<strong>$${c.valorAdicional}</strong></div>
                    </div>
                </c:forEach>
                <div class="summary">
                    <span>Frete</span>
                    <span>R$10</span>
                </div>
                <div class="summary">
                    <span>Total</span>
                    <span>R$${total + 10}</span>
                </div>
                <button type="submit" class="payment-button">Pagamento</button>
            </div>
        </form>
        <!-- jQuery Plugins -->
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/slick.min.js"></script>
        <script src="js/nouislider.min.js"></script>
        <script src="js/jquery.zoom.min.js"></script>
        <script src="js/main.js"></script>
        <script>
            function validarFormulario() {
                const dataEntrega = document.getElementById('datePicker').value;
                const horarioEntrega = document.getElementById('timePicker').value;
                if (!dataEntrega) {
                    alert('Por favor, selecione a data de entrega.');
                    return false;
                }
                if (!horarioEntrega) {
                    alert('Por favor, selecione a hora de entrega.');
                    return false;
                }
                return true;
            }
        </script>
    </body>
</html>