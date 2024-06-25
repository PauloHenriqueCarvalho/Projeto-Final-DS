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
import model.bean.Categoria;
import model.bean.ProdutoCarrinho;
import model.bean.ProdutoCarrinhoSabores;
import model.bean.Sabor;

/**
 *
 * @author paulo
 */
public class ProdutoCarrinhoSaboresDAO {
    public List<ProdutoCarrinhoSabores> read(){
        List<ProdutoCarrinhoSabores> lista = new ArrayList<>();
        try{
            Connection con = Conexao.getConn();
            PreparedStatement ps = con.prepareStatement("select * from produto_carrinho_sabores");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                ProdutoCarrinhoSabores p = new ProdutoCarrinhoSabores();
                ProdutoCarrinho pc = new ProdutoCarrinho();
                Sabor s = new Sabor();
                
                s = dadosSabor(rs.getInt("id_sabor"));        
                pc.setIdProdutoCarrinho(rs.getInt("id_produto_carrinho"));
                
                p.setIdProdutoCarrinhoSabores(rs.getInt("id_produto_carrinho_sabores"));
                p.setIdProdutoCarrinho(pc);
                p.setIdSabor(s);
                lista.add(p);
            }
            
            rs.close();
            ps.close();
            con.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return lista;
    }
    public List<ProdutoCarrinhoSabores> readByProdutoCarrinhoId(int id){
        List<ProdutoCarrinhoSabores> lista = new ArrayList<>();
        try{
            Connection con = Conexao.getConn();
            PreparedStatement ps = con.prepareStatement("select * from produto_carrinho_sabores WHERE id_produto_carrinho = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                ProdutoCarrinhoSabores p = new ProdutoCarrinhoSabores();
                ProdutoCarrinho pc = new ProdutoCarrinho();
                Sabor s = new Sabor();
                
                s = dadosSabor(rs.getInt("id_sabor"));        
                pc.setIdProdutoCarrinho(rs.getInt("id_produto_carrinho"));
                
                p.setIdProdutoCarrinhoSabores(rs.getInt("id_produto_carrinho_sabores"));
                p.setIdProdutoCarrinho(pc);
                p.setIdSabor(s);
                lista.add(p);
            }
            
            rs.close();
            ps.close();
            con.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return lista;
    }
    
    public Sabor dadosSabor(int id){
        Sabor sabor = new Sabor();
        try{
            Connection c = Conexao.getConn();
            PreparedStatement ps = c.prepareStatement("select * from sabor where id_sabor = ? AND idPai != 0");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
             
                sabor.setIdSabor(rs.getInt("id_sabor"));
                sabor.setIdPai(rs.getObject("id_produto") != null ? rs.getInt("id_produto") : null);
                sabor.setNome(rs.getString("nome"));
                sabor.setIdPai(rs.getObject("idPai") != null ? rs.getInt("idPai") : null);
                sabor.setDescricao(rs.getString("descricao"));
                sabor.setValorAdicional(rs.getDouble("valorAdicional"));
                sabor.setStatus(rs.getString("status"));
            }
            rs.close();
            ps.close();
            c.close();
            
        } catch(SQLException e){
            e.printStackTrace();
        }
        return sabor;
    }
}
