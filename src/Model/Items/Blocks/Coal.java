package Model.Items.Blocks;

import Control.GameplayHandler.WorldHandler;

/**
 * Created by fh on 18.12.16.
 */
public class Coal extends Block
{

    private boolean used = false;

    public Coal(double posX, double posY)
    {
        super(posX, posY, new int[] {51,51,51,0,0,0}, true);//, wh);
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
}
