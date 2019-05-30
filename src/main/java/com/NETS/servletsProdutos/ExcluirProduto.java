/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NETS.servletsProdutos;

import com.NETS.dao.DaoProduto;
import com.NETS.models.Produto;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Long.parseLong;
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
@WebServlet(name = "ExcluirProduto", urlPatterns = {"/excluirProduto"})
public class ExcluirProduto extends HttpServlet {

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Produto produto = null;
        try {
            produto = DaoProduto.consultaPorId(parseLong(request.getParameter("id_produto")));
        } catch (SQLException ex) {
            Logger.getLogger(ExcluirProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            DaoProduto.excluirProduto(produto);
        } catch (SQLException ex) {
            Logger.getLogger(ExcluirProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         RequestDispatcher dispatcher = 
	    request.getRequestDispatcher("./consultarProduto");
        dispatcher.forward(request, response);
        
    }

}
