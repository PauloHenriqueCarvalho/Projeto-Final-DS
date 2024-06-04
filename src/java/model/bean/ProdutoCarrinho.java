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
    private Massa idMassa;
    private Recheio idRecheio;
    private Topper idTopper;
    private Sabor idSabor;
    private Cobertura idCobertura;
    private Produto produto;
    private float quantidade;
    private Usuario idUsuario;
    private float valorAdicional;
    

    public ProdutoCarrinho() {
    }

    public ProdutoCarrinho(int idProdutoCarrinho, Massa idMassa, Recheio idRecheio, Topper idTopper, Sabor idSabor, Cobertura idCobertura, Produto produto, float quantidade, Usuario idUsuario, float valorAdicional) {
        this.idProdutoCarrinho = idProdutoCarrinho;
        this.idMassa = idMassa;
        this.idRecheio = idRecheio;
        this.idTopper = idTopper;
        this.idSabor = idSabor;
        this.idCobertura = idCobertura;
        this.produto = produto;
        this.quantidade = quantidade;
        this.idUsuario = idUsuario;
        this.valorAdicional = valorAdicional;
    }

   

    public float getValorAdicional() {
        return valorAdicional;
    }

    public void setValorAdicional(float valorAdicional) {
        this.valorAdicional = valorAdicional;
    }
    

    public int getIdProdutoCarrinho() {
        return idProdutoCarrinho;
    }

    public void setIdProdutoCarrinho(int idProdutoCarrinho) {
        this.idProdutoCarrinho = idProdutoCarrinho;
    }

    public Massa getIdMassa() {
        return idMassa;
    }

    public void setIdMassa(Massa idMassa) {
        this.idMassa = idMassa;
    }

    public Recheio getIdRecheio() {
        return idRecheio;
    }

    public void setIdRecheio(Recheio idRecheio) {
        this.idRecheio = idRecheio;
    }

    public Topper getIdTopper() {
        return idTopper;
    }

    public void setIdTopper(Topper idTopper) {
        this.idTopper = idTopper;
    }

    public Sabor getIdSabor() {
        return idSabor;
    }

    public void setIdSabor(Sabor idSabor) {
        this.idSabor = idSabor;
    }

    public Cobertura getIdCobertura() {
        return idCobertura;
    }

    public void setIdCobertura(Cobertura idCobertura) {
        this.idCobertura = idCobertura;
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

}
