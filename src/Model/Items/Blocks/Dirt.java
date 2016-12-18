package Model.Items.Blocks;

import Control.GameplayHandler.WorldHandler;

/**
 * Created by 204g04 on 12.12.2016.
 */
public class Dirt extends Block {


    public Dirt(double posX, double posY, WorldHandler wh) {
        super(posX, posY, new int[] {(int)(Math.random() * 15) + 100, (int)(Math.random() * 15) + 40, (int)(Math.random() * 15),0,0,0},true,wh);
    }




}
