package com.malsolo.autentia.cursos.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.malsolo.autentia.cursos.domain.Curso;
import com.malsolo.autentia.cursos.domain.Profesor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
public class CursoMapperTest {
	
	private Logger logger = LoggerFactory.getLogger(CursoMapperTest.class);
	
	@Autowired
	private CursoMapper cursoMapper;

    @Test
    public void testMarkerMethod() {
    }

    @Test
    public void testFindAll() {
    	logger.info("Probando findAll()");
    	List<Curso> cursos = this.cursoMapper.findAll();
        assertNotNull("Error al buscar todos los cursos, findAll ha devuelto null", cursos);
        assertEquals("Error al buscar todos los cursos, findAll no ha devuelto el número esperado", 2, cursos.size());
        for (Curso curso : cursos) {
        	logger.debug("Encontrado curso: {} ", curso);
			if (curso.getTitulo().contains("JSF2")) {
				assertTrue("Error al buscar todos los cursos, findAll no ha devuelto el registro esperado", curso.getProfesor().getNombre().contains("Roberto"));
			}
		}
    	logger.info("Probando findAll(). HECHO.");
    }

    @Test
    public void testFindById() {
    	logger.info("Probando findById()");
    	Long id = 1L;
    	Curso curso = this.cursoMapper.findById(id);
        assertNotNull(String.format("Error al buscar por ID, findById ha devuelto null para el ID '%d'", id), curso);
        assertEquals("Error al buscar por ID, findById ha devuelto un ID incorrecto", id, curso.getId());
    	logger.info("Probando findById(). HECHO.");
    }
    
    @Test
    public void testInsert() {
    	logger.info("Probando insert()");
    	Curso curso = createNewTransientCurso();
    	this.cursoMapper.insert(curso);
    	logger.debug("Insertado curso: {} ", curso);//Ojo: curso.profesor.nombre es "nombre"
    	Curso cursoDB = this.cursoMapper.findById(curso.getId());
    	logger.debug("Curso en BBDD: {} ", cursoDB);//Ahora: curso.profesor.nombre es "Roberto...". Amigo mío, esto es detached
    	logger.info("Probando insert(). HECHO.");
	}
    
    @Test
    public void testUpdate() {
    	logger.info("Probando update()");
    	Long id = 1L;
    	Curso curso = this.cursoMapper.findById(id);
    	curso.setTitulo("JSF2 avanzado");
    	curso.setNivel(Curso.NIVEL_AVANZADO);
    	this.cursoMapper.update(curso);
    	logger.info("Probando update(). HECHO.");
	}
    
    @Test
    public void testDelete() {
    	logger.info("Probando delete()");
    	Long id = 1L;
    	Curso curso = this.cursoMapper.findById(id);
    	assertNotNull(String.format("Error al borrar, el ID a borrar (%d) no ha sido encontrado de nuevo", id), curso);
    	logger.debug("Curso a borrar: {} ", curso);
    	this.cursoMapper.delete(id);//Borra sin flush alguno. Este JPA me tiene subyugado
    	Curso cursoDB = this.cursoMapper.findById(id);
    	logger.debug("¿Borrado curso?: {} ", cursoDB);
    	assertTrue(String.format("Error al borrar, el ID borrado (%d) ha sido encontrado de nuevo", id), cursoDB==null);
    	logger.info("Probando delete(). HECHO.");
	}

    @Test
    public void testDeleteAgain() {
    	testDelete();//Pero las transacciones funcionan. O eso parece.
    }
    
	private Curso createNewTransientCurso() {
		Curso curso = new Curso();
			Profesor profesor = new Profesor();//Oops! Molaría más getRandomProfesor();
			profesor.setId(1L);//Con dos cojones
			profesor.setNombre("nombre");//Irrelevante porque no es JPA ni cascada ni nada así
		curso.setProfesor(profesor);
		curso.setTitulo("Titulo");
		curso.setNivel(Curso.NIVEL_BASICO);
		curso.setHoras(25);
		curso.setActivo(true);
		return curso;
	}

}
