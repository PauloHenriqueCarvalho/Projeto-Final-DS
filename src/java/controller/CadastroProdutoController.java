/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.Categoria;
import model.bean.Produto;
import model.bean.ProdutoImagem;
import model.bean.Projeto;
import model.bean.Usuario;
import model.dao.CategoriaDAO;
import model.dao.ProdutoDAO;
import model.dao.ProdutoImagemDAO;
import model.dao.UsuarioDAO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Senai
 */
public class CadastroProdutoController extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoriaDAO dao = new CategoriaDAO();
        List<Categoria> listaCategorias = dao.listarTodos();
        request.setAttribute("categorias", listaCategorias);
        String nextPage = "/WEB-INF/jsp/cadastroProduto.jsp";
        
        if(Usuario.getIdUsuarioStatic() != 0) {
            UsuarioDAO u = new UsuarioDAO();
            List<Usuario> usuarios = u.getUsuarioById(Usuario.getIdUsuarioStatic());
            request.setAttribute("usuario", usuarios);
        }
            
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextPage);
        dispatcher.forward(request, response);
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Verifica se a requisição é do tipo multipart (upload de arquivo)
        String url = request.getServletPath();
        if (url.equals("/cadastro-produto")) {
            System.out.println("Entra no id");
            List<ProdutoImagem> imagens = new ArrayList<>();
            try {
                // Parseia a requisição para obter os itens do formulário
                List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                // Instancia um novo produto
                Produto produto = new Produto();
                Categoria categoria = new Categoria();
                // Processa cada item do formulário
                for (FileItem item : items) {
                    // Verifica se o item é um campo de formulário
                    if (item.isFormField()) {
                        // Se sim, verifica o nome do campo e define o valor do produto de acordo
                        switch (item.getFieldName()) {
                            case "nome":
                                produto.setNome(item.getString());
                                break;
                            case "descricao":
                                produto.setDescricao(item.getString());
                                break;
                            case "preco":
                                produto.setValor(Float.parseFloat(item.getString()));
                                break;

                            case "categoria":
                               categoria.setIdCategoria(Integer.parseInt(item.getString()));
                               produto.setCategoria(categoria);
                                break;
                            case "precoCusto":
                                produto.setPrecoCusto(Float.parseFloat(item.getString()));
                                break;
                            case "quantidade":
                                produto.setQuantidadeEstoque(Integer.parseInt(item.getString()));
                                break;   
                        }
                    } else {
                        // Se não, o item é um arquivo de imagem
                        // Converte o InputStream do arquivo em um array de bytes
                        System.out.println("Enrta no das imagens");
                        InputStream inputStream = item.getInputStream();
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        byte[] buffer = new byte[4096];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                        byte[] imagemBytes = outputStream.toByteArray();
                        // Define a imagem do produto
                        ProdutoImagem imagem = new ProdutoImagem();
                        imagem.setImagemBytes(imagemBytes);
                        imagens.add(imagem);
                        
                        inputStream.close();
                        outputStream.close();
                    }
                }
                
                ProdutoDAO dao = new ProdutoDAO();
                int sucesso = dao.inserirProduto(produto);
                
                
                if (sucesso > 0 ) {
                    // Se a inserção for bem-sucedida, redireciona para .a página de produtos
                    ProdutoImagemDAO pmDAO = new ProdutoImagemDAO();
                    Produto idProduto = new Produto();
                    idProduto.setIdProduto(sucesso);
                    for (int i = 0; i < imagens.size(); i++) {
                        imagens.get(i).setProduto(idProduto);
                        
                        pmDAO.inserirImagem(imagens.get(i));
                        System.out.println("Imagem: " + i);
                    } 
                    request.getSession().removeAttribute("erroCadastro");
                    Projeto.setIdProdutoAtual(sucesso);
                    response.sendRedirect(request.getContextPath() + "/tipoProduto");
                }else if(sucesso == -2) {
                    request.getSession().setAttribute("erroCadastro", "Ja existe esse produto cadastrado!");
                    response.sendRedirect(request.getContextPath() + "/cadastroProduto");
                    
                }else {
                    // Se ocorrer algum erro, redireciona para a página de erro
                    response.sendRedirect(request.getContextPath() + "/cadastroProduto");
                }
            } catch (FileUploadException e) {
                throw new ServletException("Cannot parse multipart request.", e);
            }
        } else {
            System.out.println("Else");
            redirectToIndexPage(request, response);
        }
    }

    private void redirectToIndexPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Redireciona para a página inicial
        response.sendRedirect(request.getContextPath() + "/inicio");
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
