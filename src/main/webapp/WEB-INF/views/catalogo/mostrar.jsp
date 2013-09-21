<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>
<html>
<head>
	<title>Catálogo de cursos: mostrar</title>
</head>
<body>
<h1>
	Mostrar curso
</h1>
<c:if test="${not empty mensaje}">
	<h3>${mensaje}</h3>
</c:if>
<c:if test="${not empty curso}">
	<table border="1">
		<tbody>
			<tr>
				<td>Activo</td>
				<td>${curso.activo}</td>
			</tr>
			<tr>
				<td>Profesor</td>
				<td>${curso.profesor.nombre}</td>
			</tr>
			<tr>
				<td>Título</td>
				<td>${curso.titulo}</td>
			</tr>
			<tr>
				<td>Nivel</td>
				<td>${curso.nivel}</td>
			</tr>
			<tr>
				<td>Horas</td>
				<td>${curso.horas}</td>
			</tr>
		</tbody>
	</table>
</c:if>
<br>
<spring:url value="/" var="home"/>
<span>
	<a href="${home}">
		HOME
	</a>
</span>
</body>