/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 *
 * @author paulo
 */
public class Sabor {
    private int idSabor;
    private Integer idProduto;
    private String nome;
    private Integer idPai; // Pode ser null, então use Integer ao invés de int
    private String descricao;
    private Double valorAdicional;
    private String status;
    public Sabor() {
    }

    public Sabor(int idSabor, Integer idProduto, String nome, Integer idPai, String descricao, Double valorAdicional, String status) {
        this.idSabor = idSabor;
        this.idProduto = idProduto;
        this.nome = nome;
        this.idPai = idPai;
        this.descricao = descricao;
        this.valorAdicional = valorAdicional;
        this.status = status;
    }

    public int getIdSabor() {
        return idSabor;
    }

    public void setIdSabor(int idSabor) {
        this.idSabor = idSabor;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdPai() {
        return idPai;
    }

    public void setIdPai(Integer idPai) {
        this.idPai = idPai;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValorAdicional() {
        return valorAdicional;
    }

    public void setValorAdicional(Double valorAdicional) {
        this.valorAdicional = valorAdicional;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    public String formatadoValorAdicional() {
        return new DecimalFormat("0.00").format(valorAdicional);
    }

   
    

    
    
}
