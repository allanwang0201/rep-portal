<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="/rep-portal/" />
<title>Error</title>
<link rel="stylesheet"
	href="bootstrap.css" />
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-inverse">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      <a class="navbar-brand" href="http://www.jaycar.com.au" href="#">Jaycar</a>
		    </div>
		    <ul class="nav navbar-nav">
		      <li class="active"><a href="<%=request.getContextPath()%>/">Home</a></li>
		
		      <li class="active"><a href="<%=request.getContextPath()%>/secure">Item Information</a></li>
		    </ul>
		  </div>
		</nav>
		<h1 th:text="${title}"></h1>
		<div id="created" th:text="${#dates.format(timestamp)}"></div>
		<div>
			There was an unexpected error (type=<span th:text="${error}">Bad</span>, status=<span th:text="${status}">500</span>).
		</div>
		<div th:text="${message}">Fake content</div>
		<div>
			Please contact the operator with the above information.
		</div>
		
	</div>
</body>
</html>
