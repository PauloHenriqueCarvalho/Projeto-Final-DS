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
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.bean.Carrinho;
import model.bean.CarrinhoProduto;
import model.bean.Cobertura;
import model.bean.Massa;
import model.bean.Produto;
import model.bean.ProdutoCarrinho;
import model.bean.Projeto;
import model.bean.Recheio;
import model.bean.Sabor;
import model.bean.Topper;
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
    
    public boolean atualizarQuantidade(int idProdutoCarrinho, int quantidade) {
        String sql = "UPDATE produto_carrinho SET quantidade = ? WHERE id_produto_carrinho = ?";
        
        try{
            Connection conn = Conexao.getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, quantidade);
            pstmt.setInt(2, idProdutoCarrinho);
            int affectedRows = pstmt.executeUpdate();
            
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    
    public boolean excluirProdutoCarrinhoSemSabor(int idProdutoCarrinho) {
        PreparedStatement stmt = null;
        Connection con = Conexao.getConn();
        try {
            stmt = con.prepareStatement("DELETE FROM produto_carrinho WHERE id_produto_carrinho = ?");
            stmt.setInt(1, idProdutoCarrinho);
            stmt.executeUpdate();
            stmt.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean adicionarSabores(int idSabor, int produtoCarrinho) {
        try {
            Connection c = Conexao.getConn();
            PreparedStatement ps = c.prepareStatement("insert into produto_carrinho_sabores (id_produto_carrinho, id_sabor) values (?,?)");
            ps.setInt(1, produtoCarrinho);
            ps.setInt(2, idSabor);
            ps.executeUpdate();
            ps.close();
            c.close();
            return true;
        } catch (SQLException e) {
            excluirProdutoCarrinhoSemSabor(produtoCarrinho);
            e.printStackTrace();
            return false;
        }
    }

    public int adicionarProdutoAoCarrinho(ProdutoCarrinho produtoCarrinho, ArrayList<Integer> sabores) {
        float valorAdicional = 0;
        int id = 0;

        if (validaCarrinho(produtoCarrinho.getProduto().getIdProduto())) {
            return 0;
        }

        Connection conexao = null;
        PreparedStatement stmt = null;
        try {
            conexao = Conexao.getConn();
            String sql = "INSERT INTO produto_carrinho (id_produto, id_usuario, quantidade, id_massa, id_recheio, id_topper, id_cobertura, valorAdicional) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, produtoCarrinho.getProduto().getIdProduto());
            stmt.setInt(2, Usuario.getIdUsuarioStatic());
            stmt.setFloat(3, produtoCarrinho.getQuantidade());

            if (produtoCarrinho.getProduto().getCategoria() == 1) {
                stmt.setInt(4, produtoCarrinho.getIdMassa().getIdMassa());
                stmt.setInt(5, produtoCarrinho.getIdRecheio().getIdRecheio());
                stmt.setInt(6, produtoCarrinho.getIdTopper().getIdTopper());
                stmt.setInt(7, produtoCarrinho.getIdCobertura().getIdCobertura());
                valorAdicional += valorAdicionalCobertura(produtoCarrinho.getIdCobertura().getIdCobertura());
                valorAdicional += valorAdicionalMassa(produtoCarrinho.getIdMassa().getIdMassa());
                valorAdicional += valorAdicionalRecheio(produtoCarrinho.getIdRecheio().getIdRecheio());
                valorAdicional += valorAdicionalTopper(produtoCarrinho.getIdTopper().getIdTopper());
                valorAdicional += produtoCarrinho.getProduto().getValor();
                valorAdicional = valorAdicional * produtoCarrinho.getQuantidade();

                stmt.setFloat(8, valorAdicional);
            } else {
                float precoAdicionalSabores = 0;

                for (int i = 0; i < sabores.size(); i++) {
                    precoAdicionalSabores += valorAdicionalSabor(sabores.get(i));
                }
                System.out.println("Preco Sabor: " + precoAdicionalSabores);
                stmt.setNull(4, Types.INTEGER);
                stmt.setNull(5, Types.INTEGER);
                stmt.setNull(6, Types.INTEGER);
                stmt.setNull(7, Types.INTEGER);
                valorAdicional += precoAdicionalSabores;
                valorAdicional += produtoCarrinho.getProduto().getValor();
                valorAdicional = valorAdicional * produtoCarrinho.getQuantidade();
                stmt.setFloat(8, valorAdicional);
            }

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getInt(1); // Obtém o último ID gerado
                }
                rs.close(); // Fechar o ResultSet
            }
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
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

    public float valorAdicionalMassa(int idMassa) {
        float valor = 0;
        try {
            Connection c = Conexao.getConn();
            PreparedStatement ps = c.prepareStatement("SELECT * from massa where id_massa = ?");
            ps.setInt(1, idMassa);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                valor += rs.getFloat("valorAdicional");
            }
            rs.close();
            ps.close();
            c.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return valor;
    }

    public float valorAdicionalCobertura(int idMassa) {
        float valor = 0;
        try {
            Connection c = Conexao.getConn();
            PreparedStatement ps = c.prepareStatement("SELECT * from cobertura where id_cobertura = ?");
            ps.setInt(1, idMassa);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                valor += rs.getFloat("valorAdicional");
            }
            rs.close();
            ps.close();
            c.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return valor;
    }

    public float valorAdicionalRecheio(int idMassa) {
        float valor = 0;
        try {
            Connection c = Conexao.getConn();
            PreparedStatement ps = c.prepareStatement("SELECT * from Recheio where id_Recheio = ?");
            ps.setInt(1, idMassa);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                valor += rs.getFloat("valorAdicional");
            }
            rs.close();
            ps.close();
            c.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return valor;
    }

    public float valorAdicionalTopper(int idMassa) {
        float valor = 0;
        try {
            Connection c = Conexao.getConn();
            PreparedStatement ps = c.prepareStatement("SELECT * from Topper where id_Topper = ?");
            ps.setInt(1, idMassa);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                valor += rs.getFloat("valorAdicional");
            }
            rs.close();
            ps.close();
            c.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return valor;
    }

    public float valorAdicionalSabor(int idMassa) {
        float valor = 0;
        try {
            Connection c = Conexao.getConn();
            PreparedStatement ps = c.prepareStatement("SELECT * from Sabor where id_Sabor = ?");
            ps.setInt(1, idMassa);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                valor += rs.getFloat("valorAdicional");
            }
            rs.close();
            ps.close();
            c.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return valor;
    }

    public boolean validaCarrinho(int idProduto) {
        boolean retorno = false;
        try {
            Connection conexao = Conexao.getConn();
            PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM produto_carrinho where id_usuario = ? and id_produto = ?");
            stmt.setInt(1, Usuario.getIdUsuarioStatic());
            stmt.setInt(2, idProduto);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                retorno = true;
            }

            rs.close();
            stmt.close();
            conexao.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return retorno;
    }
    
    
    
     public Massa buscaPorIdMassa(int idMassa) {
        Massa m = new Massa();
        try {
            Connection c = Conexao.getConn();
            PreparedStatement ps = c.prepareStatement("SELECT * from massa where id_massa = ?");
            ps.setInt(1, idMassa);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                m.setIdMassa(rs.getInt("id_massa"));
                m.setNome(rs.getString("nome"));
            }
            rs.close();
            ps.close();
            c.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }
     
     public Recheio buscaPorIdRecheio(int idMassa) {
        Recheio m = new Recheio();
        try {
            Connection c = Conexao.getConn();
            PreparedStatement ps = c.prepareStatement("SELECT * from Recheio where id_Recheio = ?");
            ps.setInt(1, idMassa);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                m.setIdRecheio(rs.getInt("id_Recheio"));
                m.setNome(rs.getString("nome"));
            }
            rs.close();
            ps.close();
            c.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }
     
     public Topper buscaPorIdTopper(int idMassa) {
        Topper m = new Topper();
        try {
            Connection c = Conexao.getConn();
            PreparedStatement ps = c.prepareStatement("SELECT * from Topper where id_Topper = ?");
            ps.setInt(1, idMassa);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                m.setIdTopper(rs.getInt("id_Topper"));
                m.setNome(rs.getString("nome"));
            }
            rs.close();
            ps.close();
            c.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }
     
     public Cobertura buscaPorIdCobertura(int idMassa) {
        Cobertura m = new Cobertura();
        try {
            Connection c = Conexao.getConn();
            PreparedStatement ps = c.prepareStatement("SELECT * from Cobertura where id_Cobertura = ?");
            ps.setInt(1, idMassa);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                m.setIdCobertura(rs.getInt("id_Cobertura"));
                m.setNome(rs.getString("nome"));
            }
            rs.close();
            ps.close();
            c.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }
     
    
    public List<Produto> listarProdutosDoCarrinho() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();
        Connection con = Conexao.getConn();

        try {
            String query = "SELECT \n"
                    + "    p.id_produto, \n"
                    + "    p.nome, \n"
                    + "    p.descricao, \n"
                    + "    p.valor, \n"
                    + "    p.imagem, \n"
                    + "    p.id_categoria, \n"
                    + "    pc.id_produto_carrinho, \n"
                    + "    pc.id_usuario, \n"
                    + "    pc.id_massa, \n"
                    + "    pc.id_recheio, \n"
                    + "    pc.id_topper, \n"
                    + "    pc.id_cobertura, \n"
                    + "    pc.quantidade,\n"
                    + "    pc.valorAdicional\n"
                    + "FROM \n"
                    + "    produto p\n"
                    + "JOIN \n"
                    + "    produto_carrinho pc ON p.id_produto = pc.id_produto\n"
                    + "WHERE \n"
                    + "    pc.id_usuario = ?";

            stmt = con.prepareStatement(query);
            stmt.setInt(1, Usuario.getIdUsuarioStatic());
            rs = stmt.executeQuery();

            while (rs.next()) {
                Produto p = new Produto();
                Massa m = new Massa();
                Recheio r = new Recheio();
                Topper t = new Topper();
                Cobertura c = new Cobertura();
                m = buscaPorIdMassa(rs.getInt("id_massa"));
                r = buscaPorIdRecheio(rs.getInt("id_recheio"));
                t = buscaPorIdTopper(rs.getInt("id_topper"));
                c = buscaPorIdCobertura(rs.getInt("id_cobertura"));

                p.setIdProduto(rs.getInt("id_produto"));
                p.setNome(rs.getString("nome"));
                p.setCategoria(rs.getInt("id_categoria"));
                p.setValor(rs.getFloat("valor"));
                p.setDescricao(rs.getString("descricao"));
                p.setQuantidade(rs.getFloat("quantidade"));
                p.setIdProduto_Carrinho(rs.getInt("id_produto_carrinho"));

                // Atributos específicos de produto_carrinho
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                p.setIdUsuario(rs.getInt("id_usuario"));
                p.setIdMassa(m);
                p.setIdRecheio(r);
                p.setIdTopper(t);
                p.setIdCobertura(c);
                p.setValorAdicional(rs.getFloat("valorAdicional"));

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

    public boolean excluirProdutoCarrinho(int idProdutoCarrinho) {
        PreparedStatement stmt = null;
        Connection con = Conexao.getConn();
        try {

            stmt = con.prepareStatement("DELETE FROM produto_carrinho_sabores WHERE id_produto_carrinho = ?");
            stmt.setInt(1, idProdutoCarrinho);
            stmt.executeUpdate();
            stmt.close();

            stmt = con.prepareStatement("DELETE FROM produto_carrinho WHERE id_produto_carrinho = ?");
            stmt.setInt(1, idProdutoCarrinho);

            stmt.executeUpdate();
            stmt.close();
            con.close();
            System.out.println("Terue");
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
