<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Cursos JQUERY</title>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
	<div class="container" style="padding-top: 50px;">
	
		<h1>Cursos JQUERY</h1>
		
		<h2>Buscar por ID</h2>
		<spring:url value="/catalogo/curso/" var="curso_by_id_url"/>
		<form id="cursoByIdForm">
			<label for="curso_id">Curso ID:</label>
			<input id="curso_id" name="id" type="number" value="1" />
			<input type="submit" value="Buscar" />
			<br/><br/>
			<div id="cursoByIdResult"> </div>
		</form>
		
		<hr/>
		
		<h2>Crear curso</h2>
		<spring:url value="/catalogo/create" var="crear_curso_url"/>
		<form:form action="${crear_curso_url}" modelAttribute="curso" id="createCursoForm" method="post">
			<br/>
			<form:label	id="activoLabel" for="activo" path="activo" cssErrorClass="color:red">Activo</form:label>
			<form:checkbox path="activo"/><form:errors path="activo" />
			<br/>
			<form:label id="profesorLabel" for="profesor" path="profesor" cssErrorClass="color:red">Profesor</form:label>
			<form:select path="profesor" items="${profesores}" itemValue="id" itemLabel="nombre" /><form:errors path="profesor" />
			<br/>
			<form:label id="tituloLabel" for="titulo" path="titulo" cssErrorClass="color:red">Título</form:label>
			<form:input path="titulo"/><form:errors path="titulo" />
			<br/>
			<form:label id="nivelLabel" for="nivel" path="nivel" cssErrorClass="color:red">Nivel</form:label>
			<form:select path="nivel">
				<c:forEach items="${niveles}" var="nivel">
					<spring:message code="messages_level_${nivel}" var="label"/>
					<form:option value="${nivel}" label="${label}"/>
				</c:forEach>
			</form:select><form:errors path="nivel" />
			<br/>
			<form:label id="horasLabel" for="horas" path="horas" cssErrorClass="color:red">Horas</form:label>
			<form:input path="horas"/><form:errors path="horas" />
			<br/>
			<input id="crear" type="submit" value="Crear" />
			<br/></br>
			<div id="crearCursoResult"> </div>
		</form:form>
		
	</div>
	
<spring:url value="/" var="home"/>
<span>
	<a href="${home}">
		<spring:message code="messages_button_home"/>
	</a>
</span>
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			// Buscar Curso por ID usando AJAX
			$('#cursoByIdForm').submit(function(e) {
				var cursoId = $('#curso_id').val();
// 				$.ajax({
// 					type: 'get',
// 					url: '${curso_by_id_url}'+cursoId,
// 					contentType: "application/json",
// 					success: function(curso) {
// 						if ($.isEmptyObject(curso)){
// 							$('#cursoByIdResult').text('No encontrado');
// 						}
// 						else {
// 							$('#cursoByIdResult').text(curso.titulo + ' (' + curso.horas + ' horas). Impartido por ' + curso.profesor.nombre + ' [activo: ' + curso.activo + ']');
// 						}
// 					},
// 					error: function (e) {
// 						$('#cursoByIdResult').text('La jodimos: '+e);
// 					} 
// 				});
				$.getJSON('${curso_by_id_url}'+cursoId, function(curso) {
					if ($.isEmptyObject(curso)){
						$('#cursoByIdResult').text('Curso: no encontrado');
					}
					else {
						$('#cursoByIdResult').text('Curso: ' + curso.titulo + ' (' + curso.horas + ' horas). Impartido por ' + curso.profesor.nombre + ' [activo: ' + curso.activo + ']');
					}
				});
				e.preventDefault(); // prevent actual form submit
			});
			
			// Crear Curso usando AJAX
			$('#createCursoForm').submit(function(e) {
//				Usar cuando serialices bien el profesor
// 				var curso = $(this).serializeObject();
// 				$.postJSON('${crear_curso_url}', curso, function (response) {
// 					$('#crearCursoResult').text(response);
// 				});
				var curso = $(this).serialize();
				$.post('${crear_curso_url}', curso, function(response) {
					$('#crearCursoResult').text('Creado curso con id '+response.id+': '+response.titulo);
				});
				e.preventDefault(); // prevent actual form submit
			});
		});
		
		$.fn.serializeObject = function() {
		    var o = {};
		    var a = this.serializeArray();
		    $.each(a, function() {
		        if (o[this.name]) {
		            if (!o[this.name].push) {
		                o[this.name] = [o[this.name]];
		            }
		            o[this.name].push(this.value || '');
		        } else {
		            o[this.name] = this.value || '';
		        }
		    });
		    return o;
		};
		
		$.postJSON = function(url, data, callback) {
			return jQuery.ajax({
				'type': 'POST',
				'url': url,
				'contentType': 'application/json',
				'data': JSON.stringify(data),
				'dataType': 'json',
				'success': callback
		    });
		};
		
	</script>
	
</body>