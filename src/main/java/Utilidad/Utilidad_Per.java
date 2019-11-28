/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidad;



import Controller.Conexion;
import Dto.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Daniel
 */
public class Utilidad_Per {    
    
    
     public Persona Buscaruser(String email) {
       Connection cn=Conexion.getConexion2();
       Persona u=new Persona();
        try {
            String sql = "SELECT * FROM persona"; 
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()){
             if(rs.getString("Correo").equals(email)){
                
                int id=rs.getInt(1);
                String nom=rs.getString(2);
                String ape=rs.getString(3);
                int edad=rs.getInt(4);
                String correo=rs.getString(5);
                String pass=rs.getString(6);
                String cargo=rs.getString(7);
             
               u.setId(id);
               u.setNombre(nom);
               u.setApellido(ape);
               u.setEdad(edad);
               u.setCorreo(correo);
               u.setPass(pass);
               u.setCargo(cargo);
               
              
               
           
             }
            }
            
        } catch (Exception e) {
        }
        return u;
    }
    
     
      public boolean Existe(String email) {
       Connection cn=Conexion.getConexion2();
      boolean existe=false;
        try {
            String sql = "SELECT * FROM persona"; 
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            System.err.println("-------------por aqui paso antes de while");
            while(rs.next()){
             if(rs.getString("Correo").equals(email)){
                
              existe=true;
                 return existe;
               
              
               
           
             }else{
                 existe=false;
                 return existe;
             }
            }
            
        } catch (Exception e) {
        }
        return existe;
    }
      
      
       public boolean Es_Admin(String email) {
       Connection cn=Conexion.getConexion2();
      boolean existe=false;
        try {
            String sql = "SELECT * FROM persona"; 
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()){
             if((rs.getString("Correo").equals(email)) && (rs.getString("Cargo").equals("administrador"))){
                
              existe=true;
                return existe;
              
               
           
             }
             
             else{
                 existe=false;
                  return existe;
             }
            }
            
        } catch (Exception e) {
        }
        return existe;
    }
}
