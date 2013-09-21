package com.malsolo.autentia.cursos.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.malsolo.autentia.cursos.domain.Curso;
import com.malsolo.autentia.cursos.service.CursoService;

@Controller
@RequestMapping("/catalogo")
public class CursoController {

	private static final Logger logger = LoggerFactory.getLogger(CursoController.class);
	
	@Autowired
	private CursoService cursoService;
	
    @RequestMapping(method = RequestMethod.GET)
    public String todos(Model uiModel) {
    	logger.info("Todos los cursos");
    	
    	List<Curso> cursos = this.cursoService.todos();
    	uiModel.addAttribute("cursos", cursos);
    	
    	logger.info("Todos los cursos son {} ", cursos==null?0:cursos.size());

    	return "catalogo/todos";
    }
	
}
