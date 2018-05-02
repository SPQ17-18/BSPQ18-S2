package es.deusto.spq.biblioteca.client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaComedor extends JFrame {

	private JPanel contentPane;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaComedor frame = new VentanaComedor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					
				}
			}
		});
	}

	
	public VentanaComedor() {
		setTitle("Comedor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 310);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton botonComrpobarMenu = new JButton("Comprobar Men\u00FA");
		botonComrpobarMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentanaBusquedaMenu abrirVentana2 = new VentanaBusquedaMenu();
				abrirVentana2.setVisible(true);
				VentanaComedor.this.dispose();
				
			}
		});
		botonComrpobarMenu.setBounds(40, 177, 163, 46);
		contentPane.add(botonComrpobarMenu);
		
		JButton botonComprobarComedor = new JButton("Comprobar Comedor");
		botonComprobarComedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				VentanaBusquedaComedor abrirVentana = new VentanaBusquedaComedor();
				abrirVentana.setVisible(true);
				VentanaComedor.this.dispose();
				
			}
			
		});
		botonComprobarComedor.setBounds(283, 177, 163, 46);
		contentPane.add(botonComprobarComedor);
		
		JLabel lblEscojaUnaOpcin = new JLabel("Escoja una opci\u00F3n:");
		lblEscojaUnaOpcin.setBounds(195, 119, 122, 25);
		contentPane.add(lblEscojaUnaOpcin);
		
		JTextPane txtpnBienvendioAlMen = new JTextPane();
		txtpnBienvendioAlMen.setText(" Bienvendio al men\u00FA comedor. Aqu\u00ED podr\u00E1 ver la lista de men\u00FAs a su disposici\u00F3n, as\u00ED como reservar el men\u00FA que m\u00E1s le guste. Adem\u00E1s, tambi\u00E9n podr\u00E1 comprobar la lista de mesas disponibles para reservar con antelaci\u00F3n si as\u00ED lo desea.");
		txtpnBienvendioAlMen.setBounds(10, 11, 464, 249);
		contentPane.add(txtpnBienvendioAlMen);
	}
}
