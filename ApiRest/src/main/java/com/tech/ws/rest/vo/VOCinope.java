package com.tech.ws.rest.vo;

public class VOCinope {
	private int codigo;
	private String fila;
	private int codDconti;
	private int codOpera;
	private String nomOpera;
	private String antecesor;
	private String cantidad;
	private String subtotal;
	private String sam;
	private String detalle;
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getFila() {
		return fila;
	}
	public void setFila(String fila) {
		this.fila = fila;
	}
	public int getCodOpera() {
		return codOpera;
	}
	public void setCodOpera(int codOpera) {
		this.codOpera = codOpera;
	}
	public String getNomOpera() {
		return nomOpera;
	}
	public void setNomOpera(String nomOpera) {
		this.nomOpera = nomOpera;
	}
	public String getAntecesor() {
		return antecesor;
	}
	public void setAntecesor(String antecesor) {
		this.antecesor = antecesor;
	}
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	public String getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}
	public String getSam() {
		return sam;
	}
	public void setSam(String sam) {
		this.sam = sam;
	}
	public int getCodDconti() {
		return codDconti;
	}
	public void setCodDconti(int codDconti) {
		this.codDconti = codDconti;
	}

}
