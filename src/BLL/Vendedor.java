package BLL;

import DB.MapperVendedor;

public class Vendedor extends Usuario{

	public Vendedor(String nombre, String apellido, int dni, String email, String nombreUsuario, String password) {
		super(nombre, apellido, dni, email, nombreUsuario, password);
		// TODO Auto-generated constructor stub
	}

	
	// instancio una venta o un espectaculo?
	// instancio el mapper
	MapperVendedor mapperVendedor = new MapperVendedor();
	
	// METODOS
	
	//registrarVenta es el que debe enviar los datos al mapper
	public void registrarVenta(int dniVendedor, int id_espectaculo, String ubicacion, int cantidadVenta, String fechaVenta) {
		/*
		 * 	id_venta (AI) - no lo necesito para registrarla es Auto Increment
			dni_vendedor INT - 
			id_espectaculo INT
			ubicacion String
			cantidad_venta INT
			fecha_venta String
		 */
		mapperVendedor.mapperRegistrarVenta(dniVendedor, id_espectaculo, ubicacion, cantidadVenta, fechaVenta);
		
	}
	
	// recibe el id_espectaculo y una cantidad de entradas a vender y devuelve true si hay espacio libre y false si no lo hay 
	// vamos a tener que agregar un campo en Espectaculos para llevar la cuenta de cuanto espacio libre queda.
	public void verificarCapacidad(String id_espectaculo, int entradas) {
		
		int capacidad = mapperVendedor.mapperObtenerCapacidad(id_espectaculo);
		
		
	}
	

}
