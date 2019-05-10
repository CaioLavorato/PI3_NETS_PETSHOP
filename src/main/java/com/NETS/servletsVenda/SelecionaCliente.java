/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NETS.servletsVenda;

import com.NETS.dao.DaoCliente;
import com.NETS.models.Cliente;
import com.NETS.servletsCliente.ConsultaCliente;
import com.NETS.servletsCliente.DetalhesCliente;
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
@WebServlet(name = "SelecionaCliente", urlPatterns = {"/selecionaCliente"})
public class SelecionaCliente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Caso o cliente seja selecionado, adicionar o id do cliente na Sessão
        // e ir para a página de selecionar produtos
        String IdClienteVenda = request.getParameter("idclientevenda");
        if (IdClienteVenda != null && !"".equals(IdClienteVenda)){
            //Verifica se o Cliente existe
            Long long_id = Long.parseLong(IdClienteVenda);
        
            try {
                Cliente cliente = DaoCliente.obterPorId(long_id);
                if (cliente.getId() != null){

                    // Armazena uma mensagem com o que ocorreu no ultimo evento
                    // em uma Session HTTP
                    HttpSession session = request.getSession();
                    session.setAttribute("clienteVenda", cliente);

                    response.sendRedirect(request.getContextPath() + "/selecionaCliente");

                }

            } catch (SQLException ex) {
                Logger.getLogger(DetalhesCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            
            ArrayList<Cliente> listaCliente = new ArrayList<>();
        
            String nome = null;
            if (request.getParameter("nome") != null && !"".equals(
                    request.getParameter("nome"))){
                nome = request.getParameter("nome");
            }

            String cpf = null;
            if (request.getParameter("cpf") != null && !"".equals(
                    request.getParameter("cpf"))){
                cpf = request.getParameter("cpf");
            }

            try {
                listaCliente = DaoCliente.searchByNameOrCPF(nome, cpf);
                request.setAttribute("listaCliente", listaCliente);
            } catch (SQLException ex) {
                Logger.getLogger(ConsultaCliente.class.getName()).log(
                        Level.SEVERE, null, ex);
            }

            RequestDispatcher dispatcher
               = request.getRequestDispatcher("WEB-INF/jsp/clienteVenda.jsp");
            dispatcher.forward(request, response);
            
        }
    }
}
