package uo.sdi.actions;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.model.Asignatura;
import uo.sdi.persistence.Persistencia;

public class EliminarAsignaturaAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			String id= (String) request.getSession().getAttribute(
					"idEditar");

			Persistencia.eliminarAsignatura(id);

			List<Asignatura> asignaturas = Persistencia.listarAsignaturas();

			request.getSession().setAttribute("asignaturas", asignaturas);


		} catch (SQLException e) {
			request.setAttribute("ERROR", "Esta asignatura no se puede eliminar");
			return "FRACASO";
		}
		return "EXITO";
	}

}
