package es.deusto.spq.biblioteca.client.gui;

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
/**
 * Clase de creación de ventana reserva mesa
 * Estructura realizada por
 * @author Jon Martinez
 * Fondo implementado por
 * @author Ariane
 * Funcionalidad del controller añadida por
 * @author Julen
 */
public class VentanaReservaMesa extends JFrame {

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
	public VentanaReservaMesa(Controller controller) {
		this.controller = controller;
		ventana();
		this.setVisible(true);
	}
	
	/**
	 * Inicialización de la ventana
	 */
	public void ventana() {
		setTitle("Reserva de mesas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 708, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		
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
		label.setBounds(0, 0, 692, 340);
		contentPane.add(label);
		

		FondoComedor fondo = new FondoComedor();
		getContentPane().add(fondo,contentPane);
		fondo.setBounds(0, 0, 795, 382);
	}
	
	/**
	 * Visualizacion de la ventana
	 */
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
