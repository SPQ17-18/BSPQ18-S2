package es.deusto.spq.biblioteca.client.gui;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class FondoLibro extends JPanel{
	
	public void paintComponent(Graphics g) {
		// Como no estamos usando un Jlabel no podemos utilizar el set Icon, asi que lo
		// crearemos con el método ImageIcon
		Image imagen = null;
		try {
			URL url = new URL("https://www.bbva.com/wp-content/uploads/2017/05/Economia-finanzas-Libros-BBVA-1920x0-c-f.jpg");
			imagen = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon imagenFondo = new ImageIcon(imagen);
		g.drawImage(imagenFondo.getImage(), 0, 0, 795, 382, null);
		setOpaque(false);
		super.paintComponent(g);
	}

}
