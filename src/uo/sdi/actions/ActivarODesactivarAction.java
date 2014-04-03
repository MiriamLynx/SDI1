package uo.sdi.actions;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.model.Usuario;
import uo.sdi.persistence.Persistencia;

public class ActivarODesactivarAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			String activacion = request.getParameter("activacion");

			String idUsuario = request.getParameter("idUsuario");

			boolean activado = false;

			if (activacion.equals("true")) {
				activado = true;
			}

			@SuppressWarnings("unchecked")
			List<Usuario> usuariosActivados = (List<Usuario>) request
					.getSession().getAttribute("usuariosActivados");

			@SuppressWarnings("unchecked")
			List<Usuario> usuariosDesactivados = (List<Usuario>) request
					.getSession().getAttribute("usuariosDesactivados");

			if (activado) {
				Persistencia.modificarActivacionUsuario(false, idUsuario);
				for (Usuario a : usuariosActivados) {
					if (a.getId().equals(idUsuario)) {
						a.setActivado(false);
						usuariosActivados.remove(a);
						usuariosDesactivados.add(a);
						break;
					}
				}
				request.getSession().setAttribute("usuariosDesactivados",
						usuariosDesactivados);
				request.getSession().setAttribute("usuariosActivados",
						usuariosActivados);
			} else {
				Persistencia.modificarActivacionUsuario(true, idUsuario);
				for (Usuario a : usuariosDesactivados) {
					if (a.getId().equals(idUsuario)) {
						a.setActivado(true);
						usuariosActivados.add(a);
						usuariosDesactivados.remove(a);
						break;
					}
				}
				request.getSession().setAttribute("usuariosDesactivados",
						usuariosDesactivados);
				request.getSession().setAttribute("usuariosActivados",
						usuariosActivados);
			}
		} catch (SQLException e) {
			Log.error("Se ha producido una Excepcion SQL");
			return "FRACASO";
		}

		return "EXITO";
	}

}
