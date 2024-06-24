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
public class Categoria {
    private int idCategoria;
    private String nome;
    private boolean status;
    private int qtd;
    
    public Categoria() {
    }

    public Categoria(int idCategoria, String nome, boolean status, int qtd) {
        this.idCategoria = idCategoria;
        this.nome = nome;
        this.status = status;
        this.qtd = qtd;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    

    


    
    
}
