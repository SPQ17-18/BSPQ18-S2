package es.deusto.spq.biblioteca.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;

import es.deusto.spq.biblioteca.controller.*;
import es.deusto.spq.biblioteca.dao.ReservaDAO;
import es.deusto.spq.biblioteca.dao.SalaDAO;


public class VentanaBuscar extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel lnumPlazas, lfecha, lhora;
	private JButton reservar;
	private JTextField fecha, hora;
	private JScrollPane scroll;
	private static VentanaBuscar INSTANCE;
	private Controller controller = null;
	private JTable tablaSalasDisponibles;
	private DefaultTableModel modelo = new DefaultTableModel();
	List<SalaDAO> consultarPlazas = new ArrayList<>();


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
		
		fecha = new JTextField();
		fecha.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		fecha.setColumns(10);
		fecha.setBounds(84, 119, 108, 25);
		panel.add(fecha);
		
		hora = new JTextField();
		hora.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		hora.setColumns(10);
		hora.setBounds(84, 119, 108, 25);
		panel.add(hora);
		
		reservar = new JButton("Reservar");
		reservar.setFont(new Font("Times New Roman", Font.PLAIN, 13));		
		reservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tablaSalasDisponibles.getSelectedRow() >= 0) { 
					System.out.println(tablaSalasDisponibles.getValueAt(tablaSalasDisponibles.getSelectedRow(), 0));
					int plazas;
			}
				
			}	
	
	});
	
	}


}
