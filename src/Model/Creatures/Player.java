package Model.Creatures;

import Control.GameplayHandler.WorldHandler;
import Model.Items.Blocks.Block;
import Model.InteractableObject;
import View.DrawingPanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

/**
 * Created by 204g04 on 09.12.2016.
 */
public class Player extends Creature implements InteractableObject {

    private WorldHandler wh;
    private Rectangle2D.Double rectangle1;
    private int posX, posY;
    private boolean direction = true;

    public Player(int posX, int posY, WorldHandler wh) {
        this.posX = posX;
        this.posY = posY;
        rectangle1 = new Rectangle2D.Double(posX+20,posY,10,100);
        this.wh = wh;
    }

    @Override
    public void keyPressed(int key) {

    }

    @Override
    public void keyReleased(int key) {
        if (key == KeyEvent.VK_A) {
            direction = false;
            if (!isBlock(1))
            posX = posX - 50;
            System.out.println("Position: "+posX/50+", "+posY/50+"; Block: "+(isBlock(1)));
        } else if (key == KeyEvent.VK_D) {
            direction = true;
            if (!isBlock(0))
            posX = posX + 50;
            System.out.println("Position: "+posX/50+", "+posY/50+"; Block: "+(isBlock(0)));
        }
        if (key == KeyEvent.VK_W) {
            if (!isBlock(2))
                if (isBlock(3))
                posY = posY - 80;
        }
        if (key==KeyEvent.VK_Q){
            destroy();
        }
//sg
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void draw(DrawingPanel dp, Graphics2D g2d) {
        g2d.setColor(new Color(194, 148, 24));
        g2d.fill(rectangle1);
        //g2d.fill(rectangle2);
        g2d.setColor(new Color(0,0,0));
        g2d.draw(rectangle1);
        //g2d.draw(rectangle2);
        rectangle1.setFrame(posX+20,posY,10,100);
    }

    @Override
    public void update(double dt) {
        if (!isBlock(3)){
            posY = posY + 5;

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
        if(direction){
            b = wh.getAllBlocks((posX/50)+1,posY/50+1);
            wh.getAllBlocks((posX/50)+1,posY/50+1).setDisplayed(false);
            wh.setAllBlocks((posX/50)+1,posY/50+1,null);
        }else{
            b = wh.getAllBlocks((posX/50)-1,posY/50+1);
            wh.getAllBlocks((posX/50)-1,posY/50+1).setDisplayed(false);
            wh.setAllBlocks((posX/50)-1,posY/50+1,null);
        }
        return  b;

    }
}
