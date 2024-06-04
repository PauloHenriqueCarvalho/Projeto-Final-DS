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
public class Projeto {
    private static boolean sair;
    private static int idProdutoAtual;
    private static int idCategoriaAtual;
    private float valorCarrinho;
    private static boolean bolo;
    private static boolean salgado;
    private static boolean doce;

    public static int getIdCategoriaAtual() {
        return idCategoriaAtual;
    }

    public static void setIdCategoriaAtual(int idCategoriaAtual) {
        Projeto.idCategoriaAtual = idCategoriaAtual;
    }
    
    
    public Projeto() {
    }

    public Projeto(float valorCarrinho) {
        this.valorCarrinho = valorCarrinho;
    }

    public static boolean isSair() {
        return sair;
    }

    public static void setSair(boolean sair) {
        Projeto.sair = sair;
    }

    public static int getIdProdutoAtual() {
        return idProdutoAtual;
    }

    public static void setIdProdutoAtual(int idProdutoAtual) {
        Projeto.idProdutoAtual = idProdutoAtual;
    }

    public float getValorCarrinho() {
        return valorCarrinho;
    }

    public void setValorCarrinho(float valorCarrinho) {
        this.valorCarrinho = valorCarrinho;
    }

    public static boolean isBolo() {
        return bolo;
    }

    public static void setBolo(boolean bolo) {
        Projeto.bolo = bolo;
    }

    public static boolean isSalgado() {
        return salgado;
    }

    public static void setSalgado(boolean salgado) {
        Projeto.salgado = salgado;
    }

    public static boolean isDoce() {
        return doce;
    }

    public static void setDoce(boolean doce) {
        Projeto.doce = doce;
    }

  

    
    
    
    
}
