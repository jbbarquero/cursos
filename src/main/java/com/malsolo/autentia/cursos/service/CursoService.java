package com.malsolo.autentia.cursos.service;

import java.util.List;

import com.malsolo.autentia.cursos.domain.Curso;
import com.malsolo.autentia.cursos.domain.CursoPage;
import com.malsolo.autentia.cursos.persistence.OrderType;


public interface CursoService {
	
	/**
	 * Como usuario, quiero poder acceder al catálogo de cursos disponibles en el sistema:
	 * <ul>
	 * <li>Sólo se mostrarán los cursos marcados como activos</li>
	 * <li>El listado mostrará el título del curso, el nivel y el número de horas del mismo</li>
	 * <li>Se podrá ordenar la tabla por la columna del título de curso</li>
	 * <li>El listado será paginado</li>
	 * </ul>
	 * @param orderType el tipo de orden, para título por ahora.
	 * @param numeroPagina el número de página, 1 es la primera
	 * @param registrosPorPagina pues eso.
	 * @return
	 */
	public CursoPage catalogo(OrderType orderType, int numeroPagina, int registrosPorPagina);
	
	/**
	 * Como usuario quiero poder dar de alta nuevos cursos, la información sobre los mismos será la siguiente:
	 * <ul>
	 * <li>Si está activo o no,</li>
	 * <li>El profesor que imparte el curso, se podrá seleccionar de entre uno de los existentes en el sistema (no será una tabla administrable),</li>
	 * <li>Título</li>
	 * <li>Número de horas,</li>
	 * <li>Nivel del curso, pudiendo seleccionar entre: básico, intermedio y avanzado</li>
	 * </ul>
	 * @param curso
	 */
	public void alta(Curso curso);
	
	/**
	 * Como programador quiero todos los cursos para comenzar la maqueta web.
	 * @return
	 */
	public List<Curso> todos();
	
	/**
	 * Nadie se ha percatado de que hay que mostrar, al menos el curso creado
	 * @param id
	 * @return
	 */
	public Curso findById(Long id);
	

}
