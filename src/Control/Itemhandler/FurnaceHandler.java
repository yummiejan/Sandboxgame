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
    private String currentObject;
    private int objectCounter, fuelCounter, productCounter;
    private Furnace furnace;
    private boolean guiDisplayed;
    private Image furnaceGui;
    private int currentPlace = 1;
    private Rectangle2D.Double currentRectangle;
    private Rectangle2D.Double loadingBalken;
    private int x = 699; //49, 49+52+117, 49+52+117+52+117
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
            try {
                furnaceGui = ImageIO.read(new File("images/Furnace_GUI.png"));
            } catch (IOException e) {
            }

            g2d.drawImage(furnaceGui,650,0,null);
            g2d.setColor(Color.red);

            g2d.draw(currentRectangle);
            currentRectangle.setFrame(x, y, 52, 52);

            loadingBalken.setFrame(700,85,l,10);
            g2d.draw(loadingBalken);
            g2d.fill(loadingBalken);

            if (objectQueue.front() == "Dirt") {
                try {
                    image = ImageIO.read(new File("images/dirt_inv.png"));
                } catch (IOException e) {
                }
            }else if(objectQueue.front() == "Wood") {
                try {
                    image = ImageIO.read(new File("images/wood_inv.png"));
                } catch (IOException e) {
                }
            } else if(objectQueue.front() == "Stone") {
                try {
                    image = ImageIO.read(new File("images/stone_inv.png"));
                } catch (IOException e) {
                }
            }
            if(!objectQueue.isEmpty()) {
                g2d.drawImage(image, 714, 122, null);
                g2d.setColor(new Color(0, 0, 0));
                if (objectCounter < 9) {
                    g2d.drawString("" + objectCounter, 714 + 28, 122 + 32);
                } else {
                    g2d.drawString("" + objectCounter, 714 + 23, 122 + 32);
                }
            }

            if (fuelQueue.front() == "Coal") {
                try {
                    image2 = ImageIO.read(new File("images/coal_inv.png"));
                } catch (IOException e) {
                }
            }else if(fuelQueue.front() == "Wood") {
                try {
                    image2 = ImageIO.read(new File("images/wood_inv.png"));
                } catch (IOException e) {
                }
            } else if(fuelQueue.front() == "Stick") {
                try {
                    image2 = ImageIO.read(new File("images/stick_inv.png"));
                } catch (IOException e) {
                }
            }
            if(!fuelQueue.isEmpty()) {
                g2d.drawImage(image2, 883, 122, null);
                g2d.setColor(new Color(0, 0, 0));
                if (fuelCounter < 9) {
                    g2d.drawString("" + fuelCounter, 883 + 28, 122 + 32);
                } else {
                    g2d.drawString("" + fuelCounter, 883 + 23, 122 + 32);
                }
            }

            if (productStack.top() == "Dirt") {
                try {
                    image3 = ImageIO.read(new File("images/dirt_inv.png"));
                } catch (IOException e) {
                }
            }else if(productStack.top() == "Coal") {
                try {
                    image3 = ImageIO.read(new File("images/coal_inv.png"));
                } catch (IOException e) {
                }
            } else if(productStack.top() == "Stone") {
                try {
                    image3 = ImageIO.read(new File("images/stone_inv.png"));
                } catch (IOException e) {
                }
            }
            if(!productStack.isEmpty()) {
                g2d.drawImage(image3, 1052, 122, null);
                g2d.setColor(new Color(0, 0, 0));
                if (productCounter < 9) {
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
        if (!objectQueue.isEmpty() && !fuelQueue.isEmpty() && productCounter > 64) {
            if(fuelQueue.front() == "Coal") {
                while(l <= 300) {
                    l += 20 * dt;
                    recipes(dt);
                    furnace.setActivated(true);
                }
                furnace.setActivated(false);
                fuelQueue.dequeue();
                l = 0;
            } else if(fuelQueue.front() == "Wood") {
                while(l <= 300) {
                    l += 50 * dt;
                    recipes(dt);
                    furnace.setActivated(true);
                }
                furnace.setActivated(false);
                fuelQueue.dequeue();
                l = 0;
            } else if(fuelQueue.front() == "Stick") {
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
     * Adds a new Item to the furnace and checks if you can cook named Item.
     *
     * @param item Item to be added to the Queue.
     */
    public void addObject(Item item) {
        if (objectCounter < 64 && objectQueue.front() == item) {
            objectQueue.enqueue(item);
            objectCounter++;
        }
    }

    public void addFuel(Item item) {
        if (fuelCounter < 64) {
            fuelQueue.enqueue(item);
            fuelCounter++;
        }
    }

    public void addProduct(Item item){
        if (productCounter < 64) {
            productStack.push(item);
            productCounter++;
        }
    }

    public void removeObject() {
        objectQueue.dequeue();
        objectCounter--;
    }

    public void removeFuel() {
        fuelQueue.dequeue();
        fuelCounter--;
    }

    public void removeProduct(){
        productStack.pop();
        productCounter--;
    }

    public void recipes(double dt) {
        if(objectQueue.front() == "Wood") {
            while (i <= 100) {
                i += 50 * dt;
            }
            productStack.push("Coal");
            objectQueue.dequeue();
            i = 0;
        } else if(objectQueue.front() == "Stone") {
            while (i <= 100) {
                i += 50 * dt;
            }
            productStack.push("Dirt");
            objectQueue.dequeue();
            i = 0;
        } else if(objectQueue.front() == "Dirt") {
            while (i <= 100) {
                i += 50 * dt;
            }
            productStack.push("Stone");
            objectQueue.dequeue();
            i = 0;
        }
    }

    /*public void getCurrentObject() {
        if (currentPlace == 1) {

        }
    }*/

    public void setGuiDisplayed(boolean guiDisplayed) {
        this.guiDisplayed = guiDisplayed;
    }

    public boolean getGuiDisplayed() { return guiDisplayed; }

    public int getCurrentPlace() {
        return currentPlace;
    }
}