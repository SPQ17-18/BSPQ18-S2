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
	
	public VentanaLogin(Controller controller){
		this.controller = controller;
		ventana();
		Ventana.setVisible(true);
		
	}

	public void ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lbnPanel = new JLabel("Biblioteca");
		lbnPanel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		
		panel = new JPanel();
		panel.setBounds(100, 100, 1400, 300);
		getContentPane().add(panel);
		panel.add(lbnPanel);
		
		
		Salas = new JButton();
		Salas.setText("Salas de estudio");
		
		Comedor = new JButton();
		Comedor.setText("Servicios de comedor");
		
		Biblioteca = new JButton();
		Biblioteca.setText("Libros y otros materiales");
		
		
		Salas.setFocusable(false);
		Salas.setBounds(100, 600, 150, 100);
		Comedor.setFocusable(false);
		Salas.setBounds(200, 500, 150, 100);
		Biblioteca.setFocusable(false);
		Salas.setBounds(300, 400, 150, 100);
		
		Salas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaLogin.this.setVisible(false);
				Salas.setVisible(true);
			}
		});	
		Comedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaLogin.this.setVisible(false);
				Comedor.setVisible(true);
			}
		});	
		Biblioteca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaLogin.this.setVisible(false);
				Biblioteca.setVisible(true);
			}
		});
		
	add(Ventana,BorderLayout.CENTER);
	Ventana.setSize(1400,300);
	Ventana.add(Biblioteca);
	Ventana.add(Comedor);
	Ventana.add(Salas);
	
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