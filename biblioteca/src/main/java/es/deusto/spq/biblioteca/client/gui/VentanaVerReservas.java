package es.deusto.spq.biblioteca.client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaVerReservas extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaVerReservas frame = new VentanaVerReservas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaVerReservas() {
		setTitle("Ver Reserva");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/menu2.jpg")).getImage();
		
		textField = new JTextField();
		textField.setBounds(42, 76, 170, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton button_1 = new JButton("Ver Reserva");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TablaReservas abrirVentana8 = new TablaReservas();
				abrirVentana8.setVisible(true);
				VentanaVerReservas.this.dispose();
			}
		});
		button_1.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		button_1.setBounds(295, 73, 156, 29);
		contentPane.add(button_1);
		
		JLabel lblNewLabel = new JLabel("Introducir DNI:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(42, 21, 170, 54);
		contentPane.add(lblNewLabel);
		
		JButton button = new JButton("Volver");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaReservaMesa abrirVentana5 = new VentanaReservaMesa();
				abrirVentana5.setVisible(true);
				VentanaVerReservas.this.dispose();
			}
		});
		button.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		button.setBounds(295, 122, 157, 29);
		contentPane.add(button);
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 596, 334);
		contentPane.add(label);
	}
}
