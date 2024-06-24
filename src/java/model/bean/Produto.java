/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;

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
    private double valor;
    private ProdutoImagem imagem;
    private byte[] imagemBytes;
    private String imagemBase64;
    private double quantidade;
    private int idProduto_Carrinho;
    private double precoCusto;
    private double quantidadeEstoque;
    private Timestamp dataCadastro;
    private boolean sabor;
    private boolean status;
    private int quantidadeVendida;
    private boolean novo;
    
    private int idUsuario;
   
    private int idSabor;
    private double valorAdicional;
    
   
    public Produto() {
    }

    public Produto(int idProduto, Categoria categoria, String nome, String descricao, double valor, ProdutoImagem imagem, byte[] imagemBytes, String imagemBase64, double quantidade, int idProduto_Carrinho, double precoCusto, double quantidadeEstoque, Timestamp dataCadastro, boolean sabor, boolean status, int quantidadeVendida, boolean novo, int idUsuario, int idSabor, double valorAdicional) {
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
        this.quantidadeVendida = quantidadeVendida;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
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

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public int getIdProduto_Carrinho() {
        return idProduto_Carrinho;
    }

    public void setIdProduto_Carrinho(int idProduto_Carrinho) {
        this.idProduto_Carrinho = idProduto_Carrinho;
    }

    public double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public double getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(double quantidadeEstoque) {
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

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
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

    public double getValorAdicional() {
        return valorAdicional;
    }

    public void setValorAdicional(double valorAdicional) {
        this.valorAdicional = valorAdicional;
    }
    public String formatadoValor() {
        return new DecimalFormat("0.00").format(valor);
    }

    public String formatadoCusto() {
        return new DecimalFormat("0.00").format(precoCusto);
    }
     public String formatadoValorAdicional() {
        return new DecimalFormat("0.00").format(valorAdicional);
    }
    
    
    
}
