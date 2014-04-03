package uo.sdi.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import uo.sdi.model.Alumno;
import uo.sdi.model.Asignatura;
import uo.sdi.model.Matricula;
import uo.sdi.model.Profesor;
import uo.sdi.model.Usuario;
import uo.sdi.util.Jdbc;

public class Persistencia {

	// CONSULTAS
	private static final String LISTAR_PROFESORES = "select distinct * from tusuarios where privilegios = 'profesor' ";
	private static final String LISTAR_ASIGNATURAS = "select distinct * from tasignaturas ";
	private static final String GET_PROFESORES_BY_ASIGNATURA_ID = "SELECT distinct u.* FROM Tusuarios u, TImparte i, "
			+ "TAsignaturas a WHERE u.id = i.id_usuario "
			+ "AND a.id = i.id_asignatura" + " AND a.id = ? ";
	private static final String GET_ASIGNATURAS_BY_PROFESOR_ID = "SELECT distinct a.* FROM Tusuarios u, TImparte i, "
			+ "TAsignaturas a WHERE u.id = i.id_usuario "
			+ "AND a.id = i.id_asignatura" + " AND u.id = ? ";
	private static final String INSERTAR_USUARIO = "INSERT INTO TUSUARIOS VALUES ( ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_USUARIO_BY_ID = "select distinct u.* from TUsuarios u where u.id = ?";
	private static final String MODIFICAR_USUARIO_BY_ID = "update tusuarios set nombre = ?, apellidos = ?, correo = ? where id = ?";
	private static final String MODIFICAR_PASSWORD_BY_ID = "update tusuarios set password = ? where id = ?";
	private static final String GET_MATRICULAS_BY_USUARIO_ID = "select distinct * from tmatriculas m where m.id_usuario = ?";
	private static final String GET_ASIGNATURA_BY_ID = "select distinct * from tasignaturas where id = ?";
	private static final String GET_ALUMNOS_BY_ASIGNATURA_ID = "select distinct u.* from tasignaturas a, tmatriculas m, tusuarios u where u.id = m.id_usuario and m.id_asignatura = a.id and a.id = ?";
	private static final String MODIFICAR_NOTA = "update tmatriculas set calificacion = ? where id_usuario = ? and id_asignatura = ?";
	private static final String GET_USUARIOS_DESACTIVADOS = "select distinct * from tusuarios where validado = false";
	private static final String GET_USUARIOS_ACTIVADOS = "select distinct * from tusuarios where validado = true";
	private static final String MODIFICAR_ACTIVACION = "update tusuarios set validado = ? where id = ?";
	private static final String MODIFICAR_DATOS_USUARIO_BY_ID = "update tusuarios set id = ?, nombre = ?, apellidos = ?, correo = ? where id = ?";
	private static final String MODIFICAR_ASIGNATURA_BY_ID = "update tasignaturas set id = ?, nombre = ?, curso = ?, creditos = ? where id = ?";
	private static final String ELIMINAR_ASIGNATURA_BY_ID = "delete from tasignaturas where id = ?";
	private static final String INSERTAR_ASIGNATURA = "INSERT INTO TASIGNATURAS VALUES ( ?, ?, ?, ?)";
	private static final String INSERTAR_IMPARTICION = "INSERT INTO TIMPARTE VALUES ( ?, ?)";
	private static final String INSERTAR_MATRICULA = "INSERT INTO TMATRICULAS VALUES ( ? ,?, ?)";
	private static final String LISTAR_ALUMNOS = "select * from tusuarios where privilegios = 'alumno'";
	private static final String BUSCAR_PROFESORES = "select * from tusuarios where (nombre = ? or apellidos = ?) and privilegios = 'profesor'";
	private static final String BUSCAR_ASIGNATURAS = "select * from tasignaturas where nombre = ?";
	private static final String BUSCAR_USUARIOS_ACTIVADOS_POR_NOMBRE = "select * from tusuarios where (nombre = ? or apellidos = ?) and validado = true";
	private static final String BUSCAR_USUARIOS_DESACTIVADOS_POR_NOMBRE = "select * from tusuarios where (nombre = ? or apellidos = ?) and validado = false";

	private static Connection c = null;

	public static List<Profesor> getProfesoresByAsignaturaId(String idAsignatura)
			throws SQLException {

		List<Profesor> profesores = new ArrayList<Profesor>();

		c = Jdbc.getConnection();
		PreparedStatement ps = c
				.prepareStatement(GET_PROFESORES_BY_ASIGNATURA_ID);
		ps.setString(1, idAsignatura);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Profesor profesor = new Profesor(rs.getString("id"),
					rs.getString("nombre"), rs.getString("apellidos"),
					rs.getString("correo"), rs.getString("password"),
					rs.getBoolean("validado"), rs.getString("privilegios"));
			profesores.add(profesor);
		}
		Jdbc.close(rs, ps);
		Jdbc.close(c);
		return profesores;
	}

	public static List<Asignatura> listarAsignaturas() throws SQLException {

		List<Asignatura> asignaturas = new ArrayList<Asignatura>();

		c = Jdbc.getConnection();
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery(LISTAR_ASIGNATURAS);
		while (rs.next()) {
			String id = rs.getString("id");
			Asignatura asignatura = new Asignatura(id, rs.getString("nombre"),
					rs.getInt("creditos"), rs.getString("curso"));
			List<Profesor> misProfesores = getProfesoresByAsignaturaId(id);
			asignatura.setProfesores(misProfesores);
			asignaturas.add(asignatura);

		}
		Jdbc.close(rs, st);
		Jdbc.close(c);
		return asignaturas;
	}

	public static List<Asignatura> getAsignaturasByProfesorId(String id)
			throws SQLException {

		List<Asignatura> asignaturas = new ArrayList<Asignatura>();

		c = Jdbc.getConnection();
		PreparedStatement ps = c
				.prepareStatement(GET_ASIGNATURAS_BY_PROFESOR_ID);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Asignatura asignatura = new Asignatura(rs.getString("id"),
					rs.getString("nombre"), rs.getInt("creditos"),
					rs.getString("curso"));
			asignaturas.add(asignatura);
		}
		Jdbc.close(rs, ps);
		Jdbc.close(c);
		return asignaturas;
	}

	public static List<Profesor> listarProfesores() throws SQLException {

		List<Profesor> profesores = new ArrayList<Profesor>();

		c = Jdbc.getConnection();
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery(LISTAR_PROFESORES);
		while (rs.next()) {
			String id = rs.getString("id");
			Profesor profesor = new Profesor(id, rs.getString("nombre"),
					rs.getString("apellidos"), rs.getString("correo"),
					rs.getString("password"), rs.getBoolean("validado"),
					rs.getString("privilegios"));
			List<Asignatura> misAsignaturas = getAsignaturasByProfesorId(id);
			profesor.setAsignaturas(misAsignaturas);
			profesores.add(profesor);

		}
		Jdbc.close(rs, st);
		Jdbc.close(c);
		return profesores;
	}

	public static void insertarUsuario(String id, String nombre,
			String apellidos, String correo, String password, String privilegios)
			throws SQLException {
		c = Jdbc.getConnection();
		PreparedStatement ps = c.prepareStatement(INSERTAR_USUARIO);
		ps.setString(1, id);
		ps.setString(2, nombre);
		ps.setString(3, apellidos);
		ps.setString(4, correo);
		ps.setString(5, password);
		ps.setString(6, privilegios);
		ps.setBoolean(7, false);
		ps.executeUpdate();
		Jdbc.close(ps);
		Jdbc.close(c);
	}

	public static Usuario getUsuarioById(String id) throws SQLException {
		c = Jdbc.getConnection();
		PreparedStatement ps = c.prepareStatement(GET_USUARIO_BY_ID);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			if (rs.getString("privilegios").equals("alumno")) {
				Usuario usuario = new Alumno(id, rs.getString("nombre"),
						rs.getString("apellidos"), rs.getString("correo"),
						rs.getString("password"), rs.getBoolean("validado"),
						rs.getString("privilegios"));
				return usuario;
			} else {
				Usuario usuario = new Profesor(id, rs.getString("nombre"),
						rs.getString("apellidos"), rs.getString("correo"),
						rs.getString("password"), rs.getBoolean("validado"),
						rs.getString("privilegios"));
				return usuario;
			}
		}
		return null;
	}

	public static void modificarUsuario(String id, String nombre,
			String apellidos, String correo) throws SQLException {
		c = Jdbc.getConnection();
		PreparedStatement ps = c.prepareStatement(MODIFICAR_USUARIO_BY_ID);
		ps.setString(1, nombre);
		ps.setString(2, apellidos);
		ps.setString(3, correo);
		ps.setString(4, id);
		ps.executeUpdate();
		Jdbc.close(ps);
		Jdbc.close(c);
	}

	public static void modificarPassword(String nueva, String id)
			throws SQLException {
		c = Jdbc.getConnection();
		PreparedStatement ps = c.prepareStatement(MODIFICAR_PASSWORD_BY_ID);
		ps.setString(1, nueva);
		ps.setString(2, id);
		ps.executeUpdate();
		Jdbc.close(ps);
		Jdbc.close(c);
	}

	public static List<Matricula> encontrarExpediente(Usuario usuario)
			throws SQLException {

		List<Matricula> matriculas = new ArrayList<Matricula>();

		c = Jdbc.getConnection();
		PreparedStatement ps = c.prepareStatement(GET_MATRICULAS_BY_USUARIO_ID);
		ps.setString(1, usuario.getId());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			String idAsignatura = rs.getString("id_asignatura");
			Asignatura asignatura = getAsignaturaById(idAsignatura);
			Matricula matricula = new Matricula((Alumno) usuario, asignatura,
					rs.getInt("calificacion"));
			matriculas.add(matricula);

		}
		Jdbc.close(rs, ps);
		Jdbc.close(c);
		return matriculas;
	}

	public static Asignatura getAsignaturaById(String idAsignatura)
			throws SQLException {

		c = Jdbc.getConnection();
		PreparedStatement ps = c.prepareStatement(GET_ASIGNATURA_BY_ID);
		ps.setString(1, idAsignatura);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Asignatura asignatura = new Asignatura(rs.getString("id"),
					rs.getString("nombre"), rs.getInt("creditos"),
					rs.getString("curso"));
			List<Profesor> misProfesores = getProfesoresByAsignaturaId(idAsignatura);
			asignatura.setProfesores(misProfesores);
			return asignatura;
		}
		Jdbc.close(rs, ps);
		Jdbc.close(c);
		return null;
	}

	public static List<Alumno> getAlumnosByAsignaturaId(String idAsignatura)
			throws SQLException {

		List<Alumno> alumnos = new ArrayList<Alumno>();

		c = Jdbc.getConnection();
		PreparedStatement ps = c.prepareStatement(GET_ALUMNOS_BY_ASIGNATURA_ID);
		ps.setString(1, idAsignatura);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Alumno alumno = new Alumno(rs.getString("id"),
					rs.getString("nombre"), rs.getString("apellidos"),
					rs.getString("correo"), rs.getString("password"),
					rs.getBoolean("validado"), rs.getString("privilegios"));
			alumnos.add(alumno);
		}
		Jdbc.close(rs, ps);
		Jdbc.close(c);
		return alumnos;
	}

	public static void modificarNota(Matricula m, int nuevaNota)
			throws SQLException {
		c = Jdbc.getConnection();
		PreparedStatement ps = c.prepareStatement(MODIFICAR_NOTA);
		ps.setInt(1, nuevaNota);
		ps.setString(2, m.getAlumno().getId());
		ps.setString(3, m.getAsignatura().getId());
		ps.executeUpdate();
		Jdbc.close(ps);
		Jdbc.close(c);
	}

	public static List<Usuario> getUsuariosDesactivados() throws SQLException {

		List<Usuario> usuarios = new ArrayList<Usuario>();

		c = Jdbc.getConnection();
		PreparedStatement ps = c.prepareStatement(GET_USUARIOS_DESACTIVADOS);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			if (rs.getString("privilegios").equals("alumno")) {
				Usuario usuario = new Alumno(rs.getString("id"),
						rs.getString("nombre"), rs.getString("apellidos"),
						rs.getString("correo"), rs.getString("password"),
						rs.getBoolean("validado"), rs.getString("privilegios"));
				usuarios.add(usuario);
			} else {
				Usuario usuario = new Profesor(rs.getString("id"),
						rs.getString("nombre"), rs.getString("apellidos"),
						rs.getString("correo"), rs.getString("password"),
						rs.getBoolean("validado"), rs.getString("privilegios"));
				usuarios.add(usuario);
			}
		}
		return usuarios;
	}

	public static List<Usuario> getUsuariosActivados() throws SQLException {
		List<Usuario> usuarios = new ArrayList<Usuario>();

		c = Jdbc.getConnection();
		PreparedStatement ps = c.prepareStatement(GET_USUARIOS_ACTIVADOS);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			if (rs.getString("privilegios").equals("alumno")) {
				Usuario usuario = new Alumno(rs.getString("id"),
						rs.getString("nombre"), rs.getString("apellidos"),
						rs.getString("correo"), rs.getString("password"),
						rs.getBoolean("validado"), rs.getString("privilegios"));
				usuarios.add(usuario);
			} else {
				Usuario usuario = new Profesor(rs.getString("id"),
						rs.getString("nombre"), rs.getString("apellidos"),
						rs.getString("correo"), rs.getString("password"),
						rs.getBoolean("validado"), rs.getString("privilegios"));
				usuarios.add(usuario);
			}
		}
		return usuarios;
	}

	public static void modificarActivacionUsuario(boolean activacion,
			String idUsuario) throws SQLException {
		c = Jdbc.getConnection();
		PreparedStatement ps = c.prepareStatement(MODIFICAR_ACTIVACION);
		ps.setBoolean(1, activacion);
		ps.setString(2, idUsuario);
		ps.executeUpdate();
		Jdbc.close(ps);
		Jdbc.close(c);
	}

	public static void modificarDatosUsuario(String id, String nombre,
			String apellidos, String correo, String viejoId)
			throws SQLException {
		c = Jdbc.getConnection();
		PreparedStatement ps = c
				.prepareStatement(MODIFICAR_DATOS_USUARIO_BY_ID);
		ps.setString(1, id);
		ps.setString(2, nombre);
		ps.setString(3, apellidos);
		ps.setString(4, correo);
		ps.setString(5, viejoId);
		ps.executeUpdate();
		Jdbc.close(ps);
		Jdbc.close(c);
	}

	public static void modificarAsignatura(String id, String nombre,
			String curso, String creditos, String viejoId) throws SQLException {
		c = Jdbc.getConnection();
		PreparedStatement ps = c.prepareStatement(MODIFICAR_ASIGNATURA_BY_ID);
		ps.setString(1, id);
		ps.setString(2, nombre);
		ps.setString(3, curso);
		ps.setString(4, creditos);
		ps.setString(5, viejoId);
		ps.executeUpdate();
		Jdbc.close(ps);
		Jdbc.close(c);
	}

	public static void eliminarAsignatura(String id) throws SQLException {
		c = Jdbc.getConnection();
		PreparedStatement ps = c.prepareStatement(ELIMINAR_ASIGNATURA_BY_ID);
		ps.setString(1, id);
		ps.executeUpdate();
		Jdbc.close(ps);
		Jdbc.close(c);
	}

	public static void insertarAsignatura(String id, String nombre,
			String curso, int creditos) throws SQLException {
		c = Jdbc.getConnection();
		PreparedStatement ps = c.prepareStatement(INSERTAR_ASIGNATURA);
		ps.setString(1, id);
		ps.setString(2, nombre);
		ps.setString(3, curso);
		ps.setInt(4, creditos);
		ps.executeUpdate();
		Jdbc.close(ps);
		Jdbc.close(c);
	}

	public static void asignarImparticion(String idProfesor, String idAsignatura)
			throws SQLException {
		c = Jdbc.getConnection();
		PreparedStatement ps = c.prepareStatement(INSERTAR_IMPARTICION);
		ps.setString(1, idProfesor);
		ps.setString(2, idAsignatura);
		ps.executeUpdate();
		Jdbc.close(ps);
		Jdbc.close(c);
	}

	public static void asignarMatricula(String idAlumno, String idAsignatura)
			throws SQLException {
		c = Jdbc.getConnection();
		PreparedStatement ps = c.prepareStatement(INSERTAR_MATRICULA);
		ps.setInt(1, 12);
		ps.setString(2, idAlumno);
		ps.setString(3, idAsignatura);
		ps.executeUpdate();
		Jdbc.close(ps);
		Jdbc.close(c);
	}

	public static List<Alumno> listarAlumnos() throws SQLException {

		List<Alumno> alumnos = new ArrayList<Alumno>();

		c = Jdbc.getConnection();
		PreparedStatement ps = c.prepareStatement(LISTAR_ALUMNOS);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Alumno alumno = new Alumno(rs.getString("id"),
					rs.getString("nombre"), rs.getString("apellidos"),
					rs.getString("correo"), rs.getString("password"),
					rs.getBoolean("validado"), rs.getString("privilegios"));
			alumnos.add(alumno);
		}
		Jdbc.close(rs, ps);
		Jdbc.close(c);
		return alumnos;
	}

	public static List<Profesor> buscarProfesoresPorNombreYApellido(
			String criterio) throws SQLException {
		List<Profesor> profesores = new ArrayList<Profesor>();
		c = Jdbc.getConnection();
		PreparedStatement ps = c.prepareStatement(BUSCAR_PROFESORES);
		ps.setString(1, criterio);
		ps.setString(2, criterio);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			String id = rs.getString("id");
			Profesor profesor = new Profesor(id, rs.getString("nombre"),
					rs.getString("apellidos"), rs.getString("correo"),
					rs.getString("password"), rs.getBoolean("validado"),
					rs.getString("privilegios"));
			List<Asignatura> misAsignaturas = getAsignaturasByProfesorId(id);
			profesor.setAsignaturas(misAsignaturas);
			profesores.add(profesor);

		}
		Jdbc.close(rs, ps);
		Jdbc.close(c);
		return profesores;
	}

	public static List<Usuario> getUsuariosActivadosPorNombre(String criterio)
			throws SQLException {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		c = Jdbc.getConnection();
		PreparedStatement ps = c
				.prepareStatement(BUSCAR_USUARIOS_ACTIVADOS_POR_NOMBRE);
		ps.setString(1, criterio);
		ps.setString(2, criterio);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			String id = rs.getString("id");
			Alumno alumno = new Alumno(id, rs.getString("nombre"),
					rs.getString("apellidos"), rs.getString("correo"),
					rs.getString("password"), rs.getBoolean("validado"),
					rs.getString("privilegios"));
			usuarios.add(alumno);

		}
		Jdbc.close(rs, ps);
		Jdbc.close(c);
		return usuarios;
	}

	public static List<Usuario> getUsuariosDesactivadosPorNombre(String criterio)
			throws SQLException {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		c = Jdbc.getConnection();
		PreparedStatement ps = c
				.prepareStatement(BUSCAR_USUARIOS_DESACTIVADOS_POR_NOMBRE);
		ps.setString(1, criterio);
		ps.setString(2, criterio);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			String id = rs.getString("id");
			Alumno alumno = new Alumno(id, rs.getString("nombre"),
					rs.getString("apellidos"), rs.getString("correo"),
					rs.getString("password"), rs.getBoolean("validado"),
					rs.getString("privilegios"));
			usuarios.add(alumno);

		}
		Jdbc.close(rs, ps);
		Jdbc.close(c);
		return usuarios;
	}

	public static List<Asignatura> buscarAsignaturasPorNombre(String criterio)
			throws SQLException {
		List<Asignatura> asignaturas = new ArrayList<Asignatura>();
		c = Jdbc.getConnection();
		PreparedStatement ps = c.prepareStatement(BUSCAR_ASIGNATURAS);
		ps.setString(1, criterio);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			String id = rs.getString("id");
			Asignatura asignatura = new Asignatura(id, rs.getString("nombre"),
					rs.getInt("creditos"), rs.getString("curso"));
			List<Profesor> misProfesores = getProfesoresByAsignaturaId(id);
			asignatura.setProfesores(misProfesores);
			asignaturas.add(asignatura);

		}
		Jdbc.close(rs, ps);
		Jdbc.close(c);
		return asignaturas;
	}

}