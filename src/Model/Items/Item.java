package Model.Items;

import Model.DrawableObject;
import View.DrawingPanel;

import java.awt.*;

/**
 * Created by 204g04 on 09.12.2016.
 */
public abstract class Item implements DrawableObject {

    private String name;

    public Item(String name) {
        this.name = name;
    }

    @Override
    public void draw(DrawingPanel dp, Graphics2D g2d) {

    }

    public String getName() {
        return name;
    }
}
