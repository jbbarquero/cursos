<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<h2>Courses</h2>
<p>Let's do some fun stuff with courses</p>
<ul>
	<spring:url value="/catalogo" var="catalogo_url"/>
 	<li><a href="${catalogo_url}">Catálogo</a></li>
	<spring:url value="/catalogo/1" var="ver_1_url"/>
 	<li><a href="${ver_1_url}">Ver el primer curso</a></li>
	<spring:url value="/catalogo?form" var="crear_curso_url"/>
 	<li><a href="${crear_curso_url}">Crear curso</a></li>
</ul>
</body>
</html>
