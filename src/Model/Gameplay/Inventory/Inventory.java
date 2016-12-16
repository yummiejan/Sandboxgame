package Model.Gameplay.Inventory;

import Control.GameplayHandler.InventoryHandler;
import Model.InteractableObject;
import View.DrawingPanel;
import View.MainFrame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

/**
 * Created by 204g04 on 12.12.2016.
 */
public class Inventory implements InteractableObject {

    private Rectangle2D.Double backRectangle;
    private Rectangle2D.Double rectangle;
    private Rectangle2D.Double rectangle2;
    private Rectangle2D itemPlace[][];
    private Rectangle2D armorPlace[];
    private double posX, posY;

    public Inventory(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
        backRectangle = new Rectangle2D.Double(posX, posY, 10, 10);
        rectangle = new Rectangle2D.Double(posX, posY, 10, 10);
        rectangle2 = new Rectangle2D.Double(posX, posY, 10, 10);
        itemPlace = new Rectangle2D[10][4];
        armorPlace = new Rectangle2D[4];
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
                rectangle.setFrame(posX+i*35,posY+j*35,35,35);
            }
        }
        for (int i = 0; i < armorPlace.length; i++) {
            g2d.setColor(new Color(0,0,0));
            g2d.draw(rectangle2);
            rectangle2.setFrame(posX+35*itemPlace.length+20,posY+i*35,35,35);
        }
    }

    @Override
    public void update(double dt) {

    }
}
