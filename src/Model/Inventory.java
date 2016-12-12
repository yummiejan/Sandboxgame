package Model;

import View.DrawingPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

/**
 * Created by 204g04 on 12.12.2016.
 */
public class Inventory implements InteractableObject{

    private Rectangle2D.Double backRectangle;
    private Rectangle2D.Double rectangle;
    private Rectangle2D.Double rectangle2;
    private Rectangle2D itemPlace[][];
    private Rectangle2D amorPlace[];
    private double posX, posY;

    public Inventory(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
        backRectangle = new Rectangle2D.Double(posX, posY, 10, 10);
        rectangle = new Rectangle2D.Double(posX, posY, 10, 10);
        rectangle2 = new Rectangle2D.Double(posX, posY, 10, 10);
        itemPlace = new Rectangle2D[10][4];
        amorPlace = new Rectangle2D[4];
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
        g2d.setColor(new Color(79, 79, 79));
        g2d.fill(backRectangle);
        backRectangle.setFrame(posX,posY,35*itemPlace.length+55,35*itemPlace[0].length+1);
        for (int i = 0; i < itemPlace.length; i++) {
            for (int j = 0; j < itemPlace[i].length ; j++) {
                g2d.setColor(new Color(0,0,0));
                g2d.draw(rectangle);
                rectangle.setFrame(i*35,j*35,35,35);
            }
        }
        for (int i = 0; i < amorPlace.length; i++) {
            g2d.setColor(new Color(0,0,0));
            g2d.draw(rectangle2);
            rectangle2.setFrame(35*itemPlace.length+20,i*35,35,35);
        }
    }

    @Override
    public void update(double dt) {

    }
}
