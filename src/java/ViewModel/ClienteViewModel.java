/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModel;

import Model.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Matheus
 */
public class ClienteViewModel {
    
    public void novo(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        request.setAttribute("action", "create");
        request.getRequestDispatcher("clientes/new.jsp").forward(request, response);
    };
    
    public void create(int clienteId, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.sendRedirect(request.getContextPath() + "/clientes");
    };
    
    public void list(List<Cliente> clientes, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        request.setAttribute("clientes", clientes);
        request.getRequestDispatcher("clientes/list.jsp").forward(request, response);
    };
    
    public void edit(Cliente cliente, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        request.setAttribute("action", "update");
        request.setAttribute("cliente", cliente);
        request.getRequestDispatcher("clientes/edit.jsp").forward(request, response);
    };
    
    public void update(int clienteId, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.sendRedirect(request.getContextPath() + "/clientes");
    };
    
    public void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.sendRedirect(request.getContextPath() + "/clientes");
    };
    
    public void validarEmail(Cliente cliente, String email, HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException{                                               
        request.setAttribute("mensagem", email);                                             
        request.setAttribute("cliente", cliente);
        request.setAttribute("action", "create");
        request.getRequestDispatcher("clientes/new.jsp").forward(request, response);
    }
    
    public Cliente getCliente(HttpServletRequest request){
        Cliente cliente = new Cliente();
        
        try {
            int id = Integer.valueOf(request.getParameter("cliente.id"));
            cliente.setId(id);
        } catch (Exception e) {
            cliente.setId(0);
        }
        cliente.setNome(request.getParameter("cliente.nome"));
        cliente.setEmail(request.getParameter("cliente.email"));
        cliente.setTelefone(request.getParameter("cliente.telefone"));
        
        return cliente;
    };
}
