package mastermind.src.main.java;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mastermind.src.main.resources.guess.GuessCircle;

public class App extends Application {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 800;
    private static final int PADDING = 10;
    private static final int GUESS_RADIUS = 20;
    private static final int NUM_GUESSES = 8;
    private static final int NUM_CIRCLES = 4;

    public static void main(String[] args) throws Exception {
        launch(App.class);
    }

    @Override
    public void start(Stage window) throws Exception {
        window.setTitle("Mastermind");

        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(PADDING));
        layout.setTop(new Label("Choose the correct color sequence"));

        GridPane guesses = new GridPane();
        guesses.setHgap(GUESS_RADIUS / 4);
        guesses.setVgap(GUESS_RADIUS / 4);
        for (int i = 0; i < NUM_CIRCLES; i++) {
            for (int j = 0; j < NUM_GUESSES; j++) {
                guesses.add(new GuessCircle(GUESS_RADIUS), i, j);
            }
        }
        guesses.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> guessesClicked(event));
        layout.setLeft(guesses);

        Scene view = new Scene(layout, WIDTH, HEIGHT);
        window.setScene(view);
        window.show();
    }

    public void guessesClicked(MouseEvent event) {
        GuessCircle circle = (GuessCircle) event.getTarget();
        if (GuessCircle.selected == circle) {
            GuessCircle.selected = null;
            circle.deselectCircle();
        } else {
            if (GuessCircle.selected != null) {
                GuessCircle.selected.deselectCircle();
            }
            GuessCircle.selected = circle;
            circle.selectCircle();
        }
    }
}
