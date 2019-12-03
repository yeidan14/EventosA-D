/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidad;

import Controller.Conexion;
import Dto.Sitio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class Utilidad_Sitio {
    
    
    
    public Sitio Buscarsitio(String nombre) {
       Connection cn=Conexion.getConexion2();
       Sitio u=new Sitio();
        try {
            String sql = "SELECT * FROM sitio"; 
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()){
             if(rs.getString("Nombre").equals(nombre)){
                
                int id=rs.getInt(1);
                String nom=rs.getString(2);
                int capacidad=rs.getInt(3);
                Date f=rs.getDate(4);
                String ubicacion=rs.getString(5);
                String estado=rs.getString(6);
               
             
               u.setIdSitio(id);
               u.setNombre(nom);
               u.setCapacidad(capacidad);
               u.setFechaIni(f);
               u.setUbicacion(ubicacion);
               u.setEstado(estado);
               
              
               
           
             }
            }
            
        } catch (Exception e) {
        }
        return u;
    }
    
    
   
}
