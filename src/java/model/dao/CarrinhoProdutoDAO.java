/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.bean.CarrinhoProduto;
import model.bean.Produto;

/**
 *
 * @author Senai
 */
public class CarrinhoProdutoDAO {
    
    public boolean adicionarProdutoAoCarrinho(CarrinhoProduto carrinhoProduto) {
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Estabelecer conexão com o banco de dados
            conexao = Conexao.getConn();

            // Verificar se o produto já está no carrinho
            String query = "SELECT COUNT(*) AS total FROM carrinho_produto WHERE produto = ? AND carrinho = ?";
            stmt = conexao.prepareStatement(query);
            stmt.setInt(1, carrinhoProduto.getProduto());
            stmt.setInt(2, carrinhoProduto.getCarrinho());
            rs = stmt.executeQuery();

            if (rs.next()) {
                int total = rs.getInt("total");
                if (total > 0) {
                    // O produto já está no carrinho, não é necessário adicionar novamente
                    return false;
                }
            }

            // Preparar a consulta SQL para inserir o produto no carrinho
            String sql = "INSERT INTO carrinho_produto (produto, carrinho, tamanho) VALUES (?, ?, ?)";
            stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, carrinhoProduto.getProduto());
            stmt.setInt(2, carrinhoProduto.getCarrinho());
            stmt.setInt(3, carrinhoProduto.getQuantidade());

            // Executar a consulta
            int linhasAfetadas = stmt.executeUpdate();

            // Verificar se a inserção foi bem-sucedida (verificar se uma linha foi afetada)
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            // Lidar com exceções (por exemplo, registrar ou lançar para ser tratado mais tarde)
            e.printStackTrace();
            return false;
        } finally {
            // Fechar os recursos (result set, statement e conexão)
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Produto> listarProdutosDoCarrinho(int idCarrinho) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();
        Connection con = Conexao.getConn();
        try {
            stmt = con.prepareStatement("SELECT p.* FROM Produto p "
                    + "INNER JOIN carrinho_produto cp ON p.id_Produto = cp.ID_produto "
                    + "WHERE cp.carrinho = ?");
            stmt.setInt(1, idCarrinho); 
            rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(rs.getInt("id_Produto"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getFloat("valor"));
                // Defina outros atributos do produto conforme necessário
                System.out.println("Nome do produto: " + produto.getNome());
                produtos.add(produto);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException ex) {

            System.err.println("Erro ao listar produtos do carrinho: " + ex);
        }
        return produtos;
    }

    public boolean excluirProdutoCarrinho(int idCarrinho, int idProduto) {
        PreparedStatement stmt = null;
        Connection con = Conexao.getConn();
        try {
            stmt = con.prepareStatement("DELETE FROM carrinho_produto WHERE produto = ? AND carrinho = ?");
            stmt.setInt(1, idProduto);
            stmt.setInt(2, idCarrinho);
            stmt.executeUpdate();
            stmt.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao listar produtos do carrinho: " + ex);
            return false;
        }
    }
}
