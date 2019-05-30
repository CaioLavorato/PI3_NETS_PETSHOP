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
import javax.servlet.http.HttpSession;

/**
 *
 * @author caio.lavorato
 */
@WebServlet(name = "CadastrarProduto", urlPatterns = {"/cadastrarProduto"})
public class CadastrarProduto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher
           = request.getRequestDispatcher("WEB-INF/jsp/cadastrarProduto.jsp");
        
        ArrayList<CategoriaProduto> listaCategoria = null;
        try {
            listaCategoria = DaoCategoriaProduto.getCategorias();
            
            request.setAttribute("categorias", listaCategoria);
            
            dispatcher.forward(request, response);
            
        } catch (SQLException ex) {
            Logger.getLogger(DetalhesProduto.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Double valor = null;
        try{
            valor = Double.parseDouble(request.getParameter("valor"));
        } catch(Exception e){      
            e.printStackTrace();
        }
        String nomeProduto = request.getParameter("nomeProduto");
        String descricao = request.getParameter("descricao");
        Long idCategoria = Long.parseLong(request.getParameter("categoria"));
        
        CategoriaProduto categoriaProduto = new CategoriaProduto();
        
        String message = "";
        try {
           categoriaProduto = DaoCategoriaProduto.obterCategoria(idCategoria);
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarProduto.class.getName()).log(Level.SEVERE, null, ex);
            message = "Falha ao localizar categoria selecionada";
        }
        
        Produto produto = new Produto(nomeProduto, valor, descricao, categoriaProduto);
        try {
            DaoProduto.inserirProduto(produto);
            
            message = "Produto cadastrado com sucesso";
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarProduto.class.getName()).log(Level.SEVERE, null, ex);
            message = "Falha ao cadastrar o Produto";
        }
        
        // Armazena uma mensagem com o que ocorreu no ultimo evento
        // em uma Session HTTP
        HttpSession session = request.getSession();
        session.setAttribute("message", message);
        
        response.sendRedirect(request.getContextPath() + "/cadastrarProduto");
    }
}
