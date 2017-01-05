package Model.Gameplay.Inventory;

import Model.DataStructures.Stack;
import Model.InteractableObject;
import View.DrawingPanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

/**
 * Created by Felix on 12.12.2016.
 */
public class Hotbar implements InteractableObject {

    private Rectangle2D.Double backRectangle;
    private Rectangle2D.Double rectangle;
    private Rectangle2D.Double itemRectangle;
    private Rectangle2D.Double chooseRectangle;
    private Inventory firstInventory;
    private int posX, posY, chosenX;
    private Stack place[];
    private boolean displayed;

    public Hotbar(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        chosenX = 450;
        place = new Stack[5];
        rectangle = new Rectangle2D.Double(posX,posY,250,50);
        backRectangle = new Rectangle2D.Double(posX,posY,250,50);
        itemRectangle = new Rectangle2D.Double(posX+2.5, posY+2.5, 5, 5);
        chooseRectangle = new Rectangle2D.Double(chosenX, posY, 10, 10);
        addNewStack();
    }

    @Override
    public void keyPressed(int key) {
        if(displayed == true){
            if (key == KeyEvent.VK_1) {
                chosenX = 450;
            }else if (key == KeyEvent.VK_2) {
                chosenX = 485;
            }else if (key == KeyEvent.VK_3) {
                chosenX = 520;
            }else if (key == KeyEvent.VK_4) {
                chosenX = 555;
            }else if (key == KeyEvent.VK_5) {
                chosenX = 590;
            }
        }
    }

    @Override
    public void keyReleased(int key) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void draw(DrawingPanel dp, Graphics2D g2d) {
        if(displayed = true) {
            for (int i = 0; i < place.length; i++) {
                if (place[i].top() == "Coal") {
                    g2d.setColor(new Color(51, 51, 51));
                    g2d.fill(itemRectangle);
                    itemRectangle.setFrame(posX -125 + i * 35 + 8.75, posY + 8.75, 17.5, 17.5);
                    if(place[i].getSize() < 9) {
                        g2d.drawString("" + place[i].getSize(), (int) (posX - 125) + i * 35 + 28, (int) posY + 32);
                    }else{
                        g2d.drawString("" + place[i].getSize(), (int) (posX - 125) + i * 35 + 23, (int) posY + 32);
                    }
                }
                if (place[i].top() == "Dirt") {
                    g2d.setColor(new Color(75, 25, 0));
                    g2d.fill(itemRectangle);
                    itemRectangle.setFrame(posX -125 + i * 35 + 8.75, posY + 8.75, 17.5, 17.5);
                    if(place[i].getSize() < 9) {
                        g2d.drawString("" + place[i].getSize(), (int) (posX -125) + i * 35 + 28, (int) posY + 32);
                    }else{
                        g2d.drawString("" + place[i].getSize(), (int) (posX -125) + i * 35 + 23, (int) posY + 32);
                    }
                }
                if (place[i].top() == "Furnace") {

                }
            }
            g2d.setColor(new Color(79, 79, 79, 100));
            g2d.fill(backRectangle);
            backRectangle.setFrame(posX - 125, 0, 35 * place.length, 35);
            for (int i = 0; i < place.length; i++) {
                g2d.setColor(new Color(0, 0, 0, 100));
                g2d.draw(rectangle);
                rectangle.setFrame(i * 35 + posX - 125, 0, 35, 35);
            }
        }
        g2d.setColor(new Color(250, 0, 0));
        g2d.draw(chooseRectangle);
        chooseRectangle.setFrame(chosenX, posY, 35, 35);
    }

    @Override
    public void update(double dt) {

    }

    public void addNewStack(){
        for (int i = 0; i < place.length; i++) {
            place[i] = new Stack<String>();
        }
    }

    public void setDisplayed(boolean displayed) {
        this.displayed = displayed;
    }

    public boolean isDisplayed() {
        return displayed;
    }

    public Stack getPlace(double a) {
        return place[(int)a];
    }

    public int getChosenX() {
        return chosenX;
    }
}
