package Model.Items.Items;

import Model.Items.Item;
import View.DrawingPanel;

import java.awt.*;

/**
 * Created by Felix on 07.01.2017.
 */
public class Stick extends Item {

    private String[] recipe;

    public Stick() {
        super("Stick");
        createRecipe();
    }

    @Override
    public void draw(DrawingPanel dp, Graphics2D g2d) {

    }

    @Override
    public void update(double dt) {

    }

    public void createRecipe(){
        recipe = new String[2];
        recipe[0] = "Wood";
        recipe[1] = "Wood";
    }
}
