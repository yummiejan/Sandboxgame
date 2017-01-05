package Model.Creatures;

import Control.GameplayHandler.InventoryHandler;
import Control.GameplayHandler.WorldHandler;
import Model.Items.Blocks.Block;
import Model.InteractableObject;
import Model.Items.Blocks.Dirt;
import View.DrawingPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by 204g04 on 09.12.2016.
 */
public class Player extends Creature implements InteractableObject {

    private WorldHandler wh;
    private InventoryHandler ih;
    private Rectangle2D.Double rectangle1;
    private int posX, posY;
    private int direction = 0;
    private boolean up = false;
    private BufferedImage playerStanding,playerWalking;

    public Player(int posX, int posY, WorldHandler wh, InventoryHandler ih) {
        this.posX = posX;
        this.posY = posY;
        rectangle1 = new Rectangle2D.Double(posX+20,posY,10,100);
        this.wh = wh;
        this.ih = ih;

        playerStanding = null;
        try {
            playerStanding = ImageIO.read(new File("images/standing.png"));
        } catch (IOException e) {
        }

        playerWalking = null;
        try {
            playerWalking = ImageIO.read(new File("images/right.png"));
        } catch (IOException e) {
        }
    }

    @Override
    public void keyPressed(int key) {

    }

    @Override
    public void keyReleased(int key) {
        /*
        * Beim Tastendruck w,a,s,d wird überprüft in welche Richtung der Player schaut
        *
        */
        if (key == KeyEvent.VK_A) {
            direction = 1;
            if (!isBlock(1)&&!isBlock(5))
            posX = posX - 50;
        } else if (key == KeyEvent.VK_D) {
            direction = 0;
            if (!isBlock(0)&&!isBlock(4))
            posX = posX + 50;
        }
        if (key == KeyEvent.VK_W) {
            direction = 2;
            if (!isBlock(2))
                if (isBlock(3))
                posY = posY - 80;
        }else if(key == KeyEvent.VK_S){
            direction = 3;
        }
        if (key==KeyEvent.VK_Q){
            destroy();
            //System.out.println(destroy().getContent());
            //ih.addNewItem(destroy().getContent());
        }
        if (key==KeyEvent.VK_R){
            place(new Dirt((posX/50)+1,posY/50+1));//,wh));
        }
        if (key==KeyEvent.VK_SHIFT){
            if (up){
                up = false;
            }else{
                up = true;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void draw(DrawingPanel dp, Graphics2D g2d) {
        g2d.drawImage(playerStanding,posX,posY,null);
    }

    @Override
    public void update(double dt) {
        if (!isBlock(3)){
            posY = posY + 5;

        }
    }

    /**
     * Checks if there is a block next to the player.
     * @param richtung direction the player is facing
     * @return
     */
    public boolean isBlock(int richtung){
        Block b;
        if (richtung == 0){
             b = wh.getAllBlocks(posX/50+1, posY/50+1);
        }else if (richtung == 1){
             b = wh.getAllBlocks(posX/50-1, posY/50+1);
        }else if (richtung == 2){
             b = wh.getAllBlocks(posX/50, posY/50-1);
        }else if (richtung == 3){
             b = wh.getAllBlocks(posX/50, posY/50+2);
        }else if (richtung == 4){
            b = wh.getAllBlocks(posX/50+1, posY/50);
        }else if (richtung == 5){
            b = wh.getAllBlocks(posX/50-1, posY/50);
        }else {
            return false;
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
     * Destroys the block in the direction you're facing.
     * @return the block to be destroyed
     */
    public Block destroy(){
        Block b = null;
        if(direction==0){
                if (up)
                {
                    if(isBlock(4)) {
                        b = wh.getAllBlocks((posX / 50) + 1, posY / 50);
                        wh.getAllBlocks((posX / 50) + 1, posY / 50).setDisplayed(false);
                        wh.setAllBlocks((posX / 50) + 1, posY / 50, null);
                        //ih.addNewItem("Dirt");
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
        return  b;

    }

    /**
     * Places a block in the direction you're facing
     * @param b block to be placed
     */
    public void place(Block b){
        if(direction==0){
            wh.setAllBlocks((posX/50)+1,posY/50+1,b);
            wh.getFrame().getActiveDrawingPanel().addObject(wh.getAllBlocks((posX)+1,posY/50+1));
        }
    }
}
