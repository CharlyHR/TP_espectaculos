package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import BLL.Usuario;

public class MapperAdministradorUsuario {

	public void mapperCrearUsuario(int dni, String nombre, String apellido, String email, String nombreUsuario, String password, int tipo) {
	    try {
	        Connection con = null;
	        con = Conexion.getConection();
	        Statement s = con.createStatement();
	        String sql = "INSERT INTO usuario (dni, nombre, apellido, email, nombre_usuario, password, tipo ) "
	                + "VALUES ('" + dni + "', '" + nombre + "', '" + apellido + "', '" + email + "', '" + nombreUsuario + "','" + password + "','" + tipo + "')";
	        s.executeUpdate(sql);
	        con.close();
	        
	    } catch (Exception e1) {
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
	
	/*
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
	}*/
	
	
	// funcion de tabla
	// se conecta a la BD y trae un resultset que devuelve al modelo
	// Iniciar la obtención de datos de la base de datos y configurar el modelo
	public List<Usuario> mapperCargarTabla(DefaultTableModel modelo) {
        List<Usuario> usuarios = new ArrayList<>();

        try {
            Connection con = Conexion.getConection();
            String sql = "SELECT dni, nombre, apellido, email, nombre_usuario, tipo FROM usuario";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            for (int i = 1; i <= cantidadColumnas; i++) {
                modelo.addColumn(rsMd.getColumnLabel(i));
            }

            while (rs.next()) {
                Object[] fila = new Object[cantidadColumnas];
                for (int i = 1; i <= cantidadColumnas; i++) {
                    fila[i - 1] = rs.getObject(i);
                }
                modelo.addRow(fila);

                Usuario usuario = new Usuario();
                usuario.setDni(Integer.parseInt(rs.getString("dni")));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setEmail(rs.getString("email"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setTipo(Integer.parseInt(rs.getString("tipo")));
                usuarios.add(usuario);
            }

            ps.close();
            con.close();
        } catch (SQLException e) {
            System.err.println(e.toString());
        }
        return usuarios;
    }
	
	public void mapperActualizarUsuario(int dni, String nuevo_nombre, String nuevo_apellido, String nuevo_email, String nuevo_nombre_usr, String nueva_pass, int nuevo_tipo) {
	    try {
	        Connection con = Conexion.getConection();
	        Statement s = con.createStatement();
	        String sql = "UPDATE usuario SET nombre = '" + nuevo_nombre + "', apellido = '" + nuevo_apellido +
	                "', email = '" + nuevo_email + "', nombre_usuario = '" + nuevo_nombre_usr +
	                "', password = '" + nueva_pass + "', tipo = '" + nuevo_tipo + "' WHERE dni = '" + dni + "'";
	        s.executeUpdate(sql);
	        con.close();
	    } catch (Exception e1) {
	        e1.printStackTrace();
	    }
	}
}
