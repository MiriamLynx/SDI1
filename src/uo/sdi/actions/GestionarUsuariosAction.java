package uo.sdi.actions;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.model.Usuario;
import uo.sdi.persistence.Persistencia;

public class GestionarUsuariosAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			List<Usuario> activados = Persistencia.getUsuariosActivados();

			request.getSession().setAttribute("usuariosActivados", activados);

			List<Usuario> desactivados = Persistencia.getUsuariosDesactivados();

			request.getSession().setAttribute("usuariosDesactivados", desactivados);

		} catch (SQLException e) {
			Log.error("Se ha producido una Excepcion SQL");
			return "FRACASO";
		}

		return "EXITO";
	}

}
