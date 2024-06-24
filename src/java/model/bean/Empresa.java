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
public class Empresa {
    private int id;
    private int funcionarios;
    private int vendas;
   private double vendido;
   private double lucro;

    public Empresa() {
    }

    public Empresa(int id, int funcionarios, int vendas, double vendido, double lucro) {
        this.id = id;
        this.funcionarios = funcionarios;
        this.vendas = vendas;
        this.vendido = vendido;
        this.lucro = lucro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(int funcionarios) {
        this.funcionarios = funcionarios;
    }

    public int getVendas() {
        return vendas;
    }

    public void setVendas(int vendas) {
        this.vendas = vendas;
    }

    public double getVendido() {
        return vendido;
    }

    public void setVendido(double vendido) {
        this.vendido = vendido;
    }

    public double getLucro() {
        return lucro;
    }

    public void setLucro(double lucro) {
        this.lucro = lucro;
    }
    

  
}
