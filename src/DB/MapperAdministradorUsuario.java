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
	
	
	public void mapperModificarUsuario(int dniUsuario) {
			
			// Valores hardcodeados para probar, despues seran ingresados desde la UI
			String nuevo_nombre = "Nombre cambiado";
			String nuevo_apellido = "apellido cambiado";
			String nuevo_email = "mail cambiado";
			String nuevo_nombre_usr = "nombre usr cambiado";
			String nueva_pass = "cambiada pass";
			
			
			try {
				Connection con = null;
				con = Conexion.getConection();
				Statement s = con.createStatement();
				String sql = "UPDATE usuario set nombre = '" + nuevo_nombre + "', apellido = '" + nuevo_apellido + "',email = '" + nuevo_email + "', nombre_usuario = '" + nuevo_nombre_usr + "', password = '" + nueva_pass + "' WHERE dni = '" + dniUsuario + "'";
				s.executeUpdate(sql);
				con.close(); 
			}catch (Exception e1) {
				e1.printStackTrace();
			}
			
			//place holder
			System.out.println("Se ha modificado el usuario: \n DNI: " + dniUsuario);
		}
}
