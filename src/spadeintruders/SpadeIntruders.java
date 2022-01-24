package spadeintruders;

public class SpadeIntruders {
    public static void main(String[] args) {
        new Thread(new PetlaGry(new Gra(1280, 720))).start();; //Tworzenie wątku obsługującego całą gre
    }
}