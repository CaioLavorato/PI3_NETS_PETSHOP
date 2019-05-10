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
import java.sql.Statement;

/**
 *
 * @author caio.lavorato
 */
public class DaoEndereco {

   
    // insere um endereco a partir de apenas um endereco
    public static long inserirEndereco(Endereco endereco) throws SQLException{
        
        int id_endereco = 0;
        
        String query = "INSERT INTO endereco (rua, bairro,  estado, cidade, numero, cep) VALUES (?,?,?,?,?,?)";
        
        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, endereco.getRua());
            stmt.setString(2, endereco.getBairro());
            stmt.setString(3, endereco.getEstado());
            stmt.setString(4, endereco.getCidade());
            stmt.setString(5, endereco.getNumero());
            stmt.setString(6, endereco.getCep());
           
            stmt.executeUpdate();
            
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                id_endereco =(generatedKeys.getInt(1));
            }
        }
        return id_endereco;
    }
    
    public static void atualizarEndereco(Endereco endereco) throws SQLException{
        String query = "UPDATE endereco SET rua=?, bairro=?, estado=?, cidade=?, "
                + "numero=?, cep=? WHERE (id=?)";
        
        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, endereco.getRua());
            stmt.setString(2, endereco.getBairro());
            stmt.setString(3, endereco.getEstado());
            stmt.setString(4, endereco.getCidade());
            stmt.setString(5, endereco.getNumero());
            stmt.setString(6, endereco.getCep());
            stmt.setInt(7, endereco.getId());
             
            stmt.executeUpdate();
        }
    }
    
    public static Endereco excluirEndereco(Endereco endereco) throws SQLException{
        String query = "DELETE FROM endereco WHERE (id=?)";
        
        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, endereco.getId());
            
            stmt.executeUpdate();
        }
        return endereco;
    }
    
    public static Endereco obterEndereco(Integer id) throws SQLException{
        Endereco endereco = new Endereco();
        
        String query = "SELECT * FROM endereco WHERE endereco.id = ?";
        
        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet resultados = stmt.executeQuery()) {
                while(resultados.next()){
                    endereco.setId(resultados.getInt("id"));
                    endereco.setBairro(resultados.getString("bairro"));
                    endereco.setCep(resultados.getString("cep"));
                    endereco.setCidade(resultados.getString("cidade"));
                    endereco.setEstado(resultados.getString("estado"));
                    endereco.setNumero(resultados.getString("numero"));
                    endereco.setRua(resultados.getString("rua"));
                    
                }
            }
        
        }
        
        return endereco;
    }

    static long inserirEndereco(Filial filial) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

