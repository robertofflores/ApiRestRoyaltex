package com.tech.ws.rest.vo;

public class VOUsuario {
	
	private int codusuario;
	private String nombre;
	private String usuario;
	private String password;
	private boolean uservalido;
	private String resultado;
	private String estado;
	private String cedula;
	private int ciudad;
	private int cliente;
	private String nomcliente;
	private String nomciudad;
	private String local;
	
	public int getCodusuario() {
		return codusuario;
	}
	public void setCodusuario(int codusuario) {
		this.codusuario = codusuario;
	}
	
	public int getCiudad() {
		return ciudad;
	}
	public void setCiudad(int ciudad) {
		this.ciudad = ciudad;
	}
	public int getCliente() {
		return cliente;
	}
	public void setCliente(int cliente) {
		this.cliente = cliente;
	}

	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
		public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isUservalido() {
		return uservalido;
	}
	public void setUservalido(boolean uservalido) {
		this.uservalido = uservalido;
	}
	public String getNomcliente() {
		return nomcliente;
	}
	public void setNomcliente(String nomcliente) {
		this.nomcliente = nomcliente;
	}
	public String getNomciudad() {
		return nomciudad;
	}
	public void setNomciudad(String nomciudad) {
		this.nomciudad = nomciudad;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	
}
