package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Jean-Pierre on 15.11.2016.
 */
public class DrawingPanel extends JPanel implements ActionListener, KeyListener, MouseListener {

    //Attribute
    private int dt;
    private long lastLoop, elapsedTime;

    // Referenzen
    private ArrayList<DrawableObject> drawableObjects;

    /**
     * Konstruktor
     */
    public DrawingPanel(){
        super();
        addMouseListener(this);
        setDoubleBuffered(true);
        drawableObjects = new ArrayList<>();
        dt = 35; //Vernünftiger Startwert
        lastLoop = System.nanoTime();
        javax.swing.Timer timer = new javax.swing.Timer(dt, this);
        timer.start();
    }

    /**
     * Zeichnen aller registrierten Objekte
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        elapsedTime = System.nanoTime() - lastLoop;
        lastLoop = System.nanoTime();
        dt = (int) ((elapsedTime / 1000000L)+0.5);
        if ( dt == 0 ) dt = 1;
        Graphics2D g2d = (Graphics2D) g;
        Iterator<DrawableObject> iterator = drawableObjects.iterator();
        while (iterator.hasNext()){
            DrawableObject tempDO = iterator.next();
            tempDO.draw(this,g2d);
            tempDO.update((double)dt/1000);
        }
    }

    /**
     * Diese Methode fügt ein neues Objekt zum Zeichnen hinzu. Die
     * Klasse des Objekts muss mindestens das Interface DrawableObject implementieren.
     * @param d Das ab sofort zu zeichnende Objekt
     */
    public void addObject(DrawableObject d){
        drawableObjects.add(d);
    }

    /**
     * Diese Methode entfernt ein Objekt aus der Menge der zu zeichnenden Objekte. Die
     * Klasse des Objekts muss mindestens das Interface DrawableObject implementieren.
     * @param d Das ab sofort nicht mehr zu zeichnende Objekt
     */
    public void removeObject(DrawableObject d){
        drawableObjects.remove(d);
    }

    /**
     * Timer-Repaint
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    /**
     * Unbenutzt bis auf Weiteres
     */
    public void keyTyped(KeyEvent e){

    }

    /**
     * Weitergabe an Zeichnungsobjekte.
     */
    public void keyPressed(KeyEvent e){
        Iterator<DrawableObject> iterator = drawableObjects.iterator();
        while (iterator.hasNext()){
            DrawableObject tempDO = iterator.next();
            if (tempDO instanceof InteractableObject){
                ((InteractableObject)tempDO).keyPressed(e.getKeyCode());
            }
        }
    }

    /**
     * Weitergabe an Zeichnungsobjekte.
     */
    public void keyReleased(KeyEvent e){
        Iterator<DrawableObject> iterator = drawableObjects.iterator();
        while (iterator.hasNext()){
            DrawableObject tempDO = iterator.next();
            if (tempDO instanceof InteractableObject){
                ((InteractableObject)tempDO).keyReleased(e.getKeyCode());
            }
        }

    }

    /**
     * Unbenutzt bis auf Weiteres
     */
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Unbenutzt bis auf Weiteres
     */
    public void mouseReleased(MouseEvent e) {
        Iterator<DrawableObject> iterator = drawableObjects.iterator();
        while (iterator.hasNext()){
            DrawableObject tempDO = iterator.next();
            if (tempDO instanceof InteractableObject){
                ((InteractableObject)tempDO).mouseReleased(e);
            }
        }
    }

    /**
     * Unbenutzt bis auf Weiteres
     */
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Unbenutzt bis auf Weiteres
     */
    public void mouseExited(MouseEvent e) {
    }

    /**
     * Weitergabe an Zeichnungsobjekte.
     */
    public void mouseClicked(MouseEvent e) {

    }

}
