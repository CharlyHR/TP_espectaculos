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
		//seba.crearEspectaculo(1, "Recital de Luismi", "23/2/2023", 10000, "http:foto", 50000, 50000);
		System.out.println("La capacidad del espectaculo 1 es: " + seba.obtenerCapacidad(1));
		
	}

}
