package Model.Creatures;

import Control.GameplayHandler.InventoryHandler;
import Control.GameplayHandler.WorldHandler;
import Model.Items.Blocks.*;
import Model.InteractableObject;
import View.DrawingPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Fyn on 09.12.2016.
 */
public class Player extends Creature implements InteractableObject {

    private WorldHandler wh;
    private InventoryHandler ih;
    private int posX, posY, wantedX;
    private double velY,gravity;
    private boolean onGround;
    private int direction;
    private boolean up;
    private boolean moveBlocked;
    private BufferedImage playerStanding, playerRight, playerLeft;
    private Image currentImage;

    public Player(int posX, int posY, WorldHandler wh, InventoryHandler ih) {
        this.posX = posX;
        this.posY = posY;
        this.wh = wh;
        this.ih = ih;
        gravity = 200;
        wantedX = posX;
        try {
            playerStanding = ImageIO.read(new File("images/character_front.png"));
            playerRight = ImageIO.read(new File("images/character_right.png"));
            playerLeft = ImageIO.read(new File("images/character_left.png"));
        } catch (IOException e) {
        }
        currentImage = playerStanding;
    }

    @Override
    public void keyPressed(int key) {
        if(!moveBlocked) {
            switch (key) {
                case KeyEvent.VK_W:
                    if (!isBlock(2)) {
                        startJump();
                    }
                    direction = 2;
                    break;
                case KeyEvent.VK_A:
                    currentImage = playerLeft;
                    if (!isBlock(1) && !isBlock(5)) {
                        if (isBlock(3)) {
                            wantedX -= 50;
                        } else if (!isBlock(7)) {
                            wantedX -= 50;
                        }
                    }
                    direction = 1;
                    break;
                case KeyEvent.VK_S:
                    direction = 3;
                    break;
                case KeyEvent.VK_D:
                    currentImage = playerRight;
                    if (!isBlock(0) && !isBlock(4)) {
                        if (isBlock(3)) {
                            wantedX += 50;
                        } else if (!isBlock(6)) {
                            wantedX += 50;
                        }
                    }
                    direction = 0;
                    break;
                case KeyEvent.VK_Q:
                    destroy();
                    break;
                case KeyEvent.VK_SHIFT:
                    if (up) {
                        up = false;
                    } else {
                        up = true;
                    }
                    break;
                case KeyEvent.VK_R:
                    //TODO Bauen ermöglichen und Bugs fixen
                    if(ih.getFirstHotbar().getPlace((ih.getFirstHotbar().getChosenX()/35)-12).top() == "Dirt") {
                        place(new Dirt((posX / 50) + 1, (posY / 50) + 1));
                    }else if(ih.getFirstHotbar().getPlace((ih.getFirstHotbar().getChosenX()/35)-12).top() == "Coal") {
                        place(new Coal((posX / 50) + 1, (posY / 50) + 1));
                    }else if(ih.getFirstHotbar().getPlace((ih.getFirstHotbar().getChosenX()/35)-12).top() == "Stone") {
                        place(new Stone((posX / 50) + 1, (posY / 50) + 1));
                    }else if(ih.getFirstHotbar().getPlace((ih.getFirstHotbar().getChosenX()/35)-12).top() == "Wood") {
                        place(new Wood((posX / 50) + 1, (posY / 50) + 1));
                    }
                    break;
            }
        }
    }

    @Override
    public void keyReleased(int key) {
        if(!moveBlocked) {
            switch (key) {
                case KeyEvent.VK_W:
                    endJump();
                    break;
                case KeyEvent.VK_A:
                    currentImage = playerStanding;
                    break;
                case KeyEvent.VK_S:
                    break;
                case KeyEvent.VK_D:
                    currentImage = playerStanding;
                    break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void draw(DrawingPanel dp, Graphics2D g2d) {
        g2d.drawImage(currentImage,(int)posX,(int)posY,null);
    }

    @Override
    public void update(double dt) {
        posY += velY * dt;
        velY += gravity * dt;
        if(isBlock(3)){
            velY = 0.0;
            onGround = true;
        }
        if(wantedX < posX){
            posX -= 5;
        }else if(wantedX > posX){
            posX += 5;
        }else{
            posX = wantedX;
        }
    }

    /**
     * Prüft, ob der Block in Blickrichtung solide ist.
     * @param richtung Die Richtung, in der geprüft werden soll.
     * @return Solidarität des Blockes.
     */

    public boolean isBlock(int richtung){
        Block b = null;
        switch(richtung) {
            case 0:
                b = wh.getAllBlocks(wantedX / 50 + 1, posY / 50 + 1);
                break;
            case 1:
                b = wh.getAllBlocks(wantedX / 50 - 1, posY / 50 + 1);
                break;
            case 2:
                b = wh.getAllBlocks(wantedX / 50, posY / 50 - 1);
                break;
            case 3:
                b = wh.getAllBlocks(wantedX / 50, posY / 50 + 2);
                break;
            case 4:
                b = wh.getAllBlocks(wantedX / 50 + 1, posY / 50);
                break;
            case 5:
                b = wh.getAllBlocks(wantedX / 50 - 1, posY / 50);
                break;
            case 6:
                b = wh.getAllBlocks(wantedX / 50 + 1, posY / 50 + 2);
                break;
            case 7:
                b = wh.getAllBlocks(wantedX / 50 - 1, posY / 50 + 2);
                break;
        }
        if(b == null){
            return false;
        }
        if(b.isSolid()){
            return true;
        }
        return false;
    }

    /**
     * Liefert den sich in Abhängigkeit der Blickrichtung befindenden Block zurück.
     * @return Den oben genannten Block.
     */

    public Block getBlock(){
        Block b;
        if (direction == 0 && !up){
            b = wh.getAllBlocks(posX / 50 + 1, posY / 50 + 1);
        }else if (direction == 1 && !up){
            b = wh.getAllBlocks(posX / 50 - 1, posY / 50 + 1);
        }else if (direction == 2){
            b = wh.getAllBlocks(posX / 50, posY / 50 - 1);
        }else if (direction == 3){
            b = wh.getAllBlocks(posX / 50, posY / 50 + 2);
        }else if (direction == 0){
            b = wh.getAllBlocks(posX / 50 + 1, posY / 50);
        }else if (direction == 1){
            b = wh.getAllBlocks(posX / 50 - 1, posY / 50);
        }else b = null;
        return b;
    }

    /**
     * Zerstört den Block in der Blickrichtung.
     * @return zerstörter Block
     */
    private Block destroy(){
        Block b = null;
        switch (direction){
            case 0:
                if (up){
                    if(isBlock(4)){
                        b = wh.getAllBlocks((posX / 50) + 1, posY / 50);
                        if(b.getName().equals("Stone") || b.getName().equals("Coal")){
                            if(ih.getFirstHotbar().getPlace((ih.getFirstHotbar().getChosenX()/35)-12).top() == "Pickaxe" || ih.getFirstHotbar().getPlace((ih.getFirstHotbar().getChosenX()/35)-12).top() == "Woodpickaxe"){
                                wh.getAllBlocks((posX / 50) + 1, posY / 50).setDisplayed(false);
                                wh.setAllBlocks((posX / 50) + 1, posY / 50, null);
                                ih.addNewItem(b.getName());
                            }
                        }else {
                            wh.getAllBlocks((posX / 50) + 1, posY / 50).setDisplayed(false);
                            wh.setAllBlocks((posX / 50) + 1, posY / 50, null);
                            if(b.getName().equals("Grass")) {
                                ih.addNewItem("Dirt");
                            }else{
                                ih.addNewItem(b.getName());
                            }
                        }
                    }
                }else{
                    if (isBlock(0)){
                        b = wh.getAllBlocks((posX / 50) + 1, posY / 50 + 1);
                        if(b.getName().equals("Stone") || b.getName().equals("Coal")) {
                            if (ih.getFirstHotbar().getPlace((ih.getFirstHotbar().getChosenX() / 35) - 12).top() == "Pickaxe" || ih.getFirstHotbar().getPlace((ih.getFirstHotbar().getChosenX()/35)-12).top() == "Woodpickaxe") {
                                wh.getAllBlocks((posX / 50) + 1, posY / 50 + 1).setDisplayed(false);
                                wh.setAllBlocks((posX / 50) + 1, posY / 50 + 1, null);
                                ih.addNewItem(b.getName());
                            }
                        }else{
                            wh.getAllBlocks((posX / 50) + 1, posY / 50 + 1).setDisplayed(false);
                            wh.setAllBlocks((posX / 50) + 1, posY / 50 + 1, null);
                            if(b.getName().equals("Grass")) {
                                ih.addNewItem("Dirt");
                            }else{
                                ih.addNewItem(b.getName());
                            }
                        }
                    }
                }
                break;
            case 1:
                if(up){
                    if(isBlock(5)){
                        b = wh.getAllBlocks((posX / 50) - 1, posY / 50);
                        if(b.getName().equals("Stone") || b.getName().equals("Coal")) {
                            if (ih.getFirstHotbar().getPlace((ih.getFirstHotbar().getChosenX() / 35) - 12).top() == "Pickaxe" || ih.getFirstHotbar().getPlace((ih.getFirstHotbar().getChosenX()/35)-12).top() == "Woodpickaxe") {
                                wh.getAllBlocks((posX / 50) - 1, posY / 50).setDisplayed(false);
                                wh.setAllBlocks((posX / 50) - 1, posY / 50, null);
                                ih.addNewItem(b.getName());
                            }
                        }else{
                            wh.getAllBlocks((posX / 50) - 1, posY / 50).setDisplayed(false);
                            wh.setAllBlocks((posX / 50) - 1, posY / 50, null);
                            if(b.getName().equals("Grass")) {
                                ih.addNewItem("Dirt");
                            }else{
                                ih.addNewItem(b.getName());
                            }
                        }
                    }
                }else{
                    if(isBlock(1)) {
                        b = wh.getAllBlocks((posX / 50) - 1, posY / 50 + 1);
                        if(b.getName().equals("Stone") || b.getName().equals("Coal")) {
                            if (ih.getFirstHotbar().getPlace((ih.getFirstHotbar().getChosenX() / 35) - 12).top() == "Pickaxe" || ih.getFirstHotbar().getPlace((ih.getFirstHotbar().getChosenX()/35)-12).top() == "Woodpickaxe") {
                                wh.getAllBlocks((posX / 50) - 1, posY / 50 + 1).setDisplayed(false);
                                wh.setAllBlocks((posX / 50) - 1, posY / 50 + 1, null);
                                ih.addNewItem(b.getName());
                            }
                        }else{
                            wh.getAllBlocks((posX / 50) - 1, posY / 50 + 1).setDisplayed(false);
                            wh.setAllBlocks((posX / 50) - 1, posY / 50 + 1, null);
                            if(b.getName().equals("Grass")) {
                                ih.addNewItem("Dirt");
                            }else{
                                ih.addNewItem(b.getName());
                            }
                        }
                    }
                }
                break;
            case 2:
                if(isBlock(2) && (wh.xBlockLevel(posX / 50) < 12)){
                    b = wh.getAllBlocks((posX / 50), posY / 50 - 1);
                    if(b.getName().equals("Stone") || b.getName().equals("Coal")) {
                        if (ih.getFirstHotbar().getPlace((ih.getFirstHotbar().getChosenX() / 35) - 12).top() == "Pickaxe" || ih.getFirstHotbar().getPlace((ih.getFirstHotbar().getChosenX()/35)-12).top() == "Woodpickaxe") {
                            wh.getAllBlocks((posX / 50), posY / 50 - 1).setDisplayed(false);
                            wh.setAllBlocks((posX / 50), posY / 50 - 1, null);
                            ih.addNewItem(b.getName());
                        }
                    }else{
                        wh.getAllBlocks((posX / 50), posY / 50 - 1).setDisplayed(false);
                        wh.setAllBlocks((posX / 50), posY / 50 - 1, null);
                        if(b.getName().equals("Grass")) {
                            ih.addNewItem("Dirt");
                        }else{
                            ih.addNewItem(b.getName());
                        }
                    }
                }
                break;
            case 3:
                if(isBlock(3) && (wh.xBlockLevel(posX / 50) < 12)){
                    b = wh.getAllBlocks((posX / 50), posY / 50 + 2);
                    if(b.getName().equals("Stone") || b.getName().equals("Coal")) {
                        if (ih.getFirstHotbar().getPlace((ih.getFirstHotbar().getChosenX() / 35) - 12).top() == "Pickaxe" || ih.getFirstHotbar().getPlace((ih.getFirstHotbar().getChosenX()/35)-12).top() == "Woodpickaxe") {
                            wh.getAllBlocks((posX / 50), posY / 50 + 2).setDisplayed(false);
                            wh.setAllBlocks((posX / 50), posY / 50 + 2, null);
                            ih.addNewItem(b.getName());
                        }
                    }else{
                        wh.getAllBlocks((posX / 50), posY / 50 + 2).setDisplayed(false);
                        wh.setAllBlocks((posX / 50), posY / 50 + 2, null);
                        if(b.getName().equals("Grass")) {
                            ih.addNewItem("Dirt");
                        }else{
                            ih.addNewItem(b.getName());
                        }
                    }
                }
                break;
        }
        return b;
    }

    /**
     * Plaziert einen Block
     * @param b Block der plaziert werden soll
     */
    private void place(Block b){
        if(direction == 0){
            wh.setAllBlocks(posX / 50 + 1, posY / 50 + 1, b);
            wh.getFrame().getActiveDrawingPanel().addObject(wh.getAllBlocks((posX / 50) + 1, (posY / 50) + 1));
            ih.getFirstHotbar().getPlace((ih.getFirstHotbar().getChosenX() / 35) - 12).pop();
        }
    }

    /**
     * Startet einen Sprung
     */
    private void startJump(){
        if(onGround){
            velY = -200.0;
            onGround = false;
        }
    }

    /**
     * Endet den Sprung wenn eine bestimmte Velocity erreicht wird.
     */
    private void endJump(){
        if(velY < -100.0)
            velY = -100.0;
    }

    /**
     * Wenn moveBlocked false ist, kann man sich nicht bewegen.
     * @param moveBlocked
     */
    public void setMoveBlocked(boolean moveBlocked) {
        this.moveBlocked = moveBlocked;
    }

    /**
     * @return die y-Position.
     */
    public int getPosY() {
        return posY;
    }
}
