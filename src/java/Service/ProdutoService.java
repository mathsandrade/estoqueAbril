/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.ProdutoDAO;
import Data.Pool;
import Model.Produto;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Matheus
 */
public class ProdutoService {
    private Connection connection;
    
    public ProdutoService(){
    }
    
    public Produto create(Produto produto){
        ProdutoDAO dao = getDao();
        Produto novoProduto = dao.insert(produto);
        releaseDao(dao);
        return novoProduto;
    }
    
    public Produto select(Produto produto){
        ProdutoDAO dao = getDao();
        Produto slProduto = dao.select(produto);
        releaseDao(dao);
        return slProduto;
    }
    
    public Produto selectNome(Produto produto){
        ProdutoDAO dao = getDao();
        Produto slProduto = dao.selectNome(produto);
        releaseDao(dao);
        return slProduto;
    }
    
    public List<Produto> list(){
        ProdutoDAO dao = getDao();
        List<Produto> produtos = dao.selectAll();
        releaseDao(dao);
        return produtos;
    }
    
    public Produto update(Produto produto){
        ProdutoDAO dao = getDao();
        Produto produtoAtt = dao.update(produto);
        releaseDao(dao);
        return produtoAtt;
    }
    
    public void delete(Produto produto){
        ProdutoDAO dao = getDao();
        dao.delete(produto);
        releaseDao(dao);
    }
    
    public String validarNome(Produto produto){
        if (produto != null ){
            return "O nome deste produto já existe.";
        }
        return "";
    }
       
    public ProdutoDAO getDao(){
        connection = Pool.get();
        return new ProdutoDAO(connection);
    }
    
    public void releaseDao(ProdutoDAO dao){
        if (dao != null) {
            if (connection != null) {
                Pool.release(connection);
            }
            dao = null;
        }
    }
}
