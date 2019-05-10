/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NETS.models;

/**
 *
 * @author Caio Lavorato
 */
public class ItemVenda {    
    private Long id;
    private Integer quantidade;
    private Double valorUnitario;
    private ProdutoFilial produtoFilial;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public ProdutoFilial getProdutoFilial() {
        return produtoFilial;
    }

    public void setProdutoFilial(ProdutoFilial produtoFilial) {
        this.produtoFilial = produtoFilial;
    } 
    
}
