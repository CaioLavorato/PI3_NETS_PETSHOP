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
public class Produto {

    private CategoriaProduto categoria;
    private long id;
    private double valor;
    private String nomeProduto;
    private String descricao;

    public Produto(String nomeProduto, Double valor, String descricao,
            CategoriaProduto categoria) {
        this.valor = valor;
        this.nomeProduto = nomeProduto;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    public void setCategoria(CategoriaProduto categoria) {
        this.categoria = categoria;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public CategoriaProduto getCategoria() {
        return this.categoria;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Produto() {

    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getNomeProduto() {
        return this.nomeProduto;
    }
}
