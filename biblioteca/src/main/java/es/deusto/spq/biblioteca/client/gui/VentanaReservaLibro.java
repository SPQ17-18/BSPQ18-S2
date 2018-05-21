package es.deusto.spq.biblioteca.client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaReservaLibro extends JFrame {

	private JPanel contentPane;
	private JLabel lblDisponibilidad;
	private JTextPane textPane;
	private JTextPane textPane_1;
	private JTextPane textPane_2;
	private JTextPane textPane_3;
	private JButton btnReservarLibro;
	private JButton btnVolver;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaReservaLibro frame = new VentanaReservaLibro();
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
	public VentanaReservaLibro() {
		setTitle("Libro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnReservarLibro = new JButton("Reservar Libro");
		btnReservarLibro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnReservarLibro.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		btnReservarLibro.setBounds(37, 304, 138, 29);
		contentPane.add(btnReservarLibro);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InfoLibro3 abrirVentana5 = new InfoLibro3();
				abrirVentana5.setVisible(true);
				VentanaReservaLibro.this.dispose();
				
			}
		});
		btnVolver.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		btnVolver.setBounds(223, 304, 137, 29);
		contentPane.add(btnVolver);
		
		lblDisponibilidad = new JLabel("Disponibilidad:");
		lblDisponibilidad.setForeground(Color.BLACK);
		lblDisponibilidad.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
		lblDisponibilidad.setBounds(37, 221, 168, 20);
		contentPane.add(lblDisponibilidad);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setForeground(Color.BLACK);
		lblAutor.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		lblAutor.setBounds(37, 82, 86, 33);
		contentPane.add(lblAutor);
		
		JLabel lblTtulo = new JLabel("Título:");
		lblTtulo.setForeground(Color.BLACK);
		lblTtulo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		lblTtulo.setBounds(37, 24, 86, 30);
		contentPane.add(lblTtulo);
		
		JLabel lblEditorial = new JLabel("Editorial:");
		lblEditorial.setForeground(Color.BLACK);
		lblEditorial.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		lblEditorial.setBounds(37, 155, 115, 20);
		contentPane.add(lblEditorial);
		
		textPane_3 = new JTextPane();
		textPane_3.setBounds(214, 211, 146, 30);
		contentPane.add(textPane_3);
		
		textPane_1 = new JTextPane();
		textPane_1.setBounds(214, 82, 146, 30);
		contentPane.add(textPane_1);
		
		textPane_2 = new JTextPane();
		textPane_2.setBounds(214, 147, 146, 30);
		contentPane.add(textPane_2);
		
		textPane = new JTextPane();
		textPane.setBounds(214, 24, 147, 30);
		contentPane.add(textPane);
	}
}
