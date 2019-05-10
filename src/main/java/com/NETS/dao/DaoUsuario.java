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

        String query = "INSERT INTO usuario (nome, sobrenome, sexo, funcao, dt_admissao, telefone) VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            Calendar dataCal = Calendar.getInstance();
            dataCal.setTime(usuario.getDtAdmissao());

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSobrenome());
            stmt.setString(3, usuario.getSexo());
            stmt.setString(4, usuario.getFuncao().getNomeFuncao());
            stmt.setDate(5, new java.sql.Date(dataCal.getTimeInMillis()));
            stmt.setString(6, usuario.getTelefone());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException("erro ao cadastrar usuario", ex.getCause());
        }

    }

    public static Usuario obterUsuarioPorId(Long id) throws SQLException {
        Usuario usuario = new Usuario();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");

        String query = "SELECT * FROM usuario WHERE usuario.id = ? AND removido = false";
        Funcao funcao = new Funcao();
        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, id);

            try (ResultSet result = stmt.executeQuery()) {

                while (result.next()) {

                    usuario.setId(result.getLong("id"));
                    usuario.setNome(result.getString("nome"));
                    usuario.setSobrenome(result.getString("sobrenome"));
                    //funcao.setId(result.getLong("id_funcao"));
                    usuario.setSexo(result.getString("sexo"));
                    //Date dtAdmissao = result.getDate("dt_admissao");
                    //usuario.setDtAdmissao(dtAdmissao);
                }
            }
        }
        //usuario.setFuncao(DaoFuncao.obterFuncaoPOrId(funcao.getId()));
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

        String query = "UPDATE usuario SET nome=?, sobre_nome=?, sexo=?, da_admissao, "
                + "funcao=? telefone=? WHERE usuario.id =?";

        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSobrenome());
            stmt.setString(3, usuario.getSexo());
            java.sql.Date data = new java.sql.Date(usuario.getDtAdmissao().getTime());
            stmt.setDate(4, data);
            stmt.setLong(5, usuario.getId());
            stmt.setString(6, usuario.getTelefone());
            stmt.executeUpdate();
        }
    }

    public static List<Usuario> obterUsuarioPorNome(String nome) throws SQLException {

        Connection conn = ConnectionDB.getConnection();
        ResultSet resultados = null;
        List<Usuario> listUsuario = new ArrayList<>();

        String query = "SELECT * FROM usuario WHERE nome = ? ";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nome);
            resultados = stmt.executeQuery();

            Usuario usuario = new Usuario();

            while (resultados.next()) {
                usuario.setId(new Long (resultados.getInt("id")));
                usuario.setNome(resultados.getString("nome"));
                usuario.setSobrenome(resultados.getString("sobrenome"));
                usuario.setSexo(resultados.getString("sexo"));
                usuario.setFuncaoNome(resultados.getString("funcao"));
                usuario.setDtAdmissao(resultados.getDate("dt_admissao"));
                usuario.setTelefone(resultados.getString("telefone"));
                
                listUsuario.add(usuario);

            }
            return listUsuario;
            
        } catch (Exception e) {
            throw new SQLException("erro ao consultar usuario", e.getCause());
        }
    }

    
}
