package DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MapperAdministrador {

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
	
}
