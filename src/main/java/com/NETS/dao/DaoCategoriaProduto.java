/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NETS.dao;

import com.NETS.connect.ConnectionDB;
import com.NETS.models.CategoriaProduto;
import com.NETS.models.Produto;
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
public class DaoCategoriaProduto {

    public static void inserirCategoria(CategoriaProduto categoriaProduto) throws SQLException {

        String query = "INSERT INTO categoria (nome, descricao) VALUES (?,?)";

        long id_categoria = 0;

        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, categoriaProduto.getNome());
            stmt.setString(2, categoriaProduto.getDescricao());

            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                id_categoria = (generatedKeys.getInt(1));
            }
        }
        categoriaProduto.setId(id_categoria);

        //return categoria;
    }

    public static void atualizarCategoria(CategoriaProduto categoriaProduto) throws SQLException {

        String query = " UPDATE categoria SET (nome=?, descricao=?) WHERE (id=?)";

        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, categoriaProduto.getNome());
            stmt.setString(2, categoriaProduto.getDescricao());
            stmt.setLong(3, categoriaProduto.getId());

            stmt.executeUpdate();
        }
    }

    public static CategoriaProduto obterCategoria(Long id) throws SQLException {
        CategoriaProduto categoria = new CategoriaProduto();

        String query = "SELECT * FROM categoria WHERE (id = ?)";

        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, id);

            try (ResultSet resultados = stmt.executeQuery()) {

                while (resultados.next()) {
                    categoria.setId(resultados.getLong("id"));
                    categoria.setNome(resultados.getString("nome"));
                    categoria.setDescricao(resultados.getString("descricao"));
                }
            }
        }
        return categoria;
    }

    public static CategoriaProduto obterCategoria(String nome) throws SQLException {
        CategoriaProduto categoria = new CategoriaProduto();

        String query = "SELECT * FROM categoria WHERE UPPER (nome) = (?)";

        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nome);

            try (ResultSet resultados = stmt.executeQuery()) {

                while (resultados.next()) {
                    categoria.setId(resultados.getLong("id"));
                    categoria.setNome(resultados.getString("nome"));
                    categoria.setDescricao(resultados.getString("descricao"));
                }
            }
        }
        return categoria;
    }

    public static ArrayList<CategoriaProduto> getCategorias() throws SQLException {
        String query = "SELECT * FROM categoria";

        ArrayList<CategoriaProduto> categorias = new ArrayList<>();

        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            try (ResultSet resultados = stmt.executeQuery()) {

                while (resultados.next()) {
                    CategoriaProduto categoria = new CategoriaProduto();
                    categoria.setId(resultados.getLong("id"));
                    categoria.setNome(resultados.getString("nome"));
                    categoria.setDescricao(resultados.getString("descricao"));

                    categorias.add(categoria);
                }
            }
        }
        return categorias;
    }

}
