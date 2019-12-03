<%-- 
    Document   : index_eventos
    Created on : 27/11/2019, 10:54:52 AM
    Author     : Daniel
--%>
<%if (session.getAttribute("admin") == null) {
        response.sendRedirect("admin.jsp");
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
    <title>Agregar Sitio</title>
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


        <div id="menu_admin"> 
        
        </div>
        
      <div class="main-panel">
        <!-- BEGIN : Main Content-->
        <div class="main-content">
          <div class="content-wrapper"><!--Statistics cards Starts--><div class="row">
    <div class="col-md-12">
      <div class="card">
        <div class="card-header">
          <h4 class="card-title" id="from-actions-multiple">Agregar Sitio</h4>
          

          
        </div>
        <div class="card-body">
          <div class="px-3">
              
              <%String registrado=(String)request.getAttribute("nombre");
             if(registrado=="registrado"){%>
           <div class="alert alert-icon-left alert-success alert-dismissible mb-2" role="alert">
                  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                  <strong>Perfecto!</strong>Se ha registrado Correctamente. El Sitio
                  
                </div>
             <%}        
             if(registrado=="yaexiste"){%>
            <div class="alert alert-icon-left alert-danger alert-dismissible mb-2" role="alert">
                  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                <strong>Error!</strong> El  Nombre del sitio ya esta creado! <br>
                
                </div>
             <%}%>
           

              <form class="form" action="Registrar_Sitio.do"  method="Request">
              

              <div class="row justify-content-md-center">
                <div class="col-md-6">
                  <div class="form-body">
                    <div class="row">
                      <div class="form-group col-12 mb-2">
                        <label for="eventRegInput1">Nombre del Sitio</label>
                        <input type="text" id="eventRegInput1" class="form-control" placeholder="Nombre del Sitio" name="nsitio" required="">
                      </div>
                    </div>

                    <div class="row">
                      <div class="form-group col-12 mb-2">
                        <label for="eventRegInput2">Capacidad del Sitio:</label>
                         <input type="number" name="csitio" class="form-control mb-3" placeholder="Capacidad" required=""/>
                      </div>
                    </div>

                    

                    <div class="row">
                      <div class="form-group col-12 mb-2">
                        <label for="eventRegInput4"> Fecha de Inicio</label>
                        <input type="date" id="eventRegInput4" class="form-control" placeholder="fecha de inicio" name="fechasitio" required="">
                      </div>
                    </div>

                    <div class="row">
                      <div class="form-group col-12 mb-2">
                        <label for="eventRegInput5">Ubicacion</label>
                        <input type="text" id="eventRegInput5" class="form-control" name="ubicacion" placeholder="Ubicacion del Sitio " required="">
                      </div>
                    </div>

                    <div class="form-group">
                      <label for="projectinput5">Estado del Sitio :</label>
                      <select id="projectinput5" name="estado" class="form-control"required="">                        
                        <option value="Ocupado">Ocupado</option>
                        <option value="Libre">Libre</option>
                       
                      </select>
                    </div>
                      
                      <div class="row">
                      <div class="form-group col-12 mb-2">
                          <button type="submit" class="btn mb-1 btn-success btn-icon btn-lg btn-block"><i class="fa fa-check-circle"></i>
                    Agregar Sitio</button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
                
                
                
            </form>

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
