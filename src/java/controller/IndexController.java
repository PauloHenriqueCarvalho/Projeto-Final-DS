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
import model.bean.Produto;
import model.dao.ProdutoDAO;

public class IndexController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/WEB-INF/jsp/index.jsp";

        ProdutoDAO dao = new ProdutoDAO();
        List<Produto> produtos = dao.listarTodos();
        for (Produto produto : produtos) {
            if (produto.getImagemBytes() != null) {
                String imagemBase64 = Base64.getEncoder().encodeToString(produto.getImagemBytes());
                produto.setImagemBase64(imagemBase64);
            }
        }
        request.setAttribute("produtos", produtos);

        RequestDispatcher d = getServletContext().getRequestDispatcher(url);
        d.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getServletPath();
        System.out.println("URL> " + url);
        if (url.equals("/buscar")) {
            System.out.println("Buscar");
            buscarProduto(request, response);
        } else if (url.equals("/carrinho")) {
            exibirCarrinho(request, response);
        } else if (url.equals("/addCarrinho")) {
             System.out.println("add");
            adicionarItem(request, response);
        } else if (url.equals("/removerCarrinho")) {
             System.out.println("rem");
            removerItem(request, response);
        } else if (url.equals("/finalizarPedido")) {
             System.out.println("final");
            finalizarPedido(request, response);
        } else {
            processRequest(request, response);
        }
    }

    private void buscarProduto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String termo = request.getParameter("termo");
        termo = "%" + termo + "%";

        ProdutoDAO dao = new ProdutoDAO();
        List<Produto> produtos = dao.busca(termo);
        for (Produto produto : produtos) {
            if (produto.getImagemBytes() != null) {
                String imagemBase64 = Base64.getEncoder().encodeToString(produto.getImagemBytes());
                produto.setImagemBase64(imagemBase64);
            }
        }

        request.setAttribute("produtos", produtos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
        dispatcher.forward(request, response);
    }

    private void adicionarItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
        if (carrinho == null) {
            carrinho = new Carrinho(new ArrayList<>());
            session.setAttribute("carrinho", carrinho);
        }

        int produtoId = Integer.parseInt(request.getParameter("id"));
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = produtoDAO.buscarPorId(produtoId);
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));

        carrinho.addItem(produto, quantidade);

        response.setContentType("text/plain");
        response.getWriter().write("Produto adicionado ao carrinho!");
    }

    private void removerItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
        if (carrinho != null) {
            int produtoId = Integer.parseInt(request.getParameter("id"));
            carrinho.removerItem(produtoId);
        }

        response.sendRedirect(request.getContextPath() + "/carrinho");
    }

    private void exibirCarrinho(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/carrinho.jsp");
        dispatcher.forward(request, response);
    }

    private void finalizarPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
        if (carrinho != null && !carrinho.getProdutos().isEmpty()) {
            // Salvar o pedido no banco de dados (implementar a lógica conforme necessário)
            // Limpar o carrinho
            session.removeAttribute("carrinho");
        }

        response.sendRedirect(request.getContextPath() + "/pedidoFinalizado.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
