package es.deusto.spq.biblioteca.client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class VentanaHacerReservaMesa extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaHacerReservaMesa frame = new VentanaHacerReservaMesa();
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
	public VentanaHacerReservaMesa() {
		setTitle("Reservar Mesa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/reservado.jpg")).getImage();
		
		JButton button = new JButton("Volver");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaReservaMesa abrirVentana6 = new VentanaReservaMesa();
				abrirVentana6.setVisible(true);
				VentanaHacerReservaMesa.this.dispose();
			}
		});
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"M1", "M2", "M3", "M4", "M5", "M6"}));
		comboBox.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		comboBox.setBounds(295, 49, 112, 29);
		contentPane.add(comboBox);
		button.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		button.setBounds(528, 293, 157, 29);
		contentPane.add(button);
		
		JButton btnReserva = new JButton("Reserva");
		btnReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMensaje3 abrirVentana7 = new VentanaMensaje3();
				abrirVentana7.setVisible(true);
				VentanaHacerReservaMesa.this.dispose();
			}
		});
		btnReserva.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		btnReserva.setBounds(529, 49, 156, 29);
		contentPane.add(btnReserva);
		
		textField = new JTextField();
		textField.setBounds(33, 55, 86, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(33, 136, 86, 23);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(33, 220, 86, 23);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(33, 298, 86, 23);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblMesa = new JLabel("Mesa:");
		lblMesa.setForeground(Color.WHITE);
		lblMesa.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		lblMesa.setBounds(294, 11, 113, 33);
		contentPane.add(lblMesa);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		lblNewLabel_1.setBounds(33, 102, 86, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("DNI:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(33, 11, 60, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Hora:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(33, 268, 86, 20);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Personas:");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(33, 188, 131, 20);
		contentPane.add(lblNewLabel_3);
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 708, 353);
		contentPane.add(label);
	}

}
