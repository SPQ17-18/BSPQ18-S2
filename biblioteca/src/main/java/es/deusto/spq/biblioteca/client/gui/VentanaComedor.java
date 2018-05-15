package es.deusto.spq.biblioteca.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaComedor extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaComedor frame = new VentanaComedor();
					
					//UIDefaults uiDefaults = UIManager.getDefaults();
					//uiDefaults.put("activeCaption", new javax.swing.plaf.ColorUIResource(Color.gray));
					//uiDefaults.put("activeCaptionText", new javax.swing.plaf.ColorUIResource(Color.white));
					//JFrame.setDefaultLookAndFeelDecorated(true);
					
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
	public VentanaComedor() {
		setTitle("Comedor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/comedor.jpg")).getImage();
		
		JButton button_2 = new JButton("Volver");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuPrincipal abrirVentana2 = new MenuPrincipal();
				abrirVentana2.setVisible(true);
				VentanaComedor.this.dispose();
			}
		});
		button_2.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		button_2.setBounds(424, 315, 156, 39);
		contentPane.add(button_2);
		
		JButton button_1 = new JButton("Ver Menú");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaBusquedaMenu abrirVentana3 = new VentanaBusquedaMenu(null);
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
				VentanaReservaMesa abrirVentana4 = new VentanaReservaMesa();
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
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 795, 382);
		contentPane.add(label);
	}

}
