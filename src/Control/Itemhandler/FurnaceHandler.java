package Control.Itemhandler;

import Model.DataStructures.*;
import Model.InteractableObject;
import Model.Items.Item;
import View.DrawingPanel;
import View.MainFrame;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Alex on 15.12.2016.
 */
public class FurnaceHandler implements InteractableObject {

    private Queue<Item> reinlegeQueue,rausnehmeQueue;
    private Item currentItem;

    public FurnaceHandler(MainFrame frame) {

    }

    @Override
    public void draw(DrawingPanel dp, Graphics2D g2d) {

    }

    @Override
    public void keyPressed(int key) {
        if(true){
            addNewItem(currentItem);
        }
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

    /**
     * Adds a new Item to the furnace and checks if you can cook named Item.
     *
     * @param i Item to be added to the Queue.
     */
    public void addNewItem(Item i){
        //Wenn man
        if(true) {
            reinlegeQueue.enqueue(i);
        }
    }
}
