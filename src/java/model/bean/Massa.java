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
public class Massa {
     private int idMassa;
     private String nome;
     private float valorAdicional;

    public Massa() {
    }

    public Massa(int idMassa, String nome, float valorAdicional) {
        this.idMassa = idMassa;
        this.nome = nome;
        this.valorAdicional = valorAdicional;
    }

    public float getValorAdicional() {
        return valorAdicional;
    }

    public void setValorAdicional(float valorAdicional) {
        this.valorAdicional = valorAdicional;
    }

    

    public int getIdMassa() {
        return idMassa;
    }

    public void setIdMassa(int idMassa) {
        this.idMassa = idMassa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
     
     
}
