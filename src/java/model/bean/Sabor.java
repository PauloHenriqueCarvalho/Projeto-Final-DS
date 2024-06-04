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
public class Sabor {
    private int idSabor;
    private Categoria idCategoria;
    private String nome;
    private float valorAdicional;

    public Sabor() {
    }

    public Sabor(int idSabor, Categoria idCategoria, String nome, float valorAdicional) {
        this.idSabor = idSabor;
        this.idCategoria = idCategoria;
        this.nome = nome;
        this.valorAdicional = valorAdicional;
    }
    
    
    public float getValorAdicional() {
        return valorAdicional;
    }

    public void setValorAdicional(float valorAdicional) {
        this.valorAdicional = valorAdicional;
    }

    

    public int getIdSabor() {
        return idSabor;
    }

    public void setIdSabor(int idSabor) {
        this.idSabor = idSabor;
    }

    public Categoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categoria idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    
}
