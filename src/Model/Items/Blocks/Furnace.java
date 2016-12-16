package Model.Items.Blocks;

import Model.DrawableObject;
import View.DrawingPanel;
import Model.Items.Blocks.Block;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by Alex on 15.12.2016.
 */
public class Furnace extends Block {

    private Arc2D.Double semicircle;
    private Rectangle2D.Double rectangle;
    private Rectangle2D.Double rectangle2;

    public Furnace(double posX, double posY) {
        super(posX, posY, new int[] {128, 128, 128, 64, 64, 64}, true);
        int w = 30;
        semicircle = new Arc2D.Double(posX+25-w/2, posY+25, w, 30, 0, 180, Arc2D.PIE);
        rectangle = new Rectangle2D.Double(posX, posY, 50, 50);
        rectangle2 = new Rectangle2D.Double(posX+5, posY+5, 40, 8);
    }

    @Override
    public void draw(DrawingPanel dp, Graphics2D g2d) {
        g2d.setColor(new Color(128,128,128));
        g2d.fill(rectangle);
        g2d.setColor(new Color(64,64,64));
        g2d.draw(rectangle);
        g2d.fill(semicircle);
        g2d.draw(semicircle);
        g2d.fill(rectangle2);
        g2d.draw(rectangle2);
    }

    @Override
    public void update(double dt) {

    }
}
