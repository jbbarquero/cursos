<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>
<html>
<head>
	<title>Catálogo de cursos: crear</title>
</head>
<body>
<h1>
	Crear curso
</h1>
<spring:url value="/catalogo?form" var="form_url"/>
<form:form action="${form_url}" modelAttribute="curso" id="cursoForm" method="post">
<c:if test="${not empty mensaje}">
	<h3>${mensaje}</h3>
</c:if>
	<table border="1">
		<tbody>
			<tr>
				<td>
					<form:label path="activo">Activo</form:label>
					<div>
						<form:errors path="activo" cssStyle="color:red" />
					</div>
				</td>
				<td><form:checkbox path="activo"/></td>
			</tr>
			<tr>
				<td>
					<form:label path="profesor">Profesor</form:label>
					<div>
						<form:errors path="profesor" cssStyle="color:red" />
					</div>
				</td>
				<td><form:select path="profesor" items="${profesores}" itemValue="id" itemLabel="nombre" /></td>
			</tr>
			<tr>
				<td>
					<form:label path="titulo">Título</form:label>
					<div>
						<form:errors path="titulo" cssStyle="color:red" />
					</div>
				</td>
				<td><form:input path="titulo"/></td>
			</tr>
			<tr>
				<td>
					<form:label path="nivel">Nivel</form:label>
					<div>
						<form:errors path="nivel" cssStyle="color:red" />
					</div>
				</td>
				<td><form:input path="nivel"/></td>
			</tr>
			<tr>
				<td>
					<form:label path="horas">Horas</form:label>
					<div>
						<form:errors path="horas" cssStyle="color:red" />
					</div>
				</td>
				<td><form:input path="horas"/></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" name="submit" value="Añadir"/>
					<input type="reset" name="reset" value="Reset"/>
				</td>
			</tr>
		</tbody>
	</table>
</form:form>
<br>
<spring:url value="/" var="home"/>
<span>
	<a href="${home}">
		HOME
	</a>
</span>
</body>