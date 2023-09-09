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
    private JTextField textFieldPrecio;
    private JTextField textFieldFoto;
    private JTextField textFieldCapacidadTotal;
    private JTextField textFieldCapacidadRestante;

    // Método para actualizar la tabla desde la lista de espectáculos
    private void actualizarTabla(DefaultTableModel modelo, List<Espectaculo> espectaculos) {
        modelo.setRowCount(0); // Limpia todas las filas existentes en el modelo

        // Llena el modelo con los datos de la lista de espectáculos
        for (Espectaculo espectaculo : espectaculos) {
            Object[] fila = {
                espectaculo.getId_espectaculo(),
                espectaculo.getNombre(),
                espectaculo.getFecha(),
                espectaculo.getPrecio(),
                espectaculo.getFotoEspectaculo(),
                espectaculo.getCapacidadTotal(),
                espectaculo.getCapacidadRestante()
            };
            modelo.addRow(fila);
        }
    }

    public UI_AdministracionEspectaculos() {
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
                    textFieldPrecio.setText(String.valueOf(espectaculoSeleccionado.getPrecio()));
                    textFieldFoto.setText(espectaculoSeleccionado.getFotoEspectaculo());
                    textFieldCapacidadTotal.setText(String.valueOf(espectaculoSeleccionado.getCapacidadTotal()));
                    textFieldCapacidadRestante.setText(String.valueOf(espectaculoSeleccionado.getCapacidadRestante()));
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
                int precio = Integer.parseInt(textFieldPrecio.getText());
                String foto = textFieldFoto.getText();
                int capacidadTotal = Integer.parseInt(textFieldCapacidadTotal.getText());
                int capacidadRestante = Integer.parseInt(textFieldCapacidadRestante.getText());

                mapperAdminEsp.mapperCrearEspectaculo(idEspectaculo, nombre, fecha, precio, foto, capacidadTotal, capacidadRestante);

                // Agregar el espectáculo a la tabla UI
                Espectaculo nuevoEspectaculo = new Espectaculo(idEspectaculo, nombre, fecha, precio, foto, capacidadTotal, capacidadRestante);
                espectaculos.add(nuevoEspectaculo);

                // Limpiar los campos de texto
                textFieldIdEspectaculo.setText("");
                textFieldNombre.setText("");
                textFieldFecha.setText("");
                textFieldPrecio.setText("");
                textFieldFoto.setText("");
                textFieldCapacidadTotal.setText("");
                textFieldCapacidadRestante.setText("");

                // Actualizar la tabla desde la lista de espectáculos
                actualizarTabla(modelo, espectaculos);

                JOptionPane.showMessageDialog(null, "Espectáculo cargado exitosamente");
            }
        });
        btnAgregarEspectaculo.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnAgregarEspectaculo.setBounds(488, 242, 96, 34);
        contentPane.add(btnAgregarEspectaculo);

        btnModificarEspectaculo = new JButton("Modificar");
        btnModificarEspectaculo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Código para modificar aquí
                int fila = table.getSelectedRow();
                if (fila >= 0) {
                    int idEspectaculo = Integer.parseInt(textFieldIdEspectaculo.getText());
                    String nombre = textFieldNombre.getText();
                    String fecha = textFieldFecha.getText();
                    int precio = Integer.parseInt(textFieldPrecio.getText());
                    String foto = textFieldFoto.getText();
                    int capacidadTotal = Integer.parseInt(textFieldCapacidadTotal.getText());
                    int capacidadRestante = Integer.parseInt(textFieldCapacidadRestante.getText());

                    // Llama al método para actualizar en la base de datos
                    MapperAdministradorEspectaculo mapperAdminEsp = new MapperAdministradorEspectaculo();
                    mapperAdminEsp.mapperModificarEspectaculo(idEspectaculo, nombre, fecha, precio, foto, capacidadTotal, capacidadRestante);

                    // Actualiza la lista de espectáculos con el espectáculo modificado
                    Espectaculo espectaculoActualizado = espectaculos.get(fila);
                    espectaculoActualizado.setNombre(nombre);
                    espectaculoActualizado.setFecha(fecha);
                    espectaculoActualizado.setPrecio(precio);
                    espectaculoActualizado.setFotoEspectaculo(foto);
                    espectaculoActualizado.setCapacidadTotal(capacidadTotal);
                    espectaculoActualizado.setCapacidadRestante(capacidadRestante);

                    // Limpia los campos de texto
                    textFieldIdEspectaculo.setText("");
                    textFieldNombre.setText("");
                    textFieldFecha.setText("");
                    textFieldPrecio.setText("");
                    textFieldFoto.setText("");
                    textFieldCapacidadTotal.setText("");
                    textFieldCapacidadRestante.setText("");

                    // Actualiza la tabla utilizando el método actualizarTabla
                    actualizarTabla(modelo, espectaculos);
                }
            }
        });
        btnModificarEspectaculo.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnModificarEspectaculo.setBounds(594, 242, 96, 34);
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
        btnEliminarEspectaculo.setBounds(700, 242, 96, 34);
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

        JLabel lblPrecio = new JLabel("Precio");
        lblPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblPrecio.setBounds(500, 110, 120, 22);
        contentPane.add(lblPrecio);

        JLabel lblFoto = new JLabel("Foto");
        lblFoto.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFoto.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblFoto.setBounds(500, 143, 120, 22);
        contentPane.add(lblFoto);

        JLabel lblCapacidadTotal = new JLabel("Capacidad Total");
        lblCapacidadTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCapacidadTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCapacidadTotal.setBounds(500, 176, 120, 22);
        contentPane.add(lblCapacidadTotal);

        JLabel lblCapacidadRestante = new JLabel("Capacidad Restante");
        lblCapacidadRestante.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCapacidadRestante.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCapacidadRestante.setBounds(500, 209, 120, 22);
        contentPane.add(lblCapacidadRestante);

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

        textFieldPrecio = new JTextField();
        textFieldPrecio.setColumns(10);
        textFieldPrecio.setBounds(632, 112, 164, 20);
        contentPane.add(textFieldPrecio);

        textFieldFoto = new JTextField();
        textFieldFoto.setColumns(10);
        textFieldFoto.setBounds(632, 145, 164, 20);
        contentPane.add(textFieldFoto);

        textFieldCapacidadTotal = new JTextField();
        textFieldCapacidadTotal.setColumns(10);
        textFieldCapacidadTotal.setBounds(632, 178, 164, 20);
        contentPane.add(textFieldCapacidadTotal);

        textFieldCapacidadRestante = new JTextField();
        textFieldCapacidadRestante.setColumns(10);
        textFieldCapacidadRestante.setBounds(632, 211, 164, 20);
        contentPane.add(textFieldCapacidadRestante);

        JButton btnLimpiarCampos = new JButton("Limpiar Campos");
        btnLimpiarCampos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Limpiar los campos de texto
                textFieldIdEspectaculo.setText("");
                textFieldNombre.setText("");
                textFieldFecha.setText("");
                textFieldPrecio.setText("");
                textFieldFoto.setText("");
                textFieldCapacidadTotal.setText("");
                textFieldCapacidadRestante.setText("");
            }
        });
        btnLimpiarCampos.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnLimpiarCampos.setBounds(488, 287, 164, 34);
        contentPane.add(btnLimpiarCampos);
    }
}
