package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Usuario;
import DB.MapperLogin;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



@SuppressWarnings("serial")
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField txtPasswordField;
	
	// creo variables de los otros frames
	public static Home frmAdmin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(115, 108, 155, 20);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
		lblLogin.setBounds(32, 11, 59, 27);
		contentPane.add(lblLogin);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsuario.setBounds(32, 106, 59, 20);
		contentPane.add(lblUsuario);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(32, 170, 84, 20);
		contentPane.add(lblPassword);
		
		txtPasswordField = new JPasswordField();
		txtPasswordField.setBounds(115, 172, 155, 20);
		contentPane.add(txtPasswordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				MapperLogin mapperLogin = new MapperLogin();
				Usuario usr = new Usuario();
				
				
				String pass = new String(txtPasswordField.getPassword());
				// si no hay campo vacio
				if(!textUsuario.getText().equals("")&& !pass.equals("")) {
					usr.setNombreUsuario(textUsuario.getText());
					System.out.println("Le estoy mandando el nombre de usuario: " +textUsuario.getText());
		            usr.setPassword(pass);
		            System.out.println("Le estoy mandando la pass: " + usr.getPassword());
		            
		            // Veo si es vendedor (2) o Administrador (1)
		            
					
					if(mapperLogin.mapperBotonLogin(usr)) {// los datos son correctos
						System.out.println("Los datos son correctos");
						JOptionPane.showMessageDialog(null, "Ingreso correcto.");
						//PREGUNTA: POR QUE MODIFICA AL USUARIO SI mapperLogin solo devuelve un boolean?!
						System.out.println("A ver si funciona la magia:");
						System.out.println("El email del usr que me afane es:"+ usr.getEmail());
						
						// si es administrador, vera todo
						if(usr.getTipo() == 1) {
							System.out.println("ES UN ADMIN");
							//Login.this.dispose();
							dispose(); // Cierra la ventana actual del Login

						    // Abre la ventana Home
						    frmAdmin = new Home();
						    frmAdmin.setVisible(true);
							
							
						}else if (usr.getTipo() == 2) {
							System.out.println("ES VENDEDOR");
						}
						
					}else {// los datos son incorrectos
						JOptionPane.showMessageDialog(null, "Datos incorrectos");
					}
					
				}else {//los datos estan vacios
					JOptionPane.showMessageDialog(null, "Debe ingresar los datos.");
				}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogin.setBounds(153, 232, 89, 23);
		contentPane.add(btnLogin);
	}
}
