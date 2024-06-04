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
public class ProdutoCarrinhoSabores {
    private int idProdutoCarrinhoSabores;
    private ProdutoCarrinho idProdutoCarrinho;
    private Sabor idSabor;

    public ProdutoCarrinhoSabores() {
    }

    public ProdutoCarrinhoSabores(int idProdutoCarrinhoSabores, ProdutoCarrinho idProdutoCarrinho, Sabor idSabor) {
        this.idProdutoCarrinhoSabores = idProdutoCarrinhoSabores;
        this.idProdutoCarrinho = idProdutoCarrinho;
        this.idSabor = idSabor;
    }

    public int getIdProdutoCarrinhoSabores() {
        return idProdutoCarrinhoSabores;
    }

    public void setIdProdutoCarrinhoSabores(int idProdutoCarrinhoSabores) {
        this.idProdutoCarrinhoSabores = idProdutoCarrinhoSabores;
    }

    public ProdutoCarrinho getIdProdutoCarrinho() {
        return idProdutoCarrinho;
    }

    public void setIdProdutoCarrinho(ProdutoCarrinho idProdutoCarrinho) {
        this.idProdutoCarrinho = idProdutoCarrinho;
    }

    public Sabor getIdSabor() {
        return idSabor;
    }

    public void setIdSabor(Sabor idSabor) {
        this.idSabor = idSabor;
    }
    
}
