/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Sitios;

import Controller.Conexion;
import Dao.SitioJpaController;
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

/**
 *
 * @author Daniel
 */
public class Modificar_Sitio extends HttpServlet {

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
       String nombre=request.getParameter("nombre");      
        
           
            SitioJpaController s=new  SitioJpaController(c.getBd());
            Utilidad_Sitio us=new Utilidad_Sitio();            
            
          
       try {
            Sitio b=us.Buscarsitio(nombre);   
           if(b==null){
                String estado = "noencontrado";
                request.setAttribute("estado", estado);                
                request.getRequestDispatcher("modificar_sitio.jsp").forward(request, response);

            } else {
                request.setAttribute("sitio", b);
                 String estado = "encontrado";
                request.setAttribute("estado", estado);   
                request.getRequestDispatcher("actualizar_sitio.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(Modificar_Sitio.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("erroruser.jsp");
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
