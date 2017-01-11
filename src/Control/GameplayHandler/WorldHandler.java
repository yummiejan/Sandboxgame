package Control.GameplayHandler;

import Control.Itemhandler.FurnaceHandler;
import Model.*;
import Model.Creatures.Player;
import Model.Items.Blocks.*;
import View.DrawingPanel;
import View.MainFrame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import static sun.audio.AudioPlayer.player;

/**
 * Created by 204g07 on 09.12.2016.
 */
public class WorldHandler implements InteractableObject{

    private Block allBlocks[][];
    private MainFrame frame;
    private InventoryHandler invHandler;
    private Image currentBackground,background,invBackground;
    private Player player;
    private Furnace furnace;
    private FurnaceHandler furnaceHandler;

    public WorldHandler(MainFrame frame){
        this.frame = frame;
        invHandler = new InventoryHandler(frame);
        frame.getActiveDrawingPanel().addObject(invHandler);
        try {
            //background = ImageIO.read(new File("images/background.png"));
            invBackground = ImageIO.read(new File("images/background.png"));
        } catch (IOException e) {}

        currentBackground = background;


        allBlocks = new Block[23][13];
        for (int i = 0; i < allBlocks.length; i++) {
            int counter = (int)(Math.random() * 2) + 4;
            int grassChance = (int)(Math.random());
            for (int j = 0; j < allBlocks[i].length; j++) {
                if(counter == j){
                    allBlocks[i][j] = new Grass(i * 50, j*50);
                    frame.getActiveDrawingPanel().addObject(allBlocks[i][j]);
                }
                //TODO Random Chance für grass spawnen & Grass muss mit entfernt werden, wenn dadrunter der Block abgebaut wird
                if(counter == (j-1) /*&& grassChance == 0*/){
                    allBlocks[i][j] = new Brushes(i * 50, (j-2)*50);
                    frame.getActiveDrawingPanel().addObject(allBlocks[i][j]);
                    //grassChance = (int)(Math.random());
                }else{
                    //grassChance = (int)(Math.random());
                }
                if (j > counter) {
                    int random = (int) (Math.random() * 3) + 1;
                    if (random == 1 && j > counter + 3 ) {
                        allBlocks[i][j] = new Coal(i * 50, j * 50);
                    } else if (random == 2 || random == 3 && j > counter + 3) {
                        allBlocks[i][j] = new Stone(i * 50, j * 50);
                    } else {
                        allBlocks[i][j] = new Dirt(i * 50, j * 50);
                    }
                    frame.getActiveDrawingPanel().addObject(allBlocks[i][j]);
                }
            }

        }
        furnace = new Furnace(0, (xBlockLevel(0)-1)*50);
        allBlocks[0][xBlockLevel(0)-1] = furnace;
        frame.getActiveDrawingPanel().addObject(allBlocks[0][xBlockLevel(0)]);
        furnaceHandler = new FurnaceHandler(frame, furnace);
        frame.getActiveDrawingPanel().addObject(furnaceHandler);
        allBlocks[22][xBlockLevel(0)-1] = new CraftingTable(frame.getWidth()-65, (xBlockLevel(22)-1)*50);
        frame.getActiveDrawingPanel().addObject(allBlocks[22][xBlockLevel(0)-1]);
        int x = (int)(Math.random()*19+2);
        player = new Player(x*50,(xBlockLevel(x)-2)*50,this,invHandler);
        frame.getActiveDrawingPanel().addObject(player);
    }

    /**
     * Methode für die Höhe der Blöcke.
     * @param x Die zu analysierende Stelle.
     * @return Anzahl der sich auf einer x-Koordinate befindende Blöcke.
     */

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
        if(key == KeyEvent.VK_F && player.getBlock() != null) {
            if(player.getBlock().getName().equals("Furnace")) {
                if(!furnaceHandler.getGuiDisplayed()){
                    furnaceHandler.setGuiDisplayed(true);
                    player.setMoveBlocked(true);
                } else {
                    furnaceHandler.setGuiDisplayed(false);
                    player.setMoveBlocked(false);
                }
            } else System.out.println("You cannot interact with this Block.");
        }
        if(invAndFurnaceOpened()) {
            if(key == KeyEvent.VK_MINUS) {
                if(furnaceHandler.getCurrentPlace() == 1) {
                    invHandler.addNewItem(furnaceHandler.getFrontOQ());
                    furnaceHandler.removeObject();
                } else if(furnaceHandler.getCurrentPlace() == 2) {
                    invHandler.addNewItem(furnaceHandler.getFrontFQ());
                    furnaceHandler.removeFuel();
                } else if(furnaceHandler.getCurrentPlace() == 3) {
                    invHandler.addNewItem(furnaceHandler.getTopPS());
                    furnaceHandler.removeProduct();
                }
            }
            if(key == KeyEvent.VK_PLUS) {
                if(furnaceHandler.getCurrentPlace() == 1) {
                    furnaceHandler.addObject(invHandler.getCurrentItem());
                    invHandler.removeCurrentItem();
                } else if(furnaceHandler.getCurrentPlace() == 2) {
                    furnaceHandler.addFuel(invHandler.getCurrentItem());
                    invHandler.removeCurrentItem();
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void draw(DrawingPanel dp, Graphics2D g2d) {
        g2d.drawImage(currentBackground,-15,0,null);
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
        try {
            return allBlocks[a][b];
        }
        catch(ArrayIndexOutOfBoundsException e) {
        }
        return null;
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

    private boolean invAndFurnaceOpened() {
        if(furnaceHandler.getGuiDisplayed() && invHandler.firstInvDisplayed()) {
            return true;
        }
        return false;
    }
}