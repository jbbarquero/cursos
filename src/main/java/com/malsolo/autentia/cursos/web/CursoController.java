package com.malsolo.autentia.cursos.web;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import com.malsolo.autentia.cursos.domain.Curso;
import com.malsolo.autentia.cursos.persistence.ProfesorMapper;
import com.malsolo.autentia.cursos.service.CursoService;

@Controller
@RequestMapping("/catalogo")
public class CursoController {

	private static final Logger logger = LoggerFactory.getLogger(CursoController.class);
	
	@Autowired
	private CursoService cursoService;
	
	//OK, CursoServiceTest debería haberme hecho ver que no tengo service de profesores
	//Como el profesor no es una tabla administrable, lo dejaremos así por ahora
	@Autowired
	private ProfesorMapper profesorMapper;
	
	@ModelAttribute("niveles")
	public List<Integer> getNiveles() {
		return Curso.getNiveles();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String todos(Model uiModel) {
		logger.info("Todos los cursos");
		
		List<Curso> cursos = this.cursoService.todos();
		uiModel.addAttribute("cursos", cursos);
		
		logger.info("Todos los cursos son {} ", cursos==null?0:cursos.size());
		
		return "catalogo/todos";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String mostrar(@PathVariable("id") Long id, Model uiModel) {
		/* no Bad Religion song can make your life complete
		 * prepare for rejection you'll get no direction from me
		 */
		uiModel.addAttribute("curso", this.cursoService.findById(id));
		uiModel.addAttribute("cursoId", id);
		return "catalogo/mostrar";
	}
	
	
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String altaFormulario(Model uiModel) {
    	Curso curso = new Curso();
    	uiModel.addAttribute("profesores", this.profesorMapper.findAll());
    	uiModel.addAttribute("curso", curso);
    	return "catalogo/crear";
    }
    
    @RequestMapping(params = "form", method = RequestMethod.POST)
    public String alta(/*@javax.validation.Valid*/ Curso curso, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
    	
    	logger.info("Alta de un curso...");
    	
    	if (bindingResult.hasErrors()) {
    		uiModel.addAttribute("mensaje", "Error al dar de alta un curso");
        	uiModel.addAttribute("profesores", this.profesorMapper.findAll());
        	uiModel.addAttribute("curso", curso);
        	return "catalogo/crear";
    	}
    	
    	uiModel.asMap().clear();
    	this.cursoService.alta(curso);
    	
    	logger.info("Alta del curso {}", curso);
    	
    	return "redirect:/catalogo/"+encodeUrlPathSegment(curso.getId().toString(), httpServletRequest);
    }

    /* 
     * No se pueden usar herramientas de generación de código.
     * Pero nadie dijo que no se podía robar código.
     */
    String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
    	String enc = httpServletRequest.getCharacterEncoding();
    	if (enc == null) {
    		enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
    	}
    	try {
    		pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
    	} catch (UnsupportedEncodingException uee) {}
    	return pathSegment;
    }
    
}
