<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="utf-8" />
    <base href="/rep-portal/" />
    <title>Item Information</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimal-ui" />
    <meta name="apple-mobile-web-app-status-bar-style" content="yes" />
<!--     <link rel="shortcut icon" href="/assets/img/favicon.png" type="image/x-icon" /> -->

	<link rel="stylesheet" href="./css/tabcontent.css"/>
    <link rel="stylesheet" href="./css/mobile-angular-ui-hover.min.css" />
    <link rel="stylesheet" href="./css/mobile-angular-ui-desktop.min.css" />
    <link rel="stylesheet" href="./css/mobile-angular-ui-base.css" />
    <link rel="stylesheet" href="./css/rep-portal.css" />

    <link rel="stylesheet" href="./css/bootstrap.min.css" />
     <link rel="stylesheet" href="./css/bootstrap-theme.min.css" />
     

		
		
    <script src="./js/angular.min.js"></script>
    <script src="./js/angular-route.min.js"></script>
    <script src="./js/angular-animate.min.js"></script>
    <script src="./js/angular-sanitize.min.js"></script>
    <script src="./js/mobile-angular-ui.min.js"></script>
    <script src="./js/mobile-angular-ui.gestures.min.js"></script>

	<script src="./js/jquery-1.12.4.min.js"></script>
	<script src="./js/jquery.i18n.properties-min-1.0.9.js"></script>
	<script src="./js/bootstrap.min.js"></script>
    <script src="./js/ui-bootstrap-tpls-2.5.0.min.js"></script>
    <script src="./js/xml2json.js"></script>
    <script src="./js/rep-portal.js"></script>
    <!-- Global site tag (gtag.js) - Google Analytics -->
    <script async src="https://www.googletagmanager.com/gtag/js?id=UA-10685807-23"></script>
    <script>
        window.dataLayer = window.dataLayer || [];
        function gtag(){dataLayer.push(arguments);}
        gtag('js', new Date());

        gtag('config', 'UA-10685807-23');
    </script>
  </head>

  <body id="main"
    ng-app="rep-portal"
    ng-controller="MainController"
    ui-prevent-touchmove-defaults
    >

    <!-- Sidebars -->
    <div ng-include="'sidebar.html'"
            ui-track-as-search-param="true"
            class="sidebar sidebar-left"></div>

    <div ng-include="'sidebarRight.html'"
            class="sidebar sidebar-right"></div>

      <!-- Navbars -->
      
      <div class="navbar navbar-app navbar-absolute-top">
        <div class="navbar-brand navbar-brand-center" ui-yield-to="title">
          <b>Item Information</b>
        </div>
        <div  class="btn-group pull-left">
          <div  id="menuBtn" ui-toggle="uiSidebarLeft" class="btn sidebar-toggle">
            <i class="fa fa-bars"></i> <b>Menu</b>
          </div>
        </div>
        
<%--         <div class="btn-group pull-right">
          <div>
            <i class="fa fa-bars"></i> <a href="<c:url value="/logout" />"><b>Logout</b></a> 
          </div>
        </div> --%>
         <div class="btn-group pull-right" ui-yield-to="navbarAction">
          <div class="btn sidebar-right-toggle">
            <i class="fa"></i><a href="<c:url value="/logout" />"><b>Logout</b></a>
          </div>
        </div>
      </div>

    <!--<div class="navbar navbar-app navbar-absolute-bottom">
        <div class="btn-group justified">
          <a href="/docs" class="btn btn-navbar"><i class="fa fa-home fa-navbar"></i> Docs</a>
          <a href="https://github.com/mcasimir/mobile-angular-ui" class="btn btn-navbar"><i class="fa fa-github fa-navbar"></i> Sources</a>
          <a href="https://github.com/mcasimir/mobile-angular-ui/issues" class="btn btn-navbar"><i class="fa fa-exclamation-circle fa-navbar"></i> Issues</a>
        </div> 
      </div>-->
      
    <div class="app">



<!-- 	<div ng-controller="TooltipDemoCtrl">
 

    <div class="form-group" ng-class="{'has-error' : !inputModel}">
      <label>Disable tooltips conditionally:</label>
      <div><input type="text" ng-model="inputModel" class="form-control"
             placeholder="Product Code Search ..."
             uib-tooltip="Enter product code to find information"
             tooltip-placement="top"
             tooltip-trigger="'mouseenter'"
             tooltip-enable="!inputModel" />
             <button class="btn btn-default" ng-click="callRestService()">search</button>
       </div>
    </div>
    

</div> -->
<!-- put search bar here for product search -->


<search-Bar></search-Bar>
	
	<h3><span class="label label-info center-block">{{productTitle}}</span></h3>

	<span class="label label-warning center-block">{{message}}</span>
	
	<span class="label label-warning center-block">{{limit}}</span>
	
	<span class="label label-warning center-block">{{expire}}</span>
<!-- 	<div ng-controller="AlertDemoCtrl">
  
	  <div uib-alert ng-repeat="alert in alerts" ng-class="'alert-' + (alert.type || 'warning')" close="closeAlert($index)">{{alert.msg}}</div>
	
	  <button type="button" class='btn btn-default' ng-click="addAlert()">Add Alert</button>
	</div> -->
	

<!--
<div ng-controller="ModalDemoCtrl as $ctrl" class="modal-demo">
    <script type="text/ng-template" id="myModalContent.html">
        <div class="modal-header">
            <h3 class="modal-title" id="modal-title">I'm a modal!</h3>
        </div>
        <div class="modal-body" id="modal-body">
            <ul>
                <li ng-repeat="item in $ctrl.items">
                    <a href="#" ng-click="$event.preventDefault(); $ctrl.selected.item = item">{{ item }}</a>
                </li>
            </ul>
            Selected: <b>{{ $ctrl.selected.item }}</b>
        </div>
        <div class="modal-footer">
            <button class="btn btn-primary" type="button" ng-click="$ctrl.ok()">OK</button>
            <button class="btn btn-warning" type="button" ng-click="$ctrl.cancel()">Cancel</button>
        </div>
    </script>

    <button type="button" class="btn btn-default" ng-click="$ctrl.open()">Open me!</button>
 
</div> -->

<!-- 	
	  Modal
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      Modal content
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Alert</h4>
        </div>
        <div class="modal-body">
          <p>{{message}}</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div> -->
	
<!-- 	<div ng-controller="AccordionDemoCtrl">
	
	
	  <uib-accordion close-others="oneAtATime">
	    
	    <div uib-accordion-group class="panel-default" heading="Dynamic Body Content">
	      <p>The body of the uib-accordion group grows to fit the contents</p>
	
	    </div>
	    <div uib-accordion-group class="panel-default" heading="Dynamic Body Content2">
	      <p>The body of the uib-accordion group grows to fit the contents</p>
	
	    </div>
	  </uib-accordion>
	</div> -->

      <!-- App Body -->
      <div class="app-body"  ng-class="{loading: loading}">
      
        <div ng-show="loading" class="app-content-loading">
          <i class="fa fa-spinner fa-spin loading-spinner"></i>
        </div>
        <div class="app-content">
          <ng-view></ng-view>
        </div>
        
            
      </div>

 


    </div><!-- ~ .app -->

    <div ui-yield-to="modals"></div>

  </body>
</html>
