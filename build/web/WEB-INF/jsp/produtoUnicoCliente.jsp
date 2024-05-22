<%-- 
    Document   : produtoUnicoCliente
    Created on : 05/05/2024, 20:41:25
    Author     : paulo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Evelin Verissimo | Produto</title>

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

            <div class="section">
                <div class="container">
                    <div class="row">
                        <div class="col-md-5 col-md-push-2">
                            <div id="product-main-img">
                                <div class="product-preview">
                                    <img src="data:image/jpeg;base64,${produtos.imagemBase64}" alt="${produto.nome}">
                            </div>
                            <div class="product-preview">
                                <img src="data:image/jpeg;base64,${produtos.imagemBase64}" alt="${produto.nome}">
                            </div>
                            <div class="product-preview">
                                <img src="data:image/jpeg;base64,${produtos.imagemBase64}" alt="${produto.nome}">
                            </div>
                            <div class="product-preview">
                                <img src="data:image/jpeg;base64,${produtos.imagemBase64}" alt="${produto.nome}">
                            </div>
                        </div>
                    </div>

                    <div class="col-md-2  col-md-pull-5">
                        <div id="product-imgs">
                            <div class="product-preview">
                                <img src="data:image/jpeg;base64,${produtos.imagemBase64}" alt="${produtos.nome}">
                            </div>

                            <div class="product-preview">
                                <img src="data:image/jpeg;base64,${produtos.imagemBase64}" alt="${produtos.nome}">
                            </div>

                            <div class="product-preview">
                                <img src="data:image/jpeg;base64,${produtos.imagemBase64}" alt="${produtos.nome}">
                            </div>

                            <div class="product-preview">
                                <img src="data:image/jpeg;base64,${produtos.imagemBase64}" alt="${produtos.nome}">
                            </div>
                        </div>
                    </div>

                    <div class="col-md-5">
                         <form method="post" action="adicionarProduto" name="frmAdicionar" class="product-details">
                             
                             <input type="hidden" name="idProduto" id="idProduto" value="${produto.idProduto}">
                             
                            <h2 class="product-name">${produtos.nome}</h2>
                            <div>
                                <div class="product-rating">
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                </div>
                            </div>
                            <div>
                                <h3 class="product-price">$${produtos.valor} <del class="product-old-price">$990.00</del></h3>
                                <span class="product-available">Em estoque</span>
                            </div>
                            <p>${produtos.descricao}</p>

                            <div class="product-options">
                                <label>
                                    Peso
                                    <select class="input-select">
                                        <option value="0">X</option>
                                    </select>
                                </label>

                            </div>

                            <div class="add-to-cart">   
                                <div class="qty-label">
                                    Quantidade
                                    <div class="input-number">
                                        <input type="number" name="qtd" id="qtd">
                                        <span class="qty-up">+</span>
                                        <span class="qty-down">-</span>
                                    </div>
                                </div>

                                <button type="submit" class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> Adicionar ao Carrinho</button>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-12">
                        <div id="product-tab">
                            <ul class="tab-nav">
                                <li class="active"><a data-toggle="tab" href="#tab1">Description</a></li>
                            </ul>
                            <div class="tab-content">
                                <div id="tab1" class="tab-pane fade in active">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <p>${produto.descricao}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
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
