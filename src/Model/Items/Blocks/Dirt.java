package Model.Items.Blocks;

/**
 * Created by 204g04 on 12.12.2016.
 */
public class Dirt extends Block {

    /*private int randomR = (int)(Math.random() * 40) + 160;
    private int randomG = (int)(Math.random() * 50) + 80;
    private int randomB = (int)(Math.random() * 10) + 20;*/

    public Dirt(double posX, double posY) {
        super(posX, posY, new int[] {(int)(Math.random() * 15) + 100, (int)(Math.random() * 15) + 40, (int)(Math.random() * 15),0,0,0},true);
    }
}