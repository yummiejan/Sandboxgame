package Model.Gameplay.Inventory;

import Model.DataStructures.*;
import Model.DataStructures.List;
import Model.InteractableObject;
import Model.Items.Blocks.Wood;
import Model.Items.Item;
import Model.Items.Pickaxe;
import Model.Items.Stick;
import View.DrawingPanel;

import javax.imageio.ImageIO;
import java.awt.*;

import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

/**
 * Created by Felix on 07.01.2017.
 */
public class CraftingTableInventory implements InteractableObject{

    private Rectangle2D.Double backRectangle;
    private Rectangle2D.Double itemRectangle;
    private Rectangle2D.Double woodRectangle;
    private Line2D.Double stickLine;


    private List craftList;
    private Stack craftingPlace[][];
    private Image image;

    private double posX, posY;
    private boolean displayed;

    public CraftingTableInventory(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;

        craftList = new List<String>();
        craftingPlace = new Stack[3][3];

        backRectangle = new Rectangle2D.Double(posX,posY,craftingPlace.length,craftingPlace[0].length);
        itemRectangle = new Rectangle2D.Double(posX,posY,10,10);
        woodRectangle = new Rectangle2D.Double(posX,posY,10,10);
        stickLine = new Line2D.Double();

        addNewStack();
        createList();

        craftList.toFirst();
        //System.out.println(craftList);
        //System.out.println(craftList.getContent());
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
        if(displayed) {
            g2d.setColor(new Color(79, 79, 79, 100));
            g2d.fill(backRectangle);
            backRectangle.setFrame(posX, posY, 35 * (craftingPlace.length+1) + 10, 35 * craftingPlace[0].length + 1);
            for (int i = 0; i < craftingPlace.length; i++) {
                for (int j = 0; j < craftingPlace[i].length; j++) {
                    g2d.setColor(new Color(0, 0, 0, 100));
                    g2d.draw(itemRectangle);
                    itemRectangle.setFrame(posX + i * 35, posY + j * 35, 35, 35);
                }
            }
            for (int i = 0; i < craftList.getSize(); i++) {
                g2d.setColor(new Color(0, 0, 0, 100));
                g2d.draw(itemRectangle);
                itemRectangle.setFrame((posX + craftingPlace.length* 35) +10, posY + i * 35, 35, 35);
            }
            for (int i = 0; i < craftingPlace.length; i++) {
                for (int j = 0; j < craftingPlace[i].length; j++) {
                    if (craftingPlace[i][j].top() == "Stick") {
                        try {
                            image = ImageIO.read(new File("images/stick_inv.png"));
                        } catch (IOException e) {
                        }
                        g2d.drawImage(image,(int)(posX + i * 35 + 8.75), (int)(posY + j * 35 + 8.75),null);

                    } else if(craftingPlace[i][j].top() == "Stone") {
                        try {
                            image = ImageIO.read(new File("images/stone_inv.png"));
                        } catch (IOException e) {
                        }
                        g2d.drawImage(image,(int)(posX + i * 35 + 8.75), (int)(posY + j * 35 + 8.75),null);
                    }else if(craftingPlace[i][j].top() == "Wood") {
                        try {
                            image = ImageIO.read(new File("images/wood_inv.png"));
                        } catch (IOException e) {
                        }
                        g2d.drawImage(image,(int)(posX + i * 35 + 8.75), (int)(posY + j * 35 + 8.75),null);
                    }
                }
            }

            craftList.toFirst();
            for (int i = 0; i < craftList.getSize(); i++) {
                if(craftList.getContent() == "Stick"){
                    try {
                        image = ImageIO.read(new File("images/stick_inv.png"));
                    } catch (IOException e) {
                    }
                    g2d.drawImage(image,(int)((posX + craftingPlace.length *35)+18.75), (int)(posY + i *35 + 8.75),null);
                }
                if(craftList.getContent() == "Pickaxe"){
                    try {
                        image = ImageIO.read(new File("images/stick_inv.png"));
                    } catch (IOException e) {
                    }
                    g2d.drawImage(image,(int)((posX + craftingPlace.length *35)+18.75), (int)(posY + i *35 + 8.75),null);
                }
                if(craftList.getContent() == "Wood"){
                    try {
                        image = ImageIO.read(new File("images/wood_inv.png"));
                    } catch (IOException e) {
                    }
                    g2d.drawImage(image,(int)((posX + craftingPlace.length *35)+18.75), (int)(posY + i *35 + 8.75),null);
                }
                craftList.next();
            }
        }
    }

    @Override
    public void update(double dt) {
        //System.out.println(crafted());
    }

    public void createList(){
        craftList.append("Pickaxe");
        craftList.append("Stick");
        craftList.append("Wood");
    }

    public void addNewStack(){
        for (int i = 0; i < craftingPlace.length ; i++) {
            for (int j = 0; j < craftingPlace[i].length; j++) {
                craftingPlace[i][j] = new Stack<String>();
            }
        }
    }

    public boolean isDisplayed() {
        return displayed;
    }

    public void setDisplayed(boolean displayed) {
        this.displayed = displayed;
    }

    public double CTIgetPosY() {
        return posY;
    }

    public double CTIgetPosX() {
        return posX;
    }

    public int getCraftingPlaceLength() {
        return craftingPlace.length;
    }

    public int aGetCraftingPlaceLength(int a) {
        return craftingPlace[a].length;
    }

    public Stack getCraftingPlace(int a, int b) {
        return craftingPlace[a][b];
    }


    public String crafted(){
        if(isDisplayed()) {
            if (getCraftingPlace(0, 0).top() == "Stick" && getCraftingPlace(1, 0).top() == "Stick" && getCraftingPlace(0, 1).top() == "Stick" && getCraftingPlace(1, 1).top() == "Stick") {
                return "Wood";
            }
            if (getCraftingPlace(0, 0).top() == "Stone" && getCraftingPlace(1, 0).top() == "Stone" && getCraftingPlace(2, 0).top() == "Stone" && getCraftingPlace(1, 1).top() == "Stick" && getCraftingPlace(1, 2).top() == "Stick") {
                return "Pickaxe";
            }
            if (getCraftingPlace(1, 1).top() == "Wood" && getCraftingPlace(1, 2).top() == "Wood") {
                return "Stick";
            }
        }



        return "Nichts";
    }
}
