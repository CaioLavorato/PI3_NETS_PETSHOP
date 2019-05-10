/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NETS.dao;

import com.NETS.connect.ConnectionDB;
import com.NETS.models.Funcao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Caio Lavorato
 */
public class DaoFuncao {
    
    public static Long cadastrarFuncao(Funcao funcao) throws SQLException {

        String query = "INSERT INTO  funcao (nomeFuncao) VALUES (?)";
        Long id = 0L;

        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, funcao.getNomeFuncao());

            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = (generatedKeys.getLong(1));
            }
        }

        return id;
    }

    public static ArrayList<Funcao> obterListaFuncao() throws SQLException {

        String query = "SELECT * FROM  funcao";
        ArrayList<Funcao> listaFuncao = new ArrayList<>();

        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            try (ResultSet resultados = stmt.executeQuery()) {

                while (resultados.next()) {
                    Funcao funcao = new Funcao();
                    funcao.setId(resultados.getLong("idFuncao"));
                    funcao.setNomeFuncao(resultados.getString("nomeFuncao"));

                    listaFuncao.add(funcao);
                }
            }
        }

        return listaFuncao;
    }

    public static Funcao obterFuncaoPOrId(Long id) throws SQLException {

        String query = "SELECT * FROM funcao WHERE funcao.id =?";
        Funcao funcao = new Funcao();
        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, funcao.getId());
            try (ResultSet resultados = stmt.executeQuery()) {

                while (resultados.next()) {
                    funcao.setId(resultados.getLong("id"));
                    funcao.setNomeFuncao(resultados.getString("nome_funcao"));
                }
            }
        }
        return funcao;
    }

    public static Funcao obterFuncaoPorNome(String nomeFuncao) throws SQLException {
        Connection conn = ConnectionDB.getConnection();
        ResultSet resultados = null;

        String query = "SELECT * FROM funcao WHERE funcao.nomeFuncao = ?";
        Funcao funcao = new Funcao();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nomeFuncao);
            resultados = stmt.executeQuery();

            while (resultados.next()) {
                funcao.setNomeFuncao(resultados.getString("nomeFuncao"));
                Long idfuncao = new Long(resultados.getInt("idFuncao"));
                funcao.setId(idfuncao);
            }

            return funcao;
            
        } catch (SQLException e) {
            throw new SQLDataException("Erro ao obter funcao por nome", e.getCause());
        }

    }
    
}
