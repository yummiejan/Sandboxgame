package Model.Items.Blocks;

import Control.GameplayHandler.WorldHandler;
import View.DrawingPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by Alex on 15.12.2016.
 */
public class Furnace extends Block {

    private boolean activated;
    private Rectangle2D.Double rectangle;
    private Arc2D.Double semicircle;
    private Rectangle2D.Double rectangle2;

    public Furnace(double posX, double posY, WorldHandler wh) {
        super(posX, posY, new int[] {128, 128, 128, 64, 64, 64}, true,"Furnace");//,wh);
        semicircle = new Arc2D.Double(posX+10, posY+25, 30, 30, 0, 180, Arc2D.PIE);
        rectangle = new Rectangle2D.Double(posX, posY, 50, 50);
        rectangle2 = new Rectangle2D.Double(posX+5, posY+5, 40, 8);
    }

    @Override
    public void draw(DrawingPanel dp, Graphics2D g2d) {
        if(super.isDisplayed()) {
            g2d.setColor(new Color(128, 128, 128));
            g2d.fill(rectangle);
            if (activated) {
                g2d.setColor(new Color(176, 48, 48));
            } else {
                g2d.setColor(new Color(64, 64, 64));
            }
            g2d.fill(semicircle);
            g2d.draw(semicircle);
            g2d.setColor(new Color(64, 64, 64));
            g2d.draw(rectangle);
            g2d.fill(rectangle2);
            g2d.draw(rectangle2);
        }
    }

    @Override
    public void update(double dt) {

    }

    /**
     * Checks if the furnace is activated.
     * @return
     */
    public boolean isActivated() {
        return activated;
    }

    /**
     *
     * @param activated
     */
    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Override
    public void keyReleased(int key) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
