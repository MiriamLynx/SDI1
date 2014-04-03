package uo.sdi.actions;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.model.Usuario;
import uo.sdi.persistence.Persistencia;

public class ModificarUsuarioAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		try {

			String viejoId = (String) request.getSession().getAttribute(
					"idEditar");
			String id = request.getParameter("nuevoId");
			String nombre = request.getParameter("nuevoNombre");
			String apellidos = request.getParameter("nuevoApellidos");
			String correo = request.getParameter("nuevoCorreo");

			Persistencia.modificarDatosUsuario(id, nombre, apellidos, correo,
					viejoId);

			List<Usuario> activados = Persistencia.getUsuariosActivados();

			request.getSession().setAttribute("usuariosActivados", activados);

			List<Usuario> desactivados = Persistencia.getUsuariosDesactivados();

			request.getSession().setAttribute("usuariosDesactivados",
					desactivados);

			request.setAttribute("EXITO", "Datos guardados correctamente");

		} catch (SQLException e) {
			Log.error("Se ha producido una Excepcion SQL");
			return "FRACASO";
		}
		return "EXITO";
	}

}
