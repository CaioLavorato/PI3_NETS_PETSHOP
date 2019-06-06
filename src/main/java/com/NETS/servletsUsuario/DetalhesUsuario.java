/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NETS.servletsUsuario;

import com.NETS.dao.DaoFuncao;
import com.NETS.dao.DaoUsuario;
import com.NETS.models.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Caio Lavorato
 */
@WebServlet(name = "DetalhesUsuario", urlPatterns = {"/detalhesUsuario"})
public class DetalhesUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id_usuario");
        Integer int_id = Integer.parseInt(id);

        ArrayList<Usuario> listUsuario = null;

        try {
            Usuario usuario = DaoUsuario.obterUsuarioPorId(int_id);
            request.setAttribute("listaFuncao", DaoFuncao.obterListaFuncao());
            request.setAttribute("usuario", usuario);
            request.setAttribute("funcao", usuario.getFuncao());

        } catch (SQLException ex) {
            Logger.getLogger(DetalhesUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        RequestDispatcher dispatcher
                = request.getRequestDispatcher("WEB-INF/jsp/detalhesUsuario.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
