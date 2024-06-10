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
public class Empresa {
    private int idEmpresa;
    private float vendas;
    private float vendido;
    private int funcionarios;

    public Empresa() {
    }

    public Empresa(int idEmpresa, float vendas, float vendido, int funcionarios) {
        this.idEmpresa = idEmpresa;
        this.vendas = vendas;
        this.vendido = vendido;
        this.funcionarios = funcionarios;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public float getVendas() {
        return vendas;
    }

    public void setVendas(float vendas) {
        this.vendas = vendas;
    }

    public float getVendido() {
        return vendido;
    }

    public void setVendido(float vendido) {
        this.vendido = vendido;
    }

    public int getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(int funcionarios) {
        this.funcionarios = funcionarios;
    }
    
    
    
}
