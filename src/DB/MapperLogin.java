package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.logging.Logger;

import BLL.Usuario;

public class MapperLogin {
	
	public boolean mapperBotonLogin(Usuario usr) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = Conexion.getConection();
		
		 
		//valido que el usuario exista
		
		String sql = "SELECT dni, nombre, apellido, email, nombre_usuario, password, tipo FROM usuario WHERE nombre_usuario = '" + usr.getNombreUsuario() + "'";
		
		try {
			ps = con.prepareStatement(sql);
			//ps.setInt(1, usr.getDni());
			rs = ps.executeQuery();
			if(rs.next()) {
				if(usr.getPassword().equals(rs.getString(6))) {
					System.out.println("coinciden los password");
					// coinciden passwords, los paso al modelo
					usr.setDni(rs.getInt(1));
					usr.setNombre(rs.getString(2));
					usr.setTipo(rs.getInt(7));
					System.out.println(usr.getTipo());
					usr.setEmail(rs.getString(4));
					return true;
					
				}else {
					return false;
				}
			
			}
			return false;
		}catch(SQLException e) {
			System.out.println(e);
			e.printStackTrace();
			return false;
		}
	}

}
