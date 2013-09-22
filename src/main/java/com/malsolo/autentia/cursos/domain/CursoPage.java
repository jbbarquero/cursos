package com.malsolo.autentia.cursos.domain;

import java.util.List;
import java.util.Objects;

public class CursoPage {
	
	private int totalPages;
	private int currentPage;
	private long totalRecords;
	private List<Curso> cursos;
	
	public CursoPage(int totalPages, int currentPage, long totalRecords,
			List<Curso> cursos) {
		super();
		this.totalPages = totalPages;
		this.currentPage = currentPage;
		this.totalRecords = totalRecords;
		this.cursos = cursos;
	}
	
	public CursoPage() {} //For Jackson

	public int getTotalPages() {
		return totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public List<Curso> getCursos() {
		return cursos;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(totalPages, currentPage, totalRecords, cursos);
	}
	
	@Override
	public String toString() {
		return com.google.common.base.Objects.toStringHelper(this)
				.add("totalPages", totalPages)
				.add("currentPage", currentPage)
				.add("totalRecords", totalRecords)
				.add("cursos", cursos)
				.toString();
	}

}
