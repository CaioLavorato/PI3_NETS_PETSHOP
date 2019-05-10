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
@WebServlet(name = "DetalhesProduto", urlPatterns = {"/detalhesProduto"})
public class DetalhesProduto extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String id = request.getParameter("id");
        Long long_id = Long.parseLong(id);
        
        
        ArrayList<Produto> listaProduto = null;
        ArrayList<CategoriaProduto> listaCategoriaProduto = new ArrayList<CategoriaProduto>();
        
        try {
            Produto livro = DaoProduto.consultaPorId(long_id);
            
            request.setAttribute("livro", livro);
            
            listaCategoriaProduto = DaoCategoriaProduto.getCategorias();
            request.setAttribute("categorias", listaCategoriaProduto);
            

            
        } catch (SQLException ex) {
            Logger.getLogger(DetalhesProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        RequestDispatcher dispatcher = 
	    request.getRequestDispatcher("WEB-INF/jsp/detalhesProduto.jsp");
        dispatcher.forward(request, response);
    }
}
