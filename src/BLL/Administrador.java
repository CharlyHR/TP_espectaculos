package BLL;

import DB.MapperAdministradorEspectaculo;

public class Administrador extends Usuario{

	public Administrador(String nombre, String apellido, int dni, String email, String nombreUsuario, String password) {
		super(nombre, apellido, dni, email, nombreUsuario, password);
		// TODO Auto-generated constructor stub
	}
	
	MapperAdministradorEspectaculo mapperAdmin = new MapperAdministradorEspectaculo();
	public void crearEspectaculo(int id_espectaculo, String nombre, String fecha, int precio, String foto, int capacidadTotal, int capacidadRestante) {
		
		mapperAdmin.mapperCrearEspectaculo(id_espectaculo, nombre, fecha, precio, foto, capacidadTotal, capacidadRestante);
		
	}
	
	public int obtenerCapacidad(int idEspectaculo) {
		int cap = mapperAdmin.mapperObtenerCapacidad(idEspectaculo);
		return cap;
	}


	public void modificarEspectaculo(int idEspectaculo) {
		
		mapperAdmin.mapperModificarEspectaculo(idEspectaculo);
	}
}
