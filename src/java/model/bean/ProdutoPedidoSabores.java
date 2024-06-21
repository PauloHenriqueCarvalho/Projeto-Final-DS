/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author paulo
 */
public class ProdutoPedidoSabores {
    private int id_produto_pedido_sabores;
    private int id_produto_pedido;
    private Sabor sabor;

    public ProdutoPedidoSabores() {
    }

    public ProdutoPedidoSabores(int id_produto_pedido_sabores, int id_produto_pedido, Sabor sabor) {
        this.id_produto_pedido_sabores = id_produto_pedido_sabores;
        this.id_produto_pedido = id_produto_pedido;
        this.sabor = sabor;
    }

    public int getId_produto_pedido_sabores() {
        return id_produto_pedido_sabores;
    }

    public void setId_produto_pedido_sabores(int id_produto_pedido_sabores) {
        this.id_produto_pedido_sabores = id_produto_pedido_sabores;
    }

    public int getId_produto_pedido() {
        return id_produto_pedido;
    }

    public void setId_produto_pedido(int id_produto_pedido) {
        this.id_produto_pedido = id_produto_pedido;
    }

    public Sabor getSabor() {
        return sabor;
    }

    public void setSabor(Sabor sabor) {
        this.sabor = sabor;
    }
    
    
}
