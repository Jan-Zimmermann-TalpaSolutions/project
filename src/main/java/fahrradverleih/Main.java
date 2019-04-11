package fahrradverleih;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    private Control control;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.control = new Control(primaryStage);
    }
}
