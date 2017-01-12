package Control.Itemhandler;

import Model.DataStructures.Queue;
import Model.DataStructures.Stack;
import Model.InteractableObject;
import Model.Items.Blocks.Furnace;
import View.DrawingPanel;
import View.MainFrame;

import java.awt.geom.Rectangle2D;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

/**
 * Created by Alex on 15.12.2016.
 */
public class FurnaceHandler implements InteractableObject {

    private MainFrame frame;
    private Queue objectQueue, fuelQueue;
    private Stack productStack;
    private int objectCounter, fuelCounter, productCounter;
    private Furnace furnace;
    private boolean guiDisplayed;
    private boolean escape;
    private Image furnaceGui;
    private int currentPlace = 1;
    private Rectangle2D.Double currentRectangle;
    private Rectangle2D.Double loadingBalken;
    private int x = 699;
    private int y = 107;
    private int l;
    private Image image, image2, image3, dirt, wood, stone, coal, stick;

    public FurnaceHandler(MainFrame frame, Furnace furnace) {
        this.frame = frame;
        currentRectangle = new Rectangle2D.Double(x,y,52,52);
        loadingBalken = new Rectangle2D.Double(700,85,l,10);
        objectQueue = new Queue<String>();
        fuelQueue = new Queue<String>();
        productStack = new Stack<String>();
        this.furnace = furnace;
        try {
            stone = ImageIO.read(new File("images/stone_inv.png"));
            coal = ImageIO.read(new File("images/coal_inv.png"));
            dirt = ImageIO.read(new File("images/dirt_inv.png"));
            stick = ImageIO.read(new File("images/stick_inv.png"));
            wood = ImageIO.read(new File("images/wood_inv.png"));
        } catch (Exception e) {
        }
    }

    @Override
    public void draw(DrawingPanel dp, Graphics2D g2d) {
        if (guiDisplayed){
            /**
             * Schriftart wird festgelegt. Original Minecraft-Font
             */
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Minecraft", Font.PLAIN, 10));
            /**
             * Bild des GUIs wird geladen und gemalt.
             */
            try {
                furnaceGui = ImageIO.read(new File("images/Furnace_GUI.png"));
            } catch (IOException e) {
            }
            g2d.drawImage(furnaceGui, 650, 0, null);
            g2d.setColor(Color.red);
            /**
             * Zeigt die aktuelle Auswahl an.
             */
            g2d.draw(currentRectangle);
            currentRectangle.setFrame(x, y, 52, 52);
            /**
             * Der Ladebalken, der die aktuelle Produktion wiedergibt.
             */
            loadingBalken.setFrame(700, 85, l, 10);
            g2d.draw(loadingBalken);
            g2d.fill(loadingBalken);
            /**
             * Das vorderste bzw. oberste Objekt wird gemalt.
             */
            if(!objectQueue.isEmpty()){
                if (objectQueue.front().equals("Dirt")) {
                    image = dirt;
                }else if(objectQueue.front().equals("Wood")) {
                    image = wood;
                } else if(objectQueue.front().equals("Stone")) {
                    image = stone;
                }
                g2d.drawImage(image, 714, 122, null);
                g2d.setColor(Color.BLACK);
                if (objectCounter <= 9) {
                    g2d.drawString("" + objectCounter, 714 + 28, 122 + 32);
                } else {
                    g2d.drawString("" + objectCounter, 714 + 23, 122 + 32);
                }
            }
            if(!fuelQueue.isEmpty()) {
                if (fuelQueue.front().equals("Coal")) {
                    image2 = coal;
                }else if(fuelQueue.front().equals("Wood")) {
                    image2 = wood;
                } else if(fuelQueue.front().equals("Stick")) {
                    image2 = stick;
                }
                g2d.drawImage(image2, 872, 122, null);
                g2d.setColor(Color.BLACK);
                if (fuelCounter <= 9) {
                    g2d.drawString("" + fuelCounter, 872 + 28, 122 + 32);
                } else {
                    g2d.drawString("" + fuelCounter, 872 + 23, 122 + 32);
                }
            }
            if(!productStack.isEmpty()) {
                if (productStack.top().equals("Dirt")) {
                    image3 = dirt;
                }else if(productStack.top().equals("Coal")) {
                    image3 = coal;
                } else if(productStack.top().equals("Stone")) {
                    image3 = stone;
                }
                g2d.drawImage(image3, 1030, 122, null);
                g2d.setColor(Color.BLACK);
                if (productCounter <= 9) {
                    g2d.drawString("" + productCounter, 1030 + 28, 122 + 32);
                } else {
                    g2d.drawString("" + productCounter, 1030 + 23, 122 + 32);
                }
            }
        }
    }

    @Override
    public void keyPressed(int key) {

    }

    @Override
    public void update(double dt) {
        /**
         * Sollte ursprünglich mit verschiedenen Lebensdauern pro Fuel arbeiten, aber die Inkompetenz hat gesiegt. (Lässt sich im Git nachschlagen.)
         */
        if (!objectQueue.isEmpty() && !fuelQueue.isEmpty() && productCounter < 64) {
            furnace.setActivated(true);
            recipes();
            removeFuel();
        } else {
            furnace.setActivated(false);
        }
    }

    @Override
    public void keyReleased(int key) {
        if(!escape) {
            /**
             * Die Auswahl wird hier gesteuert.
             */
            if (guiDisplayed) {
                if (key == KeyEvent.VK_8) {
                    currentPlace = 1;
                    x = 699;
                }
                if (key == KeyEvent.VK_9) {
                    currentPlace = 2;
                    x = 857;
                }
                if (key == KeyEvent.VK_0) {
                    currentPlace = 3;
                    x = 1015;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Objekt wird den Objekten hinten angehängt.
     * @param item Das anzuhängende Objekt.
     */
    public void addObject(String item) {
        objectQueue.enqueue(item);
        objectCounter++;
    }

    /**
     * Objekt wird dem Fuel hinten angehängt.
     * @param item Das anzuhängende Fuel.
     */
    public void addFuel(String item) {
        fuelQueue.enqueue(item);
        fuelCounter++;
    }

    /**
     * Objekt wird zu Produkten hinzugefügt.
     * @param item Das anzuhängende Produkt.
     */
    public void addProduct(String item) {
        productStack.push(item);
        productCounter++;
    }

    /**
     * Objekt der Objekte wird entfernt.
     */
    public void removeObject() {
        objectQueue.dequeue();
        objectCounter--;
    }

    /**
     * Objekt des Fuels wird enfernt.
     */
    public void removeFuel() {
        fuelQueue.dequeue();
        fuelCounter--;
    }

    /**
     * Objekt der Produkte wird entfernt.
     */
    public void removeProduct(){
        productStack.pop();
        productCounter--;
    }

    /**
     * Die Backrezepte. Aus Dreck wird Stein, aus Stein wird Dreck und aus Holz wird Kohle..
     */
    private void recipes() {
        if(objectQueue.front().equals("Wood")) {
            addProduct("Coal");
            removeObject();
        } else if(objectQueue.front().equals("Stone")) {
            addProduct("Dirt");
            removeObject();
        } else if(objectQueue.front().equals("Dirt")) {
            addProduct("Stone");
            removeObject();
        }
    }

    /**
     * Getter und Setter für Stacks bzw. Queues, die Anzeige und den aktuellen Slot
     */

    public String getFrontOQ() {
        return objectQueue.front().toString();
    }

    public String getFrontFQ() {
        return fuelQueue.front().toString();
    }

    public String getTopPS() {
        return productStack.top().toString();
    }

    public void setGuiDisplayed(boolean guiDisplayed) {
        this.guiDisplayed = guiDisplayed;
    }

    public boolean getGuiDisplayed() {
        return guiDisplayed;
    }

    public int getCurrentPlace() {
        return currentPlace;
    }

    public void setEscape(boolean escape) {
        this.escape = escape;
    }

    public Queue getObjectQueue() {
        return objectQueue;
    }

    public Queue getFuelQueue() {
        return fuelQueue;
    }

    public Stack getProductStack() {
        return productStack;
    }

    public int getObjectCounter() {
        return objectCounter;
    }

    public int getFuelCounter() {
        return fuelCounter;
    }
}