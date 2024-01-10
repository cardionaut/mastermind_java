package mastermind.src.main.resources.guess;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Paint;

public class BigCircle extends Circle {
    public static BigCircle selected = null;
    public static String defaultColor = "#9daba9";
    private String color = defaultColor;

    public BigCircle(int radius) {
        super(radius, Paint.valueOf(defaultColor));
        this.setStroke(getFill());
    }

    public BigCircle(int radius, String color) {
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
