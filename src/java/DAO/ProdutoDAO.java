/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Produto;
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
public class ProdutoDAO {
    
    private Connection connection;
    
    public ProdutoDAO(Connection conn){
        this.connection = conn;
    }
    
    public Produto construir(ResultSet result) throws SQLException {
        Produto produto = new Produto();
        
        produto.setId(result.getInt("id"));
        produto.setNome(result.getString("nome"));
        produto.setDescricao(result.getString("descricao"));
        produto.setPreco(result.getDouble("preco"));
        
        return produto;
    }
    
    public Produto insert(Produto produto){
        PreparedStatement command;
        String sql = "INSERT INTO produto (nome, descricao, preco) VALUES (?,?,?)";
        Produto novoProduto = null;
        try{
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            command.setString(1, produto.getNome());
            command.setString(2, produto.getDescricao());
            command.setDouble(3, produto.getPreco());
            command.executeUpdate();
            
            ResultSet result = command.getGeneratedKeys();
            if(result.next()){
                novoProduto = construir(result);
            }
            connection.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        return novoProduto;
    }
    
    public Produto select(Produto produto){
        PreparedStatement command;
        String sql = "SELECT id, nome, descricao, preco FROM produto WHERE id = ?";
        Produto produtoBanco = null;
        try{
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql);
            command.setInt(1, produto.getId());
            ResultSet result = command.executeQuery();          
            if(result.next()){
                produtoBanco = construir(result);
            }
            connection.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        return produtoBanco;
    }
    
    public Produto selectNome(Produto produto){
        PreparedStatement command;
        String sql = "SELECT id, nome, descricao, preco FROM produto WHERE nome = ?";
        Produto produtoBanco = null;
        try{
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql);
            command.setString(1, produto.getNome());
            ResultSet result = command.executeQuery();          
            if(result.next()){
                produtoBanco = construir(result);
            }
            connection.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        return produtoBanco;
    }
    
    public List<Produto> selectAll(){
        PreparedStatement command;
        String sql = "SELECT id, nome, descricao, preco FROM produto";
        List<Produto> produtos = new ArrayList<>();
        try{
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql);
            ResultSet result = command.executeQuery();
            while(result.next()){
                Produto produto = construir(result);
                produtos.add(produto);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return produtos;
    }
    
    public Produto update(Produto produto){
        PreparedStatement command;
        String sql = "UPDATE produto SET nome = ?, descricao = ?, preco = ? WHERE id = ?";
        Produto produtoAtt = null;
        try{
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            command.setString(1, produto.getNome());
            command.setString(2, produto.getDescricao());
            command.setDouble(3, produto.getPreco());
            command.setInt(4, produto.getId());
            command.executeUpdate();
            
            ResultSet result = command.getGeneratedKeys();
            if(result.next()){
                produtoAtt = construir(result);
            }
            connection.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        return produtoAtt;
    }
    
    public void delete(Produto produto){
        PreparedStatement command;
        String sql = "DELETE FROM produto WHERE id = ?";
        try{
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql);
            command.setInt(1, produto.getId());
            command.executeUpdate();
            connection.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
