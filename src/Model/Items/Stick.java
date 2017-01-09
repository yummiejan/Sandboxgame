package Model.Items;

import View.DrawingPanel;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Felix on 07.01.2017.
 */
public class Stick extends Item{

    private String recipe[];

    public Stick() {
        super("Stick");
        createRecipe();
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

    public void createRecipe(){
        recipe = new String[2];
        recipe[0] = "Wood";
        recipe[1] = "Wood";
    }
}
