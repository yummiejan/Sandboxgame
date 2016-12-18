package Model;

import Control.WorldHandler;
import View.DrawingPanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

/**
 * Created by 204g04 on 09.12.2016.
 */
public class Player extends Creature implements InteractableObject {

    private Rectangle2D.Double rectangle;
    private int posX, posY;
    private WorldHandler wh;

    public Player(int posX, int posY,WorldHandler wh) {
        this.posX = posX;
        this.posY = posY;
        this.wh = wh;
        rectangle = new Rectangle2D.Double(posX,posY,50,100);
    }

    @Override
    public void keyPressed(int key) {

    }

    @Override
    public void keyReleased(int key) {

        if(key ==KeyEvent.VK_A){
            posX = posX - 50;
        }else if(key ==KeyEvent.VK_D){
            if(wh.isBlock())
            {
                posX = posX + 50;
            }
        }
        if(key ==KeyEvent.VK_SPACE){
            posY = posY - 50;
        }else if(key ==KeyEvent.VK_S){
            posY = posY + 50;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void draw(DrawingPanel dp, Graphics2D g2d) {
        g2d.setColor(new Color(194, 148, 24));
        g2d.fill(rectangle);
        g2d.setColor(new Color(0,0,0));
        g2d.draw(rectangle);
        rectangle.setFrame(posX,posY,50,100);
    }

    @Override
    public void update(double dt) {

    }



    public int getPosX()
    {
        return posX;
    }

    public void setPosX(int posX)
    {
        this.posX = posX;
    }

    public int getPosY()
    {
        return posY;
    }

    public void setPosY(int posY)
    {
        this.posY = posY;
    }
}
