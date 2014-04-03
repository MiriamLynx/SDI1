package uo.sdi.actions;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.model.Usuario;
import uo.sdi.persistence.Persistencia;
import alb.util.log.Log;

public class BuscarUsuariosPorNombreYApellidoAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String criterio = request.getParameter("criterio");

			System.out.println(criterio + "!!!!!!!!!!!!!!!!!!!!!");
			
			List<Usuario> activados = Persistencia
					.getUsuariosActivadosPorNombre(criterio);

			request.getSession().setAttribute("usuariosActivados", activados);

			List<Usuario> desactivados = Persistencia
					.getUsuariosDesactivadosPorNombre(criterio);

			request.getSession().setAttribute("usuariosDesactivados",
					desactivados);

		} catch (SQLException sqle) {
			Log.error("Se ha producido una Excepcion SQL");
			return "FRACASO";
		}
		return "EXITO";
	}

}
