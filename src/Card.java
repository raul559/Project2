import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card extends ImageView {
    private static final String[] FACES = {"A", "2", "3", "4", "5", "6", "7", "8",
            "9", "10", "J", "K", "Q"};
    private static final int HEIGHT = 130;
    String face;

    Card(String face) {
        this.face = face;
        String cardFace = face + ".png";
        Image cardImage = new Image("file:cards/" + cardFace);
        setImage(cardImage);
        setFitHeight(HEIGHT);
        setPreserveRatio(true);
    }

    public String getFace() {
        return face;
    }
    //Assign number Values
    public int valueOf() {
        int value = 0;
        if (face.equals("J") || face.equals("Q") || face.equals("K")) {
            return 10;
        } else if (face.equals("A")) {
            //handle case where ace is 1
            return 11;
        } else {
            for (int i = 1; i < FACES.length; i++) {
                if (face.equals(FACES[i])) {
                    value = i + 1;
                }
            }
        }
        return value;
    }
}
