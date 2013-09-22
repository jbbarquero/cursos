package com.malsolo.autentia.cursos.main;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.malsolo.autentia.cursos.domain.Curso;
import com.malsolo.autentia.cursos.domain.CursoPage;
import com.malsolo.autentia.cursos.domain.Profesor;
import com.malsolo.autentia.cursos.service.CursoService;

public class JacksonMain {
	
	private static final String RUTA_TESTS = "src/test/resources/json/";
	private static final String RUTA_TESTS_TMP = "target/test-classes/json/";

	private static final Logger logger = LoggerFactory.getLogger(JacksonMain.class);
	
	public static void main(String... args) throws JsonGenerationException, JsonMappingException, IOException {
		
		logger.info("Trying JSON with Jackson 2...");
		
		ObjectMapper mapper = new ObjectMapper();
		
		logger.debug("Existing curso: {} ", mapper.readValue(new File(RUTA_TESTS+"curso.json"), Curso.class));
		logger.debug("Existing curso page: {} ", mapper.readValue(new File(RUTA_TESTS+"curso_page.json"), CursoPage.class));
		
		long l = System.nanoTime();
		
		logger.debug("Curso...");
		String cursoFileName = "curso_"+l+".json";
		File cursoFile = new File(RUTA_TESTS_TMP+cursoFileName);
		logger.debug("Curso path: {} ", cursoFile.getAbsolutePath());
		mapper.writeValue(cursoFile, creaCurso());
		logger.debug("Curso escrito");
		Curso curso = mapper.readValue(cursoFile, Curso.class);
		logger.debug("Curso leido: {} ", curso);
		
		logger.debug("Cursos...");
		String cursoPageFileName = "curso_page_"+l+".json";
		File cursoPageFile = new File(RUTA_TESTS_TMP+cursoPageFileName);
		logger.debug("Cursos path: {} ", cursoPageFile.getAbsolutePath());
		mapper.writeValue(cursoPageFile, creaCursoPage());
		logger.debug("Cursos escrito");
		CursoPage cursoPage = mapper.readValue(cursoPageFile, CursoPage.class);
		logger.debug("Cursos leido: {} ", cursoPage);
		
		logger.info("Trying JSON with Jackson 2. DONE.");
	}
	
	private static Curso creaCurso() {
		Curso curso = new Curso();
		curso.setId(69L);
		curso.setTitulo("Cursillo");
		curso.setActivo(true);
		curso.setNivel(Curso.NIVEL_BASICO);
		curso.setHoras(10);
		Profesor profesor = new Profesor();
		profesor.setId(57L);
		profesor.setNombre("Wesley Snipes");
		curso.setProfesor(profesor);
		return curso;
	}
	
	private static CursoPage creaCursoPage() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath*:/META-INF/spring/applicationContext*.xml");
		ctx.refresh();
		
		CursoService cursoService = ctx.getBean("cursoService", CursoService.class);
		CursoPage cursoPage = new CursoPage(2, 1, 5, cursoService.todos());
		ctx.close();
		return cursoPage;
	}

}
