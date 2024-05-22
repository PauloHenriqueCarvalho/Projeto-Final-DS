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
public class CarrinhoProduto {
    private int idCarrinhoProduto;
    private Carrinho carrinho;
    private Produto produto;
    private int quantidade;

    public CarrinhoProduto() {
    }

    public int getIdCarrinhoProduto() {
        return idCarrinhoProduto;
    }

    public void setIdCarrinhoProduto(int idCarrinhoProduto) {
        this.idCarrinhoProduto = idCarrinhoProduto;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public CarrinhoProduto(int idCarrinhoProduto, Carrinho carrinho, Produto produto, int quantidade) {
        this.idCarrinhoProduto = idCarrinhoProduto;
        this.carrinho = carrinho;
        this.produto = produto;
        this.quantidade = quantidade;
    }

   
    
    
    
    
}
