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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
        String id_livro = request.getParameter("id_produto");
        
        try {
            //checka se o produto existe
            Produto produto = DaoProduto.consultaPorId(Long.parseLong(id_livro));
            
            if (produto != null){
                DaoProduto.excluirProduto(produto);
            }
            else{
                System.out.println("Produto não existe!");
            }
	    response.sendRedirect("WEB-INF/jsp/consultaProduto.jsp\\");
        } catch (SQLException ex) {
            Logger.getLogger(ExcluirProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
