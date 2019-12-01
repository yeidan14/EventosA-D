/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.PersonaJpaController;
import Dto.Persona;
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
public class Login_Admin extends HttpServlet {

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
   String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        Utilidad.Utilidad_Per bus= new  Utilidad.Utilidad_Per();
        Persona p =bus.Es_Admin(email);
     
        System.err.println(""+p.getNombre());
      
        try {

            if (p != null) {
                if (pass.equals(p.getPass())) {
                    HttpSession misession = request.getSession(true);
                    misession.setAttribute("admin", p.getNombre());

                    response.sendRedirect("index_admin.jsp");
                } else {
                    String tipo = "Contraerror";
                    request.setAttribute("nombre", tipo);                    
                    request.getRequestDispatcher("admin.jsp").forward(request, response);
                }
            } else if(p==null) {
                
                String tipo = "NoExiste";
                    request.setAttribute("nombre", tipo);
                    request.getRequestDispatcher("admin.jsp").forward(request, response);
            }

//        
        } catch (Exception ex) {
            Logger.getLogger(Login_Admin.class.getName()).log(Level.SEVERE, null, ex);
            
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
