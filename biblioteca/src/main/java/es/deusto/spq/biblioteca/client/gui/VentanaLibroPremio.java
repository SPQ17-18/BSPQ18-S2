package es.deusto.spq.biblioteca.client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.deusto.spq.biblioteca.controller.Controller;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;

public class VentanaLibroPremio extends JFrame {

	private Controller controller;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnVolver;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaLibroPremio frame = new VentanaLibroPremio();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	
	public VentanaLibroPremio(Controller controller) throws HeadlessException {
		this.controller = controller;
		VentanaLibroPremio();
		this.setVisible(true);
	}


	/**
	 * Create the frame.
	 */
	public void VentanaLibroPremio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			controller.anyadirPremio(textField.getSelectedText(),textField_1.getSelectedText());
			}
		});
		btnNewButton.setBounds(272, 59, 128, 23);
		contentPane.add(btnNewButton);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(272, 133, 128, 23);
		contentPane.add(btnVolver);
		
		textField = new JTextField();
		textField.setBounds(33, 60, 121, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(33, 134, 121, 20);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel = new JLabel("ISBN");
		lblNewLabel.setBounds(41, 35, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNombreDelPremio = new JLabel("Nombre del Premio");
		lblNombreDelPremio.setBounds(41, 109, 100, 14);
		contentPane.add(lblNombreDelPremio);
	}
	
	/**
	 * Visualizacion de la ventana
	 */
	public void ejecutarVentana() {
		try {
			final VentanaLibroPremio Ventana = new VentanaLibroPremio(controller);
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
