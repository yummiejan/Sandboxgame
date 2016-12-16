package Control.GameplayHandler;

import Model.*;
import Model.Creatures.Player;
import Model.Items.Blocks.Block;
import Model.Items.Blocks.Dirt;
import View.DrawingPanel;
import View.MainFrame;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by 204g07 on 09.12.2016.
 */
public class WorldHandler implements InteractableObject{

    private Block allBlocks[][];

    public WorldHandler(MainFrame frame){
        System.out.println("Width: "+frame.getActiveDrawingPanel().getWidth());
        System.out.println("Height: "+frame.getActiveDrawingPanel().getHeight());
        allBlocks = new Block[23][13];
        System.out.print("Blocks: ");
        for (int i = 0; i < allBlocks.length; i++) {
            int counter = (int)(Math.random()*2)+7;
            int console = 0;
            for (int j = 0; j < allBlocks[i].length; j++) {
                if(j > counter){
                    allBlocks[i][j] = new Dirt(i*50,j*50);
                    frame.getActiveDrawingPanel().addObject(allBlocks[i][j]);
                    console++;
                 }
            }
            System.out.print(console+" ");
        }
        System.out.println();
        int x = (int)(Math.random()*19+2);
        frame.getActiveDrawingPanel().addObject(new Player(x*50,xBlockLevel(x)*50,this));
        System.out.println("Spawnposition: "+x+", "+xBlockLevel(x));
    }

    public int xBlockLevel(int x){
        int n = 0;
        for(int i = 0; i < allBlocks[x].length; i++){
            if(allBlocks[x][i] == null){
                n++;
            }
        }
        return n-2;
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
    public Block getAllBlocks(int a, int b) {

        return allBlocks[a][b];
    }

    public void setAllBlocks(int a,int b,Block block) {
        allBlocks[a][b] = block;
    }
}
