/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NETS.servletsUsuario;

import com.NETS.dao.DaoUsuario;
import com.NETS.models.Usuario;
import com.NETS.servletFilial.ConsultaFilial;
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
 * @author caio.lavorato
 */
@WebServlet(name = "ConsultarUsuario", urlPatterns = {"/consultarUsuario"})
public class ConsultarUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<Usuario> listUsuario = new ArrayList<Usuario>();
        try {
            listUsuario = DaoUsuario.obterUsuarioPorNome("");
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaFilial.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("listUsuario", listUsuario);
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("WEB-INF/jsp/consultarUsuario.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = "";
        String sobrenome = "";
        if (request.getParameter("nome") != null) {
            nome = request.getParameter("nome");
        }
        sobrenome = request.getParameter("sobrenome");

        ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
        try {
            listaUsuario = DaoUsuario.obterUsuarioPorNome(nome);

        } catch (SQLException ex) {
            Logger.getLogger(ConsultaFilial.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("listUsuario", listaUsuario);
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("WEB-INF/jsp/consultarUsuario.jsp");
        dispatcher.forward(request, response);

    }
}
