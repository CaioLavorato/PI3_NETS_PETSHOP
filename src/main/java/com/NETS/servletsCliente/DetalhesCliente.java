/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NETS.servletsCliente;

import com.NETS.dao.DaoCliente;
import com.NETS.models.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author caio.lavorato
 */
@WebServlet(name = "DetalhesCliente", urlPatterns = {"/detalhesCliente"})
public class DetalhesCliente extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String id = request.getParameter("id");
        Long long_id = Long.parseLong(id);
        
        try {
            Cliente cliente = DaoCliente.obterPorId(long_id);
            request.setAttribute("cliente", cliente);
            request.setAttribute("endereco", cliente.getEndereco());
        } catch (SQLException ex) {
            Logger.getLogger(DetalhesCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher dispatcher = 
	    request.getRequestDispatcher("WEB-INF/jsp/detalhesCliente.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
