package es.deusto.spq.biblioteca.client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import es.deusto.spq.biblioteca.controller.Controller;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaValoracion extends JFrame {

	private JPanel contentPane;
	private Controller controller;
	
	public VentanaValoracion(Controller controller) {
		this.controller = controller;
		ventana();
		this.setVisible(true);
	}
	
	public void ventana() {
		setTitle("Valoración");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 414, 285);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnVolver = new JButton("Aceptar");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMensaje2 abrirVentana5 = new VentanaMensaje2(controller);
				abrirVentana5.setVisible(true);
				VentanaValoracion.this.dispose();
			}
		});
		btnVolver.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		btnVolver.setBounds(246, 159, 142, 29);
		contentPane.add(btnVolver);
		
		JButton btnAceptar = new JButton("Volver");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaComedor abrirVentana2 = new VentanaComedor(controller);
				abrirVentana2.setVisible(true);
				VentanaValoracion.this.dispose();
			}
		});
		btnAceptar.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		btnAceptar.setBounds(246, 61, 142, 29);
		contentPane.add(btnAceptar);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Menú 1", "Menú 2", "Menú 3", "Menú 4", "Menú 5"}));
		comboBox.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		comboBox.setBounds(10, 59, 112, 29);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		comboBox_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		comboBox_1.setBounds(10, 160, 112, 29);
		contentPane.add(comboBox_1);
		
		JLabel lblNewLabel = new JLabel("Valore nuestros menús:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblNewLabel.setBounds(10, 11, 192, 44);
		contentPane.add(lblNewLabel);
		
		JLabel lblCalificacin = new JLabel("Calificación:");
		lblCalificacin.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblCalificacin.setBounds(10, 115, 192, 44);
		contentPane.add(lblCalificacin);
	}
	public void ejecutarVentana() {
		try {
			final VentanaMensaje Ventana = new VentanaMensaje(controller);
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
