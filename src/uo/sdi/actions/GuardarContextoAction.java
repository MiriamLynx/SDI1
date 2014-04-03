package uo.sdi.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.model.Usuario;
import uo.sdi.persistence.Persistencia;

public class GuardarContextoAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		try {

			String idUsuario = request.getParameter("idUsuario");

			Usuario usuario = Persistencia.getUsuarioById(idUsuario);

			request.getSession().setAttribute("idEditar", idUsuario);

			request.getSession().setAttribute("nombreEditar",
					usuario.getNombre());

			request.getSession().setAttribute("apellidosEditar",
					usuario.getApellidos());

			request.getSession().setAttribute("correoEditar",
					usuario.getCorreo());

		} catch (SQLException e) {
			Log.error("Se ha producido una Excepcion SQL");
			return "FRACASO";
		}

		return "EXITO";
	}

}
