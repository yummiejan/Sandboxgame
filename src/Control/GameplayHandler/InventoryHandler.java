package Control.GameplayHandler;

import Model.Gameplay.Inventory.Hotbar;
import Model.InteractableObject;
import Model.Gameplay.Inventory.Inventory;
import View.DrawingPanel;
import View.MainFrame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by 204g04 on 12.12.2016.
 */
public class InventoryHandler implements InteractableObject{

    private Inventory inventory;
    private Hotbar hotbar;
    private boolean enabled;

    public InventoryHandler(MainFrame frame) {
        enabled = false;
        inventory = new Inventory(0,0);
        hotbar = new Hotbar(frame.getActiveDrawingPanel().getWidth()/2,0);
        if(enabled){
            frame.getActiveDrawingPanel().addObject(inventory);
            System.out.println("Inventory");
            frame.getActiveDrawingPanel().removeObject(hotbar);
        }else{
            frame.getActiveDrawingPanel().addObject(hotbar);
            System.out.println("Hotbar");
            frame.getActiveDrawingPanel().removeObject(inventory);
        }
    }

    @Override
    public void keyPressed(int key) {
        if(key == KeyEvent.VK_E){
            enabled = true;
            /*if(key == KeyEvent.VK_E){
             enabled = false;
             }*/
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
}
