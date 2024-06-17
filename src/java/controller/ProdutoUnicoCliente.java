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
import model.bean.Produto;
import model.bean.ProdutoCarrinho;
import model.bean.ProdutoImagem;
import model.bean.Projeto;
import model.bean.Sabor;
import model.bean.Usuario;
import model.dao.CarrinhoProdutoDAO;
import model.dao.CategoriaDAO;
import model.dao.ProdutoDAO;
import model.dao.ProdutoImagemDAO;
import model.dao.SaborDAO;
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
        Produto idcap = dao.readById(Projeto.getIdProdutoAtual());
        int idCategoria =2;
        request.setAttribute("idCategoria", idCategoria);
        CarrinhoProdutoDAO daoCarrinho = new CarrinhoProdutoDAO();

        // Verifica se o produto já está no carrinho
        if (daoCarrinho.validaCarrinho(Projeto.getIdProdutoAtual())) {
            request.setAttribute("existeCarrinho", "Esse produto já está no carrinho! Remova-o para poder adicionar este");
        }

        SaborDAO sDAO = new SaborDAO();
        List<Sabor> sabores = sDAO.listarTiposProduto(Projeto.getIdProdutoAtual());
        request.setAttribute("sabores", sabores);
        
        List<Sabor> saboresEspecificos = sDAO.listarTodosEspecificos();
        request.setAttribute("saboresEspecificos", saboresEspecificos);

        // Valida se o usuário está logado
        if (Usuario.getIdUsuarioStatic() != 0) {
            UsuarioDAO u = new UsuarioDAO();
            List<Usuario> usuarios = u.getUsuarioById(Usuario.getIdUsuarioStatic());
            request.setAttribute("usuario", usuarios);
        }

        request.setAttribute("usuarios", Usuario.getIdUsuarioStatic());

        // Pega os dados do produto pelo ID
        Produto produtos = dao.readById(Projeto.getIdProdutoAtual());
        if (produtos.getImagemBytes() != null) {
            String imagemBase64 = Base64.getEncoder().encodeToString(produtos.getImagemBytes());
            produtos.setImagemBase64(imagemBase64);
        }
        request.setAttribute("produtos", produtos);
        
        ProdutoImagemDAO pmd = new ProdutoImagemDAO();
        List<ProdutoImagem> imagensProdutos = pmd.listarImagem(Projeto.getIdProdutoAtual());
        for (ProdutoImagem c : imagensProdutos) {
            if (c.getImagemBytes() != null) {
                String imagemBase64 = Base64.getEncoder().encodeToString(c.getImagemBytes());
                c.setImagemBase64(imagemBase64);

            }
        }
        request.setAttribute("imagensProdutos", imagensProdutos);

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

            Sabor sabor = new Sabor();
            produto = produtoDAO.readById(Projeto.getIdProdutoAtual());
            u.setIdUsuario(Usuario.getIdUsuarioStatic());
            p.setQuantidade(Float.parseFloat(request.getParameter("qtd")));
            p.setIdUsuario(u);
            p.setProduto(produto);
            // Verificar se é bolo

            String[] selectedSabores = request.getParameterValues("sabor");

            if (selectedSabores == null || selectedSabores.length == 0) {
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
        } else {
            processRequest(request, response);
        }
}

@Override
        public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
