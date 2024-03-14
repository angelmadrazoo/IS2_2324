package es.unican.is2.FranquiciasUCGUI;
import es.unican.is2.FranquiciasUCCommon.*;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JLabel;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;

/**
 * Clase que implementa la vista del gerente dentro de la capa de
 * presentacion de la aplicacion usando Swing
 */
@SuppressWarnings("serial")
public class VistaGerente extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombreTienda;
	private JTextField txtTotalSueldos;
	private JTextField txtDireccionTienda;
	private JList<String> listNombreEmpleados;
	private DefaultListModel<String> listModel;
	private JButton btnBuscar;

	private IGestionTiendas tiendas;
	private IGestionEmpleados empleados;
	/**
	 * Create the frame.
	 */
	public VistaGerente(IGestionTiendas tiendas, IGestionEmpleados empleados) {
		this.tiendas = tiendas;
		this.empleados = empleados;
		init();
	}

	public void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 441, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		listModel = new DefaultListModel<String>();

		txtTotalSueldos = new JTextField();
		txtTotalSueldos.setBounds(230, 251, 86, 20);
		contentPane.add(txtTotalSueldos);
		txtTotalSueldos.setColumns(10);
		txtTotalSueldos.setName("txtTotalContribuyente");

		JLabel lblTotalSueldos = new JLabel("Total sueldos");
		lblTotalSueldos.setBounds(115, 254, 99, 14);
		contentPane.add(lblTotalSueldos);

		listNombreEmpleados = new JList<String>();
		listNombreEmpleados.setBounds(230, 98, 121, 116);
		contentPane.add(listNombreEmpleados);
		listNombreEmpleados.setBorder(new LineBorder(new Color(0, 0, 0)));
		listNombreEmpleados.setModel(listModel);
		listNombreEmpleados.setName("listNombreEmpleados");

		JLabel lblEmpleados = new JLabel("Empleados");
		lblEmpleados.setBounds(132, 103, 83, 14);
		contentPane.add(lblEmpleados);

		JLabel lblNombreContribuyente = new JLabel("Direccion");
		lblNombreContribuyente.setBounds(155, 54, 65, 14);
		contentPane.add(lblNombreContribuyente);

		txtDireccionTienda = new JTextField();
		txtDireccionTienda.setBounds(230, 51, 185, 20);
		contentPane.add(txtDireccionTienda);
		txtDireccionTienda.setColumns(10);
		txtDireccionTienda.setName("txtDireccionTienda");

		JLabel lblDatosTienda = new JLabel("Datos Tienda");
		lblDatosTienda.setBounds(230, 11, 149, 14);
		contentPane.add(lblDatosTienda);

		txtNombreTienda = new JTextField();
		txtNombreTienda.setBounds(10, 51, 113, 20);
		contentPane.add(txtNombreTienda);
		txtNombreTienda.setColumns(10);
		txtNombreTienda.setName("txtDireccionTienda");

		JLabel lblNombreTienda = new JLabel("Nombre Tienda");
		lblNombreTienda.setBounds(21, 27, 139, 14);
		contentPane.add(lblNombreTienda);
		lblNombreTienda.setName("lblNombreTienda");

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rellenaDatosTienda(txtNombreTienda.getText());
			}
		});
		btnBuscar.setBounds(21, 122, 89, 23);
		contentPane.add(btnBuscar);
		btnBuscar.setName("btnBuscar");
		listNombreEmpleados.setVisible(true);
	}
	
	
	private void rellenaDatosTienda(String nombre) {
		try {
		Tienda t = tiendas.tienda(nombre);
		if (t != null) {
			txtDireccionTienda.setText(t.getNombre());
			txtTotalSueldos.setText(Double.toString(t.gastoMensualSueldos()));
			listModel.removeAllElements();
			for (int i = 0; i < t.getEmpleados().size()-1; i++) {
				Empleado e = t.getEmpleados().get(i);
				listModel.addElement(e.getNombre());
			}
		} else {
			txtDireccionTienda.setText("Tienda no existe");
			txtTotalSueldos.setText("");
			listModel.removeAllElements();
		}
		}catch (DataAccessException e) {
			txtDireccionTienda.setText("Error acceso a datos");
			txtTotalSueldos.setText("");
			listModel.removeAllElements();
			e.printStackTrace();
		}

	}
}
