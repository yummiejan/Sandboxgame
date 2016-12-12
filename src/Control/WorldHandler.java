package Control;

import Model.*;
import Model.List;import View.DrawingPanel;
import View.MainFrame;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by 204g07 on 09.12.2016.
 */
public class WorldHandler implements InteractableObject{

    private Block allBlocks[][];


    public WorldHandler(MainFrame frame){
        System.out.println(frame.getActiveDrawingPanel().getWidth());
        System.out.print(frame.getActiveDrawingPanel().getHeight());
        allBlocks = new Block[23][13];
        for (int i = 0; i < allBlocks.length; i++) {
            for (int j = 0; j < allBlocks[i].length; j++) {
                if(j > 9){
                    frame.getActiveDrawingPanel().addObject(new Dirt(i*50,j*50));
                }
            }
        }
        frame.getActiveDrawingPanel().addObject(new Player(100,400,this));
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
    public Block[][] getAllBlocks(int a, int b) {

        return allBlocks;
    }

    public void setAllBlocks(Block[][] allBlocks) {
        this.allBlocks = allBlocks;
    }
}
