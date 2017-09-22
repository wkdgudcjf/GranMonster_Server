<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
         
 <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
      <a class="navbar-brand" href="/admin"><img src="/img/logo.png"></a>
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
          <li id="navi_dash" class="nav-item" data-toggle="tooltip" data-placement="right" title="Dashboard">
            <a class="nav-link" href="/admin">
              <i class="fa fa-fw fa-dashboard"></i>
              <span class="nav-link-text">
                Dashboard</span>
            </a>
          </li>
          <li id="navi_app" class="nav-item" data-toggle="tooltip" data-placement="right" title="registapp">
            <a class="nav-link" href="/managementapp">
              <i class="fa fa-fw fa-area-chart"></i>
              <span class="nav-link-text">
              	 앱 관리</span>
            </a>
          </li>
          <li id="navi_company" class="nav-item" data-toggle="tooltip" data-placement="right" title="registcompany">
            <a class="nav-link" href="/managementcompany">
              <i class="fa fa-fw fa-table"></i>
              <span class="nav-link-text">
                 	회사 관리</span>
            </a>
          </li>
           <li id="navi_user" class="nav-item" data-toggle="tooltip" data-placement="right" title="registcompany">
            <a class="nav-link" href="/managementuser">
              <i class="fa fa-fw fa-table"></i>
              <span class="nav-link-text">
                 	회원 관리</span>
            </a>
          </li>
           <li id="navi_billing" class="nav-item" data-toggle="tooltip" data-placement="right" title="registcompany">
            <a class="nav-link" href="/managementbilling">
              <i class="fa fa-fw fa-table"></i>
              <span class="nav-link-text">
                 	영수증 관리</span>
            </a>
          </li>
          <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Menu Levels">
            <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseMulti" data-parent="#exampleAccordion">
              <i class="fa fa-fw fa-sitemap"></i>
              <span class="nav-link-text">
                Menu Levels</span>
            </a>
            <ul class="sidenav-second-level collapse" id="collapseMulti">
              <li>
                <a href="#">Second Level Item</a>
              </li>
              <li>
                <a href="#">Second Level Item</a>
              </li>
              <li>
                <a class="nav-link-collapse collapsed" data-toggle="collapse" href="#collapseMulti2">Third Level</a>
                <ul class="sidenav-third-level collapse" id="collapseMulti2">
                  <li>
                    <a href="#">Third Level Item</a>
                  </li>
                  <li>
                    <a href="#">Third Level Item</a>
                  </li>
                </ul>
              </li>
            </ul>
          </li>
        </ul>
        <ul class="navbar-nav sidenav-toggler">
          <li class="nav-item">
            <a class="nav-link text-center" id="sidenavToggler">
              <i class="fa fa-fw fa-angle-left"></i>
            </a>
          </li>
        </ul>
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a class="nav-link" data-toggle="modal" data-target="#LogoutModal">
              <i class="fa fa-fw fa-sign-out"></i>
              Logout</a>
          </li>
        </ul>
      </div>
    </nav>