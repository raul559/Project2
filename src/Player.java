import java.util.ArrayList;
import java.util.Random;

public class Player {
    private static final Deck deck = new Deck();
     private final ArrayList<Card> hand = new ArrayList<>(12);


     Player() {
        for (int i = 0; i < 12; i++){
            hand.add(i,deck.dealCard());
        }
    }

    ArrayList<Card> getHand() {
        return hand;
    }

    int valueOfHand() {
        int handValue = 0;
        for (Card card : hand) {
            int cardVal = card.valueOf();
            handValue += cardVal;
        }
        return handValue;
    }

    void clearHand() {
        hand.clear();
    }
    //Determines whether to Stand
    boolean stand(int otherPlayerValue) {
        boolean stand = false;
        if (valueOfHand() > otherPlayerValue) {
            stand = true;
        }else if( valueOfHand() > 19){
            stand = true;
        }
        else if (valueOfHand() >= 16) {
            Random rand = new Random();
            stand = rand.nextBoolean();
        }
        return  stand;
    }
        void hit () {
            hand.add(deck.dealCard());
        }

        boolean bust () {
            return valueOfHand() > 21;
        }

    }
