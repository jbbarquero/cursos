package com.malsolo.autentia.cursos.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.malsolo.autentia.cursos.domain.Curso;
import com.malsolo.autentia.cursos.persistence.CursoMapper;
import com.malsolo.autentia.cursos.persistence.OrderType;
import com.malsolo.autentia.cursos.persistence.ProfesorMapper;

@Service("cursoService")
public class CursoServiceWithCursoMapperImpl implements CursoService {
	
	//TODO: añadir adjuntar y descargar fichero con temario.
	
	@Autowired
	private CursoMapper cursoMapper;
	
	@Autowired
	private ProfesorMapper profesorMapper;
	
	@Transactional(readOnly=true)
	@Override
	public List<Curso> catalogo(OrderType orderType, int numeroPagina, int registrosPorPagina) {
		Preconditions.checkNotNull(orderType, "Error al consultar el catalogo, orden incorrecto (null)");
		Preconditions.checkArgument(numeroPagina>0, "Error al consultar el catalogo, número de página incorrecto: %d",  numeroPagina);
		Preconditions.checkArgument(numeroPagina>0, "Error al consultar el catalogo, número de registros por página incorrecto: %d",  registrosPorPagina);
		int primero = (numeroPagina-1)*registrosPorPagina;
		return this.cursoMapper.findActiveEntries(orderType, new RowBounds(primero, registrosPorPagina));
	}

	@Transactional
	@Override
	public void alta(Curso curso) {
		Preconditions.checkNotNull(curso, "Error al crear un curso, curso incorrecto (null)");
		Preconditions.checkArgument((curso.getNivel()==Curso.NIVEL_BASICO) || (curso.getNivel()==Curso.NIVEL_INTERMEDIO) || (curso.getNivel()==Curso.NIVEL_AVANZADO), "Error al crear un curso, nivel incorrecto: %d", curso.getNivel());
		Preconditions.checkNotNull(curso.getProfesor(), "Error al crear un curso, profesor incorrecto (null)");
		Preconditions.checkNotNull(curso.getProfesor().getId(), "Error al crear un curso, profesor incorrecto (su ID es null)");
		Preconditions.checkNotNull(profesorMapper.findById(curso.getProfesor().getId()), "Error al crear un curso, profesor inexistente ID: %d ", curso.getProfesor().getId());
		cursoMapper.insert(curso);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Curso> todos() {
		return cursoMapper.findAll();
	}

	@Transactional(readOnly=true)
	@Override
	public Curso findById(Long id) {
		return cursoMapper.findById(id);
	}

}
