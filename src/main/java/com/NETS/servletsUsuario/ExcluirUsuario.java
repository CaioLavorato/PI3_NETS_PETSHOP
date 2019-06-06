package com.NETS.servletsUsuario;

import com.NETS.dao.DaoUsuario;
import com.NETS.models.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Long.parseLong;
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
@WebServlet(name = "ExcluirUsuario", urlPatterns = {"/excluirUsuario"})
public class ExcluirUsuario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id_usuario");
        Integer Integer_id = Integer.parseInt(id);
        Usuario usuario = null;
        try {
            usuario = DaoUsuario.obterUsuarioPorId(Integer_id);
        } catch (SQLException ex) {
            Logger.getLogger(ExcluirUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            DaoUsuario.excluirUsuario(usuario);

        } catch (SQLException ex1) {
            Logger.getLogger(ExcluirUsuario.class.getName()).log(Level.SEVERE, null, ex1);
        }

        RequestDispatcher dispatcher
                = request.getRequestDispatcher("./consultarUsuario");
        dispatcher.forward(request, response);
    }
}
