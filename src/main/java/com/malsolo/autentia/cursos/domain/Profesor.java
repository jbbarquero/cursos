package com.malsolo.autentia.cursos.domain;

import java.io.Serializable;
import java.util.Objects;

public class Profesor implements Serializable {
	
	/** serialVersionUID  */
	private static final long serialVersionUID = -8249851878435018619L;
	
	private Long id;
//    @NotNull
//    @Size(max = 100)
	private String nombre;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
	}
	
	@Override
	public String toString() {
		return com.google.common.base.Objects.toStringHelper(this)
				.add("id", id)
				.add("nombre", nombre)
				.toString();
	}
    
}
