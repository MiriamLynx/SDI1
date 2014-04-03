package uo.sdi.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.model.Usuario;
import uo.sdi.persistence.Persistencia;

public class ModificarDatosUsuarioAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			Usuario usuario = (Usuario) request.getSession().getAttribute(
					"usuario");

			String id = usuario.getId();
			String nombre = request.getParameter("nombre");
			String apellidos = request.getParameter("apellidos");
			String correo = request.getParameter("correo");

			Persistencia.modificarUsuario(id, nombre, apellidos, correo);

			usuario.setNombre(nombre);
			usuario.setApellidos(apellidos);
			usuario.setCorreo(correo);

			request.getSession().setAttribute("usuario", usuario);

			String privilegios = usuario.getPrivilegios();

			request.setAttribute("EXITO", "Datos guardados correctamente");

			switch (privilegios) {

			case "alumno":
				return "EXITO_ALUMNO";

			case "profesor":
				return "EXITO_PROFESOR";

			case "administrador":
				return "EXITO_ADMINISTRADOR";

			}

		} catch (SQLException e) {
			Log.error("Se ha producido una Excepcion SQL");
			return "FRACASO";
		}
		return "EXITO";
	}
}
