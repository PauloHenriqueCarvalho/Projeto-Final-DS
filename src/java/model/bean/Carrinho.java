/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.util.List;


public class Carrinho {
    private List<ProdutoCarrinho> produtos;

    public Carrinho() {
    }

    public Carrinho(List<ProdutoCarrinho> produtos) {
        this.produtos = produtos;
    }

    public List<ProdutoCarrinho> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoCarrinho> produtos) {
        this.produtos = produtos;
    }
    
    public void addItem(Produto produto, int qtd){
        for(ProdutoCarrinho item : produtos) {
            if(item.getProduto().getIdProduto() == produto.getIdProduto()){
                item.setQuantidade(item.getQuantidade() + qtd);
                return;
            }
        }
        produtos.add(new ProdutoCarrinho(produto, qtd));
    }
    
    public void removerItem(int produtoId){
        produtos.removeIf(item -> item.getProduto().getIdProduto() == produtoId);
    }
    
    public List<ProdutoCarrinho> getItens(){
        return produtos;
    }
    
    public double getTotal(){
        double total = 0.0;
        for(ProdutoCarrinho item : produtos){
            total+= item.getQuantidade() * item.getProduto().getValor();
        }
        return total;
    }
    
    
    
}
