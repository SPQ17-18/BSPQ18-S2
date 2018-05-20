package es.deusto.spq.biblioteca.client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JTextField;

import es.deusto.spq.biblioteca.controller.Controller;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class VentanaVerReservas extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private Controller controller;

	/**
	 *Clase constructor
	 * @param controller controller de la aplicacion
	 */
	public VentanaVerReservas(Controller controller) {
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
		setBounds(100, 100, 612, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
//		Image img = new ImageIcon(this.getClass().getResource("/menu2.jpg")).getImage();
		
		textField = new JTextField();
		textField.setBounds(42, 76, 170, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setColumns(10);
		textArea.setBounds(42, 76+30, 170, 26+80);
		contentPane.add(textArea);
		
		JButton button_1 = new JButton("Ver Reserva");
		button_1.addActionListener(new ActionListener() {
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
		button_1.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		button_1.setBounds(295, 73, 156, 29);
		contentPane.add(button_1);
		
		JLabel lblNewLabel = new JLabel("Introducir DNI:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(42, 21, 170, 54);
		contentPane.add(lblNewLabel);
		
		JButton button = new JButton("Volver");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaReservaMesa abrirVentana5 = new VentanaReservaMesa(controller);
				abrirVentana5.setVisible(true);
				VentanaVerReservas.this.dispose();
			}
		});
		button.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		button.setBounds(295, 122, 157, 29);
		contentPane.add(button);
//		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 596, 334);
		contentPane.add(label);
	}
	
	/**
	 * Visualizacion de la ventana
	 */
	public void ejecutarVentana() {
		try {
			final VentanaVerReservas Ventana = new VentanaVerReservas(controller);
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
