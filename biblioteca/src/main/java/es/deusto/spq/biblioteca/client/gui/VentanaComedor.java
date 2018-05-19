package es.deusto.spq.biblioteca.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import es.deusto.spq.biblioteca.controller.Controller;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaComedor extends JFrame {

	private JPanel contentPane;
	private Controller controller;
	
	public VentanaComedor(Controller controller) {
		this.controller = controller;
		ventana();
		this.setVisible(true);
	}
	
	public void ventana() {
		setTitle("Comedor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
//		Image img = new ImageIcon(this.getClass().getResource("/comedor.jpg")).getImage();
		
		JButton button_2 = new JButton("Volver");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuPrincipal abrirVentana2 = new MenuPrincipal(controller);
				abrirVentana2.setVisible(true);
				VentanaComedor.this.dispose();
			}
		});
		
		JButton btnAadirValoracin = new JButton("Añadir Valoración");
		btnAadirValoracin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaValoracion abrirVentana4 = new VentanaValoracion(controller);
				abrirVentana4.setVisible(true);
				VentanaComedor.this.dispose();
			}
		});
		btnAadirValoracin.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		btnAadirValoracin.setBounds(424, 316, 156, 39);
		contentPane.add(btnAadirValoracin);
		button_2.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		button_2.setBounds(629, 226, 156, 39);
		contentPane.add(button_2);
		
		JButton button_1 = new JButton("Ver Menú");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaBusquedaMenu abrirVentana3 = new VentanaBusquedaMenu(controller);
				abrirVentana3.setVisible(true);
				VentanaComedor.this.dispose();
			}
		});
		button_1.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		button_1.setBounds(224, 316, 156, 39);
		contentPane.add(button_1);
		
		JButton button = new JButton("Reservar Mesa");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaReservaMesa abrirVentana4 = new VentanaReservaMesa(controller);
				abrirVentana4.setVisible(true);
				VentanaComedor.this.dispose();
			}
		});
		button.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		button.setBounds(24, 316, 156, 39);
		contentPane.add(button);
		
		JLabel label_1 = new JLabel("Escoja una opción...");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 38));
		label_1.setBounds(10, 177, 354, 122);
		contentPane.add(label_1);
//		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 795, 382);
		contentPane.add(label);
	}
	
	public void ejecutarVentana() {
		try {
			final VentanaComedor Ventana = new VentanaComedor(controller);
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

