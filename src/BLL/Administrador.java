package BLL;

import DB.MapperAdministradorEspectaculo;
import DB.MapperAdministradorUsuario;

public class Administrador extends Usuario{

	public Administrador(String nombre, String apellido, int dni, String email, String nombreUsuario, String password, int tipo) {
		super(nombre, apellido, dni, email, nombreUsuario, password, tipo);
		// TODO Auto-generated constructor stub
	}
	
	MapperAdministradorEspectaculo mapperAdmin = new MapperAdministradorEspectaculo();
	public void crearEspectaculo(int id_espectaculo, String nombre, String fecha, int precio, String foto, int capacidadTotal, int capacidadRestante) {
		
		mapperAdmin.mapperCrearEspectaculo(id_espectaculo, nombre, fecha, foto);
		
	}
	
	public int obtenerCapacidad(int idEspectaculo) {
		int cap = mapperAdmin.mapperObtenerCapacidad(idEspectaculo);
		return cap;
	}


	public void modificarEspectaculo(int idEspectaculo, String nombre, String fecha, String foto) {
		
		mapperAdmin.mapperModificarEspectaculo(idEspectaculo,nombre, fecha, foto);
	}
	
	public void eliminarEspectaculo(int idEspectaculo) {
		
		mapperAdmin.mapperEliminarEspectaculo(idEspectaculo);
	}
	
	
	// de mapperAdministradorUsuario
	MapperAdministradorUsuario mapperAdmUsr = new MapperAdministradorUsuario(); // ESTA BIEN ESTO? tener dos mappers admin?
	public void crearUsuario(int dni, String nombre, String apellido, String email, String nombreUsuario, String password,  int tipo) {
		mapperAdmUsr.mapperCrearUsuario(dni, nombre, apellido, email, nombreUsuario, password, tipo);
	}
	
	public void eliminarUsuario(int dniUsuario) {
		mapperAdmUsr.mapperEliminarUsuario(dniUsuario);
	}
	
	public void modificarUsuario(int dni, String nuevo_nombre, String nuevo_apellido, String nuevo_email, String nuevo_nombre_usr, String nueva_pass, int nuevo_tipo) {
		mapperAdmUsr.mapperActualizarUsuario(dni, nuevo_nombre, nuevo_apellido, nuevo_email, nuevo_nombre_usr, nueva_pass, nuevo_tipo);
	}
}
