package mastermind.src;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(App.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Mastermind");
        primaryStage.show();
    }
}
