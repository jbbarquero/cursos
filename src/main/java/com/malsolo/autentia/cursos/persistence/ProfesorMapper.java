package com.malsolo.autentia.cursos.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.malsolo.autentia.cursos.domain.Profesor;

public interface ProfesorMapper {

	@Select("select id, nombre from profesores where id = #{id}")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="nombre", column="nombre")
	})
	public Profesor findById(@Param("id") Long id);

	@Select("select id, nombre from profesores ")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="nombre", column="nombre")
	})
	public List<Profesor> findAll();
	
}
