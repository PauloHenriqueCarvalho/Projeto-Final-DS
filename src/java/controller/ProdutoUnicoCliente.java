/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.Carrinho;
import model.bean.CarrinhoProduto;
import model.bean.Categoria;
import model.bean.Produto;
import model.bean.Projeto;
import model.bean.Usuario;
import model.dao.CarrinhoProdutoDAO;
import model.dao.CategoriaDAO;
import model.dao.ProdutoDAO;
import model.dao.UsuarioDAO;

/**
 *
 * @author Senai
 */
public class ProdutoUnicoCliente extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nextPage = "/WEB-INF/jsp/produtoUnicoCliente.jsp";
        
        //Valida se o Usuario ta logado
        if(Usuario.getIdUsuarioStatic() != 0) {
            UsuarioDAO u = new UsuarioDAO();
            List<Usuario> usuarios = u.getUsuarioById(Usuario.getIdUsuarioStatic());
            request.setAttribute("usuario", usuarios);
        }
            
        //pega os dados do produto pelo id
        ProdutoDAO dao = new ProdutoDAO();
        Produto produtos = dao.buscarPorId(Projeto.getIdProdutoAtual());
        if (produtos.getImagemBytes() != null) {
            String imagemBase64 = Base64.getEncoder().encodeToString(produtos.getImagemBytes());
            produtos.setImagemBase64(imagemBase64);
        }
        request.setAttribute("produtos", produtos);
        
        //Lista as categoria do header 
        CategoriaDAO cat = new CategoriaDAO();
        List<Categoria> categoria = cat.listarTodos();
        request.setAttribute("categorias", categoria);


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
        if (url.equals("/adicionarProduto")) {
            System.out.println("Add Produto");
            adicionarProduto(request, response);

        } else {
            processRequest(request, response);
        }

    }

    private void adicionarProduto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CarrinhoProdutoDAO car = new CarrinhoProdutoDAO();
        CarrinhoProduto c = new CarrinhoProduto();
        Carrinho carrinho = new Carrinho();
        Produto p = new Produto();

        String idProdutoStr = "" + Produto.getIdProdutoStatic();
        String quantidadeStr = request.getParameter("qtd");
        
        if (idProdutoStr != null && quantidadeStr != null && Usuario.getIdUsuarioStatic() != 0) {
            p.setIdProduto(Integer.parseInt(idProdutoStr));
            int quantidade = Integer.parseInt(quantidadeStr);

            carrinho.setId_carrinho(Usuario.getIdUsuarioStatic());
            carrinho.setId_usuario(Usuario.getIdUsuarioStatic());
 
            c.setProduto(p);
            c.setCarrinho(carrinho);
            c.setQuantidade(quantidade);
            
            car.adicionarProdutoAoCarrinho(c);
            response.sendRedirect(request.getContextPath() + "/inicio");
        } else {
            String errorMessage = "Voce precisa estar logado para Adicionar ao carrinho!";
            response.sendRedirect(request.getContextPath() + "/produto-unico");
            request.setAttribute("errorMessage", errorMessage);
            
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
