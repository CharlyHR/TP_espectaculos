package BLL;

public class Usuario{

	private String nombre;
	private String apellido;
	private int dni;
	private String email;
	private String nombreUsuario;
	private String password;
	
	
	
	// constructor
	
	public Usuario(String nombre, String apellido, int dni, String email, String nombreUsuario, String password) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
	}

	// getters
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public int getDni() {
		return dni;
	}
	
	// setters
	
	public void setDni(int dni) {
		this.dni = dni;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
		
}
