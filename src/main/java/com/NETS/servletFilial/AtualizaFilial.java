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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Caio Lavorato
 */

    @WebServlet(name = "AtualizaFilial", urlPatterns = {"/atualizarFilial"})
public class AtualizaFilial extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Endereco endereco = new Endereco();
        
        endereco.setId(Integer.parseInt(request.getParameter("id_endereco")));
        endereco.setRua(request.getParameter("rua"));
        endereco.setBairro(request.getParameter("bairro"));
        endereco.setEstado(request.getParameter("estado"));
        endereco.setCidade(request.getParameter("cidade"));
        endereco.setNumero(request.getParameter("numero"));
        endereco.setCep(request.getParameter("cep"));

        Filial filial = new Filial();
        filial.setId(Integer.parseInt(request.getParameter("id_filial")));
        filial.setNomeFantasia(request.getParameter("nomeFantasia"));

        filial.setCnpj(request.getParameter("cnpj"));
        filial.setTelefone(request.getParameter("telefone"));
        filial.setEmail(request.getParameter("email"));
        filial.setEndereco(endereco);
        try {
            DaoFilial.atualizarFilial(filial);
            request.setAttribute("filial", filial);
            request.setAttribute("endereco", endereco);
            response.sendRedirect(request.getContextPath() +
               "/detalhesFilial?id_filial=" + Long.toString(filial.getId()));
        } catch (SQLException ex) {
            Logger.getLogger(AtualizaFilial.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
