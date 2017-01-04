package Model.Gameplay.Inventory;

import Model.DataStructures.Stack;
import Model.InteractableObject;
import Model.Items.Blocks.Dirt;
import Model.Items.Item;
import View.DrawingPanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

/**
 * Created by 204g04 on 12.12.2016.
 */
public class Inventory implements InteractableObject {

    private Rectangle2D.Double backRectangle;
    private Rectangle2D.Double rectangle;
    private Rectangle2D.Double rectangle2;
    private Rectangle2D.Double itemRectangle;
    private Stack itemPlace[][];
    private Stack armorPlace[];
    private Stack<String> itemStack;
    private double posX, posY;
    private boolean displayed;

    public Inventory(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
        backRectangle = new Rectangle2D.Double(posX, posY, 10, 10);
        rectangle = new Rectangle2D.Double(posX, posY, 10, 10);
        rectangle2 = new Rectangle2D.Double(posX, posY, 10, 10);
        itemRectangle = new Rectangle2D.Double(posX+2.5, posY+2.5, 5, 5);
        itemPlace = new Stack[10][4];
        armorPlace = new Stack[4];
        itemStack = new Stack<String>();
        addNewStack();
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
            for (int i = 0; i < itemPlace.length; i++) {
                for (int j = 0; j < itemPlace[i].length; j++) {
                    if (itemPlace[i][j].top() == "Coal") {
                        g2d.setColor(new Color(51, 51, 51));
                        g2d.fill(itemRectangle);
                        itemRectangle.setFrame(posX + i * 35 + 8.75, posY + j * 35 + 8.75, 17.5, 17.5);
                    }
                    if (itemPlace[i][j].top() == "Dirt") {
                        g2d.setColor(new Color(75, 25, 0));
                        g2d.fill(itemRectangle);
                        itemRectangle.setFrame(posX + i * 35 + 8.75, posY + j * 35 + 8.75, 17.5, 17.5);
                    }

                }
            }

        }
    }
    public void addNewStack(){
        for (int i = 0; i < itemPlace.length ; i++) {
            for (int j = 0; j < itemPlace[i].length; j++) {
                itemPlace[i][j] = new Stack<String>();
            }
        }
    }


    @Override
    public void update(double dt) {

    }

    public void setDisplayed(boolean displayed) {
        this.displayed = displayed;
    }

    public boolean isDisplayed() {
        return displayed;
    }



    public Stack getItemPlacePlace(int a, int b) {
        return itemPlace[a][b];
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
}
