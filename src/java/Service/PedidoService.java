/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.PedidoDAO;
import Data.Pool;
import Model.Cliente;
import Model.Pedido;
import Model.Produto;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Matheus
 */
public class PedidoService {
    private Connection connection;
    
    public PedidoService(){
    }
    
    public Pedido create(Pedido pedido){
        PedidoDAO dao = getDao();
        Cliente cliente = new ClienteService().select(pedido.getCliente());
        pedido.setCliente(cliente);
        Produto produto = new ProdutoService().select(pedido.getProduto());
        pedido.setProduto(produto);
        Pedido novoPedido = dao.insert(pedido);
        releaseDao(dao);
        return novoPedido;
    }
    
    public Pedido select(Pedido pedido){
        PedidoDAO dao = getDao();
        Pedido slPedido = dao.select(pedido);
        releaseDao(dao);
        return slPedido;
    }
    
    public List<Pedido> list(){
        PedidoDAO dao = getDao();
        List<Pedido> pedidos = dao.selectAll();
        releaseDao(dao);
        return pedidos;
    }
    
    public Pedido update(Pedido pedido){
        PedidoDAO dao = getDao();
        Cliente cliente = new ClienteService().select(pedido.getCliente());
        pedido.setCliente(cliente);
        Produto produto = new ProdutoService().select(pedido.getProduto());
        pedido.setProduto(produto);
        Pedido pedidoAtt = dao.update(pedido);
        releaseDao(dao);
        return pedidoAtt;
    }
    
    public void delete(Pedido pedido){
        PedidoDAO dao = getDao();
        dao.delete(pedido);
        releaseDao(dao);
    }
    
    public PedidoDAO getDao(){
        connection = Pool.get();
        return new PedidoDAO(connection);
    }
    
    public void releaseDao(PedidoDAO dao){
        if (dao != null) {
            if (connection != null) {
                Pool.release(connection);
            }
            dao = null;
        }
    }
}
