/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NETS.servletsProdutos;

import com.NETS.dao.DaoCategoriaProduto;
import com.NETS.dao.DaoProduto;
import com.NETS.models.CategoriaProduto;
import com.NETS.models.Produto;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ConsultarProduto", urlPatterns = {"/consultarProduto"})
public class BuscaProdutos extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ArrayList<CategoriaProduto> listaCategoriaProduto = new ArrayList<CategoriaProduto>();
        
        String nomeProduto = "";
        if (request.getParameter("nomeProduto") != null){
            nomeProduto = request.getParameter("nomeProduto");
        }
        
        String categoria = request.getParameter("categoria");
       
        
        ArrayList<Produto> listaProduto = null;
        try {
            listaProduto = DaoProduto.consultarProduto(getInitParameter(nomeProduto));
            
            listaCategoriaProduto = DaoCategoriaProduto.getCategorias();
            request.setAttribute("categorias", listaCategoriaProduto);
            
            request.setAttribute("listaProduto", listaProduto);
            
        } catch (SQLException ex) {
            Logger.getLogger(BuscaProdutos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        RequestDispatcher dispatcher = 
	    request.getRequestDispatcher("WEB-INF/jsp/consultarProduto.jsp");
        dispatcher.forward(request, response);
    }
}
