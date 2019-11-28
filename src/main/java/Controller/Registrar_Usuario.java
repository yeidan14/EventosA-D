/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.PersonaJpaController;
import Dto.Persona;
import Utilidad.Email;
import Utilidad.Utilidad_Per;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Daniel
 */
public class Registrar_Usuario extends HttpServlet {

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
        
       Email email = new Email();            
       String nombre=request.getParameter("nombre");
       String apellidos=request.getParameter("apellidos");
       String correo=request.getParameter("email");
       String pass=request.getParameter("pass");
       String edad= request.getParameter("edad");
       Integer ed=Integer.parseInt(edad);
            String de = "eventos.a.d.cucuta@gmail.com";
            String clave = "yeidan141995";
         
            String mensaje = "¡Felicitaciones Su registro ha Sido Exitoso!"
                    + "\n"+"Para Ingresar a la Plataforma Utilice Su Correo y Su Contraseña:"
                    +"\n"+"QUE ESPERAS? INICIA SESSION LO ANTES POSBLE..."+"\n"+"Gracias por ser parte de nuestros Clientes!";
            String asunto = "Bienvenido a Eventos A&D";
            
            
         Utilidad_Per p=new Utilidad_Per();        
         if(p.Existe(correo)==false){
        Persona per = new Persona();
        per.setNombre(nombre);
        per.setApellido(apellidos);
        per.setPass(pass);
        per.setCargo("usuario");
        per.setEdad(ed);
        per.setCorreo(correo);
        Conexion con = Conexion.getConexion();
        PersonaJpaController reg = new PersonaJpaController(con.getBd());
        reg.create(per);
         boolean resultado = email.enviarCorreo(de, clave, correo, mensaje, asunto);
         String registrado = "registrado";
                request.setAttribute("nombre", registrado);
                request.getRequestDispatcher("index.jsp").forward(request, response);
         }else{
               String registrado = "yaexiste";
                request.setAttribute("nombre", registrado);
                request.getRequestDispatcher("registro.jsp").forward(request, response);
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
