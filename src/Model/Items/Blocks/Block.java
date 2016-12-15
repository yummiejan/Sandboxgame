package Model.Items.Blocks;

import Model.DrawableObject;
import Model.Items.Item;
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
    private int[] colours = new int[5];
    private boolean solid;

    public Block(double posX, double posY, int[] colours,boolean solid) {
        this.posX = posX;
        this.posY = posY;
        this.colours = colours;
        rectangle = new Rectangle2D.Double(posX, posY, height, width);
        this.solid = solid;
    }

    @Override
    public void draw(DrawingPanel dp, Graphics2D g2d) {
        g2d.setColor(new Color(colours[0],colours[1],colours[2]));
        g2d.fill(rectangle);
        g2d.setColor(new Color(colours[3],colours[4],colours[5]));
        g2d.draw(rectangle);
        rectangle.setFrame(posX,posY,width,height);
    }

    @Override
    public void update(double dt) {

    }

    public boolean isSolid() {
        return solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }
}
