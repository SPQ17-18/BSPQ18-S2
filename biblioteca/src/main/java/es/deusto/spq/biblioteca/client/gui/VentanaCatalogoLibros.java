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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaCatalogoLibros extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCatalogoLibros frame = new VentanaCatalogoLibros();
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
	public VentanaCatalogoLibros() {
		
		//Biblioteca bli = new Biblioteca;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton botonMostrarCatalogo = new JButton("Mostrar Cat\u00E1logo");
		botonMostrarCatalogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		botonMostrarCatalogo.setBounds(20, 54, 150, 23);
		contentPane.add(botonMostrarCatalogo);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(407, 88, 17, 162);
		contentPane.add(scrollBar);
		
		JTextPane txtpnHolaAquPodr = new JTextPane();
		txtpnHolaAquPodr.setBounds(10, 11, 414, 72);
		txtpnHolaAquPodr.setText("Hola! Aqu\u00ED podr\u00E1 comprobar el cat\u00E1logo de libros que tiene a su disposici\u00F3n haci\u00E9ndo click en el boton a continuaci\u00F3n.");
		contentPane.add(txtpnHolaAquPodr);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 88, 414, 162);
		contentPane.add(textPane);
	}
}
