/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NETS.models;

import java.util.Date;

/**
 *
 * @author Caio Lavorato
 */
public class Usuario {

    private Integer id;
    private String nomeUsuario;
    private String sobrenomeUsuario;
    private Funcao funcao;
    private String sexo;
    private String telefone;
    private String email;
    private Date dtAdmissao;
    private Long idfuncao;
    private String funcaoNome;

   

    public Usuario(String nomeUsuario, String sobrenomeUsuario,String sexo,
            Funcao funcao,Date dtAdmissao, String telefone) {
        this.nomeUsuario = nomeUsuario;
        this.sobrenomeUsuario = sobrenomeUsuario;
        this.sexo = sexo;
        this.funcao = funcao;
        this.dtAdmissao = dtAdmissao;
        this.telefone = telefone;
    }

    public Usuario() {
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nomeUsuario;
    }

    public void setNome(String nome) {
        this.nomeUsuario = nome;
    }

    public String getSobrenome() {
        return sobrenomeUsuario;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenomeUsuario = sobrenome;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDtAdmissao() {
        return dtAdmissao;
    }

    public void setDtAdmissao(Date dtAdmissao) {
        this.dtAdmissao = dtAdmissao;
    }

    public Long getIdFuncao() {
        return idfuncao;
    }

    public void setIdFuncao(Long idfuncao) {
        this.idfuncao = idfuncao;
    }

    public String getFuncaoNome() {
        return funcaoNome;
    }

    public void setFuncaoNome(String funcaoNome) {
        this.funcaoNome = funcaoNome;
    }

  
    
    
}

    
