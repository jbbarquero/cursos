App = Ember.Application.create();

App.Router.map(function() {
	this.resource('about');
	this.resource('courses', function() {
		this.resource('course', { path: ':course_id' });
	});
});

App.CoursesRoute = Ember.Route.extend({
	model: function() {
		return courses;
	}
});

var courses = [{
	id: '1',
	titulo: "Introduccion a JSF2",
	nivel: "Intermedio",
	horas: 25,
	activo: true,
	profesor: { id: '1', nombre: "Roberto Canales Mora" }
}, {	
	id: '2',
	titulo: "Novedades en Spring 3",
	nivel: "Intermedio",
	horas: 16,
	activo: true,
	profesor: { id: '2', nombre: "Javier Beneito Barquero" }
}, {	
	id: '3',
	titulo: "Filostros y Forlayos in a Nuthsell",
	nivel: "Basico",
	horas: 10,
	activo: false,
	profesor: { id: '3', nombre: "Ingeniero Fuckwosky" }
}];

