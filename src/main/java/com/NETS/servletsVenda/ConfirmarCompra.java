/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NETS.servletsVenda;

import com.NETS.dao.DaoVenda;
import com.NETS.models.ItemVenda;
import com.NETS.models.Venda;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author caio.lavorato
 */
@WebServlet(name = "ConfirmarCompra", urlPatterns = {"/confirmarCompra"})
public class ConfirmarCompra extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Armazena uma mensagem com o que ocorreu no ultimo evento
        // em uma Session HTTP
        HttpSession session = request.getSession();
        if(request.getParameter("idVenda") == null){
           response.sendRedirect("TelaPrincipal.html");
           return;
        }
        Long idVenda = Long.parseLong(request.getParameter("idVenda"));
        try {
            Venda venda = DaoVenda.obterPorId(idVenda);
            
            Double total = 0.0;
            for(ItemVenda itemVenda: venda.getListaItemVenda()) {
                total = total + itemVenda.getValorUnitario() * itemVenda.getQuantidade();
            }
            
            request.setAttribute("venda", venda);
            request.setAttribute("total", total);
            
            //Present the items for confirmation
            RequestDispatcher dispatcher
                = request.getRequestDispatcher("WEB-INF/jsp/confirmarCompra.jsp");
            dispatcher.forward(request, response);
      
        } catch (SQLException ex) {
            Logger.getLogger(ConfirmarCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
