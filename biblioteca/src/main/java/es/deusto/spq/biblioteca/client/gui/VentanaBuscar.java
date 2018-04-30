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
	private JButton reservar, disponible;
	private JTextField txtcodSala,txtfecha, txthora;
	int capacidad;
	private JScrollPane scroll;
	private static VentanaBuscar INSTANCE;
	private Controller controller = null;
	private JTable tablaSalasDisponibles;
	private JComboBox<String> NumAsistentes;
	private DefaultTableModel modelo = new DefaultTableModel();
	List<Sala> consultarPlazas = new ArrayList<>();


	public static VentanaBuscar getInstance() {
		return INSTANCE;
	}
	
	public void dispose(){
		INSTANCE.setVisible(false);
	}
	
	public VentanaBuscar(Controller controller){
		this.controller = controller;
		
	}
	
	public void ventana(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 572);
		panel = new JPanel();
		setContentPane(panel);
		panel.setLayout(null);
		
		lnumPlazas = new JLabel("Numero de personas:");
		lnumPlazas.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lnumPlazas.setBounds(10, 38, 75, 20);
		panel.add(lnumPlazas);
		
		lfecha = new JLabel("Fecha:");
		lfecha.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lfecha.setBounds(10, 38, 75, 20);
		panel.add(lfecha);
		
		lhora = new JLabel("Hora:");
		lhora.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lhora.setBounds(10, 38, 75, 20);
		panel.add(lhora);
		
		txtfecha = new JTextField();
		txtfecha.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtfecha.setColumns(10);
		txtfecha.setBounds(84, 119, 108, 25);
		panel.add(txtfecha);
		
		txthora = new JTextField();
		txthora.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txthora.setColumns(10);
		txthora.setBounds(84, 119, 108, 25);
		panel.add(txthora);
		
		txtcodSala = new JTextField();
		txtcodSala.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		txtcodSala.setColumns(10);
		txtcodSala.setBounds(84, 119, 108, 25);
		panel.add(txtcodSala);
		
		disponible = new JButton("Salas disponibles");
		disponible.setFont(new Font("Times New Roman", Font.PLAIN, 13));		
		disponible.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				List<Reserva> reservas = new ArrayList<>();
				for (Reserva r : reservas) {
					if (r.getId_sala().equals(txtcodSala) && r.getFecha().equals(txtfecha) && r.getHora().equals(txthora)){
						
					}
					}
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
		scroll.setViewportView(tablaSalasDisponibles);
		
		reservar = new JButton("Reservar");
		reservar.setFont(new Font("Times New Roman", Font.PLAIN, 13));		
		reservar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (tablaSalasDisponibles.getSelectedRow() >= 0) { 
					System.out.println(tablaSalasDisponibles.getValueAt(tablaSalasDisponibles.getSelectedRow(), 0));
					int plazas;
					try{
						String id_reserva = (String)tablaSalasDisponibles.getValueAt(tablaSalasDisponibles.getSelectedRow(), 0);
						String id_sala = (String)tablaSalasDisponibles.getValueAt(tablaSalasDisponibles.getSelectedRow(), 1);
						String dni_respon = (String)tablaSalasDisponibles.getValueAt(tablaSalasDisponibles.getSelectedRow(), 2);
						String fecha = (String)tablaSalasDisponibles.getValueAt(tablaSalasDisponibles.getSelectedRow(), 3);
						String hora = (String)tablaSalasDisponibles.getValueAt(tablaSalasDisponibles.getSelectedRow(), 4);
						int capacidad = (int)tablaSalasDisponibles.getValueAt(tablaSalasDisponibles.getSelectedRow(), 5);
						
						Reserva r =new Reserva(  id_reserva,id_sala,  dni_respon,  fecha,  hora, capacidad);
						plazas = 1 + NumAsistentes.getSelectedIndex();
						controller.getCl().getService().anyadirReserva( id_sala,  dni_respon,  fecha,  hora,  capacidad);
					
						int PlazasVuelo = (int)tablaSalasDisponibles.getValueAt(tablaSalasDisponibles.getSelectedRow(), 6);
						modelo = (DefaultTableModel)tablaSalasDisponibles.getModel();
						modelo.setValueAt(PlazasVuelo-plazas, tablaSalasDisponibles.getSelectedRow(), 5);
						JOptionPane.showInputDialog(null, "has echo una reserva", JOptionPane.INFORMATION_MESSAGE);
					}catch (NumberFormatException ex) {
						JOptionPane.showInputDialog(null, "Error: Se ha excedido el numero de plazas", JOptionPane.ERROR_MESSAGE);
					
					
			} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}	
	
	}
	
	});
		
		private void cargarReservas(Reserva r){
			modelo = (DefaultTableModel)tablaSalasDisponibles.getModel();
			String id_reserva = r.getId_reserva();
			String id_sala = r.getId_sala();
			String dni_respon = r.getDni_respon();
			String fecha = r.getFecha();
			String hora = r.getHora();
			int capacidad = r.getPlazas();

			Object[] fila = {id_reserva, id_sala, dni_respon, fecha, hora, capacidad};
			modelo.addRow(fila);                    
		}

	}
}
