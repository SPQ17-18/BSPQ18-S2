package es.deusto.spq.biblioteca.client.gui;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/**
 * Clase para añadir el fondo a la ventana de sala
 * @author Ariane
 *
 */
public class FondoSala extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
		// Como no estamos usando un Jlabel no podemos utilizar el set Icon, asi que lo
		// crearemos con el método ImageIcon
		Image imagen = null;
		try {
			URL url = new URL("http://interiores.com/wp-content/uploads/2008/11/salon-estudio-borrador.jpg");
			imagen = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon imagenFondo = new ImageIcon(imagen);
		g.drawImage(imagenFondo.getImage(), 0, 0, 600, 400, null);
		setOpaque(false);
		super.paintComponent(g);
	}

}
