package es.deusto.spq.biblioteca.client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class InfoLibro2 extends JFrame {

	private JPanel contentPane;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoLibro2 frame = new InfoLibro2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public InfoLibro2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 235);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblElLibroHa = new JLabel("¡El libro ha sido reservado exitósamente!");
		lblElLibroHa.setBounds(40, 30, 263, 87);
		lblElLibroHa.setToolTipText("");
		lblElLibroHa.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		contentPane.add(lblElLibroHa);
		
		JButton button = new JButton("Aceptar");
		button.setBounds(90, 140, 148, 29);
		button.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		contentPane.add(button);
	}

}
