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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;
import javax.resource.cci.ConnectionFactory;
import model.bean.Categoria;

import model.bean.Produto;

/**
 *
 * @author Paulo Henrique
 */
public class ProdutoDAO {

    public boolean verificarProduto(String nome) {
        boolean retorno = false;
        try {
            Connection con = Conexao.getConn();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM produto WHERE nome = ?");
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                retorno = true;
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retorno;
    }

    public void alterarStatus(boolean status, int id) {
        try {
            Connection con = Conexao.getConn();
            PreparedStatement ps = con.prepareStatement("UPDATE PRODUTO SET status = ? WHERE id_produto = ?");
            if (status) {
                ps.setString(1, "disponivel");
            } else {
                ps.setString(1, "Indisponivel");
            }
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int inserirProduto(Produto produto) {
        int idGerado = -1;

        if (verificarProduto(produto.getNome())) {
            return -2;
        }

        try (Connection conexao = Conexao.getConn();
                PreparedStatement ps = conexao.prepareStatement(
                        "INSERT INTO produto (nome, valor, id_categoria, descricao, preco_custo, quantidade_estoque) VALUES (?,?,?,?,?,?)",
                        PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, produto.getNome());
            ps.setFloat(2, produto.getValor());
            ps.setInt(3, produto.getCategoria().getIdCategoria());
            ps.setString(4, produto.getDescricao());
            ps.setFloat(5, produto.getPrecoCusto());
            ps.setFloat(6, produto.getQuantidadeEstoque());

            int linhasAfetadas = ps.executeUpdate();

            // Recupera o ID gerado
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    idGerado = rs.getInt(1);
                }
                rs.close();
            }

            ps.close();
            conexao.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idGerado;
    }

    public Produto readById(int id) {
        Produto produto = null;
        ProdutoImagemDAO dao = new ProdutoImagemDAO();
        try {
            Connection c = Conexao.getConn();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM produto WHERE id_produto = ?");
            System.out.println("Id Produto: " + id);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                produto = new Produto();
                produto.setIdProduto(rs.getInt("id_produto"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setValor(rs.getFloat("valor"));
                produto.setCategoria(categoria);
                produto.setDataCadastro(rs.getTimestamp("data_cadastro"));
                produto.setPrecoCusto(rs.getFloat("preco_custo"));
                produto.setQuantidadeEstoque(rs.getInt("quantidade_Estoque"));
                Blob imagemBlob = dao.imagemPadrao(rs.getInt("id_produto"));
                if (imagemBlob != null) {
                    byte[] imagemBytes = imagemBlob.getBytes(1, (int) imagemBlob.length());
                    produto.setImagemBytes(imagemBytes);
                }
            }
            rs.close();
            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produto;
    }

    // Método para listar todos os produtos
    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();
        ProdutoImagemDAO dao = new ProdutoImagemDAO();
        try {
            Connection conexao = Conexao.getConn();
            PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM produto");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto p = new Produto();
                p.setIdProduto(rs.getInt("id_produto"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setValor(rs.getFloat("valor"));
                p.setDataCadastro(rs.getTimestamp("data_cadastro"));
                p.setPrecoCusto(rs.getFloat("preco_custo"));
                p.setQuantidadeEstoque(rs.getInt("quantidade_Estoque"));

                //Imagem do Produto
                Blob imagemBlob = dao.imagemPadrao(rs.getInt("id_produto"));
                if (imagemBlob != null) {
                    byte[] imagemBytes = imagemBlob.getBytes(1, (int) imagemBlob.length());
                    p.setImagemBytes(imagemBytes);
                }
                //Fim imagem do produto

                Categoria categoria = new Categoria();
                CategoriaDAO daoc = new CategoriaDAO();
                categoria = daoc.readById(rs.getInt("id_categoria"));
                p.setCategoria(categoria);

                //Verificar se o produto ta disponivel
                boolean statusCategoria = false;
                if (categoria.isStatus()) {
                    statusCategoria = true;
                }

                if (rs.getString("status").equals("disponivel") && statusCategoria) {
                    p.setStatus(true);
                    produtos.add(p);
                } else {
                    p.setStatus(false);
                }
                //Fim  verificar se o produto ta disponivel

                // Verificar se o produto é novo
                Timestamp dataCadastro = rs.getTimestamp("data_cadastro");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dataCadastro);
                calendar.add(Calendar.DAY_OF_MONTH, 7);
                Timestamp dataCadastroMais7Dias = new Timestamp(calendar.getTimeInMillis());

                if (dataCadastro.before(dataCadastroMais7Dias)) {
                    p.setNovo(true);
                } else {
                    p.setNovo(false);
                }
                //Fim verificar se o produto é novo

            }
            rs.close();
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    public List<Produto> listarTodosAdm() {
        List<Produto> produtos = new ArrayList<>();
        ProdutoImagemDAO dao = new ProdutoImagemDAO();
        try {
            Connection conexao = Conexao.getConn();
            PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM produto");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto p = new Produto();
                p.setIdProduto(rs.getInt("id_produto"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setValor(rs.getFloat("valor"));
                p.setDataCadastro(rs.getTimestamp("data_cadastro"));
                p.setPrecoCusto(rs.getFloat("preco_custo"));
                p.setQuantidadeEstoque(rs.getInt("quantidade_Estoque"));

                //Imagem do Produto
                Blob imagemBlob = dao.imagemPadrao(rs.getInt("id_produto"));
                if (imagemBlob != null) {
                    byte[] imagemBytes = imagemBlob.getBytes(1, (int) imagemBlob.length());
                    p.setImagemBytes(imagemBytes);
                }
                //Fim imagem do produto

                Categoria categoria = new Categoria();
                CategoriaDAO daoc = new CategoriaDAO();
                categoria = daoc.readById(rs.getInt("id_categoria"));
                p.setCategoria(categoria);

                //Verificar se o produto ta disponivel
                if (rs.getString("status").equals("disponivel")) {
                    p.setStatus(true);
                    
                } else {
                    p.setStatus(false);
                }
                produtos.add(p);
                //Fim  verificar se o produto ta disponivel

                // Verificar se o produto é novo
                Timestamp dataCadastro = rs.getTimestamp("data_cadastro");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dataCadastro);
                calendar.add(Calendar.DAY_OF_MONTH, 7);
                Timestamp dataCadastroMais7Dias = new Timestamp(calendar.getTimeInMillis());

                if (dataCadastro.before(dataCadastroMais7Dias)) {
                    p.setNovo(true);
                } else {
                    p.setNovo(false);
                }
                //Fim verificar se o produto é novo

            }
            rs.close();
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    public List<Produto> listarPorCategoria(int idCategoria, String filter, int currentPage, int productsPerPage) {
        List<Produto> produtos = new ArrayList<>();
        ProdutoImagemDAO dao = new ProdutoImagemDAO();
        try {
            Connection con = Conexao.getConn();
            String sql = "SELECT * FROM produto WHERE id_categoria = ?";
            if (filter != null) {
                if (filter.equals("asc")) {
                    sql += " ORDER BY valor ASC";
                } else if (filter.equals("desc")) {
                    sql += " ORDER BY valor DESC";
                }
            }
            sql += " LIMIT ? OFFSET ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idCategoria);
            stmt.setInt(2, productsPerPage);
            stmt.setInt(3, (currentPage - 1) * productsPerPage);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto p = new Produto();
                p.setIdProduto(rs.getInt("id_produto"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setValor(rs.getFloat("valor"));
                p.setDataCadastro(rs.getTimestamp("data_cadastro"));
                p.setPrecoCusto(rs.getFloat("preco_custo"));
                p.setQuantidadeEstoque(rs.getInt("quantidade_Estoque"));

                //Imagem do Produto
                Blob imagemBlob = dao.imagemPadrao(rs.getInt("id_produto"));
                if (imagemBlob != null) {
                    byte[] imagemBytes = imagemBlob.getBytes(1, (int) imagemBlob.length());
                    p.setImagemBytes(imagemBytes);
                }
                //Fim imagem do produto

                Categoria categoria = new Categoria();
                CategoriaDAO daoc = new CategoriaDAO();
                categoria = daoc.readById(rs.getInt("id_categoria"));
                p.setCategoria(categoria);

                //Verificar se o produto ta disponivel
                boolean statusCategoria = false;
                if (categoria.isStatus()) {
                    statusCategoria = true;
                }

                if (rs.getString("status").equals("disponivel") && statusCategoria) {
                    p.setStatus(true);
                    produtos.add(p);
                } else {
                    p.setStatus(false);
                }
                //Fim  verificar se o produto ta disponivel

                // Verificar se o produto é novo
                Timestamp dataCadastro = rs.getTimestamp("data_cadastro");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dataCadastro);
                calendar.add(Calendar.DAY_OF_MONTH, 7);
                Timestamp dataCadastroMais7Dias = new Timestamp(calendar.getTimeInMillis());

                if (dataCadastro.before(dataCadastroMais7Dias)) {
                    p.setNovo(true);
                } else {
                    p.setNovo(false);
                }
                //Fim verificar se o produto é novo
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    public List<Produto> listarTodos(String filter, int currentPage, int productsPerPage) {
        List<Produto> produtos = new ArrayList<>();
        ProdutoImagemDAO dao = new ProdutoImagemDAO();
        try {
            Connection con = Conexao.getConn();
            String sql = "SELECT * FROM produto";
            if (filter != null) {
                if (filter.equals("asc")) {
                    sql += " ORDER BY valor ASC";
                } else if (filter.equals("desc")) {
                    sql += " ORDER BY valor DESC";
                }
            }
            sql += " LIMIT ? OFFSET ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, productsPerPage);
            stmt.setInt(2, (currentPage - 1) * productsPerPage);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto p = new Produto();
                p.setIdProduto(rs.getInt("id_produto"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setValor(rs.getFloat("valor"));
                p.setDataCadastro(rs.getTimestamp("data_cadastro"));
                p.setPrecoCusto(rs.getFloat("preco_custo"));
                p.setQuantidadeEstoque(rs.getInt("quantidade_Estoque"));

                //Imagem do Produto
                Blob imagemBlob = dao.imagemPadrao(rs.getInt("id_produto"));
                if (imagemBlob != null) {
                    byte[] imagemBytes = imagemBlob.getBytes(1, (int) imagemBlob.length());
                    p.setImagemBytes(imagemBytes);
                }
                //Fim imagem do produto

                Categoria categoria = new Categoria();
                CategoriaDAO daoc = new CategoriaDAO();
                categoria = daoc.readById(rs.getInt("id_categoria"));
                p.setCategoria(categoria);

                //Verificar se o produto ta disponivel
                boolean statusCategoria = false;
                if (categoria.isStatus()) {
                    statusCategoria = true;
                }

                if (rs.getString("status").equals("disponivel") && statusCategoria) {
                    p.setStatus(true);
                    produtos.add(p);
                } else {
                    p.setStatus(false);
                }
                //Fim  verificar se o produto ta disponivel

                // Verificar se o produto é novo
                Timestamp dataCadastro = rs.getTimestamp("data_cadastro");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dataCadastro);
                calendar.add(Calendar.DAY_OF_MONTH, 7);
                Timestamp dataCadastroMais7Dias = new Timestamp(calendar.getTimeInMillis());

                if (dataCadastro.before(dataCadastroMais7Dias)) {
                    p.setNovo(true);
                } else {
                    p.setNovo(false);
                }
                //Fim verificar se o produto é novo
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    public int countProdutosByCategoria(int idCategoria) {
        int total = 0;
        try {
            Connection con = Conexao.getConn();
            String sql = "SELECT COUNT(*) AS total FROM produto WHERE id_categoria = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idCategoria);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public int countProdutosTodos() {
        int total = 0;
        try {
            Connection con = Conexao.getConn();
            String sql = "SELECT COUNT(*) AS total FROM produto";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public List<Produto> listarPorPesquisa(String search) {
        List<Produto> produtos = new ArrayList<>();
        ProdutoImagemDAO dao = new ProdutoImagemDAO();
        try {
            Connection conexao = Conexao.getConn();
            PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM produto WHERE nome LIKE ?");
            stmt.setString(1, "%" + search + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto p = new Produto();
                p.setIdProduto(rs.getInt("id_produto"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setValor(rs.getFloat("valor"));
                p.setDataCadastro(rs.getTimestamp("data_cadastro"));
                p.setPrecoCusto(rs.getFloat("preco_custo"));
                p.setQuantidadeEstoque(rs.getInt("quantidade_Estoque"));

                //Imagem do Produto
                Blob imagemBlob = dao.imagemPadrao(rs.getInt("id_produto"));
                if (imagemBlob != null) {
                    byte[] imagemBytes = imagemBlob.getBytes(1, (int) imagemBlob.length());
                    p.setImagemBytes(imagemBytes);
                }
                //Fim imagem do produto

                Categoria categoria = new Categoria();
                CategoriaDAO daoc = new CategoriaDAO();
                categoria = daoc.readById(rs.getInt("id_categoria"));
                p.setCategoria(categoria);

                //Verificar se o produto ta disponivel
                boolean statusCategoria = false;
                if (categoria.isStatus()) {
                    statusCategoria = true;
                }

                if (rs.getString("status").equals("disponivel") && statusCategoria) {
                    p.setStatus(true);
                    produtos.add(p);
                } else {
                    p.setStatus(false);
                }
                //Fim  verificar se o produto ta disponivel

                // Verificar se o produto é novo
                Timestamp dataCadastro = rs.getTimestamp("data_cadastro");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dataCadastro);
                calendar.add(Calendar.DAY_OF_MONTH, 7);
                Timestamp dataCadastroMais7Dias = new Timestamp(calendar.getTimeInMillis());

                if (dataCadastro.before(dataCadastroMais7Dias)) {
                    p.setNovo(true);
                } else {
                    p.setNovo(false);
                }
                //Fim verificar se o produto é novo
            }
            rs.close();
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    public void delete(int id) {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = Conexao.getConn();
            conexao.setAutoCommit(false);  // Inicia uma transação

            // Excluir registros nas tabelas que referenciam produto
            String[] deleteQueries = {
                "DELETE FROM produto_imagem WHERE id_produto = ?",
                "DELETE FROM sabor WHERE id_produto = ?",
                "DELETE FROM produto_carrinho WHERE id_produto = ?",
                "DELETE FROM produto_pedido WHERE id_produto = ?",
                "DELETE FROM wishlist_produto WHERE id_produto = ?"
            };

            for (String query : deleteQueries) {
                stmt = conexao.prepareStatement(query);
                stmt.setInt(1, id);
                stmt.executeUpdate();
                stmt.close();
            }

            // Excluir o produto
            stmt = conexao.prepareStatement("DELETE FROM produto WHERE id_produto = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();

            // Commit da transação
            conexao.commit();
        } catch (SQLException e) {
            try {
                if (conexao != null) {
                    conexao.rollback();  // Reverte a transação em caso de erro
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
            e.printStackTrace();
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

    public List<Produto> listarPorCategoriaBusca(int idCategoria, String filter, int currentPage, int productsPerPage, String busca) {
        List<Produto> produtos = new ArrayList<>();
        ProdutoImagemDAO dao = new ProdutoImagemDAO();
        try {
            Connection con = Conexao.getConn();
            String sql = "SELECT * FROM produto WHERE id_categoria = ? AND nome LIKE ?";
            if (filter != null) {
                if (filter.equals("asc")) {
                    sql += " ORDER BY valor ASC";
                } else if (filter.equals("desc")) {
                    sql += " ORDER BY valor DESC";
                }
            }
            sql += " LIMIT ? OFFSET ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idCategoria);
            stmt.setString(2, "%" + busca + "%");
            stmt.setInt(3, productsPerPage);
            stmt.setInt(4, (currentPage - 1) * productsPerPage);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto p = new Produto();
                p.setIdProduto(rs.getInt("id_produto"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setValor(rs.getFloat("valor"));
                p.setDataCadastro(rs.getTimestamp("data_cadastro"));
                p.setPrecoCusto(rs.getFloat("preco_custo"));
                p.setQuantidadeEstoque(rs.getInt("quantidade_Estoque"));

                //Imagem do Produto
                Blob imagemBlob = dao.imagemPadrao(rs.getInt("id_produto"));
                if (imagemBlob != null) {
                    byte[] imagemBytes = imagemBlob.getBytes(1, (int) imagemBlob.length());
                    p.setImagemBytes(imagemBytes);
                }
                //Fim imagem do produto

                Categoria categoria = new Categoria();
                CategoriaDAO daoc = new CategoriaDAO();
                categoria = daoc.readById(rs.getInt("id_categoria"));
                p.setCategoria(categoria);

                //Verificar se o produto ta disponivel
                boolean statusCategoria = false;
                if (categoria.isStatus()) {
                    statusCategoria = true;
                }

                if (rs.getString("status").equals("disponivel") && statusCategoria) {
                    p.setStatus(true);
                    produtos.add(p);
                } else {
                    p.setStatus(false);
                }
                //Fim  verificar se o produto ta disponivel

                // Verificar se o produto é novo
                Timestamp dataCadastro = rs.getTimestamp("data_cadastro");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dataCadastro);
                calendar.add(Calendar.DAY_OF_MONTH, 7);
                Timestamp dataCadastroMais7Dias = new Timestamp(calendar.getTimeInMillis());

                if (dataCadastro.before(dataCadastroMais7Dias)) {
                    p.setNovo(true);
                } else {
                    p.setNovo(false);
                }
                //Fim verificar se o produto é novo
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    public List<Produto> busca(String busca) {
        List<Produto> produtos = new ArrayList<>();
        ProdutoImagemDAO dao = new ProdutoImagemDAO();
        try {
            Connection conexao = Conexao.getConn();
            PreparedStatement ps = conexao.prepareStatement("SELECT * FROM produto WHERE nome LIKE ?");
            ps.setString(1, "%" + busca + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Produto p = new Produto();
                p.setIdProduto(rs.getInt("id_produto"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setValor(rs.getFloat("valor"));
                p.setDataCadastro(rs.getTimestamp("data_cadastro"));
                p.setPrecoCusto(rs.getFloat("preco_custo"));
                p.setQuantidadeEstoque(rs.getInt("quantidade_Estoque"));

                //Imagem do Produto
                Blob imagemBlob = dao.imagemPadrao(rs.getInt("id_produto"));
                if (imagemBlob != null) {
                    byte[] imagemBytes = imagemBlob.getBytes(1, (int) imagemBlob.length());
                    p.setImagemBytes(imagemBytes);
                }
                //Fim imagem do produto

                Categoria categoria = new Categoria();
                CategoriaDAO daoc = new CategoriaDAO();
                categoria = daoc.readById(rs.getInt("id_categoria"));
                p.setCategoria(categoria);

                //Verificar se o produto ta disponivel
                boolean statusCategoria = false;
                if (categoria.isStatus()) {
                    statusCategoria = true;
                }

                if (rs.getString("status").equals("disponivel") && statusCategoria) {
                    p.setStatus(true);
                    produtos.add(p);
                } else {
                    p.setStatus(false);
                }
                //Fim  verificar se o produto ta disponivel

                // Verificar se o produto é novo
                Timestamp dataCadastro = rs.getTimestamp("data_cadastro");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dataCadastro);
                calendar.add(Calendar.DAY_OF_MONTH, 7);
                Timestamp dataCadastroMais7Dias = new Timestamp(calendar.getTimeInMillis());

                if (dataCadastro.before(dataCadastroMais7Dias)) {
                    p.setNovo(true);
                } else {
                    p.setNovo(false);
                }
                //Fim verificar se o produto é novo
            }
            rs.close();
            ps.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

}
