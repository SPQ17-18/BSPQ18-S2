package es.deusto.spq.biblioteca.client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.deusto.spq.biblioteca.remote.Biblioteca;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import es.deusto.spq.biblioteca.controller.*;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.Color;


public class VentanaCatalogoLibros extends JFrame {

	private JPanel contentPane;
	private Controller controller;
	
	public VentanaCatalogoLibros(Controller controller){
		setTitle("Catálogo");
		this.controller = controller;
		VentanaCatalogoLibrosejecutor();
		this.setVisible(true);
	}


	public void VentanaCatalogoLibrosejecutor() {
		
		//Biblioteca bli = new Biblioteca;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 317);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("Volver");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaLibros abrirVentana = new VentanaLibros(controller);
				abrirVentana.setVisible(true);
				VentanaCatalogoLibros.this.dispose();
			}
		});
		button.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		button.setBounds(443, 241, 89, 26);
		contentPane.add(button);
		
		JButton btnL_4 = new JButton("L6");
		btnL_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaReservaLibro abrirVentana7 = new VentanaReservaLibro(controller);
				abrirVentana7.setVisible(true);
				VentanaCatalogoLibros.this.dispose();
			}
		});
		btnL_4.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		btnL_4.setBounds(291, 190, 92, 58);
		contentPane.add(btnL_4);
		
		JButton btnL_3 = new JButton("L5");
		btnL_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaReservaLibro abrirVentana6 = new VentanaReservaLibro(controller);
				abrirVentana6.setVisible(true);
				VentanaCatalogoLibros.this.dispose();
			}
		});
		btnL_3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		btnL_3.setBounds(53, 187, 92, 58);
		contentPane.add(btnL_3);
		
		JButton btnL_2 = new JButton("L4");
		btnL_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaReservaLibro abrirVentana5 = new VentanaReservaLibro(controller);
				abrirVentana5.setVisible(true);
				VentanaCatalogoLibros.this.dispose();
			}
		});
		btnL_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		btnL_2.setBounds(290, 110, 92, 58);
		contentPane.add(btnL_2);
		
		JButton btnL_1 = new JButton("L3");
		btnL_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaReservaLibro abrirVentana4 = new VentanaReservaLibro(controller);
				abrirVentana4.setVisible(true);
				VentanaCatalogoLibros.this.dispose();
			}
		});
		btnL_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		btnL_1.setBounds(53, 108, 92, 58);
		contentPane.add(btnL_1);
		
		JButton btnL = new JButton("L2");
		btnL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaReservaLibro abrirVentana3 = new VentanaReservaLibro(controller);
				abrirVentana3.setVisible(true);
				VentanaCatalogoLibros.this.dispose();
			}
		});
		btnL.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		btnL.setBounds(289, 30, 92, 58);
		contentPane.add(btnL);
		
		JButton btnNewButton = new JButton("L1");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaReservaLibro abrirVentana2 = new VentanaReservaLibro(controller);
				abrirVentana2.setVisible(true);
				VentanaCatalogoLibros.this.dispose();
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		btnNewButton.setBounds(53, 30, 92, 58);
		contentPane.add(btnNewButton);
		
		JLabel label = new JLabel("");
	//	Image img = new ImageIcon(this.getClass().getResource("/libros.jpg")).getImage();
	//	label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 542, 278);
		contentPane.add(label);
	}
	
	public void ejecutarVentana() {
		try {
			final VentanaCatalogoLibros ventanaCatalogoLibros = new VentanaCatalogoLibros(controller);
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					ventanaCatalogoLibros.setVisible(true);
				}
			});
		} catch (Exception e) {
			System.exit(1); 
		}

	}
}
