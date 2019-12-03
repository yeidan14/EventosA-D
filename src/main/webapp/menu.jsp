<%-- 
    Document   : Menu
    Created on : 27/11/2019, 11:18:46 AM
    Author     : Daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <div data-active-color="white" data-background-color="purple-bliss" data-image="app-assets/img/sidebar-bg/01.jpg" class="app-sidebar">
        <!-- main menu header-->
        <!-- Sidebar Header starts-->
        <div class="sidebar-header">
          <div class="logo clearfix"><a href="#" class="logo-text float-left">
                  <div class="logo-img"><img src="app-assets/img/logo.png" width="120%"/></div><span class="text align-middle">EVENTOS</span></a><a id="sidebarClose" href="javascript:;" class="nav-close d-block d-md-block d-lg-none d-xl-none"><i class="ft-x"></i></a></div>
        </div>
        <!-- Sidebar Header Ends-->
        <!-- / main menu header-->
        <!-- main menu content-->
        <div class="sidebar-content">
          <div class="nav-container">
            <ul id="main-menu-navigation" data-menu="menu-navigation" data-scroll-to-active="true" class="navigation navigation-main">
             
              
              
              <li class=" nav-item"><a href="sitios_user.jsp"><i class="ft-book"></i><span data-i18n="" class="menu-title">Sitios</span></a>
              </li>
              <li class=" nav-item"><a href="solicitud_user.jsp"><i class="ft-life-buoy"></i><span data-i18n="" class="menu-title">Solicitudes</span></a>
              </li>
            </ul>
          </div>
        </div>
        <!-- main menu content-->
        <div class="sidebar-background"></div>
        <!-- main menu footer-->
        <!-- include includes/menu-footer-->
        <!-- main menu footer-->
      </div>
        
        <nav class="navbar navbar-expand-lg navbar-light bg-faded header-navbar">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" data-toggle="collapse" class="navbar-toggle d-lg-none float-left"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button><span class="d-lg-none navbar-right navbar-collapse-toggle"><a aria-controls="navbarSupportedContent" href="javascript:;" class="open-navbar-container black"><i class="ft-more-vertical"></i></a></span>
            <form role="search" class="navbar-form navbar-right mt-1">
              <div class="position-relative has-icon-right">
                
                <div class="form-control-position"></div>
              </div>
            </form>
          </div>
          <div class="navbar-container">
            <div id="navbarSupportedContent" class="collapse navbar-collapse">
              <ul class="navbar-nav">
                <li class="nav-item mr-2 d-none d-lg-block"><a id="navbar-fullscreen" href="javascript:;" class="nav-link apptogglefullscreen"><i class="ft-maximize font-medium-3 blue-grey darken-4"></i>
                    <p class="d-none">fullscreen</p></a></li>
               
                
                <li class="dropdown nav-item"><a id="dropdownBasic3"
                                                 href="#" data-toggle="dropdown"
                                                 class="nav-link position-relative 
                                                 dropdown-toggle"><%=(String)session.getAttribute("usuario")%> <i class="ft-user 
                         font-medium-3 blue-grey darken-4"></i>
                    <p class="d-none">User Settings</p></a>
                  <div ngbdropdownmenu="" aria-labelledby="dropdownBasic3" 
                       class="dropdown-menu text-left dropdown-menu-right">
                      
                      <div class="dropdown-divider">
                          
                      </div><a href="Logout.do"        class="dropdown-item">
                          <i  class="ft-power mr-2"></i><span>Logout</span></a>
                  </div>
                </li>
                
              </ul>
            </div>
          </div>
        </div>
      </nav>
   