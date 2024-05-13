/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author Senai
 */
public class ProdutoPedido {
    private int id_produto_pedido;
    private int id_pedido;
    private int id_produto;
    private int qtd;

    public ProdutoPedido() {
    }

    public ProdutoPedido(int id_produto_pedido, int id_pedido, int id_produto, int qtd) {
        this.id_produto_pedido = id_produto_pedido;
        this.id_pedido = id_pedido;
        this.id_produto = id_produto;
        this.qtd = qtd;
    }

    public int getId_produto_pedido() {
        return id_produto_pedido;
    }

    public void setId_produto_pedido(int id_produto_pedido) {
        this.id_produto_pedido = id_produto_pedido;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
    
    
}
