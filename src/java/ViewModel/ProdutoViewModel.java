/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModel;

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
public class ProdutoViewModel {
      
    public void novo(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        request.setAttribute("action", "create");
        request.getRequestDispatcher("produtos/new.jsp").forward(request, response);
    };
    
    public void create(int clienteId, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.sendRedirect(request.getContextPath() + "/produtos");
    };
    
    public void list(List<Produto> produtos, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        request.setAttribute("produtos", produtos);
        request.getRequestDispatcher("produtos/list.jsp").forward(request, response);
    };
    
    public void edit(Produto produto, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        request.setAttribute("action", "update");
        request.setAttribute("produto", produto);
        request.getRequestDispatcher("produtos/edit.jsp").forward(request, response);
    };
    
    public void update(int clienteId, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.sendRedirect(request.getContextPath() + "/produtos");
    };
    
    public void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.sendRedirect(request.getContextPath() + "/produtos");
    };
    
    public void validarNome(Produto produto, String nome, HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException{                                               
        request.setAttribute("mensagem", nome);                                             
        request.setAttribute("produto", produto);
        request.setAttribute("action", "create");
        request.getRequestDispatcher("produtos/new.jsp").forward(request, response);
    }
    
    public Produto getProduto(HttpServletRequest request){
        Produto produto = new Produto();
        
        try{
            int id = Integer.valueOf(request.getParameter("produto.id"));
            produto.setId(id);
        }catch (Exception e) {
            produto.setId(0);
        }
        produto.setNome(request.getParameter("produto.nome"));
        produto.setDescricao(request.getParameter("produto.descricao"));
        try{
            double preco = Double.valueOf(request.getParameter("produto.preco"));
            produto.setPreco(preco);
        }catch(Exception e){
            produto.setPreco(0);
        }
        
        return produto;
    };
}
