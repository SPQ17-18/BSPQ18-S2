package es.deusto.spq.biblioteca.client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InfoLibro3 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoLibro3 frame = new InfoLibro3();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InfoLibro3() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 448, 203);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblaDndeDesea = new JLabel("¿A dónde desea volver?");
		lblaDndeDesea.setToolTipText("");
		lblaDndeDesea.setFont(new Font("Times New Roman", Font.ITALIC, 24));
		lblaDndeDesea.setBounds(99, 11, 263, 87);
		contentPane.add(lblaDndeDesea);
		
		JButton btnLibros = new JButton("Menú");
		btnLibros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaLibros abrirVentana4 = new VentanaLibros();
				abrirVentana4.setVisible(true);
				InfoLibro3.this.dispose();
			}
		});
		btnLibros.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		btnLibros.setBounds(240, 103, 148, 29);
		contentPane.add(btnLibros);
		
		JButton btnCatlogo = new JButton("Catálogo");
		btnCatlogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCatalogoLibros abrirVentana5 = new VentanaCatalogoLibros(null);
				abrirVentana5.setVisible(true);
				InfoLibro3.this.dispose();
			}
		});
		btnCatlogo.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		btnCatlogo.setBounds(39, 103, 148, 29);
		contentPane.add(btnCatlogo);
	}

}
