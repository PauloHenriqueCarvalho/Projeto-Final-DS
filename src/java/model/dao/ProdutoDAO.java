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
import javax.resource.cci.ConnectionFactory;
import model.bean.Categoria;
import model.bean.Estoque;
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
                PreparedStatement ps = conexao.prepareStatement("INSERT INTO produto (nome, valor, id_categoria, imagem, descricao) VALUES (?, ?, ?, ?, ?)")) {

            ps.setString(1, produto.getNome());
            ps.setFloat(2, produto.getValor());
            ps.setInt(3, produto.getCategoria());
            ps.setBytes(4, produto.getImagemBytes());
            ps.setString(5, produto.getDescricao());

            int linhasAfetadas = ps.executeUpdate();
            ps.close();
            conexao.close();

            return linhasAfetadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Produto> listarTodosDisponiveis() {
        List<Produto> produtos = new ArrayList<>();

        try {
            Connection conexao = Conexao.getConn();
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

    public float readById(int id) {
        float produto = 0;
        try {
            Connection c = Conexao.getConn();
            PreparedStatement ps = c.prepareStatement("select * from produto where id_produto = ?");
            System.out.println("Id Prouto: " + id);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                produto = rs.getFloat("valor");
            }
            rs.close();
            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produto;
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
                p.setIdProduto(rs.getInt("id_produto"));
                p.setNome(rs.getString("nome"));
                p.setCategoria(rs.getInt("id_categoria"));
                p.setValor(rs.getFloat("valor"));
                p.setDescricao(rs.getString("descricao"));

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

    public List<Produto> listarPorCategoria(int idCategoria, String filter, int currentPage, int productsPerPage) {
        List<Produto> produtos = new ArrayList<>();

        try {
            Connection con = Conexao.getConn();
            String sql = "SELECT * FROM produto WHERE id_categoria = ?";

            if (filter != null) {
                if (filter.equals("asc")) {
                    sql += " ORDER BY valor ASC";
                } else if (filter.equals("desc")) {
                    sql += " ORDER BY valor DESC";
                }
            }

            sql += " LIMIT ? OFFSET ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, idCategoria);
            stmt.setInt(2, productsPerPage);
            stmt.setInt(3, (currentPage - 1) * productsPerPage);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(rs.getInt("id_produto"));
                produto.setCategoria(rs.getInt("id_categoria"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setValor(rs.getFloat("valor"));
                produto.setImagemBytes(rs.getBytes("imagem"));
                produtos.add(produto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return produtos;
    }

    public int countProdutosByCategoria(int idCategoria) {
        int total = 0;
        
        try {
             Connection con = Conexao.getConn();
            String sql = "SELECT COUNT(*) AS total FROM produto WHERE id_categoria = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idCategoria);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    // Método para listar produtos por categoria
    public List<Produto> listarPorCategoria(int c) {
        List<Produto> produtos = new ArrayList<>();
        try {
            Connection conexao = Conexao.getConn();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String query = "SELECT * FROM produto WHERE id_categoria = ?";

            stmt = conexao.prepareStatement(query);
            stmt.setInt(1, c);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Produto p = new Produto();
                p.setIdProduto(rs.getInt("id_Produto"));
                p.setNome(rs.getString("nome"));
                p.setCategoria(rs.getInt("id_categoria"));
                p.setValor(rs.getFloat("valor"));   

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
    public Produto buscarPorId(int id) {
        Produto p = null;
        try (Connection conexao = Conexao.getConn();
                PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM produto WHERE id_Produto = ?")) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    p = new Produto();
                    p.setIdProduto(rs.getInt("id_Produto"));
                    p.setNome(rs.getString("nome"));
                    p.setCategoria(rs.getInt("id_categoria"));
                    p.setValor(rs.getFloat("valor"));
                    p.setDescricao(rs.getString("descricao"));
                    // Recuperar a imagem como um array de bytes
                    Blob imagemBlob = rs.getBlob("imagem");
                    if (imagemBlob != null) {
                        byte[] imagemBytes = imagemBlob.getBytes(1, (int) imagemBlob.length());
                        p.setImagemBytes(imagemBytes);
                    }
                }
            }

            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public List<Produto> listarTodosComEstoque() {
        List<Produto> produtos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexao.getConn();
            String sql = "SELECT p.*, e.quantidade, e.preco_custo FROM produto p INNER JOIN estoque e ON p.id_Produto = e.id_produto;";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(rs.getInt("id_Produto"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getFloat("valor"));
                // Defina outros atributos do produto...

                // Cria um estoque para o produto
                Estoque estoque = new Estoque();
                estoque.setQuantidade(rs.getInt("quantidade"));
                estoque.setCusto(rs.getFloat("preco_custo"));
                Blob imagemBlob = rs.getBlob("imagem");
                if (imagemBlob != null) {
                    byte[] imagemBytes = imagemBlob.getBytes(1, (int) imagemBlob.length());
                    produto.setImagemBytes(imagemBytes);
                }
                // Associa o estoque ao produto
                produto.setEstoque(estoque);

                // Adiciona o produto à lista
                produtos.add(produto);

            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return produtos;
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

    public List<Produto> busca(String busca) {
        List<Produto> produtos = new ArrayList<>();
        try {
            Connection conexao = Conexao.getConn();
            PreparedStatement ps = conexao.prepareStatement("SELECT * FROM produto WHERE nome LIKE ?");
            ps.setString(1, "%" + busca + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(rs.getInt("id_Produto"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getFloat("valor"));
                // Defina outros atributos do produto...

                // Cria um estoque para o produto
                Blob imagemBlob = rs.getBlob("imagem");
                if (imagemBlob != null) {
                    byte[] imagemBytes = imagemBlob.getBytes(1, (int) imagemBlob.length());
                    produto.setImagemBytes(imagemBytes);
                }
                // Associa o estoque ao produto

                // Adiciona o produto à lista
                produtos.add(produto);
            }

            // Feche os recursos
            rs.close();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }

}
