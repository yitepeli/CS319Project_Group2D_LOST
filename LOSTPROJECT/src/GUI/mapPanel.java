package GUI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.google.common.collect.MapMaker;

import GameManagement.GameEngine;
import GameManagement.MapManager;
import GameObjectsManagement.AreaManagement.Area;

public class mapPanel extends JPanel {

	BufferedImage image;
	GameEngine game;
	/**
	 * Create the panel.
	 */
	public mapPanel(GameEngine game) {
		this.game = game;
		image  = game.getMap();
	}

	@Override
    protected void paintComponent(Graphics g) {
		
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

	

}
