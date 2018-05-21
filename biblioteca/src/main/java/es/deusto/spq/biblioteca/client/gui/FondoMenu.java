package es.deusto.spq.biblioteca.client.gui;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/**
 * Clase para añadir el fondo a la ventana de menu
 * @author Ariane
 *
 */
public class FondoMenu extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
		// Como no estamos usando un Jlabel no podemos utilizar el set Icon, asi que lo
		// crearemos con el método ImageIcon
		Image imagen = null;
		try {
			URL url = new URL("http://www.diariovasco.com/noticias/201601/17/media/tortitas_xoptimizadax--660x400.jpg");
			imagen = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon imagenFondo = new ImageIcon(imagen);
		g.drawImage(imagenFondo.getImage(), 0, 0, 414, 382, null);
		setOpaque(false);
		super.paintComponent(g);
	}
}