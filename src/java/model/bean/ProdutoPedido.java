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
    private Pedido id_pedido;
    private Produto id_produto;
    private float valorAdicional;
    private float quantidade;

    public ProdutoPedido() {
    }

    public ProdutoPedido(int id_produto_pedido, Pedido id_pedido, Produto id_produto, float valorAdicional, float quantidade) {
        this.id_produto_pedido = id_produto_pedido;
        this.id_pedido = id_pedido;
        this.id_produto = id_produto;
        this.valorAdicional = valorAdicional;
        this.quantidade = quantidade;
    }

    public int getId_produto_pedido() {
        return id_produto_pedido;
    }

    public void setId_produto_pedido(int id_produto_pedido) {
        this.id_produto_pedido = id_produto_pedido;
    }

    public Pedido getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Pedido id_pedido) {
        this.id_pedido = id_pedido;
    }

    public Produto getId_produto() {
        return id_produto;
    }

    public void setId_produto(Produto id_produto) {
        this.id_produto = id_produto;
    }

    public float getValorAdicional() {
        return valorAdicional;
    }

    public void setValorAdicional(float valorAdicional) {
        this.valorAdicional = valorAdicional;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    

    

    
    
    
}
