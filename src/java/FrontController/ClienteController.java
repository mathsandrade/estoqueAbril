/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontController;

import Model.Cliente;
import Service.ClienteService;
import ViewModel.ClienteViewModel;
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
@WebServlet(name = "ClienteController", urlPatterns = {"/clientes"})
public class ClienteController extends HttpServlet {

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
        String action = request.getParameter("a");
        if (action == null)
            action = "";
        
        ClienteService service = new ClienteService();
        ClienteViewModel viewModel = new ClienteViewModel();
        
        if(action.equals("new")){
            viewModel.novo(request, response);
            
        }else if(action.equals("create")){
            Cliente cliente = viewModel.getCliente(request);
            
            if(service.selectEmail(cliente) != null){
                viewModel.validarEmail(cliente, service.validarEmail(cliente), request, response);
            } else{
            cliente = service.create(cliente);
            viewModel.create(cliente.getId(), request, response);
          }
            
        }else if(action.equals("edit")){
            Cliente cliente = viewModel.getCliente(request);
            cliente = service.select(cliente);
            viewModel.edit(cliente, request, response);
            
        }else if(action.equals("update")){
            Cliente cliente = viewModel.getCliente(request);
            cliente = service.update(cliente);
            viewModel.update(cliente.getId(), request, response);
            
        }else if(action.equals("delete")){
            Cliente cliente = viewModel.getCliente(request);
            service.delete(cliente);
            viewModel.delete(request, response);
            
        }else{
            List<Cliente> clientes = service.list();
            viewModel.list(clientes, request, response);
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
