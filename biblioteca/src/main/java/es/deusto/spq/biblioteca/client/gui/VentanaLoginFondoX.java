package es.deusto.spq.biblioteca.client.gui;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class VentanaLoginFondoX extends JPanel {
	private URL url = getClass().getResource("");
	Image image = new ImageIcon(url).getImage();
	
	public void paint (Graphics g){
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		super.paint(g);
	}

}
		


