/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.Categoria;
import model.bean.Produto;
import model.bean.Projeto;
import model.bean.TipoProduto;
import model.dao.CategoriaDAO;
import model.dao.ProdutoDAO;
import model.dao.TiposProdutosDAO;

/**
 *
 * @author paulo
 */
public class EspecificacaoController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nextPage = "/WEB-INF/jsp/especificacaoProduto.jsp";
        
         ProdutoDAO dao = new ProdutoDAO();

        CategoriaDAO cat = new CategoriaDAO();
        List<Categoria> listaCategorias = cat.listarTodos();
        request.setAttribute("categorias", listaCategorias);
        
        

        List<Produto> produto = dao.listarTodosAdm();
        for (int i = 0; i < produto.size(); i++) {
            if (produto.get(i).getImagemBytes() != null) {
                String imagemBase64 = Base64.getEncoder().encodeToString(produto.get(i).getImagemBytes());
                produto.get(i).setImagemBase64(imagemBase64);
            }

        }
        request.setAttribute("produtos", produto);
        
        TiposProdutosDAO tDAO = new TiposProdutosDAO();
        List<TipoProduto> tipo = new ArrayList<>();
        
        for (int i = 0; i < listaCategorias.size(); i++) {
            System.out.println("Quantas? categorias");
            TipoProduto tipoProduto = new TipoProduto();
            tipoProduto.setNomeCategoria(listaCategorias.get(i).getNome());
            int qtdAtual = 0;
            for (int j = 0; j < produto.size(); j++) {
                System.out.println("Quantas? categoria:  " + listaCategorias.get(i).getNome() + " = Produto: " + produto.get(j).getCategoria().getNome());
                if(listaCategorias.get(i).getNome().equals(produto.get(j).getCategoria().getNome())){
                    qtdAtual++;
                    System.out.println("Entra");
                }
            }
            tipoProduto.setValor(qtdAtual);
            tipo.add(tipoProduto);
        }
        request.setAttribute("listaCategoria", tipo);
        
        for (int i = 0; i < tipo.size(); i++) {
            
        }
        
        
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
        if(url.equals("/deletar-produto")){
            ProdutoDAO dao = new ProdutoDAO();
            System.out.println("Entra");
            dao.delete(Integer.parseInt(request.getParameter("idProduto")));
            response.sendRedirect("./especificacaoProdutos");
        } else if(url.equals("/status-produto")){
            ProdutoDAO dao = new ProdutoDAO();
            int idProduto = Integer.parseInt(request.getParameter("idProduto"));
            String statusProduto = request.getParameter("statusProduto");
            // Verifica se o checkbox foi marcado
            boolean isChecked = statusProduto != null;

            dao.alterarStatus(isChecked, idProduto);
        
            response.sendRedirect("./especificacaoProdutos");
        } else if(url.equals("/ver-produto")){
            Projeto.setIdProdutoAtual(Integer.parseInt(request.getParameter("idProduto")));
            response.sendRedirect("./previewProduto");
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
