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
@WebServlet(name = "AdicionaAoCarrinho", urlPatterns = {"/adicionarAoCarrinho"})
public class AdicionarAoCarrinho extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Armazena uma mensagem com o que ocorreu no ultimo evento
        // em uma Session HTTP
        HttpSession session = request.getSession();
        
        //get the shop cart
        HashMap<Produto, Integer> shopCart = (HashMap<Produto, Integer>) session.getAttribute("shopCart");
        
        
        String produtoIdString = request.getParameter("selectedProdutoId");
        
        if (produtoIdString == null || "".equals(produtoIdString)){
            String msg = "Selecione um item para adicionar ao carrinho com uma"
                    + " quantidade valida";
            
            session.setAttribute("msg", msg);
        }
        else{
            Integer quantidade = Integer.parseInt(request.getParameter(produtoIdString));
            Long produtoIdLong = Long.parseLong(produtoIdString); 
            
            if (quantidade > 0){
                try {
                    Produto produto = DaoProduto.consultaPorId(produtoIdLong);
                    //Check if this books is already on the shop cart
                    Integer quantidadeNoCarrinho = shopCart.get(produto);
                    if (quantidadeNoCarrinho == null){
                        //Adiciona o item pela primeira vez no carrinho com a quantidade
                        //selecionada
                        shopCart.put(produto, quantidade);
                    }
                    else{
                        //Somente adiciona mais quantidade ao item selecionado
                        shopCart.put(produto, shopCart.get(produto) + quantidade);  
                    }
                    session.setAttribute("shopCart", shopCart);   

                    String msg = "Item adicionado ao carrinho";

                    session.setAttribute("msg", msg);
                } 
                catch (SQLException ex) {
                    Logger.getLogger(AdicionarAoCarrinho.class.getName()).log(Level.SEVERE, null, ex);
                }             
            }
            else{
                String msg = "A quantidade selecionada deve ser maior que 0";
            
                session.setAttribute("msg", msg);
            }
        }
        response.sendRedirect(request.getContextPath() + "/selecionarProduto");
    }
}
