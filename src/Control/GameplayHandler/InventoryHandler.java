package Control.GameplayHandler;

import Model.DataStructures.Stack;
import Model.Gameplay.Inventory.Hotbar;
import Model.InteractableObject;
import Model.Gameplay.Inventory.Inventory;
import View.DrawingPanel;
import View.MainFrame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

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
        for (int i = 0; i < 256; i++) {
            addNewItem("Dirt");
        }
        //addNewItem("Coal");
        //removeItem("Dirt");
        for (int i = 0; i < 256 ; i++) {
            addNewItem("Coal");
        }


    }

    @Override
    public void keyPressed(int key) {
        if (key == KeyEvent.VK_N) {
            addNewItem("Dirt");
        }
        /**
         * Aufrufen des Inventars im Spiel
         */
        if (key == KeyEvent.VK_E && firstHotbar.isDisplayed()) {
            if (!firstInventory.isDisplayed()) {
                firstInventory.setDisplayed(true);
            } else {
                firstInventory.setDisplayed(false);
            }
        }
        /**
         * Items werden mit den Num-Tasten in die Hotbar gezogen
         */
        if (key == KeyEvent.VK_NUMPAD1) {
            //Zwei Hilfstacks werden erzeugt, damit dort die Stacks zum Tauschen kurz gespeichert werden können
            Stack help = new Stack<String>();
            Stack help2 = new Stack<String>();
            System.out.println(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35));
            System.out.println(firstHotbar.getPlace(0));
            if (firstHotbar.getPlace(0).isEmpty()) {
                //wenn der Platz in der Hotbar leer ist, werden einfach alle Items in dem Stack des Platzes hinzugefügt
                while (!firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).isEmpty()) {
                    firstHotbar.getPlace(0).push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                    firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                }
            } else {
                //ansonsten soll getauscht werden
                while (!firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).isEmpty()) {
                    help.push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                    firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                }
                while (!firstHotbar.getPlace(0).isEmpty()) {
                    help2.push(firstHotbar.getPlace(0).top());
                    firstHotbar.getPlace(0).pop();
                }
                for (int i = 0; i < help.getSize(); i++) {
                    firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).push(help2.top());
                    help2.pop();
                }
                for (int i = 0; i < 64 && !help.isEmpty(); i++) {
                    firstHotbar.getPlace(0).push(help.top());
                    help.pop();
                }
            }
        }
        if(key == KeyEvent.VK_NUMPAD2) {
            Stack help = new Stack<String>();
            Stack help2 = new Stack<String>();
            System.out.println(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35));

            if (firstHotbar.getPlace(1).isEmpty()) {
                while (!firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).isEmpty()) {
                    firstHotbar.getPlace(1).push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                    firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                }
            }else {
                while (!firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).isEmpty()) {
                    help.push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                    firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                }
                while (!firstHotbar.getPlace(1).isEmpty()) {
                    help2.push(firstHotbar.getPlace(1).top());
                    firstHotbar.getPlace(1).pop();
                }
                for (int i = 0; i < help.getSize(); i++) {
                    firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).push(help2.top());
                    help2.pop();
                }
                for (int i = 0; i < 64 && !help.isEmpty(); i++) {
                    firstHotbar.getPlace(1).push(help.top());
                    help.pop();
                }
            }
        }
        if(key == KeyEvent.VK_NUMPAD3) {
            Stack help = new Stack<String>();
            Stack help2 = new Stack<String>();
            System.out.println(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35));

            if (firstHotbar.getPlace(2).isEmpty()) {
                while (!firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).isEmpty()) {
                    firstHotbar.getPlace(2).push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                    firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                }
            }else {
                while (!firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).isEmpty()) {
                    help.push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                    firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                }
                while (!firstHotbar.getPlace(2).isEmpty()) {
                    help2.push(firstHotbar.getPlace(2).top());
                    firstHotbar.getPlace(2).pop();
                }
                for (int i = 0; i < help.getSize(); i++) {
                    firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).push(help2.top());
                    help2.pop();
                }
                for (int i = 0; i < 64 && !help.isEmpty(); i++) {
                    firstHotbar.getPlace(2).push(help.top());
                    help.pop();
                }
            }
        }
        if(key == KeyEvent.VK_NUMPAD4) {
            Stack help = new Stack<String>();
            Stack help2 = new Stack<String>();
            System.out.println(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35));

            if (firstHotbar.getPlace(3).isEmpty()) {
                while (!firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).isEmpty()) {
                    firstHotbar.getPlace(3).push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                    firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                }
            }else {
                while (!firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).isEmpty()) {
                    help.push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                    firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                }
                while (!firstHotbar.getPlace(3).isEmpty()) {
                    help2.push(firstHotbar.getPlace(3).top());
                    firstHotbar.getPlace(3).pop();
                }
                for (int i = 0; i < help.getSize(); i++) {
                    firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).push(help2.top());
                    help2.pop();
                }
                for (int i = 0; i < 64 && !help.isEmpty(); i++) {
                    firstHotbar.getPlace(3).push(help.top());
                    help.pop();
                }
            }
        }
        if(key == KeyEvent.VK_NUMPAD5) {
            Stack help = new Stack<String>();
            Stack help2 = new Stack<String>();
            System.out.println(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35));
            System.out.println(firstHotbar.getPlace(4).top());
            if (firstHotbar.getPlace(4).isEmpty()) {
                while (!firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).isEmpty()) {
                    firstHotbar.getPlace(4).push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                    firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                }
            }else {
                while (!firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).isEmpty()) {
                    help.push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                    firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                }
                while (!firstHotbar.getPlace(4).isEmpty()) {
                    help2.push(firstHotbar.getPlace(4).top());
                    firstHotbar.getPlace(4).pop();
                }
                for (int i = 0; i < help.getSize(); i++) {
                    firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).push(help2.top());
                    help2.pop();
                }
                for (int i = 0; i < 64 && !help.isEmpty(); i++) {
                    firstHotbar.getPlace(4).push(help.top());
                    help.pop();
                }
            }
        }
        if(key == KeyEvent.VK_C){
            //mit der Taste C kann überprüft werden was in den Stacks der ganzen Hotbar ist und in dem Stack des Ausgewählten Platzesim Inventar
            System.out.println("Inventar:"+firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
            System.out.println("Hotbar:"+firstHotbar.getPlace(0).top());
            System.out.println("Hotbar:"+firstHotbar.getPlace(1).top());
            System.out.println("Hotbar:"+firstHotbar.getPlace(2).top());
            System.out.println("Hotbar:"+firstHotbar.getPlace(3).top());
            System.out.println("Hotbar:"+firstHotbar.getPlace(4).top());
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

    /**
     * Adds new Item to the Inventory.
     * @param itemName Name of the item to be added to the inventory.
     */
    public void addNewItem(String itemName){
        boolean added = false;
        for (int i = 0; i < firstInventory.getItemPlace().length; i++) {
            for (int j = 0; j < firstInventory.getItemPlaceLength(i); j++) {
                if (firstInventory.getItemPlacePlace(i, j).top() == itemName && firstInventory.getItemPlacePlace(i, j).getSize() < 64 || firstInventory.getItemPlacePlace(i, j).isEmpty()) {
                    firstInventory.getItemPlacePlace(i, j).push(itemName);
                    added = true;
                    break;
                }
            }
            if(added){
                break;
            }
        }
    }

    /**
     * Removes given Item from the inventory.
     *
     * @param itemName Item to be removed from the Inventory.
     */

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
