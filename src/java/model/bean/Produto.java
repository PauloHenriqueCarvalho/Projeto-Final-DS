/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Senai
 */
public class Produto {
    private int idProduto;
    private static int idProdutoStatic;
    private Categoria categoria;
    private String nome;
    private String descricao;
    private Float valor;
    private ProdutoImagem imagem;
    private byte[] imagemBytes;
    private String imagemBase64;
    private float quantidade;
    private int idProduto_Carrinho;
    private float precoCusto;
    private float quantidadeEstoque;
    private Timestamp dataCadastro;
    private boolean sabor;
    private boolean status;
    
    private boolean novo;
    
    private int idUsuario;
   
    private int idSabor;
    private float valorAdicional;
    
   
    public Produto() {
    }

    public Produto(int idProduto, Categoria categoria, String nome, String descricao, Float valor, ProdutoImagem imagem, byte[] imagemBytes, String imagemBase64, float quantidade, int idProduto_Carrinho, float precoCusto, float quantidadeEstoque, Timestamp dataCadastro, boolean sabor, boolean status, boolean novo, int idUsuario, int idSabor, float valorAdicional) {
        this.idProduto = idProduto;
        this.categoria = categoria;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.imagem = imagem;
        this.imagemBytes = imagemBytes;
        this.imagemBase64 = imagemBase64;
        this.quantidade = quantidade;
        this.idProduto_Carrinho = idProduto_Carrinho;
        this.precoCusto = precoCusto;
        this.quantidadeEstoque = quantidadeEstoque;
        this.dataCadastro = dataCadastro;
        this.sabor = sabor;
        this.status = status;
        this.novo = novo;
        this.idUsuario = idUsuario;
        this.idSabor = idSabor;
        this.valorAdicional = valorAdicional;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
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

    public ProdutoImagem getImagem() {
        return imagem;
    }

    public void setImagem(ProdutoImagem imagem) {
        this.imagem = imagem;
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

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public int getIdProduto_Carrinho() {
        return idProduto_Carrinho;
    }

    public void setIdProduto_Carrinho(int idProduto_Carrinho) {
        this.idProduto_Carrinho = idProduto_Carrinho;
    }

    public float getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(float precoCusto) {
        this.precoCusto = precoCusto;
    }

    public float getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(float quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Timestamp getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Timestamp dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public boolean isSabor() {
        return sabor;
    }

    public void setSabor(boolean sabor) {
        this.sabor = sabor;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isNovo() {
        return novo;
    }

    public void setNovo(boolean novo) {
        this.novo = novo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdSabor() {
        return idSabor;
    }

    public void setIdSabor(int idSabor) {
        this.idSabor = idSabor;
    }

    public float getValorAdicional() {
        return valorAdicional;
    }

    public void setValorAdicional(float valorAdicional) {
        this.valorAdicional = valorAdicional;
    }

   

    

   
    
    
}
