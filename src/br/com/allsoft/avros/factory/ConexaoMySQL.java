/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.allsoft.avros.factory;

// faz as importações de classes necessárias para o funcionamento do programa

import java.sql.Connection; //conexão SQL para Java
import java.sql.DriverManager; //driver de conexão SQL para Java
import java.sql.SQLException; //classe para tratamente de exceções
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luana
 */
public class ConexaoMySQL {
    public static String status = "Não conectou..";
    
    //método construtor da classe
    public ConexaoMySQL(){
        
    }
    
    /**
     * Cria conexão com o banco de dados
     * 
     * @return Connection
     */
    public static java.sql.Connection getConexaoMySQL(){
        Connection connection = null; //atributo do tipo Connection
        
        try{
            //carregando o JDBC Driver padrão
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);
            
            //configurando a nossa conexão com um banco de dados
            String serverName = "localhost:3306"; //caminho do servidor do BD, ip da máquina do servidor
            String mydatabase = "bd_estudio"; //nome do seu banco de dados
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
            String username = "root"; //nome de uduário de ser BD
            String password = ""; //sua senha de acesso
            
            connection = DriverManager.getConnection(url, username, password);
            
            //testa sua conexão
            if (connection != null){
                status = ("STATUS: Conectado com sucesso!");
            } else {
                status = ("STATUS: Não foi possível realizar a conexão.");
            }
            
            return connection;
        } catch (SQLException e) {
            //Não conseguindo se conectar ao banco..
            System.out.println(e);
            return null;
            
        //isso não deveria estar aqui, acho
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexaoMySQL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    //método que retorna o status da sua conexão

    /**
     *
     * @return
     */
    public static String statusConection(){
        return status;
    }
        
   //método que fecha sua conexão
    public static boolean FecharConexao() throws ClassNotFoundException{
        try {
            ConexaoMySQL.getConexaoMySQL().close();
            status = "STATUS: A conexão foi fechada.";
            return true;
        } catch (SQLException e) {
            System.out.println("Não foi possível fechar a conexão.");
            return false;
        }
    }
    
    //MÉTODO QUE REINICIA SUA CONEXÃO
    public static java.sql.Connection ReiniciarConexao() throws ClassNotFoundException {
        FecharConexao();
        return ConexaoMySQL.getConexaoMySQL();
    }

}
