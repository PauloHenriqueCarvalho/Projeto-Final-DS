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
import model.dao.CarrinhoProdutoDAO;
import model.dao.CategoriaDAO;
import model.dao.ProdutoDAO;
import model.dao.UsuarioDAO;

public class IndexController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/WEB-INF/jsp/index2.jsp";
        
        if(Projeto.isSair()){
            Usuario.setIdUsuarioStatic(0);
            Projeto.setSair(false);
        }
        if(Usuario.getIdUsuarioStatic() != 0) {
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
        
        
        CarrinhoProdutoDAO car = new CarrinhoProdutoDAO();   
        List<Produto> carrinho = car.listarProdutosDoCarrinho();
         for (Produto c : carrinho) {
            if (c.getImagemBytes() != null) {
                String imagemBase64 = Base64.getEncoder().encodeToString(c.getImagemBytes());
                c.setImagemBase64(imagemBase64);
        
            }
        }
        
        System.out.println("Carrinho: " + carrinho);
        request.setAttribute("carrinhos", carrinho);

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

        response.setContentType("text/plain");
        response.getWriter().write("Produto adicionado ao carrinho!");
    }

    private void removerItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect(request.getContextPath() + "/carrinho");
    }

    private void exibirCarrinho(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/carrinho.jsp");
        dispatcher.forward(request, response);
    }

    private void finalizarPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect(request.getContextPath() + "/pedidoFinalizado.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getServletPath();
        if(url.equals("/deletarProduto")){
            
            System.out.println("Deletar: " + request.getParameter("idProduto"));
        } else {
             processRequest(request, response);
        }
       
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
