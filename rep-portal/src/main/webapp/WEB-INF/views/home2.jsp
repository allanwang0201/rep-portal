<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="/rep-portal/" />
<title text="${title}">Title</title>
<link rel="stylesheet"
	href="./css/bootstrap.css" />
</head>
<body>
	<div class="container">
	<% 
String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
String error = request.getParameter("error");
if(error!=null)
	error = "username password is incorrect";
else
	error = "";

%> 
		
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="http://www.jaycar.com.au" href="#">Jaycar</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="<%=request.getContextPath()%>/">Home</a></li>

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
		
				<div class="content">
		<%-- 	<p if="${param.logout}" class="alert">You have been logged out</p>
			<p if="${param.error}" class="alert alert-error">There was an error, please try again</p> --%>
			<h2>Login with Username and Password</h2>
			<form name="form" action="<%=request.getContextPath()%>/login" method="POST">
				<fieldset>
				  <%--   <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> --%>
					<input type="text" name="username" value="" placeholder="Username" />
					<input type="password" name="password" placeholder="Password" />
				</fieldset>
				<br>
				<input type="submit" id="login" value="Login"
					class="btn btn-primary" />
			</form>
		</div>
		
<%-- 		<div id="footer">
			<p>
				Logged in as: <span text="${username}"></span>, Roles: <span text="${userroles}"></span>
			</p>
		</div> --%>
	</div>
</body>
</html>
