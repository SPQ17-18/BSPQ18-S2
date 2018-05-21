package es.deusto.spq.biblioteca.client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaVerReservasSala extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaVerReservasSala frame = new VentanaVerReservasSala();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	
	public VentanaVerReservasSala() {
		setTitle("Ver Reserva");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 535, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(58, 72, 170, 26);
		contentPane.add(textField);
		
		JButton button = new JButton("Ver Reserva");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TablaReservasSala abrirVentana5 = new TablaReservasSala();
				abrirVentana5.setVisible(true);
				VentanaVerReservasSala.this.dispose();
			}
		});
		button.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		button.setBounds(311, 71, 156, 29);
		contentPane.add(button);
		
		JLabel label = new JLabel("Introducir DNI:");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		label.setBounds(57, 32, 170, 41);
		contentPane.add(label);
		
		JButton button_1 = new JButton("Volver");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaSala abrirVentana4 = new VentanaSala();
				abrirVentana4.setVisible(true);
				VentanaVerReservasSala.this.dispose();
			}
		});
		button_1.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		button_1.setBounds(311, 139, 157, 29);
		contentPane.add(button_1);
	}

}
