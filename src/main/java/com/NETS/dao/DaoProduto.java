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
public class DaoProduto {

    public static void inserirProduto(Produto produto) throws SQLException {

        long id_produto = 0;

        String query = "INSERT INTO produto (nomeProduto,valor,descricao,id_categoria,removido) VALUES (?,?,?,?,0)";

        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, produto.getNomeProduto());
            stmt.setDouble(2, produto.getValor());
            stmt.setString(3, produto.getDescricao());
            stmt.setLong(4, produto.getCategoria().getId());

            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                id_produto = (generatedKeys.getInt(1));
            }

            produto.setId(id_produto);
        }
    }

    public static void atualizarProduto(Produto produto) throws SQLException {

        String query = "UPDATE produto SET nomeProduto=?, valor=?, descricao=?, id_categoria=? "
                + " WHERE (id=?)";

        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, produto.getNomeProduto());
            stmt.setDouble(2, produto.getValor());
            stmt.setString(3, produto.getDescricao());
            ;
            stmt.setLong(4, produto.getCategoria().getId());
            stmt.setLong(5, produto.getId());

            stmt.executeUpdate();
        }
    }

    public static Produto excluirProduto(Produto produto) throws SQLException {

        String query = "UPDATE  produto  SET removido=true WHERE (id=?)";

        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, produto.getId());

            stmt.executeUpdate();
        }
        return produto;
    }

    public static Produto consultaPorId(Long produtoIdString) throws SQLException {

        Produto produto = new Produto();
        String query = "SELECT * FROM produto WHERE produto.id=? AND produto.removido = false";

        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, produtoIdString);

            try (ResultSet result = stmt.executeQuery()) {

                result.first();

                produto.setId(result.getLong("id"));
                produto.setNomeProduto(result.getString("nomeProduto"));
                produto.setDescricao(result.getString("descricao"));
                produto.setValor(result.getDouble("valor"));

                CategoriaProduto categoria = DaoCategoriaProduto.obterCategoria(result.getLong("id_categoria"));
                produto.setCategoria(categoria);

            } catch (Exception e) {
                System.out.println("Sem produto com este id" + e.getMessage());
                return null;
            }
        }
        return produto;
    }

    public static ArrayList<Produto> consultarProduto(String nomeProduto) throws SQLException {

        ArrayList<Produto> listaProduto = new ArrayList<>();

        String query = "SELECT * FROM produto WHERE produto.removido = false";
        if (!nomeProduto.isEmpty()) {
            query = "SELECT * FROM produto WHERE produto.nomeProduto LIKE ? AND produto.removido = false";
        }

        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            if (!nomeProduto.isEmpty()) {
                stmt.setString(1, "%" + nomeProduto + "%");
            }

            try (ResultSet result = stmt.executeQuery()) {

                while (result.next()) {
                    Produto produto = new Produto();
                    CategoriaProduto categoria = new CategoriaProduto();
                    produto.setId(result.getLong("id"));

                    produto.setNomeProduto(result.getString("nomeProduto"));
                    produto.setValor(result.getDouble("valor"));
                    produto.setDescricao(result.getString("descricao"));

                    categoria.setId(result.getLong("id_categoria"));
                    produto.setCategoria(categoria);
                    listaProduto.add(produto);

                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        if (!listaProduto.isEmpty()) {
            for (Produto produtoTemp : listaProduto) {
                produtoTemp.setCategoria(DaoCategoriaProduto.obterCategoria(produtoTemp.getCategoria().getId()));
            }
        }
        return listaProduto;
    }

}
