package Model.Items.Blocks;

import Control.GameplayHandler.WorldHandler;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by fh on 18.12.16.
 */
public class Coal extends Block
{

    private boolean used = false;

    public Coal(double posX, double posY)
    {
        super(posX, posY, true,"Coal");
    }

    /**
     * Checks if the current coal has been used.
     * @return
     */
    public boolean isUsed()
    {
        return used;
    }

    /**
     * Sets the coal's state to used.
     * @param used
     */
    public void setUsed(boolean used)
    {
        this.used = used;
    }

    @Override
    public void keyReleased(int key) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
