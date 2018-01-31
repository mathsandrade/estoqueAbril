/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModel;

import Model.Cliente;
import Model.Pedido;
import Model.Produto;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Matheus
 */
public class PedidoViewModel {
    
    public void novo(List<Cliente> clientes, List<Produto> produtos, HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        request.setAttribute("clientes", clientes);
        request.setAttribute("produtos", produtos);
        request.setAttribute("action", "create");
        request.getRequestDispatcher("pedidos/new.jsp").forward(request, response);
    };
    
    public void create(int pedidoId, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.sendRedirect(request.getContextPath() + "/pedidos");
    };
    
    public void list(List<Pedido> pedidos, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        request.setAttribute("pedidos", pedidos);
        request.getRequestDispatcher("pedidos/list.jsp").forward(request, response);
    };
    
    public void edit(List<Cliente> clientes, List<Produto> produtos, Pedido pedido, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        request.setAttribute("action", "update");
        request.setAttribute("clientes", clientes);
        request.setAttribute("produtos", produtos);
        request.setAttribute("pedido", pedido);
        request.getRequestDispatcher("pedidos/edit.jsp").forward(request, response);
    };
    
    public void update(int pedidoId, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.sendRedirect(request.getContextPath() + "/pedidos");
    };
    
    public void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.sendRedirect(request.getContextPath() + "/pedidos");
    };
    
    public Pedido getPedido(HttpServletRequest request){
        Pedido pedido = new Pedido();
        Produto produto = new Produto();
        Cliente cliente = new Cliente();
        
        try {
            int id = Integer.valueOf(request.getParameter("pedido.id"));
            pedido.setId(id);
        } catch (Exception e) {
            pedido.setId(0);
        }
        try {
            int idprod = Integer.valueOf(request.getParameter("produto.id"));
            produto.setId(idprod);
        } catch (Exception e) {
            produto.setId(0);
        }
        try {
            int idcli = Integer.valueOf(request.getParameter("cliente.id"));
            cliente.setId(idcli);
        } catch (Exception e) {
            cliente.setId(0);
        }
        pedido.setProduto(produto);
        pedido.setCliente(cliente);
        
        return pedido;
    };
}
