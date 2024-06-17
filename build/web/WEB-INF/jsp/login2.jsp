<%-- 
    Document   : login2
    Created on : 31/05/2024, 21:33:41
    Author     : paulo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
            href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.2.0/mdb.min.css"
            rel="stylesheet"
            />
        <link rel="stylesheet" href="./styles/cadastro.css">
        <link rel="stylesheet" href="css/login.css">
        <title>JSP Page</title>
    </head>
    <body>

        <main>
            <div class="container-left">
                <div class="sair">
                    <a id="sair" href="./inicio"><p><i class="fa-solid fa-arrow-left"></i></p></a>
                    
                </div>
                <img src="./img/LogoEvelinVerissimoLogin.png" alt="">
                <div class="sair">

                </div>
            </div>
            <div class="container-right">

                <div class="inputs">
                    <h2 class="text-uppercase text-center mb-5">Login</h2>

                    <form method="post" action="logar" class="card-body p-5 text-center">

     
                        <div data-mdb-input-init class="form-outline mb-4">
                            <input type="email" id="email"  name="email" class="form-control form-control-lg"  required=""/>
                            <label class="form-label" for="form3Example3cg">Seu Email</label>
                        </div>

                        <div data-mdb-input-init class="form-outline mb-4">
                            <input type="password" id="senha" name="senha" class="form-control form-control-lg" required=""/>
                            <label class="form-label" for="form3Example4cg">Senha</label>
                        </div>
                        <div class="d-flex justify-content-center">
                            <button type="submit" class="btn-login">Login</button>
                        </div>

                        <p class="text-center text-muted mt-5 mb-0">Não tem conta? <a href="./cadastroUsuario">Cadastrar-se</a>
                        <c:if test="${not empty errorMessage}">
                            <div style="color: red;">
                                ${errorMessage}
                            </div>

                        </c:if>
                    </form>
                </div>
            </div>
        </main>
    </body>
    <script
        type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.2.0/mdb.umd.min.js"
    ></script>
</html>
