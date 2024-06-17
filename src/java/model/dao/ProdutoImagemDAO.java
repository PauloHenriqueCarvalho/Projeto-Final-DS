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
import java.util.List;
import model.bean.ProdutoImagem;

/**
 *
 * @author paulo
 */
public class ProdutoImagemDAO {
    public boolean verificarImagem(int idProduto) {
        boolean retorno = false;
        try {
            Connection con = Conexao.getConn();
            String query = "SELECT * FROM produto_imagem WHERE id_produto = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, idProduto);
            ResultSet rs  = stmt.executeQuery();
            while(rs.next()){
                if(rs.getBoolean("imagemPadrao"))retorno = true;   
            }
            rs.close();
            stmt.close();
            con.close();
          
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return retorno;
    }
    
    public Blob imagemPadrao(int idProduto) {
        Blob retorno = null;
        try {
            Connection con = Conexao.getConn();
            String query = "SELECT * FROM produto_imagem WHERE id_produto = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, idProduto);
            ResultSet rs  = stmt.executeQuery();
            while(rs.next()){
                if(rs.getBoolean("imagemPadrao"))retorno = rs.getBlob("imagem");   
            }

            rs.close();
            stmt.close();
            con.close();
          
        } catch (SQLException ex) {
            ex.printStackTrace();
           
        }
        return retorno;
    }
    
    public boolean inserirImagem(ProdutoImagem p) {
        boolean imagemPadrao = false;
        if(verificarImagem(p.getProduto().getIdProduto())) imagemPadrao = false;
        else imagemPadrao = true;
        
        try {
            Connection con = Conexao.getConn();
            String query = "INSERT INTO produto_imagem (id_produto, imagemPadrao, imagem) values (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, p.getProduto().getIdProduto());
            ps.setBoolean(2, imagemPadrao);
            ps.setBytes(3, p.getImagemBytes());
            
            ps.executeUpdate();
            ps.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        
    }
    
    public List<ProdutoImagem> listarImagem(int idProdutoImagem) {
        List<ProdutoImagem> imagens = new ArrayList<>();
        try {
            Connection con = Conexao.getConn();
            String query = "SELECT *  FROM produto_imagem WHERE id_produto = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            
            stmt.setInt(1, idProdutoImagem);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                ProdutoImagem p = new ProdutoImagem();
                p.setImagemPadrao(rs.getBoolean("imagemPadrao"));
                Blob imagemBlob = rs.getBlob("imagem");
                if (imagemBlob != null) {
                    byte[] imagemBytes = imagemBlob.getBytes(1, (int) imagemBlob.length());
                    p.setImagemBytes(imagemBytes);
                }
                imagens.add(p);
            }

            stmt.close();
            con.close();
           
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
        return imagens;
    }
    
    

    public boolean removerImagem(int idProdutoImagem) {
        try {
            Connection con = Conexao.getConn();
            String query = "DELETE FROM produto_imagem WHERE id_produto_imagem = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, idProdutoImagem);
            int rowsAffected = stmt.executeUpdate();

            stmt.close();
            con.close();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
