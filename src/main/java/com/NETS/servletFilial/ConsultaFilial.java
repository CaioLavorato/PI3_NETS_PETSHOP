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
@WebServlet(name = "ConsultarFilial", urlPatterns = {"/consultarFilial"})
public class ConsultaFilial extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        ArrayList<Filial> listaFilial = new ArrayList<Filial>();
        try {
            listaFilial = DaoFilial.consultaFilial("");
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaFilial.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("listaFilial", listaFilial);
        RequestDispatcher dispatcher = 
	    request.getRequestDispatcher("WEB-INF/jsp/consultarFilial.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String nomeFantasia = "";
        if(request.getParameter("nomeFantasia") != null){
            nomeFantasia = request.getParameter("nomeFantasia");
        }
        String cnpj = request.getParameter("cnpj");
        
        ArrayList<Filial> listaFilial = new ArrayList<Filial>();
        try {
            listaFilial = DaoFilial.consultaFilial(nomeFantasia);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaFilial.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("listaFilial", listaFilial);
        RequestDispatcher dispatcher = 
	    request.getRequestDispatcher("WEB-INF/jsp/consultarFilial.jsp");
        dispatcher.forward(request, response);
     
    }
}
    

