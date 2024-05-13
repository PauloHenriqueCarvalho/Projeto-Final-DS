/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.sql.Date;

/**
 *
 * @author Senai
 */
public class Pedido {
    private int id_pedido;
    private int id_cliente;
    private int id_funcionario;
    private Date data_pedido;
    private String status;
    private float total;

    public Pedido() {
    }

    public Pedido(int id_pedido, int id_cliente, int id_funcionario, Date data_pedido, String status, float total) {
        this.id_pedido = id_pedido;
        this.id_cliente = id_cliente;
        this.id_funcionario = id_funcionario;
        this.data_pedido = data_pedido;
        this.status = status;
        this.total = total;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public Date getData_pedido() {
        return data_pedido;
    }

    public void setData_pedido(Date data_pedido) {
        this.data_pedido = data_pedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    
}
