/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NETS.dao;

import com.NETS.connect.ConnectionDB;
import com.NETS.models.ItemVenda;
import com.NETS.models.Produto;
import com.NETS.models.ProdutoFilial;
import com.NETS.models.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author caio.lavorato
 */
public class DaoItemVenda {
     public static void inserirItemVenda(Long venda_id, ItemVenda itemVenda) throws SQLException{
        
        String query = "INSERT INTO item_venda (id_venda, id_produto, quantidade, valor_unitario) VALUES (?,?,?,?)";
        
        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setLong(1, venda_id);
            stmt.setLong(2, itemVenda.getProdutoFilial().getProduto().getId());
            stmt.setInt(3, itemVenda.getQuantidade());
            stmt.setDouble(4, itemVenda.getValorUnitario());
            
            stmt.executeUpdate();
            
            }
    }

    static ArrayList<ItemVenda> obterListaPorId(Venda venda) throws SQLException {
        ArrayList<ItemVenda> listaItemVenda = new ArrayList<>();
        
        String query = "SELECT * FROM item_venda WHERE id_venda = ?";
        
        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setLong(1, venda.getId());
            
            try (ResultSet result = stmt.executeQuery()) {
                while(result.next()){
                    ItemVenda itemVenda = new ItemVenda();
                    Produto produto = new Produto();
                    ProdutoFilial produtoFilial = new ProdutoFilial();
                    itemVenda.setProdutoFilial(produtoFilial);
                    
                    itemVenda.setId(result.getLong("item_venda_id"));
                    
                    produto.setId(result.getLong("id_produto"));
                    itemVenda.getProdutoFilial().setProduto(produto);
                    
                    itemVenda.setQuantidade(result.getInt("quantidade"));
                    itemVenda.setValorUnitario(result.getDouble("valor_unitario"));
                    
                    listaItemVenda.add(itemVenda);
                }
            }
            
        }
        
        //preenche todos os itens de venda seus respectivos livros
        for(ItemVenda itemVenda: listaItemVenda){
            itemVenda.getProdutoFilial().setProduto(DaoProduto.consultaPorId(itemVenda.getProdutoFilial().getProduto().getId()));
            itemVenda.setProdutoFilial(DaoEstoque.obterPorId(itemVenda.getProdutoFilial().getProduto().getId(), 
                    venda.getFilial().getId()));
        }
        
        return listaItemVenda;
    }
            
    
    
}
