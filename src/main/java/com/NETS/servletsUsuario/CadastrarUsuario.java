/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NETS.servletsUsuario;

import com.NETS.dao.DaoFuncao;
import com.NETS.dao.DaoUsuario;
import com.NETS.models.Funcao;
import com.NETS.models.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
@WebServlet(name = "CadastrarUsuario", urlPatterns = {"/cadastrarUsuario"})
public class CadastrarUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession sessao = request.getSession();
            ArrayList<Funcao> listaFuncao = DaoFuncao.obterListaFuncao();
            request.setAttribute("listaFuncao2", listaFuncao);
            sessao.setAttribute("listaFuncao", listaFuncao);

        } catch (SQLException ex) {
            Logger.getLogger(CadastrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("WEB-INF/jsp/cadastrarUsuario.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

         Date data=null;
        try {
           data = Auxiliar.InputDateToUtilDate(request.getParameter("dt_admissao"));
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        String nomeUsuario = request.getParameter("nome");
        String sexo = request.getParameter("sexo");
        String telefone = request.getParameter("telefone");
        String sobrenomeUsuario = request.getParameter("sobrenome");
        String Funcao = request.getParameter("funcao");

        Funcao funcao = new Funcao();

        String message = "";

        try {
            funcao = DaoFuncao.obterFuncaoPorNome(Funcao);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        Usuario usuario = new Usuario(nomeUsuario, sobrenomeUsuario, sexo, funcao, data, telefone);
        try {
            DaoUsuario.inserirUsuario(usuario);
        } catch (SQLException ex) {
             Logger.getLogger(CadastrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
            message = "Falha ao cadastrar o Usuario";
        }
        
        
        HttpSession session = request.getSession();
        session.setAttribute("message", message);
        
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("WEB-INF/jsp/cadastrarUsuario.jsp");
        dispatcher.forward(request, response);
    }
}
