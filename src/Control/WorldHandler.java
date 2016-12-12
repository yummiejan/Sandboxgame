package Control;

import Model.*;
import Model.List;import View.DrawingPanel;
import View.MainFrame;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by 204g07 on 09.12.2016.
 */
public class WorldHandler implements InteractableObject{

    private Model.List<Block> list;

    public WorldHandler(MainFrame frame){
        System.out.println(frame.getActiveDrawingPanel().getWidth());
        for (int i = 0; i < frame.getActiveDrawingPanel().getWidth()/50 ; i++) {
            frame.getActiveDrawingPanel().addObject(new Dirt(i*50,frame.getActiveDrawingPanel().getHeight()-50));
        }

    }

    @Override
    public void keyPressed(int key) {

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
