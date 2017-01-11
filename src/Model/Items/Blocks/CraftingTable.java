package Model.Items.Blocks;

import Control.GameplayHandler.WorldHandler;
import Model.DrawableObject;
import View.DrawingPanel;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by Felix on 07.01.2017.
 */
public class CraftingTable extends Block implements DrawableObject{

    private Rectangle2D.Double rectangle;
    private Rectangle2D.Double craftField;
    private Line2D.Double line;
    private Line2D.Double line2;
    private Line2D.Double line3;
    private Line2D.Double line4;
    private Line2D.Double line5;
    private Line2D.Double line6;
    private Line2D.Double line7;
    private Line2D.Double line8;



    public CraftingTable(double posX, double posY) {
        super(posX, posY, true, "Craftingtable");
        rectangle = new Rectangle2D.Double(posX, posY, 50, 50);
        craftField = new Rectangle2D.Double(posX, posY, 50, 8);
        line = new Line2D.Double(posX+10,posY,posX+10,posY+50);
        line2 = new Line2D.Double(posX+20,posY,posX+20,posY+50);
        line3 = new Line2D.Double(posX+30,posY,posX+30,posY+50);
        line4 = new Line2D.Double(posX+40,posY,posX+40,posY+50);
        line5 = new Line2D.Double(posX,posY+10,posX+50,posY+10);
        line6 = new Line2D.Double(posX,posY+20,posX+50,posY+20);
        line7 = new Line2D.Double(posX,posY+30,posX+50,posY+30);
        line8 = new Line2D.Double(posX,posY+40,posX+50,posY+40);
    }

    @Override
    public void draw(DrawingPanel dp, Graphics2D g2d) {
        if(super.isDisplayed()){
            g2d.setColor(new Color(131, 66, 23));
            g2d.fill(rectangle);
            g2d.setColor(new Color(0,0,0));
            g2d.draw(rectangle);
            g2d.draw(line);
            g2d.draw(line2);
            g2d.draw(line3);
            g2d.draw(line4);
            g2d.draw(line5);
            g2d.draw(line6);
            g2d.draw(line7);
            g2d.draw(line8);
            g2d.setColor(new Color(90, 29, 21));
            g2d.fill(craftField);
        }
    }
}
