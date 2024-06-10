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
        float total = cDAO.precoCarrinho();
        request.setAttribute("total", total);   
        request.setAttribute("carrinhos", carrinho);
        
        List<Endereco> enderecos = daoEndereco.readEnderecos();
        request.setAttribute("enderecos", enderecos);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextPage);
        dispatcher.forward(request, response);
    }

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
        processRequest(request, response);
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
        if (url.equals("/add-endereco")) {
            String complemento = request.getParameter("complento");
            String cep = request.getParameter("cep");
            String numero = request.getParameter("numero");
            System.out.println("CEP: " + cep);
            if(!cep.trim().equals("") && !numero.trim().equals("") ){
                try {
                Endereco endereco = buscarEnderecoPorCep(cep);
                EnderecoDAO dao = new EnderecoDAO();
                endereco.setComplemento(complemento);
                endereco.setNumero(Integer.parseInt(numero));
                endereco.setCep(cep);
                System.out.println("Cadastroiu");
                dao.insert(endereco);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            response.sendRedirect("./endereco");
            

        } else if(url.equals("/remover-endereco")){
            EnderecoDAO eDAO = new EnderecoDAO();
            eDAO.delet(Integer.parseInt(request.getParameter("idEndereco")));
            response.sendRedirect("./endereco");
            
        } else if(url.equals("/definirEnderecoPadrao")){
           
            EnderecoDAO daoEndereco = new EnderecoDAO();
            int novoEnderecoPadrao = Integer.parseInt(request.getParameter("idEndereco"));
             System.out.println("Enrea?"+ novoEnderecoPadrao);
            daoEndereco.mudarEnderecoPadrao(novoEnderecoPadrao);
            response.sendRedirect("./endereco");
        
        }else {
            processRequest(request, response);
        }
    }

    /**
     * Busca o endereço associado a um determinado CEP utilizando a API ViaCEP.
     *
     * @param cep O CEP para o qual se deseja buscar o endereço.
     * @return Uma instância da classe Endereco que representa o endereço
     * associado ao CEP fornecido.
     * @throws IOException Se ocorrer um erro na comunicação com a API ViaCEP.
     */
    public static Endereco buscarEnderecoPorCep(String cep) throws IOException {
        //Formata a URL da API inserindo o CEP fornecido
        String url = String.format(apiCEP, cep);

        //Cria um cliente HTTP que será usado para enviar a solicitação
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            // Cria uma solicitação HTTP GET para a URL especificada
            HttpGet request = new HttpGet(url);

            // Executa a solicitação e obtém a resposta
            try (CloseableHttpResponse response = client.execute(request)) {
                // Converte o conteúdo da resposta para uma string JSON
                String jsonResponse = EntityUtils.toString(response.getEntity());

                // Cria uma instância da classe Gson para deserialização do JSON
                Gson gson = new Gson();

                //Converte o Json em Objeto e retorna
                return gson.fromJson(jsonResponse, Endereco.class);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
