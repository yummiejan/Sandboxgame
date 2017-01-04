package Model.Gameplay.Inventory;

import Model.DataStructures.*;
import Model.DataStructures.List;
import Model.InteractableObject;
import Model.Items.Blocks.Dirt;
import Model.Items.Blocks.InventoryDirt;
import Model.Items.Item;
import View.DrawingPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

/**
 * Created by 204g04 on 12.12.2016.
 */
public class Inventory implements InteractableObject {

    private Rectangle2D.Double backRectangle;
    private Rectangle2D.Double rectangle;
    private List<List> mainList;
    private List<Stack> itemList;
    private double posX, posY;
    private boolean displayed;

    public Inventory(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
        backRectangle = new Rectangle2D.Double(posX, posY, 10, 10);
        rectangle = new Rectangle2D.Double(posX, posY, 10, 10);
        mainList = new List<List>();
        itemList = new List<Stack>();
        createList();
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
        if (displayed == true) {
            g2d.setColor(new Color(79, 79, 79, 100));
            g2d.fill(backRectangle);
            backRectangle.setFrame(posX, posY, 35 * mainList.getSize() , 35 * 4);
            for (int i = 0; i < mainList.getSize(); i++) {
                for (int j = 0; j < itemList.getSize(); j++) {

                    g2d.setColor(new Color(0, 0, 0, 100));
                    g2d.draw(rectangle);
                    rectangle.setFrame(posX + i * 35, posY + j * 35, 35, 35);
                }
            }

        }
    }

    @Override
    public void update(double dt) {

    }

    public void createList() {
        while(mainList.getSize() < 10){
            mainList.append(itemList);
            while(itemList.getSize() < 4){
                itemList.append(new Stack<InventoryDirt>());
            }
        }
        //System.out.print(itemList.getContent().top());
    }

    public void setDisplayed(boolean displayed) {
        this.displayed = displayed;
    }

    public boolean isDisplayed() {
        return displayed;
    }


    public List<Stack> getItemList() {
        return itemList;
    }

    public List<List> getMainList() {
        return mainList;
    }
}
