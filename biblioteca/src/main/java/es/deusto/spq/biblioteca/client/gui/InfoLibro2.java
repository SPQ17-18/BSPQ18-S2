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

public class InfoLibro2 extends JFrame {

	private JPanel contentPane;
	private Controller controller;
	
	public InfoLibro2(Controller controller) {
		this.controller = controller;
		ventana();
		this.setVisible(true);
	}
	
	public void ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 235);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblElLibroHa = new JLabel("¡El libro ha sido reservado exitósamente!");
		lblElLibroHa.setBounds(40, 30, 263, 87);
		lblElLibroHa.setToolTipText("");
		lblElLibroHa.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		contentPane.add(lblElLibroHa);
		
		JButton button = new JButton("Aceptar");
		button.setBounds(90, 140, 148, 29);
		button.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		contentPane.add(button);
	}
	
	public void ejecutarVentana() {
		try {
			final InfoLibro2 Ventana = new InfoLibro2(controller);
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
