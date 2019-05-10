/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NETS.models;

/**
 *
 * @author caio.lavorato
 */
public class CategoriaProduto {
    
    private long id; 
    private String nome;
    private String descricao;

    public void setId(long id) {
        this.id = id;
    }
    

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    
}

