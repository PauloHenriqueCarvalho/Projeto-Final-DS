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
import model.bean.Categoria;
import model.bean.Produto;
import model.bean.Usuario;
import model.dao.CarrinhoDAO;
import model.dao.CarrinhoProdutoDAO;
import model.dao.CategoriaDAO;
import model.dao.ProdutoDAO;
import model.dao.UsuarioDAO;
import model.dao.WishListDAO;

/**
 *
 * @author paulo
 */
public class ListaDesejosController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nextPage = "/WEB-INF/jsp/listaDesejos.jsp";
        if (Usuario.getIdUsuarioStatic() != 0) {
            UsuarioDAO u = new UsuarioDAO();
            List<Usuario> usuarios = u.getUsuarioById(Usuario.getIdUsuarioStatic());
            request.setAttribute("usuario", usuarios);
        }
        CarrinhoDAO cDAO = new CarrinhoDAO();
        float total = cDAO.precoCarrinho();
        request.setAttribute("total", total);

        CarrinhoProdutoDAO car = new CarrinhoProdutoDAO();
        List<Produto> carrinho = car.listarProdutosDoCarrinho();
        for (Produto c : carrinho) {
            if (c.getImagemBytes() != null) {
                String imagemBase64 = Base64.getEncoder().encodeToString(c.getImagemBytes());
                c.setImagemBase64(imagemBase64);

            }
        }
        request.setAttribute("carrinhos", carrinho);


        CategoriaDAO cat = new CategoriaDAO();
        List<Categoria> categoria = cat.listarTodos();
        request.setAttribute("categorias", categoria);

        WishListDAO dao = new WishListDAO();
        List<Produto> produtos = dao.readWishList();
        for (Produto produto : produtos) {
            if (produto.getImagemBytes() != null) {
                String imagemBase64 = Base64.getEncoder().encodeToString(produto.getImagemBytes());
                produto.setImagemBase64(imagemBase64);
            }
        }
        
        request.setAttribute("produtos", produtos);

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
         String url = request.getServletPath();

        if (url.equals("/busca")) {
            String termo = request.getParameter("termo");
            termo = "%" + termo + "%";

            ProdutoDAO dao = new ProdutoDAO();
            List<Produto> produtos = dao.busca(termo);
            for (int i = 0; i < produtos.size(); i++) {
                if (produtos.get(i).getImagemBytes() != null) {
                    String imagemBase64 = Base64.getEncoder().encodeToString(produtos.get(i).getImagemBytes());
                    produtos.get(i).setImagemBase64(imagemBase64);
                }

            }

            request.setAttribute("produtos", produtos);

            // Redirecione de volta para a página principal
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listaDesejos.jsp");
            dispatcher.forward(request, response);
        } else {
            processRequest(request, response);
            System.out.println("Else");
        }
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
        if (url.equals("/deletarListaDejesos")) {
            WishListDAO w = new WishListDAO();
            if (Usuario.getIdUsuarioStatic() != 0) {
                System.out.println("Id produto: " + Integer.parseInt(request.getParameter("idProduto")));
                boolean removido = w.removerProdutoDaLista(Integer.parseInt(request.getParameter("idProduto")));
                request.getSession().setAttribute("remocaoLista", removido);

            } else {
                request.getSession().setAttribute("alerta", "Você precisa estar logado para remover produtos da lista de desejos");
            }
            response.sendRedirect(request.getContextPath() + "/lista-desejos");
            System.out.println("Produto removido da lista de desejos");

        } else {
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
