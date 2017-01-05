package Model.Gameplay.Inventory;

import Model.DataStructures.Stack;
import Model.InteractableObject;
import Model.Items.Blocks.Dirt;
import Model.Items.Item;
import View.DrawingPanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by Felix on 12.12.2016.
 */
public class Inventory implements InteractableObject {

    private Rectangle2D.Double backRectangle;
    private Rectangle2D.Double rectangle;
    private Rectangle2D.Double rectangle2;
    private Rectangle2D.Double itemRectangle;
    private Rectangle2D.Double chooseRectangle;
    private Rectangle2D.Double frectangle;
    private Arc2D.Double fsemicircle;
    private Rectangle2D.Double frectangle2;
    private Stack itemPlace[][];
    private Stack armorPlace[];
    private Stack<String> itemStack;
    private double posX, posY, chosenX, chosenY;
    private boolean displayed;

    public Inventory(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
        chosenX = 0;
        chosenY = 0;
        backRectangle = new Rectangle2D.Double(posX, posY, 10, 10);
        rectangle = new Rectangle2D.Double(posX, posY, 10, 10);
        rectangle2 = new Rectangle2D.Double(posX, posY, 10, 10);
        itemRectangle = new Rectangle2D.Double(posX+2.5, posY+2.5, 5, 5);
        chooseRectangle = new Rectangle2D.Double(chosenX, chosenY, 10, 10);

        itemPlace = new Stack[10][4];
        armorPlace = new Stack[4];
        itemStack = new Stack<String>();
        addNewStack();
    }

    @Override
    public void keyPressed(int key) {
        /**
         * Steuerung des roten Rechtecks innerhalb des Inventars
         */
        if(displayed == true){
            if (key == KeyEvent.VK_RIGHT && chosenX < (itemPlace.length-1) * 35) {
                chosenX = chosenX + 35;
            }else if (key == KeyEvent.VK_LEFT && chosenX > 0) {
                chosenX = chosenX - 35;
            }else if (key == KeyEvent.VK_UP && chosenY > 0) {
                chosenY = chosenY - 35;
            }else if (key == KeyEvent.VK_DOWN && chosenY < (itemPlace[0].length-1) * 35) {
                chosenY = chosenY + 35;
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
        if(displayed == true) {
            g2d.setColor(new Color(79, 79, 79, 100));
            g2d.fill(backRectangle);
            backRectangle.setFrame(posX, posY, 35 * itemPlace.length + 55, 35 * itemPlace[0].length + 1);
            for (int i = 0; i < itemPlace.length; i++) {
                for (int j = 0; j < itemPlace[i].length; j++) {
                    g2d.setColor(new Color(0, 0, 0, 100));
                    g2d.draw(rectangle);
                    rectangle.setFrame(posX + i * 35, posY + j * 35, 35, 35);
                }
            }
            for (int i = 0; i < armorPlace.length; i++) {
                g2d.setColor(new Color(0, 0, 0, 100));
                g2d.draw(rectangle2);
                rectangle2.setFrame(posX + 35 * itemPlace.length + 20, posY + i * 35, 35, 35);

            }
            g2d.setColor(new Color(250, 0, 0));
            g2d.draw(chooseRectangle);
            chooseRectangle.setFrame(chosenX, chosenY, 35, 35);

            for (int i = 0; i < itemPlace.length; i++) {
                for (int j = 0; j < itemPlace[i].length; j++) {
                    if (itemPlace[i][j].top() == "Coal") {
                        g2d.setColor(new Color(75, 25, 0));
                        g2d.fill(itemRectangle);
                        itemRectangle.setFrame(posX + i * 35 + 8.75, posY + j * 35 + 8.75, 17.5, 17.5);
                        g2d.setColor(new Color(0, 0, 0));
                        if(itemPlace[i][j].getSize() < 9) {
                            g2d.drawString("" + itemPlace[i][j].getSize(), (int) posX + i * 35 + 28, (int) posY + j * 35 + 32);
                        }else{
                            g2d.drawString("" + itemPlace[i][j].getSize(), (int) posX + i * 35 + 23, (int) posY + j * 35 + 32);
                        }
                    }
                    if (itemPlace[i][j].top() == "Dirt") {
                        g2d.setColor(new Color(51, 51, 51));
                        g2d.fill(itemRectangle);
                        itemRectangle.setFrame(posX + i * 35 + 8.75, posY + j * 35 + 8.75, 17.5, 17.5);
                        g2d.setColor(new Color(0, 0, 0));
                        if(itemPlace[i][j].getSize() < 9) {
                            g2d.drawString("" + itemPlace[i][j].getSize(), (int) posX + i * 35 + 28, (int) posY + j * 35 + 32);
                        }else{
                            g2d.drawString("" + itemPlace[i][j].getSize(), (int) posX + i * 35 + 23, (int) posY + j * 35 + 32);
                        }
                    }
                    if (itemPlace[i][j].top() == "Furnace") {

                    }

                }
            }

        }
    }

    @Override
    public void update(double dt) {

    }


    public void addNewStack(){
        for (int i = 0; i < itemPlace.length ; i++) {
            for (int j = 0; j < itemPlace[i].length; j++) {
                itemPlace[i][j] = new Stack<String>();
            }
        }
    }

    public void setDisplayed(boolean displayed) {
        this.displayed = displayed;
    }

    public boolean isDisplayed() {
        return displayed;
    }



    public Stack getItemPlacePlace(double a, double b) {
        return itemPlace[(int)a][(int)b];
    }

    public int getItemPlaceLength(int a) {
        return itemPlace[a].length;
    }

    public Stack[][] getItemPlace() {
        return itemPlace;
    }

    public void setItemPlace(Stack item, int a, int b) {
        itemPlace[a][b] = item;
    }

    public Stack getArmorPlace(int a) {
        return armorPlace[a];
    }

    public void setArmorPlace(Stack armor, int a) {
        armorPlace[a] = armor;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public double getChosenX() {
        return chosenX;
    }

    public double getChosenY() {
        return chosenY;
    }
}
