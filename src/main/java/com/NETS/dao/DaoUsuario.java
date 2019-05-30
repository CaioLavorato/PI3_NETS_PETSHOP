/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NETS.dao;

import com.NETS.connect.ConnectionDB;
import com.NETS.models.Funcao;
import com.NETS.models.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author caio.lavorato
 */
public class DaoUsuario {

    public static void inserirUsuario(Usuario usuario) throws SQLException {

        Connection conn = ConnectionDB.getConnection();

        String query = "INSERT INTO usuario (nome,sobrenome,sexo,idfuncao,dt_admissao,telefone,removido) VALUES (?,?,?,?,?,?,0)";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSobrenome());
            stmt.setString(3, usuario.getSexo());
            stmt.setLong(4, usuario.getFuncao().getId());
            java.sql.Date data = new java.sql.Date(usuario.getDtAdmissao().getTime());
            stmt.setDate(5, data);
            stmt.setString(6, usuario.getTelefone());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException("erro ao cadastrar usuario", ex.getCause());
        }

    }

    public static Usuario obterUsuarioPorId(Integer usuarioIdString) throws SQLException {

        Usuario usuario = new Usuario();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");

        String query = "SELECT * FROM usuario WHERE usuario.id = ? AND removido = false";

        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, usuarioIdString);

            try (ResultSet result = stmt.executeQuery()) {

                result.first();

                usuario.setId(result.getInt("id"));
                usuario.setNome(result.getString("nome"));
                usuario.setSobrenome(result.getString("sobrenome"));
                usuario.setSexo(result.getString("sexo"));
                usuario.setDtAdmissao(result.getDate("dt_admissao"));
                usuario.setTelefone(result.getString("telefone"));

                Funcao funcao = DaoFuncao.obterFuncaoPOrId(result.getLong("idfuncao"));
                usuario.setFuncao(funcao);

            } catch (Exception e) {
                System.out.println("Sem Usuario com este id" + e.getMessage());
                return null;
            }
        }

        return usuario;
    }

    public static void excluirUsuario(Usuario usuario) throws SQLException {

        String query = "UPDATE usuario SET removido=true WHERE usuario.id = ?";

        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, usuario.getId());

            stmt.executeUpdate();
        }
    }

    public static void alterarUsuario(Usuario usuario) throws SQLException {

        String query = "UPDATE usuario SET nome=?, sobrenome=?, sexo=?, idfuncao=?, "
                + "dt_admissao=?, telefone=?  WHERE usuario.id =?";

        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSobrenome());
            stmt.setString(3, usuario.getSexo());
            stmt.setString(4, usuario.getFuncao().getNomeFuncao());
            java.sql.Date data = new java.sql.Date(usuario.getDtAdmissao().getTime());
            stmt.setDate(5, data);
            stmt.setString(6, usuario.getTelefone());
            stmt.executeUpdate();
        }
    }

    public static ArrayList<Usuario> obterUsuarioPorNome(String nome) throws SQLException {

        ArrayList<Usuario> listUsuario = new ArrayList<>();

        String query = "SELECT * FROM usuario WHERE removido = false ";
        if (!nome.isEmpty()) {
            query = "SELECT * FROM usuario WHERE nome LIKE ? AND removido = 0";
        }
        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            if (!nome.isEmpty()) {
                stmt.setString(1, "%" + nome + "%");
            }

            try (ResultSet result = stmt.executeQuery()) {

                while (result.next()) {
                    Usuario usuario = new Usuario();
                    Funcao funcao = new Funcao();

                    usuario.setId(result.getInt("id"));
                    usuario.setNome(result.getString("nome"));
                    usuario.setSobrenome(result.getString("sobrenome"));
                    usuario.setSexo(result.getString("sexo"));
                    usuario.setDtAdmissao(result.getDate("dt_admissao"));
                    usuario.setTelefone(result.getString("telefone"));

                    funcao.setId(result.getLong("idfuncao"));
                    usuario.setFuncao(funcao);
                    listUsuario.add(usuario);
                }
            } catch (Exception e) {
                System.out.println(e.getCause());
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return listUsuario;
    }

}
