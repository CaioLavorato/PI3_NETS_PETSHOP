/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NETS.servletsEstoque;

import com.NETS.dao.DaoEstoque;
import com.NETS.dao.DaoFilial;
import com.NETS.dao.DaoProduto;
import com.NETS.models.CategoriaProduto;
import com.NETS.models.Filial;
import com.NETS.models.Produto;
import com.NETS.models.ProdutoFilial;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
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
@WebServlet(name = "Estoque", urlPatterns = {"/estoque"})
public class Estoque extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher
        = request.getRequestDispatcher("WEB-INF/jsp/estoque.jsp");
        
        String id = request.getParameter("id");
        Long long_id = Long.parseLong(id);
        
        try {
            ArrayList<ProdutoFilial> listaProdutoFilial = DaoEstoque.obterEstoque(long_id);

            request.setAttribute("listaProdutoFilial", listaProdutoFilial);
            
            Produto produto = new Produto();
            produto = DaoProduto.consultaPorId(long_id);
            request.setAttribute("produto", produto);

            
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Estoque.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ArrayList<String> parameterNames = new ArrayList<String>();
        
        String id = request.getParameter("id");
        Long long_id = Long.parseLong(id);
        
        // Remove the parameter ID from the request
        request.removeAttribute("id");
        
        ArrayList<Produto> listaProduto = null;
        ArrayList<CategoriaProduto> listaCategoriaProduto = new ArrayList<CategoriaProduto>();
        
        Produto produto = new Produto();
        
        try {
            produto = DaoProduto.consultaPorId(long_id);
        } catch (SQLException ex) {
            Logger.getLogger(Estoque.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Enumeration enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String parameterName = (String) enumeration.nextElement();
            if (!"id".equals(parameterName))
                parameterNames.add(parameterName);
        }
        
        ArrayList<ProdutoFilial> listaProdutoFilial = new ArrayList<ProdutoFilial>();
        for (String parameter : parameterNames) {
            
            try {
                ProdutoFilial produtoFilial = new ProdutoFilial();
                produtoFilial.setProduto(produto);
                Filial filial = DaoFilial.consultaPorId(Integer.parseInt(parameter));
                Integer estoque = Integer.parseInt(request.getParameter(parameter));
                produtoFilial.setEstoque(estoque);
                produtoFilial.setFilial(filial);
                
                listaProdutoFilial.add(produtoFilial);
                
            } catch (SQLException ex) {
                Logger.getLogger(Estoque.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        for (ProdutoFilial produtoFilial : listaProdutoFilial){
            try {
                DaoEstoque.atualizar(produtoFilial, produtoFilial.getEstoque());
            } catch (SQLException ex) {
                Logger.getLogger(Estoque.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        response.sendRedirect(request.getContextPath() +
                "/estoque?id=" + Long.toString(long_id));

    }
}
