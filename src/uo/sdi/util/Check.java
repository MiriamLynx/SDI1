package uo.sdi.util;

import java.sql.SQLException;

import uo.sdi.model.Usuario;
import uo.sdi.persistence.Persistencia;

public class Check {

	public static boolean yaExisteId(String id) throws SQLException {
		Usuario usuario = Persistencia.getUsuarioById(id);
		if (usuario != null) {
			return true;
		}
		return false;
	}

	public static Usuario getUsuarioById(String id) throws SQLException {
		return Persistencia.getUsuarioById(id);
	}

	public static boolean passwordCorrecta(Usuario usuario, String password) {
		return usuario.getPassword().equals(password);
	}

	public static boolean cuentaActiva(Usuario usuario) {
		return usuario.getActivado();
	}

	public static boolean passwordsCoinciden(String password,
			String repetirPassword) {
		return password.equals(repetirPassword);
	}

	public static boolean nuevaContraseñaValida(String actual, String nueva,
			String repetir, String verdadera) {
		if (actual.equals(verdadera) && nueva.equals(repetir)) {
			return true;
		}
		return false;
	}

	public static boolean nuevaContraseñaValidaAdmin(String nueva,
			String repetir) {
		if (nueva.equals(repetir)) {
			return true;
		}
		return false;
	}
}
