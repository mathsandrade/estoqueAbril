/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.ClienteDAO;
import Data.Pool;
import Model.Cliente;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Matheus
 */
public class ClienteService {
    
    private Connection connection;
    
    public ClienteService(){
    }
    
    public Cliente create(Cliente cliente){
        ClienteDAO dao = getDao();
        Cliente novoCliente = dao.insert(cliente);    
        releaseDao(dao); 
        return novoCliente;
    }
    
    public Cliente select(Cliente cliente){
        ClienteDAO dao = getDao();
        Cliente slCliente = dao.select(cliente);
        releaseDao(dao);   
        return slCliente;
    }
    
    public Cliente selectEmail(Cliente cliente){
        ClienteDAO dao = getDao();
        Cliente slCliente = dao.selectEmail(cliente);
        releaseDao(dao);
        return slCliente;
    }
    
    public List<Cliente> list(){
        ClienteDAO dao = getDao();
        List<Cliente> clientes = dao.selectAll();
        releaseDao(dao);
        return clientes;
    }
    
    public Cliente update(Cliente cliente){
        ClienteDAO dao = getDao();
        Cliente clienteAtt = dao.update(cliente);
        releaseDao(dao);
        return clienteAtt;
    }
    
    public void delete(Cliente cliente){
        ClienteDAO dao = getDao();
        dao.delete(cliente);
        releaseDao(dao);
    }
    
    public String validarEmail(Cliente email){
        if (email != null ){
            return "Este email já existe.";
        }
        return "";
    }
    
    public ClienteDAO getDao(){
        connection = Pool.get();
        return new ClienteDAO(connection);
    }
    
    public void releaseDao(ClienteDAO dao){
        if (dao != null) {
            if (connection != null) {
                Pool.release(connection);
            }
            dao = null;
        }
    }
}
