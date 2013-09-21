package com.malsolo.autentia.cursos.persistence;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.malsolo.autentia.cursos.domain.Curso;

public interface CursoMapper {
	
	public Curso findById(Long id);

	public List<Curso> findAll();
	
	public void insert(Curso Curso);
	
	public void update(Curso Curso);
	
	public void delete(Long id);

	/** For the time being: only active courses :( */
	public List<Curso> findActiveEntries(OrderType orderType, RowBounds rowBounds);

	public long count();

	/** For the time being: only active courses :( */
	public long countActiveEntries();

	public Curso findByActivo(Boolean activo);

	public long countByActivo(Boolean activo);
}
