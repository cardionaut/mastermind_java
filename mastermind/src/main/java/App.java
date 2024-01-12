package mastermind.src.main.java;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mastermind.src.main.resources.feedback.Feedback;
import mastermind.src.main.resources.guess.BigCircle;

public class App extends Application {
    public static final double BIG_RADIUS = 30.0;
    public static final int NUM_GUESSES = 8;
    public static final int NUM_TO_GUESS = 4;
    public static final String DEFAULT_COLOR = "#9daba9";
    public static final String[] COLORS = { "#B03A2E", "#633974", "#21618C", "#117864", "#9A7D0A", "#935116" };

    public static final double PADDING = BIG_RADIUS / 2;
    public static final double BIG_SPACING = BIG_RADIUS / 4;
    public static final double BIG_STROKE_WIDTH = BIG_RADIUS / 8;
    public static final double WIDTH = BIG_RADIUS * 2 * COLORS.length
            + BIG_SPACING * (COLORS.length - 1)
            + PADDING * 2;
    public static final double HEIGHT = (2 * BIG_RADIUS + BIG_STROKE_WIDTH + BIG_SPACING) * (NUM_GUESSES + 3)
            + PADDING * 2;

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

        // guesses (left)
        GridPane guessGrid = new GridPane(BIG_SPACING, BIG_SPACING);
        for (int i = 0; i < NUM_TO_GUESS; i++) {
            for (int j = 0; j < NUM_GUESSES; j++) {
                guessGrid.add(new BigCircle(BIG_RADIUS), i, j);
            }
        }
        guessGrid.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> guessGridClicked(event));
        BigCircle.selected = (BigCircle) guessGrid.getChildren().get(0);
        BigCircle.selected.selectCircle();
        layout.setLeft(guessGrid);

        // feedback (right)
        VBox feedback = new VBox(BIG_SPACING);
        for (int i = 0; i < NUM_GUESSES; i++) {
            feedback.getChildren().add(new Feedback());
        }
        layout.setRight(feedback);

        // end turn button and color selection (bottom)
        VBox bottom = new VBox(BIG_SPACING);
        Button endTurn = new Button("End Turn");
        endTurn.setPrefWidth(WIDTH - PADDING * 2);
        endTurn.setPrefHeight(BIG_RADIUS);
        endTurn.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> endGuess(event));
        bottom.getChildren().add(endTurn);
        GridPane colorGrid = new GridPane(BIG_SPACING, BIG_SPACING);
        for (int i = 0; i < COLORS.length; i++) {
            colorGrid.add(new Circle(BIG_RADIUS, Paint.valueOf(COLORS[i])), i, 0);
        }
        colorGrid.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> colorGridClicked(event));
        bottom.getChildren().add(colorGrid);
        layout.setBottom(bottom);

        Scene view = new Scene(layout, WIDTH, HEIGHT);
        window.setScene(view);
        window.show();
    }

    public void guessGridClicked(MouseEvent event) {
        BigCircle circle = null;
        try {
            circle = (BigCircle) event.getTarget();

        } catch (ClassCastException e) {
            return;
        }
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
        Circle circle = null;
        try {
            circle = (Circle) event.getTarget();

        } catch (ClassCastException e) {
            return;
        }
        if (BigCircle.selected != null) {
            BigCircle.selected.setColor(circle.getFill());
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
        System.out.println("End guess");
    }
}
