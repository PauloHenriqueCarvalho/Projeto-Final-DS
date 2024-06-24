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
public class PedidosGrafico {
    private String dataentrega;
    private double total;

    public PedidosGrafico() {
    }

    public PedidosGrafico(String dataentrega, double total) {
        this.dataentrega = dataentrega;
        this.total = total;
    }

    public String getDataentrega() {
        return dataentrega;
    }

    public void setDataentrega(String dataentrega) {
        this.dataentrega = dataentrega;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
}
