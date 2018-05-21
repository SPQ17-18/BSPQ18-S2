package es.deusto.spq.biblioteca.client.gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JLabel;

import es.deusto.spq.biblioteca.controller.Controller;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Clase de creación de ventana mensaje
 * Estructura realizada por
 * @author Jon Martinez
 */
public class VentanaMensaje3 extends JFrame {

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
	public VentanaMensaje3(Controller controller) {
		this.controller = controller;
		ventana();
		this.setVisible(true);
	}
	
	/**
	 * Inicialización de la ventana
	 */
	public void ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 235);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("Aceptar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal abrirVentana6 = new MenuPrincipal(controller);
				abrirVentana6.setVisible(true);
				VentanaMensaje3.this.dispose();
			}
		});
		button.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		button.setBounds(91, 134, 148, 29);
		contentPane.add(button);
		
		JLabel lblreservaExitosa = new JLabel("¡Reserva exitosa!");
		lblreservaExitosa.setFont(new Font("Times New Roman", Font.ITALIC, 25));
		lblreservaExitosa.setBounds(75, 34, 190, 87);
		contentPane.add(lblreservaExitosa);
	}
	
	/**
	 * Visualizacion de la ventana
	 */
	public void ejecutarVentana() {
		try {
			final VentanaMensaje3 Ventana = new VentanaMensaje3(controller);
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