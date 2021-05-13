package com.tech.ws.rest.vo;

public class VOOpcion {
	private int codigo;
	private int modulo;
	private String nombre;
	private String pagina;
	
	public String getPagina() {
		return pagina;
	}
	public int getModulo() {
		return modulo;
	}
	public void setModulo(int modulo) {
		this.modulo = modulo;
	}
	public void setPagina(String pagina) {
		this.pagina = pagina;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
