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
    private int subcategoria;
    private String nome;
    private String descricao;
    private String tamanhoCamisa;
    private String unidadeMedida;
    private float peso; 
    private Float valor;
    private Float desconto;
    private Float valorFinal;
    private int clube;
    private byte[] imagemBytes;
    private String imagemBase64;

    public Produto() {
    }
    
    
    public Produto(int idProduto, int categoria, int subcategoria, String nome, String descricao, String tamanhoCamisa, String unidadeMedida, float peso, Float valor, Float desconto, Float valorFinal, int clube, byte[] imagemBytes, String imagemBase64) {
        this.idProduto = idProduto;
        this.categoria = categoria;
        this.subcategoria = subcategoria;
        this.nome = nome;
        this.descricao = descricao;
        this.tamanhoCamisa = tamanhoCamisa;
        this.unidadeMedida = unidadeMedida;
        this.peso = peso;
        this.valor = valor;
        this.desconto = desconto;
        this.valorFinal = valorFinal;
        this.clube = clube;
        this.imagemBytes = imagemBytes;
        this.imagemBase64 = imagemBase64;
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

    public int getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(int subcategoria) {
        this.subcategoria = subcategoria;
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

    public String getTamanhoCamisa() {
        return tamanhoCamisa;
    }

    public void setTamanhoCamisa(String tamanhoCamisa) {
        this.tamanhoCamisa = tamanhoCamisa;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Float getDesconto() {
        return desconto;
    }

    public void setDesconto(Float desconto) {
        this.desconto = desconto;
    }

    public Float getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(Float valorFinal) {
        this.valorFinal = valorFinal;
    }

    public int getClube() {
        return clube;
    }

    public void setClube(int clube) {
        this.clube = clube;
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
    
}
