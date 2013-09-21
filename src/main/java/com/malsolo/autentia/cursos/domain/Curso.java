package com.malsolo.autentia.cursos.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Curso implements Serializable {
	
	public static final Integer NIVEL_BASICO = 0; 
	public static final Integer NIVEL_INTERMEDIO = 5; 
	public static final Integer NIVEL_AVANZADO = 10; 

	/** serialVersionUID */
	private static final long serialVersionUID = 4655650822711784147L;

	private Long id;

//    @NotNull
//    @Size(max = 100)
    private String titulo;

//    @NotNull
    private Integer nivel;

//    @NotNull
    private Integer horas;

//    @NotNull
    private Boolean activo;

//    @NotNull
    private Profesor profesor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public Integer getHoras() {
		return horas;
	}

	public void setHoras(Integer horas) {
		this.horas = horas;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
    
    @Override
    public int hashCode() {
    	return Objects.hash(id, titulo, nivel, horas, activo, profesor);
    }
    
    @Override
    public String toString() {
    	return com.google.common.base.Objects.toStringHelper(this)
    			.add("id", id)
    			.add("titulo", titulo)
    			.add("nivel", nivel)
    			.add("horas", horas)
    			.add("activo", activo)
    			.add("profesor", profesor)
    			.toString();
    }
    
    /**
     * Devuelve los niveles contemplados en una lista.
     * ¿Me hubiera venido bien un enum? Quilosá.
     * @return los niveles contemplados en una lista.
     */
    public static List<Integer> getNiveles() {
    	List<Integer> niveles = new ArrayList<>();
    	niveles.add(NIVEL_BASICO);
    	niveles.add(NIVEL_INTERMEDIO);
    	niveles.add(NIVEL_AVANZADO);
    	return niveles;
    }

}
