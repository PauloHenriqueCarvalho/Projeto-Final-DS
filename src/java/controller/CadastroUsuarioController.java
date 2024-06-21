/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.Usuario;
import model.dao.UsuarioDAO;

/**
 *
 * @author Senai
 */
public class CadastroUsuarioController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nextPage = "/WEB-INF/jsp/cadastro2.jsp";
        request.setAttribute("errorMessage", null);
        if (Usuario.getIdUsuarioStatic() != 0) {
            UsuarioDAO u = new UsuarioDAO();
            List<Usuario> usuarios = u.getUsuarioById(Usuario.getIdUsuarioStatic());
            request.setAttribute("usuario", usuarios);
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextPage);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String url = request.getServletPath();
    if (url.equals("/cadastrar")) {
        String nextPage = "/WEB-INF/jsp/cadastro2.jsp";

        UsuarioDAO dao = new UsuarioDAO();
        request.removeAttribute("errorMessage");
        String errorMessage = "";

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String confirmarSenha = request.getParameter("confirmarSenha");
        String telefone = request.getParameter("telefone");
        String cpf = request.getParameter("cpf");

        if (nome == null || nome.trim().isEmpty()
                || email == null || email.trim().isEmpty()
                || senha == null || senha.trim().isEmpty()
                || confirmarSenha == null || confirmarSenha.trim().isEmpty()
                || telefone == null || telefone.trim().isEmpty()
                || cpf == null || cpf.trim().isEmpty()) {
            errorMessage = "Todos os campos são obrigatórios.";

        } else if (!senha.equals(confirmarSenha)) {
            errorMessage = "As senhas devem ser iguais";

        } else if (cpf.length() != 14) {
            errorMessage = "Digite o CPF corretamente";

        } else if (telefone.length() != 14) {
            errorMessage = "Digite o telefone corretamente";

        } else if (dao.verificarCpf(cpf)) {
            errorMessage = "Já existe um usuário com esse CPF!";
        } else if (dao.verificarEmail(email)) {
            errorMessage = "Já existe um usuário com esse Email!";
        } else if (dao.verificarTelefone(telefone)) {
            errorMessage = "Já existe um usuário com esse Telefone!";
        } else {
            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(senha);
            usuario.setTelefone(telefone);
            usuario.setCpf(cpf);

            dao.insertCliente(usuario);
            nextPage = "/WEB-INF/jsp/login2.jsp"; 
        }

        if (!errorMessage.isEmpty()) {
            request.setAttribute("errorMessage", errorMessage);
        }
        RequestDispatcher d = getServletContext().getRequestDispatcher(nextPage);
        d.forward(request, response);
    } else {
        processRequest(request, response);
    }
}


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
