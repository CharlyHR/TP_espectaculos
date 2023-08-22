package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MapperVendedor {
	
	//conexion a la BD
	
	//metodo registrarVenta requiere los datos:
	//(VENTA) -> 		fechaVenta
	//(ESPECTACULO) ->	ubicacion, precio, id_estadio
	
	public void mapperRegistrarVenta(int dniVendedor, int id_espectaculo, String ubicacion, int cantidadVenta, String fechaVenta) {
		// se conecta
		try {
			Connection con = null;
			
			
			PreparedStatement ps;
			ResultSet res;
			con = Conexion.getConection();
			
			Statement s = con.createStatement();
            String sql = "INSERT INTO venta (dni_vendedor, id_espectaculo, ubicacion, cantidad_venta, fecha_venta) "
					+ "				VALUES ('" + dniVendedor + "', '" + id_espectaculo + "', '" + ubicacion + "', '" + cantidadVenta + "', '" + fechaVenta + "')";
            s.executeUpdate(sql);
            //con.commit();
			
			
			//ps= con.prepareStatement("INSERT INTO venta (dni_vendedor, id_espectaculo, ubicacion, cantidad_venta, fecha_venta) "
			//		+ "				VALUES ('" + dniVendedor + "', '" + id_espectaculo + "', '" + ubicacion + "', '" + cantidadVenta + "', '" + fechaVenta + "')");
			
			//res = ps.executeQuery();
			
			con.close(); // la cerre aca por que no me dejaba cerrarla en el finally, no se por que
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//place holder
		System.out.println("Se ha registrado una venta:\nFecha de Venta: " + fechaVenta);
		
	}

	
	
	// mapperObtenerCapacidad: recibe un id_espectaculo y recupera de la BD la capacidad del estadio dado
	public int mapperObtenerCapacidad(int idEspectaculo) {
	    try {
	        Connection con = null;
	        con = Conexion.getConection();
	        Statement s = con.createStatement();
	        String sql = "SELECT capacidad_restante FROM espectaculo WHERE id_espectaculo = '" + idEspectaculo + "'";
	        ResultSet rs = s.executeQuery(sql);

	        // Verificar si hay al menos una fila en el resultado
	        if (rs.next()) {
	            int capacidad = rs.getInt("capacidad_restante");
	            System.out.println("Desde el mapperObtenerCapacidad se trajo la siguiente capacidad de la BD:" + capacidad);
	            con.close();
	            return capacidad;
	        } else {
	            // No se encontraron filas que coincidan con el ID proporcionado
	            con.close();
	            return 0;
	        }

	    } catch (Exception e1) {
	        e1.printStackTrace();
	        return 0;
	    }
	}
}


// como vamos a calcular el precio por ubicacion?
// en la venta deberia quedar registrado quien fue el vendedor, creo un atributo vendedor dentro de venta?