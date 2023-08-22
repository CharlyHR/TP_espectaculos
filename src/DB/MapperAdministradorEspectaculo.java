package DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MapperAdministradorEspectaculo {

	public void mapperCrearEspectaculo(int id_espectaculo, String nombre, String fecha, int precio, String foto, int capacidadTotal, int capacidadRestante) {
		
		try {
			Connection con = null;
			PreparedStatement ps;
			ResultSet res;
			con = Conexion.getConection();
			
			Statement s = con.createStatement();
            String sql = "INSERT INTO espectaculo (id_espectaculo, nombre, fecha, precio, foto, capacidad_total, capacidad_restante ) "
					+ "				VALUES ('" + id_espectaculo + "', '" + nombre + "', '" + fecha + "', '" + precio + "', '" + foto + "','" + capacidadTotal + "','" + capacidadRestante + "')";
            s.executeUpdate(sql);
			
			con.close(); // la cerre aca por que no me dejaba cerrarla en el finally, no se por que
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//place holder
		System.out.println("Se ha creado un espectaculo:\nNombre: " + nombre);
		
	}
	
	
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
