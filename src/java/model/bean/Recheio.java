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
public class Recheio {
     private int idRecheio;
     private String nome;
     private float valorAdicional;

    public Recheio() {
    }

    public Recheio(int idRecheio, String nome, float valorAdicional) {
        this.idRecheio = idRecheio;
        this.nome = nome;
        this.valorAdicional = valorAdicional;
    }

    public float getValorAdicional() {
        return valorAdicional;
    }

    public void setValorAdicional(float valorAdicional) {
        this.valorAdicional = valorAdicional;
    }

    

    public int getIdRecheio() {
        return idRecheio;
    }

    public void setIdRecheio(int idRecheio) {
        this.idRecheio = idRecheio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
     
     
}
