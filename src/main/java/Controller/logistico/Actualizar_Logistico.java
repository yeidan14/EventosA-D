/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.logistico;

import Controller.Conexion;
import Dao.LogisticoJpaController;
import Dto.Logistico;
import Utilidad.Utilidad_Sitio;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Daniel
 */
public class Actualizar_Logistico extends HttpServlet {

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
       String descripcion=request.getParameter("descripcion");    
       int id=Integer.parseInt(request.getParameter("id"));      
       
        int capacidad=Integer.parseInt(request.getParameter("clogistica"));    
       
           
            LogisticoJpaController s=new  LogisticoJpaController(c.getBd());
            Utilidad_Sitio us=new Utilidad_Sitio();
            
           
            
            try { 
           
           Logistico l=new Logistico();
             l.setDescripcion(descripcion);
             l.setCant(capacidad);
             l.setIdLog(id);
                System.err.println("ididid"+id);
             s.destroy(id);
             s.create(l);
         String registrado = "registrado";
                request.setAttribute("nombre", registrado);
                request.getRequestDispatcher("modificar_logistico.jsp").forward(request, response);
         
        
         }catch(Exception ex){
               Logger.getLogger(Actualizar_Logistico.class.getName()).log(Level.SEVERE, null, ex);
           response.sendRedirect("modificar_logistico.jsp");
        
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
