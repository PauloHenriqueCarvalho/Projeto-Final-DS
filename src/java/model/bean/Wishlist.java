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
public class Wishlist {
    private int IdWishlist;
    private Usuario IdUsuario;
    private Produto IdProduto;
    private Date dataAdicionado;

    public Wishlist() {
    }

    public Wishlist(int IdWishlist, Usuario IdUsuario, Produto IdProduto, Date dataAdicionado) {
        this.IdWishlist = IdWishlist;
        this.IdUsuario = IdUsuario;
        this.IdProduto = IdProduto;
        this.dataAdicionado = dataAdicionado;
    }

    public int getIdWishlist() {
        return IdWishlist;
    }

    public void setIdWishlist(int IdWishlist) {
        this.IdWishlist = IdWishlist;
    }

    public Usuario getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(Usuario IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public Produto getIdProduto() {
        return IdProduto;
    }

    public void setIdProduto(Produto IdProduto) {
        this.IdProduto = IdProduto;
    }

    public Date getDataAdicionado() {
        return dataAdicionado;
    }

    public void setDataAdicionado(Date dataAdicionado) {
        this.dataAdicionado = dataAdicionado;
    }
    
    
    
   
    
    
    
}
