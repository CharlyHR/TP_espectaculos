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
		
		String sql = "SELECT DNI, Nombre, Apellido, Email, NombreUsuario, Password, Tipo FROM usuario WHERE NombreUsuario = '" + usr.getNombreUsuario() + "'";
		
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
