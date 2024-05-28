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
public class Produto {
    private int idProduto;
    private static int idProdutoStatic;
    private int categoria;
    private String nome;
    private String descricao;
    private Float valor;
    private byte[] imagemBytes;
    private String imagemBase64;
    private Estoque estoque;
    private int quantidade;
    private int idProduto_Carrinho;

   
    public Produto() {
    }

    public Produto(int idProduto, int categoria, String nome, String descricao, Float valor, byte[] imagemBytes, String imagemBase64, Estoque estoque, int quantidade, int idProduto_Carrinho) {
        this.idProduto = idProduto;
        this.categoria = categoria;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.imagemBytes = imagemBytes;
        this.imagemBase64 = imagemBase64;
        this.estoque = estoque;
        this.quantidade = quantidade;
        this.idProduto_Carrinho = idProduto_Carrinho;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public static int getIdProdutoStatic() {
        return idProdutoStatic;
    }

    public static void setIdProdutoStatic(int idProdutoStatic) {
        Produto.idProdutoStatic = idProdutoStatic;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
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

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getIdProduto_Carrinho() {
        return idProduto_Carrinho;
    }

    public void setIdProduto_Carrinho(int idProduto_Carrinho) {
        this.idProduto_Carrinho = idProduto_Carrinho;
    }

    
    
   
    
    
    
}
