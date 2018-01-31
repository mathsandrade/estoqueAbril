/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontController;

import Model.Produto;
import Service.ProdutoService;
import ViewModel.ProdutoViewModel;
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
@WebServlet(name = "ProdutoController", urlPatterns = {"/produtos"})
public class ProdutoController extends HttpServlet {

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
        
        ProdutoService service = new ProdutoService();
        ProdutoViewModel viewModel = new ProdutoViewModel();
        
        if(action.equals("new")){
            viewModel.novo(request, response);
            
        }else if(action.equals("create")){
            Produto produto = viewModel.getProduto(request);
            
            if(service.selectNome(produto) != null){
                viewModel.validarNome(produto, service.validarNome(produto), request, response);
            } else{
            produto = service.create(produto);
            viewModel.create(produto.getId(), request, response);
          }
            
        }else if(action.equals("edit")){
            Produto produto = viewModel.getProduto(request);
            produto = service.select(produto);
            viewModel.edit(produto, request, response);
            
        }else if(action.equals("update")){
            Produto produto = viewModel.getProduto(request);
            produto = service.update(produto);
            viewModel.update(produto.getId(), request, response);
            
        }else if(action.equals("delete")){
            Produto produto = viewModel.getProduto(request);
            service.delete(produto);
            viewModel.delete(request, response);
            
        }else{
            List<Produto> produtos = service.list();
            viewModel.list(produtos, request, response);
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
