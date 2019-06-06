package com.NETS.dao;

import com.NETS.connect.ConnectionDB;
import com.NETS.models.Cliente;
import com.NETS.models.Endereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author caio.lavorato
 */
public class DaoCliente {

     public static Cliente inserirCliente(Cliente cliente) throws SQLException{
        
        Long id_endereco = DaoEndereco.inserirEndereco(cliente.getEndereco());
        String query = "INSERT INTO cliente (nome, sobrenome, cpf, sexo, dt_nascimento, id_endereco) VALUES (?,?,?,?,?,?)";
        
        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
        
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getSobrenome());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getSexo());
            
            java.sql.Date data = new java.sql.Date(cliente.getDtNascimento().getTime());
            stmt.setDate(5, data);
            stmt.setLong(6, id_endereco);
             
            stmt.executeUpdate();
             
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                cliente.setId(generatedKeys.getLong(1));
            }
        }
        return cliente;
    }
     public static void atualizarCliente(Cliente cliente) throws SQLException{
        
        DaoEndereco.atualizarEndereco(cliente.getEndereco());
        
        String query = "UPDATE cliente SET  id_endereco=?,nome=?, sobrenome=?, cpf=?, sexo=?, dt_nascimento=? WHERE id=?";
        
        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, cliente.getEndereco().getId());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getSobrenome());
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getSexo());
            java.sql.Date data =null;
            if(cliente.getDtNascimento() != null){
                data = new java.sql.Date(cliente.getDtNascimento().getTime());
            
            }
            stmt.setDate(6, data);
            
            stmt.setLong(7, cliente.getId());
             
            stmt.executeUpdate();
             
        }
    }
    
    public static Cliente obterPorId(Long id) throws SQLException{
        Cliente cliente = new Cliente();
        Endereco endereco = new Endereco();
        String query = "SELECT * FROM cliente WHERE id=?";
        
        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setLong(1, id);
            
            try (ResultSet result = stmt.executeQuery()) {
                while(result.next()){
                    cliente.setId(result.getLong("id"));
                    cliente.setNome(result.getString("nome"));
                    cliente.setSobrenome(result.getString("sobrenome"));
                    cliente.setCpf(result.getString("cpf"));
                    cliente.setSexo(result.getString("Sexo"));
                    endereco.setId(result.getInt("id_endereco"));
                    cliente.setEndereco(endereco);
                }
            }
        }
        
        if(cliente.getId() != null){
            cliente.setEndereco(DaoEndereco.obterEndereco(cliente.getEndereco().getId()));
        }
        return cliente;
    }
    
    public static Cliente excluirCliente(Cliente cliente) throws SQLException{
        String query = "UPDATE cliente SET removido=1 WHERE (cliente.id=?)";
        
        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setLong(1, cliente.getId());
            
            stmt.executeUpdate();
        }
        
        return cliente;
    }
    
    public static ArrayList<Cliente> obterListaCliente() throws SQLException{
        ArrayList<Cliente> listaCliente = new ArrayList<>();
        
        String query = "SELECT * FROM cliente WHERE removido = 0";
        
        try (Connection conn = ConnectionDB.getConnection();
               PreparedStatement stmt = conn.prepareStatement(query)) {
            
            try (ResultSet result = stmt.executeQuery()) {
                while(result.next()){
                    
                    Cliente cliente = new Cliente();
                    Endereco endereco = new Endereco();
                    
                    cliente.setId(result.getLong("id"));
                    cliente.setNome(result.getString("nome"));
                    cliente.setSobrenome(result.getString("sobrenome"));
                    cliente.setCpf(result.getString("cpf"));
                    cliente.setSexo(result.getString("Sexo"));
                    endereco.setId(result.getInt("id_endereco"));
                    cliente.setEndereco(endereco);
                    
                    listaCliente.add(cliente);
                }
            }
        }
        
        for(Cliente cliente: listaCliente){
            cliente.setEndereco(DaoEndereco.obterEndereco(cliente.getEndereco().getId()));
        }
        
        return listaCliente;
            
    }
    
    public static ArrayList<Cliente> searchByNameOrCPF(String nome, String cpf) throws SQLException{
        ArrayList<Cliente> listaCliente = new ArrayList<>();
    
        String query = "SELECT * FROM cliente";
        
        
        if (nome != null || cpf != null){
            query = query + " WHERE";
        }
        if (nome != null){
            query = query + " nome=?";
            if (cpf != null){
                query = query +" OR";
            }
        }
        if (cpf != null){
            query = query + " cpf=?";
        }
        
        
        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            
            if (nome != null && cpf != null){
                stmt.setString(1, nome);
                stmt.setString(2, cpf);
            }
            else if(nome != null){
                stmt.setString(1, nome);
            }
            else if (cpf != null){
                stmt.setString(1, cpf);
            }
            
            
            try (ResultSet result = stmt.executeQuery()) {
                while(result.next()){
                    
                    Cliente cliente = new Cliente();
                    Endereco endereco = new Endereco();
                    
                    cliente.setId(result.getLong("id"));
                    cliente.setNome(result.getString("nome"));
                    cliente.setSobrenome(result.getString("sobrenome"));
                    cliente.setCpf(result.getString("cpf"));
                    cliente.setSexo(result.getString("Sexo"));
                    endereco.setId(result.getInt("id_endereco"));
                    cliente.setEndereco(endereco);
                    
                    listaCliente.add(cliente);
                }
            }
        }
        
        return listaCliente;
    }
    
}
