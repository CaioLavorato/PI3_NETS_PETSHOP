/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NETS.dao;

import com.NETS.connect.ConnectionDB;
import com.NETS.models.Cliente;
import com.NETS.models.Filial;
import com.NETS.models.ItemVenda;
import com.NETS.models.Usuario;
import com.NETS.models.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author caio.lavorato
 */
public class DaoVenda {
        public static Long inserirVenda(Venda venda) throws SQLException{
        
        String query = "INSERT INTO  venda (id_cliente, id_filial, id_usuario, data_venda) VALUES (?,?,?,?)";
        
        try (Connection conn = ConnectionDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
             
            stmt.setLong(1, venda.getCliente().getId());
            stmt.setLong(2, venda.getFilial().getId());
            stmt.setLong(3, venda.getUsuario().getId());
            stmt.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
            
            stmt.executeUpdate();
             
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                venda.setId(generatedKeys.getLong(1));
            }
        }
        
         // executa o dao de insercao de itens de venda passando o id da venda inserida acima
        for(ItemVenda itemVenda: venda.getListaItemVenda()){
            DaoItemVenda.inserirItemVenda(venda.getId(), itemVenda);
            // para cada item da lista decresce o estoque do livro relacionado
            DaoEstoque.atualizaEstoqueProdutoFilial(venda.getFilial().getId(),
                    itemVenda.getQuantidade(), itemVenda.getProdutoFilial().getProduto().getId());
        } 
        
        return venda.getId();
    } 

    // busca todas as vendas de uma filial realizada dentro de uma semana
    public static ArrayList<Venda> obterVendaFilial(Integer filial_id) throws SQLException{
        ArrayList<Venda> listaVenda = new ArrayList<>();
        String query = "SELECT * FROM venda WHERE venda.id_filial = ? AND venda.data_venda >= ?";
        
        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setLong(1, filial_id);
            
            // decresce 1 semana da data da requisicao para filtrar relatorio
            Calendar dataAtual = Calendar.getInstance();
            dataAtual.add(Calendar.DAY_OF_MONTH, -7);
            stmt.setDate(2, new java.sql.Date(dataAtual.getTimeInMillis()));
            
            try (ResultSet result = stmt.executeQuery()) {
                while(result.next()){
                    Venda venda  = new Venda();
                    Cliente cliente = new Cliente();
                    Filial filial = new Filial();
                    Usuario usuario = new Usuario();
                    
                    cliente.setId(result.getLong("id_cliente"));
                    venda.setCliente(cliente);
                    
                    filial.setId(result.getInt("id_filial"));
                    venda.setFilial(filial);
                    
                    usuario.setId(result.getInt("id_usuario"));
                    venda.setUsuario(usuario);
                    
                    venda.setId(result.getLong("id"));
                    
                    java.util.Date dataVenda = result.getDate("data_venda");
                    venda.setDataVenda(dataVenda);
                    
                    listaVenda.add(venda);
                }
            }
            
        }
        
        //preenchendo venda com seus respectivos objetos
        for(Venda venda: listaVenda){
            venda.setCliente(DaoCliente.obterPorId(venda.getCliente().getId()));
            venda.setFilial(DaoFilial.consultaPorId(venda.getFilial().getId()));
            venda.setUsuario(DaoUsuario.obterUsuarioPorId(venda.getUsuario().getId()));
            
            venda.setListaItemVenda(DaoItemVenda.obterListaPorId(venda));
        }
        
        return listaVenda;
    }
    
     public static Venda obterPorId(Long id) throws SQLException{
        
         Venda venda = new Venda();
        
        String query = "SELECT * FROM venda WHERE venda.id = ?";
        
        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setLong(1, id);
            
            
            try (ResultSet result = stmt.executeQuery()) {
                while(result.next()){
                    Cliente cliente = new Cliente();
                    Filial filial = new Filial();
                    Usuario usuario = new Usuario();
                    
                    cliente.setId(result.getLong("id_cliente"));
                    venda.setCliente(cliente);
                    
                    filial.setId(result.getInt("id_filial"));
                    venda.setFilial(filial);
                    
                    usuario.setId(result.getInt("id_usuario"));
                    venda.setUsuario(usuario);
                    
                    venda.setId(result.getLong("id"));
                    
                    java.util.Date dataVenda = result.getDate("data_venda");
                    venda.setDataVenda(dataVenda);
                    
                }
            }
            
        }
        
        //preenchendo venda com seus respectivos objetos
        venda.setCliente(DaoCliente.obterPorId(venda.getCliente().getId()));
        venda.setFilial(DaoFilial.consultaPorId(venda.getFilial().getId()));
        venda.setUsuario(DaoUsuario.obterUsuarioPorId(venda.getUsuario().getId()));
        
        venda.setListaItemVenda(DaoItemVenda.obterListaPorId(venda));

        return venda;
    }
    
}
