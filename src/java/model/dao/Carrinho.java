/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.bean.Produto;

/**
 *
 * @author Senai
 */
public class Carrinho {
    private static int id;
    private List<Produto> listaProdutos;
    private float preco;
    private int quantidadeItens;

    public Carrinho() {
    }

    public Carrinho(List<Produto> listaProdutos, float preco, int quantidadeItens) {
        this.listaProdutos = listaProdutos;
        this.preco = preco;
        this.quantidadeItens = quantidadeItens;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Carrinho.id = id;
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getQuantidadeItens() {
        return quantidadeItens;
    }

    public void setQuantidadeItens(int quantidadeItens) {
        this.quantidadeItens = quantidadeItens;
    }
    
    
    
            
    
}
