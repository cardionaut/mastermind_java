package mastermind.src.main.resources.feedback;

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
}
