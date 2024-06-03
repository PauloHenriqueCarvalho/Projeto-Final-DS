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
import model.bean.Produto;
import model.bean.ProdutoCarrinho;
import model.bean.Usuario;



/**
 *
 * @author Joao Guilherme
 */
public class CarrinhoDAO {
    
    /*
    Esse método deve retornar todos os produtos de um usuário fornecido como
    parâmetro, portanto deve ser chamado na página que exibe o carrinho.  
    */
    private List<Produto> lerProdutos() {
        
        List<Produto> produtos = new ArrayList();
        try {
            java.sql.Connection conexao = Conexao.getConn();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            stmt = conexao.prepareStatement("SELECT p.* FROM carrinho_produto AS cp JOIN produto AS p ON cp.id_produto = p.id_Produto"
                    + " JOIN carrinho AS c ON cp.id_carrinho = c.id_Carrinho JOIN usuario AS u ON c.id_usuario = u.id_Usuario WHERE u.id_Usuario = ?");
            stmt.setInt(1, Usuario.getIdUsuarioStatic());
            
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
    
    public float precoCarrinho(){
        float preco = 0;
        try{
            Connection c = Conexao.getConn();
            PreparedStatement ps = c.prepareStatement("Select * from produto_carrinho where id_usuario = ?");
            ps.setInt(1, Usuario.getIdUsuarioStatic());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                preco += rs.getFloat("valorAdicional");
                
            }
            System.out.println("Preco final: " + preco);
            rs.close();
            ps.close();
            c.close();
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        return preco;
    }
    
}
