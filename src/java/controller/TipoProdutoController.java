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
import model.bean.Produto;
import model.bean.Projeto;
import model.bean.Sabor;
import model.dao.ProdutoDAO;
import model.dao.SaborDAO;

/**
 *
 * @author paulo
 */
public class TipoProdutoController extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nextPage = "/WEB-INF/jsp/tiposProdutoAdministrador.jsp";
        
        
        ProdutoDAO daoP = new ProdutoDAO();
        Produto produtoAtual =daoP.readById(Projeto.getIdProdutoAtual());
        request.setAttribute("produtoAtual", produtoAtual);
        
        SaborDAO daoS = new SaborDAO();
        List<Sabor> sabores = daoS.listarTiposProduto(Projeto.getIdProdutoAtual());
        request.setAttribute("especificacao", sabores);
        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextPage);
        dispatcher.forward(request, response);
    }

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
        if(url.equals("/cadastro-tipo")){
            SaborDAO daoS = new SaborDAO();
            Sabor s = new Sabor();
            s.setNome(request.getParameter("nome"));
            s.setDescricao(request.getParameter("descricao"));
            s.setIdProduto(Projeto.getIdProdutoAtual());
            daoS.createTipo(s);
            response.sendRedirect("./tipoProduto");
        } else if(url.equals("/cadastro-especificacao")){
            SaborDAO daoS = new SaborDAO();
            Sabor s = new Sabor();
            s.setNome(request.getParameter("nome"));
            s.setDescricao(request.getParameter("descricao"));
            s.setIdPai(Integer.parseInt(request.getParameter("idPai")));
            s.setValorAdicional(Float.parseFloat(request.getParameter("valorAdicional")));
            daoS.createEspecificacao(s);
            response.sendRedirect("./tipoProduto");
        
        }else {
            processRequest(request, response);
        }
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
