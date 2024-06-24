/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author Senai
 */
public class Pedido {
    private int id_pedido;  
    private Usuario id_cliente;
    private FormaPagamento idPagamento;
    private Timestamp data_pedido;
    private Timestamp data_entrega;
    private String status;
    private double total;
    private double frete;
    private Endereco id_endereco;
    
    private static Endereco id_enderecoAtual;
    private static int id_pedidoAtual;
    private static double freteAtual;
    private static Timestamp data_entregaAtual;
    private static FormaPagamento idPagamentoStatic;
    private String dataEntregaFormatada;

    public String getDataEntregaFormatada() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(data_entrega);
    }
    

    public Pedido() {
    }

    public static FormaPagamento getIdPagamentoStatic() {
        return idPagamentoStatic;
    }

    public static void setIdPagamentoStatic(FormaPagamento idPagamentoStatic) {
        Pedido.idPagamentoStatic = idPagamentoStatic;
    }

    
    public Pedido(int id_pedido, Usuario id_cliente, FormaPagamento idPagamento, Timestamp data_pedido, Timestamp data_entrega, String status, double total, double frete, Endereco id_endereco, String dataEntregaFormatada) {
        this.id_pedido = id_pedido;
        this.id_cliente = id_cliente;
        this.idPagamento = idPagamento;
        this.data_pedido = data_pedido;
        this.data_entrega = data_entrega;
        this.status = status;
        this.total = total;
        this.frete = frete;
        this.id_endereco = id_endereco;
        this.dataEntregaFormatada = dataEntregaFormatada;
    }

    public void setDataEntregaFormatada(String dataEntregaFormatada) {
        this.dataEntregaFormatada = dataEntregaFormatada;
    }
    
    
    
   

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    

   
    public Usuario getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Usuario id_cliente) {
        this.id_cliente = id_cliente;
    }

    public FormaPagamento getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(FormaPagamento idPagamento) {
        this.idPagamento = idPagamento;
    }

    public Timestamp getData_pedido() {
        return data_pedido;
    }

    public void setData_pedido(Timestamp data_pedido) {
        this.data_pedido = data_pedido;
    }

    public Timestamp getData_entrega() {
        return data_entrega;
    }

    public void setData_entrega(Timestamp data_entrega) {
        this.data_entrega = data_entrega;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getFrete() {
        return frete;
    }

    public void setFrete(double frete) {
        this.frete = frete;
    }

    public Endereco getId_endereco() {
        return id_endereco;
    }

    public void setId_endereco(Endereco id_endereco) {
        this.id_endereco = id_endereco;
    }

    public static Endereco getId_enderecoAtual() {
        return id_enderecoAtual;
    }

    public static void setId_enderecoAtual(Endereco id_enderecoAtual) {
        Pedido.id_enderecoAtual = id_enderecoAtual;
    }

    public static int getId_pedidoAtual() {
        return id_pedidoAtual;
    }

    public static void setId_pedidoAtual(int id_pedidoAtual) {
        Pedido.id_pedidoAtual = id_pedidoAtual;
    }

    public static double getFreteAtual() {
        return freteAtual;
    }

    public static void setFreteAtual(double freteAtual) {
        Pedido.freteAtual = freteAtual;
    }

    public static Timestamp getData_entregaAtual() {
        return data_entregaAtual;
    }

    public static void setData_entregaAtual(Timestamp data_entregaAtual) {
        Pedido.data_entregaAtual = data_entregaAtual;
    }

    
    

    
    
   

    
    
    
}
