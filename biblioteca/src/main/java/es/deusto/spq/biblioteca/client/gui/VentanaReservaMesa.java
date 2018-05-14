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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaReservaMesa extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaReservaMesa frame = new VentanaReservaMesa();
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
	public VentanaReservaMesa() {
		setTitle("Reserva de mesas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 708, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/menu.jpg")).getImage();
		
		JButton botonVolver = new JButton("Volver");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaComedor abrirVentana3 = new VentanaComedor();
				abrirVentana3.setVisible(true);
				VentanaReservaMesa.this.dispose();
			}
		});
		
		JButton butonVerReserva = new JButton("Ver Reserva");
		butonVerReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaVerReservas abrirVentana6 = new VentanaVerReservas();
				abrirVentana6.setVisible(true);
				VentanaReservaMesa.this.dispose();
			}
		});
		butonVerReserva.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		butonVerReserva.setBounds(33, 21, 156, 39);
		contentPane.add(butonVerReserva);
		
		JButton botonReservar = new JButton("Reservar");
		botonReservar.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		botonReservar.setBounds(271, 21, 156, 39);
		contentPane.add(botonReservar);
		botonVolver.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		botonVolver.setBounds(506, 21, 156, 39);
		contentPane.add(botonVolver);
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 692, 340);
		contentPane.add(label);
	}
}
