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
public class ProdutoImagem {
    private int idProdutoImagem;
    private Produto produto;
    private byte[] imagemBytes;
    private String imagemBase64;
    private boolean imagemPadrao;

    public ProdutoImagem() {
    }

    public ProdutoImagem(int idProdutoImagem, Produto produto, byte[] imagemBytes, String imagemBase64, boolean imagemPadrao) {
        this.idProdutoImagem = idProdutoImagem;
        this.produto = produto;
        this.imagemBytes = imagemBytes;
        this.imagemBase64 = imagemBase64;
        this.imagemPadrao = imagemPadrao;
    }

    public int getIdProdutoImagem() {
        return idProdutoImagem;
    }

    public void setIdProdutoImagem(int idProdutoImagem) {
        this.idProdutoImagem = idProdutoImagem;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public byte[] getImagemBytes() {
        return imagemBytes;
    }

    public void setImagemBytes(byte[] imagemBytes) {
        this.imagemBytes = imagemBytes;
    }

    public String getImagemBase64() {
        return imagemBase64;
    }

    public void setImagemBase64(String imagemBase64) {
        this.imagemBase64 = imagemBase64;
    }

    public boolean isImagemPadrao() {
        return imagemPadrao;
    }

    public void setImagemPadrao(boolean imagemPadrao) {
        this.imagemPadrao = imagemPadrao;
    }

    
    
    
}
