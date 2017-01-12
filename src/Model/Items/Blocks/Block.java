package Model.Items.Blocks;

import Model.Items.Item;
import View.DrawingPanel;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.sound.sampled.spi.AudioFileReader;
import java.awt.*;
import java.io.*;

/**
 * Created by 204g04 on 12.12.2016.
 */
public abstract class Block extends Item {

    private Image image, coal, grass, stone, wood, brushes, dirt;
    private File sound;
    private Clip clip;
    private double posX, posY;
    private boolean solid;
    private String name;
    private boolean displayed = true;

    public Block(double posX, double posY, boolean solid, String name) {
        super(name);
        this.posX = posX;
        this.posY = posY;
        this.solid = solid;
        this.name = name;

        try {
            coal = ImageIO.read(new File("images/coal.png"));
            stone = ImageIO.read(new File("images/stone.png"));
            wood = ImageIO.read(new File("images/wood.png"));
            grass = ImageIO.read(new File("images/grass.png"));
            brushes = ImageIO.read(new File("images/brushes.png"));
            dirt = ImageIO.read(new File("images/dirt.png"));
            sound = new File("sounds/stone.wav");
        } catch (Exception e) {

        }

        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(sound);
            AudioFormat format = stream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
        } catch (Exception e) {

        }

        switch (name) {
            case "Coal":
                image = coal;
                break;
            case "Stone":
                image = stone;
                break;
            case "Wood":
                image = wood;
                break;
            case "Grass":
                image = grass;
                break;
            case "Brushes":
                image = brushes;
                break;
            case "Dirt":
                image = dirt;
                break;
        }
    }

    @Override
    public void draw(DrawingPanel dp, Graphics2D g2d) {
        if (isDisplayed()){
            g2d.drawImage(image, (int)posX, (int)posY, null);
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
     * Sets the block to be shown on the interface.
     * @param displayed
     */
    public void setDisplayed(boolean displayed) {
        this.displayed = displayed;
        try {
            clip.open(); // reserviert Ressourcen
            clip.start(); //Clip wird abgespielt
        } catch (Exception e){

        }
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

    /**
     * @return x-Position des Blocks.
     */
    public double getPosX() {
        return posX;
    }

    /**
     * @return y-Position des Blocks.
     */
    public double getPosY(){
        return posY;
    }
}
