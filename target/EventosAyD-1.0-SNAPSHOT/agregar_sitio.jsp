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
          <h4 class="card-title" id="from-actions-multiple">Event Registration</h4>
          

          
        </div>
        <div class="card-body">
          <div class="px-3">

            <form class="form">
              <div class="form-actions top clearfix">
                <div class="buttons-group float-left">
                  <button type="button" class="btn btn-raised btn-warning mr-1">
                    <i class="ft-x"></i> Cancel
                  </button>
                  <button type="button" class="btn btn-raised btn-primary mr-1">
                    <i class="fa fa-check-square-o"></i> Save
                  </button>
                </div>

                <div class="buttons-group float-right">
                  <button type="button" class="btn btn-raised btn-info mr-1">Action 1</button>
                  <button type="button" class="btn btn-raised btn-danger mr-1">Action 2</button>
                  <button type="button" class="btn btn-raised btn-success">Action 3</button>
                </div>
              </div>

              <div class="row justify-content-md-center">
                <div class="col-md-6">
                  <div class="form-body">
                    <div class="row">
                      <div class="form-group col-12 mb-2">
                        <label for="eventRegInput1">Full Name</label>
                        <input type="text" id="eventRegInput1" class="form-control" placeholder="name" name="fullname">
                      </div>
                    </div>

                    <div class="row">
                      <div class="form-group col-12 mb-2">
                        <label for="eventRegInput2">Title</label>
                        <input type="text" id="eventRegInput2" class="form-control" placeholder="title" name="title">
                      </div>
                    </div>

                    <div class="row">
                      <div class="form-group col-12 mb-2">
                        <label for="eventRegInput3">Company</label>
                        <input type="text" id="eventRegInput3" class="form-control" placeholder="company" name="company">
                      </div>
                    </div>

                    <div class="row">
                      <div class="form-group col-12 mb-2">
                        <label for="eventRegInput4">Email</label>
                        <input type="email" id="eventRegInput4" class="form-control" placeholder="email" name="email">
                      </div>
                    </div>

                    <div class="row">
                      <div class="form-group col-12 mb-2">
                        <label for="eventRegInput5">Contact Number</label>
                        <input type="number" id="eventRegInput5" class="form-control" name="contact" placeholder="contact number">
                      </div>
                    </div>

                    <div class="row">
                      <div class="form-group col-12 mb-2">
                        <label>Existing Customer</label>
                        <div class="input-group">
                          <div class="custom-control custom-radio d-inline-block ml-1">
                            <input type="radio" id="customRadioInline1" name="customRadioInline1" class="custom-control-input">
                            <label class="custom-control-label" for="customRadioInline1">Yes</label>
                          </div>
                          <div class="custom-control custom-radio d-inline-block ml-2">
                            <input type="radio" id="customRadioInline2" checked name="customRadioInline1" class="custom-control-input">
                            <label class="custom-control-label" for="customRadioInline2">No</label>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <div class="form-actions clearfix">
                <div class="buttons-group float-left">
                  <button type="button" class="btn btn-raised btn-warning mr-1">
                    <i class="ft-x"></i> Cancel
                  </button>
                  <button type="button" class="btn btn-raised btn-primary mr-1">
                    <i class="fa fa-check-square-o"></i> Save
                  </button>
                </div>

                <div class="buttons-group float-right">
                  <button type="button" class="btn btn-raised btn-info mr-1">Action 1</button>
                  <button type="button" class="btn btn-raised btn-danger mr-1">Action 2</button>
                  <button type="button" class="btn btn-raised btn-success">Action 3</button>
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
