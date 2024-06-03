/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.Carrinho;
import model.bean.CarrinhoProduto;
import model.bean.Categoria;
import model.bean.Cobertura;
import model.bean.Massa;
import model.bean.Produto;
import model.bean.ProdutoCarrinho;
import model.bean.Projeto;
import model.bean.Recheio;
import model.bean.Sabor;
import model.bean.Topper;
import model.bean.Usuario;
import model.dao.CarrinhoProdutoDAO;
import model.dao.CategoriaDAO;
import model.dao.CoberturaDAO;
import model.dao.MassaDAO;
import model.dao.ProdutoDAO;
import model.dao.RecheioDAO;
import model.dao.SaborDAO;
import model.dao.TopperDAO;
import model.dao.UsuarioDAO;

/**
 *
 * @author Senai
 */
public class ProdutoUnicoCliente extends HttpServlet {

    int g = 0;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nextPage = "/WEB-INF/jsp/produtoUnicoCliente.jsp";
        ProdutoDAO dao = new ProdutoDAO();
        Produto idcap = dao.buscarPorId(Projeto.getIdProdutoAtual());
        int idCategoria = idcap.getCategoria();
        request.setAttribute("idCategoria", idCategoria);
        CarrinhoProdutoDAO daoCarrinho = new CarrinhoProdutoDAO();

        // Verifica se o produto já está no carrinho
        if (daoCarrinho.validaCarrinho(Projeto.getIdProdutoAtual())) {
            request.setAttribute("existeCarrinho", "Esse produto já está no carrinho! Remova-o para poder adicionar este");
        }

        MassaDAO mDAO = new MassaDAO();
        List<Massa> massas = mDAO.readMassas();
        request.setAttribute("massas", massas);

        RecheioDAO rDAO = new RecheioDAO();
        List<Recheio> recheio = rDAO.readRecheio();
        request.setAttribute("recheios", recheio);

        CoberturaDAO cDAO = new CoberturaDAO();
        List<Cobertura> cobertura = cDAO.readCobertura();
        request.setAttribute("coberturas", cobertura);

        TopperDAO tDAO = new TopperDAO();
        List<Topper> toppers = tDAO.readTopper();
        request.setAttribute("toppers", toppers);

        SaborDAO sDAO = new SaborDAO();
        List<Sabor> sabores = sDAO.readSabor(idCategoria);
        request.setAttribute("sabores", sabores);

        // Valida se o usuário está logado
        if (Usuario.getIdUsuarioStatic() != 0) {
            UsuarioDAO u = new UsuarioDAO();
            List<Usuario> usuarios = u.getUsuarioById(Usuario.getIdUsuarioStatic());
            request.setAttribute("usuario", usuarios);
        }

        request.setAttribute("usuarios", Usuario.getIdUsuarioStatic());

        // Pega os dados do produto pelo ID
        Produto produtos = dao.buscarPorId(Projeto.getIdProdutoAtual());
        if (produtos.getImagemBytes() != null) {
            String imagemBase64 = Base64.getEncoder().encodeToString(produtos.getImagemBytes());
            produtos.setImagemBase64(imagemBase64);
        }
        request.setAttribute("produtos", produtos);

        // Lista as categorias do header
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
            g++;
            
            CarrinhoProdutoDAO car = new CarrinhoProdutoDAO();
            CarrinhoProduto c = new CarrinhoProduto();
            Carrinho carrinho = new Carrinho();
            ProdutoCarrinho p = new ProdutoCarrinho();
            Produto produto = new Produto();
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Usuario u = new Usuario();
            Cobertura cobertura = new Cobertura();
            Massa massa = new Massa();
            Recheio recheio = new Recheio();
            Topper topper = new Topper();
            Sabor sabor = new Sabor();
            produto = produtoDAO.buscarPorId(Projeto.getIdProdutoAtual());
            u.setIdUsuario(Usuario.getIdUsuarioStatic());
            p.setQuantidade(Float.parseFloat(request.getParameter("qtd")));
            p.setIdUsuario(u);
            p.setProduto(produto);
            // Verificar se é bolo
            if (produto.getCategoria() == 1) {
                cobertura.setIdCobertura(Integer.parseInt(request.getParameter("cobertura")));
                recheio.setIdRecheio(Integer.parseInt(request.getParameter("recheio")));
                massa.setIdMassa(Integer.parseInt(request.getParameter("massa")));
                topper.setIdTopper(Integer.parseInt(request.getParameter("topper")));
                sabor.setIdSabor(0);
                p.setIdCobertura(cobertura);
                p.setIdMassa(massa);
                p.setIdRecheio(recheio);
                p.setIdTopper(topper);
                p.setIdSabor(sabor);
                ArrayList<Integer> sabores = new ArrayList<>();
                if (car.adicionarProdutoAoCarrinho(p, sabores) != 0) {
                    response.sendRedirect(request.getContextPath() + "/inicio");
                } else {
                    response.sendRedirect(request.getContextPath() + "/produto-unico");
                }
            } else {
                String[] selectedSabores = request.getParameterValues("sabor");

                if (selectedSabores == null || selectedSabores.length == 0 ) {
                    request.setAttribute("erroSabor", "Por favor, selecione pelo menos um sabor.");
                    processRequest(request, response);
                    return;
                }
                 List<Integer> saborIds = Arrays.stream(selectedSabores)
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                 
                boolean teste = false;
                ArrayList<Integer> sabores = new ArrayList<>();
                for (int saborId : saborIds) {
                    Sabor saborSelecionado = new Sabor();
                    saborSelecionado.setIdSabor(saborId);
                    System.out.println("Sabores: " + saborId);
                    sabores.add(saborId);
                    
                }
                int idProdutoCarrinho = car.adicionarProdutoAoCarrinho(p, sabores);

               
                for (int saborId : saborIds) {
                    Sabor saborSelecionado = new Sabor();
                    saborSelecionado.setIdSabor(saborId);
                    System.out.println("Sabores: " + saborId);
                    teste = car.adicionarSabores(saborId, idProdutoCarrinho);
                    if (!teste) {
                        response.sendRedirect(request.getContextPath() + "/produto-unico");
                    }
                }
                if (teste) {
                    response.sendRedirect(request.getContextPath() + "/inicio");
                } else {
                    response.sendRedirect(request.getContextPath() + "/produto-unico");
                }
            }

        } else {
            processRequest(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
