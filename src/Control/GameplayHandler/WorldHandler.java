package Control.GameplayHandler;

import Model.*;
import Model.Creatures.Player;
import Model.Items.Blocks.Block;
import Model.Items.Blocks.Coal;
import Model.Items.Blocks.Dirt;
import Model.Items.Blocks.Furnace;
import View.DrawingPanel;
import View.MainFrame;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;

/**
 * Created by 204g07 on 09.12.2016.
 */
public class WorldHandler implements InteractableObject{

    private Block allBlocks[][];
    private MainFrame frame;
    private InventoryHandler ih;
    private Ellipse2D.Double sonne;

    public WorldHandler(MainFrame frame){
        this.frame = frame;
        allBlocks = new Block[23][13];
        for (int i = 0; i < allBlocks.length; i++) {
            int counter = (int) (Math.random() * 2) + 6;
            for (int j = 0; j < allBlocks[i].length; j++) {
                if (j > counter) {
                    int random = (int)(Math.random()*5)+1;
                    if (random==1){
                        allBlocks[i][j] = new Coal(i * 50, j * 50);
                    }else{
                        allBlocks[i][j] = new Dirt(i * 50, j * 50);
                    }
                    frame.getActiveDrawingPanel().addObject(allBlocks[i][j]);
                }
            }
        }
        allBlocks[0][xBlockLevel(0)-1] = new Furnace(0, (xBlockLevel(0)-1)*50,this);
        frame.getActiveDrawingPanel().addObject(allBlocks[0][xBlockLevel(0)]);
        int x = (int)(Math.random()*19+2);
        frame.getActiveDrawingPanel().addObject(new Player(x*50,(xBlockLevel(x)-2)*50,this,ih));

        sonne = new Ellipse2D.Double(500,500,100,100);
    }

    public int xBlockLevel(int x){
        int n = 0;
        for(int i = 0; i < allBlocks[x].length; i++){
            if(allBlocks[x][i] == null){
                n++;
            }
        }
        return n;
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
        g2d.setColor(new Color(255, 212,0));
        g2d.fill(sonne);
        g2d.draw(sonne);
    }

    @Override
    public void update(double dt) {

    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public Block getAllBlocks(int a, int b) {
        return allBlocks[a][b];
    }

    /**
     *
     * @param a
     * @param b
     * @param block
     */
    public void setAllBlocks(int a,int b,Block block) {
        allBlocks[a][b] = block;
    }

    public MainFrame getFrame()
    {
        return frame;
    }
}
