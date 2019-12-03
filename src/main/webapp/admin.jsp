<%-- 
    Document   : admin
    Created on : 27/11/2019, 04:47:24 PM
    Author     : Daniel
--%>
<%if (session.getAttribute("admin") != null) {
        response.sendRedirect("index_admin.jsp");
    }%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="loading">
  <!-- BEGIN : Head-->
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <meta name="description" content="Apex admin is super flexible, powerful, clean &amp; modern responsive bootstrap 4 admin template with unlimited possibilities.">
    <meta name="keywords" content="admin template, Apex admin template, dashboard template, flat admin template, responsive admin template, web app">
    <meta name="author" content="PIXINVENT">
    <title>Admin</title>
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
    <!-- END VENDOR CSS-->
    <!-- BEGIN APEX CSS-->
    <link rel="stylesheet" type="text/css" href="app-assets/css/app.css">
    <!-- END APEX CSS-->
    <!-- BEGIN Page Level CSS-->
    <!-- END Page Level CSS-->
  </head>
  <!-- END : Head-->

  <!-- BEGIN : Body-->
  <body data-col="1-column" class=" 1-column  blank-page">
    <!-- ////////////////////////////////////////////////////////////////////////////-->
    <div class="wrapper nav-collapsed menu-collapsed">
      <div class="main-panel">
        <!-- BEGIN : Main Content-->
        <div class="main-content">
          <div class="content-wrapper"><!--Lock Screen Starts-->
<section id="lock-screen">
  <div class="container-fluid forgot-password-bg">
    <div class="row full-height-vh m-0">
      <div class="col-12 d-flex align-items-center justify-content-center">
        <div class="card">
          <div class="card-content">
            <div class="card-body lock-screen-img">
              <div class="row m-0">
                <div class="col-lg-6 d-lg-block d-none text-center">
                  <img src="app-assets/img/gallery/lock.png" alt="" class="img-fluid" height="230" width="400">
                </div>
                <div class="col-lg-6 col-md-12 pt-3 px-4 bg-white">
                  <h4 class="card-title mb-3">
                   ADMINISTRADOR
                  </h4>
                    <%String registrado=(String)request.getAttribute("nombre");
             if(registrado=="registrado"){%>
           <div class="alert alert-icon-left alert-success alert-dismissible mb-2" role="alert">
                  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                  <strong>Perfecto!</strong>Se ha registrado Correctamente. Ahora Ingresa
                  
                </div>
             <%}        
             if(registrado=="NoExiste"){%>
            <div class="alert alert-icon-left alert-danger alert-dismissible mb-2" role="alert">
                  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                <strong>Error!</strong> El administrador no existe 
                
                </div>
             <%}  if(registrado=="Contraerror"){%>
            <div class="alert alert-icon-left alert-danger alert-dismissible mb-2" role="alert">
                  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                  <strong>Error!</strong> La Contraseña No es Correcta
                
                </div>
             <%}%>
             <form name="login_admin" action="Login_Admin.do" method="Request" >
                        <input type="text" name="email" class="form-control mb-3" placeholder="Email" />
                        <input type="password" name="pass" class="form-control mb-3" placeholder="password" />
                  <div class="fg-actions d-flex justify-content-between">
                    <div class="login-btn">
                    
                    </div>
                    <div class="recover-pass">
                        <button type="submit" class="btn btn-primary">
                        <a class="text-decoration-none text-white">
                          INGRESAR
                        </a>
                      </button>
                    </div>
                  </div>
                    </form>
              <a href="index.jsp"><button  type="button" class="btn btn-raised btn-outline-primary"><i class="fa fa-user-o"></i>
                      Inicio</button></a>  
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
<!--Lock Screen Ends-->

          </div>
        </div>
        <!-- END : End Main Content-->
      </div>
    </div>
    <!-- ////////////////////////////////////////////////////////////////////////////-->

    <!-- BEGIN VENDOR JS-->
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
    <!-- END PAGE VENDOR JS-->
    <!-- BEGIN APEX JS-->
    <script src="app-assets/js/app-sidebar.js" type="text/javascript"></script>
    <script src="app-assets/js/notification-sidebar.js" type="text/javascript"></script>
    <script src="app-assets/js/customizer.js" type="text/javascript"></script>
    <!-- END APEX JS-->
    <!-- BEGIN PAGE LEVEL JS-->
    <!-- END PAGE LEVEL JS-->
  </body>
  <!-- END : Body-->
</html>
