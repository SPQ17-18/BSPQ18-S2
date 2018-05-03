package es.deusto.spq.biblioteca.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import es.deusto.spq.biblioteca.controller.Controller;

public class VerReservas extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel Ventana, Titulo;
	private JLabel Panel, dni;
	private JTextField txtdni;
	private JTextArea area;
	private JButton ver, inicio;
	private Controller controller;

	public  VerReservas(Controller controller) {
		this.controller=controller;
		ventana();
		Ventana.setVisible(true);
	}


	public void ventana() {
	this.setSize(1000,1000);
	Ventana = new JPanel();
		setTitle("Reservas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Titulo = new JPanel();
		Titulo.setBounds(0, 0, 1400, 100);
		getContentPane().add(Titulo);
		
		txtdni = new JTextField();
		txtdni.setBounds(855,175,200,30);
		
		area = new JTextArea();
		area.setBounds(300,100,200,800);
		area.setVisible(true);
		area.setEditable(false);
		
		Panel = new JLabel("Tus reservas");
		Panel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		Titulo.add(Panel);
		
		dni = new JLabel ("Su dni");
		dni.setBounds(50,60,100,30);
		
		ver = new JButton();
		ver.setText("Ver reservas");
		ver.setFocusable(false);
		ver.setBounds(550, 150, 300, 80);
		
		inicio = new JButton();
		inicio.setText("Volver");
		inicio.setFocusable(false);
		inicio.setBounds(550, 233, 300, 80);
		
		ver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dni.setVisible(false);
				txtdni.setVisible(false);
				ver.setVisible(false);
				inicio.setVisible(true);
				ControllerVer();
		
			}
		});
		
		inicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VerReservas.this.setVisible(false);
				VentanaLogin v = new VentanaLogin(controller);
				v.setVisible(true);
		
			}
		});
		
		
		Ventana.add(ver);
		Ventana.add(inicio);
		Ventana.add(txtdni);
		Ventana.add(dni);
		Ventana.add(area);
		Ventana.setSize(1400,1000);
		add(Ventana, BorderLayout.CENTER);
		Ventana.setLayout(null);
		Ventana.setBackground(Color.GRAY);
	}
	
	public void ControllerVer() {
		String s;
			s= controller.verReservas(txtdni.getText());
			String[] primera = s.split("/");
			for (String cadena : primera) {
				String[] segunda = cadena.split("#");
				area.append("Dni responsable: " + txtdni.getText()+ "\n");
				area.append("Codigo sala: " + segunda[1] + "\n");
				area.append("Fecha: " + segunda[2] + "\n");
				area.append("Hora: " + segunda[3] + "\n");
				area.append("Plazas: " + segunda[4] + "\n");

			}


	}
	
	
	
	public void lanzarVentana() {
		try {
			final VerReservas Ventana = new VerReservas(controller);
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					Ventana.setVisible(true);
				}
			});
		} catch (Exception e) {
			System.exit(1);  
		}
	}

}
