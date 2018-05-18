package es.deusto.spq.biblioteca.client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class InfoLibro1 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoLibro1 frame = new InfoLibro1();
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
	public InfoLibro1() {
		setTitle("Error");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 235);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblElLibroQue = new JLabel("El libro que ha seleccionado ya ha sido reservado.");
		lblElLibroQue.setToolTipText("");
		lblElLibroQue.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		lblElLibroQue.setBounds(10, 31, 324, 87);
		contentPane.add(lblElLibroQue);
		
		JButton button = new JButton("Aceptar");
		button.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		button.setBounds(91, 144, 148, 29);
		contentPane.add(button);
	}

}
