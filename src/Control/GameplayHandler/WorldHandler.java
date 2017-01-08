package Control.GameplayHandler;

import Model.*;
import Model.Creatures.Player;
import Model.Items.Blocks.*;
import View.DrawingPanel;
import View.MainFrame;
import javafx.scene.shape.Circle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import static sun.audio.AudioPlayer.player;

/**
 * Created by 204g07 on 09.12.2016.
 */
public class WorldHandler implements InteractableObject{

    private Block allBlocks[][];
    private MainFrame frame;
    private InventoryHandler ih;
    private Image currentBackground,background,invBackground;

    public WorldHandler(MainFrame frame){
        this.frame = frame;

        try {
            //background = ImageIO.read(new File("images/background.png"));
            invBackground = ImageIO.read(new File("images/background.png"));
        } catch (IOException e) {}

        currentBackground = background;


        allBlocks = new Block[23][13];
        for (int i = 0; i < allBlocks.length; i++) {
            int counter = (int) (Math.random() * 2) + 4;
            int counter2 = 5;//(int) (Math.random() * 200) + 4;
            for (int j = 0; j < allBlocks[i].length; j++) {
                if (j > counter) {
                    int random = (int) (Math.random() * 3) + 1;
                    if (random == 1 && j > counter + 3 ) {
                        allBlocks[i][j] = new Coal(i * 50, j * 50);
                    } else if (random == 2 || random == 3 && j > counter + 3) {
                        allBlocks[i][j] = new Stone(i * 50, j * 50);
                    } else {
                        allBlocks[i][j] = new Dirt(i * 50, j * 50);
                    }
                    //allBlocks[i][xBlockLevel(i)-1] = new Grass(i * 50, (xBlockLevel(i)-1)*50);
                    frame.getActiveDrawingPanel().addObject(allBlocks[i][j]);

                }
            }
        }
        allBlocks[0][xBlockLevel(0)-1] = new Furnace(0, (xBlockLevel(0)-1)*50,this);
        frame.getActiveDrawingPanel().addObject(allBlocks[0][xBlockLevel(0)]);
        allBlocks[22][xBlockLevel(0)-1] = new CraftingTable(frame.getWidth()-65, (xBlockLevel(22)-1)*50,this);
        frame.getActiveDrawingPanel().addObject(allBlocks[22][xBlockLevel(0)-1]);
        int x = (int)(Math.random()*19+2);
        frame.getActiveDrawingPanel().addObject(new Player(x*50,(xBlockLevel(x)-2)*50,this,ih));


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
        //g2d.drawImage(currentBackground,0,0,null);
    }

    @Override
    public void update(double dt) {
        //System.out.println(currentBackground.getSource());
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
