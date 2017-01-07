package Model.Creatures;

import Control.GameplayHandler.InventoryHandler;
import Control.GameplayHandler.WorldHandler;
import Model.Gameplay.Inventory.Inventory;
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
import java.util.Timer;

/**
 * Created by 204g04 on 09.12.2016.
 */
public class Player extends Creature implements InteractableObject {

    private WorldHandler wh;
    private InventoryHandler ih;

    private Rectangle2D.Double rectangle1;

    private int posX, posY;
    private double velX,velY;
    private double gravity;
    private boolean onGround;
    private boolean destroyed;

    private int direction = 0;
    private boolean up = false;

    private BufferedImage playerStanding,playerRight,playerLeft;
    private Image currentImage;

    private Timer timer;

    public Player(int posX, int posY, WorldHandler wh, InventoryHandler ih) {
        this.posX = posX;
        this.posY = posY;
        rectangle1 = new Rectangle2D.Double(posX+20,posY,10,100);
        this.wh = wh;
        this.ih = ih;

        destroyed = false;
        playerStanding = null;
        playerRight = null;
        playerLeft = null;

        gravity = 200;

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
        if(key == KeyEvent.VK_A){
            currentImage = playerLeft;
        }else if(key == KeyEvent.VK_D){
            currentImage = playerRight;
        }else if(key == KeyEvent.VK_W){
            startJump();
        }
    }

    @Override
    public void keyReleased(int key) {
        /*
        * Beim Tastendruck w,a,s,d wird überprüft in welche Richtung der Player schaut
        *
        */
        if (key == KeyEvent.VK_A) {
            currentImage = playerStanding;
            direction = 1;
            if (!isBlock(1)&&!isBlock(5))
            posX = posX - 50;
        } else if (key == KeyEvent.VK_D) {
            currentImage = playerStanding;
            direction = 0;
            if (!isBlock(0)&&!isBlock(4))
            posX = posX + 50;
        }
        if (key == KeyEvent.VK_W) {
            endJump();
            direction = 2;
        }else if(key == KeyEvent.VK_S){
            direction = 3;
        }
        if (key==KeyEvent.VK_Q){
            destroyed = true;
            destroy();
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
        g2d.drawImage(currentImage,(int)posX,(int)posY,null);
    }

    @Override
    public void update(double dt) {

        posY += velY * dt;
        velY += gravity * dt;
        //System.out.println(posY);
        //System.out.println(onGround);

        if(isBlock(3)){
            velY = 0.0;
            onGround = true;
        }
    }

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
        ih.addNewItem(b.getName());
        return  b;

    }
    public void place(Block b){
        if(direction==0){
            wh.setAllBlocks((posX/50)+1,posY/50+1,b);
            wh.getFrame().getActiveDrawingPanel().addObject(wh.getAllBlocks((posX)+1,posY/50+1));
        }
        ih.removeItem(b.getName());
        System.out.println(b.getName());
    }

    public void startJump(){
        if(onGround){
            velY=-200.0;
            onGround = false;
        }
    }

    public void endJump(){
        if(velY < -100.0)
            velY = -100.0;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}
