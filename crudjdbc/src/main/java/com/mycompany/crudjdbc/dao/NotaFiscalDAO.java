/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crudjdbc.dao;

import com.mycompany.crudjdbc.model.NotaFiscal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author alexsandro
 */
public class NotaFiscalDAO {
    public static boolean salvar(NotaFiscal obj){
        
     boolean retorno =false;
     Connection conexao =null;
        try {
            // passo 1 - carregar o driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url ="jdbc:mysql://localhost:3306/basenotafiscal";
            
            conexao=DriverManager.getConnection(url, "root", "");
            //PASSO 3  prepara o comando SQL
           PreparedStatement comandoSQL = conexao.prepareStatement ("INSERT INTO NOTAFISCAL " + "(numeroNota,valorNota)VALUES (?,?)");
           
        comandoSQL.setInt(1,obj.getNumeroNota());
        
        comandoSQL.setDouble(2,obj.getValorNota());
        //passo 4 excutar
        int linhasAfetadas = comandoSQL.executeUpdate();
        if(linhasAfetadas>0){
        retorno=true;
        }
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro ao carregar o driver !!");   
        } catch (SQLException ex) {
            System.out.println("Erro ao abrir a conexão !!");       
        }
        
    return retorno;
}// fim do metodo salvar 
    
    public static ArrayList<NotaFiscal> listar(){
        
    ArrayList<NotaFiscal> listaRetorno = new ArrayList<>();
    Connection conexao=null;
        try {
            // passo 1 carregar o driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //passso 2 abrir a conexao 
            String url = "jdbc:mysql://localhost:3306/basenotafiscal";
            conexao = DriverManager.getConnection(url,"root","");
            // passo 3 preparar o comando SQL
            PreparedStatement comandoSQL=conexao.prepareStatement("SELECT *FROM notafiscal");
            //passo 4 executar o comando SQL 
           ResultSet rs = comandoSQL.executeQuery();
           if (rs!=null) {
                          //percorro o resultset e passo os valores a um objeto 

           while(rs.next()){
               NotaFiscal obj = new NotaFiscal();
               obj.setIdNota(rs.getInt("idNota"));
               obj.setNumeroNota(rs.getInt("NumeroNota"));
               obj.setValorNota(rs.getDouble("NumeroNota"));
               listaRetorno.add(obj);
           }
           }
        } catch (ClassNotFoundException ex) {
            System.out.println("erro ao carrregar o drive !!");    
        }
        catch (SQLException ex) {
                        System.out.println(" Erro ao abrir conexão !!");    

        }
        return listaRetorno;
        
    }// Fim metodo listar
    
    public static boolean excluir (int id){
        
     boolean retorno =false;
     Connection conexao =null;
        try {
            // passo 1 - carregar o driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url ="jdbc:mysql://localhost:3306/basenotafiscal";
            
            conexao=DriverManager.getConnection(url, "root", "");
            //PASSO 3  prepara o comando SQL
           PreparedStatement comandoSQL = conexao.prepareStatement ("DELETE FROM notafiscal WHERE idNota =?");
           
        comandoSQL.setInt(1,id);
        
        //passo 4 excutar
        int linhasAfetadas = comandoSQL.executeUpdate();
        if(linhasAfetadas>0){
        retorno=true;
        }
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro ao carregar o driver !!");   
        } catch (SQLException ex) {
            System.out.println("Erro ao abrir a conexão !!");       
        }
        return retorno;
        
    }
}//fim da classe 