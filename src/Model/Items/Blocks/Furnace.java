package Model.Items.Blocks;

import Model.Items.Blocks.Block;

/**
 * Created by Alex on 15.12.2016.
 */
public class Furnace extends Block {

    public Furnace(double posX, double posY) {
        super(posX, posY, new int[] {128, 128, 128, 64, 64, 64}, true);
    }
}
