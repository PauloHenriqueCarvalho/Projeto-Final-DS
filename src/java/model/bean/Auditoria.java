/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.sql.Timestamp;

/**
 *
 * @author paulo
 */
public class Auditoria {
    private int id;
    private String operacao;
    private String tabela;
    private Timestamp data_operacao;
    private String dataFormatada;
    private String nome;

    public Auditoria() {
    }

    public Auditoria(int id, String operacao, String tabela, Timestamp data_operacao, String dataFormatada, String nome) {
        this.id = id;
        this.operacao = operacao;
        this.tabela = tabela;
        this.data_operacao = data_operacao;
        this.dataFormatada = dataFormatada;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public String getTabela() {
        return tabela;
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }

    public Timestamp getData_operacao() {
        return data_operacao;
    }

    public void setData_operacao(Timestamp data_operacao) {
        this.data_operacao = data_operacao;
    }

    public String getDataFormatada() {
        return dataFormatada;
    }

    public void setDataFormatada(String dataFormatada) {
        this.dataFormatada = dataFormatada;
    }
    
    
    
}
