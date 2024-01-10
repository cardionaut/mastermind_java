package mastermind.src.main.java;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mastermind.src.main.resources.guess.BigCircle;

public class App extends Application {
    private static final int BIG_RADIUS = 20;
    private static final int PADDING = BIG_RADIUS / 2;
    private static final int BIG_SPACING = BIG_RADIUS / 4;
    private static final int NUM_GUESSES = 8;
    private static final int NUM_TO_GUESS = 4;
    private static final String[] COLORS = { "#B03A2E", "#633974", "#21618C", "#117864", "#9A7D0A", "#935116" };
    private static final int WIDTH = (2 * BIG_RADIUS + BIG_SPACING) * COLORS.length + PADDING * 2;
    private static final int HEIGHT = (2 * BIG_RADIUS + BIG_SPACING) * (NUM_GUESSES + 3) + PADDING * 2;

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

        VBox feedback = new VBox(BIG_SPACING);
        for (int i = 0; i < NUM_GUESSES; i++) {
            Button button = new Button("Ok");
            button.setMinWidth(2 * BIG_RADIUS + 1);
            button.setMinHeight(2 * BIG_RADIUS + 1);
            feedback.getChildren().add(button);
        }
        layout.setRight(feedback);

        Scene view = new Scene(layout, WIDTH, HEIGHT);
        window.setScene(view);
        window.show();
    }

    public void guessGridClicked(MouseEvent event) {
        BigCircle circle = (BigCircle) event.getTarget();
        if (BigCircle.selected == circle) {
            BigCircle.selected = null;
            circle.deselectCircle();
        } else {
            if (BigCircle.selected != null) {
                BigCircle.selected.deselectCircle();
            }
            BigCircle.selected = circle;
            circle.selectCircle();
        }
    }

    public void colorGridClicked(MouseEvent event) {
        BigCircle circle = (BigCircle) event.getTarget();
        if (BigCircle.selected != null) {
            BigCircle.selected.setColor(circle.getColor());
            BigCircle.selected.deselectCircle();
            BigCircle.selected = null;
        }
    }
}
