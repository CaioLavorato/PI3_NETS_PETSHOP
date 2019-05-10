/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NETS.servletsVenda;

import com.NETS.dao.DaoProduto;
import com.NETS.models.Produto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "RemoveDoCarrinho", urlPatterns = {"/removeDoCarrinho"})
public class RemoveDoCarrinho extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Armazena uma mensagem com o que ocorreu no ultimo evento
        // em uma Session HTTP
        HttpSession session = request.getSession();
        
        //get the shop cart
        HashMap<Produto, Integer> shopCart = (HashMap<Produto, Integer>) session.getAttribute("shopCart");
        
        
        String produtoIdString = request.getParameter("selectedItemId");
        Long produtoIdLong = Long.parseLong(produtoIdString);

        try {
            Produto produto = DaoProduto.consultaPorId(produtoIdLong);
            shopCart.remove(produto);
            
            session.setAttribute("shopCart", shopCart);   
        } catch (SQLException ex) {
            Logger.getLogger(AdicionarAoCarrinho.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.sendRedirect(request.getContextPath() + "/selecionarProduto");
    }
}
