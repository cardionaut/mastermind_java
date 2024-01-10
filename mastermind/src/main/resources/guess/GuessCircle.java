package mastermind.src.main.resources.guess;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Paint;

public class GuessCircle extends Circle {
    public static GuessCircle selected = null;
    public static String defaultColor = "grey";
    private String color = defaultColor;

    public GuessCircle(int radius) {
        super(radius, Paint.valueOf(defaultColor));
        this.setStroke(getFill());
    }

    public GuessCircle(int radius, String color) {
        super(radius, Paint.valueOf(color));
        this.setStroke(getFill());
        this.color = color;
    }

    public void setColor(String color) {
        this.setFill(Paint.valueOf(color));
        this.setStroke(getFill());
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    public void selectCircle() {
        this.setStroke(Paint.valueOf("black"));
    }

    public void deselectCircle() {
        this.setStroke(getFill());
    }
}
