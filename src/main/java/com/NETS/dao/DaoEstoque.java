/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NETS.dao;

import com.NETS.connect.ConnectionDB;
import com.NETS.models.Filial;
import com.NETS.models.Produto;
import com.NETS.models.ProdutoFilial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author caio.lavorato
 */
public class DaoEstoque {
    public static void atualizar( ProdutoFilial produtoFilial, int estoque) throws SQLException{
        
        String query = "UPDATE produto_filial SET estoque = ? WHERE produto_filial.id_produto=? AND produto_filial.id_filial=?";
        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setLong(1, estoque);
            stmt.setLong(2, produtoFilial.getProduto().getId());
            stmt.setLong(3, produtoFilial.getFilial().getId());
            
            stmt.executeUpdate();
        }
    }
    
    public static ArrayList<ProdutoFilial> obterEstoque(long id_produto) throws SQLException{
        
        String query = "SELECT * FROM produto_filial INNER JOIN filial "
                    + " ON produto_filial.id_filial = filial.id"
                    + " AND produto_filial.id_produto = ?"
                    + " AND filial.removido = false";
                
        ArrayList<ProdutoFilial> listaProdutoFilial = new ArrayList<ProdutoFilial>();
        
        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
                
            stmt.setLong(1, id_produto);
                
            try (ResultSet result = stmt.executeQuery()) {
                
                while (result.next()){
                    ProdutoFilial produtoFilial = new ProdutoFilial();
                    Filial filial = new Filial();
                    Produto produto = new Produto();
                    
                    produto.setId(result.getLong("id_produto"));
                    filial.setId(result.getInt("id_filial"));
                    produtoFilial.setEstoque(result.getInt("estoque"));
                    
                    produtoFilial.setProduto(produto);
                    produtoFilial.setFilial(filial);
                    
                    listaProdutoFilial.add(produtoFilial);
                }
            }
            catch (Exception e){
                System.out.println(e);
            }
          
            }
        
        for(ProdutoFilial tmp: listaProdutoFilial){
            tmp.setFilial(DaoFilial.consultaPorId(tmp.getFilial().getId()));
            tmp.setProduto(DaoProduto.consultaPorId(tmp.getProduto().getId()));
        }
        
        return listaProdutoFilial;
    }
    
    //atualiza o estoque do Produto-filial
    public static void atualizaEstoqueProdutoFilial(Integer filial_id,Integer quantidade, Long produto_id) throws SQLException{
        
        String query = "UPDATE produto_filial "
                    + "SET estoque = ((produto_filial.estoque) - (?)) "
                    + "WHERE produto_filial.id_filial = ? "
                    + "AND produto_filial.id_produto = ?";
        
        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, quantidade);
            stmt.setLong(2, filial_id);
            stmt.setLong(3, produto_id);
            stmt.executeUpdate();
        }
    }
    
    public static ProdutoFilial obterPorId(Long produto_id, Integer filial_id) throws SQLException{
        ProdutoFilial produtoFilial = new ProdutoFilial();
        
        String query = "SELECT * FROM produto_filial "
                    +  "WHERE produto_filial.id_produto = ? "
                    +  "AND produto_filial.id_filial = ?";
        
                    
        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setLong(1, produto_id);
            stmt.setLong(2, filial_id);
            
            try (ResultSet result = stmt.executeQuery()) {
                
                while (result.next()){
                    Filial filial = new Filial();
                    Produto produto = new Produto();
                    
                    produto.setId(result.getLong("id_produto"));
                    filial.setId(result.getInt("id_filial"));
                    produtoFilial.setEstoque(result.getInt("estoque"));
                    
                    produtoFilial.setProduto(produto);
                    produtoFilial.setFilial(filial);
                }
            }
            produtoFilial.setFilial(DaoFilial.consultaPorId(produtoFilial.getFilial().getId()));
            produtoFilial.setProduto(DaoProduto.consultaPorId(produtoFilial.getProduto().getId()));

            return produtoFilial;
        }
    }
    
}


