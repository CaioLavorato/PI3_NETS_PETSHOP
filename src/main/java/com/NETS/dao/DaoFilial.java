/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NETS.dao;

import com.NETS.connect.ConnectionDB;
import com.NETS.models.Endereco;
import com.NETS.models.Filial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Caio Lavorato
 */
public class DaoFilial {
        public static Filial inserirFilial(Filial filial) throws SQLException{
        
        long id_endereco = DaoEndereco.inserirEndereco(filial);
        String query = "INSERT INTO filial (nome_fantasia, cnpj,"
                + "inscricao_estadual, telefone, email, id_endereco) VALUES (?,?,?,?,?,?)";
        
        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, filial.getNomeFantasia());
            stmt.setString(2, filial.getCnpj());
            stmt.setString(3, filial.getInscricaoEstadual());
            stmt.setString(4, filial.getTelefone());
            stmt.setString(5, filial.getEmail());
            stmt.setLong(6, id_endereco);
        }
        return filial;
    }
    
     public static Filial atualizarFilial(Filial filial) throws SQLException{
         
        DaoEndereco.atualizarEndereco(filial.getEndereco());
         
        String query = "UPDATE filial SET nome_fantasia=?, cnpj=?, "
                + "telefone=?, email=? " 
                + " WHERE (id=?)";
        
        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, filial.getNomeFantasia());
            stmt.setString(2, filial.getCnpj());
            stmt.setString(3, filial.getTelefone());           
            stmt.setString(4, filial.getEmail());
            stmt.setInt(5, filial.getId());
            
            stmt.executeUpdate();
        }
        return filial;
    }
     
    public static Filial excluirFilial(Filial filial) throws SQLException{
        String query = "UPDATE  filial  SET removido=true WHERE (id=?)";
        
        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, filial.getId());
            
            stmt.executeUpdate();
        }
        return filial;
    }
    
    public static ArrayList<Filial> consultaFilial(String nomeFantasia) throws SQLException{
        ArrayList<Filial> listaFilial = new ArrayList<>();
        
        String query = "SELECT * FROM filial WHERE filial.removido = false";
        
        if(!nomeFantasia.isEmpty()){
            query = "SELECT * FROM filial WHERE filial.nome_fantasia LIKE ? AND filial.removido = false";
        }
        
         try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            
            if(!nomeFantasia.isEmpty()){
                stmt.setString(1, "%" + nomeFantasia + "%");
            }
             
             
            try (ResultSet resultados = stmt.executeQuery()) {
                
                while(resultados.next()){
                    
                    Filial filial = new Filial();
                    Endereco endereco = new Endereco();
                    filial.setId(resultados.getInt("id"));
                    filial.setCnpj(resultados.getString("cnpj"));
                    filial.setNomeFantasia(resultados.getString("nome_fantasia"));
                    filial.setInscricaoEstadual(resultados.getString("inscricao_estadual"));
                    filial.setEmail(resultados.getString("email"));
                    filial.setTelefone(resultados.getString("telefone"));
                    
                    
                    endereco.setId(resultados.getInt("id_endereco"));
                    filial.setEndereco(endereco);
                    listaFilial.add(filial);
                }
            }
         }
         
         if(!listaFilial.isEmpty()){
            for(Filial filialTemp: listaFilial){
                filialTemp.setEndereco(DaoEndereco.obterEndereco(filialTemp.getEndereco().getId()));
            }
         }
        return listaFilial;
    }
    
    public static Filial consultaPorId(Integer id) throws SQLException{
        
        Filial filial = new Filial();
        
        String query = "SELECT * FROM filial WHERE filial.id = ? AND filial.removido = false";
        
        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setInt(1, id);
                
            try (ResultSet resultados = stmt.executeQuery()) {
                
                resultados.first();
                    
                Endereco endereco = new Endereco();
                filial.setId(resultados.getInt("id"));
                filial.setCnpj(resultados.getString("cnpj"));
                filial.setNomeFantasia(resultados.getString("nome_fantasia"));
                filial.setInscricaoEstadual(resultados.getString("inscricao_estadual"));
                filial.setEmail(resultados.getString("email"));
                filial.setTelefone(resultados.getString("telefone"));
                
                    
                endereco.setId(resultados.getInt("id_endereco"));
                filial.setEndereco(endereco);
                
             }
         }
         if(filial.getEndereco().getId() != null){
             filial.setEndereco(DaoEndereco.obterEndereco(filial.getEndereco().getId()));
         }
         
        return filial;
    }
}
