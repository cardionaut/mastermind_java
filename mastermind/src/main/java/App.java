package mastermind.src.main.java;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mastermind.src.main.resources.guess.BigCircle;

public class App extends Application {
    public static final double BIG_RADIUS = 20.0;
    public static final int NUM_GUESSES = 8;
    public static final int NUM_TO_GUESS = 4;
    public static final String DEFAULT_COLOR = "#9daba9";
    public static final String[] COLORS = { "#B03A2E", "#633974", "#21618C", "#117864", "#9A7D0A", "#935116" };

    public static final double PADDING = BIG_RADIUS / 2;
    public static final double BIG_SPACING = BIG_RADIUS / 4;
    public static final double STROKE_WIDTH = BIG_RADIUS / 6;
    public static final double WIDTH = (2 * BIG_RADIUS + STROKE_WIDTH) * COLORS.length
            + BIG_SPACING * (COLORS.length - 1)
            + PADDING * 2;
    public static final double HEIGHT = (2 * BIG_RADIUS + STROKE_WIDTH + BIG_SPACING) * (NUM_GUESSES + 3) + PADDING * 2;

    public int currentGuessRow = 0;
    public int currentGuessCol = 0;

    public static void main(String[] args) {
        launch(App.class);
    }

    @Override
    public void start(Stage window) {
        window.setTitle("Mastermind");

        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(PADDING));
        layout.setTop(new Label("Find the correct color sequence:"));

        GridPane guessGrid = new GridPane(BIG_SPACING, BIG_SPACING);
        for (int i = 0; i < NUM_TO_GUESS; i++) {
            for (int j = 0; j < NUM_GUESSES; j++) {
                guessGrid.add(new BigCircle(BIG_RADIUS), i, j);
            }
        }
        guessGrid.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> guessGridClicked(event));
        layout.setLeft(guessGrid);

        GridPane colorGrid = new GridPane(BIG_SPACING, BIG_SPACING);
        for (int i = 0; i < COLORS.length; i++) {
            colorGrid.add(new BigCircle(BIG_RADIUS, COLORS[i]), i, 0);
        }
        colorGrid.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> colorGridClicked(event));
        layout.setBottom(colorGrid);

        VBox endGuess = new VBox(BIG_SPACING);
        for (int i = 0; i < NUM_GUESSES; i++) {
            Button endGuessButton = new Button("Ok");
            endGuessButton.setPrefWidth(2 * BIG_RADIUS + STROKE_WIDTH);
            endGuessButton.setPrefHeight(2 * BIG_RADIUS + STROKE_WIDTH);
            endGuess.getChildren().add(endGuessButton);
        }
        endGuess.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> endGuess(event));
        layout.setRight(endGuess);

        Scene view = new Scene(layout, WIDTH, HEIGHT);
        window.setScene(view);
        window.show();
    }

    public void guessGridClicked(MouseEvent event) {
        BigCircle circle = (BigCircle) event.getTarget();
        int row = GridPane.getRowIndex(circle);
        int col = GridPane.getColumnIndex(circle);
        if (row != currentGuessRow) { // ignore clicks on other rows
            return;
        }
        if (BigCircle.selected == circle) {
            BigCircle.selected = null;
            circle.deselectCircle();
        } else {
            if (BigCircle.selected != null) {
                BigCircle.selected.deselectCircle();
            }
            BigCircle.selected = circle;
            circle.selectCircle();
            this.currentGuessCol = col;
        }
    }

    public void colorGridClicked(MouseEvent event) {
        BigCircle circle = (BigCircle) event.getTarget();
        if (BigCircle.selected != null) {
            BigCircle.selected.setColor(circle.getColor());
            BigCircle.selected.deselectCircle();
            if (this.currentGuessCol < NUM_TO_GUESS - 1) {
                this.currentGuessCol++;
                BigCircle.selected = (BigCircle) ((GridPane) BigCircle.selected.getParent()).getChildren()
                        .get(this.currentGuessCol * NUM_GUESSES + this.currentGuessRow);
                BigCircle.selected.selectCircle();
            } else {
                BigCircle.selected = null;
            }
        }
    }

    public void endGuess(MouseEvent event) {
        System.out.println(event.getTarget());
        // Button button = (Button) event.getTarget();
    }
}
