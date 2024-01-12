package mastermind.src.main.resources.feedback;

import javafx.scene.shape.Circle;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import mastermind.src.main.java.App;

public class Feedback {
    public static final int numToGuess = App.NUM_TO_GUESS;
    public static final double smallRadius = App.BIG_RADIUS / 4;

    public Feedback() {
        GridPane feedbackGrid = new GridPane();
        for (int i = 0; i < numToGuess / 2; i++) {
            for (int j = 0; j < numToGuess / 2; j++) {
                Circle smallCircle = new Circle(smallRadius, Paint.valueOf("white"));
                smallCircle.setStroke(Paint.valueOf("black"));
                feedbackGrid.add(smallCircle, i, j);
            }
        }
    }
}
