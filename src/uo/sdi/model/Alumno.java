package uo.sdi.model;

import java.util.List;

public class Alumno extends Usuario {

	private List<Matricula> matricula;

	public Alumno(String id, String nombre, String apellidos, String correo,
			String password, boolean activado, String privilegios) {
		super(id, nombre, apellidos, correo, password, activado, privilegios);
	}

	@Override
	public String toString() {
		return "Alumno [id=" + id + ", nombre=" + nombre + ", apellidos="
				+ apellidos + ", correo=" + correo + ", password=" + password
				+ ", activado=" + activado + "]";
	}

	public List<Matricula> getMatricula() {
		return matricula;
	}

	public void setMatricula(List<Matricula> matricula) {
		this.matricula = matricula;
	}

}
