package Control;

import Control.GameplayHandler.InventoryHandler;
import Control.GameplayHandler.WorldHandler;
import Model.Creatures.Player;
import Model.Gameplay.Inventory.CraftingTableInventory;
import Model.InteractableObject;
import View.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by 204g07 on 09.12.2016.
 */
public class MainController {
    private MainFrame frame;
    private WorldHandler handler;
    private InventoryHandler handler2;

    public MainController(MainFrame frame){
        handler2 = new InventoryHandler(frame);
        frame.getActiveDrawingPanel().addObject(handler2);
        handler = new WorldHandler(frame);
        frame.getActiveDrawingPanel().addObject(handler);
        //JOptionPane.showMessageDialog(frame, "Press ESC to pause.");
    }
}
