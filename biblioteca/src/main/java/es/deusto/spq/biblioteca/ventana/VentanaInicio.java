package es.deusto.spq.biblioteca.ventana;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

	public class VentanaInicio extends JFrame implements ActionListener {

		private static final long serialVersionUID = 1L;
		private JPanel contentPane, panelCentro, panelIzquierda,panelDerecha, panelNorte;
		private JTextField txtNick,txtNombre;
		private JPasswordField psContraseña;
		private JButton btnAtras, btnAceptar, btnReservarSala, btnReservarLibro;
		private JLabel lblNombre;

		public VentanaInicio(/*JFrame va*/) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 506, 352);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			panelCentro = new JPanel();
			panelCentro.setBounds(5, 5, 459, 24);
			contentPane.add(panelCentro);

			panelNorte = new JPanel();
			panelCentro.add(panelNorte);
			panelNorte.setLayout(new BorderLayout(0, 0));

			btnAceptar = new JButton("ACEPTAR");
			btnAceptar.addActionListener(this);
			btnAceptar.setBounds(100, 269, 137, 23);
			contentPane.add(btnAceptar);

			btnAtras = new JButton("ATRAS");
			btnAtras.setBounds(249, 269, 137, 23);
			contentPane.add(btnAtras);
			btnAtras.addActionListener(this);
			
			btnReservarSala = new JButton(" Reservar Sala");
			btnReservarSala.addActionListener(this);
			btnReservarSala.setBounds(100, 269, 137, 23);
			contentPane.add(btnReservarSala);
			
			btnReservarLibro = new JButton(" Reservar Libro");
			btnReservarLibro.addActionListener(this);
			btnReservarLibro.setBounds(100, 269, 137, 23);
			contentPane.add(btnReservarLibro);
			

			panelIzquierda = new JPanel();
			panelIzquierda.setBounds(5, 40, 219, 216);
			contentPane.add(panelIzquierda);
			panelIzquierda.setLayout(null);

			panelDerecha = new JPanel();
			panelDerecha.setBounds(236, 40, 228, 216);
			contentPane.add(panelDerecha);
			panelDerecha.setLayout(null);

			lblNombre = new JLabel("Nombre : ");
			lblNombre.setBounds(12, 27, 59, 14);
			panelDerecha.add(lblNombre);

			txtNombre = new JTextField();
			txtNombre.setBounds(79, 27, 115, 20);
			panelDerecha.add(txtNombre);
			txtNombre.setColumns(10);

			this.setVisible(true);
		}
		public void campoVacio()
		{
			txtNick.setText("");
			txtNombre.setText("");
			psContraseña.setText("");
			
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}

			
			
		


}

