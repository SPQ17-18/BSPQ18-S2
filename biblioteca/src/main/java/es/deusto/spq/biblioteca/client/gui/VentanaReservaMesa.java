package es.deusto.spq.biblioteca.client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import es.deusto.spq.biblioteca.controller.Controller;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaReservaMesa extends JFrame {

	private JPanel contentPane;
	private Controller controller;
	
	public VentanaReservaMesa(Controller controller) {
		this.controller = controller;
		ventana();
		this.setVisible(true);
	}
	
	public void ventana() {
		setTitle("Reserva de mesas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 708, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
//		Image img = new ImageIcon(this.getClass().getResource("/menu.jpg")).getImage();
		
		JButton botonVolver = new JButton("Volver");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaComedor abrirVentana3 = new VentanaComedor(controller);
				abrirVentana3.setVisible(true);
				VentanaReservaMesa.this.dispose();
			}
		});
		
		JButton butonVerReserva = new JButton("Ver Reserva");
		butonVerReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaVerReservas abrirVentana6 = new VentanaVerReservas(controller);
				abrirVentana6.setVisible(true);
				VentanaReservaMesa.this.dispose();
			}
		});
		butonVerReserva.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		butonVerReserva.setBounds(33, 21, 156, 39);
		contentPane.add(butonVerReserva);
		
		JButton botonReservar = new JButton("Reservar");
		botonReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaHacerReservaMesa abrirVentana6 = new VentanaHacerReservaMesa(controller);
				abrirVentana6.setVisible(true);
				VentanaReservaMesa.this.dispose();
				
			}
		});
		botonReservar.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		botonReservar.setBounds(271, 21, 156, 39);
		contentPane.add(botonReservar);
		botonVolver.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		botonVolver.setBounds(506, 21, 156, 39);
		contentPane.add(botonVolver);
//		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 692, 340);
		contentPane.add(label);
	}
	public void ejecutarVentana() {
		try {
			final VentanaReservaMesa Ventana = new VentanaReservaMesa(controller);
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					Ventana.setVisible(true);
				}
			});
		} catch (Exception e) {
			System.exit(1);  
		}
}

}
