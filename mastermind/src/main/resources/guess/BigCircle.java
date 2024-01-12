package mastermind.src.main.resources.guess;

import javafx.scene.shape.Circle;
import mastermind.src.main.java.App;
import javafx.scene.paint.Paint;

public class BigCircle extends Circle {
    public static final String defaultColor = App.DEFAULT_COLOR;
    public static final double strokeWidth = App.BIG_STROKE_WIDTH;
    public static BigCircle selected = null;

    public BigCircle(double radius) {
        super(radius, Paint.valueOf(defaultColor));
        super.setStyle("-fx-stroke-width: %f;".formatted(strokeWidth));
        this.setStroke(getFill());
    }

    public BigCircle(double radius, String color) {
        super(radius, Paint.valueOf(color));
        super.setStyle("-fx-stroke-width: %f;".formatted(strokeWidth));
        this.setStroke(getFill());
    }
    
    public void setColor(String color) {
        this.setFill(Paint.valueOf(color));
        this.setStroke(getFill());
    }
    
    public void setColor(Paint color) {
        this.setFill(color);
        this.setStroke(getFill());
    }
    
    public String getColor() {
        return this.getFill().toString();
    }
    
    public void resetColor() {
        this.setColor(defaultColor);
    }
    
    public void selectCircle() {
        this.setStroke(Paint.valueOf("black"));
    }

    public void deselectCircle() {
        this.setStroke(getFill());
    }
}
