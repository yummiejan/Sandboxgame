package Model.Items;

import Model.DrawableObject;
import Model.InteractableObject;
import View.DrawingPanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;


/**
 * Created by 204g04 on 09.12.2016.
 */
public abstract class Item implements InteractableObject {

    private String name;

    public Item(String name) {
        this.name = name;

    }

    @Override
    public void keyPressed(int key) {
        if(key == KeyEvent.VK_SPACE) {

        }
    }

    @Override
    public void draw(DrawingPanel dp, Graphics2D g2d) {

    }

    public String getName() {
        return name;
    }
}
