/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Comparator;
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
import model.bean.Projeto;
import model.bean.Usuario;
import model.dao.CarrinhoDAO;
import model.dao.CarrinhoProdutoDAO;
import model.dao.CategoriaDAO;
import model.dao.ProdutoDAO;
import model.dao.UsuarioDAO;
import model.dao.WishListDAO;

/**
 *
 * @author Senai
 */
public class ListarCategoriaController extends HttpServlet {

    int idCat = 0;
    private static final int PRODUCTS_PER_PAGE = 9;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nextPage = "/WEB-INF/jsp/listaProdutosCliente.jsp";
        idCat = Integer.parseInt(request.getParameter("cat"));
        int currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        String filter = request.getParameter("filter");

        CategoriaDAO cat = new CategoriaDAO();
        List<Categoria> categoria = cat.listarTodos();
        if (idCat != -1) {
            Categoria c = cat.readById(idCat);
            request.setAttribute("categoriaAtual", c.getNome());
        } else {
            request.setAttribute("categoriaAtual", "Todos Produtos");
        }
        request.setAttribute("categorias", categoria);
        CarrinhoDAO cDAO = new CarrinhoDAO();
        float total = cDAO.precoCarrinho();
        request.setAttribute("total", total);

        CarrinhoProdutoDAO car = new CarrinhoProdutoDAO();
        List<Produto> carrinho = car.listarProdutosDoCarrinho();
        for (Produto ca : carrinho) {
            if (ca.getImagemBytes() != null) {
                String imagemBase64 = Base64.getEncoder().encodeToString(ca.getImagemBytes());
                ca.setImagemBase64(imagemBase64);

            }
        }
        request.setAttribute("carrinhos", carrinho);

        if (Usuario.getIdUsuarioStatic() != 0) {
            UsuarioDAO u = new UsuarioDAO();
            List<Usuario> usuarios = u.getUsuarioById(Usuario.getIdUsuarioStatic());
            request.setAttribute("usuario", usuarios);
        }

        ProdutoDAO dao = new ProdutoDAO();
        int totalProducts = 0;
        List<Produto> produtos = null;
        if(idCat != -1){
             produtos = dao.listarPorCategoria(idCat, filter, currentPage, PRODUCTS_PER_PAGE);

            // Calcula a quantidade total de páginas
             totalProducts = dao.countProdutosByCategoria(idCat);
             request.setAttribute("idCat", idCat);
        } else {
             produtos = dao.listarTodos( filter, currentPage, PRODUCTS_PER_PAGE);

            // Calcula a quantidade total de páginas
             totalProducts = dao.countProdutosTodos();
             request.setAttribute("idCat", -1);
        }
       

        int totalPages = (int) Math.ceil((double) totalProducts / PRODUCTS_PER_PAGE);

        for (Produto produto : produtos) {
            if (produto.getImagemBytes() != null) {
                String imagemBase64 = Base64.getEncoder().encodeToString(produto.getImagemBytes());
                produto.setImagemBase64(imagemBase64);
            }
        }

        request.setAttribute("produtos", produtos);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("selectedFilter", filter);
        

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextPage);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getServletPath();

        if (url.equals("/busca")) {

            idCat = Integer.parseInt(request.getParameter("cat"));
            int currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
            String filter = request.getParameter("filter");

            CategoriaDAO cat = new CategoriaDAO();
            List<Categoria> categoria = cat.listarTodos();
            Categoria c = cat.readById(idCat);
            request.setAttribute("categoriaAtual", c.getNome());
            request.setAttribute("categorias", categoria);

            CarrinhoDAO cDAO = new CarrinhoDAO();
            float total = cDAO.precoCarrinho();
            request.setAttribute("total", total);

            CarrinhoProdutoDAO car = new CarrinhoProdutoDAO();
            List<Produto> carrinho = car.listarProdutosDoCarrinho();
            for (Produto ca : carrinho) {
                if (ca.getImagemBytes() != null) {
                    String imagemBase64 = Base64.getEncoder().encodeToString(ca.getImagemBytes());
                    ca.setImagemBase64(imagemBase64);

                }
            }
            request.setAttribute("carrinhos", carrinho);

            if (Usuario.getIdUsuarioStatic() != 0) {
                UsuarioDAO u = new UsuarioDAO();
                List<Usuario> usuarios = u.getUsuarioById(Usuario.getIdUsuarioStatic());
                request.setAttribute("usuario", usuarios);
            }

            ProdutoDAO dao = new ProdutoDAO();

            int totalProducts = dao.countProdutosByCategoria(idCat);

            int totalPages = (int) Math.ceil((double) totalProducts / PRODUCTS_PER_PAGE);

            request.setAttribute("currentPage", currentPage);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("selectedFilter", filter);
            request.setAttribute("idCat", idCat);
            String termo = request.getParameter("termo");
            termo = "%" + termo + "%";

            List<Produto> produtos = dao.listarPorCategoriaBusca(idCat, filter, currentPage, PRODUCTS_PER_PAGE, termo);
            for (int i = 0; i < produtos.size(); i++) {
                if (produtos.get(i).getImagemBytes() != null) {
                    String imagemBase64 = Base64.getEncoder().encodeToString(produtos.get(i).getImagemBytes());
                    produtos.get(i).setImagemBase64(imagemBase64);
                }
            }

            request.setAttribute("produtos", produtos);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listaProdutosCliente.jsp");
            dispatcher.forward(request, response);
        } else {
            processRequest(request, response);
            System.out.println("Else");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getServletPath();
        WishListDAO w = new WishListDAO();

        if (url.equals("/produtoPage")) {
            Projeto.setIdProdutoAtual(Integer.parseInt(request.getParameter("idProduto")));
            response.sendRedirect(request.getContextPath() + "/produto-unico");

        } else if (url.equals("/listaDesejos")) {

            if (Usuario.getIdUsuarioStatic() != 0) {
                boolean valida = w.adicionarProdutoAoCarrinho(Integer.parseInt(request.getParameter("idProduto")));
                request.getSession().setAttribute("validacaoLista", valida);

            } else {
                request.getSession().setAttribute("alerta", "Você precisa estar logado para adicionar produtos à lista de desejos");
            }
            response.sendRedirect(request.getContextPath() + "/lista?cat=" + idCat);
        } else if (url.equals("/lista-filtro")) {

        } else {
            processRequest(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
