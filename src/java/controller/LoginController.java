/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String nextPage = "/WEB-INF/jsp/login.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextPage);
        dispatcher.forward(request, response);
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getServletPath();
        String nextPage = "/WEB-INF/jsp/login.jsp"; // Definindo a página padrão

        if (url.equals("/logar")) {
            Usuario user = new Usuario();
            UsuarioDAO valida = new UsuarioDAO();

            user.setEmail(request.getParameter("email"));
            user.setSenha(request.getParameter("senha"));

            try {
                valida.validaUser(user);

                if (Usuario.getAcessoStatic() != 0) {
                    if(Usuario.getAcessoStatic() == 1){
                        int idUsuario = valida.getId(request.getParameter("email"));                   
                        response.sendRedirect(request.getContextPath() + "/inicioAdministrador");
                        return;
                    } else if(Usuario.getAcessoStatic() == 2){
                        int idUsuario = valida.getId(request.getParameter("funcionario"));                   
                        response.sendRedirect(request.getContextPath() + "/inicioFuncionario");
                        return;
                    } else if(Usuario.getAcessoStatic() == 3){
                        int idUsuario = valida.getId(request.getParameter("email"));                   
                        response.sendRedirect(request.getContextPath() + "/inicio");
                        return;
                    } else{
                        System.out.println("Nenhum usuario logado");
                    }
                    
                } else {
                    request.setAttribute("errorMessage", "Usuário ou senha inválidos");
                }
            } catch (Exception e) {
                request.setAttribute("errorMessage", "Erro ao autenticar usuário");
            }
        }
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(Usuario.getIdUsuarioStatic());
        request.setAttribute("usuario", usuario);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextPage);
        dispatcher.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
