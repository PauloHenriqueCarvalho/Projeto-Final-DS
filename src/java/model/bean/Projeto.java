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

    public Projeto() {
    }

    public static int getIdProdutoAtual() {
        return idProdutoAtual;
    }

    public static void setIdProdutoAtual(int idProdutoAtual) {
        Projeto.idProdutoAtual = idProdutoAtual;
    }

    public static boolean isSair() {
        return sair;
    }

    public static void setSair(boolean sair) {
        Projeto.sair = sair;
    }

    
    
    
}
