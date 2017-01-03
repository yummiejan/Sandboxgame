package Model.Items.Blocks;

import Model.DrawableObject;
import Model.Items.Item;
import View.DrawingPanel;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by Felix on 03.01.2017.
 */
public abstract class InventoryBlock extends Item implements DrawableObject {

    private Rectangle2D.Double rectangle;
    private double posX, posY;
    private double height = 5, width = 5;

    public InventoryBlock(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
        rectangle = new Rectangle2D.Double(posX, posY, height, width);
    }

    @Override
    public void draw(DrawingPanel dp, Graphics2D g2d) {
            g2d.setColor(new Color(0,0,0));
            g2d.fill(rectangle);
            g2d.setColor(new Color(0,0,0));
            g2d.draw(rectangle);
            rectangle.setFrame(posX, posY, width, height);
    }

    @Override
    public void update(double dt) {

    }

}
