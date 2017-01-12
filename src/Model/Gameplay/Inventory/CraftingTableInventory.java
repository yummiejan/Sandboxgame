package Model.Gameplay.Inventory;

import Model.DataStructures.*;
import Model.DataStructures.List;
import Model.InteractableObject;
import View.DrawingPanel;

import javax.imageio.ImageIO;
import java.awt.*;

import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;

/**
 * Created by Felix on 07.01.2017.
 */
public class CraftingTableInventory implements InteractableObject{

    private Rectangle2D.Double backRectangle;
    private Rectangle2D.Double itemRectangle;
    private List craftList;
    private Stack<String> craftingPlace[][];
    private Image image, image2, stick, pickaxe, wood, stone, woodpickaxe;
    private double posX, posY;
    private boolean displayed;

    public CraftingTableInventory(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
        craftList = new List<String>();
        craftingPlace = new Stack[3][3];
        backRectangle = new Rectangle2D.Double(posX, posY, craftingPlace.length ,craftingPlace[0].length);
        itemRectangle = new Rectangle2D.Double(posX, posY, 10, 10);
        addNewStack();
        createList();
        try {
            stone = ImageIO.read(new File("images/stone_inv.png"));
            wood = ImageIO.read(new File("images/wood_inv.png"));
            stick = ImageIO.read(new File("images/stick_inv.png"));
            pickaxe = ImageIO.read(new File("images/pickaxe_inv.png"));
            woodpickaxe = ImageIO.read(new File("images/pickaxe_wood_inv.png"));
        } catch (Exception e) {

        }
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
            backRectangle.setFrame(posX, posY, 35 * (craftingPlace.length + 1) + 10, 35 * craftingPlace[0].length + 1);
            for (int i = 0; i < craftingPlace.length; i++) {
                for (int j = 0; j < craftingPlace[i].length; j++) {
                    g2d.setColor(new Color(0, 0, 0, 100));
                    g2d.draw(itemRectangle);
                    itemRectangle.setFrame(posX + i * 35, posY + j * 35, 35, 35);
                }
            }
            //for (int i = 0; i < craftList.getSize(); i++) {
                g2d.draw(itemRectangle);
                itemRectangle.setFrame((posX + craftingPlace.length * 35) + 10, posY + 35, 35, 35);
                g2d.setColor(new Color(0, 0, 0));
            //}
            for (int i = 0; i < craftingPlace.length; i++) {
                for (int j = 0; j < craftingPlace[i].length; j++) {
                    if(!craftingPlace[i][j].isEmpty()) {
                        switch (craftingPlace[i][j].top()) {
                            case "Stick":
                                image = stick;
                                break;
                            case "Stone":
                                image = stone;
                                break;
                            case "Wood":
                                image = wood;
                                break;
                            case "Pickaxe":
                                image = pickaxe;
                                break;
                        }
                        g2d.drawImage(image, (int)(posX + i * 35 + 8.75), (int)(posY + j * 35 + 8.75), null);
                    }
                }
            }
            if(!crafted().equals("Nichts")) {
                switch (crafted()) {
                    case "Stick":
                        image2 = stick;
                        break;
                    case "Pickaxe":
                        image2 = pickaxe;
                        break;
                    case "Wood":
                        image2 = wood;
                        break;
                    case "Woodpickaxe":
                        image2 = woodpickaxe;
                        break;
                }
                g2d.drawImage(image2, (int)(posX + craftingPlace.length * 35 + 18.75), (int)(posY + 35 + 8.75), null);
            }
            //craftList.next();
        }
    }

    @Override
    public void update(double dt) {
        System.out.println(craftList.getContent());

    }

    /**
     * Hängt an die Liste die craftbaren Items an.
     */
    private void createList(){
        craftList.append("Pickaxe");
        craftList.append("Woodpickaxe");
        craftList.append("Stick");
        craftList.append("Wood");
    }

    /**
     * Erstellt Stacks für die Slots beim Craften.
     */
    private void addNewStack(){
        for (int i = 0; i < craftingPlace.length ; i++) {
            for (int j = 0; j < craftingPlace[i].length; j++) {
                craftingPlace[i][j] = new Stack<>();
            }
        }
    }

    /**
     * @return ob die Hotbar angezeigt wird.
     */
    public boolean isDisplayed() {
        return displayed;
    }

    /**
     * Legt fest, ob die Hotbar angezeigt wird oder nicht.
     * @param displayed
     */
    public void setDisplayed(boolean displayed) {
        this.displayed = displayed;
    }

    /**
     * @return die Position des Craftingtableinventorys.
     */
    public double CTIgetPosX() {
        return posX;
    }

    /**
     * @return die Länge des Craftingplaces.
     */
    public int getCraftingPlaceLength() {
        return craftingPlace.length;
    }

    /**
     * @param a
     * @return die Länge des Craftingplaces a.
     */
    public int aGetCraftingPlaceLength(int a) {
        return craftingPlace[a].length;
    }

    /**
     * @param a
     * @param b
     * @return den Stack an den Koordinaten a und b.
     */
    public Stack getCraftingPlace(int a, int b) {
        return craftingPlace[a][b];
    }

    /**
     * @return Die Liste der craftbaren Items.
     */
    public List getCraftList() {
        return craftList;
    }

    /**
     * Die Craftingrezepte.
     * @return das gecraftete Objekt.
     */
    public String crafted(){
        if(displayed) {
           if (getCraftingPlace(0, 0).top() == "Stick" && getCraftingPlace(1, 0).top() == "Stick" && getCraftingPlace(0, 1).top() == "Stick" && getCraftingPlace(1, 1).top() == "Stick") {
                craftList.toFirst();
                while(craftList.getContent() != "Wood"){
                    craftList.next();
                }
                return craftList.getContent().toString();
            }else if (getCraftingPlace(0, 0).top() == "Stone" && getCraftingPlace(1, 0).top() == "Stone" && getCraftingPlace(2, 0).top() == "Stone" && getCraftingPlace(1, 1).top() == "Stick" && getCraftingPlace(1, 2).top() == "Stick") {
                craftList.toFirst();
                while(craftList.getContent() != "Pickaxe"){
                    craftList.next();
                }
                return craftList.getContent().toString();
           }else if (getCraftingPlace(1, 1).top() == "Wood" && getCraftingPlace(1, 2).top() == "Wood") {
                craftList.toFirst();
                while(craftList.getContent() != "Stick"){
                    craftList.next();
                }
                return craftList.getContent().toString();
           }else if (getCraftingPlace(0, 0).top() == "Wood" && getCraftingPlace(1, 0).top() == "Wood" && getCraftingPlace(2, 0).top() == "Wood" && getCraftingPlace(1, 1).top() == "Stick" && getCraftingPlace(1, 2).top() == "Stick") {
                craftList.toFirst();
                while(craftList.getContent() != "Woodpickaxe"){
                    craftList.next();
                }
                return craftList.getContent().toString();
            }
        }
        return "Nichts";
    }
}