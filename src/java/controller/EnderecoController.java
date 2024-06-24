/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
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
import model.bean.Endereco;
import model.bean.Produto;
import model.bean.Usuario;
import model.dao.CarrinhoDAO;
import model.dao.CarrinhoProdutoDAO;
import model.dao.CategoriaDAO;
import model.dao.EnderecoDAO;
import model.dao.UsuarioDAO;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Senai
 */
public class EnderecoController extends HttpServlet {

    private static final String apiCEP = "https://viacep.com.br/ws/%s/json/";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nextPage = "/WEB-INF/jsp/enderecos.jsp";
        if (Usuario.getIdUsuarioStatic() != 0) {
            UsuarioDAO u = new UsuarioDAO();
            List<Usuario> usuarios = u.getUsuarioById(Usuario.getIdUsuarioStatic());

            request.setAttribute("usuario", usuarios);
            request.setAttribute("nome", usuarios.get(0).getNome());
        }

        CategoriaDAO cat = new CategoriaDAO();
        List<Categoria> categoria = cat.listarTodos();
        request.setAttribute("categorias", categoria);
        EnderecoDAO daoEndereco = new EnderecoDAO();
        CarrinhoProdutoDAO car = new CarrinhoProdutoDAO();
        List<Produto> carrinho = car.listarProdutosDoCarrinho();
        for (Produto c : carrinho) {
            if (c.getImagemBytes() != null) {
                String imagemBase64 = Base64.getEncoder().encodeToString(c.getImagemBytes());
                c.setImagemBase64(imagemBase64);

            }
        }

        CarrinhoDAO cDAO = new CarrinhoDAO();
        double total = cDAO.precoCarrinho();
        request.setAttribute("total", total);
        request.setAttribute("carrinhos", carrinho);
        request.setAttribute("qtdCarrinho", carrinho.size());
        List<Endereco> enderecos = daoEndereco.readEnderecos();
        request.setAttribute("enderecos", enderecos);

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
        if (url.equals("/add-endereco")) {
            String complemento = request.getParameter("complemento");
            String cep = request.getParameter("add-cep");
            String numero = request.getParameter("numero");
            if (!cep.trim().equals("") && !numero.trim().equals("")) {
                try {
                    Endereco endereco = buscarEnderecoPorCep(cep);
                    if (endereco.getLogradouro() == null) {
                        System.out.println("Endreco null");
                        request.getSession().setAttribute("erroCadastroEndereco", "Endereco Invalido!");
                    } else {
                        System.out.println("Endereco> " + endereco.getLogradouro());
                        request.getSession().removeAttribute("erroCadastroEndereco");

                        EnderecoDAO dao = new EnderecoDAO();
                        endereco.setComplemento(complemento);
                        endereco.setNumero(Integer.parseInt(numero));
                        endereco.setCep(cep);
                        dao.insert(endereco);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            response.sendRedirect("./endereco");

        } else if (url.equals("/remover-endereco")) {
            EnderecoDAO eDAO = new EnderecoDAO();
            eDAO.delet(Integer.parseInt(request.getParameter("idEndereco")));
            response.sendRedirect("./endereco");

        } else if (url.equals("/definirEnderecoPadrao")) {

            EnderecoDAO daoEndereco = new EnderecoDAO();
            int novoEnderecoPadrao = Integer.parseInt(request.getParameter("idEndereco"));
            daoEndereco.mudarEnderecoPadrao(novoEnderecoPadrao);
            response.sendRedirect("./endereco");

        } else {
            processRequest(request, response);
        }
    }

    public static Endereco buscarEnderecoPorCep(String cep) throws IOException {
        String url = String.format(apiCEP, cep);

       try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);

            try (CloseableHttpResponse response = client.execute(request)) {
                String jsonResponse = EntityUtils.toString(response.getEntity());
                Gson gson = new Gson();
                return gson.fromJson(jsonResponse, Endereco.class);
            } catch (Exception e) {
                return new Endereco();
            }
        } catch (Exception e) {
            return new Endereco();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
