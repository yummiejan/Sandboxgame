package Control;

import Model.Hotbar;
import Model.InteractableObject;
import Model.Inventory;
import View.DrawingPanel;
import View.MainFrame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by 204g04 on 12.12.2016.
 */
public class InventoryHandler implements InteractableObject{

    private boolean enabled;
    public InventoryHandler(MainFrame frame) {
        if(enabled){
            frame.getActiveDrawingPanel().addObject(new Inventory(0,0));
        }else {
            frame.getActiveDrawingPanel().addObject(new Hotbar(frame.getActiveDrawingPanel().getWidth()/2,0));
        }
    }

    @Override
    public void keyPressed(int key) {
        if(key == KeyEvent.VK_E && enabled == false){
            enabled = true;
            System.out.println(enabled);
        }
        if(key == KeyEvent.VK_I && enabled == true){
            enabled = false;
            System.out.println(enabled);
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
