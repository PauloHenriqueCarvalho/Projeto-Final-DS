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
public class FormaPagamento {
    private int id_forma_pagamento;
    private String nome;
    private String descricao;

    public FormaPagamento() {
    }

    public FormaPagamento(int id_forma_pagamento, String nome, String descricao) {
        this.id_forma_pagamento = id_forma_pagamento;
        this.nome = nome;
        this.descricao = descricao;
    }
    
    
}
