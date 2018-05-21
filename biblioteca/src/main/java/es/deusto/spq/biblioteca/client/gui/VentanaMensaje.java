package es.deusto.spq.biblioteca.client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMensaje extends JFrame {

	private JPanel contentPane;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMensaje frame = new VentanaMensaje();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public VentanaMensaje() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 235);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaComedor abrirVentana3 = new VentanaComedor();
				abrirVentana3.setVisible(true);
				VentanaMensaje.this.dispose();
				
			}
		});
		btnAceptar.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		btnAceptar.setBounds(87, 141, 148, 29);
		contentPane.add(btnAceptar);
		
		JLabel lblNewLabel = new JLabel("¡Compra exitosa!");
		lblNewLabel.setFont(new Font("Times New Roman", Font.ITALIC, 25));
		lblNewLabel.setBounds(70, 30, 190, 87);
		contentPane.add(lblNewLabel);
	}

}
