package es.deusto.spq.biblioteca.client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JLabel;

import es.deusto.spq.biblioteca.controller.Controller;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class VentanaVerReservasSala extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private Controller controller;
	
	/**
	 *Clase constructor
	 * @param controller controller de la aplicacion
	 */
	public VentanaVerReservasSala(Controller controller) {
		this.controller = controller;
		ventana();
		this.setVisible(true);
	}
	
	/**
	 * Inicialización de la ventana
	 */
	public void ventana() {
		setTitle("Ver Reserva");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 535, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(58, 72, 170, 26);
		contentPane.add(textField);
		
		textArea = new JTextArea();
		textArea.setColumns(10);
		textArea.setBounds(58, 72+30, 170, 26+80);
		contentPane.add(textArea);
		
		JButton button = new JButton("Ver Reserva");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 ArrayList<String>reserva=new ArrayList<String>();
				try {
					
					reserva = controller.getCl().getService().verReservas(textField.getText());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String s;
				for(int i=0; i<reserva.size();i++) {
					s=reserva.get(i);
					String[] primera = s.split("/");
					for (String cadena : primera) {
						String[] segunda = cadena.split("#");
						textArea.append("Sala: " + segunda[1] + "\n" + "DNI: " + segunda[2] + "\n" +"Fecha: " + segunda[3] + "\n" +"Hora: " + segunda[4] + "\n" + "\n"+ "Plazas: " + segunda[5] + "\n" + "\n");

					}
				}
				
	
			}
		});
		button.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		button.setBounds(311, 71, 156, 29);
		contentPane.add(button);
		
		JLabel label = new JLabel("Introducir DNI:");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		label.setBounds(57, 32, 170, 41);
		contentPane.add(label);
		
		JButton button_1 = new JButton("Volver");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaSala abrirVentana4 = new VentanaSala(controller);
				abrirVentana4.setVisible(true);
				VentanaVerReservasSala.this.dispose();
			}
		});
		button_1.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		button_1.setBounds(311, 139, 157, 29);
		contentPane.add(button_1);
		
		FondoSala fondo = new FondoSala();
		getContentPane().add(fondo,contentPane);
		fondo.setBounds(0, 0, 795, 382);
	}
	
	/**
	 * Visualizacion de la ventana
	 */
	public void ejecutarVentana() {
		try {
			final VentanaVerReservasSala Ventana = new VentanaVerReservasSala(controller);
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