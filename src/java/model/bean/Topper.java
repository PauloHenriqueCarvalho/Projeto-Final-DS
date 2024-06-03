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
public class Topper {
     private int idTopper;
     private String nome;
     private float valorAdicional;

    public Topper() {
    }

    public Topper(int idTopper, String nome, float valorAdicional) {
        this.idTopper = idTopper;
        this.nome = nome;
        this.valorAdicional = valorAdicional;
    }

    public float getValorAdicional() {
        return valorAdicional;
    }

    public void setValorAdicional(float valorAdicional) {
        this.valorAdicional = valorAdicional;
    }

    
    public int getIdTopper() {
        return idTopper;
    }

    public void setIdTopper(int idTopper) {
        this.idTopper = idTopper;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
     
}
