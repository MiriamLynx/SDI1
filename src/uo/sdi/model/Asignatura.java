package uo.sdi.model;

import java.util.List;

public class Asignatura {

	private String id;
	private String nombre;
	private int creditos;
	private String curso;

	private List<Profesor> profesores;
	private List<Matricula> matriculas;

	public Asignatura(String id, String nombre, int creditos, String curso) {
		this.setId(id);
		this.setCreditos(creditos);
		this.setNombre(nombre);
		this.setCurso(curso);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setProfesores(List<Profesor> profesores) {
		this.profesores = profesores;
	}

	public List<Profesor> getProfesores() {
		return profesores;
	}

	@Override
	public String toString() {
		return "Asignatura [id=" + id + ", nombre=" + nombre + ", creditos="
				+ creditos + ", curso=" + curso + ", profesores=" + profesores
				+ "]";
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

}
