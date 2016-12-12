import Control.MainController;
import java.awt.*;
import View.MainFrame;

/**
 * Created by 204g07 on 09.12.2016.
 */
public class MainProgram {
    public static void main (String[] args){
        EventQueue.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        MainProgram.setup();
                    }
                });
    }

    private static void setup(){
        new MainController(new MainFrame("Fyn ist toll!",0,0,1200,700));
    }
}
