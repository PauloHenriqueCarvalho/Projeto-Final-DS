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
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.3.0/mdb.min.css"
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
                                <a href="#" class="logo">
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
                                <!-- /Wishlist -->


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

                                <div class="d-flex justify-content-between align-items-center mb-5">
                                    <div>
                                        <h5 class="mb-0">Carrinho <span class="text-primary font-weight-bold"></span></h5>
                                    </div>
                                    <div class="text-end">
                                        <p class="mb-0">Nome <span>01/12/19</span></p>

                                    </div>
                                </div>

                                <ul id="progressbar-2" class="d-flex justify-content-between mx-0 mt-0 mb-5 px-0 pt-0 pb-2">
                                    <li class="step0 active text-center" id="step1"></li>
                                    <li class="step0 text-muted text-center" id="step2"></li>
                                    <li class="step0 text-muted text-center" id="step3"></li>
                                    <li class="step0 text-muted text-end" id="step4"></li>
                                </ul>

                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </section>
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
                                            </</form>>
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
                                            <span id="shipping-cost">Gratis</span>
                                        </li>
                                        <li class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 mb-3">
                                            <div>
                                                <strong>Total do Pedido</strong>
                                                <strong>
                                                    <p class="mb-0">(incluindo FRETE)</p>
                                                </strong>
                                            </div>
                                            <span><strong id="total-cost">R$${total}</strong></span>
                                        </li>
                                    </ul>
                                    <div class="mb-4">
                                        <label for="cep" class="form-label">CEP</label>
                                        <input type="text" name="cep" id="cep" class="form-control" placeholder="Digite seu CEP" />
                                        <button type="button"  class="btn btn-secondary mt-2" onclick="calcularFrete()">Calcular Frete</button>
                                    </div>
                                    <div id="loading-spinner" class="loading-spinner"></div>
                                    <div id="feedback-message"></div>
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
                    
                </div>
            </section>

            <script>
                function validarCEP(cep) {
                    // Verifica se o CEP é válido (apenas números e 8 dígitos)
                    var cepRegex = /^[0-9]{8}$/;
                    return cepRegex.test(cep);
                }

                function mostrarSpinner() {
                    document.getElementById("loading-spinner").style.display = "inline-block";
                }

                function esconderSpinner() {
                    document.getElementById("loading-spinner").style.display = "none";
                }

                function mostrarMensagem(tipo, mensagem) {
                    var feedbackMessage = document.getElementById("feedback-message");
                    feedbackMessage.innerHTML = mensagem;
                    feedbackMessage.className = "alert alert-" + tipo + " show";
                }

                function calcularFrete() {
                    var cep = document.getElementById("cep").value;

                    // Limpa mensagens anteriores
                    document.getElementById("feedback-message").className = "alert"; // Remove a classe 'show' para animação de desaparecimento

                    // Validação do CEP
                    if (!validarCEP(cep)) {

                        mostrarMensagem("danger", "Por favor, insira um CEP válido.");
                        return;
                    }

                    mostrarSpinner();

                    // URL da API de cálculo de frete
                    var apiUrl = "https://www.cepcerto.com/ws/json/" + cep;

                    // Chamada AJAX para calcular o frete usando jQuery
                    $.ajax({
                        url: 'https://cepcerto.com/ws/json/' + cep,
                        method: 'GET',
                        dataType: 'json',
                        success: function (data) {
                            console.log('Dados da API de CEP:', data);

                            let logradouro = data.logradouro;
                            document.getElementById("shipping-cost").textContent = "R$ " + 10;
                            // Obtém o valor atual do custo total (sem o "R$ " e convertido para número)
                            var currentTotalCost = parseFloat(document.getElementById("total-cost").innerText.replace("R$", ""));
                            // Calcula o novo custo total somando o custo do frete ao custo total atual
                            var totalCost = currentTotalCost + 10;

                            document.getElementById("total-cost").textContent = "R$" + totalCost.toFixed(2); // Formata o novo custo total com duas casas decimais

                            mostrarMensagem("success", "Endereço: " + logradouro + ". Custo do frete: R$ " + 10);
                            esconderSpinner();
                        },
                        error: function (error) {
                            esconderSpinner();
                            console.error('Erro na requisição:', error);
                            mostrarMensagem("danger", "Ocorreu um erro ao calcular o frete. Por favor, tente novamente.");
                            console.log('Erro na requisição:', error);
                        }
                    });
                }

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

                document.getElementById('checkout-form').addEventListener('submit', function (event) {
                    event.preventDefault(); // Impede o envio do formulário
                    const form = this;
                    swal({
                        title: "Confirmar Alterações",
                        text: "Você deseja atualizar as quantidades e continuar para o checkout?",
                        icon: "warning",
                        buttons: true,
                        dangerMode: true,
                    }).then((willSubmit) => {
                        if (willSubmit) {
                            form.submit(); // Envia o formulário apenas se o usuário confirmar
                        }
                    });
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
