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

public class VentanaBusquedaComedor extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaBusquedaComedor frame = new VentanaBusquedaComedor();
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
	public VentanaBusquedaComedor() {
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
		
		JButton botonReservarMesa = new JButton("Reservar");
		botonReservarMesa.setBounds(287, 55, 126, 23);
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
}
