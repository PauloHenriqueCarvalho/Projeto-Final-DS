<%-- 
    Document   : checkoutRevisarCarrinho
    Created on : 01/06/2024, 20:14:01
    Author     : paulo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.3.0/mdb.min.css"
            rel="stylesheet"
            /> 

        <script
            type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.3.0/mdb.umd.min.js"
        ></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

        <!-- Font Awesome -->
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
            rel="stylesheet"
            />
        <!-- Google Fonts -->
        <link
            href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
            rel="stylesheet"
            />
        <!-- MDB -->

        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>


        <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>
        <link type="text/css" rel="stylesheet" href="css/slick.css"/>
        <link type="text/css" rel="stylesheet" href="css/slick-theme.css"/>
        <link type="text/css" rel="stylesheet" href="css/nouislider.min.css"/>
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="./styles/home.css">
        <link type="text/css" rel="stylesheet" href="css/style.css"/>

        <link rel="stylesheet" href="./css/checkout.css">

        <title>JSP Page</title>
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
        <!-- /HEADER -->
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
        <main>
            <section class="h-100 gradient-custom">
                <div class="container py-5">

                    <div class="col-md-8">
                        <div class="card mb-4">
                            <div class="card-header py-3">
                                <h5 class="mb-0">Carrinho - ${qtdCarrinho} items</h5>
                            </div>
                            <div class="card-body" id="produto-carrinho">
                                <c:forEach items="${carrinhos}" var="c">
                                    <div class="row">
                                        <div class="col-lg-3 col-md-12 mb-4 mb-lg-0">
                                            <div class="bg-image hover-overlay hover-zoom ripple rounded" data-mdb-ripple-color="light">
                                                <img class="w-100" src="data:image/jpeg;base64,${c.imagemBase64}" alt="${c.nome}">
                                                <a href="#!">
                                                    <div class="mask" style="background-color: rgba(251, 251, 251, 0.2)"></div>
                                                </a>
                                            </div>
                                        </div>
                                        <div class="col-lg-5 col-md-6 mb-4 mb-lg-0">
                                            <p><strong>${c.nome}</strong></p>
                                            <c:choose>
                                                <c:when test="${c.categoria == 1}">
                                                    <p>Massa: ${c.idMassa.nome}</p>
                                                    <p>Recheio: ${c.idRecheio.nome}</p>
                                                    <p>Cobertura: ${c.idCobertura.nome}</p>
                                                    <p>Topper: ${c.idTopper.nome}</p>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:forEach items="${sabores}" var="s">
                                                        <c:choose>
                                                            <c:when test="${s.idProdutoCarrinho.idProdutoCarrinho == c.idProduto_Carrinho}">
                                                                <p>Sabor: ${s.idSabor.nome}</p>
                                                            </c:when>
                                                        </c:choose>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>
                                            <form action="deletarProdutoCarrinho" method="post">
                                                <input type="hidden" id="idProduto" name="idProduto" value="${c.idProduto_Carrinho}">
                                                <button type="submit" onclick="removeCarrinho(event)" class="btn btn-primary btn-sm me-1 mb-2" title="Remove item">
                                                    <i class="fas fa-trash"></i>
                                                </button>
                                            </form>              
                                            <form class="product-btns" action="listaDesejosCarrinho" method="post">
                                                <input type="hidden" name="idProduto" id="idProduto" value="${c.idProduto}">
                                                <c:choose>
                                                    <c:when test="${empty usuario}">
                                                        <button type="submit" onclick="showAlert4(event)" class="btn btn-danger btn-sm mb-2">
                                                            <i class="fa fa-heart-o"></i>
                                                            <span class="tooltipp"></span>
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

                                        <form id="checkout-form" action="atualizarCarrinho" method="post" class="col-lg-4 col-md-6 mb-4 mb-lg-0">
                                            <div class="d-flex mb-4" style="max-width: 300px">
                                                <button class="btn btn-primary px-3 me-2" onclick="this.parentNode.querySelector('input[type=number]').stepDown()">
                                                    <i class="fas fa-minus"></i>
                                                </button>
                                                <div class="form-outline">
                                                    <input id="form1" min="0" name="quantity_${c.idProduto_Carrinho}" value="${c.quantidade}" type="number" class="form-control" />
                                                </div>
                                                <button class="btn btn-primary px-3 ms-2" onclick="this.parentNode.querySelector('input[type=number]').stepUp()">
                                                    <i class="fas fa-plus"></i>
                                                </button>
                                            </div>
                                            <c:choose>
                                                <c:when test="${c.categoria == 1}">
                                                    <label class="txt-qtd" for="form1">KG </label>
                                                </c:when>
                                                <c:otherwise>
                                                    <label class="txt-qtd" for="form1">Centos (1 cento = 100) </label>
                                                </c:otherwise>
                                            </c:choose>
                                            <p class="text-start text-md-center">
                                                <strong>$${c.valorAdicional}</strong>
                                            </p>
                                        </form>>
                                    </div>
                                    <hr class="my-4" />
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card mb-4">
                            <div class="card-header py-3">
                                <h5 class="mb-0">Resumo</h5>
                            </div>
                            <form action="continuar-checkout" method="post" class="card-body">
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-0">
                                        Produtos
                                        <span>R$${total}</span>
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between align-items-center px-0">
                                        Frete
                                        <span id="shipping-cost">R$10.00</span>
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 mb-3">
                                        <div>
                                            <strong>Total do Pedido</strong>
                                            <strong>
                                                <p class="mb-0">(incluindo FRETE)</p>
                                            </strong>
                                        </div>
                                        <span><strong id="total-cost">R$${total + 10}</strong></span>
                                    </li>
                                </ul>

                                <button type="submit" class="btn btn-primary btn-lg btn-block">Continuar Checkout</button>
                                <c:if test="${not empty sessionScope.feedback}">
                                    <div id="update-feedback" class="alert alert-success">${sessionScope.feedback}</div>
                                    <c:remove var="feedback" scope="session"/>
                                </c:if>
                                <c:if test="${not empty sessionScope.continuarError}">
                                    <div class="alert alert-danger">${sessionScope.continuarError}</div>
                                    <c:remove var="continuarError" scope="session"/>
                                </c:if>
                            </form>
                        </div>


                    </div>
            </section>

            <script>

                document.getElementById('checkout-form').addEventListener('submit', function (event) {
                    const quantities = document.querySelectorAll('input[name^="quantity_"]');
                    let valid = true;
                    quantities.forEach(input => {
                        if (input.value <= 0) {
                            valid = false;
                            input.classList.add('is-invalid');
                            mostrarMensagem("danger", "Quantidade deve ser maior que zero.");
                        } else {
                            input.classList.remove('is-invalid');
                        }
                    });

                    if (!valid) {
                        event.preventDefault();
                    }
                });


                function removeCarrinho(event) {
                    event.preventDefault();
                    swal('Removido Com Sucesso!', 'Não deixe para depois, compre agora!', 'success').then(() => {
                        event.target.closest('form').submit();
                    });
                }

            </script>


        </main>
    </body>
    <!-- MDB -->
    <script src=https://cdnjs.cloudflare.com/ajax/libs/jquery/1.4.4/jquery.min.js type="text/javascript"></script>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/slick.min.js"></script>
    <script src="js/nouislider.min.js"></script>
    <script src="js/jquery.zoom.min.js"></script>
    <script src="js/main.js"></script>
</html>
