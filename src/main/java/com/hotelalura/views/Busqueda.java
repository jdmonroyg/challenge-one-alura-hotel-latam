package com.hotelalura.views;

import com.hotelalura.controller.HuespedController;
import com.hotelalura.controller.ReservaController;
import com.hotelalura.models.Huesped;
import com.hotelalura.models.Reserva;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.hotelalura.utility.UtilidadesValidacion.validarNumero;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modeloReserva;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private ReservaController reservasController;
	private HuespedController huespedController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
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
	public Busqueda() {
		this.reservasController=new ReservaController();
		this.huespedController=new HuespedController();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		
		
		
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloReserva = (DefaultTableModel) tbReservas.getModel();
		modeloReserva.addColumn("Numero de Reserva");
		modeloReserva.addColumn("Fecha Check In");
		modeloReserva.addColumn("Fecha Check Out");
		modeloReserva.addColumn("Valor");
		modeloReserva.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Objects.requireNonNull(Busqueda.class.getResource("/imagenes/reservado.png"))), scroll_table, null);
		scroll_table.setVisible(true);
		cargarTablaReservas();
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Objects.requireNonNull(Busqueda.class.getResource("/imagenes/pessoas.png"))), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		cargarTablaHuespedes();

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Objects.requireNonNull(Busqueda.class.getResource("/imagenes/Ha-100px.png"))));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarTabla();
				String textoBuscado = txtBuscar.getText();
				if (!textoBuscado.isEmpty()){
					if (validarNumero(textoBuscado)){
						cargarTablaHuespedesId();
						cargarTablaReservasId();
					}
					else {
						buscarNombreOrApellido(textoBuscado);
					}
				}else {
					cargarTablaHuespedes();
					cargarTablaReservas();
				}
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tbHuespedes.getSelectedRow()>=0){
					actualizarHuesped();
					actualizarTabla();
				}else if (tbReservas.getSelectedRow()>=0){
					actualizarReserva();
					actualizarTabla();
				}
				else {
					JOptionPane.showMessageDialog(null,"Por favor, elije un item");
				}

			}
		});
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);

		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tbHuespedes.getSelectedRow()>=0){
					eliminarHuesped();
					actualizarTabla();
				}else if (tbReservas.getSelectedRow()>=0){
					eliminarReserva();
					actualizarTabla();
				}
				else {
					JOptionPane.showMessageDialog(null,"Por favor, elije un item");
				}
			}
		});
	}




	private void cargarTablaReservas(){
		this.reservasController.listar().forEach(reserva ->
			modeloReserva.addRow(new Object[]{
					reserva.getId(),
					reserva.getFechaEntrada(),
					reserva.getFechaSalida(),
					reserva.getValor(),
					reserva.getFormaPago()
			})
		);
	}
	private void cargarTablaReservasId(){
		this.reservasController.buscarId(txtBuscar.getText()).forEach(reserva ->
				modeloReserva.addRow(new Object[]{
						reserva.getId(),
						reserva.getFechaEntrada(),
						reserva.getFechaSalida(),
						reserva.getValor(),
						reserva.getFormaPago()
				})
		);
	}
	private void cargarTablaHuespedes(){
		this.huespedController.listar().forEach( huesped ->
				modeloHuesped.addRow(new Object[]{
						huesped.getId(),
						huesped.getNombre(),
						huesped.getApellido(),
						huesped.getFechaNacimiento(),
						huesped.getNacionalidad(),
						huesped.getTelefono(),
						huesped.getIdReserva()
				})
		);
	}
	private void cargarTablaHuespedesId(){
		this.huespedController.buscarId(txtBuscar.getText()).forEach( huesped ->
				modeloHuesped.addRow(new Object[]{
						huesped.getId(),
						huesped.getNombre(),
						huesped.getApellido(),
						huesped.getFechaNacimiento(),
						huesped.getNacionalidad(),
						huesped.getTelefono(),
						huesped.getIdReserva()
				})
		);
	}

	public void buscarNombreOrApellido(String searchText) {
		System.out.println("aca");
		List<Object> resultados = this.huespedController.buscarbuscarNombreOrApellido(searchText);

		resultados.forEach(resultado -> {
			if (resultado instanceof Huesped) {
				Huesped huesped = (Huesped) resultado;
				modeloHuesped.addRow(new Object[]{
						huesped.getId(),
						huesped.getNombre(),
						huesped.getApellido(),
						huesped.getFechaNacimiento(),
						huesped.getNacionalidad(),
						huesped.getTelefono(),
						huesped.getIdReserva()
				});
			} else if (resultado instanceof Reserva) {
				Reserva reserva = (Reserva) resultado;
				modeloReserva.addRow(new Object[]{
						reserva.getId(),
						reserva.getFechaEntrada(),
						reserva.getFechaSalida(),
						reserva.getValor(),
						reserva.getFormaPago()
				});
			}
		});
	}
	private void actualizarHuesped(){
		Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),tbHuespedes.getSelectedColumn()))
				.ifPresent(filaHuesped ->{
					try{
						int idHuesped=Integer.parseInt(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),0).toString());
						String nombre=modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),1).toString();
						String apellido=modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),2).toString();
						Date fechaNacimiento=Date.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),3).toString());
						String nacionalidad=modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),4).toString();
						String telefono=modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),5).toString();
						int idReserva= Integer.parseInt(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),6).toString());
						this.huespedController.modificar(new Huesped(idHuesped,nombre,apellido,fechaNacimiento,nacionalidad,telefono,idReserva));
						JOptionPane.showMessageDialog(this, String.format("El id-%d fue modificado con exito!",idHuesped));
					}
					catch (Exception e){
						JOptionPane.showMessageDialog(this, "Recuerde verificar los tipos de datos de los campos");
					}
				});
	}
	private void actualizarReserva() {
		Optional.ofNullable(modeloReserva.getValueAt(tbReservas.getSelectedRow(),tbReservas.getSelectedColumn()))
				.ifPresent(filaReserva ->{
					try{
						int idReserva=Integer.parseInt(modeloReserva.getValueAt(tbReservas.getSelectedRow(),0).toString());
						Date fechaEntrada= Date.valueOf(modeloReserva.getValueAt(tbReservas.getSelectedRow(),1).toString());
						Date fechaSalida= Date.valueOf(modeloReserva.getValueAt(tbReservas.getSelectedRow(),2).toString());
						double valor= Double.parseDouble(modeloReserva.getValueAt(tbReservas.getSelectedRow(),3).toString());
						String formaPago=modeloReserva.getValueAt(tbReservas.getSelectedRow(),4).toString();
						this.reservasController.modificar(new Reserva(idReserva,fechaEntrada,fechaSalida,valor,formaPago));
						JOptionPane.showMessageDialog(this, String.format("El id-%d fue modificado con exito!",idReserva));
					}catch (Exception e){
						JOptionPane.showMessageDialog(this, "Recuerde verificar los tipos de datos de los campos");
					}

				});
	}

	private void eliminarReserva(){
		Optional.ofNullable( modeloReserva.getValueAt(tbReservas.getSelectedRow(),tbReservas.getSelectedColumn()))
				.ifPresent(filaReserva -> {
					int idReserva=Integer.parseInt(modeloReserva.getValueAt(tbReservas.getSelectedRow(),0).toString());
					this.reservasController.eliminar(idReserva);
					JOptionPane.showMessageDialog(this, String.format("El id-%d de reservas y el huesped asociado con exito!",idReserva));
				});
	}
	private void eliminarHuesped(){
		Optional.ofNullable( modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),tbHuespedes.getSelectedColumn()))
				.ifPresent(filaHuesped -> {
					int idHuesped=Integer.parseInt(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),0).toString());
					int idReserva=Integer.parseInt(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),6).toString());
					// foreing key option, activado on delete cascade
					this.reservasController.eliminar(idReserva);
					JOptionPane.showMessageDialog(this, String.format("El id-%d de reserva y el id-%d de huesped fue eliminado con exito!",idHuesped, idReserva));
				});
	}

	private void actualizarTabla() {
		limpiarTabla();
		cargarTablaHuespedes();
		cargarTablaReservas();
	}


	private void limpiarTabla() {
		((DefaultTableModel) tbHuespedes.getModel()).setRowCount(0);
		((DefaultTableModel) tbReservas.getModel()).setRowCount(0);
	}
	
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
		}
}
