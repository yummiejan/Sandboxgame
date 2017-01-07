package Model.Items.Blocks;

import Model.Items.Blocks.Block;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by 204g04 on 12.12.2016.
 */
public class Dirt extends Block {

    private String content;

    public Dirt(double posX, double posY) {
        super(posX, posY,true,"Dirt");
        content = "Dirt";
    }

    /**
     * Returns content
     * @return content
     */
    public String getContent() {
        return content;
    }

    @Override
    public void keyReleased(int key) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
