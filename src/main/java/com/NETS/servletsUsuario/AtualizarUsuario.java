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
import java.util.Date;
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
@WebServlet(name = "AtualizarUsuario", urlPatterns = {"/atualizarUsuario"})
public class AtualizarUsuario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String message = "";
        
        String id = request.getParameter("id_usuario");
        Integer Integer_id = Integer.parseInt(id);
        
         Date data=null;
        try {
           data = Auxiliar.InputDateToUtilDate(request.getParameter("dt_admissao"));
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            
            String nomeUsuario = request.getParameter("nome");
            String sobrenome = request.getParameter("sobrenome");
            String sexo = request.getParameter("sexo");
            String telefone = request.getParameter("telefone");
            
            // implementar modo de insercao de funcao;
            Long idFuncao = Long.parseLong(request.getParameter("funcao"));
            
            
        
            Funcao funcao = new Funcao();
        
            funcao = DaoFuncao.obterFuncaoPOrId(idFuncao);
        
            Usuario usuario = new Usuario(nomeUsuario,sobrenome,sexo,funcao,data,telefone);
            
            usuario.setId(Integer_id);
          
            DaoUsuario.alterarUsuario(usuario);
            
            message = "Usuario atualizado com sucesso";
     
        } catch (Exception ex) {
            Logger.getLogger(DetalhesUsuario.class.getName()).log(Level.SEVERE,
                                                               null, ex);
            message = "Falha ao atualizar informações do Usuario";
        }
        
                RequestDispatcher dispatcher
                = request.getRequestDispatcher("./consultarUsuario");
        dispatcher.forward(request, response);
    }
}
