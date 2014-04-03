package uo.sdi.model;

public abstract class Usuario {

	String id;
	String nombre;
	String apellidos;
	String correo;
	String password;
	boolean activado;
	String privilegios;

	public Usuario(String id, String nombre, String apellidos, String correo,
			String password, boolean activado, String privilegios) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.password = password;
		this.activado = activado;
		this.privilegios = privilegios;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos="
				+ apellidos + ", correo=" + correo + ", password=" + password
				+ ", activado=" + activado + "]";
	}

	public String getPrivilegios() {
		return privilegios;
	}

	public void setPrivilegios(String privilegios) {
		this.privilegios = privilegios;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getActivado() {
		return activado;
	}

	public void setActivado(boolean activado) {
		this.activado = activado;
	}

}
