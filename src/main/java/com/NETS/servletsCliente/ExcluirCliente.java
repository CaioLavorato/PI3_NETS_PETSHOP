package com.NETS.servletsCliente;

import com.NETS.dao.DaoCliente;
import com.NETS.models.Cliente;
import com.NETS.servletsCliente.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "ExcluirCliente", urlPatterns = {"/excluirCliente"})
public class ExcluirCliente extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        Long Long_id = Long.parseLong(id);
        Cliente cliente = null;
        try {
            cliente = DaoCliente.obterPorId(Long_id);
        } catch (SQLException ex) {
            Logger.getLogger(ExcluirCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            DaoCliente.excluirCliente(cliente);
        } catch (SQLException ex1) {
            Logger.getLogger(ExcluirCliente.class.getName()).log(Level.SEVERE, null, ex1);
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
