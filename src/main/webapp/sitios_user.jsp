<%-- 
    Document   : index_eventos
    Created on : 27/11/2019, 10:54:52 AM
    Author     : Daniel
--%>
<%@page import="java.sql.Connection"%>
<%@page import="Dao.SitioJpaController"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="Controller.Conexion"%>

<%if (session.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp");
    }%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="loading">
 
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <meta name="description" content="Apex admin is super flexible, powerful, clean &amp; modern responsive bootstrap 4 admin template with unlimited possibilities.">
    <meta name="keywords" content="admin template, Apex admin template, dashboard template, flat admin template, responsive admin template, web app">
    <meta name="author" content="PIXINVENT">
    <title>Eventos</title>
    <link rel="apple-touch-icon" sizes="60x60" href="app-assets/img/ico/apple-icon-60.png">
    <link rel="apple-touch-icon" sizes="76x76" href="app-assets/img/ico/apple-icon-76.png">
    <link rel="apple-touch-icon" sizes="120x120" href="app-assets/img/ico/apple-icon-120.png">
    <link rel="apple-touch-icon" sizes="152x152" href="app-assets/img/ico/apple-icon-152.png">
    <link rel="shortcut icon" type="image/x-icon" href="app-assets/img/ico/favicon.ico">
    <link rel="shortcut icon" type="image/png" href="app-assets/img/ico/favicon-32.png">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-touch-fullscreen" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="default">
    <link href="https://fonts.googleapis.com/css?family=Rubik:300,400,500,700,900|Montserrat:300,400,500,600,700,800,900" rel="stylesheet">
    <!-- BEGIN VENDOR CSS-->
    <!-- font icons-->
    <link rel="stylesheet" type="text/css" href="app-assets/fonts/feather/style.min.css">
    <link rel="stylesheet" type="text/css" href="app-assets/fonts/simple-line-icons/style.css">
    <link rel="stylesheet" type="text/css" href="app-assets/fonts/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="app-assets/vendors/css/perfect-scrollbar.min.css">
    <link rel="stylesheet" type="text/css" href="app-assets/vendors/css/prism.min.css">
    <link rel="stylesheet" type="text/css" href="app-assets/vendors/css/chartist.min.css">
    <!-- END VENDOR CSS-->
    <!-- BEGIN APEX CSS-->
    <link rel="stylesheet" type="text/css" href="app-assets/css/app.css">
    <!-- END APEX CSS-->
    <!-- BEGIN Page Level CSS-->
    <!-- END Page Level CSS-->
  </head>
  <!-- END : Head-->

  <!-- BEGIN : Body-->
  <body data-col="2-columns" class=" 2-columns ">
   
    <div class="wrapper nav-collapsed menu-collapsed">


        <div id="menu"> 
        
        </div>
        
      <div class="main-panel">
        <!-- BEGIN : Main Content-->
        <div class="main-content">
          <div class="content-wrapper"><!--Statistics cards Starts-->
<div class="row">
                            <div class="col-md-12">
                                <div class="card">
                                    <div class="card-header">
                                        



                                    </div>
                                    <div class="card-body">
                                        <div class="px-3">

                                            <%String registrado = (String) request.getAttribute("nombre");
                  if (registrado == "Registadosolicitud") {%>
                                            <div class="alert alert-icon-left alert-success alert-dismissible mb-2" role="alert">
                                                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                                <strong>Perfecto!</strong>Se ha Enviado  Correctamente. La Solicitud

                                            </div>
                                            <%}
                 if (registrado == "eliminado") {%>
                                            <div class="alert alert-icon-left alert-success alert-dismissible mb-2" role="alert">
                                                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                                <strong>Perfecto!</strong>Se ha Eliminado Correctamente. El Sitio

                                            </div>
                                            <%}%>


                                            <%
 
                                                //CONECTANOD A LA BASE DE DATOS:
                                                Conexion cn = Conexion.getConexion();
                                               Connection con=Conexion.getConexion2();
                                                PreparedStatement ps;
                                                //Emnpezamos Listando los Datos de la Tabla Usuario
                                                Statement smt;
                                                ResultSet rs;
                                                smt = con.createStatement();
                                                rs = smt.executeQuery("select * from sitio");
                                                //Creamo la Tabla:  
                                                SitioJpaController s = new SitioJpaController(cn.getBd());

                                            %>

                                            
                                                                    

                                                                           

                                                              
                                                                        
                                                                        <section id="header-footer">
  <div class="row">
    <div class="col-12 mt-3 mb-1">
      <div class="content-header">Lista de Sitios Disponibles</div>
      <p class="content-sub-header">Apareceran Los sitios cuando esten dispobibles , si no Aparecen estan Ocupados</p>
    </div>
  </div>
  <div class="row match-height">
    
       <%      while (rs.next()) {
          if(rs.getString("Estado").equals("Libre")){

                                                                            %>
                                                                            
                                                           
                                                                                
                                                                     
                                                                        
                   <div class="col-lg-4 col-md-6 col-sm-12">
      <div class="card" style="height: 399px;">
        <div class="card-header pb-2">
          <h4 class="card-title"><%= rs.getString("Nombre")%></h4>
          <p class="card-text">El sitio Tiene las Siguientes Especificaciones:</p>
        </div>
        <div class="card-content">
          <ul class="list-group">
            <li class="list-group-item">
              <span class="badge bg-primary float-right"><%= rs.getString("Capacidad")%></span> Capacidad de Personas :
            </li>
            <li class="list-group-item">
              <span class="badge bg-info float-right"><%= rs.getString("Ubicacion")%></span> Ubicacion del sitio:
            </li>
            <li class="list-group-item">
              <span class="badge bg-warning float-right"><%= rs.getString("Estado")%></span> Estado del Sitio:
            </li>
            
          </ul>
        </div>
        <div class="card-footer border-top-0">
         <form name="msitiose" action="sitio_solicitud.do" > 
             <button type="submit" class="btn btn-raised btn-outline-success round btn-min-width mr-1 mb-1">Solicitar</button>                                              
             <input name="id" style="visibility: hidden" value="<%= rs.getString("IdSitio")%>"/> 
                                                                            
                                                                        </form>
        </div>
      </div>
    </div>                                                     
                                                                            
   

                                                                            





                                                                        </tr> 

                                                                        <% }}%>
    

    
  </div>
</section>
                                                                
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>






          </div>
        </div>
        <!-- END : End Main Content-->

        <!-- BEGIN : Footer-->
        <footer class="footer footer-static footer-light">
          <p class="clearfix text-muted text-sm-center px-2"><span>Diseñado y Desarrollado Por Daniel Manrique & Alexander Jauregui  <a href="https://ingsistemas.cloud.ufps.edu.co/" id="pixinventLink" target="_blank" class="text-bold-800 primary darken-2">UFPS/ING DE SISTEMAS</a> </span></p>
        </footer>
        <!-- End : Footer-->

      </div>
    </div>
  
    <script src="app-assets/vendors/js/core/jquery-3.2.1.min.js" type="text/javascript"></script>
    <script src="app-assets/vendors/js/core/popper.min.js" type="text/javascript"></script>
    <script src="app-assets/vendors/js/core/bootstrap.min.js" type="text/javascript"></script>
    <script src="app-assets/vendors/js/perfect-scrollbar.jquery.min.js" type="text/javascript"></script>
    <script src="app-assets/vendors/js/prism.min.js" type="text/javascript"></script>
    <script src="app-assets/vendors/js/jquery.matchHeight-min.js" type="text/javascript"></script>
    <script src="app-assets/vendors/js/screenfull.min.js" type="text/javascript"></script>
    <script src="app-assets/vendors/js/pace/pace.min.js" type="text/javascript"></script>
    <!-- BEGIN VENDOR JS-->
    <!-- BEGIN PAGE VENDOR JS-->
    <script src="app-assets/vendors/js/chartist.min.js" type="text/javascript"></script>
    <!-- END PAGE VENDOR JS-->
    <!-- BEGIN APEX JS-->
    <script src="app-assets/js/app-sidebar.js" type="text/javascript"></script>
    <script src="app-assets/js/notification-sidebar.js" type="text/javascript"></script>
    <script src="app-assets/js/customizer.min.js" type="text/javascript"></script>
    <!-- END APEX JS-->
    <!-- BEGIN PAGE LEVEL JS-->
    <script src="app-assets/js/dashboard1.js" type="text/javascript"></script>
    <!-- END PAGE LEVEL JS-->
  </body>
  <!-- END : Body-->
</html>
