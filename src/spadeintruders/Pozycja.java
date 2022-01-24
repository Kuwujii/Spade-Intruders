package spadeintruders;

public class Pozycja { //Prosta klasa przechowywująca pozycje
    private int x;
    private int y;

    public Pozycja(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
