/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Sitios;

import Controller.Conexion;
import Dao.PersonaJpaController;
import Dao.SitioJpaController;
import Dto.Persona;
import Dto.Sitio;
import Utilidad.Email;
import Utilidad.Utilidad_Per;
import Utilidad.Utilidad_Sitio;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Daniel
 */
public class Registrar_Sitio extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
        
           Conexion c =Conexion.getConexion();
       String nombre=request.getParameter("nsitio");      
        String cap=request.getParameter("csitio");   
         System.err.println(nombre +"----------"+cap);
        Integer capacidad=Integer.parseInt(cap);     
       Date fini=Date.valueOf(request.getParameter("fechasitio"));
        System.err.println("fecha:----------"+fini);
       String ubicacion=request.getParameter("ubicacion");
       String estado=request.getParameter("estado");    
           
            SitioJpaController s=new  SitioJpaController(c.getBd());
            Utilidad_Sitio us=new Utilidad_Sitio();
            
            Sitio b=us.Buscarsitio(nombre);      
            System.err.println(""+b);
        
         if(b.getIdSitio()==null){
              System.err.println("creado nuevo -----------");
             Sitio nuevo=new Sitio();
             nuevo.setNombre(nombre);
             nuevo.setCapacidad(capacidad);
             nuevo.setFechaIni(fini);
             nuevo.setUbicacion(ubicacion);
             nuevo.setEstado(estado);              
             s.create(nuevo);        
         String registrado = "registrado";
                request.setAttribute("nombre", registrado);
                request.getRequestDispatcher("agregar_sitio.jsp").forward(request, response);
              
        
         }else if(b.getIdSitio()!=null){ 
            
              System.err.println("vacio-----------");
              String registrado = "yaexiste";
                request.setAttribute("nombre", registrado);
                request.getRequestDispatcher("agregar_sitio.jsp").forward(request, response);
         }
  
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
