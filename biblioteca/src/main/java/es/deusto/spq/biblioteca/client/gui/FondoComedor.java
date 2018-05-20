package es.deusto.spq.biblioteca.client.gui;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class FondoComedor extends JPanel{
	public void paintComponent(Graphics g) {
		// Como no estamos usando un Jlabel no podemos utilizar el set Icon, asi que lo
		// crearemos con el método ImageIcon
		Image imagen = null;
		try {
			URL url = new URL("https://c1.staticflickr.com/9/8201/8163759278_008074762c_b.jpg");
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
