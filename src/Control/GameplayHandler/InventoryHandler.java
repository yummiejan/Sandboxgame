package Control.GameplayHandler;

import Model.DataStructures.Stack;
import Model.Gameplay.Inventory.Hotbar;
import Model.InteractableObject;
import Model.Gameplay.Inventory.Inventory;
import Model.Items.Blocks.Block;
import Model.Items.Blocks.Coal;
import Model.Items.Blocks.Dirt;
import Model.Items.Item;
import View.DrawingPanel;
import View.MainFrame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

/**
 * Created by Felix on 12.12.2016.
 */
public class InventoryHandler implements InteractableObject{

    private MainFrame frame;
    private Inventory firstInventory;
    private Hotbar firstHotbar;

    public InventoryHandler(MainFrame frame) {
        this.frame = frame;
        firstInventory = new Inventory(0,0);
        frame.getActiveDrawingPanel().addObject(firstInventory);
        firstInventory.setDisplayed(false);
        firstHotbar = new Hotbar(frame.getActiveDrawingPanel().getWidth()/2,0);
        frame.getActiveDrawingPanel().addObject(firstHotbar);
        for (int i = 0; i < 65; i++) {
            addNewItem("Dirt");
        }

        addNewItem("Dirt");
        addNewItem("Coal");
        //addNewItem("Furnace");
    }

    @Override
    public void keyPressed(int key) {
        if(key == KeyEvent.VK_E && firstHotbar.isDisplayed()){
            if (!firstInventory.isDisplayed()) {
                firstInventory.setDisplayed(true);
            }else{
                firstInventory.setDisplayed(false);
            }
        }

    }

    @Override
    public void keyReleased(int key) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void draw(DrawingPanel dp, Graphics2D g2d) {

    }

    @Override
    public void update(double dt) {

    }

    public void addNewItem(String itemName){
        boolean added = false;
        for (int i = 0; i < firstInventory.getItemPlace().length; i++) {
            for (int j = 0; j < firstInventory.getItemPlaceLength(i); j++) {
                if (firstInventory.getItemPlacePlace(i, j).top() == itemName && firstInventory.getItemPlacePlace(i, j).getSize() < 64) {
                    firstInventory.getItemPlacePlace(i, j).push(itemName);
                    added = true;
                    break;
                } else if (firstInventory.getItemPlacePlace(i, j).isEmpty()) {
                    firstInventory.getItemPlacePlace(i, j).push(itemName);
                    added = true;
                    break;
                }
                System.out.print(i);
                System.out.println(j);
            }
            if(added){
                break;
            }
        }
        System.out.println(firstInventory.getItemPlacePlace(0,0).top());
        System.out.println(firstInventory.getItemPlacePlace(0,1).top());
        System.out.println(firstInventory.getItemPlacePlace(0,2).top());
        System.out.println(firstInventory.getItemPlacePlace(0,0).getSize());
        System.out.println(firstInventory.getItemPlacePlace(0,1).getSize());

    }

    public void removeItem(String itemName){
        boolean removed = false;
        for (int i = 0; i < firstInventory.getItemPlace().length; i++) {
            for (int j = 0; j < firstInventory.getItemPlaceLength(i); j++) {
                if (firstInventory.getItemPlacePlace(i, j).top() == itemName) {
                    firstInventory.getItemPlacePlace(i, j).pop();
                    removed = true;
                    break;
                }
            }
            if(removed){
                break;
            }
        }
    }
}


/**
 */
