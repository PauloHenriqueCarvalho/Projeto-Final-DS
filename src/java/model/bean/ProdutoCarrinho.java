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
public class ProdutoCarrinho {

    private int idProdutoCarrinho;

    private Sabor idSabor;

    private Produto produto;
    private float quantidade;
    private Usuario idUsuario;
    private float valorAdicional;
    

    public ProdutoCarrinho() {
    }

    public ProdutoCarrinho(int idProdutoCarrinho, Sabor idSabor, Produto produto, float quantidade, Usuario idUsuario, float valorAdicional) {
        this.idProdutoCarrinho = idProdutoCarrinho;
        this.idSabor = idSabor;
        this.produto = produto;
        this.quantidade = quantidade;
        this.idUsuario = idUsuario;
        this.valorAdicional = valorAdicional;
    }

    public int getIdProdutoCarrinho() {
        return idProdutoCarrinho;
    }

    public void setIdProdutoCarrinho(int idProdutoCarrinho) {
        this.idProdutoCarrinho = idProdutoCarrinho;
    }

    public Sabor getIdSabor() {
        return idSabor;
    }

    public void setIdSabor(Sabor idSabor) {
        this.idSabor = idSabor;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public float getValorAdicional() {
        return valorAdicional;
    }

    public void setValorAdicional(float valorAdicional) {
        this.valorAdicional = valorAdicional;
    }

    

}
