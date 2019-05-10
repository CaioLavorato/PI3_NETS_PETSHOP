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
@WebServlet(name = "ExcluiFilial", urlPatterns = {"/excluirFilial"})
public class ExcluiFilial extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Filial filial = null;
        try {
            filial = DaoFilial.consultaPorId(Integer.parseInt(request.getParameter("id_filial")));
        } catch (SQLException ex) {
            Logger.getLogger(ExcluiFilial.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            DaoFilial.excluirFilial(filial);
        } catch (SQLException ex) {
            Logger.getLogger(ExcluiFilial.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         RequestDispatcher dispatcher = 
	    request.getRequestDispatcher("./consultarFilial");
        dispatcher.forward(request, response);
        
    }

}
