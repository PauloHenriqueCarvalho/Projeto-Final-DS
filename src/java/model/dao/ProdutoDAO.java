/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import conexao.Conexao;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import model.bean.Categoria;
import model.bean.Produto;

/**
 *
 * @author Senai
 */
public class ProdutoDAO {
    public static String convertBlobToBase64(Blob blob) {
        try {
            byte[] bytes = blob.getBytes(1, (int) blob.length());
            return Base64.getEncoder().encodeToString(bytes);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean inserirProduto(Produto produto) {
        try (Connection conexao = Conexao.getConn();
                PreparedStatement ps = conexao.prepareStatement("INSERT INTO produto (nome, valor, desconto, valorFinal, categoria, subcategoria, imagem, descricao, unidade_medida) VALUES (?, ?,?, ?, ?, ?, ?, ?, ?)")) {

            // Calcular o valor final com base no valor e desconto
            float valorFinal = produto.getValor() - produto.getDesconto();

            // Defina os parâmetros do PreparedStatement com os valores do produto
            ps.setString(1, produto.getNome());
            ps.setFloat(2, produto.getValor());
            ps.setFloat(3, produto.getDesconto());
            ps.setFloat(4, valorFinal); // Defina o valor final calculado
            ps.setInt(5, produto.getCategoria());
            ps.setInt(6, produto.getSubcategoria());
            ps.setBytes(7, produto.getImagemBytes()); // Defina a imagem como array de bytes
            ps.setString(8, produto.getDescricao());
            ps.setString(9, produto.getUnidadeMedida());
            // Execute o PreparedStatement
            int linhasAfetadas = ps.executeUpdate();
            ps.close();
            conexao.close();
            // Verifique se a inserção foi bem-sucedida (verifique se uma linha foi afetada)
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            // Você pode lidar com a exceção aqui, talvez registrando-a ou lançando-a novamente
            e.printStackTrace();
            return false;
        }
    }

    public List<Produto> listarTodosDisponiveis() {
        List<Produto> produtos = new ArrayList<>();

        try {
            Connection conexao = Conexao.getConn(); // Obtenção da conexão com o banco de dados
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String query = "SELECT * FROM produto AS p INNER JOIN estoque AS e ON p.idProduto = e.produto WHERE e.quantidade > 0";

            stmt = conexao.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Produto p = new Produto();
                p.setIdProduto(rs.getInt("idProduto"));
                p.setNome(rs.getString("nome"));
                p.setCategoria(rs.getInt("categoria"));
                p.setValor(rs.getFloat("valor"));
                p.setDesconto(rs.getFloat("desconto"));
                p.setValorFinal(rs.getFloat("valorFinal"));
                produtos.add(p);
            }

            rs.close();
            stmt.close();
            conexao.close(); // Fechamento da conexão

        } catch (SQLException e) {
            e.printStackTrace(); // Tratamento de exceções
        }
        return produtos;
    }

    // Método para listar todos os produtos
    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();

        try {
            Connection conexao = Conexao.getConn();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            stmt = conexao.prepareStatement("SELECT * FROM produto");

            rs = stmt.executeQuery();

            while (rs.next()) {
                Produto p = new Produto();
                p.setIdProduto(rs.getInt("idProduto"));
                p.setNome(rs.getString("nome"));
                p.setCategoria(rs.getInt("categoria"));
                p.setValor(rs.getFloat("valor"));
                p.setDesconto(rs.getFloat("desconto"));
                p.setValorFinal(rs.getFloat("valorFinal"));

                // Recuperar a imagem como um array de bytes
                Blob imagemBlob = rs.getBlob("imagem");
                if (imagemBlob != null) {
                    byte[] imagemBytes = imagemBlob.getBytes(1, (int) imagemBlob.length());
                    p.setImagemBytes(imagemBytes);
                }
                produtos.add(p);
            }

            rs.close();
            stmt.close();
            conexao.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return produtos;
    }

    // Método para listar produtos por categoria
    public List<Produto> listarPorCategoria(Categoria c) {
        List<Produto> produtos = new ArrayList<>();
        try {
            Connection conexao = Conexao.getConn();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String query = "SELECT * FROM produto AS p INNER JOIN categoria AS c ON p.categoria = c.idCategoria WHERE c.nome = ?";

            stmt = conexao.prepareStatement(query);
            stmt.setString(1, c.getNome());

            rs = stmt.executeQuery();

            while (rs.next()) {
                Produto p = new Produto();
                p.setIdProduto(rs.getInt("idProduto"));
                p.setNome(rs.getString("nome"));
                p.setCategoria(rs.getInt("categoria"));
                p.setValor(rs.getFloat("valor"));
                p.setDesconto(rs.getFloat("desconto"));
                p.setValorFinal(rs.getFloat("valorFinal"));

                // Recuperar a imagem como um array de bytes
                Blob imagemBlob = rs.getBlob("imagem");
                if (imagemBlob != null) {
                    byte[] imagemBytes = imagemBlob.getBytes(1, (int) imagemBlob.length());
                    p.setImagemBytes(imagemBytes);
                }
                produtos.add(p);
            }
            rs.close();
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    public List<Produto> listarPorSubcategoria(int id) {
        List<Produto> produtos = new ArrayList<>();
        try {
            Connection conexao = Conexao.getConn();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String query = "SELECT * FROM produto AS p INNER JOIN subcategoria AS s ON p.subcategoria = s.idSubcategoria WHERE s.idSubcategoria = ?";

            stmt = conexao.prepareStatement(query);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Produto p = new Produto();
                p.setIdProduto(rs.getInt("idProduto"));
                p.setNome(rs.getString("nome"));
                p.setCategoria(rs.getInt("categoria"));
                p.setValor(rs.getFloat("valor"));
                p.setDesconto(rs.getFloat("desconto"));
                p.setValorFinal(rs.getFloat("valorFinal"));

                // Recuperar a imagem como um array de bytes
                Blob imagemBlob = rs.getBlob("imagem");
                if (imagemBlob != null) {
                    byte[] imagemBytes = imagemBlob.getBytes(1, (int) imagemBlob.length());
                    p.setImagemBytes(imagemBytes);
                }
                produtos.add(p);
            }
            rs.close();
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    // Método para listar produtos por pesquisa
    public List<Produto> listarPorPesquisa(String search) {
        List<Produto> produtos = new ArrayList<>();
        try {
            Connection conexao = Conexao.getConn();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            stmt = conexao.prepareStatement("SELECT * FROM produto WHERE nome LIKE ?");
            stmt.setString(1, "%" + search + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {
                Produto p = new Produto();
                p.setIdProduto(rs.getInt("idProduto"));
                p.setNome(rs.getString("nome"));
                p.setCategoria(rs.getInt("categoria"));
                p.setValor(rs.getFloat("valor"));
                p.setDesconto(rs.getFloat("desconto"));
                p.setValorFinal(rs.getFloat("valorFinal"));
                produtos.add(p);
            }

            rs.close();
            stmt.close();
            conexao.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return produtos;
    }

    // Método para obter um produto pelo seu ID
    public Produto readById(int id) {
        Produto p = null;
        try (Connection conexao = Conexao.getConn();
                PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM produto WHERE idProduto = ?")) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    p = new Produto();
                    p.setIdProduto(rs.getInt("idProduto"));
                    p.setNome(rs.getString("nome"));
                    p.setCategoria(rs.getInt("categoria"));
                    p.setValor(rs.getFloat("valor"));
                    p.setDesconto(rs.getFloat("desconto"));
                    p.setValorFinal(rs.getFloat("valorFinal"));

                    // Recuperar a imagem como um array de bytes
                    Blob imagemBlob = rs.getBlob("imagem");
                    if (imagemBlob != null) {
                        byte[] imagemBytes = imagemBlob.getBytes(1, (int) imagemBlob.length());
                        p.setImagemBytes(imagemBytes);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    // Método para criar um novo produto
    // Método para atualizar um produto existente
    // Método para excluir um produto pelo seu ID
    public void delete(int id) {
        try {
            Connection conexao = Conexao.getConn();
            PreparedStatement stmt = null;

            stmt = conexao.prepareStatement("DELETE FROM produto WHERE idProduto = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();

            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
