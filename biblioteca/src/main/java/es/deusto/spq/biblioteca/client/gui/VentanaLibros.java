package es.deusto.spq.biblioteca.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import es.deusto.spq.biblioteca.controller.Controller;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaLibros extends JFrame {

	private JPanel contentPane;
	private Controller controller;

	/**
	 *Clase constructor
	 * @param controller controller de la aplicacion
	 */
	public VentanaLibros(Controller controller) {
		this.controller = controller;
		ventana();
		this.setVisible(true);
	}

	/**
	 * Inicialización de la ventana
	 */
	public void ventana() {
		setTitle("Libros");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("");
//		Image img = new ImageIcon(this.getClass().getResource("/comedor.jpg")).getImage();

		JButton button_2 = new JButton("Volver");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuPrincipal abrirVentana1 = new MenuPrincipal(controller);
				abrirVentana1.setVisible(true);
				VentanaLibros.this.dispose();
			}
		});

		button_2.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		button_2.setBounds(424, 316, 156, 39);
		contentPane.add(button_2);


		JButton btnCatalogo = new JButton("Ver catalogo");
		btnCatalogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				VentanaCatalogoLibros abrirVentana2 = new VentanaCatalogoLibros(controller);
//				abrirVentana2.setVisible(true);
				VentanaLibros.this.dispose();
			}
		});
		btnCatalogo.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		btnCatalogo.setBounds(224, 316, 156, 39);
		contentPane.add(btnCatalogo);

		JButton button = new JButton("Reservar Libro");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaReservaLibro abrirVentana3 = new VentanaReservaLibro(controller);
				abrirVentana3.setVisible(true);
				VentanaLibros.this.dispose();

			}
		});
		button.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		button.setBounds(24, 316, 156, 39);
		contentPane.add(button);

		JLabel label_1 = new JLabel("Escoja una opción...");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 38));
		label_1.setBounds(10, 177, 354, 122);
		contentPane.add(label_1);
//		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 795, 382);
		contentPane.add(label);
		
		FondoLibro fondo = new FondoLibro();
		getContentPane().add(fondo,contentPane);
		fondo.setBounds(0, 0, 795, 382);
	}

	/**
	 * Visualizacion de la ventana
	 */
	public void ejecutarVentana() {
		try {
			final VentanaLibros Ventana = new VentanaLibros(controller);
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