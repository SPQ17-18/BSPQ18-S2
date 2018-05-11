package es.deusto.spq.biblioteca.client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.SwingUtilities;

import es.deusto.spq.biblioteca.controller.Controller;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import es.deusto.spq.biblioteca.controller.*;


public class VentanaBusquedaComedor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller controller = null;
	
	public VentanaBusquedaComedor(Controller controller){
		this.controller = controller;
		VentanaBusquedaComedorejecutor();
		this.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public void VentanaBusquedaComedorejecutor() {
		setTitle("Lista de mesas disponibles");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(407, 102, 17, 148);
		contentPane.add(scrollBar);
		
		JButton botonVolver = new JButton("Volver");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentanaComedor abrirVentana3 = new VentanaComedor(controller);
				abrirVentana3.setVisible(true);
				VentanaBusquedaComedor.this.dispose();
				
			}
		});
		botonVolver.setBounds(284, 55, 126, 23);
		contentPane.add(botonVolver);
		
		JButton botonReservarMesa = new JButton("Reservar");
		botonReservarMesa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		botonReservarMesa.setBounds(158, 55, 116, 23);
		contentPane.add(botonReservarMesa);
		
		
		JButton botonMostrarMesas = new JButton("Mostrar Mesas");
		botonMostrarMesas.setBounds(22, 55, 126, 23);
		contentPane.add(botonMostrarMesas);
		
		JTextPane txtpnHolaAquPodr = new JTextPane();
		txtpnHolaAquPodr.setText("Hola! Aqu\u00ED podr\u00E1 darle al bot\u00F3n que encontrar\u00E1 a continuaci\u00F3n para mostrar la lista de salas disponibles para reservar.");
		txtpnHolaAquPodr.setBounds(10, 11, 414, 80);
		contentPane.add(txtpnHolaAquPodr);
		
		JList list = new JList();
		list.setBounds(10, 102, 414, 148);
		contentPane.add(list);
	}
	
	public void ejecutarVentana() {
		// TODO Auto-generated method stub
		try {
			final VentanaBusquedaComedor ventanaBusquedaComedor = new VentanaBusquedaComedor(controller);
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					ventanaBusquedaComedor.setVisible(true);
				}
			});
		} catch (Exception e) {
			System.exit(1); 
		}

	}
}