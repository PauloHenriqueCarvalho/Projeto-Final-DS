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
import model.bean.Produto;
import model.bean.TipoProduto;

/**
 *
 * @author paulo
 */
public class TiposProdutosDAO {
    public List<TipoProduto> quantidadeProdutos(){
        List<TipoProduto> produtos = new ArrayList<>();
        try{
            Connection con = Conexao.getConn();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM produto");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                TipoProduto t = new TipoProduto();
                Produto p = new Produto();
                ProdutoDAO pDAO = new ProdutoDAO();
                p = pDAO.readById(rs.getInt("id_produto"));
                t.setNomeCategoria(p.getCategoria().getNome());  
                produtos.add(t);
            }
            rs.close();
            ps.close();
            con.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return produtos;
    }
   
}
