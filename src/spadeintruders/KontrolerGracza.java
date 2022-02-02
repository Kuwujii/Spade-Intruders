package spadeintruders;

import java.awt.event.KeyEvent;

public class KontrolerGracza implements Kontroler {
    private Wejscie wejscie;

    public KontrolerGracza(Wejscie wejscie) {
        this.wejscie = wejscie;
    }

    @Override
    public boolean czyProsiWLewo() {
        return this.wejscie.czyWcisniete(KeyEvent.VK_A);
    }

    @Override
    public boolean czyProsiWPrawo() {
        return this.wejscie.czyWcisniete(KeyEvent.VK_D);
    }

    public boolean czyStrzela() {
        return this.wejscie.czyWcisniete(KeyEvent.VK_SPACE);
    }
}
