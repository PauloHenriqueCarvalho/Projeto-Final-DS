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
import model.bean.Projeto;
import model.bean.Usuario;
import model.bean.Wishlist;

/**
 *
 * @author Paulo Henrique
 */
public class WishListDAO {
    
    
    public boolean validarSeJaTemNaLista(int id){
        boolean r = false;
        try{
            Connection c = Conexao.getConn();
            PreparedStatement ps = c.prepareStatement("select * from wishlist_produto where id_usuario = ? and id_produto = ?");
            ps.setInt(1, Usuario.getIdUsuarioStatic());
            ps.setInt(2, id);
            ResultSet rs = ps.executeQuery();
            if(!rs.next()){
                return true;
            }
            rs.close();
            ps.close();
            c.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
        return r;
    }
    
    public boolean removerProdutoDaLista(int id){
        boolean r = false;
        try{
            Connection c = Conexao.getConn();
            PreparedStatement ps = c.prepareStatement("delete from wishlist_produto where id_produto = ? and id_usuario = ?");
            ps.setInt(1, id);
            ps.setInt(2, Usuario.getIdUsuarioStatic());
            ps.executeUpdate();
            r = true;
            ps.close();
            c.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
        return r;
    }
    
    
    public boolean adicionarProdutoAoCarrinho(int w) {
        if(!validarSeJaTemNaLista(w)) return false;
        
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = Conexao.getConn();
            String sql = "INSERT INTO wishlist_produto (id_usuario, id_produto) VALUES (?, ?)";
            stmt = conexao.prepareStatement(sql);
            System.out.println("USUARIO: " + Usuario.getIdUsuarioStatic());
            System.out.println("prodytodu: " + w);
            stmt.setInt(1, Usuario.getIdUsuarioStatic());
            stmt.setInt(2, w);

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
    
    public List<Produto> readWishList(){
        List<Produto> produto = new ArrayList<>();
        
        try{
            Connection c = Conexao.getConn();
            PreparedStatement ps = c.prepareStatement("select * from wishlist_produto where id_usuario = ?");
            ps.setInt(1, Usuario.getIdUsuarioStatic());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Produto p = new Produto();
                int id = rs.getInt("id_produto");
                ProdutoDAO d = new ProdutoDAO();
                p = d.readById(id);
                
                produto.add(p);
                
            }
            
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return produto;
    }
}
