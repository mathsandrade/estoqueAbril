/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontController;

import Model.Cliente;
import Model.Pedido;
import Model.Produto;
import Service.ClienteService;
import Service.PedidoService;
import Service.ProdutoService;
import ViewModel.PedidoViewModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Matheus
 */
@WebServlet(name = "PedidoController", urlPatterns = {"/pedidos"})
public class PedidoController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
                String action = request.getParameter("a");
        if (action == null)
            action = "";
        
        PedidoService service = new PedidoService();
        ProdutoService prodService = new ProdutoService();
        ClienteService cliService = new ClienteService();
        
        PedidoViewModel viewModel = new PedidoViewModel();
        
        if(action.equals("new")){
            List<Produto> produtos = prodService.list();
            List<Cliente> clientes = cliService.list();
            viewModel.novo(clientes, produtos, request, response);
            
        }else if(action.equals("create")){
            Pedido pedido = viewModel.getPedido(request);
            pedido = service.create(pedido);
            viewModel.create(pedido.getId(), request, response);
            
        }else if(action.equals("edit")){
            Pedido pedido = viewModel.getPedido(request);
            pedido = service.select(pedido);
            List<Produto> produtos = prodService.list();
            List<Cliente> clientes = cliService.list();
            viewModel.edit(clientes, produtos, pedido, request, response);
            
        }else if(action.equals("update")){
            Pedido pedido = viewModel.getPedido(request);
            pedido = service.update(pedido);
            viewModel.update(pedido.getId(), request, response);
            
        }else if(action.equals("delete")){
            Pedido pedido = viewModel.getPedido(request);
            service.delete(pedido);
            viewModel.delete(request, response);
            
        }else{
            List<Pedido> pedidos = service.list();
            viewModel.list(pedidos, request, response);
        }
      }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
