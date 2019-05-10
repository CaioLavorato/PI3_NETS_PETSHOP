/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NETS.servletFilial;

import com.NETS.dao.DaoFilial;
import com.NETS.models.Endereco;
import com.NETS.models.Filial;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Caio Lavorato
 */

    @WebServlet(name = "CadastrarFilial", urlPatterns = {"/cadastrarFilial"})
public class CadastrarFilial extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher
           = request.getRequestDispatcher("WEB-INF/jsp/cadastrarFilial.jsp");
        dispatcher.forward(request, response);
        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Endereco endereco = new Endereco();
        
        endereco.setRua(request.getParameter("rua"));
        endereco.setBairro(request.getParameter("bairro"));
        endereco.setEstado(request.getParameter("estado"));
        endereco.setCidade(request.getParameter("cidade"));
        endereco.setNumero(request.getParameter("numero"));
        endereco.setCep(request.getParameter("cep"));


        String nomeFantasia = request.getParameter("nomeFantasia");
        String cnpj = request.getParameter("cnpj");
        String inscricaoEstadual = request.getParameter("inscricaoEstadual");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");


        Filial filial = new Filial(endereco ,nomeFantasia, cnpj, inscricaoEstadual, telefone, email);
        
        try {
            DaoFilial.inserirFilial(filial);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        RequestDispatcher dispatcher
           = request.getRequestDispatcher("./consultarFilial");
        dispatcher.forward(request, response);
    }
    
}
