<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Autentia's courses</title>
	<spring:url value="/resources/images/favicon.ico" var="favicon" />
	<link rel="shortcut icon" href="${favicon}" />
	<spring:url value="/resources/css/normalize.css" var="url_css_normalize"/>
	<link rel="stylesheet" href="${url_css_normalize}">
	<spring:url value="/resources/css/style.css" var="url_css_style"/>
	<link rel="stylesheet" href="${url_css_style}">
	<link rel="stylesheet" href="http://netdna.bootstrapcdn.com//twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css">
</head>
<body>

	<script type="text/x-handlebars">
		<div class="navbar">
			<div class="navbar-inner">
				<a class="brand" href="#">Autentia</a>
				<ul class="nav">
					<li>{{#link-to 'courses'}}Cursos{{/link-to}}</li>
					<li>{{#link-to 'about'}}About{{/link-to}}</li>
				</ul>
			</div>
		</div>

		{{outlet}}

	</script>
	
	<script type="text/x-handlebars" id="about">
		<h1>About</h1>
	</script>
	
	<script type="text/x-handlebars" id="courses">
    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">
          <table class="table">
            <thead>
              <tr><th>Catálogo de Cursos</th></tr>
            </thead>
            {{#each}}
            <tr><td>
              {{#link-to 'course' this}}
                {{titulo}} <small class='muted'>by {{profesor.nombre}}</small>
              {{/link-to}}
            </td></tr>
            {{/each}}
          </table>
        </div>
        <div class="span9">
          {{outlet}}
        </div>
      </div>
    </div>
	</script>
	
	<script type="text/x-handlebars" id="course">
		<h1>{{titulo}}</h1>
		<h2>by {{profesor.nombre}} 
			<small class='muted'>({{#if activo}}activo{{else}}inactivo{{/if}})</small>
		</h2>

		<hr>

		<div class='intro'>
			{{titulo}}
		</div>

		<div class='intro'>
			{{nivel}}. {{horas}} horas
		</div>
	</script>
	
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<spring:url value="/resources/js/libs/handlebars-1.0.0.js" var="url_js_handlebars"/>
	<script src="${url_js_handlebars}"></script>
	<spring:url value="/resources/js/libs/ember-1.0.0.js" var="url_js_ember"/>
	<script src="${url_js_ember}"></script>
	<script src="http://cdnjs.cloudflare.com/ajax/libs/showdown/0.3.1/showdown.min.js"></script>
	<script src="http://cdnjs.cloudflare.com/ajax/libs/moment.js/2.2.1/moment.min.js"></script>
	<spring:url value="/resources/js/libs/ember-1.0.0.js" var="url_js_ember"/>
	<spring:url value="/resources/js/app.js" var="url_js_ember_app"/>
	<script src="${url_js_ember_app}"></script>
	
</body>
</html>
