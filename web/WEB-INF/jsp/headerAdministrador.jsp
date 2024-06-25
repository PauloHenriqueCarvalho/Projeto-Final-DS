<%-- 
    Document   : headerAdministrador
    Created on : 26/05/2024, 20:48:21
    Author     : paulo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header</title>
    </head>
    <body class="toggle-sidebar">
        <header id="header" class="header fixed-top d-flex align-items-center">

            <div class="d-flex align-items-center justify-content-between">
                <a href="./inicioAdministrador" class="logo d-flex align-items-center">
                    <img src="./assets/img/logo.png" alt="">
                    <span class="d-none d-lg-block">Evelin Verissimo</span>
                </a>
               <i id="toggleSidebarBtn" class="bi bi-list toggle-sidebar-btn"></i>

            </div>

            <div class="search-bar">
                <form class="search-form d-flex align-items-center" method="POST" action="#">
                    <input type="text" name="query" placeholder="Search" title="Enter search keyword">
                    <button type="submit" title="Search"><i class="bi bi-search"></i></button>
                </form>
            </div>

            <nav class="header-nav ms-auto">
                <ul class="d-flex align-items-center">

                    </li>

                    <li class="nav-item dropdown pe-3">

                        <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
                            <img src="img/perfil.jpg" alt="Profile" class="rounded-circle">
                            <span class="d-none d-md-block dropdown-toggle ps-2">Administrador</span>
                        </a>

                        <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
                            <li class="dropdown-header">
                                <h6>Administrador</h6>
                                <span>Desenvolvedor</span>
                            </li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>

                            <li>
                                <form action="sair" method="post">
                                    <button type="submit" class="dropdown-item d-flex align-items-center">
                                        <i class="bi bi-box-arrow-right"></i>
                                        <span>Sign Out</span>
                                    </button>
                                </form>

                            </li>
                        </ul>
                    </li>

                </ul>
            </nav>

        </header>
        <aside id="sidebar" class="sidebar">

            <ul class="sidebar-nav" id="sidebar-nav">

                <li class="nav-item">
                    <a class="nav-link " href="./inicioAdministrador">
                        <i class="bi bi-grid"></i>
                        <span>Dashboard</span>
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link collapsed" data-bs-target="#components-nav" data-bs-toggle="collapse" href="#">
                        <i class="bi bi-menu-button-wide"></i><span>Produtos</span><i class="bi bi-chevron-down ms-auto"></i>
                    </a>
                    <ul id="components-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
                        <li>
                            <a href="./cadastroProduto">
                                <i class="bi bi-circle"></i><span>Adicionar Produtos</span>
                            </a>
                        </li>
                        <li>
                            <a href="./cadastroCategoria">
                                <i class="bi bi-circle"></i><span>Categorias</span>
                            </a>
                        </li>
                        <li>
                            <a href="./especificacaoProdutos">
                                <i class="bi bi-circle"></i><span>Todos Produtos</span>
                            </a>
                        </li>
                        
                        

                    </ul>
                </li>

               

                

                <li class="nav-item">
                    <a class="nav-link collapsed" data-bs-target="#charts-nav" data-bs-toggle="collapse" href="#">
                        <i class="bi bi-bar-chart"></i><span>Pedidos</span><i class="bi bi-chevron-down ms-auto"></i>
                    </a>
                    <ul id="charts-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
                        <li>
                            <a href="./historicoPedidosAdministrador">
                                <i class="bi bi-circle"></i><span>Pedidos Atuais</span>
                            </a>
                        </li>
                        <li>
                            <a href="./HistoricoTotalPedidos">
                                <i class="bi bi-circle"></i><span>Historico de Pedidos</span>
                            </a>
                        </li>

                    </ul>
                </li>
        </aside>

    </body>
    <script>
    document.addEventListener("DOMContentLoaded", function() {
        var toggleBtn = document.getElementById("toggleSidebarBtn");
                toggleBtn.addEventListener("click", function() {
            document.body.classList.toggle("toggle-sidebar");
        });
    });
</script>
</html>
