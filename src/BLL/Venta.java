package BLL;

public class Venta {

	private String fechaVenta;
	private int precio;
	private int id_espectaculo;
	
	
	// constructor
	
	public Venta(String fechaVenta, int precio, int id_espectaculo) {
		super();
		this.fechaVenta = fechaVenta;
		this.precio = precio;
		this.id_espectaculo = id_espectaculo;
	}
	
	// getters
	
	public String getFechaVenta() {
		return fechaVenta;
	}
	public void setFechaVenta(String fechaVenta) {
		this.fechaVenta = fechaVenta;
	}
	public int getPrecio() {
		return precio;
	}
	
	// setters
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public int getId_espectaculo() {
		return id_espectaculo;
	}
	public void setId_espectaculo(int id_espectaculo) {
		this.id_espectaculo = id_espectaculo;
	}
	
	
	
}
