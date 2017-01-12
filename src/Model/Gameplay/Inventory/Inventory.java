package Model.Gameplay.Inventory;

import Model.DataStructures.Stack;
import Model.InteractableObject;
import View.DrawingPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.File;

/**
 * Created by Felix on 12.12.2016.
 */
public class Inventory implements InteractableObject {

    private Rectangle2D.Double backRectangle;
    private Rectangle2D.Double rectangle;
    private Rectangle2D.Double rectangle2;
    private Rectangle2D.Double chooseRectangle;
    private Rectangle2D.Double woodRectangle;
    private Line2D.Double stickLine;
    private Image image, coal, dirt, stick, pickaxe, wood, stone, woodpickaxe;
    private Stack<String> itemPlace[][];
    private Stack<String> armorPlace[];
    private double posX, posY, chosenX, chosenY;
    private boolean displayed;
    private boolean escape;

    public Inventory(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
        chosenX = 0;
        chosenY = 0;
        backRectangle = new Rectangle2D.Double(posX, posY, 10, 10);
        rectangle = new Rectangle2D.Double(posX, posY, 10, 10);
        rectangle2 = new Rectangle2D.Double(posX, posY, 10, 10);
        chooseRectangle = new Rectangle2D.Double(chosenX, chosenY, 10, 10);
        stickLine = new Line2D.Double(posX, posY, posX, posY);
        woodRectangle = new Rectangle2D.Double(posX, posY, 10, 10);
        itemPlace = new Stack[10][4];
        armorPlace = new Stack[4];
        addNewStack();
        try {
            coal = ImageIO.read(new File("images/coal_inv.png"));
            stone = ImageIO.read(new File("images/stone_inv.png"));
            wood = ImageIO.read(new File("images/wood_inv.png"));
            stick = ImageIO.read(new File("images/stick_inv.png"));
            dirt = ImageIO.read(new File("images/dirt_inv.png"));
            pickaxe = ImageIO.read(new File("images/pickaxe_inv.png"));
            woodpickaxe = ImageIO.read(new File("images/pickaxe_wood_inv.png"));
        } catch (Exception e) {

        }
    }

    @Override
    public void keyPressed(int key) {
        /**
         * Steuerung des roten Rechtecks innerhalb des Inventars mit den Pfeiltasten
         */
        if (displayed && !escape) {
            if (key == KeyEvent.VK_RIGHT && chosenX < (itemPlace.length - 1) * 35) {
                chosenX += 35;
            } else if (key == KeyEvent.VK_LEFT && chosenX > 0) {
                chosenX -= 35;
            } else if (key == KeyEvent.VK_UP && chosenY > 0) {
                chosenY -= 35;
            } else if (key == KeyEvent.VK_DOWN && chosenY < (itemPlace[0].length - 1) * 35) {
                chosenY += 35;
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
        if(displayed) {
            /**
             * Zeichnen des Inventars
             */
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

            /**
             * Rotes Rechteck zum Auswählen der Items
             */
            g2d.setColor(new Color(250, 0, 0));
            g2d.draw(chooseRectangle);
            chooseRectangle.setFrame(chosenX, chosenY, 35, 35);

            /**
             * Items werden im Inventar gezeichnet und ein Stack auf eine Größe von 64 Items beschränkt
            */
            for (int i = 0; i < itemPlace.length; i++) {
                for (int j = 0; j < itemPlace[i].length; j++) {
                    if(!itemPlace[i][j].isEmpty()) {
                        switch (itemPlace[i][j].top()) {
                            case "Coal":
                                image = coal;
                                break;
                            case "Dirt":
                                image = dirt;
                                break;
                            case "Furnace":
                                break;
                            case "Stick":
                                image = stick;
                                break;
                            case "Pickaxe":
                                image = pickaxe;
                                break;
                            case "Wood":
                                image = wood;
                                break;
                            case "Stone":
                                image = stone;
                                break;
                            case "Woodpickaxe":
                                image = woodpickaxe;
                                break;
                        }
                        g2d.drawImage(image, (int) (posX + i * 35 + 8.75), (int) (posY + j * 35 + 8.75), null);
                        g2d.setColor(Color.BLACK);
                        g2d.setFont(new Font("Minecraft", Font.PLAIN, 10));
                        if (itemPlace[i][j].getSize() <= 9) {
                            g2d.drawString("" + itemPlace[i][j].getSize(), (int) posX + i * 35 + 28, (int) posY + j * 35 + 32);
                        } else {
                            g2d.drawString("" + itemPlace[i][j].getSize(), (int) posX + i * 35 + 23, (int) posY + j * 35 + 32);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void update(double dt) {

    }

    /**
     * Den Plätzen des Inventars werden jeweils ein String-Stack zugewiesen
     */
    private void addNewStack(){
        for (int i = 0; i < itemPlace.length ; i++) {
            for (int j = 0; j < itemPlace[i].length; j++) {
                itemPlace[i][j] = new Stack<>();
            }
        }
    }

    /**
     * Legt fest, ob das Inventar angezeigt wird oder nicht.
     * @param displayed
     */
    public void setDisplayed(boolean displayed) {
        this.displayed = displayed;
    }

    /**
     * @return ob das Inventar angezeigt wird.
     */
    public boolean isDisplayed() {
        return displayed;
    }

    /**
     * @param a
     * @param b
     * @return den Stack an der gewünschten Stelle a und b
     */
    public Stack getItemPlacePlace(double a, double b) {
        return itemPlace[(int)a][(int)b];
    }

    /**
     * @param a
     * @return die y-Länge in Array-Länge
     */
    public int getItemPlaceLength(int a) {
        return itemPlace[a].length;
    }

    /**
     * @return den 2D-Stack-Array
     */
    public Stack[][] getItemPlace() {
        return itemPlace;
    }

    /**
     * @return die x-Position.
     */
    public double getPosX() {
        return posX;
    }

    /**
     * @return die y-Position.
     */
    public double getPosY() {
        return posY;
    }

    /**
     * @return die x-Position des Auswahlquadrates.
     */
    public double getChosenX() {
        return chosenX;
    }

    /**
     * @return die y-Position des Auswahlquadrates.
     */
    public double getChosenY() {
        return chosenY;
    }

    public void setEscape(boolean escape) {
        this.escape = escape;
    }
}
