package es.deusto.spq.biblioteca.client.gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import es.deusto.spq.biblioteca.controller.*;
import javax.swing.JLabel;
import java.awt.Font;
/**
 * Clase de creación de ventana búsqueda Menú
 * Estructura realizada por
 * @author Jon Martinez
 * Fondo implementado por
 * @author Ariane
 * Funcionalidad del controller añadida por
 * @author Luis
 */

public class VentanaBusquedaMenu extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller controller;
	
	/**
	 *Clase constructor
	 * @param controller controller de la aplicacion
	 */
	public VentanaBusquedaMenu(Controller controller){
		this.controller = controller;
		VentanaBusquedaMenuejecutor();
		this.setVisible(true);
	}
	
	/**
	 * Inicialización de la ventana
	 */
	public void VentanaBusquedaMenuejecutor() {
		
		setTitle("Lista de Men\u00FAs disponibles");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 716, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
//		Image img = new ImageIcon(this.getClass().getResource("/menu3.jpg")).getImage();
		
		JButton btnComprar = new JButton("Comprar");
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMensaje abrirVentana3 = new VentanaMensaje(controller);
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
				VentanaComedor abrirVentana3 = new VentanaComedor(controller);
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
		
		String s = null;
		try {

			ArrayList<String> fechas = new ArrayList<>();
			fechas.add("13-5-2018");
			fechas.add("25-3-2018");
			fechas.add("8-4-2018");
			fechas.add("29-11-2018");
			fechas.add("16-9-2018");
			
			s =controller.verMenu(fechas.get((int) Math.random()));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] primera = s.split("/");
		for (String cadena : primera) {
			String[] segunda = cadena.split("#");
			
			textPane.setText("Fecha : " + segunda[1]
					+ "\nPlato 1 : " + segunda[2] 
							+ "\nPlato 2 : " + segunda[3]
									+ "\nPostre : " + segunda[4]);
										
			}
		

		
		
		
		contentPane.add(textPane);
		
		
		JLabel label = new JLabel("");
//		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 466, 307);
		contentPane.add(label);
		

		FondoMenu fondo = new FondoMenu();
		getContentPane().add(fondo,contentPane);
		fondo.setBounds(0, 0, 795, 382);
	}
	
	/**
	 * Visualizacion de la ventana
	 */
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
