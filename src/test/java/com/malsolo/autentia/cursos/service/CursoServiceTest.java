package com.malsolo.autentia.cursos.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.malsolo.autentia.cursos.domain.Curso;
import com.malsolo.autentia.cursos.persistence.OrderType;
import com.malsolo.autentia.cursos.persistence.ProfesorMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
public class CursoServiceTest {

	private static final String MENSAJE_METODO_PRUEBA = "PROBANDO {}.{} {} ";
	
	private Logger logger = LoggerFactory.getLogger(CursoServiceTest.class);
	
	@Autowired
	private ProfesorMapper profesorMapper;
	
	@Autowired
	private CursoService cursoService;
	
	@Rule
	public TestRule watcher = new TestWatcher() {
		protected void starting(Description description) {
			logger.info(MENSAJE_METODO_PRUEBA, description.getClassName(), description.getMethodName(), "");
		};
		
		protected void finished(Description description) {
			logger.info(MENSAJE_METODO_PRUEBA, description.getClassName(), description.getMethodName(), ". HECHO. ");
		};
	};
	
    @Test
    public void testMarkerMethod() {
    }
    
    @Test
    public void testCatalogo() {
    	int numeroPagina = 1;
    	int registrosPorPagina = 2;
    	List<Curso> cursos = this.cursoService.catalogo(OrderType.ASC, numeroPagina, registrosPorPagina);
        assertNotNull("Error al buscar catálogo de cursos, devuelto null", cursos);
        for (Curso curso : cursos) {
        	logger.debug("Encontrado curso del catálogo: {} ", curso);
		}
        assertEquals("Error al buscar catálogo de cursos, no se ha devuelto el número esperado", 2, cursos.size());
	}
    
    @Test
    public void testAlta() {
    	Curso curso = new Curso();
    	curso.setProfesor(this.profesorMapper.findById(1L));
    	curso.setTitulo("Alta de curso");
    	curso.setNivel(Curso.NIVEL_BASICO);
    	curso.setHoras(10);
    	curso.setActivo(true);
    	this.cursoService.alta(curso);
    	logger.debug("Alta de un curso: {} ", curso);
	}
    
    @Test
    public void testTodos() {
    	List<Curso> cursos = this.cursoService.todos();
        assertNotNull("Error al buscar catálogo de cursos, devuelto null", cursos);
        for (Curso curso : cursos) {
        	logger.debug("Encontrado curso del catálogo: {} ", curso);
		}
        assertEquals("Error al buscar catálogo de cursos, no se ha devuelto el número esperado", 5, cursos.size());
	}

}
