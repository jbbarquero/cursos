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
		
		<h2>Get By ID</h2>
		<spring:url value="/catalogo/curso/" var="curso_by_id_url"/>
		<form id="cursoByIdForm">
			<label for="curso_id">Curso ID:</label>
			<input id="curso_id" name="id" type="number" value="1" />
			<input type="submit" value="Buscar" />
			<br/><br/>
			<div id="cursoByIdResult"> </div>
		</form>
		
	</div>
	
<spring:url value="/" var="home"/>
<span>
	<a href="${home}">
		<spring:message code="messages_button_home"/>
	</a>
</span>
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			$('#cursoByIdForm').submit(function(e) {
				var cursoId = $('#curso_id').val();
				$.ajax({
					type: 'get',
					url: '${curso_by_id_url}'+cursoId,
					contentType: "application/json",
					success: function(curso) {
						if ($.isEmptyObject(curso)){
							$('#cursoByIdResult').text('No encontrado');
						}
						else {
							$('#cursoByIdResult').text(curso.titulo + ' (' + curso.horas + ' horas). Impartido por ' + curso.profesor.nombre + ' [activo: ' + curso.activo + ']');
						}
					},
					error: function (e) {
						$('#cursoByIdResult').text('La jodimos: '+e);
					} 
				});
// 				$.getJSON('${curso_by_id_url}'+cursoId, function(curso) {
// 					$('#cursoByIdResult').text(curso.titulo);
// 				});
				e.preventDefault(); // prevent actual form submit
			});
			
		});
		
	</script>
	
</body>