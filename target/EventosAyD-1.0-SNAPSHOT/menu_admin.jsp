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
              <li class="has-sub nav-item"><a href="#"><i class="ft-home"></i><span data-i18n="" class="menu-title">Dashboard</span><span class="tag badge badge-pill badge-danger float-right mr-1 mt-1">2</span></a>
                <ul class="menu-content">
                  <li class="active"><a href="dashboard1.html" class="menu-item">Dashboard1</a>
                  </li>
                  <li><a href="dashboard2.html" class="menu-item">Dashboard2</a>
                  </li>
                </ul>
              </li>
              <li class=" nav-item"><a href="color-palette.html"><i class="ft-droplet"></i><span data-i18n="" class="menu-title">Color Palette</span></a>
              </li>
              <li class=" nav-item"><a href="inbox.html"><i class="ft-mail"></i><span data-i18n="" class="menu-title">Inbox</span></a>
              </li>
              <li class=" nav-item"><a href="chat.html"><i class="ft-message-square"></i><span data-i18n="" class="menu-title">Chat</span></a>
              </li>
              <li class=" nav-item"><a href="taskboard.html"><i class="ft-file-text"></i><span data-i18n="" class="menu-title">Task Board</span></a>
              </li>
              <li class=" nav-item"><a href="calendar.html"><i class="ft-calendar"></i><span data-i18n="" class="menu-title">Calendar</span></a>
              </li>
              <li class="has-sub nav-item"><a href="#"><i class="ft-aperture"></i><span data-i18n="" class="menu-title">UI Kit</span></a>
                <ul class="menu-content">
                  <li><a href="grids.html" class="menu-item">Grid</a>
                  </li>
                  <li><a href="typography.html" class="menu-item">Typography</a>
                  </li>
                  <li><a href="syntax-highlighter.html" class="menu-item">Syntax Highlighter</a>
                  </li>
                  <li><a href="helper-classes.html" class="menu-item">Helper Classes</a>
                  </li>
                  <li><a href="text-utilities.html" class="menu-item">Text Utilities</a>
                  </li>
                  <li class="has-sub"><a href="#" class="menu-item">Icons</a>
                    <ul class="menu-content">
                      <li><a href="feather.html" class="menu-item">Feather Icon</a>
                      </li>
                      <li><a href="font-awesome.html" class="menu-item">Font Awesome Icon</a>
                      </li>
                      <li><a href="simple-line.html" class="menu-item">Simple Line Icon</a>
                      </li>
                    </ul>
                  </li>
                </ul>
              </li>
              
              
              <li class="has-sub nav-item"><a href="#"><i class="ft-grid"></i><span data-i18n="" class="menu-title">Tables</span></a>
                <ul class="menu-content">
                  <li><a href="regular-table.html" class="menu-item">Regular</a>
                  </li>
                  <li><a href="extended-table.html" class="menu-item">Extended</a>
                  </li>
                </ul>
              </li>
              
              
              
              
              
              <li class=" nav-item"><a href="https://pixinvent.com/apex-angular-4-bootstrap-admin-template/documentation"><i class="ft-book"></i><span data-i18n="" class="menu-title">Documentation</span></a>
              </li>
              <li class=" nav-item"><a href="https://pixinvent.ticksy.com/"><i class="ft-life-buoy"></i><span data-i18n="" class="menu-title">Support</span></a>
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
                                                 dropdown-toggle"><%=(String)session.getAttribute("admin")%> <i class="ft-user 
                         font-medium-3 blue-grey darken-4"></i>
                    <p class="d-none">User Settings</p></a>
                  <div ngbdropdownmenu="" aria-labelledby="dropdownBasic3" 
                       class="dropdown-menu text-left dropdown-menu-right">
                      
                      <div class="dropdown-divider">
                          
                      </div><a href="Logout_Admin.do"        class="dropdown-item">
                          <i  class="ft-power mr-2"></i><span>Logout</span></a>
                  </div>
                </li>
                
              </ul>
            </div>
          </div>
        </div>
      </nav>
   