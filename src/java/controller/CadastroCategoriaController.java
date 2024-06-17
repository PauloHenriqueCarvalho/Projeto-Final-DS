/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.Categoria;
import model.dao.CategoriaDAO;
import model.dao.ProdutoDAO;

/**
 *
 * @author paulo
 */
public class CadastroCategoriaController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nextPage = "/WEB-INF/jsp/cadastroCategoria.jsp";

        CategoriaDAO cat = new CategoriaDAO();
        List<Categoria> listaCategorias = cat.listarTodosAdm();
        request.setAttribute("categorias", listaCategorias);

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
        if (url.equals("/cadastro-categoria")) {
            String nome = request.getParameter("nome");
            if(nome.trim() != ""){
                CategoriaDAO daoC = new CategoriaDAO();
                daoC.insert(nome);
            }
            response.sendRedirect("./cadastroCategoria");
        } else if(url.equals("/deletar-categoria")){
            int id = Integer.parseInt(request.getParameter("idCategoria"));
            if(id != 0){
                CategoriaDAO daoC = new CategoriaDAO();
                daoC.delete(id);
            } 
            response.sendRedirect("./cadastroCategoria");
        
        } else if(url.equals("/status-categoria")){
            int id = Integer.parseInt(request.getParameter("idCategoria"));
            if(id != 0){
                 String statusProduto = request.getParameter("statusProduto");
                // Verifica se o checkbox foi marcado
                boolean isChecked = statusProduto != null;
                CategoriaDAO daoC = new CategoriaDAO();
                daoC.updateStatus(id, isChecked);
                
                
            
            } 
            response.sendRedirect("./cadastroCategoria");
        
        }else {
            processRequest(request, response);
        }

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
