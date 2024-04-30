/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.sql.Timestamp;

/**
 *
 * @author Senai
 */
public class Usuario {
    private int idUsuario;
    private static int idUsuarioStatic;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String cpf;
    private Timestamp dataRegistro;
    private String acesso;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nome, String email, String senha, String telefone, String cpf, Timestamp dataRegistro, String acesso) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.cpf = cpf;
        this.dataRegistro = dataRegistro;
        this.acesso = acesso;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public static int getIdUsuarioStatic() {
        return idUsuarioStatic;
    }

    public static void setIdUsuarioStatic(int idUsuarioStatic) {
        Usuario.idUsuarioStatic = idUsuarioStatic;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Timestamp getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Timestamp dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getAcesso() {
        return acesso;
    }

    public void setAcesso(String acesso) {
        this.acesso = acesso;
    }

    
    
}
