package Model;

import View.DrawingPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

/**
 * Created by 204g04 on 12.12.2016.
 */
public class Inventory implements InteractableObject{

    private Rectangle2D.Double rectangle;
    private Rectangle2D place[][];
    private double posX, posY;

    public Inventory(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
        rectangle = new Rectangle2D.Double(posX, posY, 10, 10);
        place = new Rectangle2D[10][3];
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
        for (int i = 0; i < place.length; i++) {
            for (int j = 0; j < place[i].length ; j++) {
                g2d.setColor(new Color(0,0,0));
                g2d.draw(rectangle);
                rectangle.setFrame(i*35,j*35,35,35);
            }
        }
    }

    @Override
    public void update(double dt) {

    }

}
