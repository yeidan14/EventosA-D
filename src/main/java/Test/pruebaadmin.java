/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Utilidad.Utilidad_Per;

/**
 *
 * @author Daniel
 */
public class pruebaadmin {
    
    public static void main(String[] args) {
        Utilidad.Utilidad_Per bus=new Utilidad_Per();
         boolean b=bus.Es_Admin("yeidan-14@hotmail.com");
            boolean c=bus.Existe("yeid23an-14@hotmail.com");
          System.err.println(""+ b+".-----."+c);
    }
    
}
