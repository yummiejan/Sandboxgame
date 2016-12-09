package Control;

import View.*;

/**
 * Created by 204g07 on 09.12.2016.
 */
public class MainController {
    private MainFrame frame;
    private WorldHandler handler;
    //alsjkhdfkjasgkfjsaefdzasjdkhfoispadfhu

    public MainController(MainFrame frame){
        handler = new WorldHandler(frame);
    }
}
