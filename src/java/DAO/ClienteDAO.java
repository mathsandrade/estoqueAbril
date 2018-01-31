/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Cliente;
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
public class ClienteDAO {
    
    private Connection connection;
    
    public ClienteDAO(Connection conn){
        this.connection = conn;
    }
    
    public Cliente construir(ResultSet result) throws SQLException{
        Cliente cliente = new Cliente();
        
        cliente.setId(result.getInt("id"));
        cliente.setNome(result.getString("nome"));
        cliente.setEmail(result.getString("email"));
        cliente.setTelefone(result.getString("telefone"));
        
        return cliente;
    }
    
    public Cliente insert(Cliente cliente){
        PreparedStatement command;
        String sql = "INSERT INTO cliente (nome, email, telefone) VALUES (?,?,?)";
        Cliente novoCliente = null;
        try{
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            command.setString(1, cliente.getNome());
            command.setString(2, cliente.getEmail());
            command.setString(3, cliente.getTelefone());
            command.executeUpdate();
            
            ResultSet result = command.getGeneratedKeys();
            if(result.next()){
                novoCliente = construir(result);
            }
            connection.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        return novoCliente;
    }
    
    public Cliente select(Cliente cliente){
        PreparedStatement command;
        String sql = "SELECT id, nome, email, telefone FROM cliente WHERE id = ?";
        Cliente clienteBanco = null;
        try{
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql);
            command.setInt(1, cliente.getId());
            ResultSet result = command.executeQuery();
            if(result.next()){
                clienteBanco = construir(result);
            }
            connection.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        return clienteBanco;
    }
    
        public Cliente selectEmail(Cliente cliente){
        PreparedStatement command;
        String sql = "SELECT id, nome, email, telefone FROM cliente WHERE email = ?";
        Cliente clienteBanco = null;
        try{
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql);
            command.setString(1, cliente.getEmail());
            ResultSet result = command.executeQuery();
            if(result.next()){
                clienteBanco = construir(result);
            }
            connection.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        return clienteBanco;
    }
    
    public List<Cliente> selectAll(){
        PreparedStatement command;
        String sql = "SELECT id, nome, email, telefone FROM cliente";
        List<Cliente> clientes = new ArrayList<>();
        try{
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql);
            ResultSet result = command.executeQuery();
            while(result.next()){
                Cliente cliente = construir(result);
                clientes.add(cliente);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return clientes;
    }
    
    public Cliente update(Cliente cliente){
        PreparedStatement command;
        String sql = "UPDATE cliente SET nome = ?, email = ?, telefone = ? WHERE id = ?";
        Cliente clienteAtt = null;
        try{
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            command.setString(1, cliente.getNome());
            command.setString(2, cliente.getEmail());
            command.setString(3, cliente.getTelefone());
            command.setInt(4, cliente.getId());
            command.executeUpdate();
            
            ResultSet result = command.getGeneratedKeys();
            if(result.next()){
                clienteAtt = construir(result);
            }
            connection.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        return clienteAtt;
    }
    
    public void delete(Cliente cliente){
        PreparedStatement command;
        String sql = "DELETE FROM cliente WHERE id = ?";
        try{
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql);
            command.setInt(1, cliente.getId());
            command.executeUpdate();
            connection.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
