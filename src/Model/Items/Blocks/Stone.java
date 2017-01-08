package Model.Items.Blocks;

import java.awt.event.MouseEvent;

/**
 * Created by Felix on 07.01.2017.
 */
public class Stone extends Block  {

    public Stone(double posX, double posY, boolean solid, String name) {
        super(posX, posY, true, "Stone");
    }

    @Override
    public void keyReleased(int key) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
