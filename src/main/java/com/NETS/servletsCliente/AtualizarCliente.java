/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NETS.servletsCliente;

import com.NETS.servletsCliente.*;
import com.NETS.dao.DaoFuncao;
import com.NETS.dao.DaoCliente;
import com.NETS.dao.DaoEndereco;
import com.NETS.models.Funcao;
import com.NETS.models.Cliente;
import com.NETS.models.Endereco;
import com.NETS.servletsUsuario.Auxiliar;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "AtualizarCliente", urlPatterns = {"/atualizarCliente"})
public class AtualizarCliente extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String message = "";

        String id = request.getParameter("id");
        String idEndereco = request.getParameter("idEndereco");
        Long Long_id = Long.parseLong(id);
        Integer LongIdEndereco = Integer.parseInt(idEndereco);

        try {

            String nomeCliente = request.getParameter("nome");
            String sobrenome = request.getParameter("sobrenome");
            String sexo = request.getParameter("sexo");
            String cpf = request.getParameter("cpf");

            String rua = request.getParameter("rua");
            String numero = request.getParameter("numero");
            String bairro = request.getParameter("bairro");
            String cidade = request.getParameter("cidade");
            String cep = request.getParameter("cep");
            String estado = request.getParameter("estado");

            Date data = null;
            try {
                data = Auxiliar.InputDateToUtilDate(request.getParameter("dtNascimento"));

            } catch (Exception e) {
                e.printStackTrace();
            }

            Endereco endereco = new Endereco(LongIdEndereco, rua, bairro, estado, cidade, numero, cep);
            Cliente cliente = new Cliente(Long_id, nomeCliente, sobrenome, cpf, sexo, data, endereco);

            cliente.setId(Long_id);

            DaoCliente.atualizarCliente(cliente);

            DaoEndereco.atualizarEndereco(endereco);
            message = "Cliente atualizado com sucesso";

        } catch (Exception ex) {
            Logger.getLogger(DetalhesCliente.class.getName()).log(Level.SEVERE,
                    null, ex);
            message = "Falha ao atualizar informações do Cliente";
        }

        try {
            request.setAttribute("listaCliente", DaoCliente.obterListaCliente());
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        RequestDispatcher dispatcher
                = request.getRequestDispatcher("WEB-INF/jsp/consultarCliente.jsp");
        dispatcher.forward(request, response);
    }
}
