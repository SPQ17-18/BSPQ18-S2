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

public class InfoLibro1 extends JFrame {

	private JPanel contentPane;
	private Controller controller;
	
	public InfoLibro1(Controller controller) {
		this.controller = controller;
		ventana();
		this.setVisible(true);
	}
	
	public void ventana() {
		setTitle("Error");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 235);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblElLibroQue = new JLabel("El libro que ha seleccionado ya ha sido reservado.");
		lblElLibroQue.setToolTipText("");
		lblElLibroQue.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		lblElLibroQue.setBounds(10, 31, 324, 87);
		contentPane.add(lblElLibroQue);
		
		JButton button = new JButton("Aceptar");
		button.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		button.setBounds(91, 144, 148, 29);
		contentPane.add(button);
	}
	
	public void ejecutarVentana() {
		try {
			final InfoLibro1 Ventana = new InfoLibro1(controller);
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
