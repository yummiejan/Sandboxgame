package Model;

import View.DrawingPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

/**
 * Created by Felix on 12.12.2016.
 */
public class Hotbar implements InteractableObject{

    private Rectangle2D.Double rectangle;
    private int posX, posY;
    private Rectangle2D place[];

    public Hotbar(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        place = new Rectangle2D[5];
        rectangle = new Rectangle2D.Double(posX,posY,250,50);
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

    @Override
    public void draw(DrawingPanel dp, Graphics2D g2d) {
        for (int i = 0; i < place.length ; i++) {
            g2d.setColor(new Color(0,0,0));
            g2d.draw(rectangle);
            rectangle.setFrame(i*35+posX-125,0,35,35);
        }
    }

    @Override
    public void update(double dt) {

    }
}
