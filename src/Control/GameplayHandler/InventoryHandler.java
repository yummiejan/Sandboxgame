package Control.GameplayHandler;

import Model.DataStructures.Stack;
import Model.Gameplay.Inventory.CraftingTableInventory;
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
    private CraftingTableInventory firstCTI;
    private boolean escape;

    public InventoryHandler(MainFrame frame) {
        this.frame = frame;
        firstInventory = new Inventory(0, 0);
        frame.getActiveDrawingPanel().addObject(firstInventory);
        firstHotbar = new Hotbar(frame.getActiveDrawingPanel().getWidth() / 2, 0);
        frame.getActiveDrawingPanel().addObject(firstHotbar);
        firstCTI = new CraftingTableInventory(frame.getActiveDrawingPanel().getWidth() - 315, 0);
        frame.getActiveDrawingPanel().addObject(firstCTI);
        for (int i = 0; i < 32; i++) {
            addNewItem("Wood");
        }
    }

    @Override
    public void keyPressed(int key) {
        if(!escape) {
            if (key == KeyEvent.VK_N) {
                addNewItem("Wood");
                addNewItem("Stone");
            }

            /**
             * Das gecraftete Item wird dem Inventar hinzugefügt
             */
            if (key == KeyEvent.VK_ENTER) {
                if (firstCTI.crafted().equals("Stick")) {
                    for (int i = 0; i < 4; i++) {
                        addNewItem(firstCTI.crafted());
                    }
                } else if (!firstCTI.crafted().equals("Nichts")) {
                    addNewItem(firstCTI.crafted());
                }
                if (!firstCTI.crafted().equals("Nichts")) {
                    firstCTI.getCraftingPlace(0, 0).pop();
                    firstCTI.getCraftingPlace(0, 1).pop();
                    firstCTI.getCraftingPlace(0, 2).pop();
                    firstCTI.getCraftingPlace(1, 0).pop();
                    firstCTI.getCraftingPlace(1, 1).pop();
                    firstCTI.getCraftingPlace(1, 2).pop();
                    firstCTI.getCraftingPlace(2, 0).pop();
                    firstCTI.getCraftingPlace(2, 1).pop();
                    firstCTI.getCraftingPlace(2, 2).pop();
                } else {
                    for (int i = 0; i < firstCTI.getCraftingPlaceLength(); i++) {
                        for (int j = 0; j < firstCTI.aGetCraftingPlaceLength(i); j++) {
                            if (!firstCTI.getCraftingPlace(i, j).isEmpty()) {
                                addNewItem(firstCTI.getCraftingPlace(i, j).top().toString());
                                firstCTI.getCraftingPlace(i, j).pop();
                            }
                        }
                    }
                }
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
             * CraftingInventar wird geöffnet
             */
            if (key == KeyEvent.VK_G) {
                if (!firstCTI.isDisplayed()) {
                    firstCTI.setDisplayed(true);
                } else {
                    firstCTI.setDisplayed(false);
                }
            }
            /**
             * Items werden mit den Num-Tasten in die Hotbar gezogen
             */
            if (!firstCTI.isDisplayed()) {
                if (key == KeyEvent.VK_NUMPAD1 && firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top() != null) {
                    //Zwei Hilfstacks werden erzeugt, damit dort die Stacks zum Tauschen kurz gespeichert werden können
                    Stack help = new Stack<String>();
                    Stack help2 = new Stack<String>();
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
                        for (int i = 0;  i < 64 && !help2.isEmpty(); i++) {
                            firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).push(help2.top());
                            help2.pop();
                        }
                        for (int i = 0; i < 64 && !help.isEmpty(); i++) {
                            firstHotbar.getPlace(0).push(help.top());
                            help.pop();
                        }
                    }
                }
                if (key == KeyEvent.VK_NUMPAD2 && firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top() != null) {
                    Stack help = new Stack<String>();
                    Stack help2 = new Stack<String>();
                    if (firstHotbar.getPlace(1).isEmpty()) {
                        while (!firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).isEmpty()) {
                            firstHotbar.getPlace(1).push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                            firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                        }
                    } else {
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
                if (key == KeyEvent.VK_NUMPAD3 && firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top() != null) {
                    Stack help = new Stack<String>();
                    Stack help2 = new Stack<String>();
                    if (firstHotbar.getPlace(2).isEmpty()) {
                        while (!firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).isEmpty()) {
                            firstHotbar.getPlace(2).push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                            firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                        }
                    } else {
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
                if (key == KeyEvent.VK_NUMPAD4 && firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top() != null) {
                    Stack help = new Stack<String>();
                    Stack help2 = new Stack<String>();
                    if (firstHotbar.getPlace(3).isEmpty()) {
                        while (!firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).isEmpty()) {
                            firstHotbar.getPlace(3).push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                            firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                        }
                    } else {
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
                if (key == KeyEvent.VK_NUMPAD5 && firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top() != null) {
                    Stack help = new Stack<String>();
                    Stack help2 = new Stack<String>();
                    if (firstHotbar.getPlace(4).isEmpty()) {
                        while (!firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).isEmpty()) {
                            firstHotbar.getPlace(4).push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                            firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                        }
                    } else {
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
            }else{
                /**
                 * Bedinung des Craftingfeldes
                 */
                if(key == KeyEvent.VK_NUMPAD1 && firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top() != null){
                    //Wenn der Platz leer ist, wird ganz normal das oberste Item des ausgewählten Stacks im Inventar hinzugefügt
                    if(firstCTI.getCraftingPlace(0,2).isEmpty()) {
                        firstCTI.getCraftingPlace(0, 2).push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                        firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                    }else{
                        //Wenn doch etwas in dem Feld ist, wird dieses item dem Inventar wieder hinzugefügt (addNewItem) und der obrige Prozess wiederholt sich
                        addNewItem(firstCTI.getCraftingPlace(0, 2).top().toString());
                        firstCTI.getCraftingPlace(0, 2).pop();
                        firstCTI.getCraftingPlace(0, 2).push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                        firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                    }
                }else if(key == KeyEvent.VK_NUMPAD2 && firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top() != null) {
                    if (firstCTI.getCraftingPlace(1, 2).isEmpty()) {
                        firstCTI.getCraftingPlace(1, 2).push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                        firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                    } else {
                        addNewItem(firstCTI.getCraftingPlace(1, 2).top().toString());
                        firstCTI.getCraftingPlace(1, 2).pop();
                        firstCTI.getCraftingPlace(1, 2).push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                        firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                    }
                }else if(key == KeyEvent.VK_NUMPAD3 && firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top() != null) {
                    if (firstCTI.getCraftingPlace(2, 2).isEmpty()) {
                        firstCTI.getCraftingPlace(2, 2).push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                        firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                    } else {
                        addNewItem(firstCTI.getCraftingPlace(2, 2).top().toString());
                        firstCTI.getCraftingPlace(2, 2).pop();
                        firstCTI.getCraftingPlace(2, 2).push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                        firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                    }
                }else if(key == KeyEvent.VK_NUMPAD4 && firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top() != null) {
                    if (firstCTI.getCraftingPlace(0, 1).isEmpty()) {
                        firstCTI.getCraftingPlace(0, 1).push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                        firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                    } else {
                        addNewItem(firstCTI.getCraftingPlace(0, 1).top().toString());
                        firstCTI.getCraftingPlace(0, 1).pop();
                        firstCTI.getCraftingPlace(0, 1).push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                        firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                    }
                }else if(key == KeyEvent.VK_NUMPAD5 && firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top() != null) {
                    if (firstCTI.getCraftingPlace(1, 1).isEmpty()) {
                        firstCTI.getCraftingPlace(1, 1).push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                        firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                    } else {
                        firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).push(firstCTI.getCraftingPlace(1, 1).top());
                        firstCTI.getCraftingPlace(1, 1).pop();
                    }
                }else if(key == KeyEvent.VK_NUMPAD6 && firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top() != null) {
                    if (firstCTI.getCraftingPlace(2, 1).isEmpty()) {
                        firstCTI.getCraftingPlace(2, 1).push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                        firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                    } else {
                        addNewItem(firstCTI.getCraftingPlace(2, 1).top().toString());
                        firstCTI.getCraftingPlace(2, 1).pop();
                        firstCTI.getCraftingPlace(2, 1).push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                        firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                    }
                }else if(key == KeyEvent.VK_NUMPAD7 && firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top() != null) {
                    if (firstCTI.getCraftingPlace(0, 0).isEmpty()) {
                        firstCTI.getCraftingPlace(0, 0).push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                        firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                    } else {
                        addNewItem(firstCTI.getCraftingPlace(0, 0).top().toString());
                        firstCTI.getCraftingPlace(0, 0).pop();
                        firstCTI.getCraftingPlace(0, 0).push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                        firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                    }
                }else if(key == KeyEvent.VK_NUMPAD8 && firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top() != null) {
                    if (firstCTI.getCraftingPlace(1, 0).isEmpty()) {
                        firstCTI.getCraftingPlace(1, 0).push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                        firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                    } else {
                        addNewItem(firstCTI.getCraftingPlace(1, 0).top().toString());
                        firstCTI.getCraftingPlace(1, 0).pop();
                        firstCTI.getCraftingPlace(1, 0).push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                        firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                    }
                }else if(key == KeyEvent.VK_NUMPAD9 && firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top() != null) {
                        if (firstCTI.getCraftingPlace(2, 0).isEmpty()) {
                        firstCTI.getCraftingPlace(2, 0).push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                        firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                    } else {
                        addNewItem(firstCTI.getCraftingPlace(2, 0).top().toString());
                        firstCTI.getCraftingPlace(2, 0).pop();
                        firstCTI.getCraftingPlace(2, 0).push(firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                        firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
                    }
                }
            }
            //mit der Taste C kann überprüft werden was in den Stacks der ganzen Hotbar ist und in dem Stack des Ausgewählten Platzesim Inventar
            if (key == KeyEvent.VK_C) {
                System.out.println("Inventar:" + firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top());
                System.out.println("Hotbar:" + firstHotbar.getPlace(0).top());
                System.out.println("Hotbar:" + firstHotbar.getPlace(1).top());
                System.out.println("Hotbar:" + firstHotbar.getPlace(2).top());
                System.out.println("Hotbar:" + firstHotbar.getPlace(3).top());
                System.out.println("Hotbar:" + firstHotbar.getPlace(4).top());
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

    /**
     * Adds new Item to the Inventory.
     * @param itemName Name of the item to be added to the inventory.
     */
    public void addNewItem(String itemName){
        boolean added = false;
        for (int i = 0; i < firstInventory.getItemPlace().length; i++) {
            for (int j = 0; j < firstInventory.getItemPlaceLength(i); j++) {
                if (firstInventory.getItemPlacePlace(i, j).top() != null && firstInventory.getItemPlacePlace(i, j).top().equals(itemName) && firstInventory.getItemPlacePlace(i, j).getSize() < 64 ) {
                    firstInventory.getItemPlacePlace(i, j).push(itemName);
                    added = true;
                    break;
                }
            }
            if(added){
                break;
            }
        }
        if(!added) {
            for (int i = 0; i < firstInventory.getItemPlace().length; i++) {
                for (int j = 0; j < firstInventory.getItemPlaceLength(i); j++) {
                    if(firstInventory.getItemPlacePlace(i,j).isEmpty()){
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
    }

    /**
     * Removes given Item from the inventory.
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

    /**
     * @return ob das Inventar geöffnet ist.
     */
    public boolean firstInvDisplayed() {
        return firstInventory.isDisplayed();
    }

    /**
     * Entfernt das aktuell ausgewählte Objekt.
     */
    public void removeCurrentItem() {
        firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).pop();
    }

    /**
     * @return das aktuelle Objekt.
     */
    public String getCurrentItem() {
        String thing = ""+firstInventory.getItemPlacePlace(firstInventory.getChosenX() / 35, firstInventory.getChosenY() / 35).top();
        return thing;
    }

    /**
     * @return die Hotbar.
     */
    public Hotbar getFirstHotbar() {
        return firstHotbar;
    }

    /**
     * @return dsd Inventory..
     */
    public Inventory getFirstIventory() {
        return firstInventory;
    }

    public void setEscape(boolean escape) {
        this.escape = escape;
    }
}
