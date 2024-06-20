package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.bean.Carrinho;
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

public class IndexController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/WEB-INF/jsp/index2.jsp";

        if (Projeto.isSair()) {
            Usuario.setIdUsuarioStatic(0);
            Projeto.setSair(false);
        }
        if (Usuario.getIdUsuarioStatic() != 0) {
            UsuarioDAO u = new UsuarioDAO();
            List<Usuario> usuarios = u.getUsuarioById(Usuario.getIdUsuarioStatic());
            request.setAttribute("usuario", usuarios);
        }

        CategoriaDAO cat = new CategoriaDAO();
        List<Categoria> categoria = cat.listarTodos();
        request.setAttribute("categorias", categoria);

        ProdutoDAO dao = new ProdutoDAO();
        List<Produto> produtos = dao.listarTodos();
        for (Produto produto : produtos) {
            if (produto.getImagemBytes() != null) {
                String imagemBase64 = Base64.getEncoder().encodeToString(produto.getImagemBytes());
                produto.setImagemBase64(imagemBase64);
            }
        }
        
        request.setAttribute("produtos", produtos);

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

        RequestDispatcher d = getServletContext().getRequestDispatcher(url);
        d.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getServletPath();

        if (url.equals("/buscaProdutos")) {
            if (Projeto.isSair()) {
                Usuario.setIdUsuarioStatic(0);
                Projeto.setSair(false);
            }
            if (Usuario.getIdUsuarioStatic() != 0) {
                UsuarioDAO u = new UsuarioDAO();
                List<Usuario> usuarios = u.getUsuarioById(Usuario.getIdUsuarioStatic());
                request.setAttribute("usuario", usuarios);
            }

            CategoriaDAO cat = new CategoriaDAO();
            List<Categoria> categoria = cat.listarTodos();
            request.setAttribute("categorias", categoria);

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

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listaProdutosCliente.jsp");
            dispatcher.forward(request, response);
        } else {
            processRequest(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getServletPath();
        if (url.equals("/deletarProduto")) {
            CarrinhoProdutoDAO dao = new CarrinhoProdutoDAO();
            boolean a = dao.excluirProdutoCarrinho(Integer.parseInt(request.getParameter("idProduto")));
            response.sendRedirect(request.getContextPath() + "/inicio");

        } else if (url.equals("/produtoPage")) {
            Projeto.setIdProdutoAtual(Integer.parseInt(request.getParameter("idProduto")));
            response.sendRedirect(request.getContextPath() + "/produto-unico");

        } else if (url.equals("/listaDesejosIndex")) {
            WishListDAO w = new WishListDAO();
            if (Usuario.getIdUsuarioStatic() != 0) {
                boolean valida = w.adicionarProdutoAoCarrinho(Integer.parseInt(request.getParameter("idProduto")));
                request.getSession().setAttribute("validacaoLista", valida);

            } else {
                request.getSession().setAttribute("alerta", "Você precisa estar logado para adicionar produtos à lista de desejos");
            }
            response.sendRedirect(request.getContextPath() + "/inicio");
        } else {
            processRequest(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
