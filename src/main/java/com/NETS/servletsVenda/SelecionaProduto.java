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
@WebServlet(name = "SelecionaProduto", urlPatterns = {"/selecionaProduto"})
public class SelecionaProduto extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Armazena uma mensagem com o que ocorreu no ultimo evento
        // em uma Session HTTP
        HttpSession session = request.getSession();
        Cliente clienteVenda = (Cliente) session.getAttribute("clienteVenda");
        
        // TODO: Obter filial pelo usuario na sessão, e não fixo
        Integer id_filial = Integer.parseInt("1");

        
        if (clienteVenda == null){          
            //Sends the user back to the client selection
            RequestDispatcher dispatcher = request.getRequestDispatcher("/selecionaCliente");
            dispatcher.forward(request,response);    
        }
        else{
            
            ArrayList<Produto> listaProduto = new ArrayList<Produto>();

            HashMap<Produto, Integer> shopCart = (HashMap<Produto, Integer>) session.getAttribute("shopCart");
            if (shopCart == null){
                shopCart = new HashMap<Produto, Integer>();
                session.setAttribute("shopCart", shopCart);
            }
                     
            String nomeProduto =(String) request.getParameter("nomeProduto");
            try {
                listaProduto = DaoProduto.consultarProduto(nomeProduto);
            } catch (SQLException ex) {
                Logger.getLogger(SelecionaProduto.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            HashMap<Long, Integer> estoque = new HashMap<Long, Integer>();
            //Obtem o estoque para cada livro
            for (Produto produto: listaProduto){
                try {
                    ProdutoFilial produtoFilial = DaoEstoque.obterPorId(
                            produto.getId(), id_filial);
                    estoque.put(produtoFilial.getProduto().getId(),
                                produtoFilial.getEstoque());
                    
                } catch (SQLException ex) {
                    Logger.getLogger(SelecionaProduto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            Double total = 0.0;
            //Calculate the total
            for(Map.Entry<Produto, Integer> entry : shopCart.entrySet()) {
                Produto key = entry.getKey();
                Integer value = entry.getValue();

                total = total + (key.getValor() * value);
            }
            
            request.setAttribute("total", total);
            request.setAttribute("listaProduto", listaProduto);
            request.setAttribute("estoque", estoque);
            
            RequestDispatcher dispatcher
               = request.getRequestDispatcher("WEB-INF/jsp/produtoVenda.jsp");
            dispatcher.forward(request, response);
            
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* Neste método a venda é realizada */
        
        // Armazena uma mensagem com o que ocorreu no ultimo evento
        // em uma Session HTTP
        HttpSession session = request.getSession();
        Cliente cliente = (Cliente) session.getAttribute("clienteVenda");
        HashMap<Produto, Integer> shopCart = (HashMap<Produto, Integer>) session.getAttribute("shopCart");
        boolean cancelarVenda = false;
        
        
        
        if (cliente == null){
            //Sends the user back to the client selection
            RequestDispatcher dispatcher = request.getRequestDispatcher("/selecionaCliente");
            dispatcher.forward(request,response);    
        }
        else if (shopCart.isEmpty()){
            //TODO Adicionar uma mensagem para o usuario.
            RequestDispatcher dispatcher = request.getRequestDispatcher("/selecionaProduto");
            dispatcher.forward(request,response);
            
        }
        else{
            try {
                Usuario usuario = DaoUsuario.obterUsuarioPorId(Integer.parseInt("1"));
                Filial filial = DaoFilial.consultaPorId(1);
                Venda venda = new Venda();
                
                venda.setCliente(cliente);
                venda.setUsuario(usuario);
                venda.setFilial(filial);

                //Cria uma lista de itens venda para a venda
                ArrayList<ItemVenda> listaItemVenda = new ArrayList<ItemVenda>();
                                
                for(Map.Entry<Produto, Integer> entry : shopCart.entrySet()) {
                    Produto produto = entry.getKey();
                    Integer quantidade = entry.getValue();
                    
                    ProdutoFilial produtoFilial = DaoEstoque.obterPorId(produto.getId(), filial.getId());
                    
                    if (quantidade > produtoFilial.getEstoque()){

                        cancelarVenda = true;
                        break;
                        
                    }
                    
                    ItemVenda itemVenda = new ItemVenda();
                    
                    itemVenda.setQuantidade(quantidade);
                    itemVenda.setValorUnitario(produto.getValor());
                    itemVenda.setProdutoFilial(produtoFilial);
                    
                    listaItemVenda.add(itemVenda);
                }
                
                //Check if we have enough items available to sell
                if (cancelarVenda){
                    String msg = "A quantidade solicitada não "
                                + "está disponível em estoque, atualize e tente"
                                + " novamente";
                    session.setAttribute("msg", msg);
                    
                    response.sendRedirect(request.getContextPath()
                            + "/selecionaProduto");
                }
                else{
                // Finaliza a realização da venda    
                    venda.setListaItemVenda(listaItemVenda);
                    DaoVenda.inserirVenda(venda);
                    
                    //Clean the old attributes not needed
                    session.removeAttribute("shopCart");
                    session.removeAttribute("clienteVenda");
                    session.removeAttribute("msg");
                   
                    response.sendRedirect(request.getContextPath()
                            + "/confirmacaoVenda?idVenda="+venda.getId());
                }

            } catch (SQLException ex) {
                Logger.getLogger(SelecionaProduto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
}
