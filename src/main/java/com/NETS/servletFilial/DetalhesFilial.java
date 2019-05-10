/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NETS.servletFilial;

import com.NETS.dao.DaoFilial;
import com.NETS.models.Filial;
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
public class DetalhesFilial {

    @WebServlet(name = "DetalhesFilial", urlPatterns = {"/detalhesFilial"})
    public class DetalheFilial extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            String id = request.getParameter("id_filial");
            Integer int_id = Integer.parseInt(id);

            ArrayList<Filial> listaProduto = null;

            try {
                Filial filial = DaoFilial.consultaPorId(int_id);

                request.setAttribute("filial", filial);
                request.setAttribute("endereco", filial.getEndereco());
            } catch (SQLException ex) {
                Logger.getLogger(DetalheFilial.class.getName()).log(Level.SEVERE, null, ex);
            }

            RequestDispatcher dispatcher
                    = request.getRequestDispatcher("WEB-INF/jsp/detalhesFilial.jsp");
            dispatcher.forward(request, response);

        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
        }

    }
}
