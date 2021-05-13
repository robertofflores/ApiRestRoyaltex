package com.tech.ws.rest.vo;

public class VOProducto {
	private String codigo;
	private String nombre;
	private int stock;
	private String local;
	private int codigoProd;
	public int getCodigoProd() {
		return codigoProd;
	}
	public void setCodigoProd(int codigoProd) {
		this.codigoProd = codigoProd;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	
}
