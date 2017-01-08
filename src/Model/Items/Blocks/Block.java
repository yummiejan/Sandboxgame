package Model.Items.Blocks;

import Model.DrawableObject;
import Model.Items.Item;
import View.DrawingPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

/**
 * Created by 204g04 on 12.12.2016.
 */
public abstract class Block extends Item /*implements DrawableObject*/ {

    private Rectangle2D.Double rectangle;
    private Image image;
    private double posX, posY;
    private double height = 50, width = 50;
    private boolean solid;
    private String name;
    private boolean displayed = true;

    public Block(double posX, double posY,boolean solid, String name) {
        super(name);
        this.posX = posX;
        this.posY = posY;
        rectangle = new Rectangle2D.Double(posX, posY, height, width);
        this.solid = solid;
        this.name = name;
        if(name == "Coal") {
            try {
                image = ImageIO.read(new File("images/coal.png"));
            } catch (IOException e) {
            }
        }else if(name == "Grass") {
            try {
                image = ImageIO.read(new File("images/grass.png"));
            } catch (IOException e) {
            }
        }else if(name == "Stone") {
            try {
                image = ImageIO.read(new File("images/stone.png"));
            } catch (IOException e) {
            }
        }else if(name == "Wood") {
            try {
                image = ImageIO.read(new File("images/wood.png"));
            } catch (IOException e) {
            }
        }else if(name == "Grass") {
            try {
                image = ImageIO.read(new File("images/grass.png"));
            } catch (IOException e) {
            }
        }else if(name == "Brushes") {
            System.out.println("adawdw");
            try {
                image = ImageIO.read(new File("images/brushes.png"));
            } catch (IOException e) {
            }
        }else if(name == "Dirt") {
            try {
                image = ImageIO.read(new File("images/dirt.png"));
            } catch (IOException e) {
            }
        }
    }

    @Override
    public void draw(DrawingPanel dp, Graphics2D g2d) {
        if (isDisplayed()){
            g2d.drawImage(image,(int)posX,(int)posY,null);
        }

    }

    @Override
    public void update(double dt) {

    }

    /**
     * Checks if the current block is solid or not.
     * @return state of the block
     */
    public boolean isSolid() {
        return solid;
    }

    /**
     * Sets the block state to solid.
     * @param solid
     */
    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    /**
     * Sets the block to be shown on the interface.
     * @param displayed
     */
    public void setDisplayed(boolean displayed) {
        this.displayed = displayed;
    }

    /**
     * Checks if the current block is displayed.
     * @return
     */
    public boolean isDisplayed() {
        return displayed;
    }

    /**
     * Ist wichtig, um den abgebbauten Block zum Inventar hinzuzufügen
     * @return den Namen eines Blockes
     */
    public String getName() {
        return name;
    }

    public double getPosX() {
        return posX;
    }
}
