
package UI;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import DB.MapperAdministradorUsuario;
import BLL.Usuario;

@SuppressWarnings("serial")
public class UI_AdministracionUsuarios extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JButton btnAgregarUsuario;
    private JButton btnModificarUsuario;
    private JButton btnEliminarUsuario;

    
    private JTextField textFieldDni;
    private JTextField textFieldNombre;
    private JTextField textFieldApellido;
    private JTextField textFieldEmail;
    private JTextField textFieldNombreUsuario;
    private JTextField textFieldPassword;
    private JTextField textFieldTipo;
    
    
    // Método para actualizar la tabla desde la lista de usuarios
    private void actualizarTabla(DefaultTableModel modelo, List<Usuario> usuarios) {
        modelo.setRowCount(0); // Limpia todas las filas existentes en el modelo

        // Llena el modelo con los datos de la lista de usuarios
        for (Usuario usuario : usuarios) {
            Object[] fila = {
                usuario.getDni(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getNombreUsuario(),
                usuario.getPassword(),
                usuario.getTipo()
            };
            modelo.addRow(fila);
        }
    }
    

    public UI_AdministracionUsuarios() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 822, 380);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 468, 319);
        contentPane.add(scrollPane);

        table = new JTable(new DefaultTableModel());

        DefaultTableModel modelo = (DefaultTableModel) table.getModel();

        // Iniciar la obtención de datos de la base de datos y configurar el modelo
        MapperAdministradorUsuario mapperAdminUsr = new MapperAdministradorUsuario();

        // Crea una lista para almacenar los usuarios
        List<Usuario> usuarios = mapperAdminUsr.mapperCargarTabla(modelo);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = table.getSelectedRow();
                if (fila >= 0) {
                    Usuario usuarioSeleccionado = usuarios.get(fila);

                    textFieldDni.setText(String.valueOf(usuarioSeleccionado.getDni()));
                    textFieldNombre.setText(usuarioSeleccionado.getNombre());
                    textFieldApellido.setText(usuarioSeleccionado.getApellido());
                    textFieldEmail.setText(usuarioSeleccionado.getEmail());
                    textFieldNombreUsuario.setText(usuarioSeleccionado.getNombreUsuario());
                    textFieldTipo.setText(String.valueOf(usuarioSeleccionado.getTipo()));
                }
            }
        });

        scrollPane.setViewportView(table);

        btnAgregarUsuario = new JButton("Agregar");
        btnAgregarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	// agrego el usuario en la BD
            	MapperAdministradorUsuario mapperAdminUsr = new MapperAdministradorUsuario();
				int dniInt = Integer.parseInt(textFieldDni.getText());
				int tipoInt = Integer.parseInt(textFieldTipo.getText());
				
				mapperAdminUsr.mapperCrearUsuario(dniInt, textFieldNombre.getText(), textFieldApellido.getText(), textFieldEmail.getText(), textFieldNombreUsuario.getText(), textFieldPassword.getText(),tipoInt );
				
				// lo agrego a la tabla UI
				
				// Actualiza la lista de usuarios con el nuevo usuario
		        Usuario nuevoUsuario = new Usuario();
		        nuevoUsuario.setDni(Integer.parseInt(textFieldDni.getText()));
		        nuevoUsuario.setNombre(textFieldNombre.getText());
		        nuevoUsuario.setApellido(textFieldApellido.getText());
		        nuevoUsuario.setEmail(textFieldEmail.getText());
		        nuevoUsuario.setNombreUsuario(textFieldNombreUsuario.getText());
		        nuevoUsuario.setTipo(Integer.parseInt(textFieldTipo.getText()));
		        usuarios.add(nuevoUsuario);

		        // Limpia los campos de texto
		        textFieldDni.setText("");
		        textFieldNombre.setText("");
		        textFieldApellido.setText("");
		        textFieldEmail.setText("");
		        textFieldNombreUsuario.setText("");
		        textFieldPassword.setText("");
		        textFieldTipo.setText("");

		        // Actualiza la tabla desde la lista de usuarios
		        actualizarTabla(modelo, usuarios);
				
				JOptionPane.showMessageDialog(null, "Usuario cargado exitosamente");
				
			
            }
        });
        
        // ===== Boton Modificar Usuario ==============
        
        btnAgregarUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnAgregarUsuario.setBounds(488, 242, 96, 34);
        contentPane.add(btnAgregarUsuario);

        btnModificarUsuario = new JButton("Modificar");
        btnModificarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int fila = table.getSelectedRow();
            	// Dentro del actionPerformed del botón de modificación
            	if (fila >= 0) {
            	    // Obtén el DNI del usuario seleccionado
            	    int dniUsuario = Integer.parseInt(textFieldDni.getText());

            	    // Obtén los nuevos valores de los campos de texto
            	    String nuevo_nombre = textFieldNombre.getText();
            	    String nuevo_apellido = textFieldApellido.getText();
            	    String nuevo_email = textFieldEmail.getText();
            	    String nuevo_nombre_usr = textFieldNombreUsuario.getText();
            	    String nueva_pass = textFieldPassword.getText();
            	    int nuevo_tipo = Integer.parseInt(textFieldTipo.getText());

            	    // Llama al método para actualizar en la base de datos
            	    MapperAdministradorUsuario mapperAdminUsr = new MapperAdministradorUsuario();
            	    mapperAdminUsr.mapperActualizarUsuario(dniUsuario, nuevo_nombre, nuevo_apellido, nuevo_email, nuevo_nombre_usr, nueva_pass, nuevo_tipo);

            	    // Actualiza la lista de usuarios con el usuario modificado
            	    Usuario usuarioActualizado = usuarios.get(fila);
            	    usuarioActualizado.setNombre(nuevo_nombre);
            	    usuarioActualizado.setApellido(nuevo_apellido);
            	    usuarioActualizado.setEmail(nuevo_email);
            	    usuarioActualizado.setNombreUsuario(nuevo_nombre_usr);
            	    usuarioActualizado.setPassword(nueva_pass);
            	    usuarioActualizado.setTipo(nuevo_tipo);

            	    // Limpia los campos de texto
            	    textFieldDni.setText("");
            	    textFieldNombre.setText("");
            	    textFieldApellido.setText("");
            	    textFieldEmail.setText("");
            	    textFieldNombreUsuario.setText("");
            	    textFieldPassword.setText("");
            	    textFieldTipo.setText("");

            	    // Actualiza la tabla utilizando el método actualizarTabla
            	    actualizarTabla(modelo, usuarios);
            	}

                
            }
            	
            
        });
        btnModificarUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnModificarUsuario.setBounds(594, 242, 96, 34);
        contentPane.add(btnModificarUsuario);

        btnEliminarUsuario = new JButton("Eliminar");
        btnEliminarUsuario.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		// lo borro de la base de datos
        		MapperAdministradorUsuario mapperAdminUsr = new MapperAdministradorUsuario();
        		mapperAdminUsr.mapperEliminarUsuario(Integer.parseInt(textFieldDni.getText()));
        		// lo borro de la tabla
        		int fila = table.getSelectedRow();
        		modelo.removeRow(fila);
        		
        		
        	}
        });
        btnEliminarUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnEliminarUsuario.setBounds(700, 242, 96, 34);
        contentPane.add(btnEliminarUsuario);

        JLabel lblDni = new JLabel("DNI");
        lblDni.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblDni.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDni.setBounds(535, 11, 87, 22);
        contentPane.add(lblDni);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNombre.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNombre.setBounds(535, 44, 87, 22);
        contentPane.add(lblNombre);

        JLabel lblApellido = new JLabel("Apellido");
        lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
        lblApellido.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblApellido.setBounds(535, 77, 87, 22);
        contentPane.add(lblApellido);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblEmail.setBounds(535, 110, 87, 22);
        contentPane.add(lblEmail);

        JLabel lblNombreUsuario = new JLabel("Nombre Usuario");
        lblNombreUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNombreUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNombreUsuario.setBounds(526, 143, 96, 22);
        contentPane.add(lblNombreUsuario);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblPassword.setBounds(535, 176, 87, 22);
        contentPane.add(lblPassword);

        JLabel lblTipo = new JLabel("Tipo");
        lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTipo.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTipo.setBounds(535, 209, 87, 22);
        contentPane.add(lblTipo);

        textFieldDni = new JTextField();
        textFieldDni.setBounds(632, 9, 164, 20);
        contentPane.add(textFieldDni);
        textFieldDni.setColumns(10);

        textFieldNombre = new JTextField();
        textFieldNombre.setColumns(10);
        textFieldNombre.setBounds(632, 46, 164, 20);
        contentPane.add(textFieldNombre);

        textFieldApellido = new JTextField();
        textFieldApellido.setColumns(10);
        textFieldApellido.setBounds(632, 79, 164, 20);
        contentPane.add(textFieldApellido);

        textFieldEmail = new JTextField();
        textFieldEmail.setColumns(10);
        textFieldEmail.setBounds(632, 112, 164, 20);
        contentPane.add(textFieldEmail);

        textFieldNombreUsuario = new JTextField();
        textFieldNombreUsuario.setColumns(10);
        textFieldNombreUsuario.setBounds(632, 145, 164, 20);
        contentPane.add(textFieldNombreUsuario);

        textFieldPassword = new JTextField();
        textFieldPassword.setColumns(10);
        textFieldPassword.setBounds(632, 178, 164, 20);
        contentPane.add(textFieldPassword);

        textFieldTipo = new JTextField();
        textFieldTipo.setColumns(10);
        textFieldTipo.setBounds(632, 211, 164, 20);
        contentPane.add(textFieldTipo);
        
        JButton btnLimpiarCampos = new JButton("Limpiar Campos");
        btnLimpiarCampos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		// Limpia los campos de texto
        	    textFieldDni.setText("");
        	    textFieldNombre.setText("");
        	    textFieldApellido.setText("");
        	    textFieldEmail.setText("");
        	    textFieldNombreUsuario.setText("");
        	    textFieldPassword.setText("");
        	    textFieldTipo.setText("");
        	}
        });
        btnLimpiarCampos.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnLimpiarCampos.setBounds(488, 287, 164, 34);
        contentPane.add(btnLimpiarCampos);
    }
}
