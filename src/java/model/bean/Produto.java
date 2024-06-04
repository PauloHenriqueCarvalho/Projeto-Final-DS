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
    private float quantidade;
    private int idProduto_Carrinho;
    
    private int idUsuario;
    private Massa idMassa;
    private Recheio idRecheio;
    private Topper idTopper;
    private int idSabor;
    private Cobertura idCobertura;
    private float valorAdicional;
    
   
    public Produto() {
    }

    public Produto(int idProduto, int categoria, String nome, String descricao, Float valor, byte[] imagemBytes, String imagemBase64, Estoque estoque, float quantidade, int idProduto_Carrinho, int idUsuario, Massa idMassa, Recheio idRecheio, Topper idTopper, int idSabor, Cobertura idCobertura, float valorAdicional) {
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
        this.idUsuario = idUsuario;
        this.idMassa = idMassa;
        this.idRecheio = idRecheio;
        this.idTopper = idTopper;
        this.idSabor = idSabor;
        this.idCobertura = idCobertura;
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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public int getIdSabor() {
        return idSabor;
    }

    public void setIdSabor(int idSabor) {
        this.idSabor = idSabor;
    }

    public Cobertura getIdCobertura() {
        return idCobertura;
    }

    public void setIdCobertura(Cobertura idCobertura) {
        this.idCobertura = idCobertura;
    }

    public float getValorAdicional() {
        return valorAdicional;
    }

    public void setValorAdicional(float valorAdicional) {
        this.valorAdicional = valorAdicional;
    }

   
    
    
   
    
    
    
}
