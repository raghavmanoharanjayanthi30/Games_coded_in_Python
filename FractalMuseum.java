import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import javafx.scene.paint.Color;
/**
 * Represents the FractalMuseum Class.
 * In order to help learn course concepts, I worked on the homework with nobody else,
 * discussed homework topics and issues with nobody else, and/or consulted related
 * material which is entitled Java How to Program by Paul Deitel and Harvey Deitel.
 *
 * @author rjayanthi30 (Raghav Raahul Manoharan Jayanthi)
 * @version 1.0
 */
public class FractalMuseum extends Application {
    private int player1Wins = 0;
    private int player2Wins = 0;
    private int player3Wins = 0;
    private int player4Wins = 0;
    private int noOfDistinctSquares = 0;
    private String fileName = "GameResults.txt";
    private File fileOut = new File(fileName);
    private PrintWriter filePrint = null;
    private int count = 0;
    private Color currentColor = Color.TEAL;
    /**
    * Creates a FractalMuseum object and calls its start method
    *
    * @param args arguments
    */
    public static void main(String[] args) {
        launch(args);
    }
     /**
    * Creates the GUI by adding layouts, controls and event handlers.
    *
    * @param stage - Stage object
    */
    public void start(Stage stage) {
        Button newCanvasBtn = new Button("New Game!");
        TextField p1GuessTxtFld = new TextField("P1 Guess");
        p1GuessTxtFld.setDisable(true);
        TextField p2GuessTxtFld = new TextField("P2 Guess");
        p2GuessTxtFld.setDisable(true);
        TextField p3GuessTxtFld = new TextField("P3 Guess");
        p3GuessTxtFld.setDisable(true);
        TextField p4GuessTxtFld = new TextField("P4 Guess");
        p4GuessTxtFld.setDisable(true);
        Button submitGuessesBtn = new Button("Submit Guesses!");
        submitGuessesBtn.setDisable(true);
        Label player1ScoreLbl = new Label("Player 1 Score: ");
        Label player2ScoreLbl = new Label("     Player 2 Score: ");
        Label player3ScoreLbl = new Label("     Player 3 Score: ");
        Label player4ScoreLbl = new Label("     Player 4 Score: ");
        Label player1Score = new Label(String.valueOf(player1Wins));
        Label player2Score = new Label(String.valueOf(player2Wins));
        Label player3Score = new Label(String.valueOf(player3Wins));
        Label player4Score = new Label(String.valueOf(player4Wins));
        Label fractalEntriesLbl = new Label("Number of Fractals:");
        Label fractalEntries = new Label();
        HBox topPane = new HBox();
        topPane.setAlignment(Pos.CENTER);
        topPane.setSpacing(30);
        topPane.getChildren().addAll(newCanvasBtn);
        HBox centerPane = new HBox();
        centerPane.setAlignment(Pos.CENTER);
        centerPane.setSpacing(30);
        Pane pane = new Pane();
        pane.setPrefWidth(500);
        pane.setPrefHeight(500);
        centerPane.getChildren().add(pane);
        HBox thirdRow = new HBox();
        thirdRow.setAlignment(Pos.CENTER);
        thirdRow.setSpacing(10);
        thirdRow.getChildren().addAll(p1GuessTxtFld, p2GuessTxtFld, p3GuessTxtFld, p4GuessTxtFld, submitGuessesBtn);
        HBox fourthRow = new HBox();
        fourthRow.setAlignment(Pos.CENTER);
        fourthRow.getChildren().addAll(player1ScoreLbl, player1Score, player2ScoreLbl, player2Score);
        fourthRow.getChildren().addAll(player3ScoreLbl, player3Score, player4ScoreLbl, player4Score);
        HBox fifthRow = new HBox();
        fifthRow.setAlignment(Pos.CENTER);
        fifthRow.setSpacing(10);
        fifthRow.getChildren().add(fractalEntriesLbl);
        fifthRow.getChildren().add(fractalEntries);
        VBox bottomPane = new VBox();
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setSpacing(10);
        bottomPane.getChildren().add(topPane);
        bottomPane.getChildren().add(centerPane);
        bottomPane.getChildren().add(thirdRow);
        bottomPane.getChildren().add(fourthRow);
        newCanvasBtn.setOnAction(event -> {
                p1GuessTxtFld.setDisable(false);
                p1GuessTxtFld.setText("");
                p2GuessTxtFld.setDisable(false);
                p2GuessTxtFld.setText("");
                p3GuessTxtFld.setDisable(false);
                p3GuessTxtFld.setText("");
                p4GuessTxtFld.setDisable(false);
                p4GuessTxtFld.setText("");
                submitGuessesBtn.setDisable(false);
                noOfDistinctSquares = FractalFactory.drawFractal(pane, currentColor);
                fractalEntries.setText(String.valueOf(noOfDistinctSquares));
                bottomPane.getChildren().removeAll(fifthRow);
            });
        submitGuessesBtn.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                try {
                    filePrint = new PrintWriter(fileOut);
                    int score1 = Integer.parseInt(p1GuessTxtFld.getText());
                    int score2 = Integer.parseInt(p2GuessTxtFld.getText());
                    int score3 = Integer.parseInt(p3GuessTxtFld.getText());
                    int score4 = Integer.parseInt(p4GuessTxtFld.getText());
                    if (score1 < 0 || score2 < 0 || score3 < 0 || score4 < 0) {
                        throw new NumberFormatException();
                    }
                    int player1Difference = Math.abs(score1 - noOfDistinctSquares);
                    int player2Difference = Math.abs(score2 - noOfDistinctSquares);
                    int player3Difference = Math.abs(score3 - noOfDistinctSquares);
                    int player4Difference = Math.abs(score4 - noOfDistinctSquares);
                    int a = Math.min(player1Difference, player2Difference);
                    int b = Math.min(a, player3Difference);
                    int c = Math.min(b, player4Difference);
                    if (player1Difference != player2Difference && player1Difference != player3Difference
                        && player1Difference != player4Difference && player2Difference != player3Difference
                        && player2Difference != player4Difference && player3Difference != player4Difference) {
                        if (c == player1Difference) {
                            player1Wins++;
                            count++;
                            player1Score.setText(String.valueOf(player1Wins));
                        } else if (c == player2Difference) {
                            player2Wins++;
                            count++;
                            player2Score.setText(String.valueOf(player2Wins));
                        } else if (c == player3Difference) {
                            player3Wins++;
                            count++;
                            player3Score.setText(String.valueOf(player3Wins));
                        } else if (c == player4Difference) {
                            player4Wins++;
                            count++;
                            player4Score.setText(String.valueOf(player4Wins));
                        }
                    } else {
                        count++;
                        Alert e = new Alert(Alert.AlertType.ERROR);
                        e.setTitle("Tie");
                        e.setHeaderText("Two or more players have the same guess.");
                        e.setContentText("Scores for all players remain unchanged.");
                        e.showAndWait();
                    }
                    p1GuessTxtFld.setDisable(true);
                    p1GuessTxtFld.setText("P1 Guess");
                    p2GuessTxtFld.setDisable(true);
                    p2GuessTxtFld.setText("P2 Guess");
                    p3GuessTxtFld.setDisable(true);
                    p3GuessTxtFld.setText("P3 Guess");
                    p4GuessTxtFld.setDisable(true);
                    p4GuessTxtFld.setText("P4 Guess");
                    submitGuessesBtn.setDisable(true);
                    bottomPane.getChildren().add(fifthRow);
                    filePrint.printf("Total number of rounds: %d\n", count);
                    filePrint.printf("Number of rounds won by Player 1: %d\n", player1Wins);
                    filePrint.printf("Number of rounds won by Player 2: %d\n", player2Wins);
                    filePrint.printf("Number of rounds won by Player 3: %d\n", player3Wins);
                    filePrint.printf("Number of rounds won by Player 4: %d\n", player4Wins);
                    filePrint.printf("Number of tied rounds: %d\n",
                        count - player1Wins - player2Wins - player3Wins - player4Wins);
                } catch (NumberFormatException e) {
                    Alert f = new Alert(Alert.AlertType.ERROR);
                    f.setTitle("Error");
                    f.setHeaderText("Invalid Guess");
                    f.setContentText("That is not a valid guess. Enter a non-negative integer.");
                    f.showAndWait();
                } catch (FileNotFoundException e) {
                    System.out.println("File not found");
                } finally {
                    if (filePrint != null) {
                        filePrint.close();
                    }
                }
            } });
        Scene scene = new Scene(bottomPane);
        stage.setScene(scene);
        stage.setTitle("Fractal Museum");
        stage.show();
    }
}