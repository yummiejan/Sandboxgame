package Model;

import View.DrawingPanel;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by 204g04 on 12.12.2016.
 */
public abstract class Block extends Item implements DrawableObject {

    private Rectangle2D.Double rectangle;
    private double posX, posY;
    private double height = 50, width = 50;

    public Block(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
        rectangle = new Rectangle2D.Double(posX, posY, height, width);
    }

    @Override
    public void draw(DrawingPanel dp, Graphics2D g2d) {
        g2d.setColor(new Color(84, 42, 25));
        g2d.fill(rectangle);
        g2d.setColor(new Color(0,0,0));
        g2d.draw(rectangle);
        rectangle.setFrame(posX,posY,width,height);
    }

    @Override
    public void update(double dt) {

    }
}
