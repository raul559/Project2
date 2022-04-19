/*Project 2 Black Jack
 * Raul Flores CS 161
 *
 * Project Description: Create a blackjack game that can allows a user to hit or stand and properly simulates the response
 *                       of the dealer using Algorithms.
 * */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BlackJacktableGUI extends Application {
    public final Player player = new Player();
    public final Player dealer = new Player();
    private final Button startBut = new Button("Start");
    private final Button hitBut = new Button("Hit");
    private final Button standBut = new Button("Stand");
    private final GridPane grid = new GridPane();
    private final GridPane playerGrid = new GridPane();
    private final GridPane dealerGrid = new GridPane();
    private final Deck deck = new Deck();
    private final Label dealerValue = new Label();
    private final Label playerValue = new Label();
    private final Label winner = new Label();
    private final HBox playerBox = new HBox(playerGrid);
    private HBox dealerBox = new HBox(dealerGrid);
    private int playerWin = 0;
    private final Label playerScore = new Label("Player Score: " + playerWin);
    private int dealerWin = 0;
    private final Label dealerScore = new Label("Dealer Score: " + dealerWin);

    private int counter = 0;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {

        {
            //Setting layout of JavaFX
            Label dealerHand = new Label("Dealer Hand");
            Label playerHand = new Label("Player Hand");

            VBox box = new VBox();
            VBox scores = new VBox(playerScore, dealerScore);
            VBox outcome = new VBox(winner);

            HBox buttons = new HBox(10, startBut, hitBut, standBut);
            buttons.setAlignment(Pos.CENTER);
            HBox dealerStat = new HBox(dealerHand, dealerValue);
            dealerStat.setSpacing(200);
            dealerBox.setPrefSize(600, 150);
            HBox playerStat = new HBox(playerHand, playerValue);
            playerStat.setSpacing(200);
            playerBox.setPrefSize(600, 150);

            grid.setPadding(new Insets(20));

            grid.add(dealerStat, 0, 0);
            grid.add(dealerBox, 0, 1);
            grid.add(playerStat, 0, 2);
            grid.add(playerBox, 0, 3);
            grid.add(buttons, 0, 4);
            grid.add(scores, 0, 5);
            grid.add(outcome, 1, 6);
            grid.setVgap(10);
            grid.setHgap(10);

            Label[] labels = new Label[3];
            for (int i = 0; i < labels.length; i++) {
                labels[i] = new Label();
                box.getChildren().add(labels[i]);
            }

            hitBut.setDisable(true);
            standBut.setDisable(true);

            startBut.setOnAction(Event -> StartGame());

            hitBut.setOnAction(Event -> {
                player.hit();
                updateHand(player, playerBox, playerValue);
                if (!player.bust()) {
                    playerValue.setText("Value:" + player.valueOfHand());
                    counter++;
                } else {
                    endGame();
                }
            });
            //Simulate Dealer Actions
            standBut.setOnAction(Event -> {
                counter = 1;
                if (!dealer.bust() || !dealer.stand(player.valueOfHand())) {
                    dealer.hit();
                    updateHand(dealer, dealerBox, dealerValue);
                    dealerValue.setText("Value: " + dealer.valueOfHand());
                    endGame();
                } else {
                    endGame();
                }

            });

            Scene s = new Scene(grid);
            s.getStylesheets().add("style.css");
            primaryStage.setHeight(600);
            primaryStage.setWidth(900);
            primaryStage.setTitle("Black Jack");
            primaryStage.setScene(s);

            primaryStage.show();
        }

    }

    public void StartGame() {
        playerGrid.getChildren().clear();
        dealerGrid.getChildren().clear();
        winner.setText("");
        counter = 0;
        deck.reset();
        dealer.clearHand();
        player.clearHand();
        dealer.hit();
        dealerBox = new HBox(dealer.getHand().get(0));
        dealerValue.setText("Value: " + dealer.valueOfHand() + "");
        playerValue.setText("Value: " + 0);
        grid.add(dealerBox, 0, 1);
        startBut.setDisable(true);
        hitBut.setDisable(false);
        standBut.setDisable(false);
    }

    void updateHand(Player p, HBox box, Label handValue) {
        //Updating hand and Images
        String cardface = p.getHand().get(counter).getFace() + ".png";
        Image cardImage = new Image("file:cards/" + cardface);
        ImageView image = new ImageView(cardImage);
        image.setImage(cardImage);
        image.setFitHeight(130);
        image.setFitWidth(100);
        box = new HBox(image);
        if (p.equals(player)) {
            playerGrid.add(box, counter, 3);
        } else if (p.equals(dealer)) {
            dealerGrid.add(box, counter, 1);
        }
    }

    void endGame() {
        standBut.setDisable(true);
        hitBut.setDisable(true);
        startBut.setDisable(false);
        //Determining Winner
        if (dealer.bust()) {
            dealerValue.setText("Value: " + dealer.valueOfHand() + " Bust!");
            winner.setText("Player Wins!");
            playerWin++;
            playerScore.setText("Player Score: " + playerWin);
        } else if (player.bust()) {
            playerValue.setText("Value: " + player.valueOfHand() + " Bust!");
            winner.setText("Dealer Wins!");
            dealerWin++;
            dealerScore.setText("Dealer Score: " + dealerWin);
        } else if (player.valueOfHand() > dealer.valueOfHand()) {
            winner.setText("Player Wins!");
            playerWin++;
            playerScore.setText("Player Score: " + playerWin);
        } else if (player.valueOfHand() < dealer.valueOfHand()) {
            winner.setText("Dealer Wins!");
            dealerWin++;
            dealerScore.setText("Dealer Score: " + dealerWin);
        } else if (dealer.valueOfHand() == player.valueOfHand()) {
            winner.setText("Push! Tie!");
        }
    }

}
