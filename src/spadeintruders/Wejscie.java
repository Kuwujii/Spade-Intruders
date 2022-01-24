package spadeintruders;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Wejscie implements KeyListener {
    private boolean[] wcisniete;

    public Wejscie() {
        this.wcisniete = new boolean[255];
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        this.wcisniete[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.wcisniete[e.getKeyCode()] = false;
    }

    public boolean czyWcisniete(int kodKlawisza) {
        return this.wcisniete[kodKlawisza];
    }
}
