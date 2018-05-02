package es.deusto.spq.biblioteca.client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaBusquedaMenu extends JFrame {

	private JPanel contentPane;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaBusquedaMenu frame = new VentanaBusquedaMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public VentanaBusquedaMenu() {
		
		setTitle("Lista de Men\u00FAs disponibles");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton botonReservarMenu = new JButton("Reservar");
		botonReservarMenu.setBounds(286, 56, 126, 23);
		contentPane.add(botonReservarMenu);
		
		JButton botonMostrarMenu = new JButton("Mostrar Men\u00FA");
		botonMostrarMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		botonMostrarMenu.setBounds(20, 56, 126, 23);
		contentPane.add(botonMostrarMenu);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(407, 102, 17, 148);
		contentPane.add(scrollBar);
		
		JTextPane txtpnAquPodrVer = new JTextPane();
		txtpnAquPodrVer.setText("Aqu\u00ED podr\u00E1 ver a continuaci\u00F3n la lista de men\u00FAs del d\u00EDa disponibles, y reserva el que m\u00E1s resulte de su agrado.");
		txtpnAquPodrVer.setBounds(10, 11, 414, 80);
		contentPane.add(txtpnAquPodrVer);
		
		JList list = new JList();
		list.setBounds(10, 102, 414, 148);
		contentPane.add(list);
	}

}
