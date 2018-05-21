package es.deusto.spq.biblioteca.client.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

import es.deusto.spq.biblioteca.controller.Controller;
/**
 * Clase de creación de ventana reserva libro
 * Estructura realizada por
 * @author Jon Martinez
 * Fondo y funcionalidad del controller implementado por
 * @author Ariane
 */
public class VentanaReservaLibro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JButton button;
	private JButton button_1;
	private Controller controller;
	
	/**
	 *Clase constructor
	 * @param controller controller de la aplicacion
	 */
	public VentanaReservaLibro(Controller controller) {
		this.controller = controller;
		ventana();
		this.setVisible(true);
	}
	
	/**
	 * Inicialización de la ventana
	 */
	public void ventana() {
		setTitle("Reservar Libro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 318);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		button = new JButton("Reserva");
		button.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		button.setBounds(397, 36, 156, 29);
		contentPane.add(button);
		
		button_1 = new JButton("Volver");
		button_1.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		button_1.setBounds(396, 216, 157, 29);
		contentPane.add(button_1);
		
		JLabel label = new JLabel("ISBN:");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		label.setBounds(24, 11, 60, 33);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(23, 41, 86, 23);
		contentPane.add(textField);
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuPrincipal abrirVentana1 = new MenuPrincipal(controller);
				abrirVentana1.setVisible(true);
				VentanaReservaLibro.this.dispose();
			}
		});

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMensaje3 abrirVentana6 = new VentanaMensaje3(controller);
				abrirVentana6.setVisible(true);

				try {
					controller.reservarLibro(textField.getText());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				VentanaReservaLibro.this.dispose();

			}

		});
		
		FondoLibro fondo = new FondoLibro();
		getContentPane().add(fondo,contentPane);
		fondo.setBounds(0, 0, 795, 382);
	}
	
	/**
	 * Visualizacion de la ventana
	 */
	public void ejecutarVentana() {
		try {
			final VentanaReservaLibro Ventana = new VentanaReservaLibro(controller);
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
