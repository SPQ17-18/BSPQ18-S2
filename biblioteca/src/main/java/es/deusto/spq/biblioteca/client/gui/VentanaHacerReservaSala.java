package es.deusto.spq.biblioteca.client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;

import es.deusto.spq.biblioteca.controller.Controller;

public class VentanaHacerReservaSala extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel lblSala;
	private JComboBox comboBox;
	private JButton button;
	private JButton button_1;
	private Controller controller;
	
	/**
	 *Clase constructor
	 * @param controller controller de la aplicacion
	 */
	public VentanaHacerReservaSala(Controller controller) {
		this.controller = controller;
		ventana();
		this.setVisible(true);
	}
	
	/**
	 * Inicialización de la ventana
	 */
	public void ventana() {
		setTitle("Reservar Sala");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 318);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblSala = new JLabel("Sala:");
		lblSala.setForeground(Color.BLACK);
		lblSala.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		lblSala.setBounds(203, 11, 113, 33);
		contentPane.add(lblSala);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"S1", "S2", "S3", "S4", "S5", "S6"}));
		comboBox.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		comboBox.setBounds(200, 39, 105, 23);
		contentPane.add(comboBox);
		
		button = new JButton("Reserva");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMensaje3 abrirVentana6 = new VentanaMensaje3(controller);
				abrirVentana6.setVisible(true);
				VentanaHacerReservaSala.this.dispose();
			}
		});
		button.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		button.setBounds(397, 36, 156, 29);
		contentPane.add(button);
		
		button_1 = new JButton("Volver");
		button_1.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		button_1.setBounds(396, 216, 157, 29);
		contentPane.add(button_1);
		
		JLabel label = new JLabel("DNI:");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		label.setBounds(24, 11, 60, 33);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(24, 101, 86, 23);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(24, 161, 86, 23);
		contentPane.add(textField_1);
		
		JLabel label_2 = new JLabel("Fecha:");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		label_2.setBounds(21, 70, 86, 30);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Personas:");
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		label_3.setBounds(21, 135, 131, 20);
		contentPane.add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(23, 41, 86, 23);
		contentPane.add(textField_2);
		
		JLabel label_4 = new JLabel("Hora:");
		label_4.setForeground(Color.BLACK);
		label_4.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		label_4.setBounds(22, 195, 86, 20);
		contentPane.add(label_4);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(24, 220, 86, 23);
		contentPane.add(textField_3);
	}
	
	/**
	 * Visualizacion de la ventana
	 */
	public void ejecutarVentana() {
		try {
			final VentanaHacerReservaSala Ventana = new VentanaHacerReservaSala(controller);
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
