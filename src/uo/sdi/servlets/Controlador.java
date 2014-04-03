package uo.sdi.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.actions.Action;
import uo.sdi.actions.ActivarODesactivarAction;
import uo.sdi.actions.AsignarImparticionAction;
import uo.sdi.actions.AsignarMatriculaAction;
import uo.sdi.actions.A馻dirAsignaturaAction;
import uo.sdi.actions.BuscarAsignaturasPorNombreAction;
import uo.sdi.actions.BuscarAsignaturasPorNombreAdminAction;
import uo.sdi.actions.BuscarProfesoresPorNombreYApellidoAction;
import uo.sdi.actions.BuscarUsuariosPorNombreYApellidoAction;
import uo.sdi.actions.EliminarAsignaturaAction;
import uo.sdi.actions.GestionarAsignaturasAction;
import uo.sdi.actions.GestionarMatriculasAction;
import uo.sdi.actions.GestionarProfesoresAction;
import uo.sdi.actions.GestionarUsuariosAction;
import uo.sdi.actions.GuardarContextoAction;
import uo.sdi.actions.GuardarContextoAlumnoAction;
import uo.sdi.actions.GuardarContextoAsignaturaAction;
import uo.sdi.actions.GuardarContextoProfesorAction;
import uo.sdi.actions.ListarAsignaturasAction;
import uo.sdi.actions.ListarProfesoresAction;
import uo.sdi.actions.LogoutAction;
import uo.sdi.actions.LoguearAction;
import uo.sdi.actions.ModificarAsignaturaAction;
import uo.sdi.actions.ModificarDatosUsuarioAction;
import uo.sdi.actions.ModificarNotaAction;
import uo.sdi.actions.ModificarPasswordAction;
import uo.sdi.actions.ModificarPasswordAdminAction;
import uo.sdi.actions.ModificarUsuarioAction;
import uo.sdi.actions.RegistrarUsuarioAction;
import uo.sdi.actions.VerAlumnosAction;
import uo.sdi.actions.VerAsignaturasAction;
import uo.sdi.actions.VerExpedienteAction;

public class Controlador extends javax.servlet.http.HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// <opcion, objeto Action>
	Map<String, Action> mapaDeAcciones;
	// <opcion, <resultado, JSP>>
	Map<String, Map<String, String>> mapaDeJSP;

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		doGet(req, res);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		String opcion, resultado, jspSiguiente;
		Action accion;
		opcion = req.getParameter("opc");
		accion = buscarAccionParaOpcion(opcion);
		resultado = accion.execute(req, res);
		jspSiguiente = buscarJSPSegun(opcion, resultado);
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(jspSiguiente);
		dispatcher.forward(req, res);
	}

	void crearMapaAcciones() {
		mapaDeAcciones = new HashMap<String, Action>();
		mapaDeAcciones.put("listarProfesores", new ListarProfesoresAction());
		mapaDeAcciones.put("listarAsignaturas", new ListarAsignaturasAction());
		mapaDeAcciones.put("registrarUsuario", new RegistrarUsuarioAction());
		mapaDeAcciones.put("loguear", new LoguearAction());
		mapaDeAcciones.put("logout", new LogoutAction());
		mapaDeAcciones.put("modificarDatosUsuario",
				new ModificarDatosUsuarioAction());
		mapaDeAcciones.put("modificarPassword", new ModificarPasswordAction());
		mapaDeAcciones.put("verExpediente", new VerExpedienteAction());
		mapaDeAcciones.put("verAsignaturas", new VerAsignaturasAction());
		mapaDeAcciones.put("verAlumnos", new VerAlumnosAction());
		mapaDeAcciones.put("modificarNota", new ModificarNotaAction());
		mapaDeAcciones.put("gestionarUsuarios", new GestionarUsuariosAction());
		mapaDeAcciones
				.put("activarODesactivar", new ActivarODesactivarAction());
		mapaDeAcciones.put("guardarContexto", new GuardarContextoAction());
		mapaDeAcciones.put("modificarDatosUsuarioAdmin",
				new ModificarUsuarioAction());
		mapaDeAcciones.put("modificarPasswordAdmin",
				new ModificarPasswordAdminAction());
		mapaDeAcciones.put("gestionarAsignaturas",
				new GestionarAsignaturasAction());
		mapaDeAcciones.put("guardarContextoAsignatura",
				new GuardarContextoAsignaturaAction());
		mapaDeAcciones.put("modificarAsignaturaAdmin",
				new ModificarAsignaturaAction());
		mapaDeAcciones
				.put("eliminarAsignatura", new EliminarAsignaturaAction());
		mapaDeAcciones.put("a馻dirAsignatura", new A馻dirAsignaturaAction());
		mapaDeAcciones.put("gestionarProfesores",
				new GestionarProfesoresAction());
		mapaDeAcciones.put("guardarContextoProfesor",
				new GuardarContextoProfesorAction());
		mapaDeAcciones
				.put("asignarImparticion", new AsignarImparticionAction());
		mapaDeAcciones.put("guardarContextoAsignarAsignatura",
				new GuardarContextoAsignaturaAction());
		mapaDeAcciones.put("asignarImparte", new AsignarImparticionAction());
		mapaDeAcciones.put("guardarContextoAddAlumno",
				new GuardarContextoAsignaturaAction());
		mapaDeAcciones.put("asignarAlumno", new AsignarMatriculaAction());
		mapaDeAcciones.put("gestionarMatriculas",
				new GestionarMatriculasAction());
		mapaDeAcciones.put("guardarContextoAlumno",
				new GuardarContextoAlumnoAction());
		mapaDeAcciones.put("listarProfesoresPorNombreYApellido",
				new BuscarProfesoresPorNombreYApellidoAction());
		mapaDeAcciones.put("listarAsignaturasPorNombre",
				new BuscarAsignaturasPorNombreAction());
		mapaDeAcciones.put("listarUsuariosPorNombreYApellido",
				new BuscarUsuariosPorNombreYApellidoAction());
		mapaDeAcciones.put("listarAsignaturasPorNombreAdmin",
				new BuscarAsignaturasPorNombreAdminAction());
		mapaDeAcciones.put("verTodosProfesores", new ListarProfesoresAction());
		mapaDeAcciones
				.put("verTodasAsignaturas", new ListarAsignaturasAction());
		mapaDeAcciones.put("verTodosUsuarios", new GestionarUsuariosAction());
		mapaDeAcciones.put("verTodasAsignaturasAdmin",
				new GestionarAsignaturasAction());

	}

	void crearMapaDeJSP() {

		Map<String, String> map;

		mapaDeJSP = new HashMap<String, Map<String, String>>();

		map = new HashMap<String, String>();
		map.put("EXITO", "/profesores.jsp");
		map.put("FRACASO", "/error.jsp");
		mapaDeJSP.put("listarProfesores", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/asignaturas.jsp");
		map.put("FRACASO", "/error.jsp");
		mapaDeJSP.put("listarAsignaturas", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/registrarUsuario.jsp");
		map.put("FRACASO", "/registrarUsuario.jsp");
		mapaDeJSP.put("registrarUsuario", map);

		map = new HashMap<String, String>();
		map.put("EXITO_ALUMNO", "/alumno.jsp");
		map.put("EXITO_PROFESOR", "/profesor.jsp");
		map.put("EXITO_ADMINISTRADOR", "/administrador.jsp");
		map.put("FRACASO", "/index.jsp");
		mapaDeJSP.put("loguear", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/index.jsp");
		mapaDeJSP.put("logout", map);

		map = new HashMap<String, String>();
		map.put("EXITO_ALUMNO", "/alumno.jsp");
		map.put("EXITO_PROFESOR", "/profesor.jsp");
		map.put("EXITO_ADMINISTRADOR", "/administrador.jsp");
		map.put("FRACASO", "/error.jsp");
		mapaDeJSP.put("modificarDatosUsuario", map);

		map = new HashMap<String, String>();
		map.put("EXITO_ALUMNO", "/alumno.jsp");
		map.put("EXITO_PROFESOR", "/profesor.jsp");
		map.put("EXITO_ADMINISTRADOR", "/administrador.jsp");
		map.put("FRACASO", "/modificarPassword.jsp");
		mapaDeJSP.put("modificarPassword", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/expediente.jsp");
		map.put("FRACASO", "/error.jsp");
		mapaDeJSP.put("verExpediente", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/asignaturasImpartidas.jsp");
		map.put("FRACASO", "/error.jsp");
		mapaDeJSP.put("verAsignaturas", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/alumnosMatriculados.jsp");
		map.put("FRACASO", "/error.jsp");
		mapaDeJSP.put("verAlumnos", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/alumnosMatriculados.jsp");
		map.put("FRACASO", "/error.jsp");
		mapaDeJSP.put("modificarNota", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/gestionUsuarios.jsp");
		map.put("FRACASO", "/error.jsp");
		mapaDeJSP.put("gestionarUsuarios", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/gestionUsuarios.jsp");
		map.put("FRACASO", "/error.jsp");
		mapaDeJSP.put("activarODesactivar", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/modificarUsuarioAdmin.jsp");
		map.put("FRACASO", "/error.jsp");
		mapaDeJSP.put("guardarContexto", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/modificarUsuarioAdmin.jsp");
		map.put("FRACASO", "/error.jsp");
		mapaDeJSP.put("modificarDatosUsuarioAdmin", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/modificarUsuarioAdmin.jsp");
		map.put("FRACASO", "/modificarPasswordAdmin.jsp");
		mapaDeJSP.put("modificarPasswordAdmin", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/gestionAsignaturas.jsp");
		map.put("FRACASO", "/error.jsp");
		mapaDeJSP.put("gestionarAsignaturas", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/modificarAsignaturaAdmin.jsp");
		map.put("FRACASO", "/error.jsp");
		mapaDeJSP.put("guardarContextoAsignatura", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/modificarAsignaturaAdmin.jsp");
		map.put("FRACASO", "/error.jsp");
		mapaDeJSP.put("modificarAsignaturaAdmin", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/gestionAsignaturas.jsp");
		map.put("FRACASO", "/modificarAsignaturaAdmin.jsp");
		mapaDeJSP.put("eliminarAsignatura", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/addAsignatura.jsp");
		map.put("FRACASO", "/addAsignatura.jsp");
		mapaDeJSP.put("a馻dirAsignatura", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/gestionProfesores.jsp");
		map.put("FRACASO", "/error.jsp");
		mapaDeJSP.put("gestionarProfesores", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/asignarProfesor.jsp");
		map.put("FRACASO", "/asignarProfesor.jsp");
		mapaDeJSP.put("asignarImparticion", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/asignarProfesor.jsp");
		map.put("FRACASO", "/error.jsp");
		mapaDeJSP.put("guardarContextoProfesor", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/asignarAsignatura.jsp");
		map.put("FRACASO", "/error.jsp");
		mapaDeJSP.put("guardarContextoAsignarAsignatura", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/asignarAsignatura.jsp");
		map.put("FRACASO", "/asignarAsignatura.jsp");
		mapaDeJSP.put("asignarImparte", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/asignarAlumno.jsp");
		map.put("FRACASO", "/error.jsp");
		mapaDeJSP.put("guardarContextoAddAlumno", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/gestionMatriculas.jsp");
		map.put("FRACASO", "/error.jsp");
		mapaDeJSP.put("gestionarMatriculas", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/asignarMatricula.jsp");
		map.put("FRACASO", "/error.jsp");
		mapaDeJSP.put("guardarContextoAlumno", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/asignarAlumno.jsp");
		map.put("FRACASO", "/asignarAlumno.jsp");
		mapaDeJSP.put("asignarAlumno", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/profesores.jsp");
		map.put("FRACASO", "/error.jsp");
		mapaDeJSP.put("listarProfesoresPorNombreYApellido", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/asignaturas.jsp");
		map.put("FRACASO", "/error.jsp");
		mapaDeJSP.put("listarAsignaturasPorNombre", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/gestionUsuarios.jsp");
		map.put("FRACASO", "/error.jsp");
		mapaDeJSP.put("listarUsuariosPorNombreYApellido", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/gestionAsignaturas.jsp");
		map.put("FRACASO", "/error.jsp");
		mapaDeJSP.put("listarAsignaturasPorNombreAdmin", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/profesores.jsp");
		map.put("FRACASO", "/error.jsp");
		mapaDeJSP.put("verTodosProfesores", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/asignaturas.jsp");
		map.put("FRACASO", "/error.jsp");
		mapaDeJSP.put("verTodasAsignaturas", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/gestionUsuarios.jsp");
		map.put("FRACASO", "/error.jsp");
		mapaDeJSP.put("verTodosUsuarios", map);

		map = new HashMap<String, String>();
		map.put("EXITO", "/gestionAsignaturas.jsp");
		map.put("FRACASO", "/error.jsp");
		mapaDeJSP.put("verTodasAsignaturasAdmin", map);

	}

	public void init() throws ServletException {
		crearMapaAcciones();
		crearMapaDeJSP();
	}

	// Obtiene un objeto accion en funci贸n de la opci贸n
	// enviada desde el navegador
	Action buscarAccionParaOpcion(String opcion) {
		return mapaDeAcciones.get(opcion);
	}

	// Obtiene la p谩gina JSP a la que habr谩 que entregar el
	// control en funci贸n de la opci贸n enviada desde el navegador
	// y el resultado de la ejecuci贸n de la acci贸n asociada
	String buscarJSPSegun(String opcion, String resultado) {
		return mapaDeJSP.get(opcion).get(resultado);
	}
}
