<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>
<html>
<head>
	<title>Catálogo de cursos: todos</title>
</head>
<body>
<h1>
	Todos los cursos
</h1>

<c:if test="${not empty cursos}">
<spring:url value="/catalogo" var="showCursosUrl"/>
	<table border="1">
		<thead>
			<tr>
				<th>Título</th>
				<th>Nivel</th>
				<th>Horas</th>
				<th>Activo</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${cursos}" var="curso">
				<tr>
					<td><a href="${showCursosUrl}/${curso.id}">${curso.titulo}</a></td>
					<td><spring:message code="messages_level_${curso.nivel}"/></td>
					<td>${curso.horas}</td>
					<td>${curso.activo}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>

</body>
</html>
<br>
<spring:url value="/" var="home"/>
<span>
	<a href="${home}">
		<spring:message code="messages_button_home"/>
	</a>
</span>
</body>