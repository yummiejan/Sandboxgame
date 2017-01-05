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
        for (int i = 0; i < 2; i++) {
            addNewItem("Dirt");
        }

        addNewItem("Dirt");
        addNewItem("Coal");
        //addNewItem("Furnace");
        System.out.println(firstInventory.getItemPlacePlace(0,0).top()+ "adawdaw");
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
        if(key == KeyEvent.VK_1){
            Stack help = new Stack<String>();
            Stack help2 = new Stack<String>();
            System.out.println(firstInventory.getItemPlacePlace(firstInventory.getChosenX()/35,firstInventory.getChosenY()/35));
            System.out.println(firstHotbar.getPlace(0));
            if(firstHotbar.getPlace(0).isEmpty()) {
                while (!firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).isEmpty()) {
                    firstHotbar.getPlace(0).push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                    firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                }
            }else{
                while (!firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).isEmpty()){
                    help.push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                    firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                }
                while (!firstHotbar.getPlace(0).isEmpty()){
                    help2.push(firstHotbar.getPlace(1).top());
                    firstHotbar.getPlace(0).pop();
                }
                firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).push(help2.top());
                firstHotbar.getPlace(0).push(help.top());
            }
            System.out.println(firstHotbar.getPlace(0).top());
        }
        if(key == KeyEvent.VK_2) {
            Stack help = new Stack<String>();
            Stack help2 = new Stack<String>();
            System.out.println(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35));

            if (firstHotbar.getPlace(1).isEmpty()) {
                while (!firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).isEmpty()) {
                    firstHotbar.getPlace(1).push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                    firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                }
            }
            System.out.println(firstHotbar.getPlace(1).top());
        }
        if(key == KeyEvent.VK_3) {
            Stack help = new Stack<String>();
            Stack help2 = new Stack<String>();
            System.out.println(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35));

            if (firstHotbar.getPlace(2).isEmpty()) {
                while (!firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).isEmpty()) {
                    firstHotbar.getPlace(2).push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                    firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                }
            }
            System.out.println(firstHotbar.getPlace(2).top());
        }
        if(key == KeyEvent.VK_4) {
            Stack help = new Stack<String>();
            Stack help2 = new Stack<String>();
            System.out.println(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35));

            if (firstHotbar.getPlace(3).isEmpty()) {
                while (!firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).isEmpty()) {
                    firstHotbar.getPlace(3).push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                    firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                }
            }
            System.out.println(firstHotbar.getPlace(3).top());
        }
        if(key == KeyEvent.VK_5) {
            Stack help = new Stack<String>();
            Stack help2 = new Stack<String>();
            System.out.println(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35));
            System.out.println(firstHotbar.getPlace(4).top());
            if (firstHotbar.getPlace(4).isEmpty()) {
                while (!firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).isEmpty()) {
                    firstHotbar.getPlace(4).push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                    firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                }
            }
            System.out.println(firstHotbar.getPlace(4).top());
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
