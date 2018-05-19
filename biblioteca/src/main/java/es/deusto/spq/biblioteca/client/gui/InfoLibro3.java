package es.deusto.spq.biblioteca.client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import es.deusto.spq.biblioteca.controller.Controller;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InfoLibro3 extends JFrame {

	private JPanel contentPane;
	private Controller controller;
	
	public InfoLibro3(Controller controller) {
		this.controller = controller;
		ventana();
		this.setVisible(true);
	}
	
	public void ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 448, 203);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblaDndeDesea = new JLabel("¿A dónde desea volver?");
		lblaDndeDesea.setToolTipText("");
		lblaDndeDesea.setFont(new Font("Times New Roman", Font.ITALIC, 24));
		lblaDndeDesea.setBounds(99, 11, 263, 87);
		contentPane.add(lblaDndeDesea);
		
		JButton btnLibros = new JButton("Menú");
		btnLibros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaLibros abrirVentana4 = new VentanaLibros(controller);
				abrirVentana4.setVisible(true);
				InfoLibro3.this.dispose();
			}
		});
		btnLibros.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		btnLibros.setBounds(240, 103, 148, 29);
		contentPane.add(btnLibros);
		
		JButton btnCatlogo = new JButton("Catálogo");
		btnCatlogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCatalogoLibros abrirVentana5 = new VentanaCatalogoLibros(null);
				abrirVentana5.setVisible(true);
				InfoLibro3.this.dispose();
			}
		});
		btnCatlogo.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		btnCatlogo.setBounds(39, 103, 148, 29);
		contentPane.add(btnCatlogo);
	}
	
	public void ejecutarVentana() {
		try {
			final InfoLibro3 Ventana = new InfoLibro3(controller);
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