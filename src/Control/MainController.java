package Control;

import Control.GameplayHandler.InventoryHandler;
import Control.GameplayHandler.WorldHandler;
import Model.Creatures.Player;
import Model.InteractableObject;
import View.*;

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
    private Player player;

    public MainController(MainFrame frame){
        handler = new WorldHandler(frame);
        handler2 = new InventoryHandler(frame);
        frame.getActiveDrawingPanel().addObject(handler);
        frame.getActiveDrawingPanel().addObject(handler2);
    }
}
