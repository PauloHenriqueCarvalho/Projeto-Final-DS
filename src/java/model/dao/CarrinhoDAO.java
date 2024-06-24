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
import model.bean.Carrinho;
import model.bean.CarrinhoProduto;
import model.bean.Categoria;
import model.bean.Produto;
import model.bean.ProdutoCarrinho;
import model.bean.Usuario;



/**
 *
 * @author Paulo Henrique
 */
public class CarrinhoDAO {
    

    
    private Blob imagemPadrao(int idProduto){
        Blob imagem = null;
        try{
            Connection con = Conexao.getConn();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM produto_imagem WHERE id_produto = ? AND imagemPadrao = true");
            ps.setInt(1, idProduto);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                imagem = rs.getBlob("imagem");
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return imagem;
    }
    
    
    public List<Produto> lerProdutos() {
        
        List<Produto> produtos = new ArrayList();
        try {
            java.sql.Connection conexao = Conexao.getConn();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            stmt = conexao.prepareStatement("SELECT p.* FROM produto_carrinho AS cp JOIN produto AS p ON cp.id_produto = p.id_Produto"
                    + " JOIN carrinho AS c ON cp.id_carrinho = c.id_Carrinho JOIN usuario AS u ON c.id_usuario = u.id_Usuario WHERE u.id_Usuario = ?");
            stmt.setInt(1, Usuario.getIdUsuarioStatic());
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Produto p = new Produto();
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("id_categoria"));
                p.setIdProduto(rs.getInt("id_produto"));
                p.setNome(rs.getString("nome"));
                p.setCategoria(c);
                p.setValor(rs.getDouble("valor"));
                p.setDescricao(rs.getString("descricao"));

                Blob imagemBlob = imagemPadrao(rs.getInt("id_produto"));
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
    
    public double precoCarrinho(){
        double preco = 0;
        try{
            Connection c = Conexao.getConn();
            PreparedStatement ps = c.prepareStatement("Select * from produto_carrinho where id_usuario = ?");
            ps.setInt(1, Usuario.getIdUsuarioStatic());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                preco += rs.getDouble("valorAdicional") * rs.getInt("quantidade");
                
            }
            rs.close();
            ps.close();
            c.close();
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        return preco;
    }
    
}
