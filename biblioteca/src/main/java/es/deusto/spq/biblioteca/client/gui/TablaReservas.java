package es.deusto.spq.biblioteca.client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.ScrollPane;

import javax.swing.JCheckBox;

import es.deusto.spq.biblioteca.controller.Controller;

public class TablaReservas extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Controller controller;

	/**
	 *Clase constructor
	 * @param controller controller de la aplicacion
	 */
	public TablaReservas(Controller controller) {
		this.controller = controller;
		ventana();
		this.setVisible(true);
	}
	
	/**
	 * Inicialización de la ventana
	 */
	public void ventana() {
		setTitle("Reservas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 871, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("Volver");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaVerReservas abrirVentana7 = new VentanaVerReservas(controller);
				abrirVentana7.setVisible(true);
				TablaReservas.this.dispose();
			}
		});
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, "", null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"DNI", "FECHA", "HORA", "PERSONAS", ""
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(85);
		table.getColumnModel().getColumn(0).setMinWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(85);
		table.getColumnModel().getColumn(1).setMinWidth(25);
		table.getColumnModel().getColumn(2).setPreferredWidth(85);
		table.getColumnModel().getColumn(2).setMinWidth(25);
		table.getColumnModel().getColumn(3).setPreferredWidth(82);
		table.getColumnModel().getColumn(3).setMinWidth(25);
		table.getColumnModel().getColumn(4).setPreferredWidth(36);
		table.getColumnModel().getColumn(4).setMinWidth(0);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminar.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		btnEliminar.setBounds(687, 284, 156, 29);
		contentPane.add(btnEliminar);
		table.setBounds(10, 43, 660, 160);
		contentPane.add(table);
		button.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		button.setBounds(687, 352, 157, 29);
		contentPane.add(button);
		
		JLabel label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/bibliotecadeusto.jpg")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 854, 397);
		contentPane.add(label);
	}
	/**
	 * Visualizacion de la ventana
	 */
	public void ejecutarVentana() {
		try {
			final TablaReservas Ventana = new TablaReservas(controller);
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
