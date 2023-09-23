package DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

	public static final String URL = "jdbc:mysql://localhost:3306/sistemaentradas";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "##Perrito10";
	
	
	// metodo para conectarse
	public static Connection getConection() {
		Connection con = null;
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = (Connection)DriverManager.getConnection(URL,USERNAME,PASSWORD);
			System.out.println("Conexion Exitosa.");
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return con;
			
	}
}
