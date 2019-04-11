package fahrradverleih;

import javafx.stage.Stage;

import java.io.IOException;

public class Control {
    private Model model;
    private View view;
    private Stage stage;

    public Control(Stage stage) {
        this.model = new Model();
        this.stage = stage;
        try{
            this.view = new View(this.model, this, this.stage);
        } catch(IOException e){

        }
    }
    //ActionListener eigentlich auch im Control
    //mein Exceptions auch
}
