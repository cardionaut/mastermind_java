package mastermind.src.main.resources.feedback;

import java.util.stream.IntStream;
import javafx.scene.shape.Circle;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import mastermind.src.main.java.App;

public class Feedback extends GridPane {
    public static final String defaultColor = App.DEFAULT_COLOR;
    public static final int numToGuess = App.NUM_TO_GUESS;
    public static final double smallRadius = App.BIG_RADIUS / 2 + App.BIG_STROKE_WIDTH / 4;

    public Feedback() {
        super();
        for (int i = 0; i < numToGuess / 2; i++) {
            for (int j = 0; j < numToGuess / 2; j++) {
                Circle smallCircle = new Circle(smallRadius, Paint.valueOf(defaultColor));
                super.add(smallCircle, i, j);
            }
        }
    }

    public void endGuess(int[] guess) {
        for (int i = 0; i < numToGuess; i++) {
            final int index = i;  // for lambda
            if (guess[i] == App.solution[i]) {
                // correct color and position
                Circle smallCircle = (Circle) super.getChildren().get(i);
                smallCircle.setFill(Paint.valueOf("#242a2b"));
            } else if (IntStream.of(App.solution).anyMatch(x -> x == guess[index])) {
                // correct color
                Circle smallCircle = (Circle) super.getChildren().get(i);
                smallCircle.setFill(Paint.valueOf("#58767a"));
            }
        }
    }
}
