package Model.Items;

import Model.DrawableObject;
import Model.InteractableObject;
import View.DrawingPanel;

import java.awt.event.KeyEvent;


/**
 * Created by 204g04 on 09.12.2016.
 */
public abstract class Item implements InteractableObject {

    public Item() {

    }

    @Override
    public void keyPressed(int key) {
        if(key == KeyEvent.VK_SPACE) {

        }
    }
}
