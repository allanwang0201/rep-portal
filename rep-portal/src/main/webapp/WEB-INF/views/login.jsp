<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="/rep-portal/" />
<title text="${title}">Title</title>
<link rel="stylesheet" href="./css/bootstrap.css" />

<style type="text/css">  
            body{background-size:cover;font-size: 16px;}  
            .form{background: rgba(255,255,255,0.2);width:90%; margin:100px auto;}  
            #login_form{display: block;}  
            .fa{display: inline-block;top: 27px;left: 6px;position: relative;color: #ccc;}
            
            .input-lg {height: 60%; width:100%; padding: 10px 16px; line-height: 1.33; border-radius: 6px; padding-left:26px;}
            
            
        </style>  
</head>
<body>
	<div>
		<% 
			String path = request.getContextPath(); 
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
			String error = request.getParameter("error");
			if(error!=null)
				error = "Username password is incorrect";
			else
				error = "";
	
		%> 
		
		<nav class="navbar navbar-inverse">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      <a class="navbar-brand" href="http://www.jaycar.com.au" href="#">Jaycar</a>
		    </div>
		    <ul class="nav navbar-nav">
		
		
		      <li class="active"><a href="<%=request.getContextPath()%>/">Item Information</a></li>
		    </ul>
		  </div>
		</nav>
				
		
		<div id="content">
			<p>
				<h1 text="${title}"></h1>
				<h2 text="${message}"></h2>
			</p>
		</div>
        <div class="container">
            <div class="form row">

                <form class="form-horizontal col-sm-offset-3 col-md-offset-3" action="<%=request.getContextPath()%>/login" method="POST" id="login_form">
                    <h4 class="form-title" style="color:red;"><%=error%></h4>
                    <h3 class="form-title">Login to your account</h3>
                    <div class="col-xs-9 col-xs-9">
                        <div class="form-group">
                            <i class="fa fa-user fa-lg"></i>
                            <input class="form-control  input-lg required" type="text" placeholder="Username" name="username" autofocus="autofocus"/>
                        </div>
                        <div class="form-group">
                                <i class="fa fa-lock fa-lg"></i>
                                <input class="form-control input-lg required" type="password" placeholder="Password" name="password"/>
                        </div>
                        <div class="form-group">
                            <%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> --%>
                            <hr />

                        </div>
                        <div class="form-group">
                            <input type="submit" class="btn btn-primary input-submit pull-left" value="Login "/>
                        </div>
                    </div>
                </form>
            </div>
        </div>



</body>
</html>
