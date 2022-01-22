package spadeintruders;

import javax.swing.JFrame;
import java.awt.*;

public class Klatka extends JFrame {
    private Canvas plotno;

    public Klatka(int szerokosc, int wysokosc) {
        setTitle("Spade Intruders");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        plotno = new Canvas();
        plotno.setPreferredSize(new Dimension(szerokosc, wysokosc));
        plotno.setFocusable(false);

        add(plotno);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }
}