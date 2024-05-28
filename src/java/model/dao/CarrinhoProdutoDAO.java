/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;

import conexao.Conexao;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.bean.Carrinho;
import model.bean.CarrinhoProduto;
import model.bean.Produto;
import model.bean.Projeto;
import model.bean.Usuario;

/**
 *
 * @author Joao Guilherme
 */
public class CarrinhoProdutoDAO {

    /*try {
            Connection conexao = Conexao.conectar();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String query = "";
            
            stmt = conexao.prepareStatement(query);
            
            
            rs.close();
            stmt.close();
            conexao.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
                   
        }*/
    public boolean adicionarProdutoAoCarrinho(CarrinhoProduto carrinhoProduto) {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = Conexao.getConn();
            String sql = "INSERT INTO carrinho_produto (id_produto, id_carrinho, quantidade) VALUES (?, ?, ?)";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, Projeto.getIdProdutoAtual());
            stmt.setInt(2, Usuario.getIdUsuarioStatic());
            stmt.setInt(3, carrinhoProduto.getQuantidade());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;   
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Produto> listarProdutosDoCarrinho() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();
        Connection con = Conexao.getConn();
        try {
            stmt = con.prepareStatement("SELECT p.*, cp.quantidade, cp.id_carrinho_produto\n"
                    + "FROM produto p\n"
                    + "INNER JOIN carrinho_produto cp ON p.id_produto = cp.id_produto\n"
                    + "WHERE cp.id_carrinho = ?;");
            stmt.setInt(1, Usuario.getIdUsuarioStatic());
            rs = stmt.executeQuery();

            while (rs.next()) {
                Produto p = new Produto();
                p.setIdProduto(rs.getInt("id_produto"));
                p.setNome(rs.getString("nome"));
                p.setCategoria(rs.getInt("id_categoria"));
                p.setValor(rs.getFloat("valor"));
                p.setDescricao(rs.getString("descricao"));
                p.setQuantidade(rs.getInt("quantidade"));
                p.setIdProduto_Carrinho(rs.getInt("id_carrinho_produto"));
                Blob imagemBlob = rs.getBlob("imagem");
                if (imagemBlob != null) {
                    byte[] imagemBytes = imagemBlob.getBytes(1, (int) imagemBlob.length());
                    p.setImagemBytes(imagemBytes);
                }
                produtos.add(p);
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
            stmt = con.prepareStatement("DELETE FROM carrinho_produto WHERE id_produto = ? AND id_carrinho = ?");
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
