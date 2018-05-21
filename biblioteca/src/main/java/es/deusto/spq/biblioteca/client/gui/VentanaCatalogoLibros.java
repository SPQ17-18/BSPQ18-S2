package es.deusto.spq.biblioteca.client.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import es.deusto.spq.biblioteca.controller.*;
import java.awt.Font;
import java.awt.Rectangle;
/**
 * Clase de creación de ventana ver catálogo de libros
 * Estructura realizada por
 * @author Jon Martinez
 * Fondo y funcionalidad implementada por
 * @author Ariane
 */
public class VentanaCatalogoLibros extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea textArea;
	private Controller controller;

	/**
	 *Clase constructor
	 * @param controller controller de la aplicacion
	 */
	public VentanaCatalogoLibros(Controller controller) {
		this.controller = controller;
		ventana();
		this.setVisible(true);
	}
	
	/**
	 * Inicialización de la ventana
	 */
	public void ventana() {
		setTitle("Ver Catalogo de libros");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setColumns(10);
		textArea.setBounds(42, 76+30, 200, 26+80);
		contentPane.add(textArea);
		
		JButton button_1 = new JButton("Ver Catalogo");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 ArrayList<String>libros=new ArrayList<String>();
				try {
			
					libros = controller.getCl().getService().getLibros();
				} catch (RemoteException e1) {
		
					e1.printStackTrace();
				}
				String s;
				for(int i=0; i<libros.size();i++) {
					s=libros.get(i);
					String[] primera = s.split("/");
					for (String cadena : primera) {
						String[] segunda = cadena.split("#");
						textArea.append("Isbn: " + segunda[0] + "\n" + "Nombre: " + segunda[1] + "\n" +"Autor: " + segunda[2] + "\n" +"Editorial: " + segunda[3] + "\n" + "\n");

					}
				}
			}
		});
		button_1.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		button_1.setBounds(408, 73, 156, 29);
		contentPane.add(button_1);
			
		JButton button = new JButton("Volver");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaLibros abrirVentana5 = new VentanaLibros(controller);
				abrirVentana5.setVisible(true);
				VentanaCatalogoLibros.this.dispose();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(new Rectangle(30,30,350,200));
		contentPane.add(scrollPane);
		
		FondoLibro fondo = new FondoLibro();
		getContentPane().add(fondo,contentPane);
		fondo.setBounds(0, 0, 795, 382);
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

