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
public class Endereco {
    private Integer id;
    private String rua;
    private String bairro;
    private String estado;
    private String cidade;
    private String numero;
    private String cep; 

    public Endereco() {
    }

    public Endereco(Integer id, String rua, String bairro, String estado, String cidade, String numero, String cep) {
        this.id = id;
        this.rua = rua;
        this.bairro = bairro;
        this.estado = estado;
        this.cidade = cidade;
        this.numero = numero;
        this.cep = cep;
    }

    
    
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    public Integer getId() {
        return id;
    }
    
    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    
    
}
