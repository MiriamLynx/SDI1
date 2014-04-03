package uo.sdi.model;

import java.util.List;

public class Profesor extends Usuario {

	private List<Asignatura> asignaturas;

	public List<Asignatura> getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(List<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}

	public Profesor(String id, String nombre, String apellidos, String correo,
			String password, boolean activado, String privilegios) {
		super(id, nombre, apellidos, correo, password, activado, privilegios);
	}

	@Override
	public String toString() {
		return "Profesor [asignaturas=" + asignaturas + ", id=" + id
				+ ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", correo=" + correo + ", password=" + password
				+ ", activado=" + activado + "]";
	}

}
