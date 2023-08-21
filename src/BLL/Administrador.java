package BLL;

import DB.MapperAdministrador;

public class Administrador extends Usuario{

	public Administrador(String nombre, String apellido, int dni, String email, String nombreUsuario, String password) {
		super(nombre, apellido, dni, email, nombreUsuario, password);
		// TODO Auto-generated constructor stub
	}
	
	MapperAdministrador mapperAdmin = new MapperAdministrador();
	public void crearEspectaculo(int id_espectaculo, String nombre, String fecha, int precio, String foto, int capacidadTotal, int capacidadRestante) {
		
		mapperAdmin.mapperCrearEspectaculo(id_espectaculo, nombre, fecha, precio, foto, capacidadTotal, capacidadRestante);
		
	}

}
