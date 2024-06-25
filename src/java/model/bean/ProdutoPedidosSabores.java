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
public class ProdutoPedidosSabores {
    private int idProdutoPedidosSabores;
    private ProdutoCarrinho idProdutoPedido;
    private Sabor idSabor;

    public ProdutoPedidosSabores() {
    }

    public ProdutoPedidosSabores(int idProdutoPedidosSabores, ProdutoCarrinho idProdutoPedido, Sabor idSabor) {
        this.idProdutoPedidosSabores = idProdutoPedidosSabores;
        this.idProdutoPedido = idProdutoPedido;
        this.idSabor = idSabor;
    }

    public int getIdProdutoPedidosSabores() {
        return idProdutoPedidosSabores;
    }

    public void setIdProdutoPedidosSabores(int idProdutoPedidosSabores) {
        this.idProdutoPedidosSabores = idProdutoPedidosSabores;
    }

    public ProdutoCarrinho getIdProdutoPedido() {
        return idProdutoPedido;
    }

    public void setIdProdutoPedido(ProdutoCarrinho idProdutoPedido) {
        this.idProdutoPedido = idProdutoPedido;
    }

    public Sabor getIdSabor() {
        return idSabor;
    }

    public void setIdSabor(Sabor idSabor) {
        this.idSabor = idSabor;
    }
    
   
}
