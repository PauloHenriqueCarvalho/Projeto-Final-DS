/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.text.DecimalFormat;

/**
 *
 * @author Senai
 */
public class ProdutoCarrinho {

    private int idProdutoCarrinho;

    private Sabor idSabor;

    private Produto produto;
    private double quantidade;
    private Usuario idUsuario;
    private double valorAdicional;
    

    public ProdutoCarrinho() {
    }

    public ProdutoCarrinho(int idProdutoCarrinho, Sabor idSabor, Produto produto, double quantidade, Usuario idUsuario, double valorAdicional) {
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

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getValorAdicional() {
        return valorAdicional;
    }

    public void setValorAdicional(double valorAdicional) {
        this.valorAdicional = valorAdicional;
    }
    public String formatadoValorAdicional() {
        return new DecimalFormat("0.00").format(valorAdicional);
    }


    

}
