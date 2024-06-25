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
import model.bean.ProdutoPedido;
import model.bean.ProdutoPedidoSabores;
import model.bean.Sabor;

/**
 *
 * @author paulo
 */
public class ProdutoPedidoSaboresDAO {
    public List<ProdutoPedidoSabores> read(){
        List<ProdutoPedidoSabores> lista = new ArrayList<>();
        try{
            Connection con = Conexao.getConn();
            PreparedStatement ps = con.prepareStatement("select * from produto_pedido_sabores");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                ProdutoPedidoSabores p = new ProdutoPedidoSabores();
                ProdutoPedido pc = new ProdutoPedido();
                Sabor s = new Sabor();
                
                s = dadosSabor(rs.getInt("id_sabor"));        
                pc.setId_produto_pedido(rs.getInt("id_produto_pedido"));
                
                p.setId_produto_pedido_sabores(rs.getInt("id_produto_pedido_sabores"));
                p.setId_produto_pedido(pc);
                p.setSabor(s);
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
    
    public Sabor dadosSabor(int id) {
    Sabor sabor = new Sabor();
    Connection c = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        c = Conexao.getConn();
        ps = c.prepareStatement("SELECT * FROM sabor WHERE id_sabor = ? AND idPai IS NOT NULL");
        ps.setInt(1, id);
        rs = ps.executeQuery();

        if (rs.next()) {
            sabor.setIdSabor(rs.getInt("id_sabor"));
            sabor.setIdProduto(rs.getObject("id_produto") != null ? rs.getInt("id_produto") : null);
            sabor.setNome(rs.getString("nome"));
            sabor.setIdPai(rs.getObject("idPai") != null ? rs.getInt("idPai") : null);
            sabor.setDescricao(rs.getString("descricao"));
            sabor.setValorAdicional(rs.getDouble("valorAdicional"));
            sabor.setStatus(rs.getString("status"));

            if (sabor.getIdPai() != null) {
                String sqlPai = "SELECT nome FROM sabor WHERE id_sabor = ?";
                try (PreparedStatement psPai = c.prepareStatement(sqlPai)) {
                    psPai.setInt(1, sabor.getIdPai());
                    try (ResultSet rsPai = psPai.executeQuery()) {
                        if (rsPai.next()) {
                            sabor.setNomePai(rsPai.getString("nome"));
                        }
                    }
                }
            }
             rs.close();
             ps.close();
            c.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } 

    return sabor;
}

}
