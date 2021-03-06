package es.deusto.spq.biblioteca.client.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import es.deusto.spq.biblioteca.controller.Controller;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Clase de creación de ventana reserva sala
 * Estructura realizada por
 * @author Jon Martinez
 * Fondo implementado por
 * @author Ariane
 * Funcionalidad del controller añadida por
 * @author Julen
 */
public class VentanaSala extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller controller;
	
	/**
	 *Clase constructor
	 * @param controller controller de la aplicacion
	 */
	public VentanaSala(Controller controller) {
		this.controller = controller;
		ventana();
		this.setVisible(true);
	}
	
	/**
	 *Clase constructor
	 * @param controller controller de la aplicacion
	 */
	public void ventana() {
		setTitle("Salas de Trabajo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
	
		JButton btnReservarSala = new JButton("Reservar Sala");
		btnReservarSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaHacerReservaSala abrirVentana5 = new VentanaHacerReservaSala(controller);
				abrirVentana5.setVisible(true);
				VentanaSala.this.dispose();
			}
		});
		btnReservarSala.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		btnReservarSala.setBounds(410, 189, 157, 29);
		contentPane.add(btnReservarSala);
		
		JButton button_1 = new JButton("Volver");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal abrirVentana4 = new MenuPrincipal(controller);
				abrirVentana4.setVisible(true);
				VentanaSala.this.dispose();
			}
		});
		button_1.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		button_1.setBounds(411, 280, 157, 29);
		contentPane.add(button_1);
		
		JButton btnVerSalas = new JButton("Ver Salas");
		btnVerSalas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaVerReservasSala abrirVentana6 = new VentanaVerReservasSala(controller);
				abrirVentana6.setVisible(true);
				VentanaSala.this.dispose();
			}
		});
		btnVerSalas.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		btnVerSalas.setBounds(411, 234, 156, 29);
		contentPane.add(btnVerSalas);
		
		JLabel label_1 = new JLabel("Escoja una opción...");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35));
		label_1.setBounds(35, 23, 354, 122);
		contentPane.add(label_1);
	
		label.setBounds(0, 0, 594, 348);
		contentPane.add(label);
		
		FondoSala fondo = new FondoSala();
		getContentPane().add(fondo,contentPane);
		fondo.setBounds(0, 0, 795, 382);
	}
	
	/**
	 * Visualizacion de la ventana
	 */
	public void ejecutarVentana() {
		try {
			final VentanaSala Ventana = new VentanaSala(controller);
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