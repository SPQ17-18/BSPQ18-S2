package es.deusto.spq.biblioteca.client.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class VentanaLogin extends JFrame  {
	private JPanel contentPane;
	private JPanel panelSur, panelNorte;
	private JLabel Pan;
	private JButton btnAceptar, btnAtras;
		
	
	public void ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelSur = new JPanel();
		panelSur.setBounds(5, 5, 422, 35);
		contentPane.add(panelSur);
		panelSur.setLayout(null);

		Pan = new JLabel("Biblioteca");
		Pan.setForeground(new Color(0, 0, 255));
		Pan.setHorizontalAlignment(SwingConstants.CENTER);
		Pan.setFont(new Font("Times New Roman", Font.BOLD, 22));
		Pan.setBounds(0, 5, 422, 30);
		panelNorte.add(Pan);


}
}


