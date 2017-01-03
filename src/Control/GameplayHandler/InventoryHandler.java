package Control.GameplayHandler;

import Model.DataStructures.Stack;
import Model.Gameplay.Inventory.Hotbar;
import Model.InteractableObject;
import Model.Gameplay.Inventory.Inventory;
import Model.Items.Blocks.Block;
import Model.Items.Blocks.Dirt;
import Model.Items.Blocks.InventoryDirt;
import Model.Items.Item;
import View.DrawingPanel;
import View.MainFrame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by 204g04 on 12.12.2016.
 */
public class InventoryHandler implements InteractableObject{

    private MainFrame frame;
    private Inventory firstInventory;
    private Hotbar firstHotbar;
    private Stack<Item> itemStack;

    public InventoryHandler(MainFrame frame) {
        this.frame = frame;
        firstInventory = new Inventory(0,0);
        frame.getActiveDrawingPanel().addObject(firstInventory);
        firstInventory.setDisplayed(false);
        firstHotbar = new Hotbar(frame.getActiveDrawingPanel().getWidth()/2,0);
        frame.getActiveDrawingPanel().addObject(firstHotbar);
        itemStack = new Stack<Item>();
        //System.out.println(firstInventory.getItemPlace(0,0));
        //addNewStack(firstInventory.getItemPlace(0,0),0,0);
        addNewItem(new InventoryDirt(0,0));
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

    public void addNewItem(Item itemName){
            for (int i = 0; i < firstInventory.getMainList().length; i++) {
                for (int j = 0; j < firstInventory.getMainList()[i].getSize(); j++) {
                    if(!firstInventory.getMainList()[i].isEmpty() && !firstInventory.getMainList()[i].getContent().top().equals(itemName)){
                        firstInventory.getMainList()[i].next();
                        System.out.print("ada");
                    }else{
                        System.out.print("ada");
                        firstInventory.getMainList()[i].getContent().push(new InventoryDirt(0,0));
                    }
                    System.out.print(i);
                }
            }
    }
}

/**for (int j = 0; j < firstInventory.getMainList()[i].getSize(); j++) {
 if(firstInventory.getMainList()[i].getContent().top().equals("Dirt")){
 firstInventory.getMainList()[i].getContent().push(1);
 }else{

 }
 }
 }*/
