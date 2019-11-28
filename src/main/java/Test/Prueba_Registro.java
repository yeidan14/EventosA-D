/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Controller.Conexion;
import Dao.PersonaJpaController;
import Dto.Persona;





/**
 *
 * @author Daniel
 */
public class Prueba_Registro {
    
    
    
    public static void main(String[] args) {
        
         Persona per = new Persona();
        per.setNombre("Yeison Daniel");
        per.setApellido("Manrique Camacho");
        per.setPass("yeidan141995");
        per.setCargo("usuario");
        per.setEdad(24);
        per.setCorreo("yeidan122@gmail.com");
        Conexion con = Conexion.getConexion();
        PersonaJpaController reg = new PersonaJpaController(con.getBd());
         reg.create(per);
        System.err.println("registrado");
        
        
        
        
    }
    
}
