package UI;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.Espectaculo;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import DB.MapperAdministradorEspectaculo;


@SuppressWarnings("serial")
public class UI_AdministracionEspectaculos extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JButton btnAgregarEspectaculo;
    private JButton btnModificarEspectaculo;
    private JButton btnEliminarEspectaculo;

    private JTextField textFieldIdEspectaculo;
    private JTextField textFieldNombre;
    private JTextField textFieldFecha;
    private JTextField textFieldFoto;
    private JButton btnUbicaciones;

    // Método para actualizar la tabla desde la lista de espectáculos
    private void actualizarTabla(DefaultTableModel modelo, List<Espectaculo> espectaculos) {
        modelo.setRowCount(0); // Limpia todas las filas existentes en el modelo

        // Llena el modelo con los datos de la lista de espectáculos
        for (Espectaculo espectaculo : espectaculos) {
            Object[] fila = {
                espectaculo.getId_espectaculo(),
                espectaculo.getNombre(),
                espectaculo.getFecha(),
                //espectaculo.getPrecio(),
                espectaculo.getFotoEspectaculo(),
                //espectaculo.getCapacidadTotal(),
                //espectaculo.getCapacidadRestante()
            };
            modelo.addRow(fila);
        }
    }

    public UI_AdministracionEspectaculos() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 995, 512);
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
        MapperAdministradorEspectaculo mapperAdminEsp = new MapperAdministradorEspectaculo();

        // Crea una lista para almacenar los espectáculos
        List<Espectaculo> espectaculos = mapperAdminEsp.mapperCargarTabla(modelo);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = table.getSelectedRow();
                if (fila >= 0) {
                    Espectaculo espectaculoSeleccionado = espectaculos.get(fila);

                    textFieldIdEspectaculo.setText(String.valueOf(espectaculoSeleccionado.getId_espectaculo()));
                    textFieldNombre.setText(espectaculoSeleccionado.getNombre());
                    textFieldFecha.setText(espectaculoSeleccionado.getFecha());
                    //textFieldPrecio.setText(String.valueOf(espectaculoSeleccionado.getPrecio()));
                    textFieldFoto.setText(espectaculoSeleccionado.getFotoEspectaculo());
                    //textFieldCapacidadTotal.setText(String.valueOf(espectaculoSeleccionado.getCapacidadTotal()));
                    //textFieldCapacidadRestante.setText(String.valueOf(espectaculoSeleccionado.getCapacidadRestante()));
                }
            }
        });

        scrollPane.setViewportView(table);

        btnAgregarEspectaculo = new JButton("Agregar");
        btnAgregarEspectaculo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Agregar espectáculo a la base de datos
                MapperAdministradorEspectaculo mapperAdminEsp = new MapperAdministradorEspectaculo();
                int idEspectaculo = Integer.parseInt(textFieldIdEspectaculo.getText());
                String nombre = textFieldNombre.getText();
                String fecha = textFieldFecha.getText();
                //int precio = Integer.parseInt(textFieldPrecio.getText());
                String foto = textFieldFoto.getText();
                //int capacidadTotal = Integer.parseInt(textFieldCapacidadTotal.getText());
                //int capacidadRestante = Integer.parseInt(textFieldCapacidadRestante.getText());

                mapperAdminEsp.mapperCrearEspectaculo(idEspectaculo, nombre, fecha, foto);

                // Agregar el espectáculo a la tabla UI
                Espectaculo nuevoEspectaculo = new Espectaculo(idEspectaculo, nombre, fecha, foto);
                espectaculos.add(nuevoEspectaculo);

                // Limpiar los campos de texto
                textFieldIdEspectaculo.setText("");
                textFieldNombre.setText("");
                textFieldFecha.setText("");
                //textFieldPrecio.setText("");
                textFieldFoto.setText("");
                //textFieldCapacidadTotal.setText("");
                //textFieldCapacidadRestante.setText("");

                // Actualizar la tabla desde la lista de espectáculos
                actualizarTabla(modelo, espectaculos);

                JOptionPane.showMessageDialog(null, "Espectáculo cargado exitosamente");
            }
        });
        
        
        btnAgregarEspectaculo.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnAgregarEspectaculo.setBounds(488, 143, 96, 34);
        contentPane.add(btnAgregarEspectaculo);
        
        //========== Modificar Espectaculo ==========================
        
        btnModificarEspectaculo = new JButton("Modificar");
        btnModificarEspectaculo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Código para modificar aquí
                int fila = table.getSelectedRow();
                if (fila >= 0) {
                    int idEspectaculo = Integer.parseInt(textFieldIdEspectaculo.getText());
                    String nombre = textFieldNombre.getText();
                    String fecha = textFieldFecha.getText();
                    //int precio = Integer.parseInt(textFieldPrecio.getText());
                    String foto = textFieldFoto.getText();
                    //int capacidadTotal = Integer.parseInt(textFieldCapacidadTotal.getText());
                    //int capacidadRestante = Integer.parseInt(textFieldCapacidadRestante.getText());

                    // Llama al método para actualizar en la base de datos
                    MapperAdministradorEspectaculo mapperAdminEsp = new MapperAdministradorEspectaculo();
                    mapperAdminEsp.mapperModificarEspectaculo(idEspectaculo, nombre, fecha, foto);

                    // Actualiza la lista de espectáculos con el espectáculo modificado
                    Espectaculo espectaculoActualizado = espectaculos.get(fila);
                    espectaculoActualizado.setNombre(nombre);
                    espectaculoActualizado.setFecha(fecha);
                    //espectaculoActualizado.setPrecio(precio);
                    espectaculoActualizado.setFotoEspectaculo(foto);
                    //espectaculoActualizado.setCapacidadTotal(capacidadTotal);
                    //espectaculoActualizado.setCapacidadRestante(capacidadRestante);

                    // Limpia los campos de texto
                    textFieldIdEspectaculo.setText("");
                    textFieldNombre.setText("");
                    textFieldFecha.setText("");
                    //textFieldPrecio.setText("");
                    textFieldFoto.setText("");
                    //textFieldCapacidadTotal.setText("");
                    //textFieldCapacidadRestante.setText("");

                    // Actualiza la tabla utilizando el método actualizarTabla
                    actualizarTabla(modelo, espectaculos);
                }
            }
        });
        btnModificarEspectaculo.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnModificarEspectaculo.setBounds(488, 188, 96, 34);
        contentPane.add(btnModificarEspectaculo);

        btnEliminarEspectaculo = new JButton("Eliminar");
        btnEliminarEspectaculo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Eliminar el espectáculo de la base de datos
                MapperAdministradorEspectaculo mapperAdminEsp = new MapperAdministradorEspectaculo();
                int idEspectaculo = Integer.parseInt(textFieldIdEspectaculo.getText());
                mapperAdminEsp.mapperEliminarEspectaculo(idEspectaculo);

                // Eliminar el espectáculo de la tabla
                int fila = table.getSelectedRow();
                modelo.removeRow(fila);
            }
        });
        btnEliminarEspectaculo.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnEliminarEspectaculo.setBounds(488, 233, 96, 34);
        contentPane.add(btnEliminarEspectaculo);

        JLabel lblIdEspectaculo = new JLabel("ID Espectaculo");
        lblIdEspectaculo.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblIdEspectaculo.setHorizontalAlignment(SwingConstants.RIGHT);
        lblIdEspectaculo.setBounds(500, 11, 120, 22);
        contentPane.add(lblIdEspectaculo);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNombre.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNombre.setBounds(500, 44, 120, 22);
        contentPane.add(lblNombre);

        JLabel lblFecha = new JLabel("Fecha");
        lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFecha.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblFecha.setBounds(500, 77, 120, 22);
        contentPane.add(lblFecha);

        JLabel lblFoto = new JLabel("Foto");
        lblFoto.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFoto.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblFoto.setBounds(500, 110, 120, 22);
        contentPane.add(lblFoto);

        textFieldIdEspectaculo = new JTextField();
        textFieldIdEspectaculo.setBounds(632, 9, 164, 20);
        contentPane.add(textFieldIdEspectaculo);
        textFieldIdEspectaculo.setColumns(10);

        textFieldNombre = new JTextField();
        textFieldNombre.setColumns(10);
        textFieldNombre.setBounds(632, 46, 164, 20);
        contentPane.add(textFieldNombre);

        textFieldFecha = new JTextField();
        textFieldFecha.setColumns(10);
        textFieldFecha.setBounds(632, 79, 164, 20);
        contentPane.add(textFieldFecha);

        textFieldFoto = new JTextField();
        textFieldFoto.setColumns(10);
        textFieldFoto.setBounds(632, 110, 164, 20);
        contentPane.add(textFieldFoto);

        JButton btnLimpiarCampos = new JButton("Limpiar");
        btnLimpiarCampos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Limpiar los campos de texto
                textFieldIdEspectaculo.setText("");
                textFieldNombre.setText("");
                textFieldFecha.setText("");
                //textFieldPrecio.setText("");
                textFieldFoto.setText("");
                //textFieldCapacidadTotal.setText("");
                //textFieldCapacidadRestante.setText("");
            }
        });
        btnLimpiarCampos.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnLimpiarCampos.setBounds(488, 278, 96, 34);
        contentPane.add(btnLimpiarCampos);
        
        btnUbicaciones = new JButton("Ubicaciones");
        btnUbicaciones.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnUbicaciones.setBounds(642, 143, 134, 34);
        contentPane.add(btnUbicaciones);
    }
}
