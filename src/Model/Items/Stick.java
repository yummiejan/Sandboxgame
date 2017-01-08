package Model.Items;

import View.DrawingPanel;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Felix on 07.01.2017.
 */
public class Stick extends Item{

    public Stick() {
        super("Stick");
    }

    @Override
    public void keyPressed(int key) {
        super.keyPressed(key);
    }

    @Override
    public void draw(DrawingPanel dp, Graphics2D g2d) {

    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void keyReleased(int key) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
