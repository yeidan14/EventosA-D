/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Sitios;

import Controller.Conexion;
import Dao.SitioJpaController;
import Dao.exceptions.NonexistentEntityException;
import Dto.Sitio;
import Utilidad.Utilidad_Sitio;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Daniel
 */
public class Actualizar_Sitio extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws Dao.exceptions.NonexistentEntityException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
     Conexion c =Conexion.getConexion();
     Integer id=Integer.parseInt(request.getParameter("id"));
       String nombre=request.getParameter("nsitio");      
        String cap=request.getParameter("csitio");   
         System.err.println(nombre +"----------"+cap);
        Integer capacidad=Integer.parseInt(cap);     
       Date fini=Date.valueOf(request.getParameter("fechasitio"));
        System.err.println("fecha:----------"+fini);
       String ubicacion=request.getParameter("ubicacion");
       String estado=request.getParameter("estado");   
       
         try { 
           
            SitioJpaController s=new  SitioJpaController(c.getBd());
               
             Sitio nuevo=s.findSitio(id);
             nuevo.setNombre(nombre);
             nuevo.setCapacidad(capacidad);
             nuevo.setFechaIni(fini);
             nuevo.setUbicacion(ubicacion);
             nuevo.setEstado(estado);             
             
             
             s.edit(nuevo);
             
         String registrado = "registrado";
                request.setAttribute("nombre", registrado);
                request.getRequestDispatcher("modificar_sitio.jsp").forward(request, response);
         
        
         }catch(Exception ex){
               Logger.getLogger(Actualizar_Sitio.class.getName()).log(Level.SEVERE, null, ex);
           response.sendRedirect("modificar_sitio.jsp");
        
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
