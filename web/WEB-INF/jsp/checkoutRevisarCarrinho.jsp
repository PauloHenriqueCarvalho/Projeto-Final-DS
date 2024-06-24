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

        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
            rel="stylesheet"
            />
        <link
            href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
            rel="stylesheet"
            />

        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>


        <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>
        <link type="text/css" rel="stylesheet" href="css/slick.css"/>
        <link type="text/css" rel="stylesheet" href="css/slick-theme.css"/>
        <link type="text/css" rel="stylesheet" href="css/nouislider.min.css"/>
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="./styles/home.css">
        <link type="text/css" rel="stylesheet" href="css/style.css"/>

        <link rel="stylesheet" href="./css/checkout.css">

        <title>Evelin Verissimo | Revisar</title>
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
        <section class="vh-10" style="background-color: #fff;">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-12">
                        <div class="card card-stepper" style="border-radius: 16px;">

                            <div class="card-body p-5">

                                <ul id="progressbar-2" class="d-flex justify-content-between mx-0 mt-0 mb-5 px-0 pt-0 pb-2">
                                    <li class="step0 active text-center" id="step1"></li>
                                    <li class="step0 text-muted text-center" id="step2"></li>
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

                                            <c:forEach items="${sabores}" var="s">
                                                <c:choose>
                                                    <c:when test="${s.idProdutoCarrinho.idProdutoCarrinho == c.idProduto_Carrinho}">
                                                        <p>Sabor: ${s.idSabor.nome}</p>
                                                    </c:when>
                                                </c:choose>
                                            </c:forEach>

                                            <form action="deletarProdutoCarrinho" method="post">
                                                <input type="hidden" id="idProduto" name="idProduto" value="${c.idProduto_Carrinho}">
                                                <button type="submit" onclick="removeCarrinho(event)" class="btn btn-primary btn-sm me-1 mb-2" title="Remove item">
                                                    <i class="fas fa-trash"></i>
                                                </button>
                                            </form>              
                                            <form class="product-btns" action="listaDesejosCarrinho" method="post">
                                                <input type="hidden" name="idProduto" id="idProduto" value="${c.idProduto}">

                                                <button type="submit" onclick="showAlert4(event)" class="btn btn-danger btn-sm mb-2">
                                                    <i class="fa fa-heart-o"></i>
                                                    <span class="tooltipp"></span>
                                                </button>



                                            </form>
                                        </div>

                                        <div id="checkout-form" class="col-lg-4 col-md-6 mb-4 mb-lg-0">
                                            <div class="d-flex mb-4" style="max-width: 300px">
                                                <form action="atualizarCarrinhoDiminuir" method="post">
                                                    <input type="hidden" value="${c.quantidade}" id="qtd" name="qtd">
                                                    <input type="hidden" value="${c.idProduto_Carrinho}" id="idProdutoCarrinho" name="idProdutoCarrinho">
                                                    <button type="submit" class="btn btn-primary px-3 ms-2">
                                                        <i class="fas fa-minus"></i>
                                                    </button>
                                                </form>
                                                <div class="form-outline">
                                                    <input id="form1" min="0" name="quantity_${c.idProduto_Carrinho}" value="${c.quantidade}" type="number" class="form-control" />
                                                </div>
                                                <form action="atualizarCarrinhoAdicionar" method="post">
                                                    <input type="hidden" value="${c.quantidade}" id="qtd" name="qtd">
                                                    <input type="hidden" value="${c.idProduto_Carrinho}" id="idProdutoCarrinho" name="idProdutoCarrinho">
                                                    <button type="submit" class="btn btn-primary px-3 me-2">
                                                        <i class="fas fa-plus"></i>
                                                    </button>
                                                </form>
                                            </div>


                                            <label class="txt-qtd" for="form1">Centos (1 cento = 100) </label>

                                            <p class="text-start text-md-center">
                                                <strong>$${c.formatadoValorAdicional()} x${c.quantidade}</strong>
                                            </p>
                                        </div>>
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
                                        <span><strong id="total-cost">R$${totalFinal}</strong></span>
                                    </li>
                                </ul>
                                <c:choose>
                                    <c:when test="${empty carrinhos}">
                                        <button type="submit" onclick="block(event)" class="btn btn-primary btn-lg btn-block">Continuar Checkout</button>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="submit"  class="btn btn-primary btn-lg btn-block">Continuar Checkout</button>
                                    </c:otherwise>
                                </c:choose>



                            </form>
                        </div>


                    </div>
            </section>
            <jsp:include page="footer.jsp"></jsp:include>               
            <script>
                function removeCarrinho(event) {
                    event.preventDefault();
                    swal('Removido Com Sucesso!', 'Não deixe para depois, compre agora!', 'success').then(() => {
                        event.target.closest('form').submit();
                    });
                }
                function block(event) {
                    event.preventDefault();
                    swal('Opa! Calma ae...', 'Você não tem nenhum produto no carrinho!', 'error');
                }
            </script>
        </main>
    </body>
    <script src=https://cdnjs.cloudflare.com/ajax/libs/jquery/1.4.4/jquery.min.js type="text/javascript"></script>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/slick.min.js"></script>
    <script src="js/nouislider.min.js"></script>
    <script src="js/jquery.zoom.min.js"></script>
    <script src="js/main.js"></script>
</html>
