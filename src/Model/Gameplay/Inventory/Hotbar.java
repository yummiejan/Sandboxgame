package Model.Gameplay.Inventory;

import Model.DataStructures.Stack;
import Model.InteractableObject;
import View.DrawingPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;

/**
 * Created by Felix on 12.12.2016.
 */
public class Hotbar implements InteractableObject {

    private Rectangle2D.Double backRectangle;
    private Rectangle2D.Double rectangle;
    private Rectangle2D.Double itemRectangle;
    private Rectangle2D.Double chooseRectangle;
    private Image image, coal, dirt, stick, pickaxe, wood, stone, woodpickaxe;
    private int posX, posY, chosenX;
    private Stack<String> place[];
    private boolean displayed = true;
    private boolean escape;

    public Hotbar(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        chosenX = 450;
        place = new Stack[5];
        rectangle = new Rectangle2D.Double(posX, posY, 250, 50);
        backRectangle = new Rectangle2D.Double(posX, posY, 250, 50);
        itemRectangle = new Rectangle2D.Double(posX + 2.5, posY + 2.5, 5, 5);
        chooseRectangle = new Rectangle2D.Double(chosenX, posY, 10, 10);
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
         * Steuerung des roten Rechtecks mit Tasten
         */
        if (displayed && !escape) {
            switch (key) {
                case KeyEvent.VK_1:
                    chosenX = 450;
                    break;
                case KeyEvent.VK_2:
                    chosenX = 485;
                    break;
                case KeyEvent.VK_3:
                    chosenX = 520;
                    break;
                case KeyEvent.VK_4:
                    chosenX = 555;
                    break;
                case KeyEvent.VK_5:
                    chosenX = 590;
                    break;
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
             * Items werden im Inventar gezeichnet und ein Stack auf eine Größe von 64 Items beschränkt
             */
            for (int i = 0; i < place.length; i++) {
                if(!place[i].isEmpty()) {
                    switch (place[i].top()) {
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
                    g2d.drawImage(image, (int)(posX - 125 + i * 35 + 8.75), (int)(posY + 8.75), null);
                    g2d.setColor(Color.BLACK);
                    g2d.setFont(new Font("Minecraft", Font.PLAIN, 10));
                    if(place[i].getSize() <= 9) {
                        g2d.drawString("" + place[i].getSize(), posX - 125 + i * 35 + 28, posY + 32);
                    }else{
                        g2d.drawString("" + place[i].getSize(), posX - 125 + i * 35 + 23, posY + 32);
                    }
                }
            }

            /**
             * Zeichnen der Hotbar
             */
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

    /**
     * den Plätzen werden String-Stacks hinzugefügt
     */
    private void addNewStack(){
        for (int i = 0; i < place.length; i++) {
            place[i] = new Stack<>();
        }
    }

    /**
     * @return ob die Hotbar angezeigt wird.
     */
    public boolean isDisplayed() {
        return displayed;
    }

    /**
     * @param a
     * @return den Stack an der Stelle a
     */
    public Stack getPlace(int a) {
        return place[a];
    }

    /**
     * @return die y-Position des Auswahlquadrates.
     */
    public int getChosenX() {
        return chosenX;
    }

    public void setEscape(boolean escape) {
        this.escape = escape;
    }
}
