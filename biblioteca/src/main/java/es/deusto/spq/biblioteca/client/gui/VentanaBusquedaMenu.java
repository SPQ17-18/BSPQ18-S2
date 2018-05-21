package es.deusto.spq.biblioteca.client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import es.deusto.spq.biblioteca.controller.*;
import javax.swing.JLabel;
import java.awt.Font;


public class VentanaBusquedaMenu extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller controller = null;
	
	public VentanaBusquedaMenu(Controller controller){
		this.controller = controller;
		VentanaBusquedaMenuejecutor();
		this.setVisible(true);
	}

	public void VentanaBusquedaMenuejecutor() {
		
		
		setTitle("Lista de Men\u00FAs disponibles");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 716, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Image img = new ImageIcon(this.getClass().getResource("/menu3.jpg")).getImage();
		
		JButton btnComprar = new JButton("Comprar");
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMensaje abrirVentana3 = new VentanaMensaje();
				abrirVentana3.setVisible(true);
				VentanaBusquedaMenu.this.dispose();
				
			}
		});
		btnComprar.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		btnComprar.setBounds(473, 281, 103, 26);
		contentPane.add(btnComprar);
		
		JButton button = new JButton("Volver");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaComedor abrirVentana3 = new VentanaComedor();
				abrirVentana3.setVisible(true);
				VentanaBusquedaMenu.this.dispose();
			}
		});
		button.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		button.setBounds(599, 281, 101, 26);
		contentPane.add(button);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(683, 28, 17, 251);
		contentPane.add(scrollBar);
		
		JLabel lblNewLabel = new JLabel("Menú disponible:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.ITALIC, 29));
		lblNewLabel.setBounds(473, 0, 206, 26);
		contentPane.add(lblNewLabel);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(473, 28, 206, 251);
		contentPane.add(textPane);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 466, 307);
		contentPane.add(label);
	}
	
	public void ejecutarVentana() {
		// TODO Auto-generated method stub
		try {
			final VentanaBusquedaMenu ventanaBusquedaMenu = new VentanaBusquedaMenu(controller);
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					ventanaBusquedaMenu.setVisible(true);
				}
			});
		} catch (Exception e) {
			System.exit(1); 
		}

	}
}
