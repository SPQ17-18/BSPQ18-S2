package es.deusto.spq.biblioteca.client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import es.deusto.spq.biblioteca.controller.Controller;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMensaje extends JFrame {

	private JPanel contentPane;
	private Controller controller;

	public VentanaMensaje(Controller controller) {
		this.controller = controller;
		ventana();
		this.setVisible(true);
	}
	
	public void ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 348, 233);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaComedor abrirVentana3 = new VentanaComedor(controller);
				abrirVentana3.setVisible(true);
				VentanaMensaje.this.dispose();
				
			}
		});
		btnAceptar.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		btnAceptar.setBounds(94, 141, 148, 29);
		contentPane.add(btnAceptar);
		
		JLabel lblNewLabel = new JLabel("¡Compra exitosa!");
		lblNewLabel.setFont(new Font("Times New Roman", Font.ITALIC, 25));
		lblNewLabel.setBounds(73, 24, 209, 87);
		contentPane.add(lblNewLabel);
	}
	
	public void ejecutarVentana() {
		try {
			final VentanaMensaje Ventana = new VentanaMensaje(controller);
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
