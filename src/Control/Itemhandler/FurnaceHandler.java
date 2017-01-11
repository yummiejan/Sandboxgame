package Control.Itemhandler;

import Control.GameplayHandler.WorldHandler;
import Model.DataStructures.Queue;
import Model.DataStructures.Stack;
import Model.InteractableObject;
import Model.Items.Item;
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
import java.util.*;

/**
 * Created by Alex on 15.12.2016.
 */
public class FurnaceHandler implements InteractableObject {

    private Queue objectQueue, fuelQueue;
    private Stack productStack;
    private int objectCounter, fuelCounter, productCounter;
    private Furnace furnace;
    private boolean guiDisplayed;
    private Image furnaceGui;
    private int currentPlace = 1;
    private Rectangle2D.Double currentRectangle;
    private Rectangle2D.Double loadingBalken;
    private int x = 699; //49, 49+52+117, 49+52+117+52+117 verrechnet :(
    private int y = 107; //107,
    private int l, i;
    private Image image;
    private Image image2;
    private Image image3;

    public FurnaceHandler(MainFrame frame, Furnace furnace) {
        currentRectangle = new Rectangle2D.Double(x,y,52,52);
        loadingBalken = new Rectangle2D.Double(700,85,l,10);
        objectQueue = new Queue<String>();
        fuelQueue = new Queue<String>();
        productStack = new Stack<String>();
        this.furnace = furnace;
    }

    @Override
    public void draw(DrawingPanel dp, Graphics2D g2d) {
        if (guiDisplayed){

            /**
             * Bild des GUIs wird geladen und gemalt.
             */
            try {
                furnaceGui = ImageIO.read(new File("images/Furnace_GUI.png"));
            } catch (IOException e) {
            }

            g2d.drawImage(furnaceGui,650,0,null);
            g2d.setColor(Color.red);

            /**
             * Zeigt die aktuelle Auswahl an.
             */
            g2d.draw(currentRectangle);
            currentRectangle.setFrame(x, y, 52, 52);

            /**
             * Der Ladebalken, der die aktuelle Produktion wiedergibt.
             */
            loadingBalken.setFrame(700,85,l,10);
            g2d.draw(loadingBalken);
            g2d.fill(loadingBalken);

            /**
             * Das vorderste bzw. oberste Objekt wird gemalt.
             */
            if(!objectQueue.isEmpty()){
                System.out.println((objectQueue.front()+"asdf"));
                if (objectQueue.front().equals("Dirt")) {
                    try {
                        image = ImageIO.read(new File("images/dirt_inv.png"));
                    } catch (IOException e) {
                    }
                }else if(objectQueue.front().equals("Wood")) {
                    try {
                        image = ImageIO.read(new File("images/wood_inv.png"));
                    } catch (IOException e) {
                    }
                } else if(objectQueue.front().equals("Stone")) {
                    try {
                        image = ImageIO.read(new File("images/stone_inv.png"));
                    } catch (IOException e) {
                    }
                }
                g2d.drawImage(image, 714, 122, null);
                g2d.setColor(new Color(0, 0, 0));
                if (objectCounter <= 9) {
                    g2d.drawString("" + objectCounter, 714 + 28, 122 + 32);
                } else {
                    g2d.drawString("" + objectCounter, 714 + 23, 122 + 32);
                }

            }

            if(!fuelQueue.isEmpty()) {
                if (fuelQueue.front().equals("Coal")) {
                    try {
                        image2 = ImageIO.read(new File("images/coal_inv.png"));
                    } catch (IOException e) {
                    }
                }else if(fuelQueue.front().equals("Wood")) {
                    try {
                        image2 = ImageIO.read(new File("images/wood_inv.png"));
                    } catch (IOException e) {
                    }
                } else if(fuelQueue.front().equals("Stick")) {
                    try {
                        image2 = ImageIO.read(new File("images/stick_inv.png"));
                    } catch (IOException e) {
                    }
                }
                g2d.drawImage(image2, 883, 122, null);
                g2d.setColor(new Color(0, 0, 0));
                if (fuelCounter <= 9) {
                    g2d.drawString("" + fuelCounter, 883 + 28, 122 + 32);
                } else {
                    g2d.drawString("" + fuelCounter, 883 + 23, 122 + 32);
                }
            }

            if(!productStack.isEmpty()) {
                if (productStack.top().equals("Dirt")) {
                    try {
                        image3 = ImageIO.read(new File("images/dirt_inv.png"));
                    } catch (IOException e) {
                    }
                }else if(productStack.top().equals("Coal")) {
                    try {
                        image3 = ImageIO.read(new File("images/coal_inv.png"));
                    } catch (IOException e) {
                    }
                } else if(productStack.top().equals("Stone")) {
                    try {
                        image3 = ImageIO.read(new File("images/stone_inv.png"));
                    } catch (IOException e) {
                    }
                }
                g2d.drawImage(image3, 1052, 122, null);
                g2d.setColor(new Color(0, 0, 0));
                if (productCounter <= 9) {
                    g2d.drawString("" + productCounter, 1052 + 28, 122 + 32);
                } else {
                    g2d.drawString("" + productCounter, 1052 + 23, 122 + 32);
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
         * Verschiedene Fuels haben verschiedene Lebensdauer.
         */
        if (!objectQueue.isEmpty() && !fuelQueue.isEmpty() && productCounter > 64) {
            if(fuelQueue.front().equals("Coal")) {
                while(l <= 300) {
                    l += 20 * dt;
                    recipes(dt);
                    furnace.setActivated(true);
                }
                furnace.setActivated(false);
                fuelQueue.dequeue();
                l = 0;
            } else if(fuelQueue.front().equals("Wood")) {
                while(l <= 300) {
                    l += 50 * dt;
                    recipes(dt);
                    furnace.setActivated(true);
                }
                furnace.setActivated(false);
                fuelQueue.dequeue();
                l = 0;
            } else if(fuelQueue.front().equals("Stick")) {
                while(l <= 300) {
                    l += 100 * dt;
                    recipes(dt);
                    furnace.setActivated(true);
                }
                furnace.setActivated(false);
                fuelQueue.dequeue();
                l = 0;
            }
        }
    }

    @Override
    public void keyReleased(int key) {
        /**
         * Die Auswahl wird hier gesteuert.
         */
        if(guiDisplayed){
            if(key == KeyEvent.VK_8) {
                currentPlace = 1;
                x = 699;
            }
            if(key == KeyEvent.VK_9) {
                currentPlace = 2;
                x = 868;
            }
            if(key == KeyEvent.VK_0) {
                currentPlace = 3;
                x = 1037;
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
        if (objectQueue.isEmpty()) {
            objectQueue.enqueue(item);
            objectCounter++;
        } else if (objectCounter < 64 && objectQueue.front().equals(item)) {
            objectQueue.enqueue(item);
            objectCounter++;
        }
    }

    /**
     * Objekt wird dem Fuel hinten angehängt.
     * @param item Das anzuhängende Fuel.
     */
    public void addFuel(String item) {
        if (fuelQueue.isEmpty()) {
            fuelQueue.enqueue(item);
            fuelCounter++;
        } else if (fuelCounter < 64 && fuelQueue.front().equals(item)) {
            fuelQueue.enqueue(item);
            fuelCounter++;
        }
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
     * Die Backrezepte. Aus Dreck wird Stein, aus Stein wird Dreck und aus Holz wird Kohle.
     * @param dt Zeit.
     */
    private void recipes(double dt) {
        if(objectQueue.front().equals("Wood")) {
            while (i <= 100) {
                i += 50 * dt;
            }
            productStack.push("Coal");
            objectQueue.dequeue();
            i = 0;
        } else if(objectQueue.front().equals("Stone")) {
            while (i <= 100) {
                i += 50 * dt;
            }
            productStack.push("Dirt");
            objectQueue.dequeue();
            i = 0;
        } else if(objectQueue.front().equals("Dirt")) {
            while (i <= 100) {
                i += 50 * dt;
            }
            productStack.push("Stone");
            objectQueue.dequeue();
            i = 0;
        }
    }

    /**
     * Drei Getter für die
     * @return
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

    public boolean getGuiDisplayed() { return guiDisplayed; }

    public int getCurrentPlace() {
        return currentPlace;
    }
}