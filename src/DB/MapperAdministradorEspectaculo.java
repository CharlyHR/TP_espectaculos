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

import BLL.Espectaculo;

public class MapperAdministradorEspectaculo {

	public void mapperCrearEspectaculo(int id_espectaculo, String nombre, String fecha, int precio, String foto, int capacidadTotal, int capacidadRestante) {
		
		try {
			Connection con = null;
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
	
	public void mapperModificarEspectaculo(int idEspectaculo, String nombre, String fecha, int precio, String foto, int capacidadTotal, int capacidadRestante) {
		
		// Valores hardcodeados para probar, despues seran ingresados desde la UI
		/*String nuevo_nombre = "Show de malabares";
		String nueva_fecha = "66 de noviembre";
		int nuevo_precio = 10;
		String nueva_foto = "http://nueva foto";
		int nueva_cap_total = 3000;
		int nueva_cap_restante = 3000;*/
		
		try {
			Connection con = null;
			con = Conexion.getConection();
			Statement s = con.createStatement();
			String sql = "UPDATE espectaculo set nombre = '" + nombre + "', fecha = '" + fecha + "',precio = '" + precio + "', foto = '" + foto + "', capacidad_total = '" + capacidadTotal + "', capacidad_restante = '" + capacidadRestante +"' WHERE id_espectaculo = '" + idEspectaculo + "'";
			s.executeUpdate(sql);
			con.close(); 
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//place holder
		System.out.println("Se ha modificado un espectaculo:\nID: " + idEspectaculo);
	}
	
	public void mapperEliminarEspectaculo(int idEspectaculo) {
		
		try {
			Connection con = null;
			con = Conexion.getConection();
			Statement s = con.createStatement();
			String sql = "DELETE FROM espectaculo WHERE id_espectaculo = '" + idEspectaculo + "'";
			s.executeUpdate(sql);
			con.close(); 
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//place holder
		System.out.println("Se ha eliminado un espectaculo:\nID: " + idEspectaculo);
	}
	
	
	// Función para cargar datos de espectáculos en un DefaultTableModel
    public List<Espectaculo> mapperCargarTabla(DefaultTableModel modelo) {
        List<Espectaculo> espectaculos = new ArrayList<>();

        try {
            Connection con = Conexion.getConection();
            String sql = "SELECT id_espectaculo, nombre, fecha, precio, foto, capacidad_total, capacidad_restante FROM espectaculo";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            // Agregar columnas al modelo
            for (int i = 1; i <= cantidadColumnas; i++) {
                modelo.addColumn(rsMd.getColumnLabel(i));
            }

            while (rs.next()) {
                Object[] fila = new Object[cantidadColumnas];
                for (int i = 1; i <= cantidadColumnas; i++) {
                    fila[i - 1] = rs.getObject(i);
                }
                modelo.addRow(fila);

                Espectaculo espectaculo = new Espectaculo();
                espectaculo.setId_espectaculo(Integer.parseInt(rs.getString("id_espectaculo")));
                espectaculo.setNombre(rs.getString("nombre"));
                espectaculo.setFecha(rs.getString("fecha"));
                espectaculo.setPrecio(Integer.parseInt(rs.getString("precio")));
                espectaculo.setFotoEspectaculo(rs.getString("foto"));
                espectaculo.setCapacidadTotal(Integer.parseInt(rs.getString("capacidad_total")));
                espectaculo.setCapacidadRestante(Integer.parseInt(rs.getString("capacidad_restante")));
                espectaculos.add(espectaculo);
            }

            ps.close();
            con.close();
        } catch (SQLException e) {
            System.err.println(e.toString());
        }
        return espectaculos;
    }
	
	
}
