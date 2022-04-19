import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private final ArrayList<Card> cards = new ArrayList<>(52);
    private static final String[] FACES = {"A", "2", "3", "4", "5", "6", "7", "8",
            "9,", "10", "J", "K", "Q"};

    Deck() {
        reset();
    }

    void reset() {
        cards.clear();
        //Setting deck of Cards
        for (int i = 0; i < 52; ) {
            for (String face : FACES) {
                cards.add(i, new Card(face));
                i++;
            }
        }

    }

    public Card dealCard() {
        Random rand = new Random();
        Card dealtCard =  cards.get(rand.nextInt(cards.size()));
        cards.remove(dealtCard);
        return dealtCard;
    }
}
