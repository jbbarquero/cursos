<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<table border="1">
		<thead>
			<tr>
				<th>Título</th>
				<th>Nivel</th>
				<th>Horas</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${cursos}" var="curso">
				<tr>
					<td>${curso.titulo}</td>
					<td>${curso.nivel}</td>
					<td>${curso.horas}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>

</body>
</html>
