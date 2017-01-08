package Model.Creatures;

import Control.GameplayHandler.InventoryHandler;
import Control.GameplayHandler.WorldHandler;
import Model.Gameplay.Inventory.Hotbar;
import Model.Items.Blocks.Block;
import Model.InteractableObject;
import Model.Items.Blocks.Dirt;
import View.DrawingPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;

/**
 * Created by Fyn on 09.12.2016.
 */
public class Player extends Creature implements InteractableObject {

    private WorldHandler wh;
    private InventoryHandler ih;
    private Hotbar hb;

    private int posX, posY, wantedX;
    private double velY,gravity;
    private boolean onGround;

    private int direction = 0;
    private boolean up = false;

    private BufferedImage playerStanding,playerRight,playerLeft;
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
        switch (key) {
            case KeyEvent.VK_W:
                if (!isBlock(2)) {
                    startJump();
                }
                break;
            case KeyEvent.VK_A:
                currentImage = playerLeft;
                //posX = posX - 50;
                break;
            case KeyEvent.VK_D:
                currentImage = playerRight;
                //posX = posX + 50;
                break;
        }
    }

    @Override
    public void keyReleased(int key) {
        switch (key) {
            case KeyEvent.VK_W:
                endJump();
                direction = 2;
                break;
            case KeyEvent.VK_A:
                if (posX > 0) {
                    currentImage = playerStanding;
                    direction = 1;
                    if (!isBlock(1) && !isBlock(5)) {
                        posX -= 50;
                    }
                }
                break;
            case KeyEvent.VK_S:
                direction = 3;
                break;
            case KeyEvent.VK_D:
                if (posX < wh.getAllBlocks(22, 12).getPosX()) {
                    currentImage = playerStanding;
                    direction = 0;
                    if (!isBlock(0) && !isBlock(4)) {
                        posX += 50;
                    }
                }
                break;

            case KeyEvent.VK_Q:
                destroy();
                break;
            case KeyEvent.VK_R:
                System.out.println(hb.getPlace(hb.getChosenX()).top());
                place(new Dirt((posX / 50) + 1, posY / 50 + 1));//,wh));
                break;
            case KeyEvent.VK_SHIFT:
                if (up) {
                    up = false;
                } else {
                    up = true;
                }
                break;
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
    }

    public boolean isBlock(int richtung){
        Block b = null;
        switch(richtung) {
            case 0:
                b = wh.getAllBlocks(posX / 50 + 1, posY / 50 + 1);
                break;
            case 1:
                b = wh.getAllBlocks(posX / 50 - 1, posY / 50 + 1);
                break;
            case 2:
                b = wh.getAllBlocks(posX / 50, posY / 50 - 1);
                break;
            case 3:
                b = wh.getAllBlocks(posX / 50, posY / 50 + 2);
                break;
            case 4:
                b = wh.getAllBlocks(posX / 50 + 1, posY / 50);
                break;
            case 5:
                b = wh.getAllBlocks(posX / 50 - 1, posY / 50);
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



    public Block destroy(){
        Block b = null;
        if(direction==0){
            if (up)
            {
                if(isBlock(4)) {
                    b = wh.getAllBlocks((posX / 50) + 1, posY / 50);
                    wh.getAllBlocks((posX / 50) + 1, posY / 50).setDisplayed(false);
                    wh.setAllBlocks((posX / 50) + 1, posY / 50, null);
                }

            } else
            {
                if (isBlock(0)) {
                    b = wh.getAllBlocks((posX / 50) + 1, posY / 50 + 1);
                    wh.getAllBlocks((posX / 50) + 1, posY / 50 + 1).setDisplayed(false);
                    wh.setAllBlocks((posX / 50) + 1, posY / 50 + 1, null);
                }


            }
        }else if(direction==1){
            if (up)
            {
                if (isBlock(5)) {
                    b = wh.getAllBlocks((posX / 50) - 1, posY / 50);
                    wh.getAllBlocks((posX / 50) - 1, posY / 50).setDisplayed(false);
                    wh.setAllBlocks((posX / 50) - 1, posY / 50, null);
                }
            }else{
                if (isBlock(1)) {
                    b = wh.getAllBlocks((posX / 50) - 1, posY / 50 + 1);
                    wh.getAllBlocks((posX / 50) - 1, posY / 50 + 1).setDisplayed(false);
                    wh.setAllBlocks((posX / 50) - 1, posY / 50 + 1, null);
                }
            }
        }else if(direction==3){
            if (isBlock(3)&&(wh.xBlockLevel(posX/50)<12))
            {
                b = wh.getAllBlocks((posX / 50), posY / 50 + 2);
                wh.getAllBlocks((posX / 50), posY / 50 + 2).setDisplayed(false);
                wh.setAllBlocks((posX / 50), posY / 50 + 2, null);
            }
        }else if(direction==2){
            if (isBlock(2)&&(wh.xBlockLevel(posX/50)<12)) {
                b = wh.getAllBlocks((posX / 50), posY / 50 - 1);
                wh.getAllBlocks((posX / 50), posY / 50 - 1).setDisplayed(false);
                wh.setAllBlocks((posX / 50), posY / 50 - 1, null);
            }
        }
        /**
         * der abgebaute Block-Content wird dem Inventar hinzugefügt (fonktioniert noch nicht, weil ich null zuruck bekomme, aber auch wenn ich nur "Dirt" eingebe)
         */
        System.out.println(b.getName());
        //ih.addNewItem(b.getName());
        return  b;

    }

    /**
     * Places a block.
     * @param b given block to be places
     */
    public void place(Block b){
        if(direction==0){
            wh.setAllBlocks((posX/50)+1,posY/50+1,b);
            wh.getFrame().getActiveDrawingPanel().addObject(wh.getAllBlocks((posX)+1,posY/50+1));
            hb.getPlace(hb.getChosenX()/50).pop();
        }

    }

    /**
     * Starts the Jump
     */
    public void startJump(){
        if(onGround){
            velY=-200.0;
            onGround = false;
        }
    }

    /**
     * Ends the Jump once a certain velocity has been reached.
     */
    public void endJump(){
        if(velY < -100.0)
            velY = -100.0;
    }

}
