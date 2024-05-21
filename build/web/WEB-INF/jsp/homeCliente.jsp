<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carrinho de Compras</title>
    </head>
    <body>
        <header>


            <h2>Carrinho de Compras</h2>
            <c:if test="${not empty carrinho}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Produto</th>
                            <th>Quantidade</th>
                            <th>Preço</th>
                            <th>Total</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${carrinho.produtos}" var="item">
                            <tr>
                                <td>${item.produto.nome}</td>
                                <td>${item.quantidade}</td>
                                <td>${item.produto.valor}</td>
                                <td>${item.produto.valor * item.quantidade}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/removerCarrinho?id=${item.produto.idProduto}">Remover</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <h3>Total: ${carrinho.total}</h3>
                <form action="${pageContext.request.contextPath}/finalizarPedido" method="post">
                    <button type="submit">Finalizar Pedido</button>
                </form>
            </c:if>
            <c:if test="${empty carrinho}">
                <p>Seu carrinho está vazio.</p>
            </c:if>
        </header>
    </body>
</html>
