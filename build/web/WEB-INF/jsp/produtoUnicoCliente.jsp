<%-- 
    Document   : produtoUnicoCliente
    Created on : 05/05/2024, 20:41:25
    Author     : paulo
--%>
<%@page import="model.bean.Produto"%>
<%@page import="model.dao.ProdutoDAO"%>
<%@page import="model.dao.CategoriaDAO"%>
<%@page import="model.bean.Projeto"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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

        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>


    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>

            <div class="section">
                <div class="container">
                    <div class="row">
                        <div class="col-md-5 col-md-push-2">
                        <c:forEach items="${imagensProdutos}" var="imagem">
                            <c:choose>
                                <c:when test="${imagem.imagemPadrao}">
                                    <div id="product-main-img">
                                        <div class="product-preview">
                                            <img src="data:image/jpeg;base64,${imagem.imagemBase64}">
                                    </div>
                                 </c:when>
                                <c:otherwise> 
                                    <div class="product-preview">
                                        <img src="data:image/jpeg;base64,${imagem.imagemBase64}" >
                                    </div>
                                </c:otherwise>
                             </c:choose>
                        </c:forEach>
                        </div>
                    </div>

                    <div class="col-md-2 col-md-pull-5">
                        <div id="product-imgs">
                        <c:forEach items="${imagensProdutos}" var="imagem">
                            <div class="product-preview">
                                <img src="data:image/jpeg;base64,${imagem.imagemBase64}">
                            </div>
                        </c:forEach>
                        </div>
                    </div>

                    <div class="col-md-5">
                        <form method="post" id="myForm" action="adicionarProduto" name="frmAdicionar" class="product-details">
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

                                <c:forEach items="${sabores}" var="s">
                                    <label>
                                        <h4>${s.nome}</h4>
                                        <div id="sabores">
                                            <c:forEach items="${saboresEspecificos}" var="sa">
                                                <div class="checkbox">
                                                    <label style="min-height: 0px;">
                                                        <c:if test="${s.idSabor == sa.idPai}">
                                                            <input type="checkbox" name="sabor" value="${sa.idSabor}" data-nome="${sa.nome}" data-valor="${sa.valorAdicional}" onchange="updateSelectedFlavors()">
                                                            ${sa.nome} <span>+${sa.valorAdicional}</span>
                                                        </c:if>
                                                    </label>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </label>
                                </c:forEach>
                                <div class="qty-label">
                                    <!-- Mensagem de erro para seleção de sabores -->
                                    <c:if test="${not empty erroSabor}">
                                        <div class="alert alert-danger">
                                            ${erroSabor}
                                        </div>
                                    </c:if>

                                    <div id="selectedFlavorsSummary">
                                        <h3>Sabores Selecionados:</h3>
                                        <ul id="selectedFlavorsList"></ul>
                                        <p id="totalAdditionalCost"></p>
                                    </div>

                                    <div id="error-message" class="alert alert-danger" style="display: none;">
                                        Por favor, selecione pelo menos um sabor.
                                    </div>

                                    <hr>
                                    Quantidade(em centos 1 = 100 unidades)
                                    <div class="input-number">
                                        <input type="number" value="1" step="1" name="qtd" id="qtd" required="">
                                    </div>
                                </div>

                            </div>

                            <div class="add-to-cart">
                                <c:choose>
                                    <c:when test="${usuarios != 0}">
                                        <button type="submit"  onclick="validateForm()" class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> Adicionar ao Carrinho</button>
                                        <c:if test="${not empty existeCarrinho}">
                                            <div style="color: red;">
                                                ${existeCarrinho}
                                            </div>
                                        </c:if>
                                    </c:when> 
                                    <c:otherwise>
                                        <button type="submit" onclick="showAlert(event)" class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> Adicionar ao Carrinho</button>

                                    </c:otherwise>


                                </c:choose>
                                <script>
                                    function showAlert(event) {
                                        event.preventDefault();
                                        swal('Opa! Calma ae...', 'Você precisa estar logado para adicionar ao carrinho!', 'error');
                                    }

                                    function updateSelectedFlavors() {
                                        var checkboxes = document.querySelectorAll('#sabores input[type="checkbox"]');
                                        var selectedFlavors = [];
                                        var totalAdditionalCost = 0;

                                        checkboxes.forEach(function (checkbox) {
                                            if (checkbox.checked) {
                                                var flavorName = checkbox.getAttribute('data-nome');
                                                var flavorCost = parseFloat(checkbox.getAttribute('data-valor'));
                                                selectedFlavors.push(flavorName + ' (+$' + flavorCost.toFixed(2) + ')');
                                                totalAdditionalCost += flavorCost;
                                            }
                                        });

                                        var selectedFlavorsList = document.getElementById('selectedFlavorsList');
                                        selectedFlavorsList.innerHTML = '';

                                        selectedFlavors.forEach(function (flavor) {
                                            var li = document.createElement('li');
                                            li.textContent = flavor;
                                            selectedFlavorsList.appendChild(li);
                                        });

                                        var totalAdditionalCostElement = document.getElementById('totalAdditionalCost');
                                        totalAdditionalCostElement.textContent = 'Custo adicional total: $' + totalAdditionalCost.toFixed(2);
                                    }

                                    function validateForm() {
                                        var checkboxes = document.querySelectorAll('#sabores input[type="checkbox"]');
                                        var isChecked = false;

                                        checkboxes.forEach(function (checkbox) {
                                            if (checkbox.checked) {
                                                isChecked = true;
                                            }
                                        });

                                        if (!isChecked) {
                                            document.getElementById('error-message').style.display = 'block';
                                        } else {
                                            document.getElementById('error-message').style.display = 'none';
                                            // Submeter o formulário aqui
                                            document.forms["myForm"].submit();
                                        }
                                    }


                                </script>
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

        <script>
            document.addEventListener('DOMContentLoaded', function () {
                var input = document.querySelector('.input-number input[type="number"]');
                var btnUp = document.querySelector('.qty-up');
                var btnDown = document.querySelector('.qty-down');
                var step = parseFloat(input.getAttribute('step'));
                var min = parseFloat(input.getAttribute('min'));

                btnUp.addEventListener('click', function () {
                    var oldValue = parseFloat(input.value);
                    var newVal = oldValue + step;
                    input.value = newVal.toFixed(1);
                });

                btnDown.addEventListener('click', function () {
                    var oldValue = parseFloat(input.value);
                    if (oldValue > min) {
                        var newVal = oldValue - step;
                        input.value = newVal.toFixed(1);
                    }
                });
            });
        </script>

        <!-- jQuery Plugins -->
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/slick.min.js"></script>
        <script src="js/nouislider.min.js"></script>
        <script src="js/jquery.zoom.min.js"></script>
        <script src="js/main.js"></script>

    </body>
</html>
