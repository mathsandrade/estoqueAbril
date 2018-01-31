/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Cliente;
import Model.Produto;
import Model.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus
 */
public class PedidoDAO {
 
    private Connection connection;
    
    public PedidoDAO(Connection conn){
        this.connection = conn;
    }
   
    public Pedido construir(ResultSet result) throws SQLException{
        Pedido pedido = new Pedido();
        
        pedido.setId(result.getInt("id"));
        pedido.setProduto(new ProdutoDAO(connection).select(new Produto(){{
            setId(result.getInt("id_produto"));
        }}));
        pedido.setCliente(new ClienteDAO(connection).select(new Cliente(){{
            setId(result.getInt("id_cliente"));
        }}));

        return pedido;       
    }
    
    public Pedido insert(Pedido pedido){
        PreparedStatement command;
        String  sql = "INSERT INTO pedido (id_produto, id_cliente) VALUES (?,?)";
        Pedido novoPedido = null;
        try{
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            command.setInt(1, pedido.getProduto().getId());
            command.setInt(2, pedido.getCliente().getId());
            command.executeUpdate();
            
            ResultSet result = command.getGeneratedKeys();
            if(result.next()){
                novoPedido = construir(result);
            }
            connection.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        return novoPedido;
    }
    
    public Pedido select(Pedido pedido){
        PreparedStatement command;
        String sql = "SELECT id, id_produto, id_cliente FROM pedido WHERE id = ?";
        Pedido pedidoBanco = null;
        try{
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql);
            command.setInt(1, pedido.getId());
            ResultSet result = command.executeQuery();
            if(result.next()){
                pedidoBanco = construir(result);
            }
            connection.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        return pedidoBanco;
    }
    
    public List<Pedido> selectAll(){
        PreparedStatement command;
        String sql = "SELECT id, id_produto, id_cliente FROM pedido";
        List<Pedido> pedidos = new ArrayList<>();
        try{
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql);
            ResultSet result = command.executeQuery();
            while(result.next()){
                Pedido pedido = construir(result);
                pedidos.add(pedido);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return pedidos;
    }
    
    public Pedido update(Pedido pedido){
        PreparedStatement command;
        String sql = "UPDATE pedido SET id_produto = ?, id_cliente = ? WHERE id = ?";
        Pedido pedidoAtt = null;
        try{
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            command.setInt(1, pedido.getProduto().getId());
            command.setInt(2, pedido.getCliente().getId());
            command.setInt(3, pedido.getId());
            command.executeUpdate();
            
            ResultSet result = command.getGeneratedKeys();
            if(result.next()){
                pedidoAtt = construir(result);
            }
            connection.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        return pedidoAtt;
    }
    
    public void delete(Pedido pedido){
        PreparedStatement command;
        String sql = "DELETE FROM pedido WHERE id = ?";
        try{
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql);
            command.setInt(1, pedido.getId());
            command.executeUpdate();
            connection.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
