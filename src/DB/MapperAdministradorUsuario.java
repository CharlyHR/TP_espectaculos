package DB;

import java.sql.Connection;
import java.sql.Statement;

public class MapperAdministradorUsuario {

	public void mapperCrearUsuario(int dni, String nombre, String apellido, String email, String nombreUsuario, String password) {
			
			try {
				Connection con = null;
				con = Conexion.getConection();
				
				Statement s = con.createStatement();
	            String sql = "INSERT INTO usuario (dni, nombre, apellido, email, nombre_usuario, password ) "
						+ "				VALUES ('" + dni + "', '" + nombre + "', '" + apellido + "', '" + email + "', '" + nombreUsuario + "','" + password + "')";
	            s.executeUpdate(sql);
				con.close(); // la cerre aca por que no me dejaba cerrarla en el finally, no se por que
			}catch (Exception e1) {
				e1.printStackTrace();
			}
			
			//place holder
			System.out.println("Se ha creado un usuario:\nNombre: " + nombre);
			
		}

	public void mapperEliminarUsuario(int dniUsuario) {
		
		try {
			Connection con = null;
			con = Conexion.getConection();
			Statement s = con.createStatement();
			String sql = "DELETE FROM usuario WHERE dni = '" + dniUsuario + "'";
			s.executeUpdate(sql);
			con.close(); 
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//place holder
		System.out.println("Se ha eliminado el usuario con DNI:\nID: " + dniUsuario);
	}
}
