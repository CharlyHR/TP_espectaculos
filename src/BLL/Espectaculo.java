package BLL;

public class Espectaculo {
	
	private int id_espectaculo;
	private String nombre;
	private String fecha;
	private int capacidadTotal;
	private int capacidadRestante;
	private int precio;
	private String ubicacion;
	private String fotoEspectaculo;
	
	
	
	public Espectaculo(int id_espectaculo, String nombre, String fecha, int precio, String fotoEspectaculo, int capacidadTotal, int capacidadRestante) {
		super();
		this.id_espectaculo = id_espectaculo;
		this.nombre = nombre;
		this.fecha = fecha;
		this.precio = precio;
		this.fotoEspectaculo = fotoEspectaculo;
		this.capacidadTotal = capacidadTotal;
		this.capacidadRestante = capacidadRestante;
		
		//this.ubicacion = ubicacion;
		
	}

/*
	// constructor
	public Espectaculo(int id_espectaculo, String fecha, int capacidadTotal, int capacidadRestante, int precio,
			String ubicacion, String fotoEspectaculo) {
		super();
		this.id_espectaculo = id_espectaculo;
		this.fecha = fecha;
		this.capacidadTotal = capacidadTotal;
		this.capacidadRestante = capacidadRestante;
		this.precio = precio;
		this.ubicacion = ubicacion;
		this.fotoEspectaculo = fotoEspectaculo;
	}*/


	public Espectaculo() {
		// TODO Auto-generated constructor stub
	}


	// getters y setters
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getId_espectaculo() {
		return id_espectaculo;
	}


	public void setId_espectaculo(int id_espectaculo) {
		this.id_espectaculo = id_espectaculo;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public int getCapacidadTotal() {
		return capacidadTotal;
	}


	public void setCapacidadTotal(int capacidadTotal) {
		this.capacidadTotal = capacidadTotal;
	}


	public int getCapacidadRestante() {
		return capacidadRestante;
	}


	public void setCapacidadRestante(int capacidadRestante) {
		this.capacidadRestante = capacidadRestante;
	}


	public int getPrecio() {
		return precio;
	}


	public void setPrecio(int precio) {
		this.precio = precio;
	}


	public String getUbicacion() {
		return ubicacion;
	}


	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}


	public String getFotoEspectaculo() {
		return fotoEspectaculo;
	}


	public void setFotoEspectaculo(String fotoEspectaculo) {
		this.fotoEspectaculo = fotoEspectaculo;
	}
	
	

}
