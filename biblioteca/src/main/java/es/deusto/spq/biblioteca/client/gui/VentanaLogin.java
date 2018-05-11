package es.deusto.spq.biblioteca.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import es.deusto.spq.biblioteca.controller.*;


public class VentanaLogin extends JFrame  {
	private static final long serialVersionUID = 1L;
	private JPanel panel,Ventana;
	private JLabel lbnPanel;
	private JButton Salas, Comedor,Biblioteca ;
	private Controller controller;
	
	public VentanaLogin(Controller controller) {
		this.controller = controller;
		ventana();
		this.setVisible(true);
	}

	public void ventana() {
		setTitle("VentanaLogin");
		this.setSize(1000,1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lbnPanel = new JLabel("Biblioteca");
		lbnPanel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		panel = new JPanel();
		panel.setBounds(0, 0, 1400, 100);
		getContentPane().add(panel);
		panel.add(lbnPanel);
		Salas = new JButton();
		Salas.setText("Salas de estudio");
		Comedor = new JButton();
		Comedor.setText("Servicios de comedor");
		Biblioteca = new JButton();
		Biblioteca.setText("Libros y otros materiales");
		
		
		Salas.setFocusable(false);
		Salas.setBounds(550, 150, 300, 80);
		Comedor.setFocusable(false);
		Comedor.setBounds(550, 233, 300, 80);
		Biblioteca.setFocusable(false);
		Biblioteca.setBounds(550, 316, 300, 80);
		
		Salas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaLogin.this.setVisible(false);
				VentanaBuscar Salas = new VentanaBuscar(controller);
				Salas.setVisible(true);
			}
		});	
		Comedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaLogin.this.setVisible(true);
				VentanaComedor Comedor = new VentanaComedor(controller);
				Comedor.setVisible(true);
			}
		});	
		Biblioteca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaLogin.this.setVisible(true);
				VentanaCatalogoLibros Biblioteca = new VentanaCatalogoLibros(controller);
				Biblioteca.setVisible(true);
			}
		});
	Ventana = new JPanel();
	Ventana.setSize(1400,1000);
	Ventana.add(Biblioteca);
	Ventana.add(Comedor);
	Ventana.add(Salas);
	add(Ventana,BorderLayout.CENTER);
	Ventana.setBackground(Color.GRAY);
	Ventana.setLayout(null);
	setResizable(true);
	}

			public void ejecutarVentana() {
				try {
					final VentanaLogin Ventana = new VentanaLogin(controller);
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