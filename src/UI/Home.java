package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Home extends JFrame {

	private JPanel contentPane;
	
	public static UI_AdministracionUsuarios frmAdminUsuarios;
	public static UI_AdministracionEspectaculos frmAdminEspectaculos;

	/**
	 * Create the frame.
	 */
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnUsuario = new JButton("Administracion de Usuarios");
		btnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Vamos al menu de Administrar Usuarios");
				//Login.this.dispose();
				//dispose(); // Cierra la ventana actual del Login

			    // Abre la ventana Home
				frmAdminUsuarios = new UI_AdministracionUsuarios();
				frmAdminUsuarios.setVisible(true);
			}
		});
		btnUsuario.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnUsuario.setBounds(10, 11, 246, 36);
		contentPane.add(btnUsuario);
		
		JButton btnAdministracionDeEspectaculos = new JButton("Administracion de Espectaculos");
		btnAdministracionDeEspectaculos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Vamos al menu de Administrar Espectaculos");
				//Login.this.dispose();
				//dispose(); // Cierra la ventana actual del Login

			    // Abre la ventana Home
				frmAdminEspectaculos = new UI_AdministracionEspectaculos();
				frmAdminEspectaculos.setVisible(true);
				
			}
		});
		btnAdministracionDeEspectaculos.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAdministracionDeEspectaculos.setBounds(10, 58, 246, 36);
		contentPane.add(btnAdministracionDeEspectaculos);
		
		JButton btnReporte = new JButton("Reporte de Ventas");
		btnReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnReporte.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnReporte.setBounds(10, 105, 246, 36);
		contentPane.add(btnReporte);
		
		JButton btnAdministracionDePromociones = new JButton("Administracion de Promociones");
		btnAdministracionDePromociones.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAdministracionDePromociones.setBounds(10, 152, 246, 36);
		contentPane.add(btnAdministracionDePromociones);
	}
}
