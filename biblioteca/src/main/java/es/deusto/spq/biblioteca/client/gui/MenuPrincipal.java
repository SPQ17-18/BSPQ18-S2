package es.deusto.spq.biblioteca.client.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import javax.swing.JLabel;

import java.awt.Color;


import javax.swing.JButton;

import es.deusto.spq.biblioteca.controller.Controller;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase de creación de la ventana principal
 * Estructura realizada por
 * @author Jon Martinez
 * Fondo implementado por
 * @author Ariane
 * Funcionalidad del controller añadida por
 * @author Julen
 *
 */
public class MenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller controller;
	
	/**
	 * Clase constructor
	 * @param controller controller de la aplicacion
	 */
	public MenuPrincipal(Controller controller) {
		this.controller = controller;
		ventana();
		this.setVisible(true);
	}
	/**
	 * Inicialización de la ventana
	 */
	public void ventana() {
		setTitle("Biblioteca");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		JLabel label = new JLabel("");
		
		JButton botonLibros = new JButton("Libros");
		botonLibros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaLibros abrirVentana5 = new VentanaLibros(controller);
				abrirVentana5.setVisible(true);
				MenuPrincipal.this.dispose();
				
			}
		});
		
		JButton botonSalas = new JButton("Salas");
		botonSalas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaSala abrirVentana3 = new VentanaSala(controller);
				abrirVentana3.setVisible(true);
				MenuPrincipal.this.dispose();
			}
		});
		botonSalas.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		botonSalas.setBounds(524, 269, 156, 39);
		contentPane.add(botonSalas);
		botonLibros.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		botonLibros.setBounds(292, 269, 156, 39);
		contentPane.add(botonLibros);
		
		JButton botonComedor = new JButton("Comedor");
		botonComedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaComedor abrirVentana = new VentanaComedor(controller);
				abrirVentana.setVisible(true);
				MenuPrincipal.this.dispose();
			}
		});
		botonComedor.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		botonComedor.setBounds(56, 269, 156, 39);
		contentPane.add(botonComedor);
		
		JLabel lblNewLabel = new JLabel("¡Bienvenido!");
		lblNewLabel.setOpaque(false);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(97, 44, 232, 63);
		contentPane.add(lblNewLabel);
		contentPane.add(label);
		
		FondoPrincipal fondo = new FondoPrincipal();
		getContentPane().add(fondo,contentPane);
		fondo.setBounds(0, 0, 750, 387);
	}
	
	/**
	 * Visualizacion de la ventana
	 */
	public void ejecutarVentana() {
		try {
			final MenuPrincipal Ventana = new MenuPrincipal(controller);
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