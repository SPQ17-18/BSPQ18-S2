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

public class VentanaLibros extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLibros frame = new VentanaLibros();
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
	public VentanaLibros() {
		setTitle("Libros");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/libros.jpg")).getImage();
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(10, 74, 156, 29);
		contentPane.add(textField);
		
		JLabel lblTtuloDelLibro = new JLabel("Título del Libro:");
		lblTtuloDelLibro.setForeground(Color.WHITE);
		lblTtuloDelLibro.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
		lblTtuloDelLibro.setBounds(10, 36, 170, 54);
		contentPane.add(lblTtuloDelLibro);
		
		JButton button = new JButton("Volver");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal abrirVentana = new MenuPrincipal();
				abrirVentana.setVisible(true);
				VentanaLibros.this.dispose();
				
			}
		});
		button.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		button.setBounds(10, 284, 156, 29);
		contentPane.add(button);
		
		JButton btnMostrarCatlogo = new JButton("Mostrar Catálogo");
		btnMostrarCatlogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCatalogoLibros abrirVentana2 = new VentanaCatalogoLibros(null);
				abrirVentana2.setVisible(true);
				VentanaLibros.this.dispose();
			}
		});
		btnMostrarCatlogo.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		btnMostrarCatlogo.setBounds(528, 73, 156, 29);
		contentPane.add(btnMostrarCatlogo);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaReservaLibro abrirVentana3 = new VentanaReservaLibro();
				abrirVentana3.setVisible(true);
				VentanaLibros.this.dispose();
			}
		});
		btnBuscar.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		btnBuscar.setBounds(258, 72, 156, 29);
		contentPane.add(btnBuscar);
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 702, 353);
		contentPane.add(label);
	}

}
