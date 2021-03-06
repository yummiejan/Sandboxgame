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
import java.awt.geom.Rectangle2D;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
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
    private Image currentBackground, background, invBackground;
    private Player player;
    private Furnace furnace;
    private FurnaceHandler furnaceHandler;
    private boolean escape;
    private Rectangle2D.Double escapeRectangle;
    private Font font;

    public WorldHandler(MainFrame frame){
        this.frame = frame;
        invHandler = new InventoryHandler(frame);
        frame.getActiveDrawingPanel().addObject(invHandler);
        try {
            //background = ImageIO.read(new File("images/background.png"));
            invBackground = ImageIO.read(new File("images/background.png"));
        } catch (IOException e) {}
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/mcfont.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts/mcfont.ttf")));
        } catch (IOException | FontFormatException e) {}
        currentBackground = background;
        escapeRectangle = new Rectangle2D.Double(-10, -10, 1200, 700);

        allBlocks = new Block[23][13];
        for (int i = 0; i < allBlocks.length; i++) {
            int counter = (int)(Math.random() * 2) + 4;
            for (int j = 0; j < allBlocks[i].length; j++) {
                if(counter == j){
                    allBlocks[i][j] = new Grass(i * 50, j * 50);
                    frame.getActiveDrawingPanel().addObject(allBlocks[i][j]);
                }
                //TODO Random Chance für grass spawnen & Grass muss mit entfernt werden, wenn dadrunter der Block abgebaut wird
                double randomGrass = (Math.random());
                if(counter == j && randomGrass >= 0.3){
                    allBlocks[i][j-1] = new Brushes(i * 50, (j - 1) * 50);
                    frame.getActiveDrawingPanel().addObject(allBlocks[i][j-1]);
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
        furnace = new Furnace(0, (xBlockLevel(0) - 1) * 50);
        allBlocks[0][xBlockLevel(0) - 1] = furnace;
        frame.getActiveDrawingPanel().addObject(allBlocks[0][xBlockLevel(0)]);
        furnaceHandler = new FurnaceHandler(frame, furnace);
        frame.getActiveDrawingPanel().addObject(furnaceHandler);
        allBlocks[22][xBlockLevel(0)-1] = new CraftingTable(frame.getWidth() - 65, (xBlockLevel(22) - 1) * 50);
        frame.getActiveDrawingPanel().addObject(allBlocks[22][xBlockLevel(0) - 1]);
        int x = (int)(Math.random() * 19 + 2);
        player = new Player(x*50,(xBlockLevel(x) - 2) * 50, this, invHandler);
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
                if(furnaceHandler.getCurrentPlace() == 1 && !furnaceHandler.getObjectQueue().isEmpty()) {
                    invHandler.addNewItem(furnaceHandler.getFrontOQ());
                    furnaceHandler.removeObject();
                } else if(furnaceHandler.getCurrentPlace() == 2 && !furnaceHandler.getFuelQueue().isEmpty()) {
                    invHandler.addNewItem(furnaceHandler.getFrontFQ());
                    furnaceHandler.removeFuel();
                } else if(furnaceHandler.getCurrentPlace() == 3 && !furnaceHandler.getProductStack().isEmpty()) {
                    invHandler.addNewItem(furnaceHandler.getTopPS());
                    furnaceHandler.removeProduct();
                }
            }
            if(key == KeyEvent.VK_PLUS) {
                if(furnaceHandler.getCurrentPlace() == 1 && furnaceHandler.getObjectCounter() < 64 && (invHandler.getCurrentItem().equals("Dirt") || invHandler.getCurrentItem().equals("Stone") || invHandler.getCurrentItem().equals("Wood"))) {
                    furnaceHandler.addObject(invHandler.getCurrentItem());
                    invHandler.removeCurrentItem();
                } else if(furnaceHandler.getCurrentPlace() == 2  && furnaceHandler.getFuelCounter() < 64 && (invHandler.getCurrentItem().equals("Coal") || invHandler.getCurrentItem().equals("Stick") || invHandler.getCurrentItem().equals("Wood"))) {
                    if(furnaceHandler.getFuelQueue().isEmpty() || furnaceHandler.getFrontFQ().equals(invHandler.getCurrentItem())) {
                        furnaceHandler.addFuel(invHandler.getCurrentItem());
                        invHandler.removeCurrentItem();
                    }
                }
            }
        }
        if(key == KeyEvent.VK_ESCAPE) {
            if(!escape) {
                escape = true;
                player.setMoveBlocked(true);
                furnaceHandler.setEscape(true);
                invHandler.setEscape(true);
                invHandler.getFirstHotbar().setEscape(true);
                invHandler.getFirstIventory().setEscape(true);
            } else {
                escape = false;
                player.setMoveBlocked(false);
                furnaceHandler.setEscape(false);
                invHandler.setEscape(false);
                invHandler.getFirstHotbar().setEscape(false);
                invHandler.getFirstIventory().setEscape(false);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void draw(DrawingPanel dp, Graphics2D g2d) {
        g2d.drawImage(currentBackground, -15, 0, null);
        if (escape) {
            g2d.setColor(new Color(0, 0, 0, 215));
            g2d.draw(escapeRectangle);
            g2d.fill(escapeRectangle);
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Minecraft", Font.PLAIN, 30));
            g2d.drawString("Head coordinates:   " + (player.getPosX() / 50 + 1) + ", " + (player.getPosY() / 50 + 1), 100, 100);
            String currentBlock;
            if (player.getBlock() == null) {
                currentBlock = "None";
            } else {
                currentBlock = player.getBlock().getName();
            }
            g2d.drawString("Direction block:   " + currentBlock, 100, 160);
            String currentHItem;
            if (invHandler.getCurrentHItem().equals("null")) {
                currentHItem = "None";
            } else {
                currentHItem = "" + invHandler.getCurrentHItem();
            }
            g2d.drawString("Current hotbar item:   " + currentHItem, 100, 220);
            String currentIItem;
            if (invHandler.getCurrentItem().equals("null")) {
                currentIItem = "None";
            } else {
                currentIItem = invHandler.getCurrentItem();
            }
            g2d.drawString("Current inventory item:   " + currentIItem, 100, 280);
            g2d.drawString("Total jumps:   " + player.getJumps(), 100, 340);
            g2d.drawString("Total moves:   " + player.getMoves(), 100, 400);
            g2d.drawString("Total placed blocks:   " + player.getPlacedBlocks(), 100, 460);
            g2d.drawString("Total ripped blocks:   " + player.getRippedBlocks(), 100, 520);
        }
    }

    @Override
    public void update(double dt) {

    }

    /**
     * Vermeidet, dass eine Fehlermeldung kommt, sobald man am Rand ist.
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
     * Setzt an der gewünschten Stelle den gewünschten Block.
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

    /**
     * @return ob Inventar und Ofen offen sind.
     */
    private boolean invAndFurnaceOpened() {
        if(furnaceHandler.getGuiDisplayed() && invHandler.firstInvDisplayed()) {
            return true;
        }
        return false;
    }
}