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
@WebServlet(name = "AtualizaProduto", urlPatterns = {"/atualizaProduto"})
public class AtualizarProduto extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String message = "";
        
        String id = request.getParameter("id_produto");
        Long long_id = Long.parseLong(id);
        
        try {
            double valor = Double.parseDouble(request.getParameter("valor"));
            String nomeProduto = request.getParameter("nomeProduto");
            String descricao = request.getParameter("descricao");
            // implementar modo de insercao de categorias;
            Long idCategoria = Long.parseLong(request.getParameter("categoria"));
        
            CategoriaProduto categoria = new CategoriaProduto();
        
            categoria = DaoCategoriaProduto.obterCategoria(idCategoria);
        
            Produto produto = new Produto(nomeProduto, valor, descricao, categoria);
            
            produto.setId(long_id);
          
            DaoProduto.atualizarProduto(produto);
            
            message = "Produto atualizado com sucesso";
     
        } catch (Exception ex) {
            Logger.getLogger(DetalhesProduto.class.getName()).log(Level.SEVERE,
                                                               null, ex);
            message = "Falha ao atualizar informações do produto";
        }
        
        // Armazena uma mensagem com o que ocorreu no ultimo evento
        // em uma Session HTTP
        HttpSession session = request.getSession();
        session.setAttribute("message", message);
        
        // Redireciona para a mesma página de detalhes do livro com os
        // dados atualizados
        response.sendRedirect(request.getContextPath() +
                "/detalheProduto?id=" + Long.toString(long_id));
    }
}
