package uo.sdi.actions;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.model.Asignatura;
import uo.sdi.persistence.Persistencia;

public class AñadirAsignaturaAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			String id = request.getParameter("Id");
			String nombre = request.getParameter("Nombre");
			String curso = request.getParameter("Curso");
			int creditos = Integer.parseInt(request.getParameter("Creditos"));

			Persistencia.insertarAsignatura(id, nombre, curso, creditos);

			request.setAttribute("EXITO", "Asignatura creada correctamente");

			List<Asignatura> asignaturas = Persistencia.listarAsignaturas();

			request.getSession().setAttribute("asignaturas", asignaturas);

		} catch (SQLException e) {
			request.setAttribute(
					"ERROR",
					"Ya existe una asignatura con ese codigo o los datos introducidos no son validos");
			return "FRACASO";
		}
		return "EXITO";
	}

}
