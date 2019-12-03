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
import Dto.Logistico;
import Dto.Persona;
import Dto.Sitio;
import Dto.Solicitud;
import Utilidad.Utilidad_Per;
import Utilidad.Utilidad_Sitio;
import java.io.IOException;
import java.io.PrintWriter;
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
public class Registrar_Solicitud extends HttpServlet {

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
        Conexion c = Conexion.getConexion();
        int idlogi = Integer.parseInt(request.getParameter("idlogistico"));
        int idsitio = Integer.parseInt(request.getParameter("idsitio"));
        
        System.err.println("sitio:"+idsitio+"-----------------"+"logis"+idlogi);
        String descripcion = request.getParameter("descripcion");
        String correo = request.getParameter("correo");
        System.err.println("correoooo"+ correo);
        Utilidad_Sitio us = new Utilidad_Sitio();
        Utilidad_Per up = new Utilidad_Per();

        SitioJpaController s = new SitioJpaController(c.getBd());
        LogisticoJpaController lo = new LogisticoJpaController(c.getBd());
        SolicitudJpaController so = new SolicitudJpaController(c.getBd());
        Persona p = up.Buscaruser(correo);
        Sitio si = s.findSitio(idsitio);
        
        
        
        Logistico logi = lo.findLogistico(idlogi);
        System.err.println(""+logi);

        try {
            Solicitud pe = new Solicitud();
            pe.setDescripcion(descripcion);
            pe.setEstado("Evaluando");
            pe.setPersonaId(p);
            pe.setSitioIdSitio(si);
            pe.setLogisticoIdLog(logi);
            si.setEstado("Ocupado");
            s.edit(si);
            so.create(pe);
           
            String estado = "Registadosolicitud";
            request.setAttribute("nombre", estado);
            request.getRequestDispatcher("sitios_user.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(Eliminar_Sitio.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("sitios_user.jsp");

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
