package com.malsolo.autentia.cursos.persistence;

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

import com.malsolo.autentia.cursos.domain.Profesor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
public class ProfesorMapperTest {

	private static final String MENSAJE_INICIO_METODO_PRUEBA = "PROBANDO {}.{} ";
	private static final String MENSAJE_FIN_METODO_PRUEBA = "FIN PROBANDO {}.{} ";
	
	private Logger logger = LoggerFactory.getLogger(ProfesorMapperTest.class);
	
	@Autowired
	private ProfesorMapper profesorMapper;
	
	@Rule
	public TestRule watcher = new TestWatcher() {
		protected void starting(Description description) {
			logger.info(MENSAJE_INICIO_METODO_PRUEBA, description.getClassName(), description.getMethodName());
		};
		
		protected void finished(Description description) {
			logger.info(MENSAJE_FIN_METODO_PRUEBA, description.getClassName(), description.getMethodName());
		};
	};
	
    @Test
    public void testMarkerMethod() {
    }

    @Test
    public void testFindAll() {
    	List<Profesor> profesores = this.profesorMapper.findAll();
        assertNotNull("Error al buscar todos los profesores, findAll ha devuelto null", profesores);
        for (Profesor profesor : profesores) {
        	logger.debug("Encontrado profesor: {} ", profesor);
		}
        assertEquals("Error al buscar todos los profesores, findAll no ha devuelto el número esperado", 3, profesores.size());
    }

    @Test
    public void testFindById() {
    	Long id = 3L;
    	Profesor profesor = this.profesorMapper.findById(id);
    	logger.debug("Encontrado por id {} profesor: {} ", id, profesor);
        assertNotNull(String.format("Error al buscar profesor por ID, findById ha devuelto null para el ID '%d'", id), profesor);
        assertEquals("Error al buscar profesor por ID, findById ha devuelto un ID incorrecto", id, profesor.getId());
    }

}
