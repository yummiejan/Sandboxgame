package Control;

import Control.GameplayHandler.WorldHandler;
import View.*;

/**
 * Created by 204g07 on 09.12.2016.
 */
public class MainController {
    private MainFrame frame;
    private WorldHandler handler;

    public MainController(MainFrame frame){
        handler = new WorldHandler(frame);
        frame.getActiveDrawingPanel().addObject(handler);
        //JOptionPane.showMessageDialog(frame, "Press ESC to pause.");
    }
}
