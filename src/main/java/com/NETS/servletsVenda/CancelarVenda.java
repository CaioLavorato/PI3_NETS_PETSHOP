/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NETS.servletsVenda;

import com.NETS.dao.DaoEstoque;
import com.NETS.dao.DaoFilial;
import com.NETS.dao.DaoProduto;
import com.NETS.dao.DaoUsuario;
import com.NETS.dao.DaoVenda;
import com.NETS.models.Cliente;
import com.NETS.models.Filial;
import com.NETS.models.ItemVenda;
import com.NETS.models.Produto;
import com.NETS.models.ProdutoFilial;
import com.NETS.models.Usuario;
import com.NETS.models.Venda;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
@WebServlet(name = "CancelarVenda", urlPatterns = {"/cancelarVenda"})
public class CancelarVenda extends HttpServlet {

    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* Neste método a venda é realizada */
        
        // Armazena uma mensagem com o que ocorreu no ultimo evento
        // em uma Session HTTP
        HttpSession session = request.getSession();
        
                    session.removeAttribute("shopCart");
                    session.removeAttribute("clienteVenda");
                    session.removeAttribute("msg");
                   
                    response.sendRedirect(request.getContextPath()
                            + "/");
                }

        
}
