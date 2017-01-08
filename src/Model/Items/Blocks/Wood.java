package Model.Items.Blocks;

import View.DrawingPanel;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Felix on 07.01.2017.
 */
public class Wood extends Block {

    public Wood(double posX, double posY, boolean solid, String name) {
        super(posX, posY, false, "Wood");
    }

    @Override
    public void draw(DrawingPanel dp, Graphics2D g2d) {

    }

    @Override
    public void keyPressed(int key) {

    }

    @Override
    public void keyReleased(int key) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
