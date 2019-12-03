/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Solicitud;

import Controller.Conexion;
import Controller.Sitios.Eliminar_Sitio;
import Dao.LogisticoJpaController;
import Dao.SitioJpaController;
import Dao.SolicitudJpaController;
import Dto.Persona;
import Dto.Sitio;
import Dto.Solicitud;
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
public class Eliminar_Solicitud extends HttpServlet {

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
         Integer id=Integer.parseInt(request.getParameter("idsol"));
         
               SitioJpaController s = new SitioJpaController(c.getBd());
        LogisticoJpaController lo = new LogisticoJpaController(c.getBd());
        SolicitudJpaController so = new SolicitudJpaController(c.getBd());
        
        Solicitud sol=so.findSolicitud(id);
        
        Sitio si=s.findSitio(sol.getSitioIdSitio().getIdSitio());
        si.setEstado("Libre");
       
         
       try { 
           s.edit(si);
            so.destroy(id);
            
            String registrado = "eliminado";
                request.setAttribute("nombre", registrado);
                request.getRequestDispatcher("solicitud_user.jsp").forward(request, response);
         
        
         }catch(Exception ex){
               Logger.getLogger(Eliminar_Solicitud.class.getName()).log(Level.SEVERE, null, ex);
           response.sendRedirect("solicitud_user.jsp");
        
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
