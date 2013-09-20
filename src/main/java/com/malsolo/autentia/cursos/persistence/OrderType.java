package com.malsolo.autentia.cursos.persistence;

public enum OrderType {
	
	ASC("asc"), DESC("desc");
	
	private final String order;
	
	OrderType(String order) {
		this.order = order;
	}
	
	public String getOrder() {
		return this.order;
	} 

}
