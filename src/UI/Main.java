package UI;

import BLL.Administrador;
import BLL.Vendedor;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// instancio un vendedor
		Vendedor jose = new Vendedor("Jose", "Perez", 123456789, "jose.perez@gmail.com", "josepe", "pass1234");
		
		// registro una venta con ese vendedor
		//(int dniVendedor, String id_espectaculo, String ubicacion, int cantidadVenta, String fechaVenta)
		//jose.registrarVenta(jose.getDni(), 100, "Campo", 1, "2 de Diciembre");
		
		//probando admin.crearEspectaculo
		Administrador seba = new Administrador("Sebastian", "Azzolina", 32393787 , "seba.azzolina@gmail.com", "sebauser", "contraseniasegura");
		//seba.crearEspectaculo(2, "este se borra", "23/2/2023", 4, "http:foto2", 5656, 5656);
		
		//probando obtener capacidad
		System.out.println("La capacidad del espectaculo 1 es: " + seba.obtenerCapacidad(1));
		
		//probando modificar espectaculo
		//seba.modificarEspectaculo(1);
		
		//probando eliminar espectaculo
		//seba.eliminarEspectaculo(2);
		
		//probando modificar venta desde el vendedor
		//jose.modificarVenta(5);
		
		//probando eliminar Venta desde el vendedor
		//jose.eliminarVenta(2);
		
		//probando crear usuario desde Administrador
		//seba.crearUsuario(5554, "a borrar", "juana", "sdf@gmail.com", "sdf", "sdfsdf");
		
		//probando eliminar usuario desde Administrador
		//seba.eliminarUsuario(11113666);
		
		//probando modificar usuario desde Administrador
		seba.modificarUsuario(5554);
	}

}
