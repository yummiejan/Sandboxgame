package Model.Items.Blocks;

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
    private Image image,coal, grass,stone,wood,brushes,dirt;
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

        try {
            coal = ImageIO.read(new File("images/coal.png"));
            stone = ImageIO.read(new File("images/stone.png"));
            wood = ImageIO.read(new File("images/wood.png"));
            grass = ImageIO.read(new File("images/grass.png"));
            brushes = ImageIO.read(new File("images/brushes.png"));
            dirt = ImageIO.read(new File("images/dirt.png"));
        } catch (IOException e) {
        }

        if(name == "Coal") {
            image = coal;
        }else if(name == "Stone") {
            image = stone;
        }else if(name == "Wood") {
            image = wood;
        }else if(name == "Grass") {
            System.out.print("Es macht keinen Sinn!");
            image = grass;
        }else if(name == "Brushes") {
            image = brushes;
        }else if(name == "Dirt") {
            image = dirt;
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

    public double getPosY(){
        return posY;
    }
}
