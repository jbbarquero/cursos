package com.malsolo.autentia.cursos.persistence;

import java.util.List;

import com.malsolo.autentia.cursos.domain.Curso;

public interface CursoMapper {

	public Curso findById(Long id);

	public List<Curso> findAll();
	
	public void insert(Curso Curso);
	
	public void update(Curso Curso);
	
	public void delete(Long id);

}
