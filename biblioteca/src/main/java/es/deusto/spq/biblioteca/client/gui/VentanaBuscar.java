package es.deusto.spq.biblioteca.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import es.deusto.spq.biblioteca.controller.*;
import es.deusto.spq.biblioteca.dao.ReservaDAO;
import es.deusto.spq.biblioteca.data.Reserva;
import es.deusto.spq.biblioteca.data.Sala;


public class VentanaBuscar extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel lnumPlazas, lfecha, lhora;
	private JButton reservar, disponible, inicio, verReserva;
	private JTextField txtcodSala,txtfecha, txthora;
	int capacidad;
	private DefaultTableModel modelo = new DefaultTableModel();
	private Controller controller = null;
	private JTable tablaSalasDisponibles;
	List<Sala> consultarPlazas = new ArrayList<>();

	
	public VentanaBuscar(Controller controller){
		this.controller = controller;
		lanzarventana();
		this.setVisible(true);
	}

	
	public void lanzarventana(){
		setTitle("ventana buscar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900, 500);
		panel = new JPanel();
		panel.setBounds(0, 0, 1400, 300);
		setContentPane(panel);
		panel.setLayout(null);
		
		lnumPlazas = new JLabel("Numero de personas:");
		lnumPlazas.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lnumPlazas.setBounds(550, 150, 300, 80);
		panel.add(lnumPlazas);
		
		lfecha = new JLabel("Fecha:");
		lfecha.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lfecha.setBounds(550, 233, 300, 80);
		
		lhora = new JLabel("Hora:");
		lhora.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lhora.setBounds(550, 316, 300, 80);
		
		txtfecha = new JTextField();
		txtfecha.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtfecha.setColumns(10);
		txtfecha.setBounds(550, 399, 300, 80);
		
		txthora = new JTextField();
		txthora.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txthora.setColumns(10);
		txthora.setBounds(550, 482, 300, 80);

		
		txtcodSala = new JTextField();
		txtcodSala.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtcodSala.setColumns(10);
		txtcodSala.setBounds(550, 565, 300, 80);
		
		reservar = new JButton();
		reservar.setText("Reservar una sala");
		verReserva = new JButton();
		verReserva.setText("Ver tus reservas");
		inicio = new JButton();
		inicio.setText("Volver al menu principal");
		
		
		reservar.setFocusable(false);
		reservar.setBounds(550, 150, 300, 80);
		verReserva.setFocusable(false);
		verReserva.setBounds(550, 233, 300, 80);
		inicio.setFocusable(false);
		inicio.setBounds(550, 316, 300, 80);
		
		
		
		inicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaBuscar.this.setVisible(false);
				VentanaLogin v = new VentanaLogin(controller);
				v.setVisible(true);
		
			}
		});
		
		verReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaBuscar.this.setVisible(true);
				VerReservas vr = new VerReservas(controller);
				vr.setVisible(true);
			}
		});	
		
		tablaSalasDisponibles = new JTable();
		tablaSalasDisponibles.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id sala","DNI responsable", "Fecha", "Hora", "Capacidad"
			}
			
		));
		
		reservar.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				if (tablaSalasDisponibles.getSelectedRow() >= 0) { 
					System.out.println(tablaSalasDisponibles.getValueAt(tablaSalasDisponibles.getSelectedRow(), 0));
					try{
						String id_sala = (String)tablaSalasDisponibles.getValueAt(tablaSalasDisponibles.getSelectedRow(), 0);
						String dni_respon = (String)tablaSalasDisponibles.getValueAt(tablaSalasDisponibles.getSelectedRow(), 1);
						String fecha = (String)tablaSalasDisponibles.getValueAt(tablaSalasDisponibles.getSelectedRow(), 2);
						String hora = (String)tablaSalasDisponibles.getValueAt(tablaSalasDisponibles.getSelectedRow(), 3);
						int capacidad = (int)tablaSalasDisponibles.getValueAt(tablaSalasDisponibles.getSelectedRow(), 4);
						controller.getCl().getService().anyadirReserva( id_sala,  dni_respon,  fecha,  hora,  capacidad);
		
						modelo = (DefaultTableModel)tablaSalasDisponibles.getModel();
						JOptionPane.showInputDialog(null, "has echo una reserva", JOptionPane.INFORMATION_MESSAGE);
					}catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				
			}	
	
	}
			}
	});
		panel.add(reservar);
		panel.add(inicio);
		panel.add(verReserva);



	}
	private void cargarReserva(Reserva r){
		modelo = (DefaultTableModel)tablaSalasDisponibles.getModel();
		String id_sala = r.getId_sala();
		String dni_respon = r.getDni_respon();
		String fecha = r.getFecha();
		String hora = r.getHora();
		int capacidad = r.getPlazas();

		Object[] fila = {id_sala, dni_respon, fecha,hora, capacidad};
		modelo.addRow(fila);                    
	}
	
	
	public void ejecutarVentana() {
		// TODO Auto-generated method stub
		try {
			final VentanaBuscar ventanaReserva = new VentanaBuscar(controller);
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					ventanaReserva.setVisible(true);
				}
			});
		} catch (Exception e) {
			System.exit(1); 
		}

	}
}
